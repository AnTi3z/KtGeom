package ru.anti3z.geom

interface Tuple2<T : Number> {
    var x: T
    var y: T

    fun toDouble(): Tuple2<Double>
    fun toFloat(): Tuple2<Float>
    fun toInt(): Tuple2<Int>
}

interface Tuple3<T : Number> : Tuple2<T> {
    var z: T

    override fun toDouble(): Tuple3<Double>
    override fun toFloat(): Tuple3<Float>
    override fun toInt(): Tuple3<Int>
}
