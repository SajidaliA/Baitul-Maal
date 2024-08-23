package com.sajid_ali.baitulmaal.model

data class Agevan(
    val id: Int? = 0,
    var name: String = "",
    val members: List<Member> = emptyList(),
    var contactNo: String = "",
)
