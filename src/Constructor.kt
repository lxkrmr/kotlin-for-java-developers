fun main() {
    Child()
    ChildOverride()
}

open class Parent {
    init {
        print("parent ")
    }
}

class Child : Parent() {
    init {
        print("child ")
    }
}

open class ParentOverride {
    open val foo = 1

    init {
        println(foo)
    }
}

class ChildOverride : ParentOverride() {
    override val foo = 2
}