package com.maestro.duka.ui.core

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maestro.duka.R
import com.maestro.duka.ui.theme.Accent
import com.maestro.duka.ui.theme.Border
import com.maestro.duka.ui.theme.DukaTheme
import com.maestro.duka.ui.theme.RegularText
import com.maestro.duka.ui.theme.Tint
import com.maestro.duka.ui.theme.fonts

@Composable

fun AuthButtonComponent(
    value:String,onButtonClicked:() ->Unit, isEnabled:Boolean = true,contentColor:Color,backgroundcolor:Color,modifier: Modifier){

    Button(onClick = { onButtonClicked.invoke() }, modifier = Modifier
        .fillMaxWidth()
        .height(48.dp),
        contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 8.dp
        ),

         enabled = isEnabled,
        colors =ButtonDefaults.buttonColors(
            containerColor = backgroundcolor,
            contentColor = contentColor
        ),
        border = ButtonDefaults.outlinedButtonBorder

    ) {

        Text(text = value,fontFamily = fonts)
    }

}
@Composable
fun EmailComponent(label: String, onTextSelected: (String) -> Unit,modifier: Modifier){
    val username = remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = username.value,
        onValueChange = { username.value = it
            onTextSelected(it)
        },
        label = { Text(text = label) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        trailingIcon = {
            if (username.value.isNotBlank())
                IconButton(onClick = { username.value = "" }) {
                    Icon(imageVector = Icons.Filled.Clear, contentDescription = "")
                } else Icon(imageVector = Icons.Outlined.Email,contentDescription = "Email")
        }
    )

}
@Composable
fun PasswordComponent(label: String, onTextSelected: (String) -> Unit,modifier: Modifier){
    val password = remember {
        mutableStateOf("")
    }
    var isPasswordVisible by remember {
        mutableStateOf(false)
    }
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = password.value,
        onValueChange = { password.value = it
            onTextSelected(it)},
        label = { Text(text = label) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                Icon(
                    painter = if (isPasswordVisible) painterResource(id = R.drawable.baseline_lock_24) else painterResource(id = R.drawable.baseline_remove_red_eye_24),
                    contentDescription = "Password Toggle"
                )
            }
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable


fun RoundedButton(
                    onButtonClicked:() ->Unit,
                    contentColor:Color,
                    backgroundcolor:Color,modifier: Modifier,pagerState: PagerState){
    
    AnimatedVisibility(visible =  pagerState.currentPage >=1) {
        OutlinedButton(
            onClick = {
                onButtonClicked.invoke()
                      },
            modifier= modifier
                .wrapContentHeight()
                .wrapContentWidth()
                .padding(8.dp),
            shape = RoundedCornerShape(32.dp),
            border= BorderStroke(5.dp, Color.Black),
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor =  contentColor,containerColor = backgroundcolor)
        ) {

            val title = if (pagerState.currentPage in 1..2){
                "Next"
            }else{
                "Get Started"
            }


            Text(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
                fontSize = 16.sp,
                fontFamily = fonts,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                color = contentColor,
                text = title)
            // Adding an Icon "Add" inside the Button
        }
    }



}

@Composable
fun UserName(label: String, onTextSelected: (String) -> Unit,modifier: Modifier){
    val username = remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = username.value,
        onValueChange = { username.value = it
            onTextSelected(it)
        },
        label = { Text(text = label) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        trailingIcon = {
            if (username.value.isNotBlank())
                IconButton(onClick = { username.value = "" }) {
                    Icon(imageVector = Icons.Filled.Clear, contentDescription = "clearusername")
                } else Icon(imageVector = Icons.Outlined.Person,contentDescription = "Username")
        }
    )

}
@Composable
fun CheckBoxComponent(value: String,onChecked:(Boolean) ->Unit){
    val checkedState = remember {
        mutableStateOf(true)
    }
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically
    ){

        Checkbox(checked = checkedState.value, onCheckedChange ={
            checkedState.value = checkedState.value
            onChecked.invoke(it)
        } )
       Text(
           modifier = Modifier
               .fillMaxWidth()
               .padding(top = 8.dp, bottom = 8.dp),
           text = value,
           fontSize = 14.sp,
           fontWeight = FontWeight.Normal,
           textAlign = TextAlign.Start,
           color = Color.Gray

       )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar (modifier: Modifier,
               text:String,
               readOnly:Boolean,
               onclick:(()->Unit)? = null,
               onValueChange:(String)->Unit,
               onSearch:()->Unit
){
    val interactionSource = remember {
        MutableInteractionSource()
    }

    val isClicked = interactionSource.collectIsPressedAsState().value
    LaunchedEffect(key1 = isClicked ){
        if (isClicked){
            onclick?.invoke()
        }
    }

    Box(modifier = modifier){
        TextField(modifier = Modifier
            .fillMaxWidth()
            .searchMode()
            ,value = text, onValueChange =onValueChange,readOnly= readOnly,
            leadingIcon = {
                Icon(painter = painterResource(id = R.drawable.search_icon),
                    contentDescription =null, modifier = Modifier.size(20.dp),
                    tint = Tint
                )
            },
            placeholder = {
                Text(text = "Search", style = MaterialTheme.typography.bodySmall, color = Color.Black )
            },
            shape = MaterialTheme.shapes.medium,
            colors = TextFieldDefaults.textFieldColors(

                containerColor = Color.White,
                cursorColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent

            ), singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearch()
                }
            ),
            textStyle = MaterialTheme.typography.bodySmall,
            interactionSource = interactionSource
        )
    }
}

