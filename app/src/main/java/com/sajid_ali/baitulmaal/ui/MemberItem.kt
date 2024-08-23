package com.sajid_ali.baitulmaal.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sajid_ali.baitulmaal.R
import com.sajid_ali.baitulmaal.model.Member


@Composable
fun MemberItem(member: Member?, onMemberClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(25.dp),
        onClick = onMemberClick,
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 20.dp)
        ) {
            Text(
                text = member?.headOfTheFamilyName ?: ""
            )

            Text(
                text = "${stringResource(id = R.string.total_payable_amount_for_one_month)} : ₹${member?.totalPayableAmountForOneMonth}",
                color = Color.Black.copy(alpha = 0.5f),
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp
            )
            if (member?.paidMonths != 12) {
                Text(
                    text = if (member?.paidMonths != null) {
                        "${12 - member.paidMonths} ${stringResource(id = R.string.un_paid_months)}"
                    } else "",
                    fontSize = 12.sp,
                    color = Color.Red.copy(alpha = 0.7f)
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "${stringResource(id = R.string.total_payable_amount)} : ₹${member?.totalPayableAmount}",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    color = colorResource(id = R.color.teal_700)
                )
                if (member?.paidMonths == 12) {
                    Text(
                        text = stringResource(id = R.string.paid),
                        color = colorResource(id = R.color.green).copy(alpha = 0.9f),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp,
                    )
                }
            }
        }
    }
}