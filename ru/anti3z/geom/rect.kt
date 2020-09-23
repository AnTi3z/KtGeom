package ru.anti3z.geom

interface Rect2<out T : Number> {
    val bottomLeft: Point2<T>
    val topRight: Point2<T>
    val bottomRight: Point2<T>
    val topLeft: Point2<T>

    val top: T
//        get() = topRight.y
    val bottom: T
//        get() = bottomLeft.y
    val right: T
//        get() = topRight.x
    val left: T
//        get() = bottomLeft.x

    val center: Point2<T>

    val width: T
    val height: T

    operator fun contains(pnt: Tuple2<*>): Boolean
//    fun collision(other: Rect2<*>): Boolean
}

interface MutableRect2<T : Number> : Rect2<T> {
    override var bottomLeft: Point2<T>
    override var topRight: Point2<T>
    override var bottomRight: Point2<T>
    override var topLeft: Point2<T>

    override var top: T
    override var bottom: T
    override var right: T
    override var left: T

    override var center: Point2<T>

    override var width: T
    override var height: T
}

sealed class Rect2Base<T : Number> : MutableRect2<T> {

    protected abstract val blPoint: MutablePoint2<T>
    protected abstract val trPoint: MutablePoint2<T>

    // TODO: Require top > bottom and right > left
    override var top: T
        get() = trPoint.y
        set(value) {
            trPoint.y = value
        }

    override var bottom: T
        get() = blPoint.y
        set(value) {
            blPoint.y = value
        }

    override var right: T
        get() = trPoint.x
        set(value) {
            trPoint.x = value
        }

    override var left: T
        get() = blPoint.x
        set(value) {
            blPoint.x = value
        }

    override var bottomLeft: Point2<T>
        get() = blPoint
        set(value) {
            blPoint.set(value)
        }

    override var topRight: Point2<T>
        get() = trPoint
        set(value) {
            trPoint.set(value)
        }

    override var bottomRight: Point2<T>
        @Suppress("UNCHECKED_CAST")
        get() = when (this) {
            is Rect2D -> Point2D(trPoint.x, blPoint.y)
            is Rect2F -> Point2F(trPoint.x, blPoint.y)
            is Rect2I -> Point2I(trPoint.x, blPoint.y)
        } as Point2<T>
        set(value) {
            trPoint.x = value.x
            blPoint.y = value.y
        }

    override var topLeft: Point2<T>
        @Suppress("UNCHECKED_CAST")
        get() = when (this) {
            is Rect2D -> Point2D(blPoint.x, trPoint.y)
            is Rect2F -> Point2F(blPoint.x, trPoint.y)
            is Rect2I -> Point2I(blPoint.x, trPoint.y)
        } as Point2<T>
        set(value) {
            blPoint.x = value.x
            trPoint.y = value.y
        }

    @Suppress("UNCHECKED_CAST")
    override var center: Point2<T>
        get() =  blPoint + blPoint.vectorTo(trPoint) / 2
        set(value) {
            val halfDiag = when (this) {
                is Rect2D -> Vector2D(width / 2, height / 2)
                is Rect2F -> Vector2F(width / 2, height / 2)
                is Rect2I -> Vector2I(width / 2, height / 2)
            } as Vector2<T>
            trPoint.set(value + halfDiag)
            blPoint.set(value - halfDiag)
        }

    @Suppress("UNCHECKED_CAST")
    override var width: T
        get() = when (this) {
            is Rect2D -> (topRight.x - bottomLeft.x)
            is Rect2F -> (topRight.x - bottomLeft.x)
            is Rect2I -> (topRight.x - bottomLeft.x)
        } as T
        set(value) {
            trPoint.x = when (this) {
                is Rect2D -> bottomLeft.x + value as Double
                is Rect2F -> bottomLeft.x + value as Float
                is Rect2I -> bottomLeft.x + value as Int
            } as T
        }

    @Suppress("UNCHECKED_CAST")
    override var height: T
        get() = when (this) {
            is Rect2D -> (topRight.y - bottomLeft.y)
            is Rect2F -> (topRight.y - bottomLeft.y)
            is Rect2I -> (topRight.y - bottomLeft.y)
        } as T
        set(value) {
            trPoint.y = when (this) {
                is Rect2D -> bottomLeft.y + value as Double
                is Rect2F -> bottomLeft.y + value as Float
                is Rect2I -> bottomLeft.y + value as Int
            } as T
        }

