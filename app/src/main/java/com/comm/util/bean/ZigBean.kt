package com.comm.util.bean

import android.os.Parcel
import android.os.Parcelable

data class ZigBean(val endpoint: Int,
                   val short_addr: Int,
                   val data: DeviceData?,
                   val model: String?,
                   val cmd: String?,
                   val sid: String?) : Parcelable {
    override fun toString(): String {
        return "ZigBean(endpoint=$endpoint, shortAddr=$short_addr, data=$data, model=$model, cmd='$cmd', sid='$sid')"
    }

    constructor(source: Parcel) : this(
            source.readInt(),
            source.readInt(),
            source.readParcelable<DeviceData>(DeviceData::class.java.classLoader),
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(endpoint)
        writeInt(short_addr)
        writeParcelable(data, 0)
        writeString(model)
        writeString(cmd)
        writeString(sid)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ZigBean> = object : Parcelable.Creator<ZigBean> {
            override fun createFromParcel(source: Parcel): ZigBean = ZigBean(source)
            override fun newArray(size: Int): Array<ZigBean?> = arrayOfNulls(size)
        }
    }
}

class DeviceData : Parcelable {
    var hardwareVer: String? = null

    var softwareVer: String? = null

    var channel: String? = null

    var model: String? = null

    var BDR: String? = null

    var data: String? = null

    var cmdID: String? = null

    constructor()

    constructor(channel: String, model: String, BDR: String) : this() {
        this.channel = channel
        this.model = model
        this.BDR = BDR
    }

    constructor(hardwareVer: String,
                softwareVer: String,
                channel: String,
                model: String,
                BDR: String,
                data: String, cmdID: String) : this() {
        this.hardwareVer = hardwareVer
        this.softwareVer = softwareVer
        this.data = data
        this.channel = channel
        this.model = model
        this.BDR = BDR
        this.cmdID = cmdID
    }

    constructor(source: Parcel) : this(
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {}

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<DeviceData> = object : Parcelable.Creator<DeviceData> {
            override fun createFromParcel(source: Parcel): DeviceData = DeviceData(source)
            override fun newArray(size: Int): Array<DeviceData?> = arrayOfNulls(size)
        }
    }
}