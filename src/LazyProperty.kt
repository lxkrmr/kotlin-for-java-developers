val lazyValue: String by lazy {
    println("computed")
    "Hello"
}

fun main() {
    // no lazyValue usage
}