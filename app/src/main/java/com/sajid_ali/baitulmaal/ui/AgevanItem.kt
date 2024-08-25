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
import com.sajid_ali.baitulmaal.model.Agevan


@Composable
fun AgevanItem(
    agevan: Agevan?,
    onAgevanLicked: (Agevan) -> Unit,
) {
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



    Card(
        shape = RoundedCornerShape(25.dp),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(3.dp),
        onClick = {
            agevan?.let { onAgevanLicked(it) }
        },
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = stringResource(id = R.string.agevan_name),
                            color = colorResource(id = R.color.teal_700).copy(alpha = 0.8f),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp
                        )
                        Text(
                            text = "${stringResource(id = R.string.members)} ${agevan?.members?.size}",
                            color = colorResource(id = R.color.teal_700).copy(alpha = 0.8f),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp
                        )
                    }

                    Text(text = agevan?.name ?: "")
                    Text(
                        text = "${stringResource(id = R.string.mobile_no)} ${agevan?.contactNo}",
                        color = Color.Black.copy(alpha = 0.5f),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp
                    )

                    Text(
                        text = "${stringResource(id = R.string.total_payable_amount_for_year)}: ₹${totalPayableAmount}",
                        color = colorResource(id = R.color.teal_700).copy(alpha = 0.8f),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = "${stringResource(id = R.string.total_paid_amount)}: ₹${totalPaidAmount}",
                            color = colorResource(id = R.color.green).copy(alpha = 0.8f),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp
                        )

                        Text(
                            text = "${stringResource(id = R.string.total_unpaid_amount)}: ₹${totalUnpaidAmount}",
                            color = Color.Red.copy(alpha = 0.8f),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp
                        )

                    }
                }

            }
        }
    }
}