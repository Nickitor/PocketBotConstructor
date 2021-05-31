package com.uneasypixel.pocketbotconstructor.domain.entities

import android.os.Parcel
import android.os.Parcelable
import com.uneasypixel.pocketbotconstructor.domain.dao.SetOfPhrasesDAO

class SetOfPhrases() : SetOfPhrasesDAO, Parcelable {

    override var name: String = ""

    override var groupSources: MutableList<String> = mutableListOf()
    override var textSources: MutableList<String> = mutableListOf()

    override var isTextResource: Boolean = true

    constructor(parcel: Parcel) : this() {

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SetOfPhrases> {
        override fun createFromParcel(parcel: Parcel): SetOfPhrases {
            return SetOfPhrases(parcel)
        }

        override fun newArray(size: Int): Array<SetOfPhrases?> {
            return arrayOfNulls(size)
        }
    }
}