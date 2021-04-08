import java.util.ArrayList;
import java.util.List;

public class Testing {
    private static List<Integer> data;
    private static MergeSort ms;
    private static LinearSort ls;
    private static FileHandler fh = new FileHandler();
    private static List<Double> ms_times = new ArrayList<Double>();
    private static List<Double> ls_times = new ArrayList<Double>();

    public static void main(String[] args) {
        int[] list_sizes = { 1, 10, 50, 100, 500, 1000 };
        // , 2500, 5000, 10000, 50000, 100000
        for (int size : list_sizes) {
            data = fh.new_list(size, 100);

            ms = new MergeSort(data, false, true);
            ls = new LinearSort(data, false, true);

            ms_times.add(ms.get_time_elapsed());
            ls_times.add(ls.get_time_elapsed());

        }
        System.out.println(ms_times);
        System.out.println(ls_times);
    }
}
