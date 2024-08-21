package com.group4.baitulmaal.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.group4.baitulmaal.R

@Composable
fun TotalPayableAmount(totalPayableAmount: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Text(
            text = stringResource(id = R.string.total_payable_amount_for_one_month),
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = colorResource(id = R.color.teal_700),
        )
        Text(
            text = " :-  ₹ $totalPayableAmount",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold, color = colorResource(id = R.color.teal_700)
        )
    }
}