package prog.kiev.ua.lesson6.math;

public class ArraySumThread implements Runnable {
    final private int[] inputArray;
    private final int begin;
    private final int end;
    private int sum = 0;
    private final Thread thread;

    public ArraySumThread(int[] inputArray, int begin, int end) {
        this.inputArray = inputArray;
        this.begin = begin;
        this.end = end;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        for (int i = begin; i <= end; i++) {
            if (i < inputArray.length) {
                sum = sum + inputArray[i];
            }

        }
    }

    public int getSum() {
        return this.sum;
    }

    public Thread getThread() {
        return this.thread;
    }

}
