package com.unicom.game.center.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: steve_shao
 * Date: 7/3/14
 * Time: 4:47 PM
 * To change this template use File | Settings | File Templates.
 */
@ContextConfiguration(locations = { "classpath:applicationContext_dao.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class FileUtilsTest {

    @Test
   	public void getFileListTest() throws Throwable{
   		String path = "d:\\PingAn_soft";
        List<String> list = FileUtils.getFileList(path);

        for (String fileName : list) {
            System.out.println(fileName);
        }
   	}

    @Test
   	public void readFileTest() throws Throwable{
   		String path = "d:\\PingAn_soft\\abc.txt";
        List<String> list = FileUtils.readFileByRow(path);

        for (String fileName : list) {
            System.out.println(fileName);
        }
   	}

    @Test
   	public void writeFileTest() throws Throwable{
   		String path = "d:\\PingAn_soft\\demo.txt";
        boolean result = FileUtils.writeFileOverWrite(path, "abc1.txt");

        System.out.println(result);
   	}
}
