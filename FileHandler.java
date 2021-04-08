import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FileHandler {

    public List<Integer> read_list() {
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

    public List<Integer> new_list(int items, int range) {
        List<Integer> list = new ArrayList<Integer>();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("list_in.txt"));) {
            Random r = new Random();
            for (int i = 0; i < items; i++) {
                int new_num = r.nextInt(range);
                list.add(new_num);
                bw.write(Integer.toString(new_num));
                bw.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
