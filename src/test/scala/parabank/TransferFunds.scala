package parabank

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import parabank.Data._

class TransferFunds extends Simulation{

   // 1 Http Conf
  val httpConf = http.baseUrl(url)
    .acceptHeader("application/json")
    //Verificar de forma general para todas las solicitudes
    .check(status.is(200))

  // 2 Scenario Definition
  val scn = scenario("TransferFunds").
    exec(http("TransferFunds")
      .post("/transfer")  
      .queryParam("fromAccountId", fromAccountId)
      .queryParam("toAccountId", toAccountId)
      .queryParam("amount", amount)
      .check(status.is(200))
       )
  
// 3 Load Scenario
  setUp(
    scn.inject(atOnceUsers(60))
  ).protocols(httpConf);
}
