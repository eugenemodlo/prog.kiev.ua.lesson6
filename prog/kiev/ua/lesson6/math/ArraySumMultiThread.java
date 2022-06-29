package prog.kiev.ua.lesson6.math;

public class ArraySumMultiThread {
    public int arraySum(int[] inputArray, int threadQty) {
        int length = inputArray.length / threadQty;
        ArraySumThread[] arraySumThreads = new ArraySumThread[threadQty];
        int sum = 0;

        for (int i = 0; i < threadQty; i++) {

            int start = length * i;
            if (i == threadQty - 1) {
                length = length + inputArray.length % threadQty;
            }
            int end = start + length - 1;

            arraySumThreads[i] = new ArraySumThread(inputArray, start, end);
        }
        for (int i = 0; i < threadQty; i++) {
            try {
                arraySumThreads[i].getThread().join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < threadQty; i++) {
            sum = sum + arraySumThreads[i].getSum();
        }

        return sum;
    }
}
