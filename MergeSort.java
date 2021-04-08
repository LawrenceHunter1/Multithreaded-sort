import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MergeSort {

    private long start = System.nanoTime();

    private List<Integer> data;
    private List<Integer> result;
    private List<Node> nodes;
    private boolean test;
    private double time_elapsed;

    MergeSort(List<Integer> data_in, boolean save, boolean under_test) {
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
            System.out.println("Merge sort on list of size " + data.size() + " from list_in.txt");
        }

        for (int i = 1; i < Math.log(result.size()); i++) {
            nodes = new ArrayList<Node>();
            int increment = (int) (result.size() / (result.size() / Math.pow(2, i)));
            for (int j = 0; j < result.size(); j += increment) {

                // System.out.println("j:" + j);

                if (j + increment > result.size() - 1) {
                    nodes.add(new Node(result.subList(j - 1, result.size() - 1)));
                } else {
                    nodes.add(new Node(result.subList(j, j + increment)));
                }
            }
            start_nodes();
            get_results();
        }
        nodes = new ArrayList<Node>();
        nodes.add(new Node(result.subList(0, (int) (result.size() / 2))));
        nodes.add(new Node(result.subList((int) (result.size() / 2), result.size())));
        start_nodes();
        get_results();

        nodes = new ArrayList<Node>();
        nodes.add(new Node(result.subList(0, result.size())));
        start_nodes();
        get_results();

        long end = System.nanoTime();
        time_elapsed = (double) (end - start) / 1_000_000_000;
        if (!test) {
            System.out.println("Time elapsed: " + time_elapsed + "s");
        }
    }

    private void save_list() {
        try (BufferedWriter br = new BufferedWriter(new FileWriter("s_list_ms.txt"));) {
            for (int i = 0; i < result.size(); i++) {
                br.write(Integer.toString(result.get(i)) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void process_results(List<List<Integer>> node_results) {
        result = new ArrayList<Integer>();
        for (List<Integer> list : node_results) {
            result.addAll(list);
        }
    }

    private void get_results() {
        List<List<Integer>> node_results = new ArrayList<List<Integer>>();
        for (Node node : nodes) {
            while (!node.check_status()) {
                try {
                    TimeUnit.NANOSECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            node_results.add(node.get_result());
            node.interrupt();
        }
        process_results(node_results);
    }

    public double get_time_elapsed() {
        return time_elapsed;
    }

    private void start_nodes() {
        for (Node node : nodes) {
            node.start();
        }
    }

    private void output_results() {
        System.out.println(result);
    }
}
