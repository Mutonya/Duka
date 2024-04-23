package com.maestro.duka.ui.onboarding

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
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
import com.maestro.duka.ui.theme.DukaTheme
import com.maestro.duka.ui.theme.fonts

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WelcometoApp(){

    Scaffold {


        Box(modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White))
        {

            Column (
                Modifier
                    .fillMaxSize()
                    .padding(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top){
                Image(painter = painterResource(id = R.drawable.buy),
                    contentDescription =null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.6f))

                Text(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, bottom = 8.dp, top = 8.dp),
                    fontSize = 34.sp,
                    fontFamily = fonts,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    text = stringResource(id = R.string.welcometo))
           Spacer(modifier = Modifier.height(4.dp))
                Text(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, bottom = 32.dp, top = 8.dp),
                    fontSize = 24.sp,
                    fontFamily = fonts,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal,
                    text = stringResource(id = R.string.appforyourneeds))


            }
        }

    }


}
@Preview
@Composable
fun previewScreen(){
    DukaTheme {
        WelcometoApp()
    }
}