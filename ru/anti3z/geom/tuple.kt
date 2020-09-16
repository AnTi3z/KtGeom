package ru.anti3z.geom

interface Tuple2<out T : Number> {
    val x: T
    val y: T

    operator fun component1() = x
    operator fun component2() = y

    fun toDouble(): Tuple2<Double>
    fun toFloat(): Tuple2<Float>
    fun toInt(): Tuple2<Int>
}

interface MutableTuple2<T : Number> : Tuple2<T> {
    override var x: T
    override var y: T

    override fun toDouble(): MutableTuple2<Double>
    override fun toFloat(): MutableTuple2<Float>
    override fun toInt(): MutableTuple2<Int>

    fun set(src: Tuple2<Number>)
    fun set(x: Number = this.x, y: Number = this.y)
}

interface Tuple3<out T : Number> : Tuple2<T> {
    val z: T

    operator fun component3() = z

    override fun toDouble(): Tuple3<Double>
    override fun toFloat(): Tuple3<Float>
    override fun toInt(): Tuple3<Int>
}

interface MutableTuple3<T : Number> : Tuple3<T> {
    override var z: T

    override fun toDouble(): MutableTuple3<Double>
    override fun toFloat(): MutableTuple3<Float>
    override fun toInt(): MutableTuple3<Int>

    fun set(src: Tuple3<Number>)
    fun set(x: Number = this.x, y: Number = this.y, z: Number = this.z)
}
