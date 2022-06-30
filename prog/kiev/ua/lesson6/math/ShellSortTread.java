package prog.kiev.ua.lesson6.math;

public class ShellSortTread implements Runnable{

    final private int[] inputArray;
    private final int begin;
    private final int end;
    private final Thread thread;

    public ShellSortTread(int[] inputArray, int begin, int end) {
        this.inputArray = inputArray;
        this.begin = begin;
        this.end = end;
        thread = new Thread(this);
        thread.start();
    }
    @Override
    public void run() {
        int length = inputArray.length;

        for (int step = length / 2; step > 0; step /= 2) {
            for (int i = step; i < length; i++) {
                for (int j = i - step; j >= 0 && inputArray[j] > inputArray[j + step] ; j -= step) {
                    int x = inputArray[j];
                    inputArray[j] = inputArray[j + step];
                    inputArray[j + step] = x;
                }
            }
        }
    }
}
