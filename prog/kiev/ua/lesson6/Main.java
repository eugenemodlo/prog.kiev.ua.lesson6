package prog.kiev.ua.lesson6;

import prog.kiev.ua.lesson6.math.MathFactorial;

public class Main {
    public static void main(String[] args) {

        for (int i = 1; i <= 100; i++) {
            MathFactorial factorial = new MathFactorial(i);
        }

    }
}
