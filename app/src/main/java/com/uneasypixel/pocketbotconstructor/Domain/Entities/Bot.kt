package com.uneasypixel.pocketbotconstructor.Domain.Entities

import android.os.Parcel
import android.os.Parcelable
import com.uneasypixel.pocketbotconstructor.Domain.DAO.BotDAO

class Bot(override var name: String?, override var imageResourceId: Int
) : BotDAO, Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(imageResourceId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Bot> {
        override fun createFromParcel(parcel: Parcel): Bot {
            return Bot(parcel)
        }

        override fun newArray(size: Int): Array<Bot?> {
            return arrayOfNulls(size)
        }
    }

    lateinit var reactionsToPhrases: Map<String, MutableList<String>>
    lateinit var groupID : String
    lateinit var token : String
    var isEnabled : Boolean = false
}