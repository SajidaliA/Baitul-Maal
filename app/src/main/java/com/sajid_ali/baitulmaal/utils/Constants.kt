package com.sajid_ali.baitulmaal.utils

import com.sajid_ali.baitulmaal.model.Month

const val aukafAmount: Int = 35
const val madresaFeesAmount: Int = 270
const val MEMBER_KEY: String = "member_key"
const val USER_TYPE_ADMIN: String = "ADMIN"
const val USER_TYPE_AGEVAN: String = "AGEVAN"
const val USER_COLLECTION: String = "users"
const val AGEVAN_COLLECTION: String = "agevans"
const val MEMBER_COLLECTION: String = "members"

val months = listOf(
    Month("જૂન"),
    Month("જુલાઈ"),
    Month("ઓગસ્ટ"),
    Month("સપ્ટેમ્બર"),
    Month("ઓક્ટોબર"),
    Month("નવેમ્બર"),
    Month("ડિસેમ્બર"),
    Month("જાન્યુઆરી"),
    Month("ફેબ્રુઆરી"),
    Month("માર્ચ"),
    Month("એપ્રિલ"),
    Month("મે")
)

const val agevanIdArg = "agevanId"
const val agevanName = "agevanName"
const val memberListRoute = "memberListRoute"
const val memberDetailsRoute = "memberDetailsRoute"
const val addNewMemberRoute = "addNewMemberRoute"
const val agevanListRoute = "agevanListRoute"
const val loginScreenRoute = "loginScreenRoute"
