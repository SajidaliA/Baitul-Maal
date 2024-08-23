package com.sajid_ali.baitulmaal.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.sajid_ali.baitulmaal.R
import com.sajid_ali.baitulmaal.model.Agevan
import com.sajid_ali.baitulmaal.utils.MEMBER_KEY
import com.sajid_ali.baitulmaal.utils.Screens
import com.sajid_ali.baitulmaal.utils.aukafAmount
import com.sajid_ali.baitulmaal.utils.madresaFeesAmount
import com.sajid_ali.baitulmaal.viewnodel.AgevanViewModel
import com.sajid_ali.baitulmaal.viewnodel.MemberViewModel

@Composable
fun MemberListScreen(navHostController: NavHostController, drawerState: DrawerState?) {

    var totalPaidAmount = 0
    var totalUnPaidAmount = 0
    var mSelectedAgevan by remember {
        mutableStateOf(Agevan(0, "જનરલ લીસ્ટ"))
    }
    var anchorPosition by remember { mutableStateOf(Offset.Zero) }
    var showPopup by remember { mutableStateOf(false) }

    val memberViewModel: MemberViewModel = viewModel()
    val members by memberViewModel.members.collectAsState()
    val agevanViewModel: AgevanViewModel = viewModel()
    val agevanList by agevanViewModel.agevanList.collectAsState()

    members.forEach { member ->
        member?.let {
            it.totalAukafAmount = it.fourYearsAbove.toInt() * aukafAmount
            it.totalMadresaFeeAmount = it.studyInMadresa.toInt() * madresaFeesAmount
            it.totalPayableAmountForOneMonth = it.totalAukafAmount + it.totalMadresaFeeAmount
            it.totalPayableAmount = it.totalPayableAmountForOneMonth * (12 - it.paidMonths)
            it.totalPaidAmount = it.totalPayableAmountForOneMonth * 12

            if (it.paidMonths == 12) {
                totalPaidAmount += it.totalPaidAmount
            } else {
                totalUnPaidAmount += it.totalPayableAmount
            }
        }
    }

    if (showPopup) {
        AgevanListPopup(agevanList, anchorPosition) {
            if (it != null) {
                mSelectedAgevan = it
            }
            showPopup = false
        }
    }



    Column {
        Header(stringResource(id = R.string.app_name), true, drawerState = drawerState)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Card(
                shape = RoundedCornerShape(25.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    textAlign = TextAlign.Center,
                    text = "${stringResource(id = R.string.total_paid_amount)}\n₹$totalPaidAmount",
                    color = Color.Black.copy(alpha = 0.8f),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Card(
                shape = RoundedCornerShape(25.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    textAlign = TextAlign.Center,
                    text = "${stringResource(id = R.string.total_unpaid_amount)}\n₹$totalUnPaidAmount",
                    color = Color.Black.copy(alpha = 0.8f),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp
                )
            }

        }
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = stringResource(id = R.string.agevan_name),
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp,
            color = colorResource(id = R.color.teal_700)
        )
        Spacer(modifier = Modifier.height(5.dp))
        Card(
            modifier = Modifier.padding(horizontal = 16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
            colors = CardDefaults.cardColors(Color.White),
            onClick = {
                showPopup = !showPopup
            },
            shape = RoundedCornerShape(25.dp)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()

                    .padding(16.dp)
                    .onGloballyPositioned { coordinates ->
                        // Capture the position and size of the Text view
                        anchorPosition = coordinates.positionInWindow()
                    },
                text = mSelectedAgevan.name
            )
        }

//        LazyRow(
//            modifier = Modifier.fillMaxWidth(),
//            contentPadding = PaddingValues(horizontal = 16.dp)
//        ) {
//            items(months) { month ->
//                MonthView(month, mSelectedMonth) { selectedMonth ->
//                    mSelectedMonth = selectedMonth
//                    Log.e("TAG", "Selected month : $selectedMonth")
//                }
//            }
//        }
        Spacer(modifier = Modifier.height(3.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(top = 10.dp, bottom = 80.dp)
        ) {
            items(members) { member ->
                if (mSelectedAgevan.id == 0) {
                    MemberItem(member) {
                        navHostController.currentBackStackEntry?.savedStateHandle?.set(
                            MEMBER_KEY,
                            member
                        )
                        navHostController.navigate(Screens.memberDetails.name)
                    }
                } else if (member?.agevadId == mSelectedAgevan.id?.toLong()) {
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
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp, end = 16.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        ExtendedFloatingActionButton(
            shape = RoundedCornerShape(25.dp),
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