    override operator fun contains(pnt: Tuple2<*>) = when (this) {
        is Rect2D -> (pnt.x.toDouble() in bottomLeft.x..topRight.x) && (pnt.y.toDouble() in bottomLeft.y..topRight.y)
        is Rect2F -> (pnt.x.toFloat() in bottomLeft.x..topRight.x) && (pnt.y.toFloat() in bottomLeft.y..topRight.y)
        is Rect2I -> (pnt.x.toInt() in bottomLeft.x..topRight.x) && (pnt.y.toInt() in bottomLeft.y..topRight.y)
    }

    override fun toString(): String {
        return "${this::class.simpleName}(bottom=${blPoint.y}, left=${blPoint.x}, top=${trPoint.y}, right=${trPoint.x})"
    }

    override fun hashCode(): Int {
        var result = blPoint.hashCode()
        result = 31 * result + trPoint.hashCode()
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Rect2Base<*>) return false

        if (blPoint != other.blPoint) return false
        if (trPoint != other.trPoint) return false

        return true
    }
}

class Rect2D(
        point1: Point2<*>,
        point2: Point2<*>,
) : Rect2Base<Double>() {

    override val blPoint: MutablePoint2<Double> = Point2D(
            minOf(point1.x.toDouble(), point2.x.toDouble()),
            minOf(point1.y.toDouble(), point2.y.toDouble())
    )

    override val trPoint: MutablePoint2<Double> = Point2D(
            maxOf(point1.x.toDouble(), point2.x.toDouble()),
            maxOf(point1.y.toDouble(), point2.y.toDouble())
    )

    companion object {
        fun create(lowLeft: Tuple2<*>, upRight: Tuple2<*>) =
                Rect2D(Point2D(lowLeft), Point2D(upRight))

        fun create(center: Tuple2<*>, width: Number, height: Number): Rect2D {
            val centerPoint2D = Point2D(center)
            val halfVector2D = Vector2D(width, height) / 2
            return Rect2D(centerPoint2D - halfVector2D, centerPoint2D + halfVector2D)
        }

        fun create(width: Number, height: Number) = Rect2D(Point2D(), Point2D(width, height))
        fun create(top: Number, left: Number, right: Number, bottom: Number) =
                Rect2D(Point2D(left, bottom), Point2D(right, top))
    }
}

class Rect2F(
        point1: Point2<*>,
        point2: Point2<*>,
) : Rect2Base<Float>() {

    override val blPoint: MutablePoint2<Float> = Point2F(
            minOf(point1.x.toFloat(), point2.x.toFloat()),
            minOf(point1.y.toFloat(), point2.y.toFloat())
    )

    override val trPoint: MutablePoint2<Float> = Point2F(
            maxOf(point1.x.toFloat(), point2.x.toFloat()),
            maxOf(point1.y.toFloat(), point2.y.toFloat())
    )

    companion object {
        fun create(lowLeft: Tuple2<*>, upRight: Tuple2<*>) =
                Rect2F(Point2F(lowLeft), Point2F(upRight))

        fun create(center: Tuple2<*>, width: Number, height: Number): Rect2F {
            val centerPoint2F = Point2F(center)
            val halfVector2F = Vector2F(width, height) / 2
            return Rect2F(centerPoint2F - halfVector2F, centerPoint2F + halfVector2F)
        }

        fun create(width: Number, height: Number) = Rect2F(Point2F(), Point2F(width, height))
        fun create(top: Number, left: Number, right: Number, bottom: Number) =
                Rect2F(Point2F(left, bottom), Point2F(right, top))
    }
}

class Rect2I(
        point1: Point2<*>,
        point2: Point2<*>,
) : Rect2Base<Int>() {

    override val blPoint: MutablePoint2<Int> = Point2I(
            minOf(point1.x.toInt(), point2.x.toInt()),
            minOf(point1.y.toInt(), point2.y.toInt())
    )

    override val trPoint: MutablePoint2<Int> = Point2I(
            maxOf(point1.x.toInt(), point2.x.toInt()),
            maxOf(point1.y.toInt(), point2.y.toInt())
    )

    companion object {
        fun create(lowLeft: Tuple2<*>, upRight: Tuple2<*>) =
                Rect2I(Point2I(lowLeft), Point2I(upRight))

        fun create(center: Tuple2<*>, width: Number, height: Number): Rect2I {
            val centerPoint2I = Point2I(center)
            val halfVector2I = Vector2I(width, height) / 2
            return Rect2I(centerPoint2I - halfVector2I, centerPoint2I + halfVector2I)
        }

        fun create(width: Number, height: Number) = Rect2I(Point2I(), Point2I(width, height))
        fun create(top: Number, left: Number, right: Number, bottom: Number) =
                Rect2I(Point2I(left, bottom), Point2I(right, top))
    }
}
