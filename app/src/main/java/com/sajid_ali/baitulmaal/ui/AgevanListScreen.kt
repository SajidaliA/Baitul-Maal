package com.sajid_ali.baitulmaal.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
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
    var isEdit by remember {
        mutableStateOf(false)
    }
    Column(modifier = Modifier.fillMaxSize()) {
        Header(stringResource(id = R.string.agevan_list), false)
        LazyColumn(
            contentPadding = PaddingValues(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 80.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(agevanList.subList(1, agevanList.size)) { agevan ->
                AgevanItem(navHostController, agevan, { agevanEdit ->
                    //Edit agevan

                    isEdit = true
                    mAgevan = agevanEdit as Agevan
                    showAddDialog = true
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
            shape = RoundedCornerShape(25.dp),
            onClick = {
                isEdit = false
                showAddDialog = true
            },
            icon = { Icon(Icons.Filled.Add, stringResource(id = R.string.add_new_agevan)) },
            text = { Text(text = stringResource(id = R.string.add_new_agevan)) })
    }
    if (showAddDialog) {
        var agevanName = ""
        var agevanContactNo = ""
        if (isEdit) {
            agevanName = mAgevan?.name ?: ""
            agevanContactNo = mAgevan?.contactNo ?: ""
        }
        AddNewAgevanDialog(
            agevanName,
            agevanContactNo,
            isEdit,
            onDismissRequest = { showAddDialog = false },
            { name, contactNo ->
            showAddDialog = false
            if (isEdit) {
                //Updated agevan
            } else {
                //Add agevan to db
            }
            })
    }
    if (openDeleteConfirmation) {
        ConfirmationDialog(
            mAgevan,
            onDismissRequest = { openDeleteConfirmation = false },
            onConfirmation = { agevan ->
                openDeleteConfirmation = false
                //Delete the agevan from DB
            },
            dialogText = stringResource(id = R.string.delete_agevan_confirm),
            buttonText = stringResource(id = R.string.delete)
        )
    }

}
