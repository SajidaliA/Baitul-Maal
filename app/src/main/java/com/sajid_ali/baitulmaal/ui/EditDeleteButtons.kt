package com.sajid_ali.baitulmaal.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sajid_ali.baitulmaal.R

@Composable
fun EditDelete(
    setColor: Boolean = false,
    obj: Any,
    onEditClicked: (Any) -> Unit,
    onDeleteClicked: (Any) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        ElevatedButton(
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 3.dp),
            colors = if (setColor) {
                ButtonDefaults.buttonColors(Color.White)
            } else {
                ButtonDefaults.buttonColors(MaterialTheme.colorScheme.surface)
            },
            modifier = Modifier.weight(1f), onClick = { onEditClicked(obj) }) {
            Text(
                stringResource(id = R.string.edit_member),
                color = colorResource(id = R.color.teal_700)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        ElevatedButton(
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 3.dp),
            colors = if (setColor) {
                ButtonDefaults.buttonColors(Color.White)
            } else {
                ButtonDefaults.buttonColors(MaterialTheme.colorScheme.surface)
            },
            modifier = Modifier.weight(1f), onClick = { onDeleteClicked(obj) }) {
            Text(stringResource(id = R.string.delete), color = colorResource(id = R.color.teal_700))
        }
    }
}