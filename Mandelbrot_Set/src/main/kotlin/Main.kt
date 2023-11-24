import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Canvas
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
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlin.math.sqrt

class Complex(var Re: Float = 0f, var Im: Float = 0f){
    fun show(){
        println("$Re + $Im" + "i")
    }

    fun abs(): Float{
        return sqrt(this.Re * this.Re + this.Im * this.Im)
    }
}

operator fun Complex.plus(number: Complex): Complex{
    var c = Complex()
    c.Re = this.Re + number.Re
    c.Im = this.Im + number.Im
    return c
}

operator fun Complex.times(number: Complex): Complex{
    var c = Complex()
    c.Re = this.Re * number.Re - this.Im * number.Im
    c.Im = this.Re * number.Im + this.Im * number.Re
    return c
}

@Composable
@Preview
fun App() {

    var xMin = -2.5f
    var yMin = -1.0f
    var xMax = 1.0f
    var yMax = 1.0f


    Canvas(modifier = Modifier.fillMaxSize(),
        onDraw = {
            var h_x = (xMax - xMin) / this.size.width
            var h_y = (yMax - yMin) / this.size.height

            //launch


            for (i in 0..this.size.width.toInt() - 1){
                for(j in 0..this.size.height.toInt() - 1){
                    var z1 = Complex(0f, 0f)
                    var c = Complex(xMin + h_x * i, yMin + h_y * j)
                    var iteration = 0
                    while(z1.abs() < 2 && iteration < 1000){
                        z1 = z1 * z1 + c
                        iteration += 1
                    }
                    if(iteration >= 1000){
                        drawCircle(Color.White, radius = 1f, Offset(i.toFloat(), j.toFloat()))
                    }
                    else{
                        drawCircle(Color.Black, radius = 1f, Offset(i.toFloat(), j.toFloat()))
                    }
                }
            }
        })
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
