package com.sajid_ali.baitulmaal.model

import android.os.Parcelable
import com.sajid_ali.baitulmaal.utils.aukafAmount
import com.sajid_ali.baitulmaal.utils.madresaFeesAmount
import kotlinx.parcelize.Parcelize

@Parcelize
data class Member(
    val id: Int? = null,
    val headOfTheFamilyName: String = "",
    val fourYearsAbove: String = "0",
    var totalAukafAmount: Int = fourYearsAbove.toInt() * aukafAmount,
    val studyInMadresa: String = "0",
    var totalMadresaFeeAmount: Int = studyInMadresa.toInt() * madresaFeesAmount,
    var totalPayableAmountForOneMonth: Int = totalAukafAmount + totalMadresaFeeAmount,
    val agevadId: Long = 0,
    val paidMonths: Int = 0,
    var totalPayableAmount: Int = totalPayableAmountForOneMonth * (12 - paidMonths),
    var totalPaidAmount: Int = totalPayableAmountForOneMonth * paidMonths,
) : Parcelable {
    constructor() : this(null)
}
