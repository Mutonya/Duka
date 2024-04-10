package com.maestro.duka.ui.onboarding

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.maestro.duka.ui.auth.AuthScreen
import com.maestro.duka.ui.core.RoundedButton
import com.maestro.duka.ui.navigation.AuthScreens
import com.maestro.duka.ui.theme.DukaTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WelcomeScreen(
    navController: NavHostController
){

    val pages = listOf(
        OnBoardingPage.FirstPage,
        OnBoardingPage.SecondPage,
        OnBoardingPage.ThirdPage
    )

    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        pages.size
    }
    Column (modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)
    ){
        HorizontalPager(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.Top,
            state = pagerState
        ) {
            PagerScreen(onBoardingPage = pages[it])
        }

        Row(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(bottom = 8.dp, end = 8.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color =
                    if (pagerState.currentPage == iteration) Color.Black else Color.Gray
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .weight(1f)
                        .size(8.dp)
                )
            }

            Spacer(modifier = Modifier.width(8.dp))



            RoundedButton(onButtonClicked = {

                navController.navigate(AuthScreens.AuthScreen.route)
            },
                contentColor = Color.White,
                backgroundcolor =Color.Black,
                modifier = Modifier
                    .padding(bottom = 10.dp, end = 8.dp),
                pagerState = pagerState
                    )
        }
    }

}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DukaTheme {
        WelcomeScreen(rememberNavController())
    }
}
