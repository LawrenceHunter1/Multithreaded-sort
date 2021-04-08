import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Testing {
    private static List<Integer> data;
    private static MergeSort ms;
    private static LinearSort ls;
    private static FileHandler fh = new FileHandler();
    private static List<Double> ms_times = new ArrayList<Double>();
    private static List<Double> ls_times = new ArrayList<Double>();
    private static int[] list_sizes = { 1, 10, 50, 100, 250, 500, 750, 1000 };
    // , 2500, 5000, 10000, 50000, 100000

    public static void main(String[] args) {
        for (int size : list_sizes) {
            data = fh.new_list(size, 100);

            ms = new MergeSort(data, false, true);
            ls = new LinearSort(data, false, true);

            ms_times.add(ms.get_time_elapsed());
            ls_times.add(ls.get_time_elapsed());

        }
        write_sizes();
        write_times();
    }

    public static void write_sizes() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("output/l_sizes.txt"));) {
            for (int time : list_sizes) {
                bw.write(Integer.toString(time));
                bw.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void write_times() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("output/ms_times.txt"));) {
            for (Double time : ms_times) {
                bw.write(Double.toString(time));
                bw.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("output/ls_times.txt"));) {
            for (Double time : ls_times) {
                bw.write(Double.toString(time));
                bw.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
