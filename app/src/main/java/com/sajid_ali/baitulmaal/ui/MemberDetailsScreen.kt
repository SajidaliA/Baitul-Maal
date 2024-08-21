package com.sajid_ali.baitulmaal.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.sajid_ali.baitulmaal.R
import com.sajid_ali.baitulmaal.data.Member
import com.sajid_ali.baitulmaal.data.Month
import com.sajid_ali.baitulmaal.utils.MEMBER_KEY
import com.sajid_ali.baitulmaal.utils.Screens
import com.sajid_ali.baitulmaal.utils.aukafAmount
import com.sajid_ali.baitulmaal.utils.madresaFeesAmount
import com.sajid_ali.baitulmaal.utils.months

@Composable
fun MemberDetailsScreen(navController: NavHostController? = null) {
    val member =
        navController?.previousBackStackEntry?.savedStateHandle
            ?.get<Member>(MEMBER_KEY)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Header(stringResource(id = R.string.member_details), showEdit = true) {
            navController?.currentBackStackEntry?.savedStateHandle?.set(
                MEMBER_KEY,
                member
            )
            navController?.navigate(Screens.addNewMember.name)
        }
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxHeight()
        ) {
            member?.let {
                MemberDetails(member)
                if (member.paidMonths == 12) {
                    AllPaid(member.totalPaidAmount)
                } else {
                    TotalPayableAmount(member.totalPayableAmount)
                }
            }
        }
    }
}

@Composable
fun AllPaid(totalPaidAmount: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.green))
            .padding(16.dp)
    ) {
        Text(
            text = " ₹ $totalPaidAmount ",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold, color = Color.White
        )
        Text(
            text = stringResource(id = R.string.paid),
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White,
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
        Text(
            text = stringResource(id = R.string.head_of_the_family),
            color = colorResource(id = R.color.teal_700),
            fontSize = 12.sp,
        )
        Text(text = member.name, fontSize = 14.sp)
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 10.dp),
            color = (Color.Black).copy(alpha = 0.1f)
        )
        Text(
            text = stringResource(id = R.string.number_of_four_years_above),
            color = colorResource(id = R.color.teal_700),
            fontSize = 12.sp
        )
        Text(text = member.fourYearsAbove.toString(), fontSize = 14.sp)
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 10.dp),
            color = (Color.Black).copy(alpha = 0.1f)
        )
        Text(
            text = stringResource(id = R.string.aukaf_amount),
            color = colorResource(id = R.color.teal_700),
            fontSize = 12.sp
        )
        Text(text = "₹ $aukafAmount", fontSize = 14.sp)
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 10.dp),
            color = (Color.Black).copy(alpha = 0.1f)
        )
        Text(
            text = stringResource(id = R.string.total_aukaf_amount),
            color = colorResource(id = R.color.teal_700),
            fontSize = 12.sp
        )
        Text(text = "₹ ${member.totalAukafAmount}", fontSize = 14.sp)
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 10.dp),
            color = (Color.Black).copy(alpha = 0.1f)
        )
        Text(
            text = stringResource(id = R.string.no_of_children_studies_in_madresa),
            color = colorResource(id = R.color.teal_700),
            fontSize = 12.sp
        )
        Text(text = member.studyInMadresa.toString(), fontSize = 14.sp)
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 10.dp),
            color = (Color.Black).copy(alpha = 0.1f)
        )
        Text(
            text = stringResource(id = R.string.madresa_fee_amount),
            color = colorResource(id = R.color.teal_700),
            fontSize = 12.sp
        )
        Text(text = "₹ $madresaFeesAmount", fontSize = 14.sp)
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 10.dp),
            color = (Color.Black).copy(alpha = 0.1f)
        )
        Text(
            text = stringResource(id = R.string.total_fees_amount),
            color = colorResource(id = R.color.teal_700),
            fontSize = 12.sp
        )
        Text(text = "₹ ${member.totalMadresaFeeAmount}", fontSize = 14.sp)
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 10.dp),
            color = (Color.Black).copy(alpha = 0.1f)
        )
        Text(
            text = stringResource(id = R.string.total_payable_amount_for_one_month),
            color = colorResource(id = R.color.teal_700),
            fontSize = 12.sp
        )
        Text(text = "₹ ${member.totalPayableAmountForOneMonth}", fontSize = 14.sp)
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 10.dp),
            color = (Color.Black).copy(alpha = 0.1f)
        )
        if (member.paidMonths != 12) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "${12 - member.paidMonths} ${stringResource(id = R.string.un_paid_months)}",
                color = Color.Red,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
        }
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
    Text(
        text = month.title,
        modifier = Modifier
            .background(
                shape = RoundedCornerShape(20.dp),
                color = if (month.isPaid) colorResource(id = R.color.green) else Color.Red.copy(
                    alpha = 0.9f
                )
            )
            .padding(horizontal = 16.dp, vertical = 5.dp),
        color = Color.White,
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold
    )
    Spacer(modifier = Modifier.width(10.dp))
}

@Preview
@Composable
private fun MemberDetailsScreenPrev() {
    MemberDetailsScreen()
}