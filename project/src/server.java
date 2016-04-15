
import java.io.*;
import java.net.*;
import java.util.Scanner;


public class server{
	private static boolean goOn=true;
	public static void main(String[] args) throws Exception {
		server server = new server();
		server.run();

	}

	public void run() throws Exception{

		while(goOn){
			ServerSocket ssocket = new ServerSocket(1025);
			Socket SOCK = ssocket.accept();
			InputStreamReader IR = new InputStreamReader(SOCK.getInputStream());
			BufferedReader BR = new BufferedReader(IR);
			String MESSAGE = BR.readLine();
			System.out.print(MESSAGE);
			Scanner scanner = new Scanner(System.in);
			String cmd = scanner.next();
			if(cmd.equals("quit")){
				goOn=false;
			}
			PrintStream PS = new PrintStream(SOCK.getOutputStream());
			PS.println(cmd);


			ssocket.close();
	}

	}

}
