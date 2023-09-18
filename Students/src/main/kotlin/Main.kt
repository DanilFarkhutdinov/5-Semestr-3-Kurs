fun main(args: Array<String>) {
    val student = Student("Danil", "Farkhutdinov", "05-105", 5.0)
    val aspirant = Aspirant("Ivanov", "Ivan", "1", 4.5)
    aspirant.scienceWork = "Math"
    var studentArray: Array<Student> = arrayOf(student, aspirant)
    println(studentArray[0].getScholarship())
    println(studentArray[1].getScholarship())
    val newstudent = Student("Petrov", "Andrey", "04-104", 4.3)
    studentArray = studentArray.plus(newstudent)
    println(studentArray[2].getScholarship())
}

open class Student(val firstName: String, val lastName: String, val group: String, val averageMark: Double){

    open fun getScholarship(): Int{
        if (averageMark == 5.0){
            return 2000
        }
        else{
            return 1900
        }
    }
}

class Aspirant(firstName: String, lastName: String, group: String, averageMark: Double): Student(firstName, lastName, group, averageMark){
    var scienceWork: String = "Undefined"
        set(value) {
            if ((value.length >= 1) and (value.length <= 50)){
                field = value
            }
        }
        get() {
            return field
        }
    override fun getScholarship(): Int {
        if (averageMark == 5.0){
            return 2500
        }
        else{
            return 2200
        }
    }
}