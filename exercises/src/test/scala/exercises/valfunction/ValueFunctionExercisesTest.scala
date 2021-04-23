package exercises.valfunction

import exercises.valfunction.ValueFunctionExercises._
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks

class ValueFunctionExercisesTest extends AnyFunSuite with ScalaCheckDrivenPropertyChecks {

  /////////////////////////////////////////////////////
  // Exercise 1: String API with higher-order functions
  /////////////////////////////////////////////////////

  // replace `ignore` by `test` to enable the test
  test("selectDigits examples") {
    assert(selectDigits("hello4world-80") == "480")
    assert(selectDigits("welcome") == "")
  }

  // replace `ignore` by `test` to enable the test
  test("selectDigits length is smaller") {
    forAll { (text: String) =>
      assert(selectDigits(text).length <= text.length)
    }
  }

  test("secret examples") {
    assert(secret("hello") == "*****")
    assert(secret("") == "")
  }

  test("secret length is always the same") {
    forAll { (text: String) =>
      assert(secret(text).length == text.length)
    }
  }

  test("isValidUsername examples") {
    assert(isValidUsername("hello") == true)
    assert(isValidUsername("hello**") == false)
  }

  ///////////////////////
  // Exercise 2: Point
  ///////////////////////
  test("Point examples") {
    assert(Point(1, 1, 1).isEven == false)
    assert(Point(1, 1, 1).isPositive == true)
    forAll { (x: Int, y: Int, z: Int, p: Int => Boolean) =>
      // we assume forall list implementation in the std lib is correct
      assert(Point(x, y, z).forAll(p) == List(x, y, z).forall(p))
    }
  }

}
