package alexsin.graphic.tool

import kotlin.math.*

class Vec : NArc<Vec> {

    var x: Double private set
    var y: Double private set

    constructor(x: Double, y: Double) {
        this.x = x
        this.y = y
    }
    constructor(x: Int, y: Int): this(x.toDouble(), y.toDouble())
    constructor(): this(0, 0)

    fun x(): Int = x.toInt()
    fun y(): Int = y.toInt()

    override fun assign(v: Vec, fn: (Double, Double) -> Double) {
        x = fn(x, v.x)
        y = fn(y, v.y)
    }
    override fun apply(v: Vec, fn: (Double, Double) -> Double): Vec = Vec(x, y).apply { this.assign(v, fn) }
    override fun replicate(d: Double): Vec = Vec(d, d)

    fun rotate(angle: Double): Vec {
        val a = angle * PI / 180
        return Vec(cos(a) * x - sin(a) * y, sin(a) * x + cos(a) * y)
    }
    override fun scalar(v: Vec): Double = x * v.x + y * v.y

    override fun get(index: Int): Double = when (index) {
        0 -> x
        1 -> y
        else -> throw IllegalArgumentException("This vector realization supports only two-dimensional vectors")
    }
    override fun equals(other: Any?): Boolean = when (other) {
        is Vec -> x == other.x && y == other.y
        else -> false
    }
    operator fun compareTo(v: Vec): Int = when {
        equals(v) -> 0
        not() > !v -> 1
        else -> -1
    }

    override fun toString(): String = "(${x()}, ${y()})"
    override fun hashCode(): Int = 31 * x.hashCode() + y.hashCode()

    companion object {
        @JvmStatic
        val ZERO = Vec()
        @JvmStatic
        val ONE = Vec(1, 1)
    }
}

fun <A : Number, B : Number> Pair<A, B>.toVec(): Vec = Vec(first.toDouble(), second.toDouble())
fun Vec.toPair(): Pair<Double, Double> = Pair(x, y)
