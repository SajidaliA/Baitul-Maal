package com.sajid_ali.baitulmaal.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.sajid_ali.baitulmaal.R
import com.sajid_ali.baitulmaal.data.Agevan


@Composable
fun AgevanItem(
    navHostController: NavHostController,
    agevan: Agevan,
    onEditClicked: (Any) -> Unit,
    onDeleteClicked: (Any) -> Unit,
) {
    Card(
        shape = RoundedCornerShape(25.dp),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(3.dp),
        onClick = {
            navHostController.popBackStack()
        },
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f)
                ) {
                    Text(
                        text = stringResource(id = R.string.agevan_name),
                        color = colorResource(id = R.color.teal_700).copy(alpha = 0.8f),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp
                    )
                    Text(text = agevan.name)
                    Text(
                        text = "${stringResource(id = R.string.mobile_no)} ${agevan.contactNo}",
                        color = Color.Black.copy(alpha = 0.5f),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp
                    )
                }

                Text(
                    modifier = Modifier.padding(end = 16.dp),
                    text = "${stringResource(id = R.string.members)} : ${agevan.members.size}",
                    color = colorResource(id = R.color.teal_700).copy(alpha = 0.8f),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp
                )

            }
            Spacer(modifier = Modifier.height(16.dp))
            EditDelete(setColor = false, obj = agevan, onEditClicked, onDeleteClicked)
        }
    }
}