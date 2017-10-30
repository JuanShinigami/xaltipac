package org.lldm.xaltipac.service.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Clase para encriptar la contraseña.
 * @author Juan Mateo Sauce.
 *
 */

public class PasswordEncrypter {
	
    public static String encrypt(String password) throws Exception {
    	
        byte encPwd[] = MD5Hash(password.getBytes());
        String hexPwd = hex(encPwd);
        return hexPwd;
    }
    
    private static byte[] MD5Hash(byte data[]) throws NoSuchAlgorithmException {
    	
            MessageDigest md = MessageDigest.getInstance("MD5");
            return md.digest(data);
    }
    
    private static String hex(byte buffer[]) {
    	
        char kHexChars[] = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
            'A', 'B', 'C', 'D', 'E', 'F'
        };
        StringBuffer hexString = new StringBuffer(2 * buffer.length);
        for(int i = 0; i < buffer.length; i++)
        {
            char highNibble = kHexChars[(buffer[i] & 0xf0) >> 4];
            char lowNibble = kHexChars[buffer[i] & 0xf];
            hexString.append(highNibble);
            hexString.append(lowNibble);
        }

        return hexString.toString();
    }

    public static void main(String args[]) throws Exception {
    	
        String clear = "ireneipp08";
        System.out.println("clear : " + clear + " hash : " + encrypt(clear));
    }

}
