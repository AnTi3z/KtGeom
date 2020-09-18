package ru.anti3z.geom

interface Geom2Factory<R : Number> {
    fun createPoint(x: Number, y: Number): Point2<R>
    fun createMutablePoint(x: Number, y: Number): MutablePoint2<R>
    fun createPoint(src: Tuple2<*>): Point2<R>
    fun createMutablePoint(src: Tuple2<*>): MutablePoint2<R>

    fun createVector(x: Number, y: Number): Vector2<R>
    fun createMutableVector(x: Number, y: Number): MutableVector2<R>
    fun createVector(src: Tuple2<*>): Vector2<R>
    fun createMutableVector(src: Tuple2<*>): MutableVector2<R>

    fun createRect(bottomLeft: Tuple2<*>, topRight: Tuple2<*>): Rect2<R>
    fun createMutableRect(bottomLeft: Tuple2<*>, topRight: Tuple2<*>): MutableRect2<R>
    fun createRect(center: Tuple2<*>, width: Number, height: Number): Rect2<R>
    fun createMutableRect(center: Tuple2<*>, width: Number, height: Number): MutableRect2<R>
    fun createRect(width: Number, height: Number): Rect2<R>
    fun createMutableRect(width: Number, height: Number): MutableRect2<R>
    fun createRect(top: Number, left: Number, right: Number, bottom: Number): Rect2<R>
    fun createMutableRect(top: Number, left: Number, right: Number, bottom: Number): Rect2<R>
}

class Geom2DFactory : Geom2Factory<Double> {
    /** Point2D creators  **/
    fun createPoint(x: Double = 0.0, y: Double = 0.0): Point2<Double> = Point2D(x, y)
    fun createMutablePoint(x: Double = 0.0, y: Double = 0.0): MutablePoint2<Double> = Point2D(x, y)
    override fun createPoint(x: Number, y: Number): Point2<Double> = Point2D(x, y)
    override fun createMutablePoint(x: Number, y: Number): MutablePoint2<Double> = Point2D(x, y)
    override fun createPoint(src: Tuple2<*>): Point2<Double> = Point2D(src)
    override fun createMutablePoint(src: Tuple2<*>): MutablePoint2<Double> = Point2D(src)

    /** Vector2D creators  **/
    fun createVector(x: Double = 0.0, y: Double = 0.0): Vector2<Double> = Vector2D(x, y)
    fun createMutableVector(x: Double = 0.0, y: Double = 0.0): MutableVector2<Double> = Vector2D(x, y)
    override fun createVector(x: Number, y: Number): Vector2<Double> = Vector2D(x, y)
    override fun createMutableVector(x: Number, y: Number): MutableVector2<Double> = Vector2D(x, y)
    override fun createVector(src: Tuple2<*>): Vector2<Double> = Vector2D(src)
    override fun createMutableVector(src: Tuple2<*>): MutableVector2<Double> = Vector2D(src)

    /** Rect2D creators  **/
    override fun createRect(bottomLeft: Tuple2<*>, topRight: Tuple2<*>): Rect2<Double> =
        Rect2D.create(bottomLeft, topRight)

    override fun createMutableRect(bottomLeft: Tuple2<*>, topRight: Tuple2<*>): MutableRect2<Double> =
        Rect2D.create(bottomLeft, topRight)

    override fun createRect(center: Tuple2<*>, width: Number, height: Number): Rect2<Double> =
        Rect2D.create(center, width, height)

    override fun createMutableRect(center: Tuple2<*>, width: Number, height: Number): MutableRect2<Double> =
        Rect2D.create(center, width, height)

    override fun createRect(width: Number, height: Number): Rect2<Double> = Rect2D.create(width, height)
    override fun createMutableRect(width: Number, height: Number): MutableRect2<Double> = Rect2D.create(width, height)
    override fun createRect(top: Number, left: Number, right: Number, bottom: Number): Rect2<Double> =
        Rect2D.create(top, left, right, bottom)

    override fun createMutableRect(top: Number, left: Number, right: Number, bottom: Number): MutableRect2<Double> =
        Rect2D.create(top, left, right, bottom)
}

class Geom2FFactory : Geom2Factory<Float> {
    /** Point2F creators  **/
    fun createPoint(x: Float = 0f, y: Float = 0f): Point2<Float> = Point2F(x, y)
    fun createMutablePoint(x: Float = 0f, y: Float = 0f): MutablePoint2<Float> = Point2F(x, y)
    override fun createPoint(x: Number, y: Number): Point2<Float> = Point2F(x, y)
    override fun createMutablePoint(x: Number, y: Number): MutablePoint2<Float> = Point2F(x, y)
    override fun createPoint(src: Tuple2<*>): Point2<Float> = Point2F(src)
    override fun createMutablePoint(src: Tuple2<*>): MutablePoint2<Float> = Point2F(src)

