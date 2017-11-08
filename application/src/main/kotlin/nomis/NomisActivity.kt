package nomis

import android.app.Activity

class NomisActivity : Activity() {

  private lateinit var game: NomisGame

  private val restartCallback: () -> Unit = {
    game = NomisGame(input, output)
  }

  private val input = NomisInputManager().apply {
    onPressed = { index ->
      output.showMove(index)
    }
    onReleased = { index ->
      output.hideMove(index)
      game.checkMove(index)
    }
    onRestart = restartCallback
  }

  private val output = NomisOutputManager()

  override fun onDestroy() {
    super.onDestroy()
    input.onDestroy()
    output.onDestroy()
  }
}