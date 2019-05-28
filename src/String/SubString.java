package String;

import java.io.IOException;

public class SubString {

    private static String s="./TestReport/2018-01-05_102806/TC1/Success/TC1_DataSet_2_Success.png";

    public static void main(String[] args) {
        System.out.println(s);
        System.out.println(s.indexOf("TC1"));
        System.out.println(s.substring(s.indexOf("TC1"),s.length()));
    }
}
