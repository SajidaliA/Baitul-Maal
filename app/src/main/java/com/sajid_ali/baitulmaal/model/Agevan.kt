package com.sajid_ali.baitulmaal.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Agevan(
    var id: String = "",
    var name: String = "",
    var contactNo: String = "",
    var members: ArrayList<Member?> = arrayListOf(),
    var totalPayableAmount: Int = 0,
    var totalPaidAmount: Int = 0,
    var totalUnPaidAmount: Int = 0,
) : Parcelable
