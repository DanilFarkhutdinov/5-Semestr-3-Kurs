fun main(args: Array<String>) {
    var dog: Animal = Dog("Собака")
    var cat: Animal = Cat("Кошка")
    var bear: Animal = Bear("Медведь")
    var animals: Array<Animal> = arrayOf(dog, cat, bear)
    var veterinar = Veterinar()

    for (i in 0..animals.size-1){
        veterinar.treatAnimal(animals[i])
        println()
    }

    for (i in 0..animals.size-1){
        animals[i].makeNoise()
        animals[i].eat()
        println()
    }

}

abstract class Animal(_name: String){

    var name: String = _name

    abstract fun makeNoise()

    abstract fun eat()

    abstract fun getDescription()
}

class Dog(_name: String) : Animal(_name){

    override fun makeNoise() {
        println("Гав гав")
    }

    override fun eat() {
        println("chappy")
    }

    override fun getDescription() {
        println("Хаски")
    }
}

class Cat(_name: String) : Animal(_name){

    override fun makeNoise() {
        println("Мяу Мяу")
    }

    override fun eat() {
        println("whiskas")
    }

    override fun getDescription() {
        println("Мэйн кун")
    }
}

class Bear(_name: String) : Animal(_name){
    override fun makeNoise() {
        println("y y y")
    }

    override fun eat() {
        println("meat")
    }

    override fun getDescription() {
        println("Гризли")
    }
}

class Veterinar{
    fun treatAnimal(animal: Animal){
        println(animal.name)
        animal.getDescription()
    }
}