package es.rubenmoreno.sudokusolver.solver

import scala.util.Random

import es.rubenmoreno.sudokusolver.board.Board

class Population(val smpls: Array[Board]) {

  val samples: Array[Board] = smpls.sortWith(_.score.getOrElse(Board.WorstScore) < _.score.getOrElse(Board.WorstScore))
  val best: Int = samples(0).score.getOrElse(Board.WorstScore)
  val average: Double = samples.map(_.score.getOrElse(Board.WorstScore)).sum / samples.length.toDouble

  def nextGen(probability: Double): Population = {

    val pairCount = samples.length / 2
    val singleCount = samples.length % 2

    val pairs = for {
      i <- 1 to pairCount toList
    } yield cross(pickPair)

    val single = if (singleCount > 0) List(samples(pickCandidate)) else Nil

    val newSamples = mutate(single ++ pairs.flatten.toList, probability)

    new Population(newSamples toArray)
  }

  private def pickCandidate: Int = {
    val x = Random.nextDouble()
    //val y = -1 * (math.sqrt(1 - x*x) - 1) // easeInCirc
    val y = x * x // easeInQuad
    math.round(y * samples.length).toInt max 0 min (samples.length - 1)
  }

  // @TODO: Avoid duplicates
  private def pickPair: (Int, Int) = (pickCandidate, pickCandidate)

  private def cross(pair: (Int, Int)) = samples(pair._1) cross samples(pair._2)

  private def mutate(samples: Seq[Board], probability: Double) = samples.map(_.mutate(probability))
}

object Population {

  def apply(template: Board, num: Int): Population = {
    val samples = for {
      i <- 1 to num toArray
    } yield template.populate

    new Population(samples)
  }

}
