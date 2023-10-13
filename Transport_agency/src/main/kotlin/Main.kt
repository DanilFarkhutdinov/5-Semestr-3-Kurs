import kotlin.random.Random

fun main(args: Array<String>) {
    var city_1 = City("Москва", true, 3)
    var city_2 = City("Казань", true, 3)
    var city_3 = City("Санкт Петербург", false, 3)
    var city_4 = City("Набережные Челны", true, 1)
    var city_5 = City("Владивосток", true, 3)
    val roadMS = Road(city_1, city_3, 634, true)
    val roadMK = Road(city_1, city_2, 717, false)
    val roadMCh = Road(city_1, city_4, 923, false)
    val roadMV = Road(city_1, city_5, 6417, false)
    var client = Client(5.5, 5000, 10.0, city_1, city_3, roadMS)
    var client1 = Client(5.5, 10000, 5.0, city_1, city_2, roadMK)
    var client2 = Client(100.5, 1000, 5.0, city_1, city_4, roadMCh)
    var client3 = Client(25.0, 70000, 9.0, city_1, city_5, roadMV)
    var agency = Agency()
    //client.transport = agency.Transport(client)
    //client1.transport = agency.Transport(client1)
    //client2.transport = agency.Transport(client2)
    client3.transport = agency.Transport(client3)
    client3.showInfo()
}

interface Transportation{
    val name: String
    var price: Int
    var w_price: Int
    var delivery_speed: Int
    val break_k: Double
}

class Car(): Transportation{
    override val name = "Автомобильная"
    override var price = 7
    override var w_price = 7
    override var delivery_speed = 90
    override val break_k = 0.3
}

class Train(): Transportation{
    override val name = "Железнодорожная"
    override var price = 5
    override var w_price = 7
    override var delivery_speed = 150
    override val break_k = 0.01
}

class Plane(): Transportation{
    override val name = "Воздушная"
    override var price = 10
    override var w_price = 8
    override var delivery_speed = 600
    override val break_k = 0.0001
}

class City(_name: String, _weather:Boolean, _size: Int){
    val name = _name
    var weather = _weather
    var size = _size
}

class Client(_weight: Double, _cl_price: Int, _cl_speed: Double, _city_from: City, _city_to: City, _road: Road){
    var weight = _weight
    var cl_price = _cl_price
    var cl_speed = _cl_speed
    var city_from =_city_from
    var city_to = _city_to
    var road = _road

    val car = Car()

    var transport: Transportation = car

    fun showInfo(){
        var price = road.distance * transport.price + weight * transport.w_price
        var delivery = road.distance / transport.delivery_speed
        var flag = false
        if ((road.highway) and (transport is Car) and (cl_price > price * 1.5)){
            price *= 1.5
            delivery -= 4
            flag = true
        }
        println("Доставка из города ${city_from.name} в город ${city_to.name}")
        println("Тип доставки: ${transport.name}")
        if (flag){
            println("По автомагистрали:")
        }
        println("Цена: $price рублей")
        println("Скорость доставки: $delivery (в часах)")
        if (status()){
            println("Заказ доставлен")
            println("Оплата прошла успешно")
        }
        else{
            println("Произошла авария")
            println("Деньги возвращены клиенту")
        }
    }

    fun status(): Boolean{
        var number: Int
        if (transport.break_k == 0.3){
            number = Random.nextInt(1, 10)
            if (number == 1 || number == 2 || number == 3){
                return false
            }
            else{
                return true
            }
        }
        else{
            if (transport.break_k == 0.01){
                number = Random.nextInt(1, 100)
                if (number == 1){
                    return false
                }
                else{
                    return true
                }
            }
            else{
                if (transport.break_k == 0.0001){
                    number = Random.nextInt(1, 10000)
                    if (number == 1){
                        return false
                    }
                    else{
                        return true
                    }
                }
                else{
                    return true
                }
            }
        }
    }
}

class Road(_city1: City, _city2: City, _distance: Int, _highway: Boolean){
    val city1 = _city1
    val city2 = _city2
    val distance = _distance
    var highway = _highway
}

class Agency(){


    fun Transport(client: Client): Transportation{
        val t = defTransportationType(client)

        val car = Car()
        val train = Train()
        val plane = Plane()

        when (t){
            1 -> return car
            2 -> return train
            3 -> return plane
            else -> {
                return car
            }
        }
    }

    fun defTransportationType(client: Client): Int{
        val car = Car()
        val train = Train()
        val plane = Plane()

        var price_cl = client.cl_price

        var carPrice = (client.road.distance + client.weight) * car.price
        var trainPrice = (client.road.distance + client.weight) * train.price
        var planePrice = (client.road.distance + client.weight) * plane.price

        var carDel = client.road.distance / car.delivery_speed
        var trainDel = client.road.distance / train.delivery_speed
        var planeDel = client.road.distance / plane.delivery_speed

        if ((client.city_from.size == 3) and (client.city_to.size == 3)){
            var wth = CkeckWeather(client)
            if ((wth[0] == 0) || (wth[1] == 0)){
                if(price_cl > carPrice){
                    if (client.cl_speed < trainDel){
                        return 2
                    }
                    if (client.cl_speed > trainDel){
                        return 1
                    }
                }
                if ((price_cl < carPrice) and (price_cl > trainPrice)){
                    return 2
                }
                if (price_cl < trainPrice){
                    return 2
                }
            }
            else{
                if (price_cl > planePrice){
                    if (client.cl_speed < planeDel){
                        return 3
                    }
                    if ((client.cl_speed > planeDel) and (client.cl_speed < trainDel)){
                        return 2
                    }
                    if (client.cl_speed > trainDel){
                        return 1
                    }
                }
                if ((price_cl < planePrice) and (price_cl > carPrice)){
                    if (client.cl_speed < planeDel){
                        return 2
                    }
                    if ((client.cl_speed > planeDel) and (client.cl_speed < trainDel)){
                        return 2
                    }
                    if (client.cl_speed > trainDel){
                        return 1
                    }
                }
                if (price_cl < carPrice){
                    return 2
                }
            }
        }
        else{
            if(((client.city_from.size == 2) and (client.city_to.size == 3)) || ((client.city_from.size == 3) and (client.city_to.size == 2)) || (client.city_from.size == 2) and (client.city_to.size == 2)){
                if(price_cl > carPrice){
                    if (client.cl_speed < trainDel){
                        return 2
                    }
                    if (client.cl_speed > trainDel){
                        return 1
                    }
                }
                if ((price_cl < carPrice) and (price_cl > trainPrice)){
                    return 2
                }
                if (price_cl < trainPrice){
                    return 2
                }
            }
            else {
                return 1
            }
        }
        return 0
    }

    fun CkeckWeather(client: Client): Array<Int>{
        var array= arrayOf<Int>()
        if (client.city_from.weather){
            array += 1
        }
        else{
            array += 0
        }
        if (client.city_to.weather){
            array += 1
        }
        else{
            array += 0
        }
        return array
    }
}
