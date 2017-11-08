Nomis is an Android Things implementation of the [Simon board game](https://en.wikipedia.org/wiki/Simon_(game)) using Kotlin and Coroutines.

## How to play

Press the start button and repeat the generated pattern before the time is up.

<a href="https://www.youtube.com/watch?v=oA9SEaHYA18" target="_blank">
  <img src="https://img.youtube.com/vi/oA9SEaHYA18/0.jpg" height="200" />
</a>


## Hardware

<a href="https://github.com/robertoestivill/nomis/raw/master/diagram/nomis.png" target="_blank">
  <img src="https://github.com/robertoestivill/nomis/raw/master/diagram/nomis.png" height="200" />
</a>

[Fritzing diagram](https://github.com/robertoestivill/nomis/raw/master/diagram/nomis.fzz)

## Run it

Make sure your device is on and connect to it through ADB

```
$ adb connect {YOUR DEVICE IP}
* daemon not running. starting it now on port 5037 *
* daemon started successfully *
connected to {YOUR DEVICE IP}:5555

```

Build the application and install it on the device

```
$ ./gradlew installDebug
```

Manually start the main activity (this will happen automatically if the device is rebooted)

```
$ adb shell am start com.robertoestivill.nomis/nomis.NomisActivity
```


## License

```
MIT License

Copyright (c) 2017 Roberto Estivill

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
