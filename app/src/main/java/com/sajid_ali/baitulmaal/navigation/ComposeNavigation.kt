package com.sajid_ali.baitulmaal.navigation

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sajid_ali.baitulmaal.ui.AddNewMemberScreen
import com.sajid_ali.baitulmaal.ui.AgevanListScreen
import com.sajid_ali.baitulmaal.ui.LoginScreen
import com.sajid_ali.baitulmaal.ui.MemberListScreen
import com.sajid_ali.baitulmaal.utils.addNewMemberRoute
import com.sajid_ali.baitulmaal.utils.agevanListRoute
import com.sajid_ali.baitulmaal.utils.loginScreenRoute
import com.sajid_ali.baitulmaal.utils.memberListRoute

@Composable
fun ComposeNavigation(
    drawerState: DrawerState? = null,
    navHostController: NavHostController? = null,
) {
    if (navHostController != null) {
        NavHost(navController = navHostController, startDestination = agevanListRoute) {
            composable(agevanListRoute) {
                AgevanListScreen(navHostController, drawerState)
            }
            composable(memberListRoute) {
                MemberListScreen(navHostController, drawerState)
            }
            composable(addNewMemberRoute) {
                AddNewMemberScreen(navHostController)
            }
            composable(loginScreenRoute) {
                LoginScreen { _: String, _: String, _: Boolean -> }
            }
        }
    }
}