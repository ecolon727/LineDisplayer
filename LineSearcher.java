package ServerStuff;
import java.util.*;   
import java.nio.file.*;
import java.net.*;
import java.io.*;
import java.io.IOException;


public class LineSearcher {	
	
	private static List<String> lines = new ArrayList<String>();
	
	public static void main(String[] args) {
			
				try {
					ServerSocket server = new ServerSocket(1236);
					Socket client       = server.accept();										
					OutputStream out    = client.getOutputStream();
					BufferedReader in   = new BufferedReader(new InputStreamReader(client.getInputStream()));
	
					
					// readFile
					readFile("hamlet.txt");				
					String clientString = in.readLine();
					System.out.println("You put: " + clientString + "\n");

	
					// convert returnLines to Builder
					int n = Integer.valueOf(clientString);				
					List<String> newList = returnLines(n);	
					StringBuilder builder = new StringBuilder();
					
					for (String str : newList) 
						builder.append(str.concat("\n"));													
	
					// send Builder to Client
					String response = builder.toString();					
					out.write(response.getBytes());
					
				} catch (IOException e) {
					e.printStackTrace();
					System.exit(-1); 
				}
		}
			
	
	// readFile class
	public static List<String> readFile(String fileName) {	
		
		try {
			lines = Files.readAllLines(Paths.get(fileName));
			
		} catch (Exception e) {
			System.out.println("could not find file");
		}		
			return lines;
	}	
	
	
	// returnLines class
	public static List<String> returnLines (int n) {		
		List<String> givenLines = new ArrayList<String>();
		
		int n1 = n - 1;
		int sub2 = n - 3;
		int sub1 = n - 2;
		int add1 = n;
		int add2 = n + 1;	
		
		if (sub2 >= 0) 
			givenLines.add(lines.get(sub2));
		
		if (sub1 >= 0) 
			givenLines.add(lines.get(sub1));
		
			givenLines.add(lines.get(n1));	
			
		if(add1 < lines.size()) 
			givenLines.add(lines.get(add1));	
		
		if(add2 < lines.size()) 
			givenLines.add(lines.get(add2));		
		
		for (String str : givenLines)
			System.out.println(str);
		
		return givenLines;
	}
	
}
