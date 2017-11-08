package nomis

import com.google.android.things.contrib.driver.button.Button

class NomisInputManager {

  companion object {
    private val GPIO_BUTTON_COLORS = arrayOf("BCM27", "BCM23", "BCM6", "BCM19")
    private val GPIO_BUTTON_RESTART = "BCM26"
  }

  lateinit var onPressed: (Int) -> Unit
  lateinit var onReleased: (Int) -> Unit
  lateinit var onRestart: () -> Unit

  var isEnabled = true

  private val buttons = GPIO_BUTTON_COLORS.map {
    Button(it, Button.LogicState.PRESSED_WHEN_LOW).apply {
      setDebounceDelay(0)
      setOnButtonEventListener { _, pressed ->
        val index = GPIO_BUTTON_COLORS.indexOf(it)
        if (isEnabled) {
          if (pressed) {
            onPressed.invoke(index)
          } else {
            onReleased.invoke(index)
          }
        }
      }
    }
  }

  private val restart = Button(GPIO_BUTTON_RESTART, Button.LogicState.PRESSED_WHEN_LOW).apply {
    setOnButtonEventListener { _, pressed ->
      if (!pressed) {
        onRestart.invoke()
      }
    }
  }

  fun onDestroy() {
    restart.close()
    buttons.forEach { it.close() }
  }
}