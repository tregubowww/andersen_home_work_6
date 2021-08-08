package ru.tregubowww.andersen_home_work_6

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Contact(
    var id: Int,
    var phoneNumber: String,
    var name: String,
    var surname: String,
    var city: String,
    var email: String,
    var avatarPath: String,

    ) : Parcelable
