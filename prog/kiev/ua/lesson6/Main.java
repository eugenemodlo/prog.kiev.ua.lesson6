package prog.kiev.ua.lesson6;

import prog.kiev.ua.lesson6.math.ArraySumThread;
import prog.kiev.ua.lesson6.math.MathFactorial;

public class Main {
    public static void main(String[] args) {
//         STEP 1
//        for (int i = 1; i <= 100; i++) {
//            MathFactorial factorial = new MathFactorial(i);
//        }

//        STEP 2
        int[] input = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        ArraySumThread arraySumThread = new ArraySumThread(input, 0, 9);

        try {
            arraySumThread.getThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(arraySumThread.getSum());
    }
}
