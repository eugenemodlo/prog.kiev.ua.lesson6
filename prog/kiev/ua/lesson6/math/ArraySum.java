package prog.kiev.ua.lesson6.math;

public class ArraySum implements Runnable{
    final private int[] inputArray;
    private int start = 0;
    private final int interval;

    public ArraySum(int[] inputArray, int interval) {
        this.inputArray = inputArray;
        this.interval = interval;


    }

    @Override
    public void run() {
        getIntervalSum(this.start);
    }

    private int getIntervalSum(int start) {
        int sum = 0;

        for (int i = start; i <= (start + this.interval); i++){
            sum = sum + this.inputArray[i];
        }
        return sum;
    }
}
