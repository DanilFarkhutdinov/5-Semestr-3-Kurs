import com.company.vehicles.*
import com.company.details.*
import  com.company.professions.*

fun main(args: Array<String>) {
    val driver: Driver = Driver("Farkhutdinov Danil Maratovich", 1)
    val engine: Engine = Engine(500.0, "Germany")
    val car = Lorry("Mersedes", "S", 3.0, driver, engine)
    car.limspeed = 350
    car.start()
    car.stop()
    car.turnRight()
    car.turnLeft()
    car.toStringInformation()
}