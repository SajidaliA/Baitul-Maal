package com.group4.baitulmaal.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.group4.baitulmaal.R
import com.group4.baitulmaal.data.Member
import com.group4.baitulmaal.data.Month
import com.group4.baitulmaal.utils.MEMBER_KEY
import com.group4.baitulmaal.utils.Screens

@Composable
fun MemberListScreen(navHostController: NavHostController) {

    var totalPaidAmount by remember {
        mutableIntStateOf(0)
    }
    var totalUnPaidAmount by remember {
        mutableIntStateOf(0)
    }


    val months = mutableListOf(
        Month(0, "ટોટલ", true),
        Month(1, "જાન્યુઆરી"),
        Month(2, "ફેબ્રુઆરી"),
        Month(3, "માર્ચ"),
        Month(4, "એપ્રિલ"),
        Month(5, "મે"),
        Month(6, "જૂન"),
        Month(7, "જુલાઈ"),
        Month(8, "ઓગસ્ટ"),
        Month(9, "સપ્ટેમ્બર"),
        Month(10, "ઓક્ટોબર"),
        Month(11, "નવેમ્બર"),
        Month(12, "ડિસેમ્બર")
    )
    val members = listOf(
        Member(
            id = 1,
            name = "સાજીદઅલી અહેમદ ભાઈ સુથાર",
            fourYearsAbove = 2,
            studyInMadresa = 1,
            paid = false
        ),
        Member(
            id = 2,
            name = "અકબરઅલી અબ્દુલભાઈ સુથાર",
            fourYearsAbove = 4,
            studyInMadresa = 2,
            paid = true
        ),
        Member(
            id = 3,
            name = "મંજુરહેમદ અહેમદભાઈ સુથાર",
            fourYearsAbove = 4,
            studyInMadresa = 2,
            paid = true
        ),
        Member(
            id = 4,
            name = "જાફરઅલી રહીમભાઈ સુથાર",
            fourYearsAbove = 5,
            studyInMadresa = 0,
            paid = true
        ),
        Member(
            id = 5,
            name = "હૈદરઅલી રહીમભાઈ સુથાર",
            fourYearsAbove = 7,
            studyInMadresa = 3,
            paid = true
        ),
        Member(
            id = 6,
            name = "અબ્બાસઅલી મહંમદભાઈ સુથાર",
            fourYearsAbove = 5,
            studyInMadresa = 1,
            paid = false
        ),
        Member(
            id = 7,
            name = "હસનઅલી મહંમદભાઈ સુથાર",
            fourYearsAbove = 2,
            studyInMadresa = 0,
            paid = true
        ),
        Member(
            id = 8,
            name = "અબ્બાસઅલી ઈસ્માઈલભાઈ સુથાર",
            fourYearsAbove = 6,
            studyInMadresa = 3,
            paid = true
        ),
        Member(
            id = 9,
            name = "હૈદરઅલી ફતેહભાઈ સુથાર",
            fourYearsAbove = 4,
            studyInMadresa = 0,
            paid = false
        ),
        Member(
            id = 10,
            name = "શેરઅલી ફતેહભાઈ સુથાર",
            fourYearsAbove = 6,
            studyInMadresa = 1,
            paid = true
        )
    )
    members.forEach { member ->
        if (member.paid) {
            totalPaidAmount += member.totalPayableAmount
        } else {
            totalUnPaidAmount += member.totalPayableAmount
        }
    }

    Column {
        Header(stringResource(id = R.string.member_list), true)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    textAlign = TextAlign.Center,
                    text = "${stringResource(id = R.string.total_paid_amount)}\n₹$totalPaidAmount",
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp
                )
            }
            Spacer(modifier = Modifier.width(15.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    textAlign = TextAlign.Center,
                    text = "${stringResource(id = R.string.total_unpaid_amount)}\n₹$totalUnPaidAmount",
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp
                )
            }

        }
        var isSelected by remember {
            mutableStateOf(false)
        }
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 15.dp)
        ) {
            items(months) { month ->
                isSelected = month.isSelected
                MonthView(month.title, isSelected) { selectedMonth->
                    Log.e("TAG", "Selected month : $selectedMonth")
                    months.map {
                        it.isSelected = false
                        if (it.title == selectedMonth){
                            it.isSelected = true
                        }
                    }
                }
            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(top = 10.dp, bottom = 80.dp)
        ) {
            items(members) { member ->
                MemberItem(member) {
                    navHostController.currentBackStackEntry?.savedStateHandle?.set(
                        MEMBER_KEY,
                        member
                    )
                    navHostController.navigate(Screens.memberDetails.name)
                }
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 15.dp, end = 15.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        ExtendedFloatingActionButton(onClick = { navHostController.navigate(Screens.addNewMember.name) },
            icon = { Icon(Icons.Filled.Add, stringResource(id = R.string.add_new_member)) },
            text = { Text(text = stringResource(id = R.string.add_new_member)) })
    }
}

@Composable
fun MonthView(month: String, isSelected : Boolean, onMonthClick: (String) -> Unit) {
    Text(
        text = month,
        modifier = Modifier
            .background(
                shape = RoundedCornerShape(20.dp),
                color = if (isSelected) colorResource(id = R.color.teal_700) else Color.LightGray
            )
            .padding(horizontal = 15.dp, vertical = 5.dp)
            .clickable {
                onMonthClick(month)
            },
        color = if (isSelected) Color.White else Color.Black,
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold
    )
    Spacer(modifier = Modifier.width(10.dp))
}

@Composable
fun MemberItem(member: Member, onMemberClick: () -> Unit) {
    ElevatedCard(
        onClick = onMemberClick,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp), modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 15.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 15.dp)
        ) {
            Text(
                text = "${member.id}. ${member.name}",
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp
            )

            Text(
                text = stringResource(id = R.string.total_payable_amount),
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
                color = Color.Gray
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "₹${member.totalPayableAmount}",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp
                )
                Text(
                    text = if (member.paid) {
                        stringResource(id = R.string.paid)
                    } else {
                        stringResource(
                            id = R.string.not_paid
                        )
                    },
                    color = if (member.paid) {
                        colorResource(id = R.color.green)
                    } else {
                        Color.Red
                    },
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
            }
        }
    }
}