package ru.anti3z.geom

import kotlin.math.hypot

interface Vector2<out T : Number> : Tuple2<T> {
    operator fun <P : Number> plus(rhs: Vector2<P>): Vector2<T>
    operator fun <P : Number> minus(rhs: Vector2<P>): Vector2<T>
    operator fun unaryMinus(): Vector2<T>
    fun scaled(factor: Number): Vector2<T>
    fun <P : Number> dot(vec: Vector2<P>): T

    operator fun times(rhs: Number) = scaled(rhs)
    operator fun <P : Number> times(rhs: Vector2<P>) = dot(rhs)

    operator fun div(rhs: Number) = scaled(1 / rhs.toDouble())

    val length: Double
        get() = hypot(x.toDouble(), y.toDouble())

    fun lengthened(value: Double) = when {
        length != 0.0 -> scaled(value / length)
        else -> throw Exception("Cannot lengthened ZERO vector")
    }

    fun normalized() = lengthened(1.0)

    override fun toDouble(): Vector2<Double>
    override fun toFloat(): Vector2<Float>
    override fun toInt(): Vector2<Int>

    fun toPoint(): Point2<T>
}

interface MutableVector2<T : Number> : Vector2<T>, MutableTuple2<T> {
    operator fun <P : Number> plusAssign(rhs: Vector2<P>)
    operator fun <P : Number> minusAssign(rhs: Vector2<P>)

    fun scale(factor: Number)
    override var length: Double
        get() = super.length
        set(value) = when {
            length != 0.0 -> scale(value / length)
            else -> throw Exception("Cannot lengthened ZERO vector")
        }

    fun normalize() = scale(1.0)
}

sealed class Vector2Base<T : Number> : MutableVector2<T> {
    @Suppress("UNCHECKED_CAST")
    override operator fun <P : Number> plus(rhs: Vector2<P>): MutableVector2<T> = when (this) {
        is Vector2D -> Vector2D(x + rhs.x.toDouble(), y + rhs.y.toDouble())
        is Vector2F -> Vector2F(x + rhs.x.toFloat(), y + rhs.y.toFloat())
        is Vector2I -> Vector2I(x + rhs.x.toInt(), y + rhs.y.toInt())
    } as Vector2Base<T>

