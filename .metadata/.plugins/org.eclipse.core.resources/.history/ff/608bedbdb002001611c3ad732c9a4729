
import java.io.*;
import java.net.*;

public class client {

	public static void main(String[] args) throws Exception {

		client client = new client();
		client.run();

	}

	public void run() throws Exception{
<<<<<<< HEAD
		
		Socket SOCK = new Socket("128.4.111.223",1025);
=======

		Socket SOCK = new Socket("localhost", 444);
>>>>>>> 9d3db2b44529773351742dfb5ee2d0b31aec6cf4
		PrintStream PS = new PrintStream(SOCK.getOutputStream());
		PS.println("HELLO to Server From Client");

		InputStreamReader IR = new InputStreamReader(SOCK.getInputStream());
		BufferedReader BR = new BufferedReader(IR);

		String MESSAGE = BR.readLine();
		System.out.println(MESSAGE);


	}

}
