package prog.kiev.ua.lesson6.math;

public class ShellSortTread implements Runnable {

    final private int[] outputArray;
    private final Thread thread;

    public ShellSortTread(int[] inputArray, int begin, int end) {
        this.outputArray = new int[end - begin + 1];
        System.arraycopy(inputArray, begin, this.outputArray, 0, end - begin + 1);
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        for (int step = outputArray.length / 2; step > 0; step /= 2) {
            for (int i = step; i < outputArray.length; i++) {
                for (int j = i - step; j >= 0 && outputArray[j] > outputArray[j + step]; j -= step) {
                    int x = outputArray[j];
                    outputArray[j] = outputArray[j + step];
                    outputArray[j + step] = x;
                }
            }
        }
    }
    public int[] getOutputArray() {
        return this.outputArray;
    }
    public Thread getThread() {
        return this.thread;
    }
}
