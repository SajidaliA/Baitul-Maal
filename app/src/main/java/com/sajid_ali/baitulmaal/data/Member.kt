package com.sajid_ali.baitulmaal.data

import android.os.Parcelable
import com.sajid_ali.baitulmaal.utils.aukafAmount
import com.sajid_ali.baitulmaal.utils.madresaFeesAmount
import kotlinx.parcelize.Parcelize

@Parcelize
data class Member(
    val id: Int,
    val name: String,
    val fourYearsAbove: Int,
    val totalAukafAmount: Int = fourYearsAbove * aukafAmount,
    val studyInMadresa: Int,
    val totalMadresaFeeAmount: Int = studyInMadresa * madresaFeesAmount,
    val totalPayableAmountForOneMonth: Int = totalAukafAmount + totalMadresaFeeAmount,
    val agevadId: Int,
    val paidMonths: Int = 0,
    val totalPayableAmount: Int = totalPayableAmountForOneMonth * (12 - paidMonths),
    val totalPaidAmount: Int = totalPayableAmountForOneMonth * paidMonths
): Parcelable
