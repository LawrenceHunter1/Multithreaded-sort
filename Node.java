class Node extends Thread {

    private Thread t;
    private int[] data;
    private int result;

    Node(int[] data_in) {
        this.data = data_in;
    }

    @Override
    public void run() {
        result = data[0] + data[1];
    }

    public int get_result() {
        return result;
    }

    public void start() {
        if (t == null) {
            t = new Thread(this);
            t.start();
        }
    }

}