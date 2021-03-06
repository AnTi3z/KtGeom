import ru.anti3z.geom.*
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue


fun main() {
    val geom2DFactory = Geom2DFactory()
    val geom2FFactory = Geom2FFactory()
    val geom2IFactory = Geom2IFactory()

    //Point2D Primary constructor
    val p1d = Point2D(1.5, 2.6)
    println("$p1d")
    assertEquals(1.5, p1d.x)
    assertEquals(2.6, p1d.y)

    //Point2D Secondary constructor
    val p2f = Point2F(5, 4.0f)
    println("$p2f")
    assertEquals(5.0f, p2f.x)
    assertEquals(4.0f, p2f.y)

    //Point2D Factory method
    val p3i = geom2IFactory.createPoint(p2f)
    println("$p3i")
    assertEquals(5, p3i.x)
    assertEquals(4, p3i.y)


    //Vector2D Primary constructor
    val v1d = Vector2D(2.0, 5.0)
    println("$v1d")
    assertEquals(2.0, v1d.x)
    assertEquals(5.0, v1d.y)

    //Vector2F Secondary constructor
    val v2f = Vector2F(3, 4.0)
    println("$v2f")
    assertEquals(3.0f, v2f.x)
    assertEquals(4.0f, v2f.y)

    //Vector2I Factory method
    val v3i = geom2IFactory.createVector(p2f)
    println("$v3i")
    assertEquals(5, v3i.x)
    assertEquals(4, v3i.y)

    //Point + Vector
    val p4 = (p1d + v2f) //Point2D
    println("$p4")
    assertEquals(4.5, p4.x)
    assertEquals(6.6, p4.y)

    //Vector + Vector
    val v4 = v1d + v2f
    assertEquals(5.0, v4.x)
    assertEquals(9.0, v4.y)


    val b1d = geom2DFactory.createMutableRect(v1d, 5, 5)
    println("$b1d")
    println("${b1d.top}")
    b1d.top = 8.0
    println("$b1d")
    assertTrue { Point2I(3, 3) in b1d }
    assertTrue { Point2D(4.5, 7.5) in b1d }
    val b2d = geom2DFactory.createRect(10, 10)
    println("$b2d")
    assertFalse { Point2I(15, 5) in b2d }
    val b3d = geom2DFactory.createRect(Point2D(2, 2.4), Vector2I(5, 6))
    println("$b3d")
    assertEquals(2.0, b3d.left)
    assertEquals(2.4, b3d.bottom)
    assertEquals(5.0, b3d.right)
    assertEquals(6.0, b3d.top)


    println("${v1d.length}")
    println("${v1d.lengthened(3.0)}")
    println("${v1d.normalized()}")
    println("${v1d * 2.0}")
    println("${v1d * v2f}")
    println("${b1d.width}")

    v1d.length = 4.0
    println("$v1d")
    println("${v1d.length}")

    v2f *= 2.0
    v1d += v2f

    println("$v2f")

    val v5 = v2f.scaled(3.0)
    println("$v5")

    val z = Point2D()
    z += v1d

    println("-------")
    val target = geom2FFactory.createVector(5.0, 5.0)
    val position = geom2FFactory.createPoint(1.0, 1.0)
    val speed = geom2FFactory.createMutableVector(0.0, 0.0)
    val tmp1 = target - position.toVector2()
    println(tmp1)
    var tmp2 = (target - position.toVector2()) / 10f
    println(tmp2)
    tmp2 = (target - position.toVector2()) * 10f
    println(tmp2)
    tmp2 = (target - position.toVector2()) / 1.5f
    println(tmp2)
    speed.set(tmp2)
    println(speed * 0.5)
    println(speed * 1)
    println(speed * 10)
    println("!!!!!!!!")
    val r2d = Rect2D(Point2D(5, 1), Point2D(1, 10))
    r2d.top = 11.0
    val r2d2 = Rect2D(Point2D(1.0, 1.0), Point2D(5.0, 11.0))
    println(r2d)
    println(r2d == r2d2)
}
