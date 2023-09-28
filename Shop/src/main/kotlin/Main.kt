fun main(args: Array<String>) {

    var televizor = Tovar("Телевизор", 100.0, 9.5)
    var phone = Tovar("Телефон", 50.0, 8.7)
    var laptop = Tovar("Ноутбук", 75.0, 8.9)

    var arraytechnic: Array<Tovar> = arrayOf(televizor, phone, laptop)

    val technic = Category("Техника", arraytechnic)

    var tea = Tovar("Чай", 10.0, 7.5)
    var meat = Tovar("Мясо", 27.0, 8.3)
    var fish = Tovar("Рыба", 25.0, 8.1)

    var arrayproducts: Array<Tovar> = arrayOf(tea, meat, fish)

    val products = Category("Продукты", arrayproducts)

    var switshot = Tovar("Свитшот", 35.0, 8.7)
    var hoody = Tovar("Худи", 37.0, 7.9)
    var jacket = Tovar("Куртка", 38.0, 7.5)

    var arrayclothes: Array<Tovar> = arrayOf(switshot, hoody, jacket)

    var array: Array<Tovar> = arrayOf(televizor, meat, jacket)

    val clothes = Category("Одежда", arrayclothes)

    val basket1 = Basket(arraytechnic)

    val basket2 = Basket(arrayproducts)

    val basket3 = Basket(array)

    var user1 = User("Ivan", "12345", basket1)

    var user2 = User("Vasya", "qwert", basket2)

    var user3 = User("Andrey", "1234567", basket3)

    println("Каталог")

    println(technic.name)
    for(i in 0..arraytechnic.size-1){
        println("Название товара: ${arraytechnic[i].name} , Цена: ${arraytechnic[i].price}, Рейтинг: ${arraytechnic[i].rating}")
    }
    println()

    println(products.name)
    for(i in 0..arrayproducts.size-1){
        println("Название товара: ${arrayproducts[i].name} , Цена: ${arrayproducts[i].price}, Рейтинг: ${arrayproducts[i].rating}")
    }
    println()

    println(clothes.name)
    for(i in 0..arrayclothes.size-1){
        println("Название товара: ${arrayclothes[i].name} , Цена: ${arrayclothes[i].price}, Рейтинг: ${arrayclothes[i].rating}")
    }
    println()

    user1.buyInfo()
    user2.buyInfo()
    user3.buyInfo()
}

class Tovar(var name: String, val price: Double, val rating: Double){

}

class Category(var name: String, val array: Array<Tovar>){

}

class Basket(var basket: Array<Tovar>){
    fun tovarsInfo(){
        for(i in 0..basket.size - 1){
            print(basket[i].name + " ")
        }
    }
}

class User(val login: String, val password: String, val basket: Basket){
    fun buyInfo(){
        println("Логин: $login, Пароль: $password")
        basket.tovarsInfo()
        println()
        println()
    }
}