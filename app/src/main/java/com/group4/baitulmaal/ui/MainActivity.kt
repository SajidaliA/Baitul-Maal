package com.group4.baitulmaal.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.group4.baitulmaal.navigation.ComposeNavigation
import com.group4.baitulmaal.ui.theme.BaitulMaalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaitulMaalTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { _ ->
                    ComposeNavigation()
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