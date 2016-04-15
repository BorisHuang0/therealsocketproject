
import java.io.*;
import java.net.*;
import java.util.Scanner;


public class server{

	public static void main(String[] args) throws Exception {
		server server = new server();
		server.run();

	}

	public void run() throws Exception{
		boolean goOn=true;
		Scanner scanner = new Scanner(System.in);
		while(goOn){
			System.out.print("Enter command: ");
			String cmd = scanner.next();
			ServerSocket ssocket = new ServerSocket(1025);
			Socket SOCK = ssocket.accept();
			InputStreamReader IR = new InputStreamReader(SOCK.getInputStream());
			BufferedReader BR = new BufferedReader(IR);

			String MESSAGE = BR.readLine();
			System.out.println(MESSAGE);

			PrintStream PS = new PrintStream(SOCK.getOutputStream());
			PS.println(cmd);


			ssocket.close();
	}

	}

}
