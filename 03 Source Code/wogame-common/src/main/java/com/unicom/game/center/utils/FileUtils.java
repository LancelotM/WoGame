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
    public static List<String> readFileByRow(String path) {    	
    	FileReader reader = null;
    	BufferedReader br = null;
    	List<String> list = new ArrayList<String>();
        try {
            File file = new File(path);
            
            if (!file.exists()) {
                return list;
            }

            reader = new FileReader(path);
            br = new BufferedReader(reader);

            String str = null;

            while ((str = br.readLine()) != null) {
                list.add(str);
            }
        } catch (FileNotFoundException e) {
            Logging.logError("FileNotFoundException occur in readFileByRow.", e);
        } catch (IOException e) {
        	Logging.logError("IOException occur in readFileByRow.", e);
        } catch (Exception e){
        	Logging.logError("Exception occur in readFileByRow.", e);
        } finally{
            try {
            	if(null != br){
            		br.close();
            	}
            	
            	if(null != reader){
            		reader.close();
            	}
			} catch (Exception e) {
				Logging.logError("Exception occur in readFileByRow.", e);
			}            
        }
        
        return list;
    }


    /**
     *
     * write file (OverWrite)
     *
     * @param path
     * @return
     */
    public static boolean writeFileOverWrite(String path, String filename) {
    	FileWriter writer = null;
    	BufferedWriter bw = null;    	
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }

            writer = new FileWriter(path);
            bw = new BufferedWriter(writer);
            bw.write(filename);
            return true;
        } catch (FileNotFoundException e) {
            Logging.logError("FileNotFoundException occur in writeFileOverWrite", e);
            return false;
        } catch (IOException e) {
        	Logging.logError("IOException occur in writeFileOverWrite", e);
            return false;
        }catch (Exception e){
        	Logging.logError("Error occur in writeFileOverWrite", e);
        	return false;
        } finally{
            try {
            	if(null != bw){
            		bw.close();
            	}
            	
            	if(null != writer){
            		writer.close();
            	}
			} catch (Exception e) {
				Logging.logError("Exception occur in writeFileOverWrite.", e);
			}
        }
    }
}
