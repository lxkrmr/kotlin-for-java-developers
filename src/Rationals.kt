data class Rational private constructor(val numerator: Int, val denominator: Int) {
    companion object {
        fun create(numerator: Int, denominator: Int): Rational {
            val checkedNumerator = if (numerator > 0 && denominator < 0) -numerator else numerator
            val checkedDenominator = if (numerator < 0 && denominator > 0) -denominator else denominator

            return Rational(checkedNumerator, checkedDenominator)
        }
    }
}

operator fun Rational?.plus(other: Rational?): Rational {
    val thisOrFallback = this ?: Rational.create(0, 0)
    val otherOrFallback = other ?: Rational.create(0, 0)
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
    val thisOrFallback = this ?: Rational.create(0, 0)
    val otherOrFallback = other ?: Rational.create(0, 0)
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
    val thisOrFallback = this ?: Rational.create(0, 0)
    val otherOrFallback = other ?: Rational.create(0, 0)
    return Rational.create(
        thisOrFallback.numerator * otherOrFallback.numerator,
        thisOrFallback.denominator * otherOrFallback.denominator
    )
}

operator fun Rational?.div(other: Rational?): Rational {
    val thisOrFallback = this ?: Rational.create(0, 0)
    val otherOrFallback = other ?: Rational.create(0, 0)
    return Rational.create(
        thisOrFallback.numerator * otherOrFallback.denominator,
        thisOrFallback.denominator * otherOrFallback.numerator
    )
}

operator fun Rational.unaryMinus() = Rational.create(-numerator, -denominator)

infix fun Int.divBy(denominator: Int): Rational {
    return Rational.create(this, denominator)
}


fun main() {
    val oneHalf = Rational.create(1, 2)
    val oneThird = 1 divBy 3

    oneHalf + oneThird eq (5 divBy 6) // 1/2 + 1/3 = 3/6 + 2/6 = 5/6
    oneHalf - oneThird eq (1 divBy 6) // 1/2 - 1/3 = 3/6 - 2/6 = 1/6
    oneThird - oneHalf eq -(1 divBy 6) // 1/3 - 1/2 = 2/6 - 3/6 = -(1/6)
    oneHalf * oneThird eq (1 divBy 6) // 1/2 * 1/3 = 1/6
    oneHalf / oneThird eq (3 divBy 2) // 1/2 / 1/3 = 3/2 = 1 1/2
}