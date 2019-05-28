package CodeRunning;

import throwable.try_catch;

public class throwsble_tests {
    public static void main(String[] args) {
        try_catch t = new try_catch();
        try {
            t.tests();
        }catch(Throwable e) {
            System.out.println("Catched!");
        }
    }
}
