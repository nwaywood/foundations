package exercises.dataprocessing

import exercises.dataprocessing.ForLoopExercises._
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks

class ForLoopExercisesTest extends AnyFunSuite with ScalaCheckDrivenPropertyChecks {

  test("sum") {
    assert(sum(List(1, 5, 2)) == 8)
    assert(sum(Nil) == 0)
  }

  test("sum is consistent with List sum") {
    forAll { (numbers: List[Int]) =>
      assert(sum(numbers) == numbers.sum)
    }
  }

  test("size") {
    assert(size(List(2, 5, 1, 8)) == 4)
    assert(size(Nil) == 0)
  }

  test("min") {
    assert(min(List(2, 5, 1, 8)) == Some(1))
    assert(min(Nil) == None)
  }

  test("wordCount") {
    assert(wordCount(List("Hi", "Hello", "Hi")) == Map("Hi" -> 2, "Hello" -> 1))
    assert(wordCount(List("Hi", "Hello", "Hi")).size == Set.from(List("Hi", "Hello", "Hi")).size)
    assert(wordCount(Nil) == Map.empty)
  }

  test("wordCount pbt") {
    forAll { (words: List[String]) =>
      assert(wordCount(words).size == Set.from(words).size)
    }
  }

  // since reduce (foldLeft) is a fully parametric function, this one test is sufficient to know its implementation must be correct
  test("foldLeft noop") {
    forAll { (numbers: List[Int]) =>
      assert(reduce(numbers, List.empty[Int])(_ :+ _) == numbers)
    }
  }

  test("map consistent with List map") {
    forAll { (numbers: List[Int], update: Int => Int) =>
      assert(map(numbers)(update) == numbers.map(update))
    }
  }

  test("reverse consistent with List reverse") {
    forAll { (numbers: List[Int]) =>
      assert(reverse(numbers) == numbers.reverse)
    }
  }

}
