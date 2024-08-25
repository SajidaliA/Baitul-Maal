package com.sajid_ali.baitulmaal.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sajid_ali.baitulmaal.R
import com.sajid_ali.baitulmaal.app.BMApplication
import com.sajid_ali.baitulmaal.ui.ui.theme.BaitulMaalTheme
import com.sajid_ali.baitulmaal.viewnodel.UserViewModel
import kotlinx.coroutines.launch

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val bmApp = application as BMApplication
        setContent {
            BaitulMaalTheme {
                val userViewModel: UserViewModel = viewModel()
                val users by userViewModel.users.collectAsState()
                val context = LocalContext.current
                val scope = rememberCoroutineScope()
                val isUserLogin = bmApp.userPref.isUserLogin().collectAsState(initial = false).value
                if (isUserLogin) {
                    navigateToMainActivity()
                }
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    LoginScreen { mobileNo, password, keepMeLoging ->
                        if (mobileNo.isEmpty()) {
                            Toast.makeText(
                                context,
                                "મહેરબાની કરીને મોબાઈલ નંબર દાખલ કરો",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else if (mobileNo.length != 10) {
                            Toast.makeText(
                                context,
                                "મહેરબાની કરીને સાચો મોબાઈલ નંબર દાખલ કરો",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else if (password.isEmpty()) {
                            Toast.makeText(
                                context,
                                "મહેરબાની કરીને પાસવર્ડ દાખલ કરો",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            userViewModel.login()
                            var userFound = false
                            users.forEach { user ->
                                user?.let {
                                    if (user.mobileNo == mobileNo && user.password == password) {
                                        userFound = true
                                        if (keepMeLoging) {
                                            scope.launch {
                                                bmApp.userPref.saveIsUserLogin(true)
                                            }
                                        } else {
                                            navigateToMainActivity()
                                        }
                                    }
                                }
                            }
                            if (!userFound) {
                                Toast.makeText(
                                    context,
                                    "મોબાઈલ નંબર અથવા પાસવર્ડ ખોટો છે.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun navigateToMainActivity() {
        Intent(this, MainActivity::class.java).apply {
            startActivity(this)
            finish()
        }
    }
}


@Composable
fun LoginScreen(onLoginClicked: (String, String, Boolean) -> Unit) {
    var mobileNo by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isChecked by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        Image(
            modifier = Modifier.size(80.dp),
            painter = painterResource(id = R.drawable.arabic_pattern),
            contentDescription = null,
            colorFilter = ColorFilter.tint(
                colorResource(id = R.color.teal_700)
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = colorResource(id = R.color.teal_700),
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            text = stringResource(id = R.string.app_name)
        )
        Spacer(modifier = Modifier.height(50.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp)
        ) {

            Text(
                color = Color.Black,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                text = stringResource(id = R.string.login)
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                color = Color.Black.copy(alpha = 0.5f),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                text = stringResource(id = R.string.please_sign_in)
            )
            Spacer(modifier = Modifier.height(32.dp))
            OutlinedTextField(
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.NumberPassword,
                    imeAction = ImeAction.Next
                ),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = colorResource(id = R.color.teal_700),
                    unfocusedTextColor = colorResource(id = R.color.teal_700)
                ),
                modifier = Modifier.fillMaxWidth(),
                value = mobileNo, onValueChange = { mobileNo = it },
                label = { Text(text = stringResource(id = R.string.mobile_no)) },
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = colorResource(id = R.color.teal_700),
                    unfocusedTextColor = colorResource(id = R.color.teal_700)
                ),
                modifier = Modifier.fillMaxWidth(),
                value = password, onValueChange = { password = it },
                label = { Text(text = stringResource(id = R.string.password)) })
            Spacer(modifier = Modifier.height(32.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = isChecked, onCheckedChange = { isChecked = it })
                Text(
                    color = Color.Black.copy(alpha = 0.5f),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    text = stringResource(id = R.string.keep_me_logged_in)
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.teal_700)
                ),
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = { onLoginClicked(mobileNo, password, isChecked) }) {
                Text(
                    modifier = Modifier.padding(3.dp),
                    fontWeight = FontWeight.SemiBold,
                    text = stringResource(id = R.string.login)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    BaitulMaalTheme {

    }
}