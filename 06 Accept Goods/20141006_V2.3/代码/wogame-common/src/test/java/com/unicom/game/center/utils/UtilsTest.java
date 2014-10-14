package com.unicom.game.center.utils;

import com.jcraft.jsch.ChannelSftp;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Alex Yin
 * 
 * @Date Jun 19, 2014
 */
@ContextConfiguration(locations = { "classpath:applicationContext_dao.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UtilsTest {

	@Value("#{properties['site.secret.key']}")
	private String secretKey;
	
	@Autowired
	private FTPHelper ftpHelper;	
	
	@Autowired
	private SFTPHelper sftpHelper;		
	
	@Test
	@Ignore
	public void dateUtilsTest(){
		Date today = new Date();
		
		String firstDay = DateUtils.getMonthFirstByInterval(today, -11);
		System.out.println(firstDay);
		
		String date = DateUtils.formatDateToString(today, "MMM dd");
		System.out.println(date);
		
		Date beginDate = DateUtils.beginOfDate(today);
		System.out.println(beginDate);
		
		Date endDate = DateUtils.endOfDate(today);
		System.out.println(endDate);

		Date previousDate = DateUtils.getDayByInterval(today, -1);
		System.out.println(previousDate);
		
		Date nextDate = DateUtils.getDayByInterval(today, 1);
		System.out.println(nextDate);
	}
	
    @Test
  	@Ignore
  	public void encryptionUtilsTest() throws Throwable{
  		String channelId = "18150";
  		String encyption = AESEncryptionHelper.encrypt(channelId, secretKey);
  		System.out.println(encyption);

  		String decryption = AESEncryptionHelper.decrypt(encyption, secretKey);
  		System.out.println(decryption);
  	}

	@Test
	@Ignore
	public void ftpDownloadTest() throws Throwable{
		String responseText = "";
        InputStream response = null;
        InputStreamReader reader = null;
        BufferedReader breader = null;
		
		response = ftpHelper.downloadFile("wostore/wostorechannelapk/response/all", "wostorechannelapk_2014070200000001.tttt");
		
        reader = new InputStreamReader(response, "utf-8");  
        
        breader = new BufferedReader(reader);  
  
        String content = null;  
          
        while ((content = breader.readLine()) != null) {  
        	responseText += content + "\n";  
        }  

		System.out.println(responseText);		
	}
	
	@Test
	public void sftpDownloadTest() throws Throwable{
		String responseText = "";
        InputStream response = null;
        InputStreamReader reader = null;
        BufferedReader breader = null;
		
        try{
			response = sftpHelper.downloadFile("/wostore/wostorechannelapk/response/all/", "wostorechannelapk_2014070200000001.tttt");
			
	        reader = new InputStreamReader(response, "utf-8");  
	        
	        breader = new BufferedReader(reader);  
	  
	        String content = null;  
	          
	        while ((content = breader.readLine()) != null) {  
	        	responseText += content + "\n";  
	        }  
	
			System.out.println(responseText);
        }catch(Exception e){
        	throw e;
        }finally{
        	if(null != response)
        		response.close();
        	if(null != reader)
        		reader.close();
        	if(null != breader)
        		breader.close();
        }        
	}

    @Test
    public void sftpGetFileListTest() throws Throwable{

        try{
            List<String> list = sftpHelper.getFileList("/wostore/wostorechannelapk/response/all/");
            for (String filename : list) {
                System.out.println(filename);
            }
        }catch(Exception e){
            throw e;
        }finally{
        }
    }

    @Test
    public void sftpReadRemoteFile() throws Throwable{
        ChannelSftp sftp = sftpHelper.connectServer();

        try{
            List<String> list = sftpHelper.readRemoteFileByRow("/wostore/wostorechannelapk/response/all/", "/wostore/wostorechannelapk/response/all/", sftp);
            for (String content : list) {
                System.out.println(content);
            }
        }catch(Exception e){
            throw e;
        }finally{
            sftpHelper.closeChannel(sftp.getSession(), sftp);
        }
    }

    @Test
    public void sortListTest() throws Throwable {
        List<String> test = new ArrayList<String>();
        test.add("wostorechannelapk_2014070315000001.tttt");
        test.add("wostorechannelapk_2014070314000002.tttt");
        test.add("wostorechannelapk_2014070315001001.tttt");
        test.add("wostorechannelapk_2014070316000001.tttt");
        test.add("wostorechannelapk_2014070315100001.tttt");

        for (String filename : Utility.sortStringList(test)) {
            System.out.println(filename);
        }
    }

    @Test
    public void getSubStringListTest() throws Throwable {
        List<String> test = new ArrayList<String>();
        test.add("wostorechannelapk_2014070315000001.tttt");
        test.add("wostorechannelapk_2014070314000002.tttt");
        test.add("wostorechannelapk_2014070315001001.tttt");
        test.add("wostorechannelapk_2014070316000001.tttt");
        test.add("wostorechannelapk_2014070315100001.tttt");

//        for (String filename : Utility.sortStringList(test)) {
//            System.out.println(filename);
//        }

        for (String filename : Utility.getSubStringList(test, "wostorechannelapk_2014070315001001.tttt")) {
            System.out.println(filename);
        }
    }

    @Test
    public void testSplit() throws Throwable {
        String a = "1,2,3,,4,,5,,,,";
        String[] s = Utility.splitString(a, ",");
    }

    @Test
    public void testInterval() throws Throwable {
        String a = "1,2,3,,4,,5,,,,";
        String[] s = Utility.splitString(a, ",");
    }
}
