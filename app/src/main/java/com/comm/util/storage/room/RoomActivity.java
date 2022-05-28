package com.comm.util.storage.room;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.comm.util.R;
import com.comm.util.bean.BaseCount;
import com.comm.util.openlib.rxretrofit.retrofit.IApiStores;
import com.comm.util.openlib.rxretrofit.retrofit.RetrofitFactory;
import com.comm.util.utils.DateUtils;
import com.comm.util.utils.GsonUtil;
import com.comm.util.utils.NetworkUtil;
import com.comm.util.utils.RoomUtil;
import com.comm.util.utils.ThreadPoolUtil;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;
import timber.log.Timber;

import static com.comm.util.utils.DateUtils.yyyyMMDDHHmmss;

public class RoomActivity extends AppCompatActivity {

    public final static int DEVICE_TYPE_TEMP = 7;
    static int i =0;
    BleEntity bleEnty;
    private ExecutorService exec;


//    public void btRxSelect(View view){
////                BleEntity bleUser = db.bleDao().findUserById(1);
////                Timber.i(String.valueOf(bleUser));
////                BleEntity data = db.bleDao().findUserById(1);
//
//                db.bleDao().getAll()
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new Consumer<List<BleEntity>>() {
//                            @Override
//                            public void accept(List<BleEntity> bleList) throws Exception {
//                                for (BleEntity ble:bleList){
//                                    Timber.i(String.valueOf(ble));
//                                }
//                            }
//                        }, new Consumer<Throwable>() {
//                            @Override
//                            public void accept(Throwable throwable) throws Exception {
//                                Timber.e(throwable);
//                            }
//                        });
//    }


//    String requestStr = "{\"bindUserType\":1,\"branchNo\":1,\"boxId\":\"JYT20190100000033\",\"token\":\"cdf12c127ed5f2dd1ee50920b72bdc54ea42e6659acd20b772755e06cc616004782575d8a9d46747cc3411c3b26d2203\",\"operate_way\":\"0\",\"temperatureForehead\":\"36.6\",\"patientCode\":12050,\"videoNow\":false,\"macAddress\":\"C6:05:04:04:7D:20\",\"equipmentModel\":\"Bluetooth BP\"}";
//    RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), requestStr);
//    public void RxRetrofitRoom(View view){
//        RetrofitFactory.create(IApiStores.class).temperatureInfoInsert(requestBody)
//                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .doOnNext(new Consumer<BaseCount>() {
//                    @Override
//                    public void accept(BaseCount baseCount) throws Exception {
//                        if (baseCount.getMeta().getStatusCode()==0){
//                            Observable.error(new Exception());
//                        }
//                    }
//                }).subscribe(new Consumer<BaseCount>() {
//                    @Override
//                    public void accept(BaseCount baseCount) throws Exception {
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        Timber.i(throwable);
//                    }
//                });
//    }
    private RoomUtil instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

         instance=RoomUtil.getInstance();
//        bledao= db.bleDao();
        exec =Executors.newCachedThreadPool();

        findViewById(R.id.bt_insert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0;i<5;i++){
                    int randomTxt = new Random().nextInt(100);
                    String  time  =DateUtils.dateToString(new Date(),yyyyMMDDHHmmss)+randomTxt;

                    String json = "{\"bindUserType\":1,\"branchNo\":1,\"boxId\":\"JYT20190100000033\",\"token\":\"51639ffde5519fbbe08cc98f1c3119cb20eac79d8fb5995eb2953025a4f21f2da6fef8c9994bae78bcf036119e73b966\",\"operate_way\":\"0\",\"temperatureForehead\":\"36.7\",\"patientCode\":12050,\"videoNow\":false,\"macAddress\":\"C6:05:04:04:7D:20\",\"equipmentModel\":\"Bluetooth BP\"}";

                    ThreadPoolUtil.getInstance().submmitJob(new Runnable() {
                        @Override
                        public void run() {
                            instance.insertBleData(json,DEVICE_TYPE_TEMP);
                        }
                    });
                }

            }
        });


        findViewById(R.id.bt_select_all).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                instance.selectBleData()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<List<BleEntity>>() {
                            @Override
                            public void accept(List<BleEntity> bleList) throws Exception {
                                for (BleEntity ble:bleList){
                                    Timber.i(String.valueOf(ble));
                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {

                            }
                        });
            }
        });
    }

    public void RxRoomUpRetrofit(View view){
//        exec.execute(new Runnable() {
//            @Override
//            public void run() {
//            }
//        });

        startPolling();
    }

    private void startPolling() {
        Long aLong = 0l;
        Observable.interval(10, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        if (NetworkUtil.isOnline()){
                            Timber.i(" 开始任务  statPolling()" );
                            BleEntity bleENND = RoomUtil.getInstance().findUserLastData();
                            Timber.i("bleENND   " + bleENND);
                            upData(bleENND);
                        }else {
                            Timber.i("offline");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Timber.i("上传报错  " + throwable);
                    }
                });
    }
//    private void statGO(Long along) {
//        db.bleDao().findUserLastData()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<BleEntity>() {
//                    @Override
//                    public void accept(BleEntity bleEntity) throws Exception {
//                        Timber.i("statGO    "+bleEntity.toString());
//                        bleEnty = bleEntity;
//                        upData(bleEntity);
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//
//                    }
//                });
//                .doOnNext(new Consumer<BleEntity>() {
//                    @Override
//                    public void accept(BleEntity bleEntity) throws Exception {
//                        Timber.i("获取数据  "  + " " + String.valueOf(bleEntity));
//                    }
//                })
//                .flatMap(new Function<BleEntity, ObservableSource<BaseCount>>() {
//                    @Override
//                    public ObservableSource<BaseCount> apply(BleEntity bleEntity) throws Exception {
//                        i++;
//                        Timber.i("statatic i    " + i);
//                        Timber.i("开始上传数据 along " + along + " "+ bleEnty);
//                        bleEnty = bleEntity;
//                        RequestBody reBody= GsonUtil.strToRequestBody(bleEntity.bleParam);
//                        Observable<BaseCount> tempApi = RetrofitFactory.create(IApiStores.class).temperatureInfoInsert(reBody);
//                        return tempApi;
//                    }
//                })
//                .subscribe(new Consumer<BaseCount>() {
//                    @Override
//                    public void accept(BaseCount baseCount) throws Exception {
//                        Timber.i("体温提交成功 along " + along + " ");
//
//                        Timber.i("statatic i    " + i);
//                        if (baseCount.getMeta().getStatusCode()==0){
//                            db.bleDao().deleteData(bleEnty);
//                        }
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        Timber.i("提交出错  along " + along + " " + throwable);
//                    }
//                });
//    }

    public void btRxSelect(View view){

//        upData();
    }

  public  void  upData(BleEntity bleEntity){
        if (bleEntity==null){
            return;
        }
      RequestBody reBody= GsonUtil.strToRequestBody(bleEntity.bleParam);
        RetrofitFactory.create(IApiStores.class).temperatureInfoInsert(reBody)
              .subscribeOn(Schedulers.io())
              .subscribe(new Consumer<BaseCount>() {
                  @Override
                  public void accept(BaseCount baseCount) throws Exception {
                      if (baseCount.getMeta().getStatusCode()==0){
                           instance.deleteData(bleEntity);
                          Timber.i("删除完成   ");
                      }
                  }
              }, new Consumer<Throwable>() {
                  @Override
                  public void accept(Throwable throwable) throws Exception {
                      Timber.i("提交出错  along "  + " " + throwable);

                  }
              });
  }


}
