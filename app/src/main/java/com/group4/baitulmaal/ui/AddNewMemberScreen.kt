package com.group4.baitulmaal.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.group4.baitulmaal.R
import com.group4.baitulmaal.data.Member
import com.group4.baitulmaal.utils.MEMBER_KEY
import com.group4.baitulmaal.utils.aukafAmount
import com.group4.baitulmaal.utils.madresaFeesAmount

@Composable
fun AddNewMemberScreen(navController: NavHostController? = null) {

    val member =
        navController?.previousBackStackEntry?.savedStateHandle
            ?.get<Member>(MEMBER_KEY)

    var headOfTheFamily by remember {
        mutableStateOf("")
    }
    var aboveFourYears by remember {
        mutableStateOf("")
    }

    var totalAukafAmount by remember {
        mutableIntStateOf(0)
    }
    if (aboveFourYears.isNotEmpty()) {
        totalAukafAmount = aboveFourYears.toInt() * aukafAmount
    }
    var studyInMadresa by remember {
        mutableStateOf("")
    }
    var totalFeesAmount by remember {
        mutableIntStateOf(0)
    }
    if (studyInMadresa.isNotEmpty()) {
        totalFeesAmount = studyInMadresa.toInt() * madresaFeesAmount
    }
    var totalPayableAmount by remember {
        mutableIntStateOf(0)
    }
    var mSelectedAgevan by remember {
        mutableStateOf("આગેવાન નું નામ")
    }

    var anchorPosition by remember { mutableStateOf(Offset.Zero) }
    var showPopup by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Header(
            title = if (member == null) {
                stringResource(id = R.string.add_new_member)
            } else {
                stringResource(id = R.string.edit_member)
            }
        ) {}
        Column(verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            )
            {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            1.dp,
                            color = colorResource(id = R.color.teal_700),
                            RoundedCornerShape(5.dp)
                        )
                        .clickable { showPopup = !showPopup }
                        .padding(15.dp)
                        .onGloballyPositioned { coordinates ->
                            // Capture the position and size of the Text view
                            anchorPosition = coordinates.positionInWindow()
                        },
                    text = mSelectedAgevan
                )
                Spacer(modifier = Modifier.height(15.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = headOfTheFamily,
                    onValueChange = { headOfTheFamily = it },
                    label = {
                        Text(text = stringResource(id = R.string.head_of_the_family))
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = colorResource(
                            id = R.color.teal_700
                        ),
                        unfocusedTextColor = Color.LightGray
                    ),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                )
                Spacer(modifier = Modifier.height(15.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = aboveFourYears,
                    onValueChange = {
                        aboveFourYears = it
                    },
                    label = {
                        Text(text = stringResource(id = R.string.number_of_four_years_above))
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = colorResource(
                            id = R.color.teal_700
                        ),
                        unfocusedTextColor = Color.LightGray
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next)
                )
                Spacer(modifier = Modifier.height(15.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = stringResource(id = R.string.aukaf_amount))
                    Text(text = " :-  ₹ $aukafAmount", fontWeight = FontWeight.SemiBold)
                }
                Spacer(modifier = Modifier.height(15.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = stringResource(id = R.string.total_aukaf_amount))
                    Text(text = " :-  ₹ $totalAukafAmount", fontWeight = FontWeight.SemiBold)
                }
                Spacer(modifier = Modifier.height(15.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = studyInMadresa,
                    onValueChange = {
                        studyInMadresa = it
                    },
                    label = {
                        Text(text = stringResource(id = R.string.no_of_children_studies_in_madresa))
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = colorResource(
                            id = R.color.teal_700
                        ),
                        unfocusedTextColor = Color.LightGray
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done)
                )
                Spacer(modifier = Modifier.height(15.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = stringResource(id = R.string.madresa_fee_amount))
                    Text(text = " :-  ₹ $madresaFeesAmount", fontWeight = FontWeight.SemiBold)
                }
                Spacer(modifier = Modifier.height(15.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = stringResource(id = R.string.total_fees_amount))
                    Text(text = " :-  ₹ $totalFeesAmount", fontWeight = FontWeight.SemiBold)
                }
                Spacer(modifier = Modifier.height(15.dp))
                totalPayableAmount = totalAukafAmount + totalFeesAmount
                TotalPayableAmount(totalPayableAmount)
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.teal_700))
            ) {
                Text(
                    text = stringResource(id = R.string.add),
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp
                )
            }
        }

    }

    if (showPopup) {
        AgevanListPopup(anchorPosition) {
            mSelectedAgevan = it
            showPopup = false
        }
    }


}

@Preview
@Composable
private fun AddNewMemberPrev() {
    AddNewMemberScreen()
}