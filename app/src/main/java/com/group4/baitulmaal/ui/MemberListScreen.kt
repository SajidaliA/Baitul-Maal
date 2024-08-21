package com.group4.baitulmaal.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.group4.baitulmaal.R
import com.group4.baitulmaal.data.Member
import com.group4.baitulmaal.utils.MEMBER_KEY
import com.group4.baitulmaal.utils.Screens
import com.group4.baitulmaal.utils.agevanList

@Composable
fun MemberListScreen(navHostController: NavHostController) {

    var totalPaidAmount = 0
    var totalUnPaidAmount = 0
//    var mSelectedMonth by remember { mutableStateOf(months[0]) }
    var mSelectedAgevan by remember {
        mutableStateOf(agevanList[0])
    }
    var anchorPosition by remember { mutableStateOf(Offset.Zero) }
    var showPopup by remember { mutableStateOf(false) }


    val members = listOf(
        Member(
            id = 1,
            name = "સાજીદઅલી અહેમદ ભાઈ સુથાર",
            fourYearsAbove = 2,
            studyInMadresa = 1,
            agevadId = 0,
            unPaidMonths = 2
        ),
        Member(
            id = 2,
            name = "અકબરઅલી અબ્દુલભાઈ સુથાર",
            fourYearsAbove = 4,
            studyInMadresa = 2,
            agevadId = 0,
            unPaidMonths = 3
        ),
        Member(
            id = 3,
            name = "મંજુરહેમદ અહેમદભાઈ સુથાર",
            fourYearsAbove = 4,
            studyInMadresa = 2,
            agevadId = 0,
            unPaidMonths = 0
        ),
        Member(
            id = 4,
            name = "જાફરઅલી રહીમભાઈ સુથાર",
            fourYearsAbove = 5,
            studyInMadresa = 0,
            agevadId = 0,
            unPaidMonths = 5
        ),
        Member(
            id = 5,
            name = "હૈદરઅલી રહીમભાઈ સુથાર",
            fourYearsAbove = 7,
            studyInMadresa = 3,
            agevadId = 0,
            unPaidMonths = 7
        ),
        Member(
            id = 6,
            name = "અબ્બાસઅલી મહંમદભાઈ સુથાર",
            fourYearsAbove = 5,
            studyInMadresa = 1,
            agevadId = 0,
            unPaidMonths = 0
        ),
        Member(
            id = 7,
            name = "હસનઅલી મહંમદભાઈ સુથાર",
            fourYearsAbove = 2,
            studyInMadresa = 0,
            agevadId = 0,
            unPaidMonths = 3
        ),
        Member(
            id = 8,
            name = "અબ્બાસઅલી ઈસ્માઈલભાઈ સુથાર",
            fourYearsAbove = 6,
            studyInMadresa = 3,
            agevadId = 0,
            unPaidMonths = 10
        ),
        Member(
            id = 9,
            name = "હૈદરઅલી ફતેહભાઈ સુથાર",
            fourYearsAbove = 4,
            studyInMadresa = 0,
            agevadId = 0,
            unPaidMonths = 1
        ),
        Member(
            id = 10,
            name = "શેરઅલી ફતેહભાઈ સુથાર",
            fourYearsAbove = 6,
            studyInMadresa = 1,
            agevadId = 0
        )
    )
    members.forEach { member ->
        if (member.unPaidMonths == 0) {
            totalPaidAmount += member.totalPaidAmount
        } else {
            totalUnPaidAmount += member.totalPayableAmount
        }
    }

    if (showPopup) {
        AgevanListPopup(anchorPosition) {
            mSelectedAgevan = it
            showPopup = false
        }
    }

    Column {
        Header(stringResource(id = R.string.member_list), true) {}
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .border(
                        1.dp,
                        color = colorResource(id = R.color.teal_700),
                        RoundedCornerShape(10.dp)
                    )
                    .padding(15.dp),
                textAlign = TextAlign.Center,
                text = "${stringResource(id = R.string.total_paid_amount)}\n₹$totalPaidAmount",
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.width(15.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .border(
                        1.dp,
                        color = colorResource(id = R.color.teal_700),
                        RoundedCornerShape(10.dp)
                    )
                    .padding(15.dp),
                textAlign = TextAlign.Center,
                text = "${stringResource(id = R.string.total_unpaid_amount)}\n₹$totalUnPaidAmount",
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )


        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
                .border(
                    1.dp,
                    color = colorResource(id = R.color.teal_700),
                    RoundedCornerShape(5.dp)
                )
                .clickable { showPopup = !showPopup }
                .padding(15.dp)
                .onGloballyPositioned { coordinates ->
                    // Capture the position and size of the Text view
                    anchorPosition = coordinates.positionInWindow()
                },
            text = mSelectedAgevan,

            )
//        LazyRow(
//            modifier = Modifier.fillMaxWidth(),
//            contentPadding = PaddingValues(horizontal = 15.dp)
//        ) {
//            items(months) { month ->
//                MonthView(month, mSelectedMonth) { selectedMonth ->
//                    mSelectedMonth = selectedMonth
//                    Log.e("TAG", "Selected month : $selectedMonth")
//                }
//            }
//        }
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
        ExtendedFloatingActionButton(
            onClick = {
                navHostController.currentBackStackEntry?.savedStateHandle?.set(
                    MEMBER_KEY,
                    null
                )
                navHostController.navigate(Screens.addNewMember.name)
            },
            icon = { Icon(Icons.Filled.Add, stringResource(id = R.string.add_new_member)) },
            text = { Text(text = stringResource(id = R.string.add_new_member)) })
    }
}

@Composable
fun MonthView(month: String, mSelectedMonth: String, onMonthClick: (String) -> Unit) {
    Text(
        text = month,
        modifier = Modifier
            .background(
                shape = RoundedCornerShape(20.dp),
                color = if (mSelectedMonth == month) colorResource(id = R.color.teal_700) else Color.LightGray
            )
            .padding(horizontal = 15.dp, vertical = 5.dp)
            .clickable {
                onMonthClick(month)
            },
        color = if (mSelectedMonth == month) Color.White else Color.Black,
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
                text = member.name,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp
            )

            Text(
                text = "${stringResource(id = R.string.total_payable_amount_for_one_month)} :- ₹${member.totalPayableAmountForOneMonth}",
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
                color = Color.Gray
            )
            if (member.unPaidMonths > 0) {
                Text(
                    text = "${member.unPaidMonths} ${stringResource(id = R.string.un_paid_months)}",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    color = Color.Red
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "${stringResource(id = R.string.total_payable_amount)} :- ₹${member.totalPayableAmount}",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
                if (member.unPaidMonths == 0) {
                    Text(
                        text = stringResource(id = R.string.paid),
                        color = colorResource(id = R.color.green),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}