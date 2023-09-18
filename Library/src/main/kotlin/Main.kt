fun main(args: Array<String>) {
    val Tom: Library = Library("Tom", "5", "math", "25", "8")
    val book1: Books = Books("Война и мир", "Толстой")
    val book2: Books = Books("Преступление и наказание", "Достоевский")
    Tom.takeBook("Приключения", "Словарь", "Энциклопедия")
    Tom.returnBook(3)
    Tom.takeBook(book1)
    Tom.returnBook(book2)
}

class Library{
    val name: String
    val number: String
    val facultet: String
    val birthday: String
    val phone: String

    public constructor(_name: String, _number: String, _facultet: String, _birthday: String, _phone: String){
        name = _name
        number = _number
        facultet = _facultet
        birthday = _birthday
        phone = _phone
    }

    fun takeBook(a: Int){
        println("$name взял $a книги")
    }

    fun takeBook(vararg books: String){
        var str: String = "$name взял книги: "
        for (book in books){
            str += book
            str += ", "
        }
        println(str)
    }

    fun takeBook(vararg books: Books){
        var str: String = "$name взял книги: "
        for (book in books){
            str += book.name
            str += ", "
        }
        println(str)
    }

    fun returnBook(b: Int){
        println("$name вернул $b книги")
    }

    fun returnBook(vararg books: String){
        var str: String = "$name вернул книги: "
        for (book in books){
            str += book
            str += ", "
        }
        println(str)
    }

    fun returnBook(vararg books: Books){
        var str: String = "$name вернул книги: "
        for (book in books){
            str += book.name
            str += ", "
        }
        println(str)
    }
}


class Books{
    val name: String
    val author: String

    constructor(_name: String, _author: String){
        name = _name
        author = _author
    }
}