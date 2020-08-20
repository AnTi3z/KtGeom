package ru.anti3z.geom

import kotlin.math.hypot

sealed class Vector2Base<T : Number> : Tuple2<T> {
    open operator fun <R : Number> plus(rhs: Vector2Base<R>) = when (this) {
        is Vector2D -> Vector2D(x + rhs.x.toDouble(), y + rhs.y.toDouble())
        is Vector2F -> Vector2F(x + rhs.x.toFloat(), y + rhs.y.toFloat())
        is Vector2I -> Vector2I(x + rhs.x.toInt(), y + rhs.y.toInt())
    }

    open operator fun <R : Number> minus(rhs: Vector2Base<R>) = when (this) {
        is Vector2D -> Vector2D(x - rhs.x.toDouble(), y - rhs.y.toDouble())
        is Vector2F -> Vector2F(x - rhs.x.toFloat(), y - rhs.y.toFloat())
        is Vector2I -> Vector2I(x - rhs.x.toInt(), y - rhs.y.toInt())
    }

    open operator fun unaryMinus() = when (this) {
        is Vector2D -> Vector2D(-x, -y)
        is Vector2F -> Vector2F(-x, -y)
        is Vector2I -> Vector2I(-x, -y)
    }

    fun scale(factor: Number) {
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

    open fun scaled(factor: Number) = when (this) {
        is Vector2D -> Vector2D.create(this).apply { scale(factor) }
        is Vector2F -> Vector2F.create(this).apply { scale(factor) }
        is Vector2I -> Vector2I.create(this).apply { scale(factor) }
    }

    open fun <R : Number> dot(vec: Vector2Base<R>): Number = when (this) {
        is Vector2D -> (x * vec.x.toDouble() + y * vec.y.toDouble())
        is Vector2F -> (x * vec.x.toFloat() + y * vec.y.toFloat())
        is Vector2I -> (x * vec.x.toInt() + y * vec.y.toInt())
    }

    open operator fun times(rhs: Number) = scaled(rhs)
    open operator fun <R : Number> times(rhs: Vector2Base<R>) = dot(rhs)

    open fun lengthened(value: Double) = when {
        length != 0.0 -> scaled(value / length)
        else -> throw Exception("Cannot lengthened ZERO vector")
    }

    open fun normalized() = lengthened(1.0)

    var length: Double
        get() = when (this) {
            is Vector2D -> hypot(x, y)
            else -> hypot(x.toDouble(), y.toDouble())
        }
        set(value) = when {
            length != 0.0 -> scale(value / length)
            else -> throw Exception("Cannot lengthened ZERO vector")
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

data class Vector2D(override var x: Double = 0.0, override var y: Double = 0.0) : Vector2Base<Double>() {
    constructor(x: Number, y: Number) : this(x.toDouble(), y.toDouble())

    override operator fun <R : Number> plus(rhs: Vector2Base<R>) = super.plus(rhs) as Vector2D
    override operator fun <R : Number> minus(rhs: Vector2Base<R>) = super.minus(rhs) as Vector2D
    override operator fun unaryMinus() = super.unaryMinus() as Vector2D
    override fun scaled(factor: Number) = super.scaled(factor) as Vector2D
    override fun <R : Number> dot(vec: Vector2Base<R>) = super.dot(vec) as Double
    override operator fun times(rhs: Number) = super.times(rhs) as Vector2D
    override operator fun <R : Number> times(rhs: Vector2Base<R>) = super.times(rhs) as Double
    override fun lengthened(value: Double) = super.lengthened(value) as Vector2D
    override fun normalized() = super.normalized() as Vector2D

    companion object {
        fun <T : Number> create(src: Tuple2<T>) = Vector2D(src.x, src.y)
    }
}

data class Vector2F(override var x: Float = 0.0f, override var y: Float = 0.0f) : Vector2Base<Float>() {
    constructor(x: Number, y: Number) : this(x.toFloat(), y.toFloat())

    override operator fun <R : Number> plus(rhs: Vector2Base<R>) = super.plus(rhs) as Vector2F
    override operator fun <R : Number> minus(rhs: Vector2Base<R>) = super.minus(rhs) as Vector2F
    override operator fun unaryMinus() = super.unaryMinus() as Vector2F
    override fun scaled(factor: Number) = super.scaled(factor) as Vector2F
    override fun <R : Number> dot(vec: Vector2Base<R>) = super.dot(vec) as Float
    override operator fun times(rhs: Number) = super.times(rhs) as Vector2F
    override operator fun <R : Number> times(rhs: Vector2Base<R>) = super.times(rhs) as Float
    override fun lengthened(value: Double) = super.lengthened(value) as Vector2F
    override fun normalized() = super.normalized() as Vector2F

    companion object {
        fun <T : Number> create(src: Tuple2<T>) = Vector2F(src.x, src.y)
    }
}

data class Vector2I(override var x: Int = 0, override var y: Int = 0) : Vector2Base<Int>() {
    constructor(x: Number, y: Number) : this(x.toInt(), y.toInt())

    override operator fun <R : Number> plus(rhs: Vector2Base<R>) = super.plus(rhs) as Vector2I
    override operator fun <R : Number> minus(rhs: Vector2Base<R>) = super.minus(rhs) as Vector2I
    override operator fun unaryMinus() = super.unaryMinus() as Vector2I
    override fun scaled(factor: Number) = super.scaled(factor) as Vector2I
    override fun <R : Number> dot(vec: Vector2Base<R>) = super.dot(vec) as Int
    override operator fun times(rhs: Number) = super.times(rhs) as Vector2I
    override operator fun <R : Number> times(rhs: Vector2Base<R>) = super.times(rhs) as Int
    override fun lengthened(value: Double) = super.lengthened(value) as Vector2I
    override fun normalized() = super.normalized() as Vector2I

    companion object {
        fun <T : Number> create(src: Tuple2<T>) = Vector2I(src.x, src.y)
    }
}
