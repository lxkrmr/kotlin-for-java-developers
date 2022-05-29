// without a sealed class we have to add an else as the expression is not exhaustive
interface Expr
class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

fun eval(e: Expr): Int = when (e) {
    is Num -> e.value
    is Sum -> eval(e.left) + eval(e.right)
    else -> 0
}

sealed class ExprSealed
class NumSealed(val value: Int) : ExprSealed()
class SumSealed(val left: ExprSealed, val right: ExprSealed) : ExprSealed()

fun evalSealed(e: ExprSealed): Int = when (e) {
    is NumSealed -> e.value
    is SumSealed -> evalSealed(e.left) + evalSealed(e.right)
}

fun main() {
    val num1 = Num(11)
    println(eval(num1))

    val num2 = Num(12)
    val sum = Sum(num1, num2)
    println(eval(sum))

    val num1Sealed = NumSealed(11)
    println(evalSealed(num1Sealed))

    val num2Sealed = NumSealed(12)
    val sumSealed = SumSealed(num1Sealed, num2Sealed)
    println(evalSealed(sumSealed))
}