    /** Vector2F creators  **/
    fun createVector(x: Float = 0f, y: Float = 0f): Vector2<Float> = Vector2F(x, y)
    fun createMutableVector(x: Float = 0f, y: Float = 0f): MutableVector2<Float> = Vector2F(x, y)
    override fun createVector(x: Number, y: Number): Vector2<Float> = Vector2F(x, y)
    override fun createMutableVector(x: Number, y: Number): MutableVector2<Float> = Vector2F(x, y)
    override fun createVector(src: Tuple2<*>): Vector2<Float> = Vector2F(src)
    override fun createMutableVector(src: Tuple2<*>): MutableVector2<Float> = Vector2F(src)

    /** Rect2F creators  **/
    override fun createRect(bottomLeft: Tuple2<*>, topRight: Tuple2<*>): Rect2<Float> =
        Rect2F.create(bottomLeft, topRight)

    override fun createMutableRect(bottomLeft: Tuple2<*>, topRight: Tuple2<*>): MutableRect2<Float> =
        Rect2F.create(bottomLeft, topRight)

    override fun createRect(center: Tuple2<*>, width: Number, height: Number): Rect2<Float> =
        Rect2F.create(center, width, height)

    override fun createMutableRect(center: Tuple2<*>, width: Number, height: Number): MutableRect2<Float> =
        Rect2F.create(center, width, height)

    override fun createRect(width: Number, height: Number): Rect2<Float> = Rect2F.create(width, height)
    override fun createMutableRect(width: Number, height: Number): MutableRect2<Float> = Rect2F.create(width, height)
    override fun createRect(top: Number, left: Number, right: Number, bottom: Number): Rect2<Float> =
        Rect2F.create(top, left, right, bottom)

    override fun createMutableRect(top: Number, left: Number, right: Number, bottom: Number): MutableRect2<Float> =
        Rect2F.create(top, left, right, bottom)
}

class Geom2IFactory : Geom2Factory<Int> {
    /** Point2I creators  **/
    fun createPoint(x: Int = 0, y: Int = 0): Point2<Int> = Point2I(x, y)
    fun createMutablePoint(x: Int = 0, y: Int = 0): MutablePoint2<Int> = Point2I(x, y)
    override fun createPoint(x: Number, y: Number): Point2<Int> = Point2I(x, y)
    override fun createMutablePoint(x: Number, y: Number): MutablePoint2<Int> = Point2I(x, y)
    override fun createPoint(src: Tuple2<*>): Point2<Int> = Point2I(src)
    override fun createMutablePoint(src: Tuple2<*>): MutablePoint2<Int> = Point2I(src)

    /** Vector2I creators  **/
    fun createVector(x: Int = 0, y: Int = 0): Vector2<Int> = Vector2I(x, y)
    fun createMutableVector(x: Int = 0, y: Int = 0): MutableVector2<Int> = Vector2I(x, y)
    override fun createVector(x: Number, y: Number): Vector2<Int> = Vector2I(x, y)
    override fun createMutableVector(x: Number, y: Number): MutableVector2<Int> = Vector2I(x, y)
    override fun createVector(src: Tuple2<*>): Vector2<Int> = Vector2I(src)
    override fun createMutableVector(src: Tuple2<*>): MutableVector2<Int> = Vector2I(src)

    /** Rect2I creators  **/
    override fun createRect(bottomLeft: Tuple2<*>, topRight: Tuple2<*>): Rect2<Int> =
        Rect2I.create(bottomLeft, topRight)

    override fun createMutableRect(bottomLeft: Tuple2<*>, topRight: Tuple2<*>): MutableRect2<Int> =
        Rect2I.create(bottomLeft, topRight)

    override fun createRect(center: Tuple2<*>, width: Number, height: Number): Rect2<Int> =
        Rect2I.create(center, width, height)

    override fun createMutableRect(center: Tuple2<*>, width: Number, height: Number): MutableRect2<Int> =
        Rect2I.create(center, width, height)

    override fun createRect(width: Number, height: Number): Rect2<Int> = Rect2I.create(width, height)
    override fun createMutableRect(width: Number, height: Number): MutableRect2<Int> = Rect2I.create(width, height)
    override fun createRect(top: Number, left: Number, right: Number, bottom: Number): Rect2<Int> =
        Rect2I.create(top, left, right, bottom)

    override fun createMutableRect(top: Number, left: Number, right: Number, bottom: Number): MutableRect2<Int> =
        Rect2I.create(top, left, right, bottom)
}
