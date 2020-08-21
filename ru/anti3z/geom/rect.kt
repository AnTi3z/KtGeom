package ru.anti3z.geom

interface Rect2Base<out T : Number> {
    val bottomLeft: Point2Base<T>
    val topRight: Point2Base<T>

    val top: T
        get() = topRight.y
    val bottom: T
        get() = bottomLeft.y
    val right: T
        get() = topRight.x
    val left: T
        get() = bottomLeft.x

    val width: T
    val height: T

    operator fun <R : Number> contains(pnt: Tuple2<R>): Boolean
}

interface MutableRect2Base<T : Number> : Rect2Base<T> {
    override var bottomLeft: MutablePoint2Base<T>
    override var topRight: MutablePoint2Base<T>

    override var top: T
        get() = super.top
        set(value) {
            topRight.y = value
        }
    override var bottom: T
        get() = super.bottom
        set(value) {
            bottomLeft.y = value
        }

    override var right: T
        get() = super.right
        set(value) {
            topRight.x = value
        }

    override var left: T
        get() = super.left
        set(value) {
            bottomLeft.x = value
        }

    override var width: T
    override var height: T
}

sealed class Rect2<T : Number> : MutableRect2Base<T> {

    override var width: T
        @Suppress("UNCHECKED_CAST")
        get() = when (this) {
            is Rect2D -> (topRight.x - bottomLeft.x) as T
            is Rect2F -> (topRight.x - bottomLeft.x) as T
            is Rect2I -> (topRight.x - bottomLeft.x) as T
        }
        set(value) {
            when (this) {
                is Rect2D -> topRight.x = bottomLeft.x + value.toDouble()
                is Rect2F -> topRight.x = bottomLeft.x + value.toFloat()
                is Rect2I -> topRight.x = bottomLeft.x + value.toInt()
            }
        }

    override var height: T
        @Suppress("UNCHECKED_CAST")
        get() = when (this) {
            is Rect2D -> (topRight.y - bottomLeft.y) as T
            is Rect2F -> (topRight.y - bottomLeft.y) as T
            is Rect2I -> (topRight.y - bottomLeft.y) as T
        }
        set(value) =
            when (this) {
                is Rect2D -> topRight.y = bottomLeft.y + value.toDouble()
                is Rect2F -> topRight.y = bottomLeft.y + value.toFloat()
                is Rect2I -> topRight.y = bottomLeft.y + value.toInt()
            }

    override operator fun <R : Number> contains(pnt: Tuple2<R>) = when (this) {
        is Rect2D -> (pnt.x.toDouble() in bottomLeft.x..topRight.x) && (pnt.y.toDouble() in bottomLeft.y..topRight.y)
        is Rect2F -> (pnt.x.toFloat() in bottomLeft.x..topRight.x) && (pnt.y.toFloat() in bottomLeft.y..topRight.y)
        is Rect2I -> (pnt.x.toInt() in bottomLeft.x..topRight.x) && (pnt.y.toInt() in bottomLeft.y..topRight.y)
    }

}

data class Rect2D(
    override var bottomLeft: MutablePoint2Base<Double>,
    override var topRight: MutablePoint2Base<Double>
) :
    Rect2<Double>() {

    companion object {
        fun <T1 : Number, T2 : Number> create(lowLeft: Tuple2<T1>, upRight: Tuple2<T2>) =
            Rect2D(Point2D.create(lowLeft), Point2D.create(upRight))

        fun <T : Number> create(center: Tuple2<T>, width: Number, height: Number): Rect2D {
            val centerPoint2D: Point2D = Point2D.create(center)
            val halfVector2D: Vector2D = Vector2D(width, height).apply { scale(0.5) }
            return Rect2D(centerPoint2D - halfVector2D, centerPoint2D + halfVector2D)
        }

        fun create(width: Number, height: Number) = Rect2D(Point2D(), Point2D(width, height))
        fun create(top: Number, left: Number, right: Number, bottom: Number) =
            Rect2D(Point2D(left, bottom), Point2D(right, top))
    }
}

data class Rect2F(override var bottomLeft: MutablePoint2Base<Float>, override var topRight: MutablePoint2Base<Float>) :
    Rect2<Float>() {

    companion object {
        fun <T1 : Number, T2 : Number> create(lowLeft: Tuple2<T1>, upRight: Tuple2<T2>) =
            Rect2F(Point2F.create(lowLeft), Point2F.create(upRight))

        fun <T : Number> create(center: Tuple2<T>, width: Number, height: Number): Rect2F {
            val centerPoint2F = Point2F.create(center)
            val halfVector2F = Vector2F(width, height).apply { scale(0.5) }
            return Rect2F(centerPoint2F - halfVector2F, centerPoint2F + halfVector2F)
        }

        fun create(width: Number, height: Number) = Rect2F(Point2F(), Point2F(width, height))
        fun create(top: Number, left: Number, right: Number, bottom: Number) =
            Rect2F(Point2F(left, bottom), Point2F(right, top))
    }
}

data class Rect2I(override var bottomLeft: MutablePoint2Base<Int>, override var topRight: MutablePoint2Base<Int>) :
    Rect2<Int>() {

    companion object {
        fun <T1 : Number, T2 : Number> create(lowLeft: Tuple2<T1>, upRight: Tuple2<T2>) =
            Rect2I(Point2I.create(lowLeft), Point2I.create(upRight))

        fun <T : Number> create(center: Tuple2<T>, width: Number, height: Number): Rect2I {
            val centerPoint2I = Point2I.create(center)
            val halfVector2I = Vector2I(width, height).apply { scale(0.5) }
            return Rect2I(centerPoint2I - halfVector2I, centerPoint2I + halfVector2I)
        }

        fun create(width: Number, height: Number) = Rect2I(Point2I(), Point2I(width, height))
        fun create(top: Number, left: Number, right: Number, bottom: Number) =
            Rect2I(Point2I(left, bottom), Point2I(right, top))
    }
}
