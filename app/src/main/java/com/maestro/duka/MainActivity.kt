package com.maestro.duka

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.maestro.duka.domain.usecases.AuthUseCases.LocalUserManagerUseCases
import com.maestro.duka.ui.auth.AuthScreen
import com.maestro.duka.ui.auth.LoginScreen
import com.maestro.duka.ui.auth.SignUpScreen
import com.maestro.duka.navigation.AuthScreens
import com.maestro.duka.navigation.AuthenticationNavigation
import com.maestro.duka.ui.onboarding.WelcomeScreen
import com.maestro.duka.ui.theme.DukaTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var appEntryUseCase:LocalUserManagerUseCases
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        lifecycleScope.launch {
            appEntryUseCase.readAppEntry().collect{
                Log.d("APPENTRY",it.toString())
            }
        }

        setContent {
            DukaTheme {


                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val screen = AuthScreens.WelcomeScreen.route

                    AuthenticationNavigation(startDestination =screen )

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