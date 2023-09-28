fun main(args: Array<String>) {
    var books = Book("Война и мир, Преступление и наказание")
    var magazines = Magazine("Журнал о животных, Журнал о растениях")
    val array: Array<Printable> = arrayOf(books, magazines)
    for (i in 0..array.size-1){
        array[i].print()
    }
    println()
    println()

    books.printBooks(array)

    println()
    println()

    magazines.printMagazines(array)

}

interface Printable{
    var name: String
    fun print(){
        print(name + " ")
    }
}

class Book(_name: String): Printable{
    override var name: String = _name
    fun printBooks(printable: Array<Printable>){

        for (i in 0..printable.size-1){
            if (printable[i] is Book) {
                print(printable[i].name + " ")
            }
        }
    }
}

class Magazine(_name: String): Printable{
    override var name: String = _name

    fun printMagazines(printable: Array<Printable>){
        for (i in 0..printable.size-1){
            if (printable[i] is Magazine) {
                print(printable[i].name + " ")
            }
        }
    }
}