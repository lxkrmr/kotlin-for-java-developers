// data modifier
// Generates useful methods:
// equals, hashCode, cpy, toString, and some others

data class Contact(val name: String, val address: String)

class Foo(val first: Int, val second: Int)
data class Bar(val first: Int, val second: Int)

data class User(val email: String) {
    var nickname: String? = null
}

fun main() {
    val contact = Contact("foo", "Some Street 21 in Some City 42")
    val contactCopy = contact.copy(address = "Adapted Street 22 in Some Other City 43")
    println(contact)
    println(contactCopy)

    println("* Default equals checks reference equality *")
    val f1 = Foo(1, 2)
    val f2 = Foo(1, 2)
    println(f1 == f2)

    println("* modifiers 'data' generates equals which compares the content *")
    val b1 = Bar(1, 2)
    val b2 = Bar(1, 2)
    println(b1 == b2)
    println("* to compare the reference equality of a data class you can use === *")
    println(b1 === b2)

    println("* for the generated methods only properties in primary constructor are used *")
    val user1 = User(email = "foo@bar.de")
    user1.nickname = "Foo"
    println(user1)

    val user2 = User(email = "foo@bar.de")
    user2.nickname = "Bar"
    println(user2)
    println(user1 == user2)
}