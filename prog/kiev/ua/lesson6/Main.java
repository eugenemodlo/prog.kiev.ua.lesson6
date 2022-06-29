package prog.kiev.ua.lesson6;

import prog.kiev.ua.lesson6.math.ArraySumMultiThread;
import prog.kiev.ua.lesson6.math.ArraySumThread;
import prog.kiev.ua.lesson6.math.MathFactorial;
import prog.kiev.ua.lesson6.math.ShellSort;

public class Main {
    public static void main(String[] args) {
        ShellSort shellSort = new ShellSort();

        int[] input = new int[] {11, 24, 23, 14, 51, 600, 17, 86, 49, 100};
        input = shellSort.sort(input);
        for (int i: input) {
            System.out.print(i + " ");
        }

////         STEP 1
//        for (int i = 1; i <= 100; i++) {
//            MathFactorial factorial = new MathFactorial(i);
//        }
//        STEP 2
//        int[] input = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
//
//        ArraySumMultiThread arraySum = new ArraySumMultiThread();
//        System.out.println();
//        System.out.println("Multi thread array sum - " + arraySum.arraySum(input, 3));
//        ArraySumThread arraySumThread = new ArraySumThread(input, 0, 9);
//
//        try {
//            arraySumThread.getThread().join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("Single thread array sum - " + arraySumThread.getSum());
    }
}
