package ru.anti3z.geom

interface Tuple2<out T : Number> {
    val x: T
    val y: T

    fun toDouble(): Tuple2<Double>
    fun toFloat(): Tuple2<Float>
    fun toInt(): Tuple2<Int>
}

interface Tuple3<out T : Number> : Tuple2<T> {
    val z: T
}
