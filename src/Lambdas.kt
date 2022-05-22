import Gender.FEMALE
import Gender.MALE

fun main() {
    val heroes = listOf(
        Hero("The Captain", 60, MALE),
        Hero("Frenchy", 42, MALE),
        Hero("The Kid", 9, null),
        Hero("Lady Lauren", 29, FEMALE),
        Hero("First Mate", 29, MALE),
        Hero("Sir Stephen", 37, MALE),
    )

    heroes.last().name eq "Sir Stephen"
    heroes.firstOrNull { it.age == 30 }?.name eq null
    heroes.map { it.age }.distinct().size eq 5
    heroes.filter { it.age < 30 }.size eq 3
    val (youngest, oldest) = heroes.partition { it.age < 30 }
    youngest.size eq 3
    oldest.size eq 3
    heroes.maxByOrNull { it.age }?.name eq "The Captain"
    heroes.all { it.age < 50 } eq false
    heroes.any { it.gender == FEMALE } eq true

    val mapByAge = heroes.groupBy { it.age }
    val (age, group) = mapByAge.maxByOrNull { (_, group) -> group.size }!!
    age eq 29

    val mapByName = heroes.associateBy { it.name }
    mapByName["Frenchy"]?.age eq 42

    val unknownHero = Hero("Unknown", 0, null)
    mapByName.getOrElse("unknown") { unknownHero }.age eq 0

    // example for bad code - because of complexity
    // tips:
    // * don't use it if it has different types in neighbouring lines
    // * prefer explicit parameter names if it might be confusing otherwise
    // * learn the library and try to reuse the library functions as much as possible
    val (first, second) = heroes.flatMap { heroes.map { hero -> it to hero } }
        .maxByOrNull { it.first.age - it.second.age }!!
    first.name eq "The Captain"
    second.name eq "The Kid"
    // same code
    val allPossiblePairs = heroes.flatMap { first -> heroes.map { second -> first to second } }
    val (oldestHero, youngestHero) = allPossiblePairs.maxByOrNull { it.first.age - it.second.age }!!
    // the code is looking for the hero pair with the highest age difference
    oldestHero.name eq "The Captain"
    youngestHero.name eq "The Kid"
    // the same code could be reached even simpler by ...
    val oldestHeroSimple = heroes.maxByOrNull { it.age }
    val youngestHeroSimple = heroes.minByOrNull { it.age }
    oldestHeroSimple?.name eq "The Captain"
    youngestHeroSimple?.name eq "The Kid"

}

data class Hero(
    val name: String,
    val age: Int,
    val gender: Gender?
)

enum class Gender { MALE, FEMALE }