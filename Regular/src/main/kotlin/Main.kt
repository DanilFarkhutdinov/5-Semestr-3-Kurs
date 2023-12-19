import java.net.HttpURLConnection
import java.net.URL
import kotlin.reflect.typeOf


fun main(args: Array<String>) {
    println("Задача 1 - поиск аббревиатур")
    task_1()
    println()
    println("Задача 2 - построение аббревиатуры")
    task_2()
    println()
    println("Задача 3 - CamelCase to snake_case")
    task_3()
    println()
    println("Задача 4 - количество слов")
    task_4()
    println()
    println("Задача 5 - слова на a, e")
    task_5()
    println()
    println("Задача 6 - деление текста на предложения")
    task_6()
    println()
    println("Задача 7 - количество вхождений the")
    task_7()
}


fun task_1(){
    var text = "Это курс информатики соответствует ФГОС и ПООП, это подтверждено ФГУ ФНЦ НИИСИ РАН"
    val regex = Regex("([А-Я])([А-Я]+)")
    println(regex.findAll(text).map { it.value }.toList().joinToString())
}


fun task_2(){
    var text = "Комитет государственной Безопасности"
    val regex = Regex("([А-Я]|[а-я])+")
    val b = regex.findAll(text).map { it.value }.toList()
    var s = ""
    for (elem in b){
        val regex2 = Regex("\\b([а-я]|[А-Я])")
        var c = regex2.findAll(elem)
        s += c.map { it.value }.toList()[0]
    }
    println(s.uppercase())
}


fun task_3(){
    var text = "camelCaseVar"
    val regex = Regex("[A-Z]+")
    val b = regex.findAll(text).map { it.value }.toList()
    var s = ""
    var count = 0
    var w_c = 0
    for (w in text){
        if(b.size > count && w.toString() == b[count]){
           if (w_c > 0){
               s += "_"
           }
            s += w.lowercaseChar()
            count += 1
        }
        else{
            s += w
        }
        w_c += 1
    }
    println(s)
}


fun task_4(){
    var text = "- Дельный, что и говорить,\n" +
            "     Был старик тот самый,\n" +
            "     Что придумал суп варить\n" +
            "     На колесах прямо.\n" +
            "     Суп - во-первых. Во-вторых,\n" +
            "     Кашу в норме прочной.\n" +
            "     Нет, старик он был старик\n" +
            "     Чуткий - это точно."
    val regex = Regex("([А-Я]|[а-я]|-)+")
    val b = regex.findAll(text).map { it.value }.toMutableList()
    b.removeIf { it == "-"}
    println(b.size)
}


fun task_5(){
    var text = "The following example creates an ArrayList with a capacity of 50 elements." +
            "Four elements are then added to the ArrayList and the ArrayList is trimmed accordingly."

    val regex = Regex("(\\b(a|e))([a-z]+|[A-Z])+|(\\b(a|e))")
    val b = regex.findAll(text).map { it.value }.toList()
    println(b.toSet().joinToString())
}


fun task_6(){
    var text = """Дядя семён ехал из города домой. С ним была собака жучка. Вдруг из леса выскочили волки! Жучка испугалась и прыгнула в сани. У дяди семёна была хорошая лошадь. Она тоже испугалась и быстро помчалась по дороге. Деревня была близко. Показались огни в окнах. Волки отстали."""
    text = """Mr. Smith bought cheapsite.com for 1.5 million dollars, i.e. he paid a lot for it! \
Did he mind? Adam Jones Jr. thinks he didn't. In any case, this isn't true... \
Well, with a probability of .9 it isn't."""
    val regex = Regex("(?=[A-Z])(?<=[.?]\\s)")
    //var b = regex.findAll(text).map { it.value }.toList()
    var b = regex.split(text)
    for (elem in b){
        println(elem)
    }
}


fun task_7(){
    var text1 = "The animals in mountain the are meny children in kindergarten the dog is a black the mom is nice cook The birth to sing a nice song"
    var text = URL("https://www.gutenberg.org/files/2638/2638-0.txt").readText()
    val r1 = Regex("(\\bPART\\s[I V]{1,3})\\b")
    val b = r1.split(text)
    val regex = Regex("\\b[t][h][e]\\b+")
    //val b = regex.findAll(text).map { it.value }.toList()
    val c = regex.findAll(b[5]).map { it.value }.toList()
    println(c.size)
}