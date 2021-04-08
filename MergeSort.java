import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MergeSort {

    private long start = System.currentTimeMillis();

    private List<Integer> data;
    private List<Integer> result;
    private List<Node> nodes;

    MergeSort(List<Integer> data_in) {
        data = data_in;
        run();
    }

    public void run() {
        result = data;
        System.out.println("Merge sort on list of size " + data.size() + " from list_in.txt");

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

        // System.out.println("Original list: ");
        // System.out.println(data);
        // System.out.println();
        // System.out.println("Sorted list: ");
        // output_results();

        System.out.println("Sorted: " + String.valueOf(check_ordered()));

        long end = System.currentTimeMillis();
        float time_elapsed = (end - start) / 1000F;
        System.out.println("Time elapsed: " + time_elapsed + "s");
        save_list();
    }

    private void save_list() {
        try (BufferedWriter br = new BufferedWriter(new FileWriter("list_out.txt"));) {
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
            for (int i = 0; i < list.size(); i++) {
                result.add(list.get(i));
            }
        }
    }

    private void get_results() {
        List<List<Integer>> node_results = new ArrayList<List<Integer>>();
        for (Node node : nodes) {
            while (!node.check_status()) {
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            node_results.add(node.get_result());
            node.interrupt();
        }
        process_results(node_results);
    }

    private boolean check_ordered() {
        boolean ordered = true;
        for (int i = 0; i < result.size() - 1; i++) {
            if (result.get(i) > result.get(i + 1)) {
                ordered = false;
            }
        }
        return ordered;
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
