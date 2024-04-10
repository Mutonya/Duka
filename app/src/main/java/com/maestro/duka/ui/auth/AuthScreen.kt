package com.maestro.duka.ui.auth

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.maestro.duka.R
import com.maestro.duka.ui.core.AuthButtonComponent
import com.maestro.duka.ui.navigation.AuthScreens
import com.maestro.duka.ui.theme.DukaTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AuthScreen(
    navHostController: NavHostController
){


    Scaffold {

        Box(modifier = Modifier.fillMaxSize()){
            Image(painter = painterResource(id = R.drawable.splash),
                contentDescription =null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.matchParentSize())
        }

        Column (
            Modifier
                .fillMaxSize()
                .padding(
                    start = 16.dp,
                    end = 16.dp, bottom = 32.dp

                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom){

            AuthButtonComponent(
                value = stringResource(id = R.string.login),
                onButtonClicked = {
                    navHostController.navigate(AuthScreens.LoginScreen.route)
                                  },
                contentColor = colorResource(id = R.color.black) ,
                backgroundcolor = colorResource(id = R.color.white),
                modifier = Modifier
            )


            Spacer(modifier = Modifier.height(8.dp))


            AuthButtonComponent(
                value = stringResource(id = R.string.signin),
                onButtonClicked = {
                    navHostController.navigate(AuthScreens.SignUpScreen.route)
                },
                contentColor = colorResource(id = R.color.white),
                backgroundcolor = Color.Transparent,
                modifier = Modifier
            )

        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DukaTheme {
        AuthScreen(rememberNavController())
    }
}