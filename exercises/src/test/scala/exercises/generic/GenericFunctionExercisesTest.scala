package exercises.generic

import java.time.LocalDate
import java.time.format.DateTimeFormatter

import exercises.generic.GenericFunctionExercises._
import org.scalacheck.{Arbitrary, Gen}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks

import scala.util.Try

class GenericFunctionExercisesTest extends AnyFunSuite with ScalaCheckDrivenPropertyChecks {

  ////////////////////
  // Exercise 1: Pair
  ////////////////////

  test("Pair swap") {
    forAll { (a: String, b: String) =>
      val p = Pair(a, b)
      assert(p.swap == Pair(b, a))
    }
  }

  test("Pair map") {
    assert(Pair("John", "Doe").map(_.length) == Pair(4, 3))
  }

  test("Pair zipWith") {
    assert(Pair(0, 2).zipWith(Pair(3, 4))((x, y) => x + y) == Pair(3, 6))
  }

  test("Pair productNames") {
    assert(products == Pair(Product("Coffee", 2.5), Product("Plane ticket", 329.99)))
  }

  ////////////////////////////
  // Exercise 2: Predicate
  ////////////////////////////

  // nice way: property tests
  test("Predicate &&") {
    forAll { (eval: Int => Boolean, value: Int) =>
      val p = Predicate(eval)

      def False[A]: Predicate[A] = Predicate[A](_ => false)
      def True[A]: Predicate[A]  = Predicate[A](_ => true)

      assert((p && False)(value) == false)
      assert((p && True)(value) == p(value))
    }
  }

  // bad way: specific tests
  test("Predicate ||") {
    assert((isEven || isPositive)(12) == true)
    assert((isEven || isPositive)(-7) == false)
  }

  test("Predicate flip") {
    assert(isEven.flip(11) == true)
  }

  test("Predicate isValidUser") {
    assert(isValidUser(User("John", 20)) == true)
    assert(isValidUser(User("John", 17)) == false)
  }

  ////////////////////////////
  // Exercise 3: JsonDecoder
  ////////////////////////////

  test("JsonDecoder UserId") {
    assert(userIdDecoder.decode("1234") == UserId(1234))
    assert(Try(userIdDecoder.decode("hello")).isFailure)
  }

  test("JsonDecoder LocalDate") {
    assert(localDateDecoder.decode("\"2020-03-26\"") == LocalDate.of(2020, 3, 26))
  }

  test("JsonDecoder weirdLocalDateDecoder") {}

}
