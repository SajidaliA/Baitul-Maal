package com.group4.baitulmaal.data

import android.os.Parcelable
import com.group4.baitulmaal.utils.aukafAmount
import com.group4.baitulmaal.utils.madresaFeesAmount
import kotlinx.parcelize.Parcelize

@Parcelize
data class Member(
    val id: Int,
    val name: String,
    val fourYearsAbove: Int,
    val totalAukafAmount: Int = fourYearsAbove * aukafAmount,
    val studyInMadresa: Int,
    val totalMadresaFeeAmount: Int = studyInMadresa * madresaFeesAmount,
    val totalPayableAmount: Int = totalAukafAmount + totalMadresaFeeAmount,
    val paid: Boolean,
    val agevadId: Int
): Parcelable
