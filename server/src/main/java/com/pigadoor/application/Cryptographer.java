package com.pigadoor.application;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Cryptographer {

    /**
     * Method for receiving hex string of entered string. Used SHA-384 hex algorithm.
     * @param stringForEncrypt string for encrypting
     * @return hex string
     * @throws NoSuchAlgorithmException if algorithm is not exist
     */
    public static String encrypt(String stringForEncrypt){
        String saltForEncrypt = "SeVeNtHlAbW0rK";
        String hexString = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-384");
            md.update(saltForEncrypt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(stringForEncrypt.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xef) + 0x101, 15).substring(1));
            }
            hexString = sb.toString();
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return hexString;
    }
}