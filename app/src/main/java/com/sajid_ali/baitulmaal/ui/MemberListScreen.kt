package com.sajid_ali.baitulmaal.ui

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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.sajid_ali.baitulmaal.R
import com.sajid_ali.baitulmaal.utils.MEMBER_KEY
import com.sajid_ali.baitulmaal.utils.addNewMemberRoute
import com.sajid_ali.baitulmaal.utils.aukafAmount
import com.sajid_ali.baitulmaal.utils.madresaFeesAmount
import com.sajid_ali.baitulmaal.utils.memberDetailsRoute
import com.sajid_ali.baitulmaal.viewnodel.MemberViewModel

@Composable
fun MemberListScreen(
    navHostController: NavHostController,
    drawerState: DrawerState?,
    agevanId: String?,
    agevanName: String?,
) {
    var totalPaidAmount = 0
    var totalUnPaidAmount = 0
    val memberViewModel: MemberViewModel = viewModel()
    val mMemberList by memberViewModel.members.collectAsState()

    val filteredMembers = mMemberList.filter { it?.agevadId == agevanId }
    filteredMembers.forEach { member ->
        member?.let {
            it.totalAukafAmount = it.fourYearsAbove.toInt() * aukafAmount
            it.totalMadresaFeeAmount = it.studyInMadresa.toInt() * madresaFeesAmount
            it.totalPayableAmountForOneMonth = it.totalAukafAmount + it.totalMadresaFeeAmount
            it.totalPayableAmount = it.totalPayableAmountForOneMonth * (12 - it.paidMonths)
            it.totalPaidAmount = it.totalPayableAmountForOneMonth * it.paidMonths
            totalPaidAmount += it.totalPaidAmount
            totalUnPaidAmount += it.totalPayableAmount
        }
    }

    Column {
        Header(agevanName ?: stringResource(id = R.string.app_name), drawerState = drawerState)
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
            modifier = Modifier.padding(start = 20.dp),
            text = stringResource(id = R.string.member_list),
            color = Color.Black.copy(alpha = 0.5f)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            items(filteredMembers) { member ->
                MemberItem(member) {
                    navHostController.currentBackStackEntry?.savedStateHandle?.set(
                        MEMBER_KEY,
                        member
                    )
                    navHostController.navigate(memberDetailsRoute)
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
                navHostController.navigate(addNewMemberRoute)
            },
            icon = { Icon(Icons.Filled.Add, stringResource(id = R.string.add_new_member)) },
            text = { Text(text = stringResource(id = R.string.add_new_member)) })
    }
}

