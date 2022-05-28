import State.*

class Person(val name: String, var age: Int)

class Rectangle(val height: Int, val width: Int) {
    val isSquare: Boolean
        get() {
            return height == width
        }
}

class StateLogger {
    var state = false
        set(value) {
            println("state has changed: $field + $value")
            field = value
        }
}

enum class State { ON, OFF }

class StateLoggerWithState {
    private var boolState = false

    var state: State
        get() = if (boolState) ON else OFF
        set(value) {
            boolState = value == ON
        }
}

class DefaultAccessorsForVarProperty {
    var trivialProperty: String = "abc"
}

class DefaultAccessorsForValProperty {
    val trivialProperty: String = "abc"
}

class LengthCounter {
    var counter: Int = 0
        private set

    fun addWord(word: String) {
        counter += word.length
    }
}

fun main() {
    person()
    rectangle()
    stateLogger()
    defaultAccessors()
    lengthCounter()
}

private fun person() {
    println("=== Person ===")
    val person = Person(age = 21, name = "bar")
    println(person.age)
    println(person.name)
    person.age = 42
    println(person.age)
}

private fun rectangle() {
    println("=== Rectangle ===")
    val square = Rectangle(2, 2)
    println(square.isSquare)
    println(square.height)
    println(square.width)
}

fun stateLogger() {
    println("=== State Logger ===")
    val stateLogger = StateLogger()
    stateLogger.state = true

    println("=== State Logger using State ;) ===")
    val stateLoggerWithState = StateLoggerWithState()
    println(stateLoggerWithState.state)
    stateLoggerWithState.state = ON
    println(stateLoggerWithState.state)
}

fun defaultAccessors() {
    println("=== Default Accessors for var property ===")
    val defaultAccessorsForVarProperty = DefaultAccessorsForVarProperty()
    println(defaultAccessorsForVarProperty.trivialProperty)
    defaultAccessorsForVarProperty.trivialProperty = "def"
    println(defaultAccessorsForVarProperty.trivialProperty)

    println("=== Default Accessors for var property ===")
    val defaultAccessorsForValProperty = DefaultAccessorsForValProperty()
    println(defaultAccessorsForValProperty.trivialProperty)
    // defaultAccessorsForValProperty.trivialProperty = "def" <- not possible because of val
    println(defaultAccessorsForValProperty.trivialProperty)
}

fun lengthCounter() {
    println("=== Length Counter")
    val lengthCounter = LengthCounter()
    println(lengthCounter.counter)
    lengthCounter.addWord("foo")
    println(lengthCounter.counter)

    // lengthCounter.counter = 100 <- not possible as the setter is private and can only be used within the class
}
