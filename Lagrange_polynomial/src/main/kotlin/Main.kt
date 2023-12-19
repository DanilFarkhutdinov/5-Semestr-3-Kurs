import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

import drawing.*
import math.*

@OptIn(ExperimentalTextApi::class, ExperimentalComposeUiApi::class)
@Composable
@Preview
fun App() {
    var xMin by remember { mutableStateOf(-10) }
    var xMax by remember { mutableStateOf( 10) }
    var yMinMax by remember{mutableStateOf(0f)}

    var yMin = (-10 + 10 * yMinMax).toInt()
    var yMax = (10 + 10 * yMinMax).toInt()
    var dist_y = yMax - yMin
    var dist_x = xMax - xMin
    var points by remember { mutableStateOf(mutableMapOf<Float, Float>()) }
    val textMeasurer = rememberTextMeasurer()
    var position by remember { mutableStateOf(Offset(0f, 0f)) }



    Canvas(modifier = Modifier.fillMaxSize().onPointerEvent(PointerEventType.Press)
    {
        position  =it.changes.first().position
        points[x_to_the_Cartesian_system(position.x, this.size.width, xMin, xMax)] = y_to_the_Cartesian_system(position.y, this.size.height, yMin, yMax)
    }.clickable(onClick = { }),
        onDraw = {
            draw_x_axis(this, xMin, xMax, yMinMax, textMeasurer)
            draw_y_axis(this, xMin, xMax, yMin, yMax, textMeasurer)
            draw_point(this, points, xMin, xMax, yMin, yMax)
            if(points.size > 1){
                drawing_polinom(this, points, xMin, xMax ,yMin, yMax)
            }
        })




    Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Bottom) {
        Row(modifier = Modifier.padding(10.dp, 10.dp).fillMaxWidth(0.7f)){
            Box(modifier = Modifier.padding(10.dp, 10.dp).fillMaxWidth(0.3f)){
                TextField(value = xMin.toString(),
                    onValueChange = { value -> xMin = value.toIntOrNull() ?:-10 })
            }
            Box(modifier = Modifier.padding(10.dp, 10.dp).fillMaxWidth(0.3f)) {
                TextField(value = xMax.toString(),
                    onValueChange = { value -> xMax = value.toIntOrNull() ?: 10 })
            }
            Box(modifier = Modifier.padding(10.dp, 10.dp).fillMaxWidth(0.3f)) {
                Text(text = "${yMinMax}", fontSize = 10.sp)
                Slider(value = yMinMax,
                    valueRange = -1f..1f,
                    steps = 9,
                    onValueChange = { yMinMax = it })
            }
        }
        Button(onClick = {points.clear()}, modifier = Modifier.padding(horizontal = 25.dp)){
            Text("Очистить")
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
