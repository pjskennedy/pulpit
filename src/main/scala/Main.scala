import org.jboss.netty.handler.codec.http.{HttpRequest, HttpResponse}
import com.twitter.finagle.builder.ServerBuilder
import com.twitter.finagle.http.{Http, Response}
import com.twitter.finagle.Service
import com.twitter.util.Future
import java.net.InetSocketAddress
import util.Properties
import com.pulpit.StatusStreamer
import com.pulpit.models.TweetWord

object PulpitStreamer {
  def main(args: Array[String]) {
    StatusStreamer(10000)
  }
}

object PulpitServer {
  def main(args: Array[String]) {
    val port = Properties.envOrElse("PORT", "8080").toInt
    println("Starting on port:"+port)
    ServerBuilder()
      .codec(Http())
      .name("pulpit-server")
      .bindTo(new InetSocketAddress(port))
      .build(new PulpitServerResponder)
    println("Started.")
  }
}

class PulpitServerResponder extends Service[HttpRequest, HttpResponse] {
  def apply(req: HttpRequest): Future[HttpResponse] = {
    val response = Response()
    response.setStatusCode(200)
    response.setContentTypeJson
    response.setContentString(TweetWord.getTopJSON(20a0))
    Future(response)
  }
}