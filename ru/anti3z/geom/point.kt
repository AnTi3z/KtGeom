package ru.anti3z.geom

interface Point2<out T : Number> : Tuple2<T> {
    operator fun plus(rhs: Vector2<*>): Point2<T>
    operator fun minus(rhs: Vector2<*>): Point2<T>

    override fun toDouble(): Point2<Double>
    override fun toFloat(): Point2<Float>
    override fun toInt(): Point2<Int>

    fun toVector(): Vector2<T>
    fun distance(point: Point2<*>): Double
    fun vectorTo(point: Point2<*>): Vector2<T>
}

interface MutablePoint2<T : Number> : Point2<T>, MutableTuple2<T> {
    override operator fun plus(rhs: Vector2<*>): MutablePoint2<T>
    override operator fun minus(rhs: Vector2<*>): MutablePoint2<T>

    operator fun plusAssign(rhs: Vector2<*>)
    operator fun minusAssign(rhs: Vector2<*>)

    override fun toDouble(): MutablePoint2<Double>
    override fun toFloat(): MutablePoint2<Float>
    override fun toInt(): MutablePoint2<Int>

    override fun toVector(): MutableVector2<T>
    override fun vectorTo(point: Point2<*>): MutableVector2<T>
}

sealed class Point2Base<T : Number> : MutablePoint2<T> {
    @Suppress("UNCHECKED_CAST")
    override operator fun plus(rhs: Vector2<*>): MutablePoint2<T> = when (this) {
        is Point2D -> Point2D(x + rhs.x.toDouble(), y + rhs.y.toDouble())
        is Point2F -> Point2F(x + rhs.x.toFloat(), y + rhs.y.toFloat())
        is Point2I -> Point2I(x + rhs.x.toInt(), y + rhs.y.toInt())
    } as MutablePoint2<T>

    @Suppress("UNCHECKED_CAST")
    override operator fun minus(rhs: Vector2<*>): MutablePoint2<T> = when (this) {
        is Point2D -> Point2D(x - rhs.x.toDouble(), y - rhs.y.toDouble())
        is Point2F -> Point2F(x - rhs.x.toFloat(), y - rhs.y.toFloat())
        is Point2I -> Point2I(x - rhs.x.toInt(), y - rhs.y.toInt())
    } as MutablePoint2<T>

    override operator fun plusAssign(rhs: Vector2<*>) {
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

    override operator fun minusAssign(rhs: Vector2<*>) {
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

    override fun set(src: Tuple2<*>) {
        when (this) {
            is Point2D -> {
                x = src.x.toDouble()
                y = src.y.toDouble()
            }
            is Point2F -> {
                x = src.x.toFloat()
                y = src.y.toFloat()
            }
            is Point2I -> {
                x = src.x.toInt()
                y = src.y.toInt()
            }
        }
    }

    override fun set(x: Number, y: Number) {
        when (this) {
            is Point2D -> {
                this.x = x.toDouble()
                this.y = y.toDouble()
            }
            is Point2F -> {
                this.x = x.toFloat()
                this.y = y.toFloat()
            }
            is Point2I -> {
                this.x = x.toInt()
                this.y = y.toInt()
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun toVector(): MutableVector2<T> = when (this) {
        is Point2D -> Vector2D(x, y)
        is Point2F -> Vector2F(x, y)
        is Point2I -> Vector2I(x, y)
    } as MutableVector2<T>

    override fun distance(point: Point2<*>): Double = vectorTo(point).length

    @Suppress("UNCHECKED_CAST")
    override fun vectorTo(point: Point2<*>): MutableVector2<T> = when (this) {
        is Point2D -> Vector2D(point.x.toDouble() - x, point.y.toDouble() - y)
        is Point2F -> Vector2F(point.x.toFloat() - x, point.y.toFloat() - y)
        is Point2I -> Vector2I(point.x.toInt() - x, point.y.toInt() - y)
    } as MutableVector2<T>
}

data class Point2D(override var x: Double = 0.0, override var y: Double = 0.0) : Point2Base<Double>() {
    constructor(x: Number, y: Number) : this(x.toDouble(), y.toDouble())
    constructor(src: Tuple2<*>) : this(src.x, src.y)
}

data class Point2F(override var x: Float = 0.0f, override var y: Float = 0.0f) : Point2Base<Float>() {
    constructor(x: Number, y: Number) : this(x.toFloat(), y.toFloat())
    constructor(src: Tuple2<*>) : this(src.x, src.y)
}

data class Point2I(override var x: Int = 0, override var y: Int = 0) : Point2Base<Int>() {
    constructor(x: Number, y: Number) : this(x.toInt(), y.toInt())
    constructor(src: Tuple2<*>) : this(src.x, src.y)
}
