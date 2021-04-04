import java.util.Arrays;

class Node extends Thread {

    private Thread t;
    private int[] data;
    private int result;
    private boolean finished = false;

    Node(int[] data_in) {
        this.data = data_in;
    }

    @Override
    public void run() {
        System.err.println("Node running with data: " + Arrays.toString(data));
        result = data[0] + data[1];
        finished = true;
    }

    public int get_result() {
        return result;
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