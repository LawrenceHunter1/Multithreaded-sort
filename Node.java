import java.util.List;

class Node extends Thread {

    private Thread t;
    private List<Integer> data;
    private boolean finished = false;

    Node(List<Integer> data_in) {
        this.data = data_in;
    }

    @Override
    public void run() {
        // System.out.println("Node starting with " + data);
        while (!check_ordered()) {
            for (int i = 0; i < data.size() - 1; i += 1) {
                if (data.get(i) > data.get(i + 1)) {
                    int temp = data.get(i);
                    data.set(i, data.get(i + 1));
                    data.set(i + 1, temp);
                }
            }
        }
        finished = true;
    }

    private boolean check_ordered() {
        boolean ordered = true;
        for (int i = 0; i < data.size() - 1; i++) {
            if (data.get(i) > data.get(i + 1)) {
                ordered = false;
            }
        }
        return ordered;
    }

    public List<Integer> get_result() {
        return data;
    }

    public boolean check_status() {
        return finished;
    }

    public void start() {
        if (t == null) {
            t = new Thread(this);
            t.start();
        }
    }

}