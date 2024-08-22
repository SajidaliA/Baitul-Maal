package com.sajid_ali.baitulmaal.data

import android.os.Parcelable
import com.sajid_ali.baitulmaal.utils.aukafAmount
import com.sajid_ali.baitulmaal.utils.madresaFeesAmount
import kotlinx.parcelize.Parcelize

@Parcelize
data class Member(
    val id: Int,
    val headOfTheFamilyName: String = "",
    val fourYearsAbove: Int = 0,
    val totalAukafAmount: Int = fourYearsAbove * aukafAmount,
    val studyInMadresa: Int = 0,
    val totalMadresaFeeAmount: Int = studyInMadresa * madresaFeesAmount,
    val totalPayableAmountForOneMonth: Int = totalAukafAmount + totalMadresaFeeAmount,
    val agevadId: Int,
    val paidMonths: Int = 0,
    val totalPayableAmount: Int = totalPayableAmountForOneMonth * (12 - paidMonths),
    val totalPaidAmount: Int = totalPayableAmountForOneMonth * paidMonths,
): Parcelable
