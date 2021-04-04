public class Controller {
    public static void main(String[] args) {
        Node N1 = new Node(new int[] { 0, 1 });
        Node N2 = new Node(new int[] { 2, 3 });
        N1.start();
        N2.start();
        System.out.println(N1.get_result());
        System.out.println(N2.get_result());
        N1.interrupt();
        N2.interrupt();
    }
}
