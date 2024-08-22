package com.sajid_ali.baitulmaal.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.sajid_ali.baitulmaal.R

@Composable
fun ConfirmationDialog(
    obj: Any?,
    onDismissRequest: () -> Unit,
    onConfirmation: (Any?) -> Unit,
    dialogText: String,
    buttonText: String,
) {
    AlertDialog(
        containerColor = Color.White,
        icon = {
            Icon(
                Icons.Default.Warning,
                contentDescription = "Example Icon",
                tint = colorResource(id = R.color.teal_700)
            )
        },
        title = {
            Text(text = stringResource(id = R.string.confirmation))
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            ElevatedButton(
                onClick = {
                    onConfirmation(obj)
                }
            ) {
                Text(text = buttonText, color = colorResource(id = R.color.teal_700))
            }
        },
        dismissButton = {
            ElevatedButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text(
                    text = stringResource(id = R.string.cencel),
                    color = colorResource(id = R.color.teal_700)
                )
            }
        }
    )
}
