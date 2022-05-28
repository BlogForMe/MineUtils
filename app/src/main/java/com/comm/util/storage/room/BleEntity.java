package com.comm.util.storage.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class BleEntity {
    @PrimaryKey
    public long createDttm;
    public String bleParam;
    public Integer bleCode;


    public long getCreateDttm() {
        return createDttm;
    }

    public BleEntity setCreateDttm(long createDttm) {
        this.createDttm = createDttm;
        return this;
    }

    public String getBleParam() {
        return bleParam;
    }

    public BleEntity setBleParam(String bleParam) {
        this.bleParam = bleParam;
        return this;
    }

    public Integer getBleCode() {
        return bleCode;
    }

    public BleEntity setBleCode(Integer bleCode) {
        this.bleCode = bleCode;
        return this;
    }

    @Override
    public String toString() {
        return "BleEntity{" + " createDttm='" + createDttm + '\'' + "bleCode='" + bleCode + '\'' +
                '}'+"\n";
    }
}
