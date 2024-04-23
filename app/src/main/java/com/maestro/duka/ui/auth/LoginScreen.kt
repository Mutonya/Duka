package com.maestro.duka.ui.auth

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maestro.duka.R
import com.maestro.duka.data.remote.dto.AuthResponse
import com.maestro.duka.di.DukaApplication
import com.maestro.duka.ui.auth.vm.AuthViewModel
import com.maestro.duka.ui.core.AuthButtonComponent
import com.maestro.duka.ui.core.ButtonWithLeadingIcon
import com.maestro.duka.ui.core.DividerTextComponent
import com.maestro.duka.ui.core.EmailComponent
import com.maestro.duka.ui.core.PasswordComponent
import com.maestro.duka.ui.home.HomeActivity
import com.maestro.duka.ui.theme.Black
import com.maestro.duka.ui.theme.fonts
import com.maestro.duka.utils.Resource

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    authViewModel: AuthViewModel?
){

    val loginState by authViewModel!!.loginState.collectAsState()
    SubscribeToLoginEvents(loginState)

    Surface {
        Column(modifier = Modifier.fillMaxSize()) {

            TopAPPScreen()

            Spacer(modifier = Modifier.height(36.dp))

            Column (modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp)){

                LoginFieldSection(authViewModel)

                DividerTextComponent()

                SocialMediaSection()

                Box(modifier = Modifier
                    .fillMaxHeight(0.8f)
                    .fillMaxWidth(), contentAlignment = Alignment.BottomCenter){
                    Text(
                        buildAnnotatedString {
                            withStyle(style = SpanStyle(fontFamily = fonts,color = Color.Gray)) {
                                append("Don't have an account? ")
                            }


                            withStyle(style = SpanStyle(fontFamily = fonts, fontWeight = FontWeight.Bold, color = Color.Black)) {
                                append("Sign up")
                            }

                        })
                }


            }
        }

    }

}

@Composable
private fun SubscribeToLoginEvents(loginState: Resource<AuthResponse>) {
    when(loginState){
        is Resource.Error -> {

            Log.e("Error", loginState.message)
        }
        is Resource.Loading -> {
            //show progressbar
            CircularProgressIndicator()
        }
        is Resource.Success -> {
            Snackbar(modifier = Modifier.padding(8.dp), content = { Text("Logged in Successfully ") })

            Log.e("Login",loginState.toString())

            val context = DukaApplication.context // Use your application context
            val intent = Intent(context, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK

            context.startActivity(intent)
        }

        Resource.Idle -> {

        }
    }
}

@Composable
private fun SocialMediaSection() {
    Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center) {
        ButtonWithLeadingIcon(
            value = stringResource(id = R.string.continuewithgoogle),
            onButtonClicked = {

            },
            modifier = Modifier.weight(1f),
            icon = R.drawable.google
        )
        Spacer(modifier = Modifier.width(8.dp))
        ButtonWithLeadingIcon(
            value = stringResource(id = R.string.continuewithfacebook),
            onButtonClicked = {

            },
            modifier = Modifier.weight(1f),
            icon = R.drawable.facebook
        )



    }
}

@Composable
private fun LoginFieldSection(authViewModel: AuthViewModel?) {
    EmailComponent(label = stringResource(id = R.string.email),
        onTextSelected = {
            authViewModel!!.onEvent(LoginUiEvents.EmailChanged(it))

        }, modifier = Modifier
    )

    PasswordComponent(label = stringResource(id = R.string.password),
        onTextSelected = {
            authViewModel!!.onEvent(LoginUiEvents.EmailChanged(it))


        }, modifier = Modifier
    )

    Spacer(modifier = Modifier.height(32.dp))

    AuthButtonComponent(
        value = stringResource(id = R.string.login),
        onButtonClicked = {


            authViewModel!!.onEvent(LoginUiEvents.LoginButtonClicked)

        },
        contentColor = Color.White,
        backgroundcolor = Color.Black,
        modifier = Modifier.padding(bottom = 8.dp, top = 8.dp),

        )
}

@Composable
private fun TopAPPScreen() {
    val uicolor = if (isSystemInDarkTheme()) Color.White else Black
    if (isSystemInDarkTheme()) R.drawable.logodark else R.drawable.logolight

    Box(contentAlignment = Alignment.TopCenter) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f),
            painter = painterResource(id = R.drawable.shape),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )


        Row(
            modifier = Modifier.padding(top = 60.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape),
                painter = painterResource(id = R.drawable.logolight),
                contentDescription = null,
                tint= Color.Unspecified
            )
            Spacer(modifier = Modifier.width(15.dp))
            Column {
                Text(
                    text = stringResource(id = R.string.duka),
                    fontFamily = fonts,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = uicolor
                )
                Text(
                    text = stringResource(id = R.string.onestop),
                    fontFamily = fonts,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = uicolor
                )

            }

        }

        Text(
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.BottomCenter),
            text = stringResource(id = R.string.login),
            fontFamily = fonts,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = uicolor
        )


    }
}