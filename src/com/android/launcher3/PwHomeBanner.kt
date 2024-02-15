package com.android.launcher3

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


fun setPwWidgetScreenInWorkspace(v: ComposeView) {
    v.setContent {
        MaterialTheme {
            PwWidgetScreen()
        }
    }

}

@Composable
fun PwWidgetScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Column(
            modifier = Modifier
                .background(
                    color = Color.Gray,
                    shape = RoundedCornerShape(40.dp),
                )
                .fillMaxWidth()
                .padding(10.dp),
        ) {
            Text(
                text = "10:57",
                modifier = Modifier.align(
                    Alignment.CenterHorizontally,
                ),
                fontSize = 20.sp,
            )
            Text(
                text = "wed 16 aug",
                modifier = Modifier.align(
                    Alignment.CenterHorizontally,
                ),
                fontSize = 15.sp,
            )
        }
        Spacer(modifier = Modifier.size(20.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(235.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Gray
                ),
                shape = RoundedCornerShape(40.dp),
            ) {
                Spacer(modifier = Modifier.size(5.dp))
                CalenderScreen()
                Spacer(modifier = Modifier.size(20.dp))
                Text(text = "a good quote ")

            }
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .align(BottomCenter)
            ) {
                Text(text = "Enter")


            }
        }
    }

}

@Composable
fun CalenderScreen() {
    Column(modifier = Modifier
        .background(
            color = Color.White,
            shape = RoundedCornerShape(40.dp),
        )
        .padding(20.dp)
        .fillMaxWidth(0.95f)) {
        Text(text = "My Space", color = Color.Blue, fontSize = 15.sp)
        Spacer(modifier = Modifier.size(5.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.ic_all_set),
                contentDescription = null,
                modifier = Modifier.size(10.dp),
                colorFilter = ColorFilter.tint(Color.Blue)
            )
            Spacer(modifier = Modifier.size(2.dp))
            Text(text = "Today I will study maths", fontSize = 15.sp)
        }
        Spacer(modifier = Modifier.size(10.dp))
        val dayList = listOf(
            CalenderDayViewData(
                day = "M",
                backgroundColor = R.color.green_200,
                textColor = R.color.white_50,
                isToday = false,
            ),
            CalenderDayViewData(
                day = "T",
                backgroundColor = R.color.green_200,
                textColor = R.color.white_50,
                isToday = false,
            ),
            CalenderDayViewData(
                day = "W",
                backgroundColor = R.color.white_50,
                textColor = R.color.black_800,
                isToday = true,
            ),
            CalenderDayViewData(
                day = "Th",
                backgroundColor = R.color.white_50,
                textColor = R.color.black_800,
                isToday = false,
            ),
            CalenderDayViewData(
                day = "F",
                backgroundColor = R.color.white_50,
                textColor = R.color.black_800,
                isToday = false,
            ),
            CalenderDayViewData(
                day = "Sa",
                backgroundColor = R.color.white_50,
                textColor = R.color.black_800,
                isToday = false,
            ),
            CalenderDayViewData(
                day = "Su",
                backgroundColor = R.color.white_50,
                textColor = R.color.black_800,
                isToday = false,
            ),
        )
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly) {
            CalenderDayView(data =dayList[0])
            CalenderDayView(data =dayList[1])
            CalenderDayView(data =dayList[2])
            CalenderDayView(data =dayList[3])
            CalenderDayView(data =dayList[4])
            CalenderDayView(data =dayList[5])
            CalenderDayView(data =dayList[6])
        }

    }
}

@Composable
fun CalenderDayView(data: CalenderDayViewData) {
    val textMeasurer = rememberTextMeasurer()
    val textLayoutResult = remember {
        textMeasurer.measure(data.day, style = TextStyle(
            fontSize = 16.sp))
    }
    Canvas(modifier = Modifier.size(40.dp)){
        val width = size.width
        val height = size.height
        drawCircle(color = Color(data.backgroundColor))
        this.drawText(textMeasurer = textMeasurer, text = data.day,
            style = TextStyle(
                fontSize = 16.sp),
            topLeft = Offset(width/2-textLayoutResult.size.width/2, height/2-textLayoutResult.size.height/2))
        if(data.isToday){
            drawCircle(color = Color.Cyan, radius = 4f, center = Offset(width/2, height+20f))
        }
    }
}

data class CalenderDayViewData(
    val day: String,
    val backgroundColor: Int,
    val textColor:Int,
    val isToday: Boolean
)

@Preview
@Composable
fun PWpreview() {
    PwWidgetScreen()
}
