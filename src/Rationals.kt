data class Rational(val numerator: Int, val denominator: Int)

operator fun Rational?.plus(other: Rational?): Rational {
    val thisOrFallback = this ?: Rational(0, 0)
    val otherOrFallback = other ?: Rational(0, 0)
    val thisWithCommonDenominator = Rational(
        thisOrFallback.numerator * otherOrFallback.denominator,
        thisOrFallback.denominator * otherOrFallback.denominator
    )
    val otherWithCommonDenominator = Rational(
        otherOrFallback.numerator * thisOrFallback.denominator,
        otherOrFallback.denominator * thisOrFallback.denominator
    )
    return Rational(thisWithCommonDenominator.numerator + otherWithCommonDenominator.numerator,
        thisWithCommonDenominator.denominator)
}

operator fun Rational?.minus(other: Rational?): Rational {
    val thisOrFallback = this ?: Rational(0, 0)
    val otherOrFallback = other ?: Rational(0, 0)
    val thisWithCommonDenominator = Rational(
        thisOrFallback.numerator * otherOrFallback.denominator,
        thisOrFallback.denominator * otherOrFallback.denominator
    )
    val otherWithCommonDenominator = Rational(
        otherOrFallback.numerator * thisOrFallback.denominator,
        otherOrFallback.denominator * thisOrFallback.denominator
    )
    return Rational(thisWithCommonDenominator.numerator - otherWithCommonDenominator.numerator,
        thisWithCommonDenominator.denominator)
}

operator fun Rational?.times(other: Rational?): Rational {
    val thisOrFallback = this ?: Rational(0, 0)
    val otherOrFallback = other ?: Rational(0, 0)
    return Rational(
        thisOrFallback.numerator * otherOrFallback.numerator,
        thisOrFallback.denominator * otherOrFallback.denominator
    )
}

operator fun Rational?.div(other: Rational?): Rational {
    val thisOrFallback = this ?: Rational(0, 0)
    val otherOrFallback = other ?: Rational(0, 0)
    return Rational(
        thisOrFallback.numerator * otherOrFallback.denominator,
        thisOrFallback.denominator * otherOrFallback.numerator
    )
}

operator fun Rational.unaryMinus() = Rational(-numerator, -denominator)


fun main() {
    val oneHalf = Rational(1, 2)
    val oneThird = Rational(1, 3)

    oneHalf + oneThird eq Rational(5, 6) // 1/2 + 1/3 = 3/6 + 2/6 = 5/6
    oneHalf - oneThird eq Rational(1, 6) // 1/2 - 1/3 = 3/6 - 2/6 = 1/6
    oneThird - oneHalf eq - Rational(1, 6) // 1/3 - 1/2 = 2/6 - 3/6 = -(1/6)
    oneHalf * oneThird eq Rational(1, 6) // 1/2 * 1/3 = 1/6
    oneHalf / oneThird eq Rational(3, 2) // 1/2 / 1/3 = 3/2 = 1 1/2
}