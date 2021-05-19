package com.uneasypixel.pocketbotconstructor.Domain.Entities

import android.os.Parcel
import android.os.Parcelable
import com.uneasypixel.pocketbotconstructor.Domain.DAO.PhraseDAO

class Phrase(override var phrase: String?) : PhraseDAO, Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(phrase)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Phrase> {
        override fun createFromParcel(parcel: Parcel): Phrase {
            return Phrase(parcel)
        }

        override fun newArray(size: Int): Array<Phrase?> {
            return arrayOfNulls(size)
        }
    }

    override var response: MutableList<String> = mutableListOf()
}