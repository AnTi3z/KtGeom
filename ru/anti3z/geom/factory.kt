package ru.anti3z.geom

interface Geom2Factory<out T : Number> {
    fun createPoint(x: Number, y: Number): Point2Base<T>
    fun <R : Number> createPoint(src: Tuple2<R>): Point2Base<T>

    fun createVector(x: Number, y: Number): Vector2Base<T>
    fun <R : Number> createVector(src: Tuple2<R>): Vector2Base<T>

    fun <R : Number> createBounds(lowLeft: Tuple2<R>, upRight: Tuple2<R>): Bounds2Base<T>
    fun <R : Number> createBounds(center: Tuple2<R>, width: Number, height: Number): Bounds2Base<T>
    fun createBounds(width: Number, height: Number): Bounds2Base<T>
}

class Geom2DFactory : Geom2Factory<Double> {
    fun createPoint(x: Double = 0.0, y: Double = 0.0) = Point2D(x, y)
    override fun createPoint(x: Number, y: Number) = Point2D(x, y)
    override fun <R : Number> createPoint(src: Tuple2<R>) = Point2D.create(src)

    fun createVector(x: Double = 0.0, y: Double = 0.0) = Vector2D(x, y)
    override fun createVector(x: Number, y: Number) = Vector2D(x, y)
    override fun <R : Number> createVector(src: Tuple2<R>) = Vector2D.create(src)

    override fun <R : Number> createBounds(lowLeft: Tuple2<R>, upRight: Tuple2<R>) = Bounds2D.create(lowLeft, upRight)
    override fun <R : Number> createBounds(center: Tuple2<R>, width: Number, height: Number) =
        Bounds2D.create(center, width, height)

    override fun createBounds(width: Number, height: Number) = Bounds2D.create(width, height)
}

class Geom2FFactory : Geom2Factory<Float> {
    fun createPoint(x: Float = 0.0f, y: Float = 0.0f) = Point2F(x, y)
    override fun createPoint(x: Number, y: Number) = Point2F(x, y)
    override fun <R : Number> createPoint(src: Tuple2<R>) = Point2F.create(src)

    fun createVector(x: Float = 0.0f, y: Float = 0.0f) = Vector2F(x, y)
    override fun createVector(x: Number, y: Number) = Vector2F(x, y)
    override fun <R : Number> createVector(src: Tuple2<R>) = Vector2F.create(src)

    override fun <R : Number> createBounds(lowLeft: Tuple2<R>, upRight: Tuple2<R>) = Bounds2F.create(lowLeft, upRight)
    override fun <R : Number> createBounds(center: Tuple2<R>, width: Number, height: Number) =
        Bounds2F.create(center, width, height)

    override fun createBounds(width: Number, height: Number) = Bounds2F.create(width, height)
}

class Geom2IFactory : Geom2Factory<Int> {
    fun createPoint(x: Int = 0, y: Int = 0) = Point2I(x, y)
    override fun createPoint(x: Number, y: Number) = Point2I(x, y)
    override fun <R : Number> createPoint(src: Tuple2<R>) = Point2I.create(src)

    fun createVector(x: Int = 0, y: Int = 0) = Vector2I(x, y)
    override fun createVector(x: Number, y: Number) = Vector2I(x, y)
    override fun <R : Number> createVector(src: Tuple2<R>) = Vector2I.create(src)

    override fun <R : Number> createBounds(lowLeft: Tuple2<R>, upRight: Tuple2<R>) = Bounds2I.create(lowLeft, upRight)
    override fun <R : Number> createBounds(center: Tuple2<R>, width: Number, height: Number) =
        Bounds2I.create(center, width, height)

    override fun createBounds(width: Number, height: Number) = Bounds2I.create(width, height)
}
