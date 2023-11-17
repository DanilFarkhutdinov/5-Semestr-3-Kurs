import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.text.drawText
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

class Point(_x: Float, _y: Float, width: Float, height: Float){
    val x = _x + width / 2
    val y = _y + height
}

fun draw_point(x_start: Float, y_start: Float, width: Float, height: Float):List<List<Float>>{
    var x_cor = mutableListOf<Float>()
    var y_cor = mutableListOf<Float>()
    var coordinates = mutableListOf<List<Float>>()
    var x = x_start
    var y = y_start


    x_cor += x
    y_cor += y
    for (i in 0..10000){
        var rand = (1..100).random()
        if (rand <= 85){
            x = 0.85f * x + 0.04f * y
            y = -0.04f * x + 0.85f * y + 1.6f
        }
        else{
            if (rand > 85 && rand <= 92){
                x = 0.2f * x - 0.26f * y
                y = 0.23f * x + 0.22f * y + 1.6f
            }
            else{
                if(rand > 92 && rand <= 99){
                    x = -0.15f * x + 0.28f * y
                    y = 0.26f * x + 0.24f * y + 0.44f
                }
                else{
                    x = 0f
                    y = 0.16f * y
                }
            }
        }
        x_cor += x *  100 + width / 2
        y_cor += height - y * 100
    }
    coordinates += x_cor
    coordinates += y_cor
    return coordinates
}


@Composable
@Preview
fun App() {
    var x by remember { mutableStateOf( 1f) }
    var y by remember { mutableStateOf( 1f) }
    var n = 1000

    Canvas(modifier = Modifier.fillMaxSize(),
        onDraw = {
            x = this.size.width / 2
            y = this.size.height

            var coordinates = draw_point(x, y, this.size.width, this.size.height)
            var x_cor = coordinates[0]
            var y_cor = coordinates[1]
            for (i in 0..10000 - 1){
                drawCircle(
                    color = Color.Green,
                    radius = 3f,
                    center = Offset(x_cor[i], y_cor[i])
                )
            }
            var p = Point(0f, 0f, this.size.width, this.size.height)
            drawCircle(
                color = Color.Red,
                radius = 10f,
                center = Offset(p.x, p.y)
            )
        })
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
