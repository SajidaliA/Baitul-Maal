package com.sajid_ali.baitulmaal.navigation

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.sajid_ali.baitulmaal.ui.AddNewMemberScreen
import com.sajid_ali.baitulmaal.ui.AgevanListScreen
import com.sajid_ali.baitulmaal.ui.LoginScreen
import com.sajid_ali.baitulmaal.ui.MemberDetailsScreen
import com.sajid_ali.baitulmaal.ui.MemberListScreen
import com.sajid_ali.baitulmaal.utils.addNewMemberRoute
import com.sajid_ali.baitulmaal.utils.agevanIdArg
import com.sajid_ali.baitulmaal.utils.agevanListRoute
import com.sajid_ali.baitulmaal.utils.agevanName
import com.sajid_ali.baitulmaal.utils.loginScreenRoute
import com.sajid_ali.baitulmaal.utils.memberDetailsRoute
import com.sajid_ali.baitulmaal.utils.memberListRoute

@Composable
fun ComposeNavigation(drawerState: DrawerState? = null, navController: NavHostController? = null) {
    if (navController != null) {
        NavHost(navController = navController, startDestination = agevanListRoute) {
            composable(agevanListRoute) {
                AgevanListScreen(navController, drawerState)
            }
            composable("${memberListRoute}/{$agevanIdArg}/{$agevanName}",
                arguments = listOf(
                    navArgument(agevanIdArg) {
                        type = NavType.StringType
                        defaultValue = ""
                    },
                    navArgument(agevanName) {
                        type = NavType.StringType
                        defaultValue = ""
                    }

                )
            ) { backStackEntry ->
                val agevanId = requireNotNull(
                    backStackEntry.arguments
                ).getString(agevanIdArg)
                val agevanName = requireNotNull(
                    backStackEntry.arguments
                ).getString(agevanName)
                MemberListScreen(navController, drawerState, agevanId, agevanName)
            }
            composable(memberDetailsRoute) {
                MemberDetailsScreen(navController)
            }
            composable(addNewMemberRoute) {
                AddNewMemberScreen(navController)
            }
            composable(loginScreenRoute) {
                LoginScreen { _: String, _: String, _: Boolean -> }
            }
        }
    }
}