import java.util.List;

public class Controller {
    private static List<Integer> data;
    private static MergeSort ms;
    private static LinearSort ls;
    private static FileHandler fh = new FileHandler();
    private static int range = -1;
    private static int items = -1;

    public static void main(String[] args) {
        try {
            if (args[0] != null) {
                items = Integer.parseInt(args[0]);
            }
            if (args[1] != null) {
                range = Integer.parseInt(args[1]);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            ;
        }
        if (items != -1 || range != -1) {
            data = fh.new_list(items, range);
        } else {
            data = fh.read_list();
        }
        ms = new MergeSort(data, false, false);
        ls = new LinearSort(data, false, false);
    }
}
