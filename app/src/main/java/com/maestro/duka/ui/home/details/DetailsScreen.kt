package com.maestro.duka.ui.home.details

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.maestro.duka.R
import com.maestro.duka.data.local.CartItems
import com.maestro.duka.data.remote.dto.ProductsResponseItem
import com.maestro.duka.data.remote.dto.Rating
import com.maestro.duka.di.DukaApplication.Companion.context
import com.maestro.duka.ui.core.ProductSizeCard
import com.maestro.duka.ui.theme.Accent
import com.maestro.duka.ui.theme.Blueback
import com.maestro.duka.ui.theme.DarkText
import com.maestro.duka.ui.theme.DukaTheme
import com.maestro.duka.ui.theme.Favorite
import com.maestro.duka.ui.theme.IconTint
import com.maestro.duka.ui.theme.LightText
import com.maestro.duka.ui.theme.MediumText
import com.maestro.duka.ui.theme.Shadow
import com.maestro.duka.ui.theme.Star
import com.maestro.duka.ui.theme.fonts
import com.maestro.duka.utils.Constants.DURATION
import com.maestro.duka.utils.Constants.SIZE_38
import kotlinx.coroutines.delay

@Composable
fun DetailsScreen(
   productItem: ProductsResponseItem,
   event: (DetailsEvent) ->Unit,
   navigateUp:() ->Unit
){
    var xOffset by remember { mutableStateOf(800.dp) }
    var yOffset by remember { mutableStateOf(800.dp) }
    var buttonScale by remember { mutableFloatStateOf(0f) }
    var iconScale by remember { mutableFloatStateOf(0f) }
    var sneakerScale by remember { mutableFloatStateOf(0.6f) }
    var sneakerRotate by remember { mutableFloatStateOf(0f) }
    var selectedSize by remember { mutableStateOf("Large") }

    val animatedXOffset = animateDpAsState(
        targetValue = xOffset,
        label = "",
        animationSpec = tween(durationMillis = DURATION, easing = FastOutLinearInEasing)
    )

    val animatedYOffset = animateDpAsState(
        targetValue = yOffset,
        label = "",
        animationSpec = tween(durationMillis = DURATION, easing = FastOutLinearInEasing)
    )

    val animatedButtonScale = animateFloatAsState(
        targetValue = buttonScale,
        label = "",
        animationSpec = tween(easing = FastOutLinearInEasing)
    )

    val animatedIconScale = animateFloatAsState(
        targetValue = iconScale,
        label = "",
        animationSpec = tween(easing = FastOutLinearInEasing)
    )

    val animatedSneakerScale = animateFloatAsState(
        targetValue = sneakerScale,
        label = "",
        animationSpec = tween(durationMillis = DURATION, easing = FastOutLinearInEasing)
    )

    val animatedSneakerRotate = animateFloatAsState(
        targetValue = sneakerRotate,
        label = "",
        animationSpec = tween(durationMillis = DURATION, easing = FastOutLinearInEasing)
    )

    LaunchedEffect(true) {
        delay(150)
        xOffset = 140.dp
        yOffset = (-130).dp
        sneakerScale = 1f
        sneakerRotate = 0f
        delay(400)
        iconScale = 1f
        delay(100)
        buttonScale = 1f
    }



    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)){

        Column (modifier = Modifier
            .verticalScroll(rememberScrollState())){
            TopNavigationBar(productItem,event,navigateUp,selectedSize)
            AsyncImage(modifier = Modifier
                .scale(animatedSneakerScale.value)
                .rotate(animatedSneakerRotate.value)
                .padding(end = 48.dp)
                .padding(top = 30.dp)
                .size(300.dp),
                model = ImageRequest.Builder(context).data(productItem.image).build(),
                contentDescription =null )

            Row ( modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 22.dp)
                .padding(top = 48.dp),
                horizontalArrangement = Arrangement.SpaceBetween){

                Column (modifier = Modifier.padding(end = 25.dp)
                    .fillMaxWidth(0.7f)) {
                    Text(
                        text = productItem.category,
                        color = LightText,
                        fontSize = 10.sp,
                        fontFamily = fonts,
                        style = TextStyle(
                            platformStyle = PlatformTextStyle(
                                includeFontPadding = false
                            )
                        )
                    )

                    Text(
                        modifier = Modifier
                            .padding(top = 2.dp),
                        text = productItem.title,
                        color = DarkText,
                        fontFamily = fonts,
                        fontSize = 22.sp,
                        style = TextStyle(
                            platformStyle = PlatformTextStyle(
                                includeFontPadding = false
                            )
                        )
                    )

                    StarView(productItem)
                }

                Text(
                    modifier = Modifier
                        .padding(top = 4.dp),
                    text = productItem.price.toString(),
                    color = Accent,
                    fontFamily = fonts,
                    fontSize = 30.sp,
                    style = TextStyle(
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        )
                    )
                )

            }



            SizesView(selectedSize)


            Text(
                modifier = Modifier
                    .padding(horizontal = 22.dp)
                    .padding(top = 24.dp),
                text = productItem.description,
                color = LightText,
                lineHeight = 20.sp,
                fontSize = 12.sp,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Justify,
                style = TextStyle(
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    )
                )
            )
            Box(modifier = Modifier
                .fillMaxHeight(0.8f)
                .fillMaxWidth(), contentAlignment = Alignment.BottomCenter) {

                BottomPart(animatedIconScale, animatedButtonScale,productItem,event,selectedSize)
            }

        }
    }

}

