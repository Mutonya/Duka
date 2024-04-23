package com.maestro.duka.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.maestro.duka.ui.auth.AuthScreen

@Composable
fun OnboardingScreen() {
    val pages = listOf(
         AuthScreen(rememberNavController()) ,
         OnboardingPage(pageNumber = 2) ,
        OnboardingPage(pageNumber = 3)
    )

    HorizontalPager(pages = pages)
}



@Composable
fun HorizontalPager(pages: List<Unit>) {
    var currentPage by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            pages[currentPage]
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pages.size) { index ->
                Dot(
                    isSelected = index == currentPage,
                    onClick = { currentPage = index }
                )
            }
        }
    }
}

@Composable
fun Dot(isSelected: Boolean, onClick: () -> Unit) {
    val color = if (isSelected) Color.Blue else Color.Gray
    Box(
        modifier = Modifier
            .size(12.dp)
            .padding(horizontal = 4.dp)
            .background(color = color, shape = RoundedCornerShape(50))
            .clickable(onClick = onClick)
    )
}

@Composable
fun OnboardingPage(pageNumber: Int) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Page $pageNumber", fontSize = 24.sp)
    }
}

@Composable
fun FirstPage() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "First Page", fontSize = 24.sp)
    }
}

@Preview
@Composable
fun PreviewOnboardingScreen() {
    OnboardingScreen()
}
