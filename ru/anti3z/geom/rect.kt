package ru.anti3z.geom

interface Rect2<out T : Number> {
    val bottomLeft: Point2<T>
    val topRight: Point2<T>

    val top: T
        get() = topRight.y
    val bottom: T
        get() = bottomLeft.y
    val right: T
        get() = topRight.x
    val left: T
        get() = bottomLeft.x

    val center: Point2<T>

    val width: T
    val height: T

    operator fun <R : Number> contains(pnt: Tuple2<R>): Boolean
//    operator fun <R : Number> collision(rect: Rect2<R>): Boolean
}

interface MutableRect2<T : Number> : Rect2<T> {
    override val bottomLeft: MutablePoint2<T>
    override val topRight: MutablePoint2<T>

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

//    override var center: Point2<T>

    override var width: T
    override var height: T
}

sealed class Rect2Base<T : Number> : MutableRect2<T> {
    override val center: Point2<T>
        @Suppress("UNCHECKED_CAST")
        get() = when (this) {
            is Rect2D -> Point2D((topRight.x - bottomLeft.x) / 2, (topRight.y - bottomLeft.y) / 2)
            is Rect2F -> Point2F((topRight.x - bottomLeft.x) / 2, (topRight.y - bottomLeft.y) / 2)
            is Rect2I -> Point2I((topRight.x - bottomLeft.x) / 2, (topRight.y - bottomLeft.y) / 2)
        } as Point2<T>
//        set(value) {
//            when (this) {
//                is Rect2D -> {
//                    val halfWidth = width/2
//                    val halfHeight = height/2
//                    topRight.x = value.x + halfWidth
//                    topRight.y =
//                }
//                is Rect2F -> topRight.x = bottomLeft.x + value.toFloat()
//                is Rect2I -> topRight.x = bottomLeft.x + value.toInt()
//            }
//        }

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
    val bottomLeftPnt: Point2<Double>,
    val topRightPnt: Point2<Double>
) : Rect2Base<Double>() {

    override val bottomLeft: MutablePoint2<Double> = Point2D(bottomLeftPnt)
    override val topRight: MutablePoint2<Double> = Point2D(topRightPnt)

    companion object {
        fun <T1 : Number, T2 : Number> create(lowLeft: Tuple2<T1>, upRight: Tuple2<T2>) =
            Rect2D(Point2D(lowLeft), Point2D(upRight))

        fun <T : Number> create(center: Tuple2<T>, width: Number, height: Number): Rect2D {
            val centerPoint2D = Point2D(center)
            val halfVector2D = Vector2D(width, height).scaled(0.5)
            return Rect2D(centerPoint2D - halfVector2D, centerPoint2D + halfVector2D)
        }

        fun create(width: Number, height: Number) = Rect2D(Point2D(), Point2D(width, height))
        fun create(top: Number, left: Number, right: Number, bottom: Number) =
            Rect2D(Point2D(left, bottom), Point2D(right, top))
    }
}

data class Rect2F(
    val bottomLeftPnt: Point2<Float>,
    val topRightPnt: Point2<Float>
) : Rect2Base<Float>() {

    override val bottomLeft: MutablePoint2<Float> = Point2F(bottomLeftPnt)
    override val topRight: MutablePoint2<Float> = Point2F(topRightPnt)

    companion object {
        fun <T1 : Number, T2 : Number> create(lowLeft: Tuple2<T1>, upRight: Tuple2<T2>) =
            Rect2F(Point2F(lowLeft), Point2F(upRight))

        fun <T : Number> create(center: Tuple2<T>, width: Number, height: Number): Rect2F {
            val centerPoint2F = Point2F(center)
            val halfVector2F = Vector2F(width, height).scaled(0.5)
            return Rect2F(centerPoint2F - halfVector2F, centerPoint2F + halfVector2F)
        }

        fun create(width: Number, height: Number) = Rect2F(Point2F(), Point2F(width, height))
        fun create(top: Number, left: Number, right: Number, bottom: Number) =
            Rect2F(Point2F(left, bottom), Point2F(right, top))
    }
}

data class Rect2I(
    val bottomLeftPnt: Point2<Int>,
    val topRightPnt: Point2<Int>
) : Rect2Base<Int>() {

    override val bottomLeft: MutablePoint2<Int> = Point2I(bottomLeftPnt)
    override val topRight: MutablePoint2<Int> = Point2I(topRightPnt)

    companion object {
        fun <T1 : Number, T2 : Number> create(lowLeft: Tuple2<T1>, upRight: Tuple2<T2>) =
            Rect2I(Point2I(lowLeft), Point2I(upRight))

        fun <T : Number> create(center: Tuple2<T>, width: Number, height: Number): Rect2I {
            val centerPoint2I = Point2I(center)
            val halfVector2I = Vector2I(width, height).scaled(0.5)
            return Rect2I(centerPoint2I - halfVector2I, centerPoint2I + halfVector2I)
        }

        fun create(width: Number, height: Number) = Rect2I(Point2I(), Point2I(width, height))
        fun create(top: Number, left: Number, right: Number, bottom: Number) =
            Rect2I(Point2I(left, bottom), Point2I(right, top))
    }
}