@Preview(showBackground = true)
@Composable
private fun DetailsPreview(){

    DukaTheme {
DetailsScreen(productItem = ProductsResponseItem(
    category = "Food",
    description = "\"Lorem Ipsum is simply dummy text of the printing and typesetting\"",
    id = 1,
    image = "https://media.istockphoto.com/id/1141208525/photo/yellow-open-backpack.jpg?s=612x612&w=0&k=20&c=k0NOqN9FnIGdkaUNx6-fMIBG2IfWwLT_AbDVefqJT_0=",
    price = 10.0,
    title = "Hellv ofv ",
    rating = Rating(
        count = 10,
        rate = 10.0
    )
), event = {}) {

}
    }

}




@Composable
private fun BottomPart(
    animatedIconScale: State<Float>,
    animatedButtonScale: State<Float>,
    productItem: ProductsResponseItem,
    event: (DetailsEvent) -> Unit,
    selectedSize: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .padding(bottom = 24.dp)
    ) {
        IconButton(
            modifier = Modifier
                .scale(animatedIconScale.value),
            onClick = {

            }
        ) {
            val isFavorite = false
            Icon(
                imageVector = if (isFavorite) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                contentDescription = "Favorite Icon",
                tint = if (isFavorite) Favorite else IconTint
            )
        }

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
                event(DetailsEvent.AddToCart(cartItems = CartItems(
                    cartId = productItem.id,
                    selectedColor = Blueback.hashCode(),
                    selectedSize = selectedSize,
                    quantity = null,
                    category = productItem.category,
                    description = productItem.description,
                image = productItem.image,
               price = productItem.price,
                 rating = productItem.rating,
                title= productItem.title
                )))
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
}

@Composable
private fun SizesView(selectedSize: String) {
    var selectedSize1 = selectedSize
    Text(
        modifier = Modifier
            .padding(horizontal = 22.dp)
            .padding(top = 8.dp),
        text = "Size",
        color = MediumText,
        fontSize = 10.sp,
        fontFamily = fonts,
        fontWeight = FontWeight.Bold,
        style = TextStyle(
            platformStyle = PlatformTextStyle(
                includeFontPadding = false
            )
        )
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 6.dp)
            .padding(horizontal = 22.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        ProductSizeCard(
            size = SIZE_38,
            isSelected = selectedSize1 == SIZE_38
        ) {
            selectedSize1 = SIZE_38
        }
        ProductSizeCard(
            size = SIZE_38,
            isSelected = selectedSize1 == SIZE_38
        ) {
            selectedSize1 = SIZE_38
        }
        ProductSizeCard(
            size = SIZE_38,
            isSelected = selectedSize1 == SIZE_38
        ) {
            selectedSize1 = SIZE_38
        }

    }
}

@Composable
private fun StarView(productItem: ProductsResponseItem) {
    Row(
        modifier = Modifier
            .padding(top = 2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .size(18.dp),
            imageVector = Icons.Outlined.Star,
            contentDescription = "Rating Icon",
            tint = Star
        )

        Text(
            modifier = Modifier
                .padding(start = 4.dp),
            textAlign = TextAlign.Center,
            text = productItem.rating.rate.toString(),
            fontFamily = fonts,
            color = MediumText,
            fontSize = 12.sp,
            style = TextStyle(
                platformStyle = PlatformTextStyle(
                    includeFontPadding = false
                )
            )
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun TopNavigationBar(
    productItem: ProductsResponseItem,
    event: (DetailsEvent) -> Unit,
    navigateUp: () -> Unit,
    selectedSize: String
) {
    TopAppBar(modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.Transparent,
            actionIconContentColor = Color.Black,
            navigationIconContentColor = Color.Black
        ),
        title = { },
        navigationIcon = {
            IconButton(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .padding(top = 8.dp)
                    .shadow(
                        elevation = 24.dp,
                        spotColor = Shadow,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .background(color = Color.White, shape = RoundedCornerShape(12.dp))
                    .size(36.dp),
                onClick = {
                    navigateUp.invoke()
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowLeft,
                    contentDescription = "Back Icon",
                    tint = IconTint
                )
            }
        },
        actions = {
            IconButton(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .padding(top = 8.dp)
                    .shadow(
                        elevation = 24.dp,
                        spotColor = Shadow,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .background(color = Color.White, shape = RoundedCornerShape(12.dp))
                    .size(36.dp),
                onClick = {
                    event(DetailsEvent.BookMarkClicked(productItem))
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "BookMark Icon",
                    tint = IconTint
                )
            }
            IconButton(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .padding(top = 8.dp)
                    .padding(end = 16.dp)
                    .shadow(
                        elevation = 24.dp,
                        spotColor = Shadow,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .background(color = Color.White, shape = RoundedCornerShape(12.dp))
                    .size(36.dp),
                onClick = {
                    //share
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Share,
                    contentDescription = "Share Icon",
                    tint = IconTint
                )
            }
        }
    )
}