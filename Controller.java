import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private static List<Integer> data;
    private static MergeSort ms;
    private static LinearSort ls;

    public static void main(String[] args) {
        data = read_list();

        ms = new MergeSort(data);
        ls = new LinearSort(data);
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
