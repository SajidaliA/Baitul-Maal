package com.sajid_ali.baitulmaal.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.sajid_ali.baitulmaal.R
import com.sajid_ali.baitulmaal.data.Agevan

@Composable
fun AddNewAgevanDialog(
    onDismissRequest: () -> Unit,
    onAddConfirm: (Agevan) -> Unit,
    mAgevan: Agevan? = null
) {
    var agevan by remember {
        mutableStateOf(Agevan(0, "", emptyList(), ""))
    }
    if (mAgevan != null) {
        agevan = mAgevan
    }
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            colors = CardDefaults.cardColors(Color.White),
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(32.dp)) {
                Text(
                    text = if (agevan.name.isEmpty()) {
                        stringResource(id = R.string.add_new_agevan)
                    } else {
                        stringResource(id = R.string.edit_member)
                    },
                    fontWeight = FontWeight.SemiBold,
                    color = colorResource(id = R.color.teal_700)
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    value = agevan.name,
                    onValueChange = { agevan.name = it },
                    label = {
                        Text(text = stringResource(id = R.string.agevan_name))
                    })
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    value = agevan.contactNo,
                    onValueChange = { agevan.contactNo = it },
                    label = {
                        Text(text = stringResource(id = R.string.mobile_no))
                    })
                Spacer(modifier = Modifier.height(32.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                ) {
                    TextButton(
                        onClick = { onDismissRequest() },
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .border(
                                1.dp, shape = RoundedCornerShape(20.dp), color = colorResource(
                                    id = R.color.teal_700
                                )
                            ),
                    ) {
                        Text(
                            stringResource(id = R.string.cencel),
                            color = colorResource(id = R.color.teal_700),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    TextButton(
                        onClick = { onAddConfirm(agevan) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .border(
                                1.dp, shape = RoundedCornerShape(20.dp), color = colorResource(
                                    id = R.color.teal_700
                                )
                            ),
                    ) {
                        Text(
                            stringResource(id = R.string.add),
                            color = colorResource(id = R.color.teal_700),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    }
}