package com.sajid_ali.baitulmaal.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.sajid_ali.baitulmaal.R
import com.sajid_ali.baitulmaal.model.Agevan

@Composable
fun AgevanListPopup(
    agevanList: List<Agevan?>,
    anchorPosition: Offset,
    onAgevanSelect: (Agevan?) -> Unit,
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
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 1.dp)
                .background(color = Color.White)
                .border(
                    1.dp,
                    color = colorResource(id = R.color.teal_700),
                    RoundedCornerShape(10.dp)
                )
        ) {
            Column {
                agevanList.forEach { agevan ->
                    Text(
                        text = agevan?.name ?: "",
                        modifier = Modifier

                            .fillMaxWidth()
                            .clickable {
                                onAgevanSelect(agevan)
                            }
                            .padding(16.dp))
                }
            }
        }
    }
}