fun Modifier.searchMode() = composed {
    if (isSystemInDarkTheme()){
        border(
            width = 1.dp,
            color = Color.Black,
            shape = MaterialTheme.shapes.medium
        )
    }else{
        this
    }
}
@Composable
fun PagerIndicator(pageCount:Int,currentPage:Int){
    Row(
        Modifier

            .padding(bottom = 8.dp, end = 8.dp)
            .navigationBarsPadding(),
        horizontalArrangement = Arrangement.Center,

        ) {
        repeat(pageCount) { iteration ->
            val color =
                if (currentPage == iteration) Color.Black else Color.Gray
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(8.dp)
            )
        }

    }
}

@Composable
fun DividerTextComponent(){
    Row (
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically

    ){
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            color = Color.Gray,
            thickness = 1.dp)
        Text(modifier = Modifier.padding(8.dp)
            ,text = stringResource(id = R.string.or), fontSize = 16.sp, color = Color.Black)
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            color = Color.Gray,
            thickness = 1.dp)

    }
}

@Composable

fun ButtonWithLeadingIcon(
    value:String,onButtonClicked:() ->Unit, isEnabled:Boolean = true,modifier: Modifier,icon:Int){

    OutlinedButton(onClick = { onButtonClicked.invoke() }, modifier = Modifier
        .height(40.dp)
        .wrapContentHeight(),
        contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 8.dp
        ),

        enabled = isEnabled,
        border = ButtonDefaults.outlinedButtonBorder

    ) {
        Icon(modifier = Modifier.padding(end = 4.dp), painter = painterResource(id = icon), contentDescription = null, tint = Color.Unspecified)
        Text(text = value)
    }

}
@SuppressLint("QueryPermissionsNeeded")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(


    navigateUp: () -> Unit
) {

    TopAppBar(modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.Transparent,
            actionIconContentColor = Color.Black,
            navigationIconContentColor = Color.Black
        ),
        title = {  },
        navigationIcon = {
            IconButton(onClick = {
                navigateUp.invoke()
            }) {
                Icon(painter = painterResource(id = R.drawable.arrow),
                    contentDescription =null )

            }
        }
    )
}

@Composable
@Preview
fun PreTopBar(){
   DukaTheme {
       ButtonWithLeadingIcon(
           value = "Continue with facebook",
           onButtonClicked = {  },
           modifier = Modifier,
           icon = R.drawable.facebook
       )
   }
}

@Composable
fun OutLinedButton(painterResource:Int, text: Int){
    OutlinedButton(
        onClick = { },
        border = BorderStroke(1.dp, Color.Red),
        modifier = Modifier.padding(8.dp)
    ) {
        Icon(modifier = Modifier.padding(end = 4.dp), tint = Color.Transparent, painter = painterResource(id = painterResource), contentDescription = null)

        Text(text = stringResource(id = text))
    }
}
@Composable
fun ProductSizeCard(
    modifier: Modifier = Modifier,
    size: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if (isSelected) Accent else Color.White
    val textColor = if (isSelected) Color.White else RegularText
    val border = if (isSelected) 0.dp else 0.8.dp

    Text(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .border(width = border, color = Border, shape = RoundedCornerShape(12.dp))
            .background(backgroundColor)
            .clickable(
                onClick = onClick
            )
            .padding(12.dp),
        text = size,
        fontSize = 12.sp,
        color = textColor,
        style = TextStyle(
            platformStyle = PlatformTextStyle(
                includeFontPadding = false
            )
        )
    )
}