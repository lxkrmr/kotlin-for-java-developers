fun main() {
    println("with")
    val window = Window()
    with(window) {
        width = 300
        height = 200
        isVisible = true
    }
    window.width eq 300
    window.height eq 200
    window.isVisible eq true

    println("run: and with can be used interchangeable, in this case we are using it to handle a nullable")
    val windowIsNull: Window? = null
    windowIsNull?.run {
        width = 300
        height = 200
        isVisible = true
    }
    windowIsNull eq null

    println("apply: returns the receiver as a result")
    val windowAndApply = Window().apply {
        width = 400
        height = 100
        isVisible = false
    }
    windowAndApply.width eq 400
    windowAndApply.height eq 100
    windowAndApply.isVisible eq false

    println("also: regular argument instead of this")
    windowAndApply.also { showWindow(it) }

}

fun showWindow(window: Window) = println(window)

data class Window(var width: Int = 0, var height: Int = 0, var isVisible : Boolean = false)

