package com.sajid_ali.baitulmaal.navigation

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sajid_ali.baitulmaal.ui.AddNewMemberScreen
import com.sajid_ali.baitulmaal.ui.AgevanListScreen
import com.sajid_ali.baitulmaal.ui.LoginScreen
import com.sajid_ali.baitulmaal.ui.MemberDetailsScreen
import com.sajid_ali.baitulmaal.ui.MemberListScreen
import com.sajid_ali.baitulmaal.utils.Screens

@Composable
fun ComposeNavigation(drawerState: DrawerState? = null, navController: NavHostController? = null) {

    if (navController != null) {
        NavHost(navController = navController, startDestination = Screens.memberList.name) {
            composable(Screens.memberList.name) {
                MemberListScreen(navController, drawerState)
            }
            composable(Screens.memberDetails.name) {
                MemberDetailsScreen(navController)
            }
            composable(Screens.addNewMember.name) {
                AddNewMemberScreen(navController)
            }
            composable(Screens.agevanList.name) {
                AgevanListScreen(navController)
            }
            composable(Screens.loginscreen.name) {
                LoginScreen { _: String, _: String -> }
            }
        }
    }
}