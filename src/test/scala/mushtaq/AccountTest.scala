package mushtaq

import java.util.concurrent.Executors

import org.scalatest.funsuite.AnyFunSuite

import scala.concurrent.duration.DurationDouble
import scala.concurrent.{Await, ExecutionContext, ExecutionContextExecutorService, Future}

class AccountTest extends AnyFunSuite {

  implicit val ec: ExecutionContext = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(1))

  private val timerService = new TimerService()
  test("demo") {
    val account = new Account(new RbiStub(timerService))

    val depositFutures: Seq[Future[Unit]] = (1 to 1000).map { _ => account.deposit(100) }

    val withdrawalFutures: Seq[Future[Unit]] = (1 to 1000).map { _ => account.withdraw(100) }

    val allFutures: Seq[Future[Unit]] = depositFutures ++ withdrawalFutures

    val future: Future[Seq[Unit]] = Future.sequence(allFutures)
//
//    val value = Await.result(future, 5.seconds)
//    println(value)

//    Thread.sleep(1000)
//    println(Await.result(account.getBalance, 5.seconds))

  }

  test("timeout") {
    val future = timerService.timeout(100.millis)
    val future2 = timerService.completeWithin(future, 50.millis)
//    Await.result(future2, 5.seconds)
    Thread.sleep(5000)
  }

}
