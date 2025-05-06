package simulations

import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._


class TaskApiSimulation extends Simulation {

  val httpProtocol = http
    .baseUrl("http://localhost:8080") // Change to match your base URL
    .acceptHeader("application/json")
    .contentTypeHeader("application/json")

  val feeder = Iterator.continually(Map(
    "title" -> ("Task " + scala.util.Random.alphanumeric.take(5).mkString),
    "description" -> "Load test task",
    "completed" -> false
  ))

  val createTask = exec(
    http("POST Create Task")
      .post("/api/tasks")
      .body(StringBody(
        """{
          |"title": "${title}",
          |"description": "${description}",
          |"completed": ${completed}
        }""".stripMargin)).asJson
      .check(status.is(200))
  )

  val getTasks = exec(
    http("GET All Tasks")
      .get("/api/tasks")
      .check(status.is(200))
  )

  val scn = scenario("Task API Load Test")
    .feed(feeder)
    .exec(createTask)
    .pause(1)
    .exec(getTasks)

  setUp(
    scn.inject(
      rampUsers(50).during(30.seconds) // simulate 50 users over 30 seconds
    )
  ).protocols(httpProtocol)
}
