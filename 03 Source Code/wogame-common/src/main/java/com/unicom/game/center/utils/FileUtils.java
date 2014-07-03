package com.unicom.game.center.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: steve_shao
 * Date: 7/3/14
 * Time: 3:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class FileUtils {

    /**
     * get file list (no recursion)
     *
     * @param path
     * @return
     */
    public static List<String> getFileList (String path) {

        List<String> list = new ArrayList<String>();

        File file = new File(path);
        if (!file.exists()) {
            return list;
        }
        String[] fileList = file.list();
        list = Arrays.asList(fileList);

        return list;
    }

    /**
     *
     * read file by row
     *
     * @param path
     * @return
     */
    public static List<String> readFileByRow(String path) throws IOException {
        try {
            File file = new File(path);
            List<String> list = new ArrayList<String>();
            if (!file.exists()) {
                return list;
            }

            FileReader reader = new FileReader(path);
            BufferedReader br = new BufferedReader(reader);

            String str = null;

            while ((str = br.readLine()) != null) {
                list.add(str);
            }
            br.close();
            reader.close();

            return list;
        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        }
    }


    /**
     *
     * write file (OverWrite)
     *
     * @param path
     * @return
     */
    public static boolean writeFileOverWrite(String path, String filename) throws IOException {
        try {
            File file = new File(path);
            List<String> list = new ArrayList<String>();
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter writer = new FileWriter(path);
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write(filename);

            bw.close();
            writer.close();

            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
