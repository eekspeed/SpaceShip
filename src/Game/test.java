package Game;

public class test {
    public static void main(String[] args) {
        String a="00000|02 0 02 1 02 2 02 3 02 4 02 5 02 6 02 7|638 682 786 296 644 160 1384 30 1114 420 1534 663 1493 452 1384 732";
        String[] b=a.split("\\|");
        String c="00000";
        System.out.println(b[0].equals(c));
    }
    
}
