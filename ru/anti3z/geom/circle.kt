package ru.anti3z.geom

interface Circle2<out T : Number> {
    val center: Point2<T>
    val radius: T
    val diam: T

    operator fun contains(pnt: Tuple2<*>): Boolean
    fun collision(other: Rect2<*>): Boolean
    fun collision(other: Circle2<*>): Boolean
}

interface MutableCircle2<T : Number> : Circle2<T> {
    override var center: Point2<T>
    override var radius: T
    override var diam: T

    fun setCenter(x: T = center.x, y: T = center.y )
}

sealed class Circle2Base<T : Number>(center: Tuple2<T>, override var radius: T) : MutableCircle2<T> {
    private val centerPoint: MutablePoint2<T> = center.toMutablePoint2()

    override var center: Point2<T>
        get() = centerPoint
        set(value) { centerPoint.set(value) }

    @Suppress("UNCHECKED_CAST")
    override var diam: T
        get() = when(this) {
            is Circle2D -> radius * 2
            is Circle2F -> radius * 2
            is Circle2I -> radius * 2
        } as T
        set(value) {
            radius = when(this) {
                is Circle2D -> value as Double / 2
                is Circle2F -> value as Float / 2
                is Circle2I -> value as Int / 2
            } as T
        }

    override fun setCenter(x: T, y: T) {
        centerPoint.x = x
        centerPoint.y = y
    }

    override fun contains(pnt: Tuple2<*>): Boolean {
        val point = when(this) {
            is Circle2D -> Point2D(pnt)
            is Circle2F -> Point2F(pnt)
            is Circle2I -> Point2I(pnt)
        }
        return centerPoint.distance(point) <= radius.toDouble()
    }

    override fun collision(other: Rect2<*>): Boolean = collision(other, this)

    override fun collision(other: Circle2<*>): Boolean = collision(this, other)
}

class Circle2D(center: Point2<*>, radius: Number) : Circle2Base<Double>(center.toDouble(), radius.toDouble())
class Circle2F(center: Tuple2<*>, radius: Number) : Circle2Base<Float>(center.toFloat(), radius.toFloat())
class Circle2I(center: Tuple2<*>, radius: Number) : Circle2Base<Int>(center.toInt(), radius.toInt())