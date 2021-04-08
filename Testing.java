import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Testing {
    private static List<Integer> data;
    private static MergeSort ms;
    private static BubbleSort bs;
    private static FileHandler fh = new FileHandler();
    private static List<Double> ms_times = new ArrayList<Double>();
    private static List<Double> bs_times = new ArrayList<Double>();
    private static List<Integer> list_sizes = read_sizes();

    public static void main(String[] args) {
        for (int i = 0; i < list_sizes.size(); i++) {
            System.out.println("Performing sorts on list of size " + list_sizes.get(i));

            data = fh.new_list(list_sizes.get(i), 100);

            ms = new MergeSort(data, false, true);
            bs = new BubbleSort(data, false, true);

            ms_times.add(ms.get_time_elapsed());
            bs_times.add(bs.get_time_elapsed());

        }
        write_sizes();
        write_times();
    }

    private static List<Integer> read_sizes() {
        List<Integer> list = new ArrayList<Integer>();
        try (BufferedReader br = new BufferedReader(new FileReader("input/sizes.txt"));) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static void write_sizes() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("output/l_sizes.txt"));) {
            for (int time : list_sizes) {
                bw.write(Integer.toString(time));
                bw.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void write_times() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("output/ms_times.txt"));) {
            for (Double time : ms_times) {
                bw.write(Double.toString(time));
                bw.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("output/bs_times.txt"));) {
            for (Double time : bs_times) {
                bw.write(Double.toString(time));
                bw.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
