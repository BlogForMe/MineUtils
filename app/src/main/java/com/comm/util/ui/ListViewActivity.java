package com.comm.util.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.android.util.DisplayUtils;
import com.comm.util.R;
import timber.log.Timber;

public class ListViewActivity extends AppCompatActivity {
    static String[] wheelFirst = {"1: \"一天\"", "2: \"两天\"", "3: \"三天\"", "7: \"一周\"", "30: \"一月\""};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
//
//        ArrayList<HashMap<String, Object>> appointList=new ArrayList();
//        for (int i=0;i<=5;i++){
//            HashMap<String, Object> map = new HashMap<String, Object>();
//            map.put("tv_appoint_doctor", "预约医生："+i+"\n2018-11-13 20:0"+i);
//            appointList.add(map);
//        }
//
//
//       ListView lv = (ListView) findViewById(R.id.list_view);
//        SimpleAdapter mSimpleAdapter = new SimpleAdapter(this,appointList,//需要绑定的数据
//                R.layout.item_app,
//                new String[] {"tv_appoint_doctor"},
//                new int[]{R.id.tv_appoint_doctor}
//        );
//        lv.setAdapter(mSimpleAdapter);



        int pxo = (int) DisplayUtils.dp2px(this, 1);
        Timber.i(pxo+"ScreenW    " + DisplayUtils.getScreenW(this) + "ScreenH   " + DisplayUtils.getScreenH(this));


        int pxWidth = (int) DisplayUtils.getScreenW(this);
//        Timber.i("pxWidth   " + DisplayUtils.px2dip(this,1920));
    }
}
