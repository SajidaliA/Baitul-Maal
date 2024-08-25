package com.sajid_ali.baitulmaal.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.sajid_ali.baitulmaal.R

@Composable
fun MenuPopup(
    anchorPosition: Offset,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
) {
    Popup(
        alignment = Alignment.TopStart,
        offset = IntOffset(
            x = anchorPosition.x.toInt(),
            y = anchorPosition.y.toInt()
        )
    ) {
        Box(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .background(
                    shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp),
                    color = colorResource(id = R.color.teal_700)
                )
        ) {
            Column {
                Text(
                    text = stringResource(id = R.string.edit_member),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp,
                    color = Color.White,
                    modifier = Modifier
                        .clickable {
                            onEdit()
                        }
                        .padding(16.dp))
                Text(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp,
                    color = Color.White,
                    text = stringResource(id = R.string.delete),
                    modifier = Modifier
                        .clickable {
                            onDelete()
                        }
                        .padding(16.dp))

            }
        }
    }
}