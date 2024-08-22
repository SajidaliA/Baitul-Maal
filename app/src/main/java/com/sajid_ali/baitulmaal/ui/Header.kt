package com.sajid_ali.baitulmaal.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sajid_ali.baitulmaal.R
import kotlinx.coroutines.launch

@Composable
fun Header(
    title: String,
    showYear: Boolean = false,
    drawerState: DrawerState? = null,
) {
    val scope = rememberCoroutineScope()
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.teal_700))
    ) {

        Icon(
            Icons.Default.Menu,
            contentDescription = null,
            modifier = Modifier
                .clickable {
                    scope.launch {
                        drawerState?.apply {
                            if (isClosed) open() else close()
                        }
                    }
                }
                .padding(16.dp),
            tint = Color.White
        )
        
        Text(
            modifier = Modifier
                .padding(16.dp)
                .weight(1f),
            text = title,
            color = Color.White,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp
        )
        if (showYear) {
            Text(
                text = "(2024-25)",
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.End
            )
        }
    }
}