package com.comm.util.ui.recycleview;

import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.comm.util.R;
import com.comm.util.ui.recycleview.adapter.BaseQuickAdapter;
import com.comm.util.ui.recycleview.adapter.PullToRefreshAdapter;
import com.comm.util.ui.recycleview.data.DataServer;
import com.comm.util.ui.recycleview.data.entity.Status;
import timber.log.Timber;

/**
 * Created by Administrator on 2017/12/27 0027.
 */


interface RequestCallBack {
    void success(List<Status> data);

    void fail(Exception e);
}

class Request extends Thread {
    private static final int PAGE_SIZE = 6;
    private static boolean mFirstPageNoMore;
    private static boolean mFirstError = true;
    private final int mPage;
    private final RequestCallBack mCallBack;
    private final Handler mHandler;

    public Request(int page, RequestCallBack callBack) {
        mPage = page;
        mCallBack = callBack;
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }

//        if (mPage == 2 && mFirstError) {
//            mFirstError = false;
//            mHandler.post(new Runnable() {
//                @Override
//                public void run() {
//                    mCallBack.fail(new RuntimeException("fail"));
//                }
//            });
//        } else {
        int size = PAGE_SIZE;
        mFirstError = false;
//        if (mPage == 1) {
//            if (mFirstPageNoMore) {
//                size = 1;
//            }
//            mFirstPageNoMore = false;
//            if (!mFirstError) {
//                mFirstError = true;
//            }
//        } else if (mPage == 4) {
//            size = 1;
//        }
        final int dataSize = size;
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mCallBack.success(DataServer.getSampleData(dataSize));
            }
        });
    }
}


public class PullToRefreshFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefresh;
    private PullToRefreshAdapter mAdapter;

    private int mNextRequestPage = 1;

    public static Fragment newInstance() {
        return new PullToRefreshFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pulltorefresh, container, false);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mSwipeRefresh = view.findViewById(R.id.swipe_refresh);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
//        layoutManager.setOrientation(VERTICAL); //默认竖直布局
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL));
        initAdapter();
        addHeadView();
        initRefreshLayout();
        refresh();
        return view;
    }

    private void addHeadView() {
        View headView = LayoutInflater.from(getActivity()).inflate(R.layout.head_view, (ViewGroup) mRecyclerView.getParent(), false);
        headView.findViewById(R.id.iv).setVisibility(View.GONE);
        ((TextView) headView.findViewById(R.id.tv)).setText("change load view");
        mAdapter.addHeaderView(headView);
    }

    private void initRefreshLayout() {
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }

    private void refresh() {
        mNextRequestPage = 1;
        mAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        new Request(mNextRequestPage, new RequestCallBack() {
            @Override
            public void success(List<Status> data) {
                setData(true, data);
                mAdapter.setEnableLoadMore(true);
                mSwipeRefresh.setRefreshing(false);
            }

            @Override
            public void fail(Exception e) {

            }
        }).start();
    }

    private void loadMore() {
        new Request(mNextRequestPage, new RequestCallBack() {
            @Override
            public void success(List<Status> data) {
                setData(false, data);
            }

            @Override
            public void fail(Exception e) {

            }
        }).start();
    }

    private void setData(boolean isRefresh, List<Status> data) {
        mNextRequestPage++;
        final int size = data == null ? 0 : data.size();
        if (isRefresh) {
            mAdapter.setNewData(data);
        } else {
            if (size > 0) {
                mAdapter.addData(data);
            }
        }
        if (mNextRequestPage == 5) {     //到了最后一页
            Timber.d("mNextRequestPage  " + mNextRequestPage);
            mAdapter.loadMoreEnd(isRefresh);
            Toast.makeText(getActivity(), "no more data", Toast.LENGTH_SHORT).show();
        } else {
            mAdapter.loadMoreComplete();    //数据已经下载完成，底部不用显示了
        }
    }


    private void initAdapter() {
        mAdapter = new PullToRefreshAdapter();
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        }, mRecyclerView);


        mRecyclerView.setAdapter(mAdapter);
    }
}
