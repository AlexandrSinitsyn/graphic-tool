package alexsin.graphic.tool

import kotlin.math.*

class MultiVec : NArc<MultiVec> {
    
    val n: Int
    var args: DoubleArray private set

    fun args(): IntArray = args.map { it.toInt() }.toIntArray()

    constructor(): this (0)
    constructor(args: DoubleArray) {
        this.args = args
        n = args.size
    }
    constructor(args: IntArray): this(args.map { it.toDouble() }.toDoubleArray())
    constructor(n: Int): this(n, 0)
    constructor(n: Int, default: Int): this(IntArray(n) { default })
    constructor(n: Int, default: Double): this(DoubleArray(n) { default })

    override fun assign(v: MultiVec, fn: (Double, Double) -> Double) {
        args = args.mapIndexed { ind, e -> fn(e, v[ind]) }.toDoubleArray()
    }
    override fun apply(v: MultiVec, fn: (Double, Double) -> Double): MultiVec = MultiVec(args).apply { this.assign(v, fn) }
    override fun replicate(d: Double): MultiVec = MultiVec(n, d)

    override fun scalar(v: MultiVec): Double = args.zip(v.args).map { it.first * it.second }.reduce { total, current -> total + current }

    override fun get(index: Int): Double = when (index) {
        in 0..n -> args[index]
        else -> throw IllegalArgumentException("This MultiVector is only $n-dimensional")
    }
    override fun equals(other: Any?): Boolean = when (other) {
        is MultiVec -> args.zip(other.args).all { it.first == it.second }
        else -> false
    }
    operator fun compareTo(v: MultiVec): Int = when {
        equals(v) -> 0
        not() > !v -> 1
        else -> -1
    }

    override fun toString(): String = "(${args.joinToString { it.toString() }})"
    override fun hashCode(): Int =
        args.mapIndexed { ind, e -> e.hashCode() * (31.0.pow(ind) + ind) }.reduce { t, c -> t + c }.toInt()

    companion object {
        @JvmStatic
        fun of(vararg args: Double): MultiVec = MultiVec(args)
        @JvmStatic
        fun of(vararg args: Int): MultiVec = MultiVec(args)
    }
}

fun MultiVec.toVec(): Vec = Vec(get(0), get(1))
fun Vec.toMultiVec(): MultiVec = MultiVec.of(x, y)
fun MultiVec.toVec3(): Vec3 = Vec3(get(0), get(1), get(2))
fun Vec3.toMultiVec(): MultiVec = MultiVec.of(x, y, z)

fun <E : Number> Array<E>.toMultiVec(): MultiVec = MultiVec(this.map { it.toDouble() }.toDoubleArray())
fun IntArray.toMultiVec(): MultiVec = MultiVec(this)
fun DoubleArray.toMultiVec(): MultiVec = MultiVec(this)
