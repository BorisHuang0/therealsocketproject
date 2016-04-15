
import java.io.*;
import java.net.*;
import java.awt.Robot;

public class client {

	public static void main(String[] args) throws Exception {

		client client = new client();
		client.run();

	}

	public void run() throws Exception{

		boolean goOn =true;
		while(goOn){

		Socket SOCK = new Socket("128.4.111.223",1025);
		PrintStream PS = new PrintStream(SOCK.getOutputStream());
		PS.println("HELLO to Server From Client");

		InputStreamReader IR = new InputStreamReader(SOCK.getInputStream());
		BufferedReader BR = new BufferedReader(IR);

		String cmd = BR.readLine();
		processCmd(cmd);
		SOCK.close();
		}


	}

	private static void processCmd(String cmd){
		if(cmd.equals("flip-screen")){
			flipScreen();
		}
	}

	private static void flipScreen(){
		try {
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_ALT);
			r.keyPress(KeyEvent.VK_DOWN);
			long lastTime = System.currentTimeMillis();
			while(System.currentTimeMillis() - lastTime < 300){

			}
			r.keyRelease(KeyEvent.VK_CONTROL);
			r.keyRelease(KeyEvent.VK_ALT);
			r.keyRelease(KeyEvent.VK_DOWN);

	} catch (AWTException e) {
		e.printStackTrace();
	}
	}

}
