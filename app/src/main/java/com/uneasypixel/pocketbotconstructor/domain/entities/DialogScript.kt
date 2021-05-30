package com.uneasypixel.pocketbotconstructor.domain.entities

import android.os.Parcel
import android.os.Parcelable
import com.uneasypixel.pocketbotconstructor.domain.dao.DialogScriptDAO

class DialogScript(override var phrase: String?) : DialogScriptDAO, Parcelable {
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

    companion object CREATOR : Parcelable.Creator<DialogScript> {
        override fun createFromParcel(parcel: Parcel): DialogScript {
            return DialogScript(parcel)
        }

        override fun newArray(size: Int): Array<DialogScript?> {
            return arrayOfNulls(size)
        }
    }

    override var response: String = ""
    override var setOfPhrases: SetOfPhrases = SetOfPhrases()
}