package gaurav

import java.util.concurrent.{Executors, ScheduledExecutorService, TimeUnit}

import scala.concurrent.{ExecutionContext, Future, Promise}

class Rbi {

  private val service: ScheduledExecutorService = Executors.newScheduledThreadPool(1)
  def notification(): Future[Unit] = {

    val p: Promise[Unit] = Promise()
    service.schedule(() => p.success(()), 100, TimeUnit.MILLISECONDS);
    p.future
  }

}
