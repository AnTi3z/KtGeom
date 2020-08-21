package ru.anti3z.geom

interface Point2Base<out T : Number> : Tuple2<T> {
    operator fun <R : Number> plus(rhs: Vector2Base<R>): Point2Base<T>
    operator fun <R : Number> minus(rhs: Vector2Base<R>): Point2Base<T>
}

interface MutablePoint2Base<T : Number> : Point2Base<T>, MutableTuple2<T>

sealed class Point2<T : Number> : MutablePoint2Base<T> {
    @Suppress("UNCHECKED_CAST")
    override operator fun <R : Number> plus(rhs: Vector2Base<R>) = when (this) {
        is Point2D -> Point2D(x + rhs.x.toDouble(), y + rhs.y.toDouble())
        is Point2F -> Point2F(x + rhs.x.toFloat(), y + rhs.y.toFloat())
        is Point2I -> Point2I(x + rhs.x.toInt(), y + rhs.y.toInt())
    } as Point2<T>

    @Suppress("UNCHECKED_CAST")
    override operator fun <R : Number> minus(rhs: Vector2Base<R>) = when (this) {
        is Point2D -> Point2D(x - rhs.x.toDouble(), y - rhs.y.toDouble())
        is Point2F -> Point2F(x - rhs.x.toFloat(), y - rhs.y.toFloat())
        is Point2I -> Point2I(x - rhs.x.toInt(), y - rhs.y.toInt())
    } as Point2<T>

    override fun toDouble() = when (this) {
        is Point2D -> this
        else -> Point2D(x, y)
    }

    override fun toFloat() = when (this) {
        is Point2F -> this
        else -> Point2F(x, y)
    }

    override fun toInt() = when (this) {
        is Point2I -> this
        else -> Point2I(x, y)
    }
}

//fun <T : Number> createPoint2D(src: Tuple2<T>) = Point2D(src.x, src.y)

data class Point2D(override var x: Double = 0.0, override var y: Double = 0.0) : Point2<Double>() {
    constructor(x: Number, y: Number) : this(x.toDouble(), y.toDouble())

    override operator fun <R : Number> plus(rhs: Vector2Base<R>) = super.plus(rhs) as Point2D
    override operator fun <R : Number> minus(rhs: Vector2Base<R>) = super.minus(rhs) as Point2D

    companion object {
        fun <T : Number> create(src: Tuple2<T>) = Point2D(src.x, src.y)
    }
}

data class Point2F(override var x: Float = 0.0f, override var y: Float = 0.0f) : Point2<Float>() {
    constructor(x: Number, y: Number) : this(x.toFloat(), y.toFloat())

    override operator fun <R : Number> plus(rhs: Vector2Base<R>) = super.plus(rhs) as Point2F
    override operator fun <R : Number> minus(rhs: Vector2Base<R>) = super.minus(rhs) as Point2F

    companion object {
        fun <T : Number> create(src: Tuple2<T>) = Point2F(src.x, src.y)
    }
}

data class Point2I(override var x: Int = 0, override var y: Int = 0) : Point2<Int>() {
    constructor(x: Number, y: Number) : this(x.toInt(), y.toInt())

    override operator fun <R : Number> plus(rhs: Vector2Base<R>) = super.plus(rhs) as Point2I
    override operator fun <R : Number> minus(rhs: Vector2Base<R>) = super.minus(rhs) as Point2I

    companion object {
        fun <T : Number> create(src: Tuple2<T>) = Point2I(src.x, src.y)
    }
}
