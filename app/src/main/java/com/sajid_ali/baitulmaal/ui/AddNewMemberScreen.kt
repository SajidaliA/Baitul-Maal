package com.sajid_ali.baitulmaal.ui

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
import androidx.compose.runtime.collectAsState
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.sajid_ali.baitulmaal.R
import com.sajid_ali.baitulmaal.model.Member
import com.sajid_ali.baitulmaal.utils.MEMBER_KEY
import com.sajid_ali.baitulmaal.utils.aukafAmount
import com.sajid_ali.baitulmaal.utils.madresaFeesAmount
import com.sajid_ali.baitulmaal.viewnodel.AgevanViewModel

@Composable
fun AddNewMemberScreen(navController: NavHostController? = null) {

    val member =
        navController?.previousBackStackEntry?.savedStateHandle
            ?.get<Member>(MEMBER_KEY)

    var headOfTheFamily by remember {
        mutableStateOf(member?.headOfTheFamilyName ?: "")
    }
    var aboveFourYears by remember {
        mutableStateOf(member?.fourYearsAbove ?: "")
    }

    var totalAukafAmount by remember {
        mutableIntStateOf(0)
    }
    if (aboveFourYears.isNotEmpty()) {
        totalAukafAmount = aboveFourYears.toInt() * aukafAmount
    }

    var studyInMadresa by remember {
        mutableStateOf(member?.studyInMadresa ?: "")
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

    val agevanName =
        member?.let { getAgevanById(member.agevadId) } ?: stringResource(id = R.string.agevan_name)

    var mSelectedAgevan by remember {
        mutableStateOf(agevanName)
    }

    var anchorPosition by remember { mutableStateOf(Offset.Zero) }
    var showPopup by remember { mutableStateOf(false) }
    val agevanViewModel: AgevanViewModel = viewModel()
    val agevanList by agevanViewModel.agevanList.collectAsState()

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
        )
        Column(verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
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
                        .padding(16.dp)
                        .onGloballyPositioned { coordinates ->
                            // Capture the position and size of the Text view
                            anchorPosition = coordinates.positionInWindow()
                        },
                    text = mSelectedAgevan
                )
                Spacer(modifier = Modifier.height(16.dp))
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
                Spacer(modifier = Modifier.height(16.dp))
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
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = stringResource(id = R.string.aukaf_amount))
                    Text(text = " :-  ₹ $aukafAmount", fontWeight = FontWeight.SemiBold)
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = stringResource(id = R.string.total_aukaf_amount))
                    Text(text = " :-  ₹ $totalAukafAmount", fontWeight = FontWeight.SemiBold)
                }
                Spacer(modifier = Modifier.height(16.dp))
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
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = stringResource(id = R.string.madresa_fee_amount))
                    Text(text = " :-  ₹ $madresaFeesAmount", fontWeight = FontWeight.SemiBold)
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = stringResource(id = R.string.total_fees_amount))
                    Text(text = " :-  ₹ $totalFeesAmount", fontWeight = FontWeight.SemiBold)
                }
                Spacer(modifier = Modifier.height(32.dp))
                totalPayableAmount = totalAukafAmount + totalFeesAmount
                Text(
                    text = "${stringResource(id = R.string.total_payable_amount_for_one_month)} : ₹ $totalPayableAmount",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    color = Color.Red.copy(alpha = 0.8f)
                )
            }

            Button(
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.teal_700)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                onClick = {
                    //Add/Update member to DB
                }
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    fontWeight = FontWeight.SemiBold,
                    text = stringResource(id = R.string.add)
                )
            }
        }

    }

    if (showPopup) {
        AgevanListPopup(agevanList, anchorPosition) {
            mSelectedAgevan = it?.name ?: ""
            showPopup = false
        }
    }


}

fun getAgevanById(agevadId: Long?): String {
    return "Agevan name"
}

@Preview
@Composable
private fun AddNewMemberPrev() {
    AddNewMemberScreen()
}