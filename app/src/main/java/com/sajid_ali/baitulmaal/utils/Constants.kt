package com.sajid_ali.baitulmaal.utils

import com.sajid_ali.baitulmaal.model.Agevan
import com.sajid_ali.baitulmaal.model.Member
import com.sajid_ali.baitulmaal.model.Month

const val aukafAmount: Int = 35
const val madresaFeesAmount: Int = 270
const val MEMBER_KEY: String = "member_key"
const val USER_TYPE_ADMIN: String = "ADMIN"
const val USER_TYPE_AGEVAN: String = "AGEVAN"
const val USER_TYPE_MEMBER: String = "MEMBER"
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


val members = listOf(
    Member(
        id = 1,
        headOfTheFamilyName = "સાજીદઅલી અહેમદ ભાઈ સુથાર",
        fourYearsAbove = "2",
        studyInMadresa = "1",
        agevadId = 0,
        paidMonths = 12
    ),
    Member(
        id = 2,
        headOfTheFamilyName = "અકબરઅલી અબ્દુલભાઈ સુથાર",
        fourYearsAbove = "4",
        studyInMadresa = "2",
        agevadId = 0,
        paidMonths = 6
    ),
    Member(
        id = 3,
        headOfTheFamilyName = "મંજુરહેમદ અહેમદભાઈ સુથાર",
        fourYearsAbove = "4",
        studyInMadresa = "2",
        agevadId = 0,
        paidMonths = 10
    ),
    Member(
        id = 4,
        headOfTheFamilyName = "જાફરઅલી રહીમભાઈ સુથાર",
        fourYearsAbove = "5",
        studyInMadresa = "0",
        agevadId = 0,
        paidMonths = 11
    ),
    Member(
        id = 5,
        headOfTheFamilyName = "હૈદરઅલી રહીમભાઈ સુથાર",
        fourYearsAbove = "7",
        studyInMadresa = "3",
        agevadId = 0,
        paidMonths = 7
    ),
    Member(
        id = 6,
        headOfTheFamilyName = "અબ્બાસઅલી મહંમદભાઈ સુથાર",
        fourYearsAbove = "5",
        studyInMadresa = "1",
        agevadId = 0,
        paidMonths = 2
    ),
    Member(
        id = 7,
        headOfTheFamilyName = "હસનઅલી મહંમદભાઈ સુથાર",
        fourYearsAbove = "2",
        studyInMadresa = "0",
        agevadId = 0,
        paidMonths = 3
    ),
    Member(
        id = 8,
        headOfTheFamilyName = "અબ્બાસઅલી ઈસ્માઈલભાઈ સુથાર",
        fourYearsAbove = "6",
        studyInMadresa = "3",
        agevadId = 0,
        paidMonths = 10
    ),
    Member(
        id = 9,
        headOfTheFamilyName = "હૈદરઅલી ફતેહભાઈ સુથાર",
        fourYearsAbove = "4",
        studyInMadresa = "0",
        agevadId = 0,
        paidMonths = 11
    ),
    Member(
        id = 10,
        headOfTheFamilyName = "શેરઅલી ફતેહભાઈ સુથાર",
        fourYearsAbove = "6",
        studyInMadresa = "1",
        agevadId = 0
    )
)

val agevanList = listOf(
    Agevan(1, "જનરલ લીસ્ટ", members, "8460766740"),
    Agevan(2, "અકબર અલી અબ્દુલ ભાઈ સુથાર", members, "9751483924"),
    Agevan(3, "અહેમદ ભાઈ ઈસ્માઈલ ભાઈ સુથાર", members, "7466609800"),
    Agevan(4, "શબ્બીર ભાઈ જમાલ ભાઈ સુથાર ", members, "9985667584"),
    Agevan(5, "હસન ભાઈ જમાલ ભાઈ ભટ્ટ", members, "8754621235")
)
