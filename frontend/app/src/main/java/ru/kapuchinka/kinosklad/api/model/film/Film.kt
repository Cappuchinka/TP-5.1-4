package ru.kapuchinka.kinosklad.api.model.film

import android.os.Parcel
import android.os.Parcelable

data class Film(
    val film_id: Int,
    val name: String,
    val description: String,
    val country: String,
    val releaseDate: Int,
    val categories: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(film_id)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(country)
        parcel.writeInt(releaseDate)
        parcel.writeString(categories)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Film> {
        override fun createFromParcel(parcel: Parcel): Film {
            return Film(parcel)
        }

        override fun newArray(size: Int): Array<Film?> {
            return arrayOfNulls(size)
        }
    }
}

