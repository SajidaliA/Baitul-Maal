package com.sajid_ali.baitulmaal.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.sajid_ali.baitulmaal.R
import com.sajid_ali.baitulmaal.navigation.ComposeNavigation
import com.sajid_ali.baitulmaal.ui.theme.BaitulMaalTheme
import com.sajid_ali.baitulmaal.utils.Screens
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaitulMaalTheme {
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()
                val navController = rememberNavController()
                var openLogoutConfirmDialog by remember {
                    mutableStateOf(false)
                }
                ModalNavigationDrawer(
                    modifier = Modifier.background(color = Color.White),
                    drawerState = drawerState,
                    drawerContent = {
                        ModalDrawerSheet {
                            Text(
                                text = "${stringResource(id = R.string.app_name)} વિકલ્પો",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(color = colorResource(id = R.color.teal_700))
                                    .padding(16.dp),
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White
                            )
                            HorizontalDivider()
                            NavigationDrawerItem(
                                icon = {
                                    Icon(
                                        Icons.Default.Home, contentDescription = null,
                                        tint = colorResource(
                                            id = R.color.teal_700
                                        )
                                    )
                                },
                                label = {
                                    Text(
                                        text = stringResource(id = R.string.member_list),
                                        color = Color.Black.copy(alpha = 0.8f)
                                    )
                                },
                                selected = false,
                                onClick = {
                                    navController.navigate(Screens.memberList.name)
                                    scope.launch {
                                        drawerState.apply {
                                            if (isClosed) open() else close()
                                        }
                                    }
                                })
                            NavigationDrawerItem(
                                icon = {
                                    Icon(
                                        Icons.Default.AccountCircle, contentDescription = null,
                                        tint = colorResource(
                                            id = R.color.teal_700
                                        )
                                    )
                                },
                                label = {
                                    Text(
                                        text = "આગેવાન લિસ્ટ",
                                        color = Color.Black.copy(alpha = 0.8f)
                                    )
                                },
                                selected = false,
                                onClick = {
                                    navController.navigate(Screens.agevanList.name)
                                    scope.launch {
                                        drawerState.apply {
                                            if (isClosed) open() else close()
                                        }
                                    }
                                })
                            NavigationDrawerItem(
                                icon = {
                                    Icon(
                                        Icons.AutoMirrored.Filled.ExitToApp,
                                        contentDescription = null,
                                        tint = colorResource(
                                            id = R.color.teal_700
                                        )
                                    )
                                },
                                label = {
                                    Text(
                                        text = stringResource(id = R.string.logout),
                                        color = Color.Black.copy(alpha = 0.8f)
                                    )
                                },
                                selected = false,
                                onClick = {
                                    openLogoutConfirmDialog = true
                                })
                        }
                    },
                    gesturesEnabled = true
                ) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize()
                    ) { _ ->
                        ComposeNavigation(drawerState, navController)
                    }
                }
                if (openLogoutConfirmDialog) {
                    ConfirmationDialog(
                        obj = null,
                        onDismissRequest = {
                            openLogoutConfirmDialog = false
                            scope.launch {
                                drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        },
                        onConfirmation = {
                            openLogoutConfirmDialog = false
                            navController.navigate(Screens.loginscreen.name) {
                                popUpTo(0)
                            }
                            scope.launch {
                                drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        },
                        dialogText = stringResource(id = R.string.logout_confirm),
                        buttonText = stringResource(id = R.string.logout)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BaitulMaalTheme {
        ComposeNavigation()
    }
}