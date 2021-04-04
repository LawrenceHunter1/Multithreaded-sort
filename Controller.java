import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Controller {
    public static void main(String[] args) {
        int[] data = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        List<Node> nodes = new ArrayList<Node>();
        List<Integer> results = new ArrayList<Integer>();
        for (int i = 0; i < data.length; i += 2) {
            nodes.add(new Node(Arrays.copyOfRange(data, i, i + 2)));
        }
        for (Node node : nodes) {
            node.start();
        }
        for (Node node : nodes) {
            while (!node.check_status()) {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            results.add(node.get_result());
            node.interrupt();
        }
        System.out.println(results);
    }
}
