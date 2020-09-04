package ru.anti3z.geom

interface Point2<out T : Number> : Tuple2<T> {
    operator fun <P : Number> plus(rhs: Vector2<P>): Point2<T>
    operator fun <P : Number> minus(rhs: Vector2<P>): Point2<T>

    override fun toDouble(): Point2<Double>
    override fun toFloat(): Point2<Float>
    override fun toInt(): Point2<Int>

    fun toVector(): Vector2<T>
}

interface MutablePoint2<T : Number> : Point2<T>, MutableTuple2<T> {
    operator fun <P : Number> plusAssign(rhs: Vector2<P>)
    operator fun <P : Number> minusAssign(rhs: Vector2<P>)

    override fun toDouble(): MutablePoint2<Double>
    override fun toFloat(): MutablePoint2<Float>
    override fun toInt(): MutablePoint2<Int>

    override fun toVector(): MutableVector2<T>
}

sealed class Point2Base<T : Number> : MutablePoint2<T> {
    @Suppress("UNCHECKED_CAST")
    override operator fun <P : Number> plus(rhs: Vector2<P>): Point2<T> = when (this) {
        is Point2D -> Point2D(x + rhs.x.toDouble(), y + rhs.y.toDouble())
        is Point2F -> Point2F(x + rhs.x.toFloat(), y + rhs.y.toFloat())
        is Point2I -> Point2I(x + rhs.x.toInt(), y + rhs.y.toInt())
    } as Point2Base<T>

    override operator fun <P : Number> plusAssign(rhs: Vector2<P>) {
        when (this) {
            is Point2D -> {
                x += rhs.x.toDouble()
                y += rhs.y.toDouble()
            }
            is Point2F -> {
                x += rhs.x.toFloat()
                y += rhs.y.toFloat()
            }
            is Point2I -> {
                x += rhs.x.toInt()
                y += rhs.y.toInt()
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override operator fun <P : Number> minus(rhs: Vector2<P>): Point2<T> = when (this) {
        is Point2D -> Point2D(x - rhs.x.toDouble(), y - rhs.y.toDouble())
        is Point2F -> Point2F(x - rhs.x.toFloat(), y - rhs.y.toFloat())
        is Point2I -> Point2I(x - rhs.x.toInt(), y - rhs.y.toInt())
    } as Point2Base<T>

    override operator fun <R : Number> minusAssign(rhs: Vector2<R>) {
        when (this) {
            is Point2D -> {
                x -= rhs.x.toDouble()
                y -= rhs.y.toDouble()
            }
            is Point2F -> {
                x -= rhs.x.toFloat()
                y -= rhs.y.toFloat()
            }
            is Point2I -> {
                x -= rhs.x.toInt()
                y -= rhs.y.toInt()
            }
        }
    }

    override fun toDouble(): MutablePoint2<Double> = when (this) {
        is Point2D -> this
        else -> Point2D(x, y)
    }

    override fun toFloat(): MutablePoint2<Float> = when (this) {
        is Point2F -> this
        else -> Point2F(x, y)
    }

    override fun toInt(): MutablePoint2<Int> = when (this) {
        is Point2I -> this
        else -> Point2I(x, y)
    }

    @Suppress("UNCHECKED_CAST")
    override fun toVector(): MutableVector2<T> = when (this) {
        is Point2D -> Vector2D(x, y)
        is Point2F -> Vector2F(x, y)
        is Point2I -> Vector2I(x, y)
    } as Vector2Base<T>
}


data class Point2D(override var x: Double = 0.0, override var y: Double = 0.0) : Point2Base<Double>() {
    constructor(x: Number, y: Number) : this(x.toDouble(), y.toDouble())
    constructor(src: Tuple2<Number>) : this(src.x, src.y)

    override operator fun <P : Number> plus(rhs: Vector2<P>) = super.plus(rhs) as Point2D
    override operator fun <P : Number> minus(rhs: Vector2<P>) = super.minus(rhs) as Point2D
}

data class Point2F(override var x: Float = 0.0f, override var y: Float = 0.0f) : Point2Base<Float>() {
    constructor(x: Number, y: Number) : this(x.toFloat(), y.toFloat())
    constructor(src: Tuple2<Number>) : this(src.x, src.y)

    override operator fun <P : Number> plus(rhs: Vector2<P>) = super.plus(rhs) as Point2F
    override operator fun <P : Number> minus(rhs: Vector2<P>) = super.minus(rhs) as Point2F
}

data class Point2I(override var x: Int = 0, override var y: Int = 0) : Point2Base<Int>() {
    constructor(x: Number, y: Number) : this(x.toInt(), y.toInt())
    constructor(src: Tuple2<Number>) : this(src.x, src.y)

    override operator fun <P : Number> plus(rhs: Vector2<P>) = super.plus(rhs) as Point2I
    override operator fun <P : Number> minus(rhs: Vector2<P>) = super.minus(rhs) as Point2I
}
