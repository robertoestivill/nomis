package nomis

import kotlinx.coroutines.experimental.*
import java.util.Random

class NomisGame(
    private val input: NomisInputManager,
    private val output: NomisOutputManager
) {

  companion object {
    private val DEFAULT_DELAY = 5000L
    private val DECREASE_STEP = 300L
  }

  private val history = ArrayList<Int>()
  private var checkIndex: Int = 0
  private var levelSpeed: Long = DEFAULT_DELAY
  private var gameOverTimer: Job? = null

  init {
    addMove()
    startTimer()
  }

  private fun addMove() {

    levelSpeed -= DECREASE_STEP // decrease timer with each move to increase difficulty
    history.add(Random().nextInt(4))

    input.isEnabled = false

    runBlocking {
      delay(levelSpeed / 10) // Wait between last pressed button and history playback
      for (move in history) {

        async {
          output.showMove(move)
          delay(levelSpeed / 5)
          output.hideMove(move)
        }.await()

        delay(levelSpeed / 50) // Little flash if two consecutive colors are the same
      }
    }

    input.isEnabled = true
  }

  fun checkMove(index: Int) {
    gameOverTimer?.cancel()

    if (history[checkIndex] != index) {
      gameOver()
      return
    }

    checkIndex++
    if (checkIndex == history.size) { // we just checked last element in history
      checkIndex = 0
      addMove()
    }

    startTimer()
  }

  private fun startTimer() {
    gameOverTimer = launch {
      delay(levelSpeed)
      gameOver()
    }
  }

  private fun gameOver() {
    input.isEnabled = false

    runBlocking {
      async {
        output.showGameOver()
        delay(DEFAULT_DELAY / 2)
        output.hideGameOver()
      }.await()
    }
  }

}