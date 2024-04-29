package com.maestro.duka.ui.home.tabs.cart

import android.util.Log
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maestro.duka.data.local.CartItems
import com.maestro.duka.ui.home.details.DetailsEvent
import com.maestro.duka.ui.home.events.CartState
import com.maestro.duka.ui.home.tabs.CardCartItem
import com.maestro.duka.ui.theme.Accent
import com.maestro.duka.ui.theme.Blueback
import com.maestro.duka.ui.theme.DukaTheme
import com.maestro.duka.ui.theme.fonts
import com.stripe.android.PaymentConfiguration
import com.stripe.android.paymentsheet.PaymentSheet
import com.stripe.android.paymentsheet.PaymentSheetResult
import com.stripe.android.paymentsheet.rememberPaymentSheet

@Composable

fun CartScreen(state:CartState){
    val paymentSheet = rememberPaymentSheet(::onPaymentSheetResult)
    val context = LocalContext.current
    var customerConfig by remember { mutableStateOf<PaymentSheet.CustomerConfiguration?>(null) }
    var paymentIntentClientSecret by remember { mutableStateOf<String?>(null) }

    Log.e("State",state.toString())


    if (state.paymentInitiated){
        customerConfig = PaymentSheet.CustomerConfiguration(
            state.customer!!,
            state.ephemeralKey!!
        )
        paymentIntentClientSecret = state.paymentIntent
        val publishableKey = state.publishableKey
        if (publishableKey != null) {
            PaymentConfiguration.init(context, publishableKey)
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top) {


    val buttonScale by remember { mutableFloatStateOf(0f) }
    var iconScale by remember { mutableFloatStateOf(0f) }
    val animatedButtonScale = animateFloatAsState(
        targetValue = buttonScale,
        label = "",
        animationSpec = tween(easing = FastOutLinearInEasing)
    )


val startpayment = state.paymentInitiated
        if (startpayment){
            
        }
    LazyColumn (
        modifier = Modifier
            .weight(1f)
            .fillMaxWidth(),
        contentPadding = PaddingValues(vertical = 20.dp)
    ){
        items(state.cartItems.size){
            CardCartItem(modifier = Modifier,productItem = state.cartItems[it]) { product ->

            }
        }

    }

        Button( modifier = Modifier.align(Alignment.CenterHorizontally)
            .wrapContentWidth()
            .height(48.dp)
            .padding(start = 32.dp, end = 32.dp, bottom = 5.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Accent
            ),
            onClick = {
                val currentConfig = customerConfig
                val currentClientSecret = paymentIntentClientSecret

                Log.e("Config","customer ${currentConfig.toString()} and currentSectret $currentClientSecret")

                if (currentConfig != null && currentClientSecret != null) {
                    presentPaymentSheet(paymentSheet, currentConfig, currentClientSecret)
                }
            }
        ) {
            Icon(
                imageVector = Icons.Rounded.ShoppingCart,
                contentDescription = null,
                tint = Color.White
            )
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
            Text("Proceed to Check Out", fontFamily = fonts)
        }
    }



}

fun presentPaymentSheet(
    paymentSheet: PaymentSheet,
    currentConfig: PaymentSheet.CustomerConfiguration,
    currentClientSecret: String
) {
    paymentSheet.presentWithPaymentIntent(
        currentClientSecret,
        PaymentSheet.Configuration(
            merchantDisplayName = "George Mutonya",
            customer = currentConfig,
            // Set `allowsDelayedPaymentMethods` to true if your business handles
            // delayed notification payment methods like US bank accounts.
            allowsDelayedPaymentMethods = true
        )
    )
}

private fun   onPaymentSheetResult(paymentSheetResult: PaymentSheetResult) {
    when(paymentSheetResult) {
        is PaymentSheetResult.Canceled -> {


            print("Canceled")
        }
        is PaymentSheetResult.Failed -> {
            print("Error: ${paymentSheetResult.error}")
            Log.e("error paying",paymentSheetResult.error.toString())
        }
        is PaymentSheetResult.Completed -> {
            // Display for example, an order confirmation screen
            print("Completed")
        }
    }
}

@Composable
@Preview
fun PreviewBottom(){
    DukaTheme {
        val buttonScale by remember { mutableFloatStateOf(0f) }
        val animatedButtonScale = animateFloatAsState(
            targetValue = buttonScale,
            label = "",
            animationSpec = tween(easing = FastOutLinearInEasing)
        )
        BottomButton(animatedButtonScale = animatedButtonScale)
    }
}

@Composable
private fun BottomButton(animatedButtonScale: State<Float>) {
    Button(
        modifier = Modifier
            .scale(animatedButtonScale.value)
            .fillMaxWidth()
            .height(48.dp)
            .padding(start = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Accent
        ),
        onClick = {

        }
    ) {
        Icon(
            imageVector = Icons.Rounded.ShoppingCart,
            contentDescription = null,
            tint = Color.White
        )
        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
        Text(text = "Add to cart")
    }
}

@Preview
@Composable
fun preiewCat(){
    //CartScreenDesign()
}