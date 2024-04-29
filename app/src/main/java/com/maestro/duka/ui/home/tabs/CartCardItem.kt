package com.maestro.duka.ui.home.tabs


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.maestro.duka.R
import com.maestro.duka.data.local.CartItems
import com.maestro.duka.data.remote.dto.ProductsResponseItem
import com.maestro.duka.data.remote.dto.Rating
import com.maestro.duka.di.DukaApplication
import com.maestro.duka.di.DukaApplication.Companion.context
import com.maestro.duka.ui.core.QuantitySelector
import com.maestro.duka.ui.theme.Accent
import com.maestro.duka.ui.theme.Blueback
import com.maestro.duka.ui.theme.DarkText
import com.maestro.duka.ui.theme.DukaTheme
import com.maestro.duka.ui.theme.ExtraLightText
import com.maestro.duka.ui.theme.RegularText
import com.maestro.duka.ui.theme.fonts

@Composable
fun CardCartItem(
    modifier: Modifier,
    productItem: CartItems,
    navigateToDetails: (ProductsResponseItem) -> Unit

){

    val interactionSource = remember {
        MutableInteractionSource()
    }


    Box( modifier = modifier
        .clickable(
            onClick = {
            },
            interactionSource = interactionSource,
            indication = rememberRipple(bounded = true)
        )
        .height(IntrinsicSize.Min)
        .padding(8.dp)
        .fillMaxWidth()
        .shadow(elevation = 2.dp, shape = RoundedCornerShape(16.dp))
        .background(Color.White)

    ) {
        Row {

            AsyncImage(modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(horizontal = 22.dp, vertical = 2.dp)
                .size(90.dp),
                model = ImageRequest.Builder(context).data(productItem.image).build(),
                contentDescription =null )


            Column (modifier = Modifier.align(Alignment.CenterVertically),
                verticalArrangement = Arrangement.Center)
            {
                Text(
                    modifier = Modifier
                        .align(Alignment.Start),
                    text = productItem.title,
                    color = RegularText,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = fonts,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        )
                    )
                )

                Text(
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(top = 2.dp)
                        .padding(end = 18.dp),
                    text = productItem.description,
                    color = ExtraLightText,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = fonts,
                    fontSize = 10.sp,
                    style = TextStyle(
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        )
                    )
                )
                Row {
                    Text(
                        modifier = Modifier
                            .padding(top = 14.dp)
                            .padding(end = 18.dp)
                            .padding(bottom = 8.dp),
                        text = productItem.price.toString(),
                        color = DarkText,
                        fontFamily = fonts,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(
                            platformStyle = PlatformTextStyle(
                                includeFontPadding = false
                            )
                        )
                    )

                    QuantitySelector(
                        count = 1,
                        decreaseItemCount = { },
                        increaseItemCount = {  },
                        modifier = Modifier)



                }



            }

        }
    }
}




@Composable
fun SingleCartItem(){
    val interactionSource = remember {
        MutableInteractionSource()
    }
    Box( modifier = Modifier
        .clickable(
            onClick = {
            },
            interactionSource = interactionSource,
            indication = rememberRipple(bounded = true)
        )
        .height(IntrinsicSize.Min)
        .padding(8.dp)
        .fillMaxWidth()
        .shadow(elevation = 2.dp, shape = RoundedCornerShape(16.dp))
        .background(Color.White)

    ) {
        Row {
            Image(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(horizontal = 22.dp, vertical = 2.dp)
                    .size(90.dp),
                painter = painterResource(id = R.drawable.item_3),
                contentDescription = null
            )

            Column (modifier = Modifier.align(Alignment.CenterVertically),
                verticalArrangement = Arrangement.Center)
            {
                Text(
                    modifier = Modifier
                        .align(Alignment.Start),
                    text = stringResource(id = R.string.producttitle),
                    color = RegularText,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = fonts,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        )
                    )
                )

                Text(
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(top = 2.dp)
                        .padding(end = 18.dp),
                    text = stringResource(id = R.string.productdesc),
                    color = ExtraLightText,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = fonts,
                    fontSize = 10.sp,
                    style = TextStyle(
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        )
                    )
                )
                Row {
                    Text(
                        modifier = Modifier
                            .padding(top = 14.dp)
                            .padding(end = 18.dp)
                            .padding(bottom = 8.dp),
                        text = "200.0",
                        color = DarkText,
                        fontFamily = fonts,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(
                            platformStyle = PlatformTextStyle(
                                includeFontPadding = false
                            )
                        )
                    )

                 QuantitySelector(
                     count = 1,
                     decreaseItemCount = { },
                     increaseItemCount = {  },
                     modifier = Modifier

                 )


                }



            }

        }
    }
}


@Composable
@Preview
fun PreviewProductsItem(){
    DukaTheme {
        SingleCartItem()
    }
}










