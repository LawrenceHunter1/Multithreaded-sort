import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class LinearSort {

    private static List<Integer> data;
    private static List<Integer> result;
    private static long start = System.nanoTime();
    private boolean test;
    private double time_elapsed;

    LinearSort(List<Integer> data_in, boolean save, boolean under_test) {
        test = under_test;
        data = data_in;
        run();
        if (save) {
            save_list();
        }
    }

    public void run() {
        result = data;
        if (!test) {
            System.out.println("Linear sort on list of size " + data.size() + " from list_in.txt");
        }
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
        long end = System.nanoTime();
        time_elapsed = (double) (end - start) / 1_000_000_000;
        if (!test) {
            System.out.println("Time elapsed: " + time_elapsed + "s");
        }
    }

    private void save_list() {
        try (BufferedWriter br = new BufferedWriter(new FileWriter("s_list_ls.txt"));) {
            for (int i = 0; i < result.size(); i++) {
                br.write(Integer.toString(result.get(i)) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double get_time_elapsed() {
        return time_elapsed;
    }

}
