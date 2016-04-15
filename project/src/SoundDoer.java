import java.net.URL;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 * class used to play sound
 * @author abrah
 *
 */
public class SoundDoer {

	private ArrayList<Clip>loadedClips;
	private float gain=0.0f;
	/**
	 * initializes this sound doer
	 */
	public SoundDoer(){
		loadedClips = new ArrayList<Clip>();
	}

	/**
	 * loads a clip so that it can be played back quickly
	 * @param path
	 */
	public void loadClip(URL file){
		try{

				AudioInputStream in = AudioSystem.getAudioInputStream(file);
				Clip audioClip = AudioSystem.getClip();
				audioClip.open(in);
				loadedClips.add(audioClip);
			}
		catch(Exception e){
			e.printStackTrace();


		}
	}

	/**
	 * plays a loaded clip, with index starting at 0 in order of load
	 * @param index
	 */
	public void playLoadedClip(int index){
		loadedClips.get(index).setFramePosition(0);
		loadedClips.get(index).start();

	}

	/**
	 * stops all the sound and releases resources
	 */
	public void stopAllSoundAndReleaseResources(){
		for(int i=0; i<loadedClips.size();i++){
			loadedClips.get(i).stop();
			loadedClips.get(i).flush();
			loadedClips.get(i).close();
		}

		loadedClips.clear();



	}
	/**
	 * sets a clips volume
	 * @param index the index of load
	 * @param amt the volume
	 */
	public void setClipVolume(int index,float amt){
		FloatControl fc = (FloatControl) loadedClips.get(index).getControl(FloatControl.Type.MASTER_GAIN);
		fc.setValue(amt);
	}


	/**
	 * returns true if the fade has completed, false otherwise.  Only fade out 1 clip at a time! (or change this method)
	 * @param index
	 * @return
	 */
	public boolean fadeOutFast(int index){
		FloatControl fc = (FloatControl) loadedClips.get(index).getControl(FloatControl.Type.MASTER_GAIN);
		if(gain>-70){
			gain-=10;
			fc.setValue(gain);
		}else{
			stopClip(index);
			gain=0.0f;
			return true;
		}
		return false;
	}


	/*
	public boolean fadeOutSlow(int index){
		FloatControl fc = (FloatControl) loadedClips.get(index).getControl(FloatControl.Type.MASTER_GAIN);

		Thread t = new Thread(new Runnable() {
			private long lastTime;
			private long now;
			@Override
			public void run() {
				while(gain>-70){
					fc.setValue(gain);
					gain-=.00001;

					lastTime=System.nanoTime();
					lastTime=now;
					while(now-lastTime<1000){
						now=System.nanoTime();
					}

				}
				gain=0.0f;
				stopClip(index);

			}
		});

		t.start();
		return true;

	}
	*/
	/**
	 * stops a specific clip
	 * @param index the index of loading
	 */
	public void stopClip(int index){
		loadedClips.get(index).stop();
	    loadedClips.get(index).flush();

	}

	/**
	 * returns whether a specific clip is done playing . may not work
	 * @param index
	 * @return boolean
	 */
	public boolean isDone(int index){
		return !loadedClips.get(index).isActive();
	}




}
