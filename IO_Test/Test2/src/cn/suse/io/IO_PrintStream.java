package cn.suse.io;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class IO_PrintStream {
    public static void main(String[] args) throws FileNotFoundException {
        PrintStream ps =new PrintStream("print.txt");
        ps.println("hahaha");
        ps.println('a');
        ps.write(97);
        System.out.println("在控制台显示");
        System.setOut(ps);
        System.out.println("在文件中显示");
    }
}
