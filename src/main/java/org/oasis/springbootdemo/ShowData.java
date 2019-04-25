package org.oasis.springbootdemo;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.List;

/**
 * @author tianbo
 * @date 2019-04-25
 */
public class ShowData {

    public static void main(String[] args) throws Exception {
        String path1 = "/home/tianbo/Desktop/data_source_1.txt";
        String path2 = "/home/tianbo/Desktop/data_source_2.txt";

        displayTable(path1);
        displayTable(path2);
    }

    private static void displayTable(String path) throws Exception {
        List<String> stringList = FileUtils.readLines(new File(path), "UTF-8");
        String title = stringList.get(0);
        String content = stringList.get(1);

        String[] titles = title.split("\\|");
        String[] fields = content.split("\\|");

        for (int i = 0; i < titles.length; i++) {
            System.out.println(titles[i] + "=" + fields[i]);
        }
        System.out.println("--------------------------------------");
    }
}
