package prog.kiev.ua.lesson6.math;

public class ShellSortMultiTread {

    public int[] sort(int[] inputArray, int threadQty) {
        int length = inputArray.length / threadQty;
        ShellSortTread[] shellSortTreads = new ShellSortTread[threadQty];

        for (int i = 0; i < threadQty; i++) {

            int start = length * i;
            if (i == threadQty - 1) {
                length = length + inputArray.length % threadQty;
            }
            int end = start + length - 1;

            shellSortTreads[i] = new ShellSortTread(inputArray, start, end);
        }
        for (int i = 0; i < threadQty; i++) {
            try {
                shellSortTreads[i].getThread().join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int[] sortedArray = new int[0];
        for (int i = 0; i < threadQty; i++) {
            for (int item: shellSortTreads[i].getOutputArray()) {
            }
            sortedArray = arrayMerge(sortedArray, shellSortTreads[i].getOutputArray());
        }

        return sortedArray;
    }

    private int[] arrayMerge(int[] arr1, int[] arr2) {
        int[] output = new int[arr1.length + arr2.length];
        int index1 = 0;
        int index2 = 0;

        for (int i = 0; i < output.length; i++) {
            if (index1 >= arr1.length) {
                output[i] = arr2[index2];
                index2++;
            } else if (index2 >= arr2.length) {
                output[i] = arr1[index1];
                index1++;
            } else if (arr1[index1] < arr2[index2]) {
                output[i] = arr1[index1];
                index1++;
            } else {
                output[i] = arr2[index2];
                index2++;
            }
        }
        return output;
    }
}
