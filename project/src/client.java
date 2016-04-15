
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;

public class client {
	private static boolean goOn=true;
	public static void main(String[] args) throws Exception {

		client client = new client();
		client.run();

	}

	public void run() throws Exception{

		while(goOn){

			Socket SOCK = new Socket("128.4.111.223",1025);
			// PrintStream PS = new PrintStream(SOCK.getOutputStream());
			// PS.println("HELLO to Server From Client");

			InputStreamReader IR = new InputStreamReader(SOCK.getInputStream());
			BufferedReader BR = new BufferedReader(IR);

			String cmd = BR.readLine();
			processCmd(cmd);
			SOCK.close();
		}

		//Thread.sleep(1000*60*5);//sleeps 5 minutes
		//check for ip
		//reconnect to server if server up


	}

	private static void processCmd(String cmd){
		if(cmd.equals("flip-screen")){
			flipScreen();
		}
		else if (cmd.equals("quit")){
			goOn=false;
		}
		else if (cmd.equals("play-asian-music")){
			playAsianMusic();
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

	private static void playAsianMusic(){
		try {
			System.out.println("1");
			URL song = new URL("http://www.abrahamd.mcilvaine.net/audio/chinese-music.wav");
			SoundDoer sd = new SoundDoer();
			System.out.println("2");
			sd.loadClip(song);
			sd.playLoadedClip(0);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
