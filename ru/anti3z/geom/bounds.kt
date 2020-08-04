package ru.anti3z.geom

sealed class Bounds2Base<out T : Number> {
    abstract val lowLeft: Point2Base<T>
    abstract val upRight: Point2Base<T>

    operator fun <R : Number> contains(pnt: Tuple2<R>) = when (this) {
        is Bounds2D -> (pnt.x.toDouble() in lowLeft.x..upRight.x) && (pnt.y.toDouble() in lowLeft.y..upRight.y)
        is Bounds2F -> (pnt.x.toFloat() in lowLeft.x..upRight.x) && (pnt.y.toFloat() in lowLeft.y..upRight.y)
        is Bounds2I -> (pnt.x.toInt() in lowLeft.x..upRight.x) && (pnt.y.toInt() in lowLeft.y..upRight.y)
    }

    open fun getWidth(): Number = when (this) {
        is Bounds2D -> upRight.x - lowLeft.x
        is Bounds2F -> upRight.x - lowLeft.x
        is Bounds2I -> upRight.x - lowLeft.x
    }

    fun setWidth(value: Number) = when (this) {
        is Bounds2D -> upRight.x = lowLeft.x + value.toDouble()
        is Bounds2F -> upRight.x = lowLeft.x + value.toFloat()
        is Bounds2I -> upRight.x = lowLeft.x + value.toInt()
    }

    open fun getHeight(): Number = when (this) {
        is Bounds2D -> upRight.y - lowLeft.y
        is Bounds2F -> upRight.y - lowLeft.y
        is Bounds2I -> upRight.y - lowLeft.y
    }

    fun setHeight(value: Number) = when (this) {
        is Bounds2D -> upRight.y = lowLeft.y + value.toDouble()
        is Bounds2F -> upRight.y = lowLeft.y + value.toFloat()
        is Bounds2I -> upRight.y = lowLeft.y + value.toInt()
    }

}

data class Bounds2D(override var lowLeft: Point2D, override var upRight: Point2D) : Bounds2Base<Double>() {
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
            Bounds2D(Point2D.create(lowLeft), Point2D.create(upRight))

        fun <T : Number> create(center: Tuple2<T>, width: Number, height: Number): Bounds2D {
            val centerPoint2D: Point2D = Point2D.create(center)
            val halfVector2D: Vector2D = Vector2D(width, height).apply { scale(0.5) }
            return Bounds2D(centerPoint2D - halfVector2D, centerPoint2D + halfVector2D)
        }

        fun create(width: Number, height: Number) = Bounds2D(Point2D(), Point2D(width, height))
    }
}

data class Bounds2F(override var lowLeft: Point2F, override var upRight: Point2F) : Bounds2Base<Float>() {
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
            Bounds2F(Point2F.create(lowLeft), Point2F.create(upRight))

        fun <T : Number> create(center: Tuple2<T>, width: Number, height: Number): Bounds2F {
            val centerPoint2F = Point2F.create(center)
            val halfVector2F = Vector2F(width, height).apply { scale(0.5) }
            return Bounds2F(centerPoint2F - halfVector2F, centerPoint2F + halfVector2F)
        }

        fun create(width: Number, height: Number) = Bounds2F(Point2F(), Point2F(width, height))
    }
}

data class Bounds2I(override var lowLeft: Point2I, override var upRight: Point2I) : Bounds2Base<Int>() {
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
            Bounds2I(Point2I.create(lowLeft), Point2I.create(upRight))

        fun <T : Number> create(center: Tuple2<T>, width: Number, height: Number): Bounds2I {
            val centerPoint2I = Point2I.create(center)
            val halfVector2I = Vector2I(width, height).apply { scale(0.5) }
            return Bounds2I(centerPoint2I - halfVector2I, centerPoint2I + halfVector2I)
        }

        fun create(width: Number, height: Number) = Bounds2I(Point2I(), Point2I(width, height))
    }
}
