package prog.kiev.ua.lesson6.math;

import java.math.BigInteger;
import java.util.stream.IntStream;

public class MathFactorial implements Runnable {
    private int number = 0;

    public MathFactorial(int number) {
        super();
        this.number = number;
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        getFactorial();
    }

    private void getFactorial() {
        BigInteger out;
        if (number <= 1) {
            out = BigInteger.valueOf(1);
        } else {
            out = IntStream.rangeClosed(2, number).mapToObj(BigInteger::valueOf).reduce(BigInteger::multiply).orElseThrow();
        }
        System.out.println("Factorial of " + number + "-> " + out);
    }

}
