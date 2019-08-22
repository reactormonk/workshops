
import org.scalatest.FunSuite
import cats._
import cats.implicits._

class UnfoldExercises extends FunSuite {

  def unfold[A, B](initial: A)(fun: A => Option[(A, B)]): Stream[B] = ???
  def unfold0[A](initial: A)(fun: A => Option[A]): Stream[A] = ???
  def unfoldE[A, B](initial: A)(fun: A => Stream[Either[A, B]]): Stream[B] = ???

  // Try will all three each

  def leapYears(from: Int): Stream[Int] = ???

  test("leapYears") {
    pending
    assert(leapYears(1990).take(8).toList == List(1992, 1996, 2000, 2004, 2008, 2012, 2016, 2020))
  }

  def collatz(from: Int): Stream[Int] = ???

  test("collatz") {
    pending
    assert(collatz(12).toList == List(12, 6, 3, 10, 5, 16, 8, 4, 2, 1))
  }

  def fibonacci: Stream[Int] = ???

  test("fibonacci") {
    pending
    assert(fibonacci.take(10).toList == List(1, 1, 2, 3, 5, 8, 13, 21, 34, 55))
  }

  def tokenize(input: String): List[String] = ???

  test("tokenize") {
    pending
    assert(tokenize("This is a test") == List("This", "is", "a", "test"))
  }

}