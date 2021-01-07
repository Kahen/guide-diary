package me.zhengjie.modules;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : Kahen
 * @date : 2021/1/6 19:54
 */
public class FileReaderTest {
    public static void main(String[] args) {
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("foo.txt")));
            for (int i = 0; i < 20000; i++) {
                out.println("第" + i + "行");
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            BufferedReader in
                    = new BufferedReader(new FileReader("foo.txt"));

            Stream<String> lines = in.lines();
            List<String> strings = lines.collect(Collectors.toList());
            System.out.println();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        RandomAccessFile
    }

}
