package ru.anti3z.geom

interface Tuple2<out T : Number> {
    val x: T
    val y: T

    fun toDouble(): Tuple2<Double>
    fun toFloat(): Tuple2<Float>
    fun toInt(): Tuple2<Int>
}

interface MutableTuple2<T : Number> : Tuple2<T> {
    override var x: T
    override var y: T
}

interface Tuple3<out T : Number> : Tuple2<T> {
    val z: T

    override fun toDouble(): Tuple3<Double>
    override fun toFloat(): Tuple3<Float>
    override fun toInt(): Tuple3<Int>
}

interface MutableTuple3<T : Number> : Tuple3<T> {
    override var z: T
}