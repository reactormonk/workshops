import org.scalatest.FunSuite

import cats.laws.discipline._
import cats.kernel.laws.discipline._
import cats._
import cats.implicits._

import org.typelevel.discipline.scalatest.Discipline

trait Fold[I, O] {
  type M
  def tally(i: I): M
  def summarize(m: M): O
  def monoid: Monoid[M]
}

class FoldExercises extends FunSuite with Discipline {

  // -- Monoid

  // Normally this'd be implicit, but if we do it here, it'd mess with the implicit resolution
  val monoidInt = new Monoid[Int] {
    def empty: Int = ???
    def combine(x: Int, y: Int): Int = ???
  }

  // checkAll("MonoidInt", MonoidTests[Int](monoidInt).monoid)

  val monoidString = new Monoid[String] {
    def empty: String = ???
    def combine(x: String, y: String): String = ???
  }

  // checkAll("MonoidString", MonoidTests[String](monoidString).monoid)

  // def monoidTuple2(implicit ...) = new Monoid[Tuple2] {
  //   def empty: Tuple2 = ???
  //   def combine(x: Tuple2, y: Tuple2): Tuple2 = ???
  // }

  // checkAll("MonoidTuple2", MonoidTests[Tuple2](monoidTuple2).monoid)

  // def monoidMap() = new Monoid[Map] {
  //   def empty: Map = ???
  //   def combine(x: Map, y: Map): Map = ???
  // }

  // checkAll("MonoidMap", MonoidTests[Map](monoidMap).monoid)

  // -- Fold


  def runList[I, O](fold: Fold[I, O], data: List[I]): O = {
    ???
  }

  test("runList") {
    pending
    val stringFold = new Fold[String, String] {
      type M = String
      def tally(i: String): String = i
      def summarize(m: String): String = m
      val monoid = implicitly
    }
    assert(runList(stringFold, List("foo", "bar", "baz")) == "foobarbaz")
  }

  val sumFold = new Fold[Int, Int] {
    type M = Unit
    def tally(i: Int): M = ???
    def summarize(m: M): Int = ???
    def monoid: Monoid[M] = implicitly
  }

  test("sumFold") {
    pending
    assert(runList(sumFold, List(1, 2, 3)) == 6)
  }

  val maxFold = new Fold[Int, Int] {
    type M = Unit
    def tally(i: Int): M = ???
    def summarize(m: M): Int = ???
    def monoid: Monoid[M] = implicitly
  }

  test("maxFold") {
    pending
    assert(runList(sumFold, List(1, 2, 3, 2)) == 3)
  }

  val averageFold = new Fold[Int, Double] {
    type M = Unit
    def tally(i: Int): M = ???
    def summarize(m: M): Double = ???
    def monoid: Monoid[M] = implicitly
  }

  test("averageFold") {
    pending
    assert(runList(averageFold, List(1, 2, 3, 2)) == 2.0)
  }

  val wcFold = new Fold[String, Map[String, Int]] {
    type M = Unit
    def tally(i: String): M = ???
    def summarize(m: Unit): Map[String,Int] = ???
    def monoid: Monoid[M] = implicitly
  }

  test("wcFold") {
    pending
    assert(runList(wcFold, List("foo", "foo", "bar", "baz")) == Map("foo" -> 2, "bar" -> 1, "baz" -> 1))
  }

  test("foldMap") {
    pending
    // val list = List(1, 2, 3, 2)
    // assert(list.foldMap(???) == 8) // sum
    // assert(list.foldMap(???) == 2) // max
    // assert(list.foldMap(???) == 2) // average
    // val wordList = List("foo", "foo", "bar", "baz")
    // assert(wordList.foldMap(???) == Map("foo" -> 2, "bar" -> 1, "baz" -> 1)) // wordcount
  }

}
