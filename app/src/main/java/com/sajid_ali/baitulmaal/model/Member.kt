package com.sajid_ali.baitulmaal.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Member(
    var id: String = "",
    var headOfTheFamilyName: String = "",
    val fourYearsAbove: String = "0",
    var totalAukafAmount: Int = 0,
    val studyInMadresa: String = "0",
    var totalMadresaFeeAmount: Int = 0,
    var totalPayableAmountForOneMonth: Int = 0,
    var paidMonths: Int = 0,
    var totalPayableAmount: Int = 0,
    var totalPaidAmount: Int = 0,
    var totalUnpaidAmount: Int = 0,
) : Parcelable {
    constructor() : this("")
}
