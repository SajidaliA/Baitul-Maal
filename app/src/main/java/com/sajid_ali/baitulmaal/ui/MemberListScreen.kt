package com.sajid_ali.baitulmaal.ui

import android.content.Context
import android.widget.Toast
import androidx.activity.compose.BackHandler
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
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.sajid_ali.baitulmaal.R
import com.sajid_ali.baitulmaal.callbacks.DataUpdateCallback
import com.sajid_ali.baitulmaal.model.Agevan
import com.sajid_ali.baitulmaal.model.Member
import com.sajid_ali.baitulmaal.utils.AGEVAN_KEY
import com.sajid_ali.baitulmaal.utils.MEMBER_KEY
import com.sajid_ali.baitulmaal.utils.addNewMemberRoute
import com.sajid_ali.baitulmaal.viewnodel.AgevanViewModel

@Composable
fun MemberListScreen(
    navHostController: NavHostController,
    drawerState: DrawerState?,
) {
    val agevanViewModel: AgevanViewModel = viewModel()
    var agevan =
        navHostController.previousBackStackEntry?.savedStateHandle
            ?.get<Agevan>(AGEVAN_KEY)
    agevanViewModel.getAgevanByid(agevan?.id)
    agevan = agevanViewModel.agevan.collectAsState().value

    var openMemberDetails by remember {
        mutableStateOf(false)
    }
    var mMember by remember {
        mutableStateOf(Member())
    }
    var mIndex by remember {
        mutableIntStateOf(0)
    }
    var showMenu by remember {
        mutableStateOf(false)
    }
    var showEditDialog by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current

    var totalPayableAmount = 0
    var totalPaidAmount = 0
    var totalUnpaidAmount = 0

    agevan?.members?.forEach { member ->
        member?.let {
            totalPayableAmount += member.totalPayableAmount
            totalPaidAmount += member.totalPaidAmount
            totalUnpaidAmount += member.totalUnpaidAmount
        }
        agevan.totalPayableAmount = totalPayableAmount
        agevan.totalPaidAmount = totalPaidAmount
        agevan.totalUnPaidAmount = totalUnpaidAmount
    }

    Column {
        Header(
            agevan?.name ?: stringResource(id = R.string.app_name),
            drawerState = drawerState,
            showOptionMenu = true,
            showMenu = showMenu,
            onEditClicked = {
                showEditDialog = true
            }, onDeleteClicked = {
                agevan?.let {
                    agevanViewModel.deleteAgevan(it.id, object : DataUpdateCallback {
                        override fun onSuccess() {
                            Toast.makeText(
                                context,
                                "આગેવાન સફળતાપૂર્વક ડીલીટ થયા",
                                Toast.LENGTH_SHORT
                            ).show()
                            navHostController.popBackStack()
                        }

                        override fun onFailure() {
                            Toast.makeText(
                                context,
                                "આગેવાન ડીલીટ થયા નથી",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    })
                }
            })
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
                    text = "${stringResource(id = R.string.total_paid_amount)}\n₹${agevan?.totalPaidAmount ?: ""}",
                    color = colorResource(id = R.color.green),
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
                    text = "${stringResource(id = R.string.total_unpaid_amount)}\n₹${agevan?.totalUnPaidAmount ?: ""}",
                    color = Color.Red.copy(alpha = 0.8f),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp
                )
            }

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 34.dp), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.member_list),
                color = colorResource(id = R.color.teal_700),
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp
            )
            Text(
                text = "${stringResource(id = R.string.members)} ${agevan?.members?.size}",
                color = colorResource(id = R.color.teal_700),
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp
            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            var memberList = emptyList<Member>()
            if (!agevan?.members.isNullOrEmpty()) {
                memberList = agevan?.members as List<Member>
            }
            itemsIndexed(memberList) { index, member ->
                MemberItem(member) {
                    showMenu = false
                    mMember = member
                    mIndex = index
                    openMemberDetails = true
                }
            }
        }
    }
    if (showEditDialog) {
        agevan?.let {
            AddNewAgevanDialog(
                it.name,
                it.contactNo,
                isEdit = true,
                onDismissRequest = { showEditDialog = false },
                { name, contactNo ->
                    showEditDialog = false
                    it.name = name
                    it.contactNo = contactNo
                    agevanViewModel.updateAgevan(it, object : DataUpdateCallback {
                        override fun onSuccess() {
                            Toast.makeText(
                                context,
                                "આગેવાન સફળતાપૂર્વક અપડેટ થયા",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        override fun onFailure() {
                            Toast.makeText(context, "આગેવાન અપડેટ થયા નથી", Toast.LENGTH_SHORT)
                                .show()
                        }

                    })
                })
        }
    }
    if (openMemberDetails) {
        MemberDetailsScreen(
            mMember,
            onMemberUpdate = {
                openMemberDetails = false
                navHostController.currentBackStackEntry?.savedStateHandle?.set(
                    MEMBER_KEY,
                    mMember
                )
                navHostController.currentBackStackEntry?.savedStateHandle?.set(
                    AGEVAN_KEY,
                    agevan
                )
                navHostController.navigate(addNewMemberRoute)
            },
            onNewPaymentAdded = {
                updateAgevan(context, agevan, mIndex, mMember, agevanViewModel)
            },
            onDeleteMember = {
                openMemberDetails = false
                agevan?.members?.remove(mMember)
                agevanViewModel.updateAgevan(agevan, object : DataUpdateCallback {
                    override fun onSuccess() {
                        Toast.makeText(
                            context,
                            "સભ્ય સફળતાપૂર્વક ડીલીટ થયા",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    override fun onFailure() {
                        Toast.makeText(context, "સભ્ય ડીલીટ થયા નથી", Toast.LENGTH_SHORT)
                            .show()
                    }
                })
            }
        )
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 16.dp, end = 16.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            ExtendedFloatingActionButton(
                containerColor = colorResource(id = R.color.teal_700),
                contentColor = Color.White,
                shape = RoundedCornerShape(30.dp),
                onClick = {
                    navHostController.navigate(addNewMemberRoute)
                },
                icon = { Icon(Icons.Filled.Add, stringResource(id = R.string.add_new_member)) },
                text = {
                    Text(
                        text = stringResource(id = R.string.add_new_member),
                        fontWeight = FontWeight.SemiBold
                    )
                })
        }
    }
    BackHandler {
        if (openMemberDetails) {
            openMemberDetails = false
        } else {
            navHostController.popBackStack()
        }
    }
}

fun updateAgevan(
    context: Context,
    agevan: Agevan?,
    index: Int,
    member: Member,
    agevanViewModel: AgevanViewModel,
) {
    agevan?.members?.set(index, member)
    agevanViewModel.updateAgevan(agevan, object : DataUpdateCallback {
        override fun onSuccess() {
            Toast.makeText(
                context,
                "સભ્ય સફળતાપૂર્વક અપડેટ થયા",
                Toast.LENGTH_SHORT
            ).show()
        }

        override fun onFailure() {
            Toast.makeText(context, "સભ્ય અપડેટ થયા નથી", Toast.LENGTH_SHORT)
                .show()
        }
    })
}

