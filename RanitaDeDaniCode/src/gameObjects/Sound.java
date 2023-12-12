package gameObjects;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
    private Clip clip;

    private FloatControl volume;

    public Sound(Clip clip){
        this.clip = clip;
        volume = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
    }

    public void play(){
        clip.setFramePosition(0);
        clip.start();
    }

    //para la bgmusic

    public void loop(){
        clip.setFramePosition(0);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){
        clip.stop();
    }

    public int getFramePosition(){
        return clip.getFramePosition();
    }

    public void changeVol(float value){
        volume.setValue(value);
    }
}
