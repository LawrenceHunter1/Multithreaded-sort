import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.Line;

public class LinearSort {
    private static List<Integer> data;
    private static List<Integer> result;
    private static long start = System.currentTimeMillis();

    LinearSort(List<Integer> data_in) {
        data = data_in;
        run();
    }

    public void run() {
        result = data;
        System.out.println("Linear sort on list of size " + data.size() + " from list_in.txt");
        boolean ordered = false;
        int counter = -1;
        while (counter != 0) {
            counter = 0;
            for (int i = 0; i < data.size() - 1; i += 1) {
                if (data.get(i) > data.get(i + 1)) {
                    int temp = data.get(i);
                    data.set(i, data.get(i + 1));
                    data.set(i + 1, temp);
                    counter++;
                }
            }
        }
        long end = System.currentTimeMillis();
        float time_elapsed = (end - start) / 1000F;
        System.out.println("Time elapsed: " + time_elapsed + "s");
    }
}
