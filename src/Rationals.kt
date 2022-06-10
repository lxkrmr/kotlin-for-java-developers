data class Rational private constructor(val numerator: Int, val denominator: Int) {
    companion object {
        fun create(numerator: Int, denominator: Int): Rational {
            if(denominator == 0) {
                throw IllegalArgumentException("Denominator can't be 0")
            }
            val checkedNumerator = if (numerator > 0 && denominator < 0) -numerator else numerator
            val checkedDenominator = if (numerator < 0 && denominator > 0) -denominator else denominator

            return Rational(checkedNumerator, checkedDenominator)
        }
    }

    override fun toString(): String {
        val denominatorWithoutSign = if (denominator < 0) -denominator else denominator
        return if (denominatorWithoutSign == 1) "$numerator" else "$numerator/$denominatorWithoutSign"
    }
}

operator fun Rational?.plus(other: Rational?): Rational {
    val thisOrFallback = this.orDefault()
    val otherOrFallback = other.orDefault()
    val thisWithCommonDenominator = Rational.create(
        thisOrFallback.numerator * otherOrFallback.denominator,
        thisOrFallback.denominator * otherOrFallback.denominator
    )
    val otherWithCommonDenominator = Rational.create(
        otherOrFallback.numerator * thisOrFallback.denominator,
        otherOrFallback.denominator * thisOrFallback.denominator
    )
    return Rational.create(
        thisWithCommonDenominator.numerator + otherWithCommonDenominator.numerator,
        thisWithCommonDenominator.denominator
    )
}

operator fun Rational?.minus(other: Rational?): Rational {
    val thisOrFallback = this.orDefault()
    val otherOrFallback = other.orDefault()
    val thisWithCommonDenominator = Rational.create(
        thisOrFallback.numerator * otherOrFallback.denominator,
        thisOrFallback.denominator * otherOrFallback.denominator
    )
    val otherWithCommonDenominator = Rational.create(
        otherOrFallback.numerator * thisOrFallback.denominator,
        otherOrFallback.denominator * thisOrFallback.denominator
    )
    return Rational.create(
        thisWithCommonDenominator.numerator - otherWithCommonDenominator.numerator,
        thisWithCommonDenominator.denominator
    )
}

operator fun Rational?.times(other: Rational?): Rational {
    val thisOrFallback = this.orDefault()
    val otherOrFallback = other.orDefault()
    return Rational.create(
        thisOrFallback.numerator * otherOrFallback.numerator,
        thisOrFallback.denominator * otherOrFallback.denominator
    )
}

operator fun Rational?.div(other: Rational?): Rational {
    val thisOrFallback = this.orDefault()
    val otherOrFallback = other.orDefault()
    return Rational.create(
        thisOrFallback.numerator * otherOrFallback.denominator,
        thisOrFallback.denominator * otherOrFallback.numerator
    )
}

operator fun Rational.unaryMinus() = Rational.create(-numerator, -denominator)

fun Rational?.orDefault(): Rational = this ?: Rational.create(0, 0)

infix fun Int.divBy(denominator: Int): Rational {
    return Rational.create(this, denominator)
}

fun String.toRational(): Rational {
    val split = this.split("/")
    val numerator = split.getOrNull(0)?.toIntOrNull() ?: throw IllegalArgumentException("Illegal numerator: $this")
    val denominator = split.getOrNull(1)?.toIntOrNull() ?: 1
    return Rational.create(numerator, denominator)
}


fun main() {
    try {
        1 divBy 0
    } catch (e: IllegalArgumentException) {
            e.message eq "Denominator can't be 0"
    }

    -1 divBy 3 eq -(1 divBy 3)
    1 divBy -3 eq -(1 divBy 3)
    -1 divBy -3 eq -(1 divBy 3)

    "1/3".toRational() eq (1 divBy 3)
    "-1/3".toRational() eq -(1 divBy 3)
    "1/-3".toRational() eq -(1 divBy 3)
    "23".toRational() eq (23 divBy 1)

    println("1/3".toRational())
    println("-1/3".toRational())
    println("1/-3".toRational())
    println("23".toRational())

    val oneHalf = Rational.create(1, 2)
    val oneThird = 1 divBy 3

    oneHalf + oneThird eq (5 divBy 6) // 1/2 + 1/3 = 3/6 + 2/6 = 5/6
    oneHalf - oneThird eq (1 divBy 6) // 1/2 - 1/3 = 3/6 - 2/6 = 1/6
    oneThird - oneHalf eq -(1 divBy 6) // 1/3 - 1/2 = 2/6 - 3/6 = -(1/6)
    oneHalf * oneThird eq (1 divBy 6) // 1/2 * 1/3 = 1/6
    oneHalf / oneThird eq (3 divBy 2) // 1/2 / 1/3 = 3/2 = 1 1/2

    val bar = (1..117).filter { 1098 % it == 0 }
    val baz = (1..117).filter { 117 % it == 0 }
    println(bar)
    println(baz)

    val split = "-1/7".split("/")
    val n = split.get(0).toIntOrNull()
    val d = split.getOrElse(1) { 1 }
    println("n $n and d $d")
}