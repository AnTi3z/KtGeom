package ru.anti3z.geom

import kotlin.math.hypot

fun <T : Number> Tuple2<T>.toVector2(): Vector2<T> {
    @Suppress("UNCHECKED_CAST")
    return when (this.x) {
        is Double -> Vector2D(this)
        is Float -> Vector2F(this)
        is Int -> Vector2I(this)
        else -> throw IllegalArgumentException()
    } as Vector2<T>
}

fun <T : Number> Tuple2<T>.toMutableVector2(): MutableVector2<T> {
    @Suppress("UNCHECKED_CAST")
    return when (this.x) {
        is Double -> Vector2D(this)
        is Float -> Vector2F(this)
        is Int -> Vector2I(this)
        else -> throw IllegalArgumentException()
    } as MutableVector2<T>
}

interface Vector2<out T : Number> : Tuple2<T> {
    operator fun plus(rhs: Vector2<*>): Vector2<T>
    operator fun minus(rhs: Vector2<*>): Vector2<T>

    operator fun times(rhs: Number): Vector2<T> = scaled(rhs.toDouble())
    operator fun div(rhs: Number): Vector2<T> = scaled(1.0 / rhs.toDouble())

    operator fun unaryMinus(): Vector2<T>
    fun scaled(factor: Double): Vector2<T>

    fun dot(vec: Vector2<*>): T
    operator fun times(rhs: Vector2<*>): T = dot(rhs)

    val length: Double
        get() = hypot(x.toDouble(), y.toDouble())

    fun lengthened(value: Double): Vector2<T> {
        require(value > 0.0) { "Length must be positive non-zero value, was $value" }
        return scaled(value / length)
    }

    fun normalized(): Vector2<T> = scaled(1.0 / length)

    override fun toDouble(): Vector2<Double>
    override fun toFloat(): Vector2<Float>
    override fun toInt(): Vector2<Int>
}

interface MutableVector2<T : Number> : Vector2<T>, MutableTuple2<T> {
    override operator fun plus(rhs: Vector2<*>): MutableVector2<T>
    override operator fun minus(rhs: Vector2<*>): MutableVector2<T>

    operator fun plusAssign(rhs: Vector2<*>) = set(this + rhs)
    operator fun minusAssign(rhs: Vector2<*>) = set(this - rhs)

    override operator fun times(rhs: Number): MutableVector2<T> = super.times(rhs) as MutableVector2<T>
    override operator fun div(rhs: Number): MutableVector2<T> = super.div(rhs) as MutableVector2<T>

    operator fun timesAssign(rhs: Number)
    operator fun divAssign(rhs: Number)

    override operator fun unaryMinus(): MutableVector2<T>
    override fun scaled(factor: Double): MutableVector2<T>

    fun scale(factor: Double)
    override var length: Double
        get() = super.length
        set(value) {
            require(value > 0.0) { "Length must be positive non-zero value, was $value" }
            scale(value / length)
        }

    fun normalize() = scale(1.0 / length)

    override fun toDouble(): MutableVector2<Double>
    override fun toFloat(): MutableVector2<Float>
    override fun toInt(): MutableVector2<Int>
}

sealed class Vector2Base<T : Number> : MutableVector2<T> {
    @Suppress("UNCHECKED_CAST")
    override operator fun plus(rhs: Vector2<*>): MutableVector2<T> = when (this) {
        is Vector2D -> Vector2D(x + rhs.x.toDouble(), y + rhs.y.toDouble())
        is Vector2F -> Vector2F(x + rhs.x.toFloat(), y + rhs.y.toFloat())
        is Vector2I -> Vector2I(x + rhs.x.toInt(), y + rhs.y.toInt())
    } as MutableVector2<T>

    @Suppress("UNCHECKED_CAST")
    override operator fun minus(rhs: Vector2<*>): MutableVector2<T> = when (this) {
        is Vector2D -> Vector2D(x - rhs.x.toDouble(), y - rhs.y.toDouble())
        is Vector2F -> Vector2F(x - rhs.x.toFloat(), y - rhs.y.toFloat())
        is Vector2I -> Vector2I(x - rhs.x.toInt(), y - rhs.y.toInt())
    } as MutableVector2<T>

    @Suppress("UNCHECKED_CAST")
    override operator fun unaryMinus(): MutableVector2<T> = when (this) {
        is Vector2D -> Vector2D(-x, -y)
        is Vector2F -> Vector2F(-x, -y)
        is Vector2I -> Vector2I(-x, -y)
    } as MutableVector2<T>

    override fun scale(factor: Double) {
        when (this) {
            is Vector2D -> {
                x *= factor
                y *= factor
            }
            is Vector2F -> {
                x = (x * factor).toFloat()
                y = (y * factor).toFloat()
            }
            is Vector2I -> {
                x = (x * factor).toInt()
                y = (y * factor).toInt()
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun scaled(factor: Double): MutableVector2<T> = when (this) {
        is Vector2D -> Vector2D(x * factor, y * factor)
        is Vector2F -> Vector2F(x * factor, y * factor)
        is Vector2I -> Vector2D(x * factor, y * factor)
    } as MutableVector2<T>

    @Suppress("UNCHECKED_CAST")
    override fun dot(vec: Vector2<*>): T = when (this) {
        is Vector2D -> x * vec.x.toDouble() + y * vec.y.toDouble()
        is Vector2F -> x * vec.x.toFloat() + y * vec.y.toFloat()
        is Vector2I -> x * vec.x.toInt() + y * vec.y.toInt()
    } as T

    override fun timesAssign(rhs: Number) = scale(rhs.toDouble())

    override fun divAssign(rhs: Number) = scale(1.0 / rhs.toDouble())

    override fun toDouble(): MutableVector2<Double> = when (this) {
        is Vector2D -> this
        else -> Vector2D(x, y)
    }

    override fun toFloat(): MutableVector2<Float> = when (this) {
        is Vector2F -> this
        else -> Vector2F(x, y)
    }

    override fun toInt(): MutableVector2<Int> = when (this) {
        is Vector2I -> this
        else -> Vector2I(x, y)
    }

    override fun set(src: Tuple2<*>) {
        when (this) {
            is Vector2D -> {
                x = src.x.toDouble()
                y = src.y.toDouble()
            }
            is Vector2F -> {
                x = src.x.toFloat()
                y = src.y.toFloat()
            }
            is Vector2I -> {
                x = src.x.toInt()
                y = src.y.toInt()
            }
        }
    }

    override fun set(x: Number, y: Number) {
        when (this) {
            is Vector2D -> {
                this.x = x.toDouble()
                this.y = y.toDouble()
            }
            is Vector2F -> {
                this.x = x.toFloat()
                this.y = y.toFloat()
            }
            is Vector2I -> {
                this.x = x.toInt()
                this.y = y.toInt()
            }
        }
    }
}

data class Vector2D(override var x: Double = 0.0, override var y: Double = 0.0) : Vector2Base<Double>() {
    constructor(x: Number, y: Number) : this(x.toDouble(), y.toDouble())
    constructor(src: Tuple2<*>) : this(src.x, src.y)
}

data class Vector2F(override var x: Float = 0.0f, override var y: Float = 0.0f) : Vector2Base<Float>() {
    constructor(x: Number, y: Number) : this(x.toFloat(), y.toFloat())
    constructor(src: Tuple2<*>) : this(src.x, src.y)
}

data class Vector2I(override var x: Int = 0, override var y: Int = 0) : Vector2Base<Int>() {
    constructor(x: Number, y: Number) : this(x.toInt(), y.toInt())
    constructor(src: Tuple2<*>) : this(src.x, src.y)
}
