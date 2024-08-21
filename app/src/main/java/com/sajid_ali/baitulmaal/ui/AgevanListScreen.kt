package com.sajid_ali.baitulmaal.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import com.sajid_ali.baitulmaal.utils.agevanList

@Composable
fun AgevanListScreen(navHostController: NavHostController) {
    var showAddDialog by remember {
        mutableStateOf(false)
    }
    var openDeleteConfirmation by remember {
        mutableStateOf(false)
    }
    var mAgevan by remember {
        mutableStateOf<Agevan?>(null)
    }
    var isEdit = false
    Column(modifier = Modifier.fillMaxSize()) {
        Header(stringResource(id = R.string.agevan_list), false) {}
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            items(agevanList.subList(1, agevanList.size)) { agevan ->
                AgevanItem(navHostController, agevan, { agevanEdit ->
                    //Edit agevan
                    showAddDialog = true
                    mAgevan = agevanEdit
                    isEdit = true
                }, { agevanDelete ->
                    //Delete agevan from DB
                    openDeleteConfirmation = true
                })
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp, end = 16.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        ExtendedFloatingActionButton(
            onClick = {
                mAgevan = null
                isEdit = false
                showAddDialog = true
            },
            icon = { Icon(Icons.Filled.Add, stringResource(id = R.string.add_new_agevan)) },
            text = { Text(text = stringResource(id = R.string.add_new_agevan)) })
    }
    if (showAddDialog) {
        AddNewAgevanDialog(onDismissRequest = { showAddDialog = false }, { agevan ->
            showAddDialog = false
            if (isEdit) {
                //Updated agevan
            } else {
                //Add agevan to db
            }
        }, mAgevan = mAgevan)
    }
    if (openDeleteConfirmation) {
        ConfirmationDialog(
            mAgevan,
            onDismissRequest = { openDeleteConfirmation = false },
            onConfirmation = { agevan ->
                openDeleteConfirmation = false
                //Delete the agevan from DB
            },
            dialogTitle = stringResource(id = R.string.confirmation),
            dialogText = stringResource(id = R.string.delete_agevan_confirm),
            icon = Icons.Default.Warning
        )
    }

}


@Composable
fun AgevanItem(
    navHostController: NavHostController,
    agevan: Agevan,
    onEditClicked: (Agevan) -> Unit,
    onDeleteClicked: (Agevan) -> Unit
) {
    Card(
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Icon(Icons.Default.Create, contentDescription = null,
                    tint = colorResource(id = R.color.teal_700),
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .border(
                            width = 1.dp,
                            color = colorResource(id = R.color.teal_700),
                            RoundedCornerShape(16.dp)
                        )
                        .clickable {
                            onEditClicked(agevan)
                        }
                        .padding(5.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Icon(Icons.Default.Delete, contentDescription = null,
                    tint = colorResource(id = R.color.teal_700),
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .border(
                            width = 1.dp,
                            color = colorResource(id = R.color.teal_700),
                            RoundedCornerShape(16.dp)
                        )
                        .clickable {
                            onDeleteClicked(agevan)
                        }
                        .padding(5.dp)
                )
            }
        }
    }
}
