package lazy.initialization.examples.data

/**
 * @author a.taganov
 */
data class BigClass(
    val strings: Strings = Strings(),
    val ints: Ints = Ints(),
    val longs: Longs = Longs(),
    val doubles: Doubles = Doubles(),
    val floats: Floats = Floats(),
    val booleans: Booleans = Booleans(),
    val internals: List<Internal> = List(10) { Internal() }
) {
    data class Internal(
        val strings: Strings = Strings(),
        val ints: Ints = Ints(),
        val longs: Longs = Longs(),
        val doubles: Doubles = Doubles(),
        val floats: Floats = Floats(),
        val booleans: Booleans = Booleans(),
    )

    data class Strings(
        val a: String = "a",
        val b: String = "b",
        val c: String = "c",
        val d: String = "d",
        val e: String = "e",
        val f: String = "f",
        val g: String = "g",
        val h: String = "h",
        val i: String = "i",
        val j: String = "j",
    )
    data class Ints(
        val a: Int = 1,
        val b: Int = 2,
        val c: Int = 3,
        val d: Int = 4,
        val e: Int = 5,
        val f: Int = 1,
        val g: Int = 2,
        val h: Int = 3,
        val i: Int = 4,
        val j: Int = 5,
    )
    data class Longs(
        val a: Long = 1,
        val b: Long = 2,
        val c: Long = 3,
        val d: Long = 4,
        val e: Long = 5,
        val f: Long = 1,
        val g: Long = 2,
        val h: Long = 3,
        val i: Long = 4,
        val j: Long = 5,
    )
    data class Doubles(
        val a: Double = 1.0,
        val b: Double = 2.0,
        val c: Double = 3.0,
        val d: Double = 4.0,
        val e: Double = 5.0,
        val f: Double = 1.0,
        val g: Double = 2.0,
        val h: Double = 3.0,
        val i: Double = 4.0,
        val j: Double = 5.0,
    )
    data class Floats(
        val a: Float = 1.0f,
        val b: Float = 2.0f,
        val c: Float = 3.0f,
        val d: Float = 4.0f,
        val e: Float = 5.0f,
        val f: Float = 1.0f,
        val g: Float = 2.0f,
        val h: Float = 3.0f,
        val i: Float = 4.0f,
        val j: Float = 5.0f,
    )
    data class Booleans(
        val a: Boolean = true,
        val b: Boolean = true,
        val c: Boolean = true,
        val d: Boolean = true,
        val e: Boolean = true,
        val f: Boolean = true,
        val g: Boolean = true,
        val h: Boolean = true,
        val i: Boolean = true,
        val j: Boolean = true,
    )
}
