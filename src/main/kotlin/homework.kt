fun main() {

    phoneBook()
}

fun phoneBook(){
    println("""
                Выберите действие:
                1 - выход
                2 - справка
                3 - добавить имя и номер телефона
                4 - добавить имя и адрес электронной почты
            """)
    var choise: String? = readlnOrNull()
    when(choise){
        "1" -> return
        "2" -> information()
        "3" -> addNumber()
        "4" -> addemail()
        else -> phoneBook()
    }
}

fun information(){
    println("""
        Здравствуйте! Вы находитесь в телефонной книге.
        Тут вы можете добавить имя и номер телефона нового 
        адресата или имя и адрес электронной почты нового
        адресата
        """)
    phoneBook()
}

fun addNumber(){
    println("Введите имя нового абонента:")
    var name: String? = readlnOrNull()
    var regexName = "^[a-z]+$|^[а-я]+$".toRegex(RegexOption.IGNORE_CASE)

    println("Введите номер телефона нового абонента:")
    var phone: String? = readlnOrNull()
    var regexPhone = "^[+][0-9]+$|^[0-9]+$".toRegex()

    if(name != null && phone != null){
        if(regexName.containsMatchIn(name) && regexPhone.containsMatchIn(phone)){
            println("Абонент $name с номером телефона $phone добавлен")
            phoneBook()
        }else{
            println("При добавлении нового абонента возникла ошибка")
            println("Попробуйте добавить абонента снова")
            phoneBook()
        }
    }else{
        println("При добавлении нового абонента возникла ошибка")
        println("Попробуйте добавить абонента снова")
        phoneBook()
    }
}

fun addemail(){
    println("Введите имя нового абонента:")
    var name: String? = readlnOrNull()
    var regexName = "^[a-z]+$|^[а-я]+$".toRegex(RegexOption.IGNORE_CASE)

    println("Введите адрес электронной почты нового абонента:")
    var email: String? = readlnOrNull()
    var regexemail = "\\b[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}\\b".toRegex(RegexOption.IGNORE_CASE)

    if(name != null && email != null){
        if(regexName.containsMatchIn(name) && regexemail.containsMatchIn(email)){
            println("Абонент $name с адресом электронной почты $email добавлен")
            phoneBook()
        }else{
            println("При добавлении нового абонента возникла ошибка")
            println("Попробуйте добавить абонента снова")
            phoneBook()
        }
    }else{
        println("При добавлении нового абонента возникла ошибка")
        println("Попробуйте добавить абонента снова")
        phoneBook()
    }
}

