package com.group4.baitulmaal.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.group4.baitulmaal.R

@Composable
fun Header(
    title: String,
    showYear: Boolean = false,
    showEdit: Boolean = false,
    onEditClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.teal_700))
    ) {
        Text(
            modifier = Modifier.padding(15.dp),
            text = title,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        if (showYear){
            Text(text = "2024",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(15.dp),
                textAlign = TextAlign.End)
        }
        if (showEdit) {
            Icon(Icons.Default.Create, contentDescription = null, tint = Color.White,
                modifier = Modifier
                    .clickable {
                        onEditClick()
                    }
                    .padding(15.dp)
            )
        }
    }
}