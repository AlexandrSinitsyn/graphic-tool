package alexsin.graphic.tool

import kotlin.math.*

class Vec3 : NArc<Vec3> {

    var x: Double private set
    var y: Double private set
    var z: Double private set

    constructor(x: Double, y: Double, z: Double) {
        this.x = x
        this.y = y
        this.z = z
    }
    constructor(x: Int, y: Int, z: Int): this(x.toDouble(), y.toDouble(), z.toDouble())
    constructor(): this(0, 0, 0)

    fun x(): Int = x.toInt()
    fun y(): Int = y.toInt()
    fun z(): Int = z.toInt()

    override fun assign(v: Vec3, fn: (Double, Double) -> Double) {
        x = fn(x, v.x)
        y = fn(y, v.y)
        z = fn(z, v.z)
    }
    override fun apply(v: Vec3, fn: (Double, Double) -> Double): Vec3 = Vec3(x, y, z).apply { this.assign(v, fn) }
    override fun replicate(d: Double): Vec3 = Vec3(d, d, d)

    fun rotateX(angle: Double): Vec3 {
        val a = angle * PI / 180
        return Vec3(x, cos(a) * y - sin(a) * z, sin(a) * y + cos(a) * z)
    }
    fun rotateY(angle: Double): Vec3 {
        val a = angle * PI / 180
        return Vec3(cos(a) * x + sin(a) * z, y, -sin(a) * x + cos(a) * z)
    }
    fun rotateZ(angle: Double): Vec3 {
        val a = angle * PI / 180
        return Vec3(cos(a) * x - sin(a) * y, sin(a) * x + cos(a) * y, z)
    }
    fun rotate(aX: Double, aY: Double, aZ: Double) = rotateX(aX).rotateY(aY).rotateZ(aZ)
    override fun scalar(v: Vec3): Double = x * v.x + y * v.y

    override fun get(index: Int): Double = when (index) {
        0 -> x
        1 -> y
        2 -> z
        else -> throw IllegalArgumentException("This vector realization supports only two-dimensional vectors")
    }
    override fun equals(other: Any?): Boolean = when (other) {
        is Vec3 -> x == other.x && y == other.y && z == other.z
        else -> false
    }
    operator fun compareTo(v: Vec3): Int = when {
        equals(v) -> 0
        not() > !v -> 1
        else -> -1
    }

    override fun toString(): String = "(${x()}, ${y()}, ${z()})"
    override fun hashCode(): Int = 31 * x.hashCode() + y.hashCode()

    companion object {
        @JvmStatic
        val ZERO = Vec3()
        @JvmStatic
        val ONE = Vec3(1, 1, 1)
    }
}

operator fun Vec3.times(v: Vec3): Vec3 = Vec3(y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y * v.z)
operator fun Vec.times(v: Vec): Vec3 = this.toVec3() * v.toVec3()

fun <A : Number, B : Number, C: Number> Triple<A, B, C>.toVec3(): Vec3 = Vec3(first.toDouble(), second.toDouble(), third.toDouble())
fun Vec3.toTriple(): Triple<Double, Double, Double> = Triple(x, y, z)
fun Vec3.toVec(): Vec = Vec(x, y)
fun Vec.toVec3(): Vec3 = Vec3(x, y, 0.0)
