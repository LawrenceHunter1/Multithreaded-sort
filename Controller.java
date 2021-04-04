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

public class Controller {

    private static List<Integer> data = set_list();
    private static List<Integer> result = data;
    private static List<Node> nodes;

    public static void main(String[] args) {

        // System.out.println("Original list: ");
        // System.out.println(data);

        // System.out.println("Size: " + result.size());

        for (int i = 1; i < Math.log(result.size()); i++) {
            nodes = new ArrayList<Node>();

            // System.out.println("i: " + i);

            int increment = (int) (result.size() / (result.size() / Math.pow(2, i)));

            // System.out.println("increment: " + increment);

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
            // output_results();
        }
        nodes = new ArrayList<Node>();
        nodes.add(new Node(result.subList(0, (int) (result.size() / 2))));
        nodes.add(new Node(result.subList((int) (result.size() / 2), result.size())));
        start_nodes();
        get_results();
        // output_results();

        nodes = new ArrayList<Node>();
        nodes.add(new Node(result.subList(0, result.size())));
        start_nodes();
        get_results();

        System.out.println("Original list: ");
        System.out.println(data);
        System.out.println();
        System.out.println("Sorted list: ");
        output_results();

        System.out.println();
        System.out.println("Sorted: " + String.valueOf(check_ordered()));

        save_list();
    }

    private static List<Integer> set_list() {
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

    private static void save_list() {
        try (BufferedWriter br = new BufferedWriter(new FileWriter("list_out.txt"));) {
            for (int i = 0; i < result.size(); i++) {
                br.write(Integer.toString(result.get(i)) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void process_results(List<List<Integer>> node_results) {
        result = new ArrayList<Integer>();
        for (List<Integer> list : node_results) {
            for (int i = 0; i < list.size(); i++) {
                result.add(list.get(i));
            }
        }
    }

    private static void get_results() {
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

    private static boolean check_ordered() {
        boolean ordered = true;
        for (int i = 0; i < result.size() - 1; i++) {
            if (result.get(i) > result.get(i + 1)) {
                ordered = false;
            }
        }
        return ordered;
    }

    private static void start_nodes() {
        for (Node node : nodes) {
            node.start();
        }
    }

    private static void output_results() {
        System.out.println(result);
        // System.out.println("Result size: " + result.size());
    }
}
