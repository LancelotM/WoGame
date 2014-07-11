package com.unicom.game.center.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FTPHelper {

	@Value("#{properties['ftp.host']}")
	private String host;

	@Value("#{properties['ftp.port']}")
	private int port;

	@Value("#{properties['ftp.username']}")
	private String username;

	@Value("#{properties['ftp.password']}")
	private String password;

	private FTPClient ftpClient;


	/**
	 * Download file
	 * 
	 * @param path
	 * @param filename
	 */
	public InputStream downloadFile(String path, String filename) {
		if (connectServer()) {
			try {
				ftpClient.changeWorkingDirectory(path);
				ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
				return ftpClient.retrieveFileStream(filename);
			} catch (Exception e) {
                Logging.logError("exception",e);
			}finally {
				try {
					ftpClient.disconnect();
				} catch (Exception e) {
					Logging.logError("FTP Disconnect Error: ",e);
				}
			}
		}
		return null;
	}

	public void disConnectFtpServer(){
		try {
			ftpClient.disconnect();
		} catch (IOException e) {
            Logging.logError("exception",e);
		}
	}
		

	private boolean connectServer() {
		if (ftpClient == null || !ftpClient.isConnected()) {
			int reply;
			try {
				if(null == ftpClient){
					ftpClient = new FTPClient();
				}
				
				ftpClient.connect(host, port);
				ftpClient.login(username, password);
				ftpClient.setDefaultPort(port);
				reply = ftpClient.getReplyCode();
				if (!FTPReply.isPositiveCompletion(reply)) {
					ftpClient.disconnect();
					return false;
				}
			} catch (Exception e) {
                Logging.logError("exception",e);
			}
		}
		return true;
	}

    public List<String> getFileList(String path) {
        List<String> list = new ArrayList<String>();
   		if(connectServer()){
   			try {
   				ftpClient.changeWorkingDirectory(path);
   				
   				FTPFile[] ftpFiles = ftpClient.listFiles(path);
   				for(int i = 0; ftpFiles != null && i < ftpFiles.length; i++){
   					FTPFile file = ftpFiles[i];
   					if(file.isFile()){
   						list.add(file.getName());
   					}
   				}
   				
   				return list;
   			} catch (Exception e){
                   Logging.logError("Error occur in getFileList.",e);
   			}finally {
				try {
					ftpClient.disconnect();
				} catch (Exception e) {
					Logging.logError("FTP Disconnect Error: ",e);
				}
   			}
   		}

   		return null;
   	}
    
    public List<String> readRemoteFileByRow(String path, String filename) throws Exception {

        InputStream response = null;
        InputStreamReader reader = null;
        BufferedReader breader = null;

        List<String> list = new ArrayList<String>();
        try {
            String str = null;

            response = downloadFile(path, filename);

            reader = new InputStreamReader(response, "utf-8");

            breader = new BufferedReader(reader);

            while ((str = breader.readLine()) != null) {
                list.add(str);
            }

            return list;
        } catch (Exception e) {
            Logging.logError("Error occur in downloadFile.", e);
        } finally {
            if(null != response)
           		response.close();
           	if(null != reader)
           		reader.close();
           	if(null != breader)
           		breader.close();
        }

        return null;
    }    
	
}
