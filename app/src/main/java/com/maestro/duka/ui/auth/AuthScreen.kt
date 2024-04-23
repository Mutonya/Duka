package com.maestro.duka.ui.auth

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.maestro.duka.R
import com.maestro.duka.ui.core.AuthButtonComponent
import com.maestro.duka.navigation.AuthScreens
import com.maestro.duka.ui.core.ButtonWithLeadingIcon
import com.maestro.duka.ui.core.DividerTextComponent
import com.maestro.duka.ui.core.TopBar
import com.maestro.duka.ui.theme.DukaTheme
import com.maestro.duka.ui.theme.fonts

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AuthScreen(
    navHostController: NavHostController
){


    Scaffold {

        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.White)){

        }

        Column (
            Modifier
                .fillMaxSize()
                .padding(
                    start = 16.dp,
                    end = 16.dp, bottom = 32.dp

                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top){

          TopBar {
              //onclick
          }

            Image(
                modifier = Modifier
                    .padding(8.dp)
                    .size(80.dp)
                    .clip(CircleShape),
                alignment = Alignment.Center,
                painter = painterResource(id = R.drawable.logolight), contentDescription =null )
            Text(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, bottom = 8.dp, top = 8.dp),
                fontSize = 18.sp,
                fontFamily = fonts,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                text = stringResource(id = R.string.letsyouin))


            ButtonWithLeadingIcon(
                value = stringResource(id = R.string.continuewithfacebook),
                onButtonClicked = {
                    navHostController.navigate(AuthScreens.LoginScreen.route)
                                  },

                modifier = Modifier,
                icon = R.drawable.facebook
            )


            Spacer(modifier = Modifier.height(8.dp))


            ButtonWithLeadingIcon(
                value = stringResource(id = R.string.continuewithgoogle),
                onButtonClicked = {
                    navHostController.navigate(AuthScreens.SignUpScreen.route)
                },

                modifier = Modifier,
                icon = R.drawable.google
            )
            ButtonWithLeadingIcon(
                value = stringResource(id = R.string.continuewithapple),
                onButtonClicked = {
                    navHostController.navigate(AuthScreens.SignUpScreen.route)
                },

                modifier = Modifier,
                icon = R.drawable.apple
            )

            DividerTextComponent()
            AuthButtonComponent(
                value = stringResource(id = R.string.signinwithpassword),
                onButtonClicked = {
                    navHostController.navigate(AuthScreens.SignUpScreen.route)
                },
                contentColor = colorResource(id = R.color.black),
                backgroundcolor = Color.Transparent,
                modifier = Modifier,

            )

            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.Gray)) {
                        append("Don't have an account?")
                    }


                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Black)) {
                        append("Sign up")
                    }

                }
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