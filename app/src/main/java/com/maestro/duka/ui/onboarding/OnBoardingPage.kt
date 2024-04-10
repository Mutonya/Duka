package com.maestro.duka.ui.onboarding

import androidx.annotation.DrawableRes
import androidx.compose.ui.res.stringResource
import com.maestro.duka.R

sealed class OnBoardingPage (
    @DrawableRes val image:Int,
    val title:Int,
    val title2:Int,
    val description:Int
){

    data object FirstPage:OnBoardingPage(
        image = R.drawable.model1,
        title = R.string.discount,
        title2 = R.string.newarrival,
        description =R.string.publishmore
    )
    data object SecondPage:OnBoardingPage(
        image = R.drawable.model2,
        title = R.string.discount1,
        title2 = R.string.newarrivals,
        description =R.string.publishmore
    )
    data object ThirdPage:OnBoardingPage(
        image = R.drawable.model3,
        title = R.string.discount2,
        title2 = R.string.newarrival3,
        description =R.string.publishmore
    )
}