package ru.anti3z.geom

fun collision(rect: Rect2<*>, circ: Circle2<*>): Boolean {
    val nearest = Point2D(circ.center)
    nearest.x = nearest.x.coerceIn(rect.left.toDouble(), rect.right.toDouble())
    nearest.y = nearest.y.coerceIn(rect.bottom.toDouble(), rect.top.toDouble())

    return circ.center.distance(nearest) <= circ.radius.toDouble()
}

fun collision(rect1: Rect2<*>, rect2: Rect2<*>): Boolean {
    return rect1.right.toDouble() >= rect2.left.toDouble() &&
            rect1.left.toDouble() <= rect2.right.toDouble() &&
            rect1.bottom.toDouble() <= rect2.top.toDouble() &&
            rect1.top.toDouble() >= rect2.bottom.toDouble()
}

fun collision(circ1: Circle2<*>, circ2: Circle2<*>): Boolean {
    return circ1.center.distance(circ2.center) <= circ1.radius.toDouble() + circ2.radius.toDouble()
}
