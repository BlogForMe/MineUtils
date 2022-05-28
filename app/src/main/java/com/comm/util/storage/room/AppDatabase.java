package com.comm.util.storage.room;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {BleEntity.class},version=2)
public abstract class AppDatabase extends RoomDatabase {

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {

        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE BleEntity "
                    + " ADD COLUMN last_update INTEGER");
        }
    };

    public abstract BleDao bleDao();

    }
