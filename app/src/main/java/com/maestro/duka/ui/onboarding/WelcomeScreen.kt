package com.maestro.duka.ui.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
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
import com.maestro.duka.R
import com.maestro.duka.ui.core.RoundedButton
import com.maestro.duka.navigation.AuthScreens
import com.maestro.duka.ui.core.PagerIndicator
import com.maestro.duka.ui.theme.DukaTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WelcomeScreen(
    navController: NavHostController,
    event: ((OnBoardingEvent) -> Unit)?
){


    val page = listOf(
        Page.FirstPage,
        Page.SecondPage,
        Page.ThirdPage,
        Page.FourthPage
    )

    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        page.size
    }
    Column (modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        HorizontalPager(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.Top,
            state = pagerState
        ) {
            when(page[it]){
                Page.FirstPage -> WelcometoApp()
                Page.FourthPage -> PagerScreens(
                    image = R.drawable.buy,
                    title = R.string.discount,

                )
                Page.SecondPage -> PagerScreens(image = R.drawable.buys,
                    title = R.string.discount1,
                  )
                Page.ThirdPage -> PagerScreens(    image = R.drawable.buyss,
                    title = R.string.discount2,
                   )
            }

        }

        PagerIndicator(pageCount = pagerState.pageCount, currentPage = pagerState.currentPage)
        RoundedButton(onButtonClicked = {
            event?.invoke(OnBoardingEvent.SaveAppEntry)
            navController.navigate(AuthScreens.LoginScreen.route)
        },
            contentColor = Color.White,
            backgroundcolor =Color.Black,
            modifier = Modifier.navigationBarsPadding(),
            pagerState = pagerState
        )
    }

}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DukaTheme {
        WelcomeScreen(rememberNavController(),null)
    }
}
