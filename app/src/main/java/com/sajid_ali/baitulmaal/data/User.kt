package com.sajid_ali.baitulmaal.data

import com.sajid_ali.baitulmaal.utils.USER_TYPE_MEMBER

data class User(
    val id: Int,
    val mobileNo: String,
    val password: String,
    val userType: String = USER_TYPE_MEMBER
)
