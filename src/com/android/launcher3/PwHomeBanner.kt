package com.android.launcher3

import android.icu.text.DateFormat
import android.icu.text.DisplayContext
import android.util.Log
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Locale
import kotlin.random.Random
import kotlinx.coroutines.delay


data class CalenderDayViewData(
    val day: String,
    val backgroundColor: Long,
    val textColor: Long,
    val isToday: Boolean,
)

@Composable
fun PwWidgetScreen() {
    var currentTime by remember {
        mutableStateOf(System.currentTimeMillis())
    }
    val quoteStringList = listOf(
        "You must be the change you wish to see in the world. -Mahatma Gandhi",
        "Spread love everywhere you go. Let no one ever come to you without leaving happier. -Mother Teresa",
        "The only thing we have to fear is fear itself. -Franklin D. Roosevelt",
        "Darkness cannot drive out darkness: only light can do that. Hate cannot drive out hate: only love can do that. -Martin Luther King Jr.",
        "Do one thing every day that scares you. -Eleanor Roosevelt",
        "Well done is better than well said. -Benjamin Franklin",
        "The best and most beautiful things in the world cannot be seen or even touched - they must be felt with the heart. -Helen Keller",
        "It is during our darkest moments that we must focus to see the light. -Aristotle",
        "Do not go where the path may lead, go instead where there is no path and leave a trail. -Ralph Waldo Emerson",
        "Be yourself; everyone else is already taken. -Oscar Wilde"
    )

    var quoteString by remember {
        mutableStateOf(
            quoteStringList[0]
        )
    }
    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            currentTime = System.currentTimeMillis()

        }
    }
    LaunchedEffect(Unit) {
        while (true) {
            delay(6 * 1000)
            val rand = Random(System.currentTimeMillis()).nextInt(0, quoteStringList.size)
            quoteString = quoteStringList[rand]
        }
    }

    val timeText = formatDateTime("HH:mm:ss", currentTime)
    val dateText by remember {
        mutableStateOf(formatDateTime("EEE, dMMM", currentTime))
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Column(
            modifier = Modifier
                .background(
                    color = Color(backgroundColor),
                    shape = RoundedCornerShape(40.dp),
                )
                .fillMaxWidth()
                .padding(10.dp),
        ) {
            Text(
                text = timeText,
                modifier = Modifier.align(
                    Alignment.CenterHorizontally,
                ),
                fontSize = 20.sp,
            )
            Text(
                text = dateText,
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
                .wrapContentHeight(Alignment.CenterVertically, true)
                .padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 20.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(Alignment.CenterVertically, true),
                colors = CardDefaults.cardColors(
                    containerColor = Color(backgroundColor)
                ),
                shape = RoundedCornerShape(40.dp),
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(5.dp)
                ) {

                    CalenderScreen(dateText)
                    Log.i("sumitsss", " color change $dateText")
                    Text(
                        text = quoteString,
                        modifier = Modifier.padding(2.dp, 20.dp),
                        textAlign = TextAlign.Center
                    )
                }

            }
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .align(BottomCenter)
                    .height(40.dp)
                    .offset(0.dp, 20.dp)
            ) {
                Text(text = "Enter")


            }
        }
    }
}

@Composable
fun CalenderScreen(dateText: String) {
    val dayList = getCalenderDayViewDataList(dateText)
    Log.i("sumitt", "$dayList")
    Column(
        modifier = Modifier
            .background(
                color = Color.White,
                shape = RoundedCornerShape(40.dp),
            )
            .padding(20.dp)
    ) {
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
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CalenderDayView(data = dayList[0])
            CalenderDayView(data = dayList[1])
            CalenderDayView(data = dayList[2])
            CalenderDayView(data = dayList[3])
            CalenderDayView(data = dayList[4])
            CalenderDayView(data = dayList[5])
            CalenderDayView(data = dayList[6])
        }

    }
}

@Composable
fun CalenderDayView(data: CalenderDayViewData) {
    val textMeasurer = rememberTextMeasurer()
    val textLayoutResult = remember {
        textMeasurer.measure(
            text = data.day,
            style = TextStyle(
                fontSize = 16.sp,
                color = Color(data.textColor),
            )
        )
    }
    Canvas(modifier = Modifier.size(40.dp)) {
        val width = size.width
        val height = size.height
        drawCircle(color = Color(data.backgroundColor))
        this.drawText(
            textMeasurer = textMeasurer, text = data.day,
            style = TextStyle(
                fontSize = 16.sp,
                color = Color(data.textColor),
            ),
            topLeft = Offset(
                width / 2 - textLayoutResult.size.width / 2,
                height / 2 - textLayoutResult.size.height / 2,
            )
        )
        if (data.isToday) {
            drawCircle(
                color = Color(0xff6093ff),
                radius = 8f,
                center = Offset(width / 2, height + 20f)
            )
        }
    }
}

fun setPwWidgetScreenInWorkspace(v: ComposeView) {
    v.setContent {
        MaterialTheme {
            PwWidgetScreen()
        }
    }

}

val backgroundColor = 0xffe7f5ef

fun formatDateTime(format: String, time: Long): String {
    val formatter = DateFormat.getInstanceForSkeleton(format, Locale.getDefault()).apply {
        setContext(DisplayContext.CAPITALIZATION_FOR_STANDALONE)
    }
    return formatter.format(time)
}

fun getCalenderDayViewDataList(dateText: String): List<CalenderDayViewData> {
    val dayIndexMap =
        mapOf("Mon" to 0, "Tue" to 1, "Wed" to 2, "Thu" to 3, "Fri" to 4, "Sat" to 5, "Sun" to 6)
    val dayMap = mapOf(0 to "M", 1 to "T", 2 to "W", 3 to "Th", 4 to "F", 5 to "Sa", 6 to "Su")
    val todayIndex = dayIndexMap[dateText.substring(0..3)] ?: 4
    val textColor: (Int) -> Long = {
        if (it >= todayIndex) {
            0xff000000
        } else {
            0xffffffff
        }
    }
    val backgroundColor: (Int) -> Long = {
        if (it >= todayIndex) {
            0xfff8f8f8
        } else {
            0xff93bea0
        }
    }

    val list = mutableListOf<CalenderDayViewData>()
    for (i in 0..6) {
        list.add(
            CalenderDayViewData(
                day = dayMap[i] ?: "M",
                isToday = todayIndex == i,
                textColor = textColor(i),
                backgroundColor = backgroundColor(i),
            )
        )
    }
    return list

}

@Preview
@Composable
fun PWpreview() {
    PwWidgetScreen()
}
