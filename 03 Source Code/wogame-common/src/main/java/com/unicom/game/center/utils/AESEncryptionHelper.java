package com.unicom.game.center.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;

/**
 * @author Alex Yin
 * @version Aug 10, 2013 3:08:11 PM
 *
 */


public class AESEncryptionHelper {

    protected static Logger logger = Logger.getLogger(AESEncryptionHelper.class);

	private final static String AES = "AES";
	
	public static String encrypt(String content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance(AES);
			
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");   
			
            secureRandom.setSeed(password.getBytes()); 

			kgen.init(128, secureRandom);

			SecretKey secretKey = kgen.generateKey();

			byte[] enCodeFormat = secretKey.getEncoded();

			SecretKeySpec key = new SecretKeySpec(enCodeFormat, AES);

			Cipher cipher = Cipher.getInstance(AES);

			byte[] byteContent = content.getBytes("utf-8");

			cipher.init(Cipher.ENCRYPT_MODE, key);

			byte[] result = cipher.doFinal(byteContent);
			
			return parseByte2HexStr(result);
		} catch (NoSuchAlgorithmException e) {
            logger.error("exception",e);
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
            logger.error("exception",e);
			e.printStackTrace();
		} catch (InvalidKeyException e) {
            logger.error("exception",e);
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
            logger.error("exception",e);
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
            logger.error("exception",e);
			e.printStackTrace();
		} catch (BadPaddingException e) {
            logger.error("exception",e);
			e.printStackTrace();
		}
		return null;
	}
	
	private static byte[] decrypt(byte[] content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance(AES);
			
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");   
			
            secureRandom.setSeed(password.getBytes()); 

			kgen.init(128, secureRandom);

			SecretKey secretKey = kgen.generateKey();

			byte[] enCodeFormat = secretKey.getEncoded();

			SecretKeySpec key = new SecretKeySpec(enCodeFormat, AES);

			Cipher cipher = Cipher.getInstance(AES);

			cipher.init(Cipher.DECRYPT_MODE, key);

			byte[] result = cipher.doFinal(content);

			return result;
		} catch (NoSuchAlgorithmException e) {
            logger.error("exception",e);
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
            logger.error("exception",e);
			e.printStackTrace();
		} catch (InvalidKeyException e) {
            logger.error("exception",e);
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
            logger.error("exception",e);
			e.printStackTrace();
		} catch (BadPaddingException e) {
            logger.error("exception",e);
			e.printStackTrace();
		}
		return null;
	}

	
	public static String decrypt(String src, String key) throws Exception{
		return new String(decrypt(parseHexStr2Byte(src), key));
	}

	private static String parseByte2HexStr(byte buf[]) {  
        StringBuffer sb = new StringBuffer();  
        for (int i = 0; i < buf.length; i++) {  
                String hex = Integer.toHexString(buf[i] & 0xFF);  
                if (hex.length() == 1) {  
                        hex = '0' + hex;  
                }  
                sb.append(hex.toUpperCase());  
        }  
        return sb.toString();  
	}
	
	private static byte[] parseHexStr2Byte(String hexStr) {  
        if (hexStr.length() < 1)  
                return null;  
        byte[] result = new byte[hexStr.length()/2];  
        for (int i = 0;i< hexStr.length()/2; i++) {  
                int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);  
                int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);  
                result[i] = (byte) (high * 16 + low);  
        }  
        return result;  
	} 
	
}
