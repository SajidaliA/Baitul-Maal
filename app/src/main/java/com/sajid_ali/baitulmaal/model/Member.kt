package com.sajid_ali.baitulmaal.model

import android.os.Parcelable
import com.sajid_ali.baitulmaal.utils.aukafAmount
import com.sajid_ali.baitulmaal.utils.madresaFeesAmount
import kotlinx.parcelize.Parcelize

@Parcelize
data class Member(
    val id: Int,
    val headOfTheFamilyName: String = "",
    val fourYearsAbove: String = "0",
    val totalAukafAmount: Int = fourYearsAbove.toInt() * aukafAmount,
    val studyInMadresa: String = "0",
    val totalMadresaFeeAmount: Int = studyInMadresa.toInt() * madresaFeesAmount,
    val totalPayableAmountForOneMonth: Int = totalAukafAmount + totalMadresaFeeAmount,
    val agevadId: Int,
    val paidMonths: Int = 0,
    val totalPayableAmount: Int = totalPayableAmountForOneMonth * (12 - paidMonths),
    val totalPaidAmount: Int = totalPayableAmountForOneMonth * paidMonths,
): Parcelable
