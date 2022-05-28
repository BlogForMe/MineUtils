package com.comm.util.utils;

import java.util.Date;
import java.util.List;
import java.util.Random;

import androidx.room.Room;
import com.android.util.AppUtil;
import com.comm.util.storage.room.AppDatabase;
import com.comm.util.storage.room.BleDao;
import com.comm.util.storage.room.BleEntity;
import io.reactivex.Observable;

import static com.comm.util.utils.DateUtils.yyyyMMDDHHmmss;

public class RoomUtil {
    private static final RoomUtil ourInstance = new RoomUtil();
    private final BleDao bledao;

    private RoomUtil() {
        AppDatabase db = Room.databaseBuilder(AppUtil.getApp(), AppDatabase.class, "bleDb.db")
//                .addMigrations(MIGRATION_1_2)
                .build();
        bledao= db.bleDao();
    }

    public static RoomUtil getInstance() {
        return ourInstance;
    }

    public void insertBleData(String saveStrData,int bleCode){
        try{
            if (bledao!=null){
                int randomTxt = new Random().nextInt(100);
                String  time  =DateUtils.dateToString(new Date(),yyyyMMDDHHmmss)+randomTxt;
                bledao.insertBle(new BleEntity().setBleCode(bleCode).setBleParam(saveStrData).setCreateDttm(Long.parseLong(time)));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Observable<List<BleEntity>> selectBleData(){
      return  bledao.getAll();
    }

    public void deleteData(BleEntity bleEntity){
       bledao.deleteData(bleEntity);
    }
    public BleEntity  findUserLastData(){
         return   bledao.findUserLastData();
    }
}
