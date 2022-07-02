package alexsin.graphic.tool

import kotlin.math.*

abstract class NArc<T : NArc<T>> {

    protected abstract fun assign(v: T, fn: (Double, Double) -> Double)
    protected abstract fun apply(v: T, fn: (Double, Double) -> Double): T
    protected abstract fun replicate(d: Double): T

    operator fun plus(v: T): T = apply(v) { a, b -> a + b }
    operator fun minus(v: T): T = apply(v) { a, b -> a - b }
    operator fun times(d: Double): T = apply(replicate(d)) { a, b -> a * b }
    operator fun times(d: Int): T = times(d.toDouble())
    operator fun div(d: Double): T = times(1.0 / d)
    operator fun div(d: Int): T = div(d.toDouble())
    operator fun unaryMinus(): T = times(-1)

    operator fun plusAssign(v: T) = assign(v) { a, b -> a + b }
    operator fun minusAssign(v: T) = assign(v) { a, b -> a - b }
    operator fun timesAssign(d: Double) = assign(replicate(d)) { a, b -> a * b }
    operator fun timesAssign(d: Int) = timesAssign(d.toDouble())
    operator fun divAssign(d: Double) = timesAssign(1.0 / d)
    operator fun divAssign(d: Int) = divAssign(d.toDouble())

    abstract fun scalar(v: T): Double
    @Suppress("UNCHECKED_CAST")
    fun length(): Double = sqrt(scalar(this as T))
    fun normalize(): T = if (length() == 0.0) replicate(0.0) else div(length())

    abstract operator fun get(index: Int): Double
    operator fun not(): Double = length()
    fun angle(v: T): Double = acos(scalar(v) / (length() * !v)) * 180 / PI
    abstract override operator fun equals(other: Any?): Boolean
    abstract override fun hashCode(): Int
}
