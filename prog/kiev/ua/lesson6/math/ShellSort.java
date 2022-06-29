package prog.kiev.ua.lesson6.math;

public class ShellSort {

    public int[] sort(int[] inputArray) {
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
        return inputArray;
    }
}
