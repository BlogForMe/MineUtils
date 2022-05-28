//package com.jonzhou.mineutils;
//
//import android.content.Context;
//
//import androidx.room.Room;
//import androidx.test.InstrumentationRegistry;
//import androidx.test.ext.junit.runners.AndroidJUnit4;
//
//import com.jonzhou.mineutils.storage.room.AppDatabase;
//import com.jonzhou.mineutils.storage.room.BleDao;
//import com.jonzhou.mineutils.storage.room.BleEntity;
//import com.jonzhou.mineutils.utils.DateUtils;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import java.io.IOException;
//import java.util.Date;
//
//import static com.jonzhou.mineutils.utils.DateUtils.yyyyMMDDHHmmss;
//
//@RunWith(AndroidJUnit4.class)
//public class SimpleEntityReadWriteTest {
//    private BleDao mUserDao;
//    private AppDatabase mDb;
//
//    @Before
//    public void createDb() {
//        Context context = InstrumentationRegistry.getTargetContext();
//        mDb = Room.databaseBuilder(context, AppDatabase.class,"bleDb.db")
//                .addMigrations()
//                .build();
//        mUserDao= mDb.bleDao();
//    }
//
//    @After
//    public void closeDb() throws IOException {
//        mDb.close();
//    }
//
//    @Test
//    public void writeUserAndReadInList() throws Exception {
//        BleEntity bleEntity = new BleEntity(DateUtils.dateToString(new Date(),yyyyMMDDHHmmss),"dataadjfjf");
////        user.setName("george");
////        mUserDao.insert(user);
////        List<User> byName = mUserDao.findUsersByName("george");
////        assertThat(byName.get(0), equalTo(user));
//    }
//}