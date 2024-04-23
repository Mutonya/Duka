package com.maestro.duka.ui.auth

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.maestro.duka.di.DukaApplication
import com.maestro.duka.ui.core.AuthButtonComponent
import com.maestro.duka.ui.core.CheckBoxComponent
import com.maestro.duka.ui.core.EmailComponent
import com.maestro.duka.ui.core.PasswordComponent
import com.maestro.duka.ui.core.UserName
import com.maestro.duka.ui.home.HomeActivity

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignUpScreen(){

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {
        Surface(
            color = Color.White,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(start = 16.dp, end = 16.dp, bottom = 8.dp, top = 0.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,


                ) {

                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
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
                Spacer(modifier = Modifier.height(8.dp))
                UserName(label = stringResource(id = R.string.username), onTextSelected = {

                }, modifier = Modifier.padding(8.dp))
                Spacer(modifier = Modifier.height(8.dp))
                EmailComponent(label = stringResource(id = R.string.email), onTextSelected = {

                }, modifier = Modifier.padding(8.dp))
                Spacer(modifier = Modifier.height(8.dp))
                PasswordComponent(label = stringResource(id = R.string.password), onTextSelected = {

                }, modifier = Modifier.padding(8.dp))
                Spacer(modifier = Modifier.height(8.dp))
                PasswordComponent(label = stringResource(id = R.string.confirm), onTextSelected = {

                }, modifier = Modifier.padding(8.dp))

                Spacer(modifier = Modifier.height(8.dp))

                CheckBoxComponent(value = stringResource(id = R.string.consent)) {

                }
                AuthButtonComponent(
                    value = stringResource(id = R.string.login),
                    onButtonClicked = {
                        val context = DukaApplication.context // Use your application context
                        val intent = Intent(context, HomeActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        context.startActivity(intent)
                    },
                    contentColor = Color.White,
                    backgroundcolor = Color.Black,
                    modifier = Modifier.padding(bottom = 8.dp, top = 8.dp),

                )

            }
        }
    }
}
@Composable
@Preview(showBackground = true)
fun PreviewSignUp() {
    Column(modifier = Modifier.fillMaxSize()) {
        SignUpScreen()
    }
}