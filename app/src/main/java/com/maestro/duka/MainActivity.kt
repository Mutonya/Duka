package com.maestro.duka

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.maestro.duka.ui.auth.AuthScreen
import com.maestro.duka.ui.auth.LoginScreen
import com.maestro.duka.ui.auth.SignUpScreen
import com.maestro.duka.ui.navigation.AuthScreens
import com.maestro.duka.ui.navigation.AuthenticationNavigation
import com.maestro.duka.ui.onboarding.WelcomeScreen
import com.maestro.duka.ui.theme.DukaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DukaTheme {
                SignUpScreen()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val screen = AuthScreens.WelcomeScreen.route
                    val navController = rememberNavController()
                    AuthenticationNavigation(navHostController = navController, startDestination =screen )

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DukaTheme {
        Greeting("Android")
    }
}