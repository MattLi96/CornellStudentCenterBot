package core;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Encrypter {
	//To generate a key, just type 16 random characters
	private static String key = "a9bka89hielcn54c"; // 128 bit key
	
	/**
	 * Encrypts the given netID and password into an info.txt file
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Scanner s = new Scanner(System.in);
			System.out.print("Input password: ");
			String password = s.nextLine();

			// Create key and cipher
			Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES");

			// encrypt the text
			cipher.init(Cipher.ENCRYPT_MODE, aesKey);
			byte[] ePass = cipher.doFinal(password.getBytes());

			//write to file
			FileOutputStream fo = new FileOutputStream("info.txt");
			fo.write(ePass);
			fo.close();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static String decrypt(){
		// decrypt the text
		byte[] ret = new byte[0];
		try{
			//read bytes in
			Path path = Paths.get("info.txt");
			byte[] data = Files.readAllBytes(path);

			//decrypt
			Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, aesKey);
			ret = cipher.doFinal(data);
		} catch (IOException | NoSuchAlgorithmException 
				| NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new String(ret);
	}
}
