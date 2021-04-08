import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private static List<Integer> data;
    private static MergeSort ms;
    private static LinearSort ls;
    private static FileHandler fh = new FileHandler();

    public static void main(String[] args) {
        fh.new_list(1000, 100);
        data = fh.read_list();
        System.out.println(data);

        // ms = new MergeSort(data, false);
        // ls = new LinearSort(data, false);
    }

    private static List<Integer> read_list() {
        List<Integer> list = new ArrayList<Integer>();
        try (BufferedReader br = new BufferedReader(new FileReader("list_in.txt"));) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
