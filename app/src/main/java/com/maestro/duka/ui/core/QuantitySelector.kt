package com.maestro.duka.ui.core

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maestro.duka.R
import com.maestro.duka.ui.theme.DarkText
import com.maestro.duka.ui.theme.DukaTheme
import com.maestro.duka.ui.theme.fonts


@Composable
@Preview
fun PreviewQuantity(){
    DukaTheme {
        QuantitySelector(
            count =1 ,
            decreaseItemCount = {  },
            increaseItemCount = {  })
    }
}

@Composable
fun QuantitySelector(
    count: Int,
    decreaseItemCount: () -> Unit,
    increaseItemCount: () -> Unit,
    modifier: Modifier = Modifier
){

    Row(modifier = Modifier.fillMaxWidth().padding(top = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center) {
       Text(text = stringResource(id = R.string.qty),
           color = DarkText,
           fontFamily = fonts,
           fontSize = 14.sp,
           textAlign = TextAlign.Center,
           fontWeight = FontWeight.Bold,
           style = TextStyle(
               platformStyle = PlatformTextStyle(
                   includeFontPadding = false
               )
           ),modifier = Modifier
               .padding(end = 8.dp)
               .align(Alignment.CenterVertically)

           )

        CartButtonCircle(imageVector = R.drawable.minus,
            onClick = { decreaseItemCount.invoke()}, contentDescription =null,
            modifier = modifier.align(
                Alignment.CenterVertically
            ) )

        Crossfade(
            targetState = count,
            modifier = Modifier
                .align(Alignment.CenterVertically),
            label = ""
        ) {
            Text(text = "$it",
                color = DarkText,
                fontFamily = fonts,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                style = TextStyle(
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    )
                ),modifier = Modifier
                    .padding(end = 8.dp)
                    .align(Alignment.CenterVertically).widthIn(24.dp)

            )
        }
        CartButtonCircle(imageVector = R.drawable.plus,
            onClick = { increaseItemCount.invoke()}, contentDescription =null,
            modifier = Modifier.align(
                Alignment.CenterVertically
            ) )
    }


}