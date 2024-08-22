package com.sajid_ali.baitulmaal.model

import com.sajid_ali.baitulmaal.utils.USER_TYPE_MEMBER

data class User(
    val id: Int? = null,
    val mobileNo: String? = null,
    val password: String? = null,
    val userType: String = USER_TYPE_MEMBER,
) {
    constructor() : this(null)
}
