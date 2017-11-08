package nomis

import com.google.android.things.pio.Gpio
import com.google.android.things.pio.PeripheralManagerService

class NomisOutputManager {

  companion object {

    private val BUTTONS_LEDS = arrayOf("BCM17", "BCM22", "BCM12", "BCM16")
    private val BUTTONS_NOTES = doubleArrayOf(82.407, 69.296, 110.0, 41.203)

    private val GAME_OVER_NOTE = 34.648
    private val PWM_GPIO = "PWM1"
  }

  private val pwm = PeripheralManagerService()
      .openPwm(PWM_GPIO)
      .apply { setPwmDutyCycle(50.0) }

  private val leds = BUTTONS_LEDS.map {
    PeripheralManagerService().openGpio(it)
        .apply { setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW) }
  }

  fun showMove(index: Int) {
    leds[index].value = true
    pwm.setPwmFrequencyHz(BUTTONS_NOTES[index])
    pwm.setEnabled(true)
  }

  fun hideMove(index: Int) {
    leds[index].value = false
    pwm.setEnabled(false)
  }

  fun showGameOver() {
    leds.forEach { it.value = true }
    pwm.setPwmFrequencyHz(GAME_OVER_NOTE)
    pwm.setEnabled(true)
  }

  fun hideGameOver() {
    leds.forEach { it.value = false }
    pwm.setEnabled(false)
  }

  fun onDestroy() {
    pwm.close()
    leds.forEach { it.close() }
  }
}