    override operator fun <P : Number> plusAssign(rhs: Vector2<P>) {
        when (this) {
            is Vector2D -> {
                x += rhs.x.toDouble()
                y += rhs.y.toDouble()
            }
            is Vector2F -> {
                x += rhs.x.toFloat()
                y += rhs.y.toFloat()
            }
            is Vector2I -> {
                x += rhs.x.toInt()
                y += rhs.y.toInt()
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override operator fun <P : Number> minus(rhs: Vector2<P>): MutableVector2<T> = when (this) {
        is Vector2D -> Vector2D(x - rhs.x.toDouble(), y - rhs.y.toDouble())
        is Vector2F -> Vector2F(x - rhs.x.toFloat(), y - rhs.y.toFloat())
        is Vector2I -> Vector2I(x - rhs.x.toInt(), y - rhs.y.toInt())
    } as Vector2Base<T>

    override operator fun <P : Number> minusAssign(rhs: Vector2<P>) {
        when (this) {
            is Vector2D -> {
                x -= rhs.x.toDouble()
                y -= rhs.y.toDouble()
            }
            is Vector2F -> {
                x -= rhs.x.toFloat()
                y -= rhs.y.toFloat()
            }
            is Vector2I -> {
                x -= rhs.x.toInt()
                y -= rhs.y.toInt()
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override operator fun unaryMinus(): MutableVector2<T> = when (this) {
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
    override fun scaled(factor: Number): MutableVector2<T> = when (this) {
        is Vector2D -> {
            val factorDouble = factor.toDouble()
            val scaledX = x * factorDouble
            val scaledY = y * factorDouble
            Vector2D(scaledX, scaledY)
        }
        is Vector2F -> {
            val factorDouble = factor.toDouble()
            val scaledX = x * factorDouble
            val scaledY = y * factorDouble
            Vector2F(scaledX, scaledY)
        }
        is Vector2I -> {
            val factorDouble = factor.toDouble()
            val scaledX = x * factorDouble
            val scaledY = y * factorDouble
            Vector2D(scaledX, scaledY)
        }
    } as Vector2Base<T>

    @Suppress("UNCHECKED_CAST")
    override fun <P : Number> dot(vec: Vector2<P>): T = when (this) {
        is Vector2D -> (x * vec.x.toDouble() + y * vec.y.toDouble()) as T
        is Vector2F -> (x * vec.x.toFloat() + y * vec.y.toFloat()) as T
        is Vector2I -> (x * vec.x.toInt() + y * vec.y.toInt()) as T
    }

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

    @Suppress("UNCHECKED_CAST")
    override fun toPoint(): MutablePoint2<T> = when (this) {
        is Vector2D -> Point2D(x, y)
        is Vector2F -> Point2F(x, y)
        is Vector2I -> Point2I(x, y)
    } as Point2Base<T>
}

data class Vector2D(override var x: Double = 0.0, override var y: Double = 0.0) : Vector2Base<Double>() {
    constructor(x: Number, y: Number) : this(x.toDouble(), y.toDouble())
    constructor(src: Tuple2<Number>) : this(src.x, src.y)

    override operator fun <R : Number> plus(rhs: Vector2<R>) = super.plus(rhs) as Vector2D
    override operator fun <R : Number> minus(rhs: Vector2<R>) = super.minus(rhs) as Vector2D
    override operator fun unaryMinus() = super.unaryMinus() as Vector2D
    override fun scaled(factor: Number) = super.scaled(factor) as Vector2D

    override operator fun times(rhs: Number) = super.times(rhs) as Vector2D
    override operator fun div(rhs: Number) = super.times(rhs) as Vector2D

    override fun lengthened(value: Double) = super.lengthened(value) as Vector2D
    override fun normalized() = super.normalized() as Vector2D
}

data class Vector2F(override var x: Float = 0.0f, override var y: Float = 0.0f) : Vector2Base<Float>() {
    constructor(x: Number, y: Number) : this(x.toFloat(), y.toFloat())
    constructor(src: Tuple2<Number>) : this(src.x, src.y)

    override operator fun <R : Number> plus(rhs: Vector2<R>) = super.plus(rhs) as Vector2F
    override operator fun <R : Number> minus(rhs: Vector2<R>) = super.minus(rhs) as Vector2F
    override operator fun unaryMinus() = super.unaryMinus() as Vector2F
    override fun scaled(factor: Number) = super.scaled(factor) as Vector2F

    override operator fun times(rhs: Number) = super.times(rhs) as Vector2F
    override operator fun div(rhs: Number) = super.times(rhs) as Vector2F

    override fun lengthened(value: Double) = super.lengthened(value) as Vector2F
    override fun normalized() = super.normalized() as Vector2F
}

data class Vector2I(override var x: Int = 0, override var y: Int = 0) : Vector2Base<Int>() {
    constructor(x: Number, y: Number) : this(x.toInt(), y.toInt())
    constructor(src: Tuple2<Number>) : this(src.x, src.y)

    override operator fun <R : Number> plus(rhs: Vector2<R>) = super.plus(rhs) as Vector2I
    override operator fun <R : Number> minus(rhs: Vector2<R>) = super.minus(rhs) as Vector2I
    override operator fun unaryMinus() = super.unaryMinus() as Vector2I
    override fun scaled(factor: Number) = super.scaled(factor) as Vector2I

    override operator fun times(rhs: Number) = super.times(rhs) as Vector2I
    override operator fun div(rhs: Number) = super.times(rhs) as Vector2I

    override fun lengthened(value: Double) = super.lengthened(value) as Vector2I
    override fun normalized() = super.normalized() as Vector2I
}
