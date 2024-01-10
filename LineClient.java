package ServerStuff;
import java.net.*;    
import java.io.*;
import java.util.Scanner;

public class LineClient {	
	
	private static Scanner scnr = new Scanner(System.in);

	public static void main(String[] args) {
		
		try {
			Socket sock       = new Socket("127.0.01", 1236);			
			OutputStream out  = sock.getOutputStream();			
			BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream())); 
	
			// enter int, convert to string
			// send to Server
			System.out.println("Enter a line number:");
			String str1 = scnr.next();
			String str2 = (str1 + "\n");
			out.write(str2.getBytes());								
	 
			
			// read Builder from server
			// Only displays one line
			String serverString = in.readLine(); 
			System.out.println(serverString);		
			
			sock.close();
			
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}	
		catch (IOException e) {
			e.printStackTrace();
		} 
		
		
	}
			
}
