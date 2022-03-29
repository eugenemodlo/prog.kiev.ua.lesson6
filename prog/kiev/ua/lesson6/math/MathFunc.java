package prog.kiev.ua.lesson6.math;

import java.math.BigInteger;
import java.util.stream.IntStream;

public class MathFunc {
    public MathFunc() {};

    public BigInteger getFactorial(int in) {
        BigInteger out = new BigInteger("0");
        if (in < 1) {
            out = BigInteger.valueOf(1);
        } else {
            out = IntStream.rangeClosed(2, in).mapToObj(BigInteger::valueOf).reduce(BigInteger::multiply).orElseThrow();
        }
        return out;
    }

}
