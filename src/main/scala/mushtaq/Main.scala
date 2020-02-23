package mushtaq

import java.util.concurrent.Executors

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors

import scala.concurrent.ExecutionContext

object Main {

  def main(args: Array[String]): Unit = {

    implicit val actorSystem: ActorSystem[Nothing] = ActorSystem(Behaviors.empty, "demo")
    implicit val ec: ExecutionContext = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(1))
    val timerService = new TimerService()
    var account: Account = new Account(new RbiStub(timerService))

    account.stream.runForeach(println)

    account.deposit(100)
    account.deposit(200)
    account.withdraw(130)

  }

}
