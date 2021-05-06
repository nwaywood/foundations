package exercises.dataprocessing

object ForLoopExercises {

  def sum(numbers: List[Int]): Int = reduce(numbers, 0)(_ + _)

  // a. Implement `size` using a mutable state and a for loop
  // such as size(List(2,5,1,8)) == 4
  // and     size(Nil) == 0
  def size[A](items: List[A]): Int =
    reduce(items, 0)((acc, _) => acc + 1)
  // Old way without reduce
  // {
  //   var total = 0
  //   for (item <- items)
  //     total = total + 1
  //   total
  // }

  // b. Implement `min` using a mutable state and a for loop
  // such as min(List(2,5,1,8)) == Some(1)
  // and     min(Nil) == None
  // Note: Option is an enumeration with two values:
  // * Some when there is a value and
  // * None when there is no value (a bit like null)
  def min(numbers: List[Int]): Option[Int] =
    // reduce(numbers, Option.empty[Int])((acc, num) =>
    //   acc match {
    //     case Some(x) => Some(x min num)
    //     case None    => Some(num)
    //   }
    // )
    // Sugar
    reduce(numbers, Option.empty[Int]) {
      case (Some(x), num) => Some(x min num)
      case (None, num)    => Some(num)
    }

  // c. Implement `wordCount` using a mutable state and a for loop.
  // `wordCount` compute how many times each word appears in a `List`
  // such as wordCount(List("Hi", "Hello", "Hi")) == Map("Hi" -> 2, "Hello" -> 1)
  // and     wordCount(Nil) == Map.empty
  // Note: You can lookup an element in a `Map` with the method `get`
  // and you can upsert a value using `updated`
  def wordCount(words: List[String]): Map[String, Int] = {
    var m = Map.empty[String, Int]
    for (w <- words)
      m.get(w) match {
        case Some(count) => m = m.updated(w, count + 1)
        case None        => m = m.updated(w, 1)
      }
    m
  }

  // d. `sum`, `size`, `min` and `wordCount` are quite similar.
  // Could you write a higher-order function that captures this pattern?
  // How would you call it?
  def reduce[T, Acc](list: List[T], initialValue: Acc)(f: (Acc, T) => Acc): Acc = {
    var acc = initialValue
    for (x <- list)
      acc = f(acc, x)
    acc
  }

  // e. Refactor `sum`, `size`, `min` and `wordCount` using the higher-order
  // function you defined above.

  //////////////////////////////////////////////
  // Bonus question (not covered by the video)
  //////////////////////////////////////////////

  // f. `foldLeft` can be used to implement most of the List API.
  // Do you want to give it a try? For example, can you implement
  // `map`, `reverse` and `lastOption` in terms of `foldLeft`
  def map[From, To](elements: List[From])(update: From => To): List[To] =
    reduce(elements, List.empty[To])((acc, element) => acc :+ update(element))

  // reverse(List(3,8,1)) == List(1,8,3)
  // reverse(Nil) == Nil
  def reverse[A](elements: List[A]): List[A] =
    reduce(elements, List.empty[A])((acc, element) => element :: acc)

  // lastOption(List(3,8,1)) == Some(1)
  // lastOption(Nil) == None
  def lastOption[A](elements: List[A]): Option[A] =
    ???

  // g. Can you generalise `min` so that it applies to more types like `Long`, `String`, ...?
  // Note: You may want to use the class Ordering from the standard library
  def generalMin[A](elements: List[A]): Option[A] =
    ???

}
