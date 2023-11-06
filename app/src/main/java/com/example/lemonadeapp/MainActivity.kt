package com.example.lemonadeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonadeapp.ui.theme.LemonadeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeAppTheme {
                LemonAppScreen()
            }
        }
    }
}

@Composable
fun LemonAppScreen(){

    var clickNumber by remember { mutableStateOf(0) }
    var steps by remember { mutableStateOf(0) }
    var image:Painter
    var contentDescription:String
    var instruction:String
    var squeezeNumber = (1..10).random()

    when(steps){
        0->{
            image = painterResource(id = R.drawable.lemon_tree)
            contentDescription = stringResource(id = R.string.lemon_tree_content_description)
            instruction = stringResource(id = R.string.instruction_1)
        }
        1->{
            image = painterResource(id = R.drawable.lemon_squeeze)
            contentDescription = stringResource(id = R.string.lemon_content_description)
            instruction = stringResource(id = R.string.instruction_2)
        }
        2->{
            image = painterResource(id = R.drawable.lemon_drink)
            contentDescription = stringResource(id = R.string.glass_of_lemonade_content_description)
            instruction = stringResource(id = R.string.instruction_3)
        }
        else->{
            image = painterResource(id = R.drawable.lemon_restart)
            contentDescription = stringResource(id = R.string.empty_glass_content_description)
            instruction = stringResource(id = R.string.instruction_4)
        }
    }


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .background(Color.Yellow)
                .fillMaxWidth()
                .fillMaxHeight(0.1f)
        ){
            Text(
                text = "Lemonade",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.padding(100.dp))
        Box(
            modifier = Modifier
                .size(width = 250.dp, height = 250.dp) // Set the size of the background
                .clip(RoundedCornerShape(40.dp))
                .background(Color(0xFFA6CF98))
                .align(CenterHorizontally)

        ) {
            Image(
                painter = image,
                contentDescription = contentDescription,
                modifier = Modifier
                    .align(Alignment.Center)
                    .clickable {
                        clickNumber++
                        if(steps == 0 && clickNumber==1){
                            steps = 1
                            clickNumber = 0
                        }else if(steps==1 && clickNumber==squeezeNumber){
                            steps = 2
                            clickNumber = 0
                        }else if(steps==2 && clickNumber==1){
                            steps = 3
                            clickNumber = 0
                        }else if(steps==3 && clickNumber==1){
                            steps = 0
                            clickNumber = 0
                        }
                    }
                )
        }
        Text(
            text = instruction,
            Modifier.padding(40.dp)
            )

    }
}


@Preview(showBackground = true)
@Composable
fun LemonadeAppPreview() {
    LemonadeAppTheme {
        LemonAppScreen()
    }
}