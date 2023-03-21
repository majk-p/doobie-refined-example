//> using scala "3.2.2"
//> using lib "org.typelevel::cats-effect:3.4.8"
//> using lib "org.tpolecat::doobie-core:1.0.0-RC2"
//> using lib "org.tpolecat::doobie-postgres:1.0.0-RC2"
//> using lib "org.tpolecat::doobie-refined:1.0.0-RC2"

import cats.effect.IO
import cats.effect.IOApp
import cats.implicits.*

import doobie.*
import doobie.implicits.*
import doobie.refined.implicits.*
import doobie.util.ExecutionContexts

import eu.timepit.refined.*
import eu.timepit.refined.api.Refined
import eu.timepit.refined.string.MatchesRegex
import eu.timepit.refined.types.numeric.PosInt
import eu.timepit.refined.types.string.NonEmptyString

val xa = Transactor.fromDriverManager[IO](
  "org.postgresql.Driver",
  "jdbc:postgresql:postgres",
  "postgres",
  "postgres"
)

type Email = String Refined MatchesRegex["^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"]

case class User(
  email: Email,
  teamId: PosInt,
  firstName: NonEmptyString,
  lastName: NonEmptyString,
  password: NonEmptyString,
  picture: Option[NonEmptyString]
)

def getUserByEmail(
  email: String
): IO[Option[User]] =
  sql"""select * from "Users" where "Email"=$email"""
    .query[User]
    .option
    .transact(xa)

def displayResult: Option[User] => IO[Unit] =
  case Some(user) => IO.println(s"✅ Found user: $user")
  case None       => IO.println("❗ User not found")

object Main extends IOApp.Simple {

  override def run: IO[Unit] =
    (getUserByEmail("admin@example.net") >>= displayResult) *>
      (getUserByEmail("admin@example.com") >>= displayResult)

}
