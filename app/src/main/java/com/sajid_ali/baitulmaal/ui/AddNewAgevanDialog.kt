package com.sajid_ali.baitulmaal.ui

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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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

@Composable
fun AddNewAgevanDialog(
    name: String,
    contactNo: String,
    isEdit: Boolean,
    onDismissRequest: () -> Unit,
    onAddConfirm: (String, String) -> Unit,
) {
    var agevanName by remember {
        mutableStateOf("")
    }
    var agevanContactNo by remember {
        mutableStateOf("")
    }
    if (isEdit) {
        agevanName = name
        agevanContactNo = contactNo
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
                    text = if (isEdit) {
                        stringResource(id = R.string.edit_member)
                    } else {
                        stringResource(id = R.string.add_new_agevan)
                    },
                    fontWeight = FontWeight.SemiBold,
                    color = colorResource(id = R.color.teal_700)
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    value = agevanName,
                    onValueChange = { agevanName = it },
                    label = {
                        Text(text = stringResource(id = R.string.agevan_name))
                    })
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    value = agevanContactNo,
                    onValueChange = { agevanContactNo = it },
                    label = {
                        Text(text = stringResource(id = R.string.mobile_no))
                    })
                Spacer(modifier = Modifier.height(32.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                ) {
                    ElevatedButton(
                        colors = ButtonDefaults.buttonColors(colorResource(id = R.color.teal_700)),
                        elevation = ButtonDefaults.elevatedButtonElevation(3.dp),
                        onClick = { onDismissRequest() },
                        modifier = Modifier
                            .fillMaxWidth(0.5f),
                    ) {
                        Text(
                            stringResource(id = R.string.cencel),
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    ElevatedButton(
                        colors = ButtonDefaults.buttonColors(colorResource(id = R.color.teal_700)),
                        elevation = ButtonDefaults.elevatedButtonElevation(3.dp),
                        onClick = { onAddConfirm(agevanName, agevanContactNo) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                    ) {
                        Text(
                            stringResource(id = R.string.add),
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    }
}