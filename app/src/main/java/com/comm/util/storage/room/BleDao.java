package com.comm.util.storage.room;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Observable;

@Dao
public interface BleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBle(BleEntity bleData);

    @Query("SELECT * FROM BleEntity")
    Observable<List<BleEntity>> getAll();

//    @Query("SELECT * FROM BleEntity WHERE id = :id")
//    Observable<BleEntity>  findUserById(int id);

    @Query("SELECT * FROM BleEntity LIMIT 0,1")
    BleEntity  findUserLastData();

    @Delete
    void deleteData(BleEntity bleData);


}
