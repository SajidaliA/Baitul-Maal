package com.group4.baitulmaal.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.group4.baitulmaal.ui.AddNewMemberScreen
import com.group4.baitulmaal.ui.MemberDetailsScreen
import com.group4.baitulmaal.ui.MemberListScreen
import com.group4.baitulmaal.utils.Screens

@Composable
fun ComposeNavigation() {

    val navController  = rememberNavController()
    
    NavHost(navController = navController, startDestination = Screens.memberList.name) {
        composable(Screens.memberList.name){
            MemberListScreen(navController)
        }
        composable(Screens.memberDetails.name){
            MemberDetailsScreen(navController)
        }
        composable(Screens.addNewMember.name){
            AddNewMemberScreen(navController)
        }
    }
}