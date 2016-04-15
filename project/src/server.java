
import java.io.*;
import java.net.*;


public class server{

	public static void main(String[] args) throws Exception {
		server server = new server();
		server.run();

	}
	
	public void run() throws Exception{
		ServerSocket ssocket = new ServerSocket(444);
		Socket SOCK = ssocket.accept();
		InputStreamReader IR = new InputStreamReader(SOCK.getInputStream());
		BufferedReader BR = new BufferedReader(IR);
		
		String MESSAGE = BR.readLine();
		System.out.println(MESSAGE);
		
		if (MESSAGE != null) {
			PrintStream PS = new PrintStream(SOCK.getOutputStream());
			PS.println("MESSAGE received!");
		}
	
	}

}
