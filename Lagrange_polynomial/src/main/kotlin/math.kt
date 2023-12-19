package math

import androidx.compose.ui.graphics.drawscope.DrawScope

fun x_to_the_Cartesian_system(xn: Float, width: Int, xMin: Int, xMax: Int): Float{
    return xn / (width / (xMax - xMin)) + xMin
}

fun y_to_the_Cartesian_system(yn: Float, height: Int, yMin: Int, yMax: Int): Float{
    return -(yn / (height / (yMax - yMin)) - yMax)
}

fun x_from_the_Cartesian_system(xd: Float, width: Float, xMin: Int, xMax: Int): Float{
    return (xd - xMin) * (width / (xMax - xMin))
}

fun y_from_the_Cartesian_system(yd: Float, height: Float, yMin: Int, yMax: Int): Float{
    return (yMax - yd) * (height / (yMax - yMin))
}

fun Lagrange_polynomial(points: Map<Float, Float>, x:Float): Float{
    var y = 0f
    for (point1 in points){
        var l = 1f
        for (point2 in points){
            if (point1.key != point2.key){
                l *= (x - point2.key) / (point1.key - point2.key)
            }
        }
        y += l * point1.value
    }
    return y
}