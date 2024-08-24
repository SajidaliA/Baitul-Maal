package com.sajid_ali.baitulmaal.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.sajid_ali.baitulmaal.R
import com.sajid_ali.baitulmaal.callbacks.DataUpdateCallback
import com.sajid_ali.baitulmaal.model.Member
import com.sajid_ali.baitulmaal.model.Month
import com.sajid_ali.baitulmaal.utils.MEMBER_KEY
import com.sajid_ali.baitulmaal.utils.Screens
import com.sajid_ali.baitulmaal.utils.aukafAmount
import com.sajid_ali.baitulmaal.utils.madresaFeesAmount
import com.sajid_ali.baitulmaal.utils.months
import com.sajid_ali.baitulmaal.viewnodel.MemberViewModel

@Composable
fun MemberDetailsScreen(navController: NavHostController? = null) {
    var member =
        navController?.previousBackStackEntry?.savedStateHandle
            ?.get<Member>(MEMBER_KEY)


    var openDeleteConfirmation by remember {
        mutableStateOf(false)
    }

    var showAddNewPaymentDialog by remember {
        mutableStateOf(false)
    }

    val memberViewModel: MemberViewModel = viewModel()
    val members = memberViewModel.members.collectAsState()
    val filteredList = members.value.filter { it?.id == member?.id }
    if (filteredList.isNotEmpty()) {
        member = filteredList[0]
    }


    Scaffold(
        topBar = {
            Header(stringResource(id = R.string.member_details))
        },
        bottomBar = {
            member?.let {
                HorizontalDivider()
                if (member.paidMonths == 12) {
                    AllPaid(member.totalPaidAmount)
                } else {
                    TotalPayableAmount(member.totalPayableAmount)
                }
            }
        }) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            member?.let {
                MemberDetails(it)
                Column(modifier = Modifier.padding(16.dp)) {
                    EditDelete(setColor = true, it, { member ->
                        navController?.currentBackStackEntry?.savedStateHandle?.set(
                            MEMBER_KEY,
                            member
                        )
                        navController?.navigate(Screens.addNewMember.name)
                    }) { member ->
                        openDeleteConfirmation = true
                    }
                    Spacer(modifier = Modifier.height(32.dp))

                    ElevatedButton(
                        enabled = member.paidMonths < 12,
                        elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 3.dp),
                        colors = ButtonDefaults.buttonColors(colorResource(id = R.color.teal_700)),
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            showAddNewPaymentDialog = true
                        }) {
                        Text(
                            modifier = Modifier.padding(3.dp),
                            fontWeight = FontWeight.SemiBold,
                            text = stringResource(id = R.string.add_new_payment),
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
    val context = LocalContext.current
    if (showAddNewPaymentDialog) {
        AddNewPaymentDialog(onDismissRequest = { showAddNewPaymentDialog = false }) {
            showAddNewPaymentDialog = false
            member?.let { member ->
                if (it > (12 - member.paidMonths)) {
                    Toast.makeText(
                        context,
                        "મહિનાઓ બાકી મહિના કરતાં વધુ ન હોવા જોઈએ",
                        Toast.LENGTH_SHORT
                    ).show()

                } else {
                    member.paidMonths = member.paidMonths.plus(it)
                    member.totalPayableAmount =
                        member.totalPayableAmountForOneMonth * (12 - member.paidMonths)
                    memberViewModel.updateMember(member, object : DataUpdateCallback {
                        override fun onSuccess() {
                            Toast.makeText(
                                context,
                                "સભ્ય સફળતાપૂર્વક અપડેટ થયા",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        override fun onFailure() {
                            Toast.makeText(context, "સભ્ય અપડેટ થયા નથી", Toast.LENGTH_SHORT).show()
                        }

                    })
                }
            }
        }
    }
    if (openDeleteConfirmation) {
        ConfirmationDialog(
            obj = member,
            onDismissRequest = { openDeleteConfirmation = false },
            onConfirmation = { memberDelete ->
                memberDelete as Member
                openDeleteConfirmation = false
                memberViewModel.deleteMember(memberDelete.id, object : DataUpdateCallback {
                    override fun onSuccess() {
                        Toast.makeText(
                            context,
                            "સભ્ય સફળતાપૂર્વક ડીલીટ થયા",
                            Toast.LENGTH_SHORT
                        ).show()
                        navController?.popBackStack()
                    }

                    override fun onFailure() {
                        Toast.makeText(
                            context,
                            "સભ્ય ડીલીટ થયા નથી",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                })
            },
            dialogText = stringResource(id = R.string.delete_member_confirm),
            buttonText = stringResource(id = R.string.delete)
        )
    }
}

@Composable
fun AllPaid(totalPaidAmount: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Text(
            text = " ₹ $totalPaidAmount ",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = colorResource(id = R.color.green)
        )
        Text(
            text = stringResource(id = R.string.paid),
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = colorResource(id = R.color.green)
        )

    }
}

@Composable
fun MemberDetails(member: Member) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Card(
            shape = RoundedCornerShape(25.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.head_of_the_family),
                    color = colorResource(id = R.color.teal_700),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                )
                Text(text = member.headOfTheFamilyName)
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 16.dp),
                    color = (Color.Black).copy(alpha = 0.1f)
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "${stringResource(id = R.string.number_of_four_years_above)} : ${member.fourYearsAbove}",
                    color = colorResource(id = R.color.teal_700),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                )
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 16.dp),
                    color = (Color.Black).copy(alpha = 0.1f)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = stringResource(id = R.string.aukaf_amount),
                            color = Color.Black.copy(alpha = 0.5f),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp
                        )
                        Text(text = "₹ $aukafAmount")
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = stringResource(id = R.string.total_aukaf_amount),
                            color = Color.Black.copy(alpha = 0.5f),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp
                        )
                        Text(text = "₹ ${member.totalAukafAmount}")
                    }
                }
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 16.dp),
                    color = (Color.Black).copy(alpha = 0.1f)
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "${stringResource(id = R.string.no_of_children_studies_in_madresa)} : ${member.studyInMadresa}",
                    color = colorResource(id = R.color.teal_700),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                )
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 16.dp),
                    color = (Color.Black).copy(alpha = 0.1f)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = stringResource(id = R.string.madresa_fee_amount),
                            color = Color.Black.copy(alpha = 0.5f),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp
                        )
                        Text(text = "₹ $madresaFeesAmount")
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = stringResource(id = R.string.total_fees_amount),
                            color = Color.Black.copy(alpha = 0.5f),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp
                        )
                        Text(text = "₹ ${member.totalMadresaFeeAmount}")
                    }
                }
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 16.dp),
                    color = (Color.Black).copy(alpha = 0.1f)
                )
                Text(
                    text = stringResource(id = R.string.total_payable_amount_for_one_month),
                    color = colorResource(id = R.color.teal_700),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                )
                Text(text = "₹ ${member.totalPayableAmountForOneMonth}")
                if (member.paidMonths != 12) {
                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 16.dp),
                        color = (Color.Black).copy(alpha = 0.1f)
                    )

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = "${12 - member.paidMonths} ${stringResource(id = R.string.un_paid_months)}",
                        color = Color.Red,
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow(contentPadding = PaddingValues(top = 16.dp)) {
            itemsIndexed(months) { index, month ->
                month.isPaid = index + 1 <= member.paidMonths
                MonthView(month = month)
            }
        }
    }
}

@Composable
fun MonthView(month: Month) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        shape = RoundedCornerShape(25.dp),
    ) {
        Text(
            text = month.title,
            modifier = Modifier
                .background(
                    color = if (month.isPaid) colorResource(id = R.color.green) else Color.Red.copy(
                        alpha = 0.9f
                    )
                )
                .padding(horizontal = 16.dp, vertical = 5.dp),
            color = Color.White,
            fontSize = 14.sp
        )
    }

    Spacer(modifier = Modifier.width(16.dp))
}

@Preview
@Composable
private fun MemberDetailsScreenPrev() {
    MemberDetailsScreen()
}