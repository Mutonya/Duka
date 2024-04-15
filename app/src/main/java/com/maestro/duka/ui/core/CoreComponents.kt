package com.maestro.duka.ui.core

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maestro.duka.R

@Composable

fun AuthButtonComponent(
    value:String,onButtonClicked:() ->Unit, isEnabled:Boolean = true,contentColor:Color,backgroundcolor:Color,modifier: Modifier){

    Button(onClick = { onButtonClicked.invoke() }, modifier = Modifier
        .fillMaxWidth()
        .heightIn(48.dp),
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
        Text(text = value)
    }

}
@Composable
fun EmailComponent(label: String, onTextSelected: (String) -> Unit,modifier: Modifier){
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
        modifier = Modifier.fillMaxWidth(),
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
    
    AnimatedVisibility(visible =  pagerState.currentPage ==2) {
        OutlinedButton(
            onClick = {
                onButtonClicked.invoke()
                      },
            modifier= Modifier.size(50.dp),
            shape = CircleShape,
            border= BorderStroke(5.dp, Color.Black),
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor =  contentColor,containerColor = backgroundcolor)
        ) {

            Icon(painterResource(id = R.drawable.baseline_keyboard_arrow_right_24) ,contentDescription = "content description", tint=Color.White)


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
