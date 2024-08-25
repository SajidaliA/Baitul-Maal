package com.sajid_ali.baitulmaal.ui

import android.widget.Toast
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
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.sajid_ali.baitulmaal.R
import com.sajid_ali.baitulmaal.callbacks.DataUpdateCallback
import com.sajid_ali.baitulmaal.model.Agevan
import com.sajid_ali.baitulmaal.utils.memberListRoute
import com.sajid_ali.baitulmaal.viewnodel.AgevanViewModel

@Composable
fun AgevanListScreen(navHostController: NavHostController, drawerState: DrawerState?) {
    var showAddDialog by remember {
        mutableStateOf(false)
    }
    var openDeleteConfirmation by remember {
        mutableStateOf(false)
    }
    val mAgevan by remember {
        mutableStateOf<Agevan?>(null)
    }
    var isEdit by remember {
        mutableStateOf(false)
    }
    val agevanViewModel: AgevanViewModel = viewModel()
    val agevanList by agevanViewModel.agevanList.collectAsState()

    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize()) {
        Header(stringResource(id = R.string.app_name), true, drawerState = drawerState)
        LazyColumn(
            contentPadding = PaddingValues(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 80.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {

            items(agevanList) { agevan ->
                AgevanItem(agevan) { agevanId, agevanName ->
                    navHostController.navigate("${memberListRoute}/$agevanId/$agevanName")
                }
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
        var id = ""
        var agevanName = ""
        var agevanContactNo = ""
        if (isEdit) {
            id = mAgevan?.id ?: ""
            agevanName = mAgevan?.name ?: ""
            agevanContactNo = mAgevan?.contactNo ?: ""
        }
        AddNewAgevanDialog(
            id,
            agevanName,
            agevanContactNo,
            isEdit,
            onDismissRequest = { showAddDialog = false },
            { updatedId, name, contactNo ->
                showAddDialog = false
                val agevan = Agevan(id = updatedId, name = name, contactNo = contactNo)
                if (isEdit) {
                    agevanViewModel.updateAgevan(agevan, object : DataUpdateCallback {
                        override fun onSuccess() {
                            Toast.makeText(
                                context,
                                "આગેવાન સફળતાપૂર્વક અપડેટ થયા",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        override fun onFailure() {
                            Toast.makeText(context, "આગેવાન અપડેટ થયા નથી", Toast.LENGTH_SHORT)
                                .show()
                        }

                    })
                } else {
                    agevanViewModel.addAgevan(agevan, object : DataUpdateCallback {
                        override fun onSuccess() {
                            Toast.makeText(
                                context,
                                "આગેવાન સફળતાપૂર્વક ઉમેરાયા",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        override fun onFailure() {
                            Toast.makeText(context, "આગેવાન ઉમેરાયા નથી", Toast.LENGTH_SHORT).show()
                        }

                    })
                }
            })
    }
    if (openDeleteConfirmation) {
        ConfirmationDialog(
            mAgevan,
            onDismissRequest = { openDeleteConfirmation = false },
            onConfirmation = { agevan ->
                agevan?.let {
                    agevan as Agevan
                    openDeleteConfirmation = false
                    agevanViewModel.deleteAgevan(agevan.id, object : DataUpdateCallback {
                        override fun onSuccess() {
                            Toast.makeText(
                                context,
                                "આગેવાન સફળતાપૂર્વક ડીલીટ થયા",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        override fun onFailure() {
                            Toast.makeText(context, "આગેવાન ડીલીટ થયા નથી", Toast.LENGTH_SHORT)
                                .show()
                        }

                    })
                }
            },
            dialogText = stringResource(id = R.string.delete_agevan_confirm),
            buttonText = stringResource(id = R.string.delete)
        )
    }

}
