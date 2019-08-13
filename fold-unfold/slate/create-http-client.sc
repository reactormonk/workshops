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

import monix.eval._
object monixDSL extends Http4sClientDsl[monix.eval.Task]
import monixDSL._
import monix.execution.Scheduler.Implicits.global

// import zio._
// object zioDSL extends Http4sClientDsl[Task]
// import zioDSL._
// import scala.concurrent.ExecutionContext
// import zio.interop.catz.implicits._

// Proper way: https://http4s.org/v0.20/client/#creating-the-client
val blockingEC = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(5))
val httpClient: Client[Task] = JavaNetClientBuilder[Task](blockingEC).create

case class Cursors(next: Option[String])
case class Paginated[T](cursors: Cursors, items: List[T])
case class Post(id: Int, guid: String, author: String, title: String)

val firstPage = httpClient.expect[Paginated[Post]]("http://localhost:3000/posts?_page=1")

def nextUri(path: String) = Uri.uri("http://localhost:3000/").withPath(path)


// firstPage.runSyncUnsafe()
// def stream: Observable[Page] = ???
// stream.toListL.runSyncUnsafe()

//
