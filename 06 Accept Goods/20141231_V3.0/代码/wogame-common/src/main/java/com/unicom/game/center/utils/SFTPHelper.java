package com.unicom.game.center.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

/**
 * @author Alex Yin
 * 
 * @Date 2014-7-2
 */
@Component
public class SFTPHelper {
	@Value("#{properties['sftp.host']}")
	private String host;

	@Value("#{properties['sftp.port']}")
	private Integer port;

	@Value("#{properties['sftp.username']}")
	private String username;

	@Value("#{properties['sftp.password']}")
	private String password;
	
	public ChannelSftp connectServer() {
		ChannelSftp sftp = null;
		try {
			JSch jsch = new JSch();
			Session sshSession = jsch.getSession(username, host, port);
			if(!Utility.isEmpty(password)){
				sshSession.setPassword(password);
			}
			
			Properties sshConfig = new Properties();
			sshConfig.put("StrictHostKeyChecking", "no");
			sshSession.setConfig(sshConfig);
			
			sshSession.setTimeout(120000);
			sshSession.connect();
			
			Channel channel = sshSession.openChannel("sftp");
			channel.connect();
			sftp = (ChannelSftp) channel;
		} catch (Exception ex) {
			Logging.logError("Error occur in connectServer.", ex);
			ex.printStackTrace();
		}
		return sftp;
	}
	
	public void closeChannel(Session session, Channel channel) throws Exception {
		if (channel != null) {
			channel.disconnect();
		}
		if (session != null) {
			session.disconnect();
		}
	}
	
	public void download(String directory, String downloadFile,String saveFile, ChannelSftp sftp) {
		try {
			sftp.cd(directory);
			File file=new File(saveFile);
			sftp.get(downloadFile, new FileOutputStream(file));
		} catch (Exception ex) {
			Logging.logError("Error occur in download.", ex);
		}
	}
	
	public InputStream downloadFile(String path, String filename) {
		ChannelSftp sftp = connectServer();
		if(null != sftp){
			try {
				sftp.cd(path);
				return sftp.get(filename);
			} catch (Exception e) {
                Logging.logError("Error occur in downloadFile.",e);
			}finally {
				try {
//					closeChannel(sftp.getSession(), sftp);
				} catch (Exception e) {
					Logging.logError("FTP Disconnect Error: ",e);
				}
			}
		}

		return null;
	}

    public InputStream downloadFile(String path, String filename, ChannelSftp sftp) {
   		if(null != sftp){
   			try {
   				sftp.cd(path);
   				return sftp.get(filename);
   			} catch (Exception e) {
                   Logging.logError("Error occur in downloadFile.",e);
   			}finally {
   				try {
   //					closeChannel(sftp.getSession(), sftp);
   				} catch (Exception e) {
   					Logging.logError("FTP Disconnect Error: ",e);
   				}
   			}
   		}

   		return null;
   	}

    public List<String> readRemoteFileByRow(String path, String filename, ChannelSftp sftp) throws Exception {

        InputStream response = null;
        InputStreamReader reader = null;
        BufferedReader breader = null;

        List<String> list = new ArrayList<String>();
        try {
            String str = null;

            response = downloadFile(path, filename, sftp);

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

    public List<String> getFileList(String path) {
   		ChannelSftp sftp = connectServer();
        List<String> list = new ArrayList<String>();
   		if(null != sftp){
   			try {
   				sftp.cd(path);

                Vector vector = sftp.ls(path);
                for (int i = 0;i < vector.size();i++) {
                    ChannelSftp.LsEntry f = (ChannelSftp.LsEntry)vector.get(i);
                    String name = f.getFilename();
                    if (!name.startsWith(".")) {
                        list.add(f.getFilename());
                    }
                }
   				return list;
   			} catch (Exception e) {
                   Logging.logError("Error occur in downloadFile.",e);
                   e.printStackTrace();
   			}finally {
   				try {
   					closeChannel(sftp.getSession(), sftp);
   				} catch (Exception e) {
   					Logging.logError("FTP Disconnect Error: ",e);
   				}
   			}
   		}

   		return null;
   	}
	
	public void delete(String directory, String deleteFile, ChannelSftp sftp) {
		try {
			sftp.cd(directory);
			sftp.rm(deleteFile);
		} catch (Exception ex) {
			Logging.logError("Error occur in delete.", ex);
		}
	}
	
	
	public boolean uploadFile(String src, String desc){
		boolean flag = false;
		
		if(Utility.isEmpty(src) || Utility.isEmpty(desc)){
			return flag;
		}
		
		ChannelSftp sftp = connectServer();
		if(null != sftp){
			try{
				sftp.put(src, desc, ChannelSftp.OVERWRITE);				
				sftp.quit();
				
				flag = true;
			}catch(Exception e){
				Logging.logError("Error occur in uploadFile.", e);
			}finally {
   				try {
   					closeChannel(sftp.getSession(), sftp);
   				} catch (Exception e) {
   					Logging.logError("FTP Disconnect Error: ",e);
   				}
   			}			
		}
		
		return flag;
	}
	
	public boolean uploadFile(InputStream src, String desc){
		boolean flag = false;
		
		if(null == src){
			return flag;
		}
		
		ChannelSftp sftp = connectServer();
		if(null != sftp){
			try{
				sftp.put(src, desc, ChannelSftp.OVERWRITE);				
				sftp.quit();
				
				flag = true;
			}catch(Exception e){
				Logging.logError("Error occur in uploadFile.", e);
			}finally {
				if(null != src){
					try {
						src.close();
					} catch (IOException e) {
						Logging.logError("Error occur in close input stream.", e);
					}
				}
				
   				try {
   					closeChannel(sftp.getSession(), sftp);
   				} catch (Exception e) {
   					Logging.logError("FTP Disconnect Error: ",e);
   				}
   			}			
		}
		
		return flag;
	}	

}
