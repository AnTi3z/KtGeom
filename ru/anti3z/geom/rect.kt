package ru.anti3z.geom

sealed class Rect2Base<out T : Number> {
    abstract val bottomLeft: Point2Base<T>
    abstract val topRight: Point2Base<T>

    operator fun <R : Number> contains(pnt: Tuple2<R>) = when (this) {
        is Rect2D -> (pnt.x.toDouble() in bottomLeft.x..topRight.x) && (pnt.y.toDouble() in bottomLeft.y..topRight.y)
        is Rect2F -> (pnt.x.toFloat() in bottomLeft.x..topRight.x) && (pnt.y.toFloat() in bottomLeft.y..topRight.y)
        is Rect2I -> (pnt.x.toInt() in bottomLeft.x..topRight.x) && (pnt.y.toInt() in bottomLeft.y..topRight.y)
    }

    open fun getWidth(): Number = when (this) {
        is Rect2D -> topRight.x - bottomLeft.x
        is Rect2F -> topRight.x - bottomLeft.x
        is Rect2I -> topRight.x - bottomLeft.x
    }

    fun setWidth(value: Number) = when (this) {
        is Rect2D -> topRight.x = bottomLeft.x + value.toDouble()
        is Rect2F -> topRight.x = bottomLeft.x + value.toFloat()
        is Rect2I -> topRight.x = bottomLeft.x + value.toInt()
    }

    open fun getHeight(): Number = when (this) {
        is Rect2D -> topRight.y - bottomLeft.y
        is Rect2F -> topRight.y - bottomLeft.y
        is Rect2I -> topRight.y - bottomLeft.y
    }

    fun setHeight(value: Number) = when (this) {
        is Rect2D -> topRight.y = bottomLeft.y + value.toDouble()
        is Rect2F -> topRight.y = bottomLeft.y + value.toFloat()
        is Rect2I -> topRight.y = bottomLeft.y + value.toInt()
    }

}

data class Rect2D(override var bottomLeft: Point2D, override var topRight: Point2D) : Rect2Base<Double>() {
    override fun getWidth() = super.getWidth() as Double
    override fun getHeight() = super.getHeight() as Double
    var width: Double
        inline get() = getWidth()
        inline set(value) = setWidth(value)
    var height: Double
        inline get() = getHeight()
        inline set(value) = setHeight(value)

    companion object {
        fun <T : Number> create(lowLeft: Tuple2<T>, upRight: Tuple2<T>) =
            Rect2D(Point2D.create(lowLeft), Point2D.create(upRight))

        fun <T : Number> create(center: Tuple2<T>, width: Number, height: Number): Rect2D {
            val centerPoint2D: Point2D = Point2D.create(center)
            val halfVector2D: Vector2D = Vector2D(width, height).apply { scale(0.5) }
            return Rect2D(centerPoint2D - halfVector2D, centerPoint2D + halfVector2D)
        }

        fun create(width: Number, height: Number) = Rect2D(Point2D(), Point2D(width, height))
    }
}

data class Rect2F(override var bottomLeft: Point2F, override var topRight: Point2F) : Rect2Base<Float>() {
    override fun getWidth() = super.getWidth() as Float
    override fun getHeight() = super.getWidth() as Float
    var width: Float
        inline get() = getWidth()
        inline set(value) = setWidth(value)
    var height: Float
        inline get() = getHeight()
        inline set(value) = setHeight(value)

    companion object {
        fun <T : Number> create(lowLeft: Tuple2<T>, upRight: Tuple2<T>) =
            Rect2F(Point2F.create(lowLeft), Point2F.create(upRight))

        fun <T : Number> create(center: Tuple2<T>, width: Number, height: Number): Rect2F {
            val centerPoint2F = Point2F.create(center)
            val halfVector2F = Vector2F(width, height).apply { scale(0.5) }
            return Rect2F(centerPoint2F - halfVector2F, centerPoint2F + halfVector2F)
        }

        fun create(width: Number, height: Number) = Rect2F(Point2F(), Point2F(width, height))
    }
}

data class Rect2I(override var bottomLeft: Point2I, override var topRight: Point2I) : Rect2Base<Int>() {
    override fun getWidth() = super.getWidth() as Int
    override fun getHeight() = super.getWidth() as Int
    var width: Int
        inline get() = getWidth()
        inline set(value) = setWidth(value)
    var height: Int
        inline get() = getHeight()
        inline set(value) = setHeight(value)

    companion object {
        fun <T : Number> create(lowLeft: Tuple2<T>, upRight: Tuple2<T>) =
            Rect2I(Point2I.create(lowLeft), Point2I.create(upRight))

        fun <T : Number> create(center: Tuple2<T>, width: Number, height: Number): Rect2I {
            val centerPoint2I = Point2I.create(center)
            val halfVector2I = Vector2I(width, height).apply { scale(0.5) }
            return Rect2I(centerPoint2I - halfVector2I, centerPoint2I + halfVector2I)
        }

        fun create(width: Number, height: Number) = Rect2I(Point2I(), Point2I(width, height))
    }
}
