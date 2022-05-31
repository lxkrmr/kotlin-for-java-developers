object KSingleton {
    fun foo() {
        println("I am a singleton in Kotlin")
    }
}

class C {
    companion object {
        @JvmStatic fun foo() {}
        fun bar() {}
    }
}

fun main() {
    KSingleton.foo()
}