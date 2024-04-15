package com.maestro.duka.ui.auth

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maestro.duka.R
import com.maestro.duka.data.remote.dto.AuthResponse
import com.maestro.duka.di.DukaApplication
import com.maestro.duka.ui.auth.vm.AuthViewModel
import com.maestro.duka.ui.core.AuthButtonComponent
import com.maestro.duka.ui.core.EmailComponent
import com.maestro.duka.ui.core.PasswordComponent
import com.maestro.duka.ui.home.HomeActivity
import com.maestro.duka.utils.Resource

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    authViewModel: AuthViewModel?
){

    val loginState by authViewModel!!.loginState.collectAsState()
    subscribeToLoginEvents(loginState)

    Scaffold (Modifier.background(color = Color.White)){
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 16.dp, end = 16.dp, bottom = 40.dp, top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,

        ){
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.25f),
                painter = painterResource(id = R.drawable.logo),
                contentScale = ContentScale.FillBounds,
                contentDescription = "Logo"
            )



            Text(
                modifier = Modifier
                    .fillMaxWidth()

                    .padding(top = 8.dp),
                text = stringResource(id = R.string.welcome),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                color = Color.Black
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 8.dp),
                text = stringResource(id = R.string.pleaselogin),
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Start,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(16.dp))

          EmailComponent(label = stringResource(id = R.string.email),
              onTextSelected = {
               authViewModel!!.onEvent(LoginUiEvents.EmailChanged(it))

          }, modifier = Modifier.padding(8.dp))

            PasswordComponent(label = stringResource(id = R.string.password),
                onTextSelected = {
                  authViewModel!!.onEvent(LoginUiEvents.EmailChanged(it))


            }, modifier = Modifier.padding(8.dp))

            Spacer(modifier = Modifier.height(32.dp))

            AuthButtonComponent(
                value = stringResource(id = R.string.login),
                onButtonClicked = {


                  authViewModel!!.onEvent(LoginUiEvents.LoginButtonClicked)

                                  },
                contentColor = Color.White,
                backgroundcolor = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp, top = 8.dp)
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 8.dp),
                text = stringResource(id = R.string.or),
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                color = Color.Black
            )


            Spacer(modifier = Modifier.height(8.dp))

            AuthButtonComponent(
                value = stringResource(id = R.string.continuewithgoogle),
                onButtonClicked = {
                    val context = DukaApplication.context // Use your application context
                    val intent = Intent(context, HomeActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)
                },
                contentColor = Color.Black,
                backgroundcolor = Color.White,
                modifier = Modifier.padding(bottom = 8.dp)
            )





        }
    }



}

@Composable
private fun subscribeToLoginEvents(loginState: Resource<AuthResponse>) {
    when(loginState){
        is Resource.Error -> {

            //show error
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
@Preview(showBackground = true)
fun FirstOnBoardingScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        LoginScreen(null)
    }
}