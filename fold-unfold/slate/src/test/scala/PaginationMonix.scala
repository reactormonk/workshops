import org.scalatest.FunSuite

import cats._
import cats.implicits._

import _root_.io.circe._
import org.http4s._
import org.http4s.implicits._
import org.http4s.server.blaze._
import org.http4s.client.blaze._
import org.http4s.client._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.ExecutionContext
import java.util.concurrent._
import org.http4s.circe._

import org.http4s.circe.CirceEntityCodec._
import _root_.io.circe.generic.auto._
import org.http4s.client.dsl._

case class Cursors(next: Option[String])
case class Paginated[T](cursors: Cursors, items: List[T])
case class Post(id: Int, guid: String, author: String, title: String)

class PaginationMonix extends FunSuite {

  import monix.eval._
  import monix.reactive._

  object monixDSL extends Http4sClientDsl[monix.eval.Task]
  import monixDSL._
  import monix.execution.Scheduler.Implicits.global

  // Proper way: https://http4s.org/v0.20/client/#creating-the-client
  val blockingEC = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(5))
  val httpClient: Client[Task] = JavaNetClientBuilder[Task](blockingEC).create

  val firstPage = httpClient.expect[Paginated[Post]]("http://localhost:3000/posts?_page=1")

  def nextUri(path: String) = Uri.uri("http://localhost:3000/").withPath(path)

  test("firstPage") {
    pending
    // To test if everything works correctly
    assert(firstPage.runSyncUnsafe() == firstPageExpected)
  }
  def stream: Observable[Post] = ???

  test("pagination") {
    pending
    assert(stream.toListL.runSyncUnsafe().last == Post(99, "11bb3ff7-da60-4ccf-83f7-0db3bdb2ece8", "Downs", "MAXEMIA"))
  }

  val firstPageExpected = Paginated(Cursors(Some("/posts_1")),List(Post(0,"b678b63f-0f39-49d0-bc68-f0203477da28","Brennan","KAGE"), Post(1,"e96a5208-f666-4cba-969d-ffce64929590","Alicia","ACUSAGE"), Post(2,"de29c249-fbed-429c-bc62-aaad590e615d","Schneider","FUTURIS"), Post(3,"b6b75600-3202-4078-a303-4f20b0c14954","Mccullough","PLAYCE"), Post(4,"1ae03297-52e9-4e02-ae3d-8278503b371f","Jamie","GONKLE"), Post(5,"a36b80f5-d82e-44be-88d1-9e7382902bd3","Molina","PLEXIA"), Post(6,"ec95220a-20f6-459b-beba-ff48b9c90435","Lynn","EMPIRICA"), Post(7,"14bbd180-ab38-4e5f-95cc-cc35fcc610bd","Martin","BEADZZA"), Post(8,"72b41241-a92c-486f-9439-d9bc6c8aab91","Concepcion","KOZGENE"), Post(9,"55986139-3ab1-4dc4-8652-54dc2e71c499","Carlson","HYDROCOM"))) 
}