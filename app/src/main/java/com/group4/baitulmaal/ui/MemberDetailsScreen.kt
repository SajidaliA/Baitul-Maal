package com.group4.baitulmaal.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.group4.baitulmaal.R
import com.group4.baitulmaal.data.Member
import com.group4.baitulmaal.utils.MEMBER_KEY
import com.group4.baitulmaal.utils.Screens
import com.group4.baitulmaal.utils.aukafAmount
import com.group4.baitulmaal.utils.madresaFeesAmount

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
            modifier = Modifier.fillMaxHeight()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.head_of_the_family),
                    color = Color.Gray,
                    fontSize = 12.sp
                )
                member?.name?.let { Text(text = it,  fontSize = 14.sp, fontWeight = FontWeight.SemiBold) }
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = stringResource(id = R.string.number_of_four_years_above),
                    color = Color.Gray,
                    fontSize = 12.sp
                )
                Text(text = member?.fourYearsAbove.toString(), fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = stringResource(id = R.string.aukaf_amount),
                    color = Color.Gray,
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "₹ $aukafAmount", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = stringResource(id = R.string.total_aukaf_amount),
                    color = Color.Gray,
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "₹ ${member?.totalAukafAmount}", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = stringResource(id = R.string.no_of_children_studies_in_madresa),
                    color = Color.Gray,
                    fontSize = 12.sp
                )
                Text(text = member?.studyInMadresa.toString(), fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = stringResource(id = R.string.madresa_fee_amount),
                    color = Color.Gray,
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "₹ $madresaFeesAmount", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = stringResource(id = R.string.total_fees_amount),
                    color = Color.Gray,
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "₹ ${member?.totalMadresaFeeAmount}", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                member?.totalPayableAmountForOneMonth?.let { TotalPayableAmount(it) }
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = if (member?.unPaidMonths == 0) {
                        stringResource(id = R.string.paid)
                    }else{
                        stringResource(id = R.string.not_paid)
                    },
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = if (member?.unPaidMonths == 0) {
                        colorResource(id = R.color.green)
                    }else{
                        Color.Red
                    },
                    textAlign = TextAlign.Center,
                )
            }

        }

    }

}

@Preview
@Composable
private fun MemberDetailsScreenPrev() {
    MemberDetailsScreen()
}