package com.company.vehicles

import com.company.professions.Driver
import com.company.details.Engine

open class Car(val mark: String, val carClass: String, val weight: Double, val driver: Driver, val engine: Engine){
    fun start(){
        println("Поехали")
    }

    fun stop(){
        println("Останавливаемся")
    }

    fun turnRight(){
        println("Поворот направо")
    }

    fun turnLeft(){
        println("Поворот налево")
    }

    fun toStringInformation(){
        println("Марка: $mark, Класс: $carClass, Вес автомобиля: $weight, ФИО водителя: ${driver.FIO}, Стаж: ${driver.drivingexp}, Мотор: ${engine.power}, Производитель мотора: ${engine.maker}")
    }
}

class Lorry(mark: String, carClass: String, weight: Double, driver: Driver, engine: Engine): Car(mark, carClass, weight, driver, engine){
    var limspeed: Int = 100
        set(value) {
            if((value >=90) and (value <= 500))
                field = value
        }
        get(){
            return field
        }
}