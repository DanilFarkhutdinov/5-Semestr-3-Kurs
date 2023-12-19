package drawing

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer

import math.*

@OptIn(ExperimentalTextApi::class)
//@Composable
fun draw_x_axis(skope: DrawScope, xMin: Int, xMax: Int, yMinMax: Float, textMeasurer: TextMeasurer){

    skope.drawLine(color = Color.Black,
        start = Offset(0f, skope.size.height*(1+yMinMax)/2),
        end = Offset(skope.size.width, skope.size.height*(1+yMinMax)/2)
    )
    for(i in xMin .. xMax) {
        skope.drawText(
            textMeasurer = textMeasurer, text = i.toString(),
            topLeft = Offset(
                skope.size.width * (i - xMin) / (xMax - xMin),
                skope.size.height * (1 + yMinMax) / 2
            )
        )
    }
}

@OptIn(ExperimentalTextApi::class)
fun draw_y_axis(skope: DrawScope, xMin: Int, xMax: Int, yMin: Int, yMax: Int, textMeasurer: TextMeasurer){
    skope.drawLine(color = Color.Black,
        start = Offset(-skope.size.width*xMin/(xMax-xMin), 0f),
        end = Offset(-skope.size.width*xMin/(xMax-xMin), skope.size.height)
    )
    for(i in yMin..yMax) {
        skope.drawText(
            textMeasurer = textMeasurer, text = i. toString(),
            topLeft = Offset(
                (0 - xMin) * skope.size.width / (xMax - xMin),
                (yMax - i) * skope.size.height / (yMax - yMin)
            )
        )
    }
}

fun draw_point(scope: DrawScope, points: Map<Float, Float>, xMin: Int, xMax: Int, yMin: Int, yMax: Int){
    for (point in points){
        scope.drawCircle(
            color = Color.Green,
            radius = 5f,
            center = Offset(
                x_from_the_Cartesian_system(point.key, scope.size.width, xMin, xMax),
                y_from_the_Cartesian_system(point.value, scope.size.height, yMin, yMax)
            )
        )
    }
}

fun drawing_polinom(skope: DrawScope, points: Map<Float, Float>, xMin: Int, xMax: Int, yMin:Int, yMax: Int){
    for (x in 0..skope.size.width.toInt() - 1){
        skope.drawLine(
            color = Color.Red,
            start = Offset(
                x.toFloat(),
                y_from_the_Cartesian_system(
                    Lagrange_polynomial(
                        points,
                        x_to_the_Cartesian_system(x.toFloat(), skope.size.width.toInt(), xMin, xMax)),
                    skope.size.height,
                    yMin,
                    yMax
                    )
            ),
            end = Offset(
                x.toFloat() + 1,
                y_from_the_Cartesian_system(
                    Lagrange_polynomial(
                        points,
                        x_to_the_Cartesian_system(x.toFloat() + 1, skope.size.width.toInt(), xMin, xMax)),
                    skope.size.height,
                    yMin,
                    yMax
                )
            )
        )
    }
}