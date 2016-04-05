import com.pi4j.io.gpio.*;

import java.util.concurrent.TimeUnit;

public class GpioLED {
    public static void main(final String... args) throws Exception {
        final GpioController gpio = GpioFactory.getInstance();

        try {
            // creating the pin with parameter PinState.HIGH
            // will instantly power up the pin
            final GpioPinDigitalOutput led1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "PinLED", PinState.HIGH);
            led1.blink(500,5000);
            System.out.println("light is blinking");

            // wait 2 seconds
            TimeUnit.SECONDS.sleep(2);

            // turn off GPIO 1
            led1.low();
            System.out.println("light is: OFF");
            // wait 1 second
            TimeUnit.SECONDS.sleep(1);
            // turn on GPIO 1 for 1 second and then off
            System.out.println("light is: ON for 1 second");
            led1.pulse(1000, true);
        } finally {
            // release the GPIO controller resources
            gpio.shutdown();
        }
    }
}
