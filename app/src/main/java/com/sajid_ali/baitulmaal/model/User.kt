package com.sajid_ali.baitulmaal.model

import com.sajid_ali.baitulmaal.utils.USER_TYPE_AGEVAN

data class User(
    val id: Int? = null,
    val mobileNo: String? = null,
    val password: String? = null,
    val userType: String = USER_TYPE_AGEVAN,
) {
    constructor() : this(null)
}
