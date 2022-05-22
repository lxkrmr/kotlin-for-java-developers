fun main() {
    val x: Int? = 1
    val y: Int = 2
    val sum = x ?: 0 + y
    println(sum)

    val s1: String? = null
    val s2: String? = ""
    s1.isEmptyOrNull() eq true
    s2.isEmptyOrNull() eq true
    val s3 = "   "
    s3.isEmptyOrNull() eq false

    val s = "foo"
    println(s as? Int)    // null
    println(s as Int?)    // exception
}

fun String?.isEmptyOrNull(): Boolean = this == null || this.isEmpty()