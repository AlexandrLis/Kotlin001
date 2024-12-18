fun main() {

    var person = Person("","","")
    readCommand(person)

}


data class Person(var name: String, var phone: String, var email: String)

sealed interface Command{
    fun doing()
    fun isValid(value1: Regex, value2: String): Boolean{
        if(value1.containsMatchIn(value2)){
            return true
        }
        return false
    }
}

fun readCommand(person: Person): Command{
    println("""
                Выберите действие:
                1 - exit
                2 - help
                3 - add new abonent
                4 - show
            """)
    var choise: String? = readlnOrNull()
    if(choise.equals("1")){
        EndClass().doing()
        return EndClass()
    }else if(choise.equals("2")){
        Information(person).doing()
        return Information(person)
    }else if(choise.equals("3")){
        AddAbonent(person).doing()
        return AddAbonent(person)
    }else if(choise.equals("4")){
        Show(person).doing()
        return Show(person)
    }else{
        readCommand(person)
        return readCommand(person)
    }
}

class Show(var person: Person) : Command{


    override fun doing(){
        if((person.name == "" && person.phone == "") || person.name == "" && person.email == ""){
            println("Not initialized")
        }else{
            println("Крайний добавленный абонент:")
            println(person)
        }
        readCommand(person)
    }
}

class EndClass : Command{
    override fun doing(){
        println("До свидания!")
        return
    }
}


class Information(var person: Person) : Command{
    override fun doing() {
        println("""
                Здравствуйте! Вы находитесь в телефонной книге.
                Тут вы можете добавить имя и номер телефона нового 
                адресата или имя и адрес электронной почты нового
                адресата
                -При добавлении имени абонента используйте только 
                буквы!
                -При добавлении номера телефона используйте только
                цифры от 0 до 9 или символ + ("плюс") перед номером
                телефона
                -При добавлении адреса электронной почты не забудьте
                использовать символ @ ("собака") и символ . ("точка")
            """)
        readCommand(person)
    }
}

class AddAbonent(var person: Person) : Command{

    override fun doing() {
        println("""
                Введите данные нового абонента в соответствии с шаблоном
                add <Имя> phone <Номер телефона> 
                или
                add <Имя> email <Адрес электронной почты>:
            """
        )
        var abonent: String? = readlnOrNull()
        if (abonent != null) {
            var arrayAbonent = abonent.split(" ")
            if (arrayAbonent.size != 4) {
                println(
                    """
                    ПРИ ДОБАВЛЕНИИ НОВОГО АБОНЕНТА ВОЗНИКЛА ОШИБКА!!!
                    ПОПРОБУЙТЕ СНОВА
                """
                )
                readCommand(person)
            }

            var name: String = arrayAbonent[1]
            var secondAbonentParametr: String = arrayAbonent[3]


            var regexName = "^[a-z]+$|^[а-я]+$".toRegex(RegexOption.IGNORE_CASE)
            var regexPhone = "^[+][0-9]+$|^[0-9]+$".toRegex()
            var regexemail = "\\b[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}\\b".toRegex(RegexOption.IGNORE_CASE)



            when{
                isValid(regexName,name) && isValid(regexPhone,secondAbonentParametr) -> {
                    println("Абонент $name с номером телефона $secondAbonentParametr добавлен")
                    person.name = name
                    person.phone = secondAbonentParametr
                    person.email = ""
                    readCommand(person)
                }
                isValid(regexName,name) && isValid(regexemail,secondAbonentParametr) -> {
                    println("Абонент $name с адресом электронной почты $secondAbonentParametr добавлен")
                    person.name = name
                    person.email = secondAbonentParametr
                    person.phone = ""
                    readCommand(person)
                }
                else -> {
                    Information(person).doing()
                    readCommand(person)
                }

            }

        } else {
            Information(person).doing()
            readCommand(person)
        }
    }
}








