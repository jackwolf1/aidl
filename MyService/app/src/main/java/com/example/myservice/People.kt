package com.example.myservice

import android.os.Parcel
import android.os.Parcelable

data class People(
    var name: String? = "",
    var id: Int = 1
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString(),
        source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(name)
        writeInt(id)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<People> = object : Parcelable.Creator<People> {
            override fun createFromParcel(source: Parcel): People = People(source)
            override fun newArray(size: Int): Array<People?> = arrayOfNulls(size)
        }
    }
}

