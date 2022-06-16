import java.math.BigInteger
import java.math.BigInteger.ONE
import java.math.BigInteger.ZERO

data class Rational internal constructor(val numerator: BigInteger, val denominator: BigInteger) {
    companion object {
        fun create(numerator: BigInteger, denominator: BigInteger): Rational {
            if (denominator == ZERO) {
                throw IllegalArgumentException("Denominator can't be 0")
            }

            val checkedNumerator = if (numerator > ZERO && denominator < ZERO) -numerator else numerator
            val checkedDenominator = if (numerator < ZERO && denominator > ZERO) -denominator else denominator

            return Rational(checkedNumerator, checkedDenominator)
        }
    }

    override fun toString(): String {
        val denominatorWithoutSign = if (denominator < ZERO) -denominator else denominator
        return if (denominatorWithoutSign == ONE) "$numerator" else "$numerator/$denominatorWithoutSign"
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

private operator fun Rational.compareTo(other: Rational): Int {
    val thisAsBigDecimal = this.numerator.toDouble() / this.denominator.toDouble()
    val otherAsBigDecimal = other.numerator.toDouble() / other.denominator.toDouble()
    return thisAsBigDecimal.compareTo(otherAsBigDecimal)
}

fun Rational?.orDefault(): Rational = this ?: Rational.create(ONE, ONE)

infix fun Number.divBy(denominator: Number): Rational {
    return Rational.create(this.toString().toBigInteger(), denominator.toString().toBigInteger())
}

fun String.toRational(): Rational {
    val split = this.split("/")
    val numerator =
        split.getOrNull(0)?.toBigIntegerOrNull() ?: throw IllegalArgumentException("Illegal numerator: $this")
    val denominator = split.getOrNull(1)?.toBigIntegerOrNull() ?: ONE
    return Rational.create(numerator, denominator)
}


fun main() {
    println("Handle denominator is 0")
    try {
        1 divBy 0
    } catch (e: IllegalArgumentException) {
        e.message eq "Denominator can't be 0"
    }

    println("Handle negative numerator and denominator")
    -1 divBy 3 eq -(1 divBy 3)
    1 divBy -3 eq -(1 divBy 3)
    -1 divBy -3 eq -(1 divBy 3)

    println("Convert string to Rational")
    "1/3".toRational() eq (1 divBy 3)
    "-1/3".toRational() eq -(1 divBy 3)
    "1/-3".toRational() eq -(1 divBy 3)
    "23".toRational() eq (23 divBy 1)

    println("Handle to string for Rational")
    println("1/3".toRational())
    println("-1/3".toRational())
    println("1/-3".toRational())
    println("23".toRational())

    println("Handle long, int and big integer to Rational")
    val oneHalf = 1L divBy 2L
    val oneThird = 1 divBy 3
    val oneQuarter = "1".toBigInteger() divBy "4".toBigInteger()

    println("Handle + - * /")
    oneHalf + oneThird eq (5 divBy 6) // 1/2 + 1/3 = 3/6 + 2/6 = 5/6
    oneHalf - oneThird eq (1 divBy 6) // 1/2 - 1/3 = 3/6 - 2/6 = 1/6
    oneThird - oneHalf eq -(1 divBy 6) // 1/3 - 1/2 = 2/6 - 3/6 = -(1/6)
    oneHalf * oneThird eq (1 divBy 6) // 1/2 * 1/3 = 1/6
    oneHalf / oneThird eq (3 divBy 2) // 1/2 / 1/3 = 3/2 = 1 1/2

    println("Handle comparison of Rational")
    (oneQuarter < oneThird) eq true
    (oneHalf > oneThird) eq true
    (oneThird == (1 divBy 3)) eq true
    (oneHalf == (2 divBy 4)) eq true

    val bar = (1..117).filter { 1098 % it == 0 }
    val baz = (1..117).filter { 117 % it == 0 }
    println(bar)
    println(baz)

    //println(oneThird in oneQuarter..oneHalf)
}