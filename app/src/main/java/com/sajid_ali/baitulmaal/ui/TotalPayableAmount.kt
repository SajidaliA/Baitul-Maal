package com.sajid_ali.baitulmaal.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sajid_ali.baitulmaal.R

@Composable
fun TotalPayableAmount(totalPayableAmount: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Red.copy(alpha = 0.7f))
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.total_payable_amount),
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White,
        )
        Text(
            text = " : ₹ $totalPayableAmount",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold, color = Color.White
        )
    }
}