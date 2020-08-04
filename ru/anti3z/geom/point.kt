package ru.anti3z.geom

sealed class Point2Base<out T : Number> : Tuple2<T> {
    open operator fun <R : Number> plus(rhs: Vector2Base<R>) = when (this) {
        is Point2D -> Point2D(x + rhs.x.toDouble(), y + rhs.y.toDouble())
        is Point2F -> Point2F(x + rhs.x.toFloat(), y + rhs.y.toFloat())
        is Point2I -> Point2I(x + rhs.x.toInt(), y + rhs.y.toInt())
    }

    open operator fun <R : Number> minus(rhs: Vector2Base<R>) = when (this) {
        is Point2D -> Point2D(x - rhs.x.toDouble(), y - rhs.y.toDouble())
        is Point2F -> Point2F(x - rhs.x.toFloat(), y - rhs.y.toFloat())
        is Point2I -> Point2I(x - rhs.x.toInt(), y - rhs.y.toInt())
    }

    override fun toDouble() = when(this) {
        is Point2D -> this
        else -> Point2D(x, y)
    }

    override fun toFloat() = when(this) {
        is Point2F -> this
        else -> Point2F(x, y)
    }

    override fun toInt() = when(this) {
        is Point2I -> this
        else -> Point2I(x, y)
    }
}

data class Point2D(override var x: Double = 0.0, override var y: Double = 0.0) : Point2Base<Double>(), Tuple2<Double> {
    constructor(x: Number, y: Number) : this(x.toDouble(), y.toDouble())

    override operator fun <R : Number> plus(rhs: Vector2Base<R>) = super.plus(rhs) as Point2D
    override operator fun <R : Number> minus(rhs: Vector2Base<R>) = super.minus(rhs) as Point2D

    companion object {
        fun <T : Number> create(src: Tuple2<T>) = Point2D(src.x, src.y)
    }
}

data class Point2F(override var x: Float = 0.0f, override var y: Float = 0.0f) : Point2Base<Float>() {
    constructor(x: Number, y: Number) : this(x.toFloat(), y.toFloat())

    override operator fun <R : Number> plus(rhs: Vector2Base<R>) = super.plus(rhs) as Point2F
    override operator fun <R : Number> minus(rhs: Vector2Base<R>) = super.minus(rhs) as Point2F

    companion object {
        fun <T : Number> create(src: Tuple2<T>) = Point2F(src.x, src.y)
    }
}

data class Point2I(override var x: Int = 0, override var y: Int = 0) : Point2Base<Int>() {
    constructor(x: Number, y: Number) : this(x.toInt(), y.toInt())

    override operator fun <R : Number> plus(rhs: Vector2Base<R>) = super.plus(rhs) as Point2I
    override operator fun <R : Number> minus(rhs: Vector2Base<R>) = super.minus(rhs) as Point2I

    companion object {
        fun <T : Number> create(src: Tuple2<T>) = Point2I(src.x, src.y)
    }
}
