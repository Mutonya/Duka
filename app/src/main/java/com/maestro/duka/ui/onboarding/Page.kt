package com.maestro.duka.ui.onboarding

import com.maestro.duka.R

sealed class Page {
     data object FirstPage:Page()
    data object SecondPage:Page()
    data object ThirdPage:Page()
    data object FourthPage:Page()
}