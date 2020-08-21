package ru.anti3z.geom

import kotlin.math.hypot

interface Vector2Base<out T : Number> : Tuple2<T> {
    operator fun <R : Number> plus(rhs: Vector2Base<R>): Vector2Base<T>
    operator fun <R : Number> minus(rhs: Vector2Base<R>): Vector2Base<T>
    operator fun unaryMinus(): Vector2Base<T>
    fun scaled(factor: Number): Vector2Base<T>
    fun <R : Number> dot(vec: Vector2Base<R>): T

    operator fun times(rhs: Number) = scaled(rhs)
    operator fun <R : Number> times(rhs: Vector2Base<R>) = dot(rhs)

    val length: Double
        get() = hypot(x.toDouble(), y.toDouble())

    fun lengthened(value: Double) = when {
        length != 0.0 -> scaled(value / length)
        else -> throw Exception("Cannot lengthened ZERO vector")
    }

    fun normalized() = lengthened(1.0)
}

interface MutableVector2Base<T : Number> : Vector2Base<T>, MutableTuple2<T> {
    fun scale(factor: Number)
    override var length: Double
        get() = super.length
        set(value) = when {
            length != 0.0 -> scale(value / length)
            else -> throw Exception("Cannot lengthened ZERO vector")
        }
    fun normalize() = scale(1.0)
}

sealed class Vector2<T : Number> : MutableVector2Base<T> {
    @Suppress("UNCHECKED_CAST")
    override operator fun <R : Number> plus(rhs: Vector2Base<R>) = when (this) {
        is Vector2D -> Vector2D(x + rhs.x.toDouble(), y + rhs.y.toDouble())
        is Vector2F -> Vector2F(x + rhs.x.toFloat(), y + rhs.y.toFloat())
        is Vector2I -> Vector2I(x + rhs.x.toInt(), y + rhs.y.toInt())
    } as Vector2Base<T>

    @Suppress("UNCHECKED_CAST")
    override operator fun <R : Number> minus(rhs: Vector2Base<R>) = when (this) {
        is Vector2D -> Vector2D(x - rhs.x.toDouble(), y - rhs.y.toDouble())
        is Vector2F -> Vector2F(x - rhs.x.toFloat(), y - rhs.y.toFloat())
        is Vector2I -> Vector2I(x - rhs.x.toInt(), y - rhs.y.toInt())
    } as Vector2Base<T>

    @Suppress("UNCHECKED_CAST")
    override operator fun unaryMinus() = when (this) {
        is Vector2D -> Vector2D(-x, -y)
        is Vector2F -> Vector2F(-x, -y)
        is Vector2I -> Vector2I(-x, -y)
    } as Vector2Base<T>

    override fun scale(factor: Number) {
        when (this) {
            is Vector2D -> {
                val factorDouble = factor.toDouble()
                x *= factorDouble
                y *= factorDouble
            }
            is Vector2F -> {
                val factorFloat = factor.toFloat()
                x *= factorFloat
                y *= factorFloat
            }
            is Vector2I -> {
                val factorInt = factor.toInt()
                x *= factorInt
                y *= factorInt
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun scaled(factor: Number) = when (this) {
        is Vector2D -> Vector2D.create(this).apply { scale(factor) }
        is Vector2F -> Vector2F.create(this).apply { scale(factor) }
        is Vector2I -> Vector2I.create(this).apply { scale(factor) }
    } as Vector2Base<T>

    @Suppress("UNCHECKED_CAST")
    override fun <R : Number> dot(vec: Vector2Base<R>): T = when (this) {
        is Vector2D -> (x * vec.x.toDouble() + y * vec.y.toDouble()) as T
        is Vector2F -> (x * vec.x.toFloat() + y * vec.y.toFloat()) as T
        is Vector2I -> (x * vec.x.toInt() + y * vec.y.toInt()) as T
    }

    override fun toDouble() = when (this) {
        is Vector2D -> this
        else -> Vector2D(x, y)
    }

    override fun toFloat() = when (this) {
        is Vector2F -> this
        else -> Vector2F(x, y)
    }

    override fun toInt() = when (this) {
        is Vector2I -> this
        else -> Vector2I(x, y)
    }
}

data class Vector2D(override var x: Double = 0.0, override var y: Double = 0.0) : Vector2<Double>() {
    constructor(x: Number, y: Number) : this(x.toDouble(), y.toDouble())

    override operator fun <R : Number> plus(rhs: Vector2Base<R>) = super.plus(rhs) as Vector2D
    override operator fun <R : Number> minus(rhs: Vector2Base<R>) = super.minus(rhs) as Vector2D
    override operator fun unaryMinus() = super.unaryMinus() as Vector2D
    override fun scaled(factor: Number) = super.scaled(factor) as Vector2D

    //    override fun <R : Number> dot(vec: Vector2Base<R>) = super.dot(vec) as Double
    override operator fun times(rhs: Number) = super.times(rhs) as Vector2D

    //    override operator fun <R : Number> times(rhs: Vector2Base<R>) = super.times(rhs) as Double
    override fun lengthened(value: Double) = super.lengthened(value) as Vector2D
    override fun normalized() = super.normalized() as Vector2D

    companion object {
        fun <T : Number> create(src: Tuple2<T>) = Vector2D(src.x, src.y)
    }
}

data class Vector2F(override var x: Float = 0.0f, override var y: Float = 0.0f) : Vector2<Float>() {
    constructor(x: Number, y: Number) : this(x.toFloat(), y.toFloat())

    override operator fun <R : Number> plus(rhs: Vector2Base<R>) = super.plus(rhs) as Vector2F
    override operator fun <R : Number> minus(rhs: Vector2Base<R>) = super.minus(rhs) as Vector2F
    override operator fun unaryMinus() = super.unaryMinus() as Vector2F
    override fun scaled(factor: Number) = super.scaled(factor) as Vector2F

    //    override fun <R : Number> dot(vec: Vector2Base<R>) = super.dot(vec) as Float
    override operator fun times(rhs: Number) = super.times(rhs) as Vector2F

    //    override operator fun <R : Number> times(rhs: Vector2Base<R>) = super.times(rhs) as Float
    override fun lengthened(value: Double) = super.lengthened(value) as Vector2F
    override fun normalized() = super.normalized() as Vector2F

    companion object {
        fun <T : Number> create(src: Tuple2<T>) = Vector2F(src.x, src.y)
    }
}

data class Vector2I(override var x: Int = 0, override var y: Int = 0) : Vector2<Int>() {
    constructor(x: Number, y: Number) : this(x.toInt(), y.toInt())

    override operator fun <R : Number> plus(rhs: Vector2Base<R>) = super.plus(rhs) as Vector2I
    override operator fun <R : Number> minus(rhs: Vector2Base<R>) = super.minus(rhs) as Vector2I
    override operator fun unaryMinus() = super.unaryMinus() as Vector2I
    override fun scaled(factor: Number) = super.scaled(factor) as Vector2I

    //    override fun <R : Number> dot(vec: Vector2Base<R>) = super.dot(vec) as Int
    override operator fun times(rhs: Number) = super.times(rhs) as Vector2I

    //    override operator fun <R : Number> times(rhs: Vector2Base<R>) = super.times(rhs) as Int
    override fun lengthened(value: Double) = super.lengthened(value) as Vector2I
    override fun normalized() = super.normalized() as Vector2I

    companion object {
        fun <T : Number> create(src: Tuple2<T>) = Vector2I(src.x, src.y)
    }
}
