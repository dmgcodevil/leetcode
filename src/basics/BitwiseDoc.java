package basics;

public class BitwiseDoc {

    public static void main(String[] args) {
        //setBitDemo();
        //resetBitDemo();
        isSetDemo();
    }

    static void resetBitDemo() {
        int n = 1 << 5; // 100000
        n = setBit(n, 1);
        n = setBit(n, 2); // 100110
        n = resetBit(n, 2); // 100010
        System.out.println(Integer.toBinaryString(n));
    }

    static void setBitDemo() {
        int n = 1 << 5; // 100000
        n = setBit(n, 1);  // 100010
        System.out.println(Integer.toBinaryString(n));
    }

    static void isSetDemo() {
        int n = 1 << 5; // 100000
        n = setBit(n, 1);  // 100010
        System.out.println(isSet(n, 1));
    }

    static int setBit(int n, int i) {
        return n | 1 << i;
    }

    static boolean isSet(int n, int i) {
        // 10010
        // 00010
        int mask = 1 << i;
        System.out.println(Integer.toBinaryString(n & 1 << i));
        return (n & mask) == mask;
    }

    static int resetBit(int n, int i) {
        return n ^ 1 << i;
    }
}