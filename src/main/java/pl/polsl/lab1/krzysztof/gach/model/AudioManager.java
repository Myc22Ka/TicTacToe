package pl.polsl.lab1.krzysztof.gach.model;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioManager {
    private static AudioManager instance;
    private Clip audioClip;
    private float masterVolume;

    private AudioManager() {
        masterVolume = -10.0f;
    }

    public static AudioManager getInstance() {
        if (instance == null) {
            instance = new AudioManager();
        }
        return instance;
    }

    public void loadAudio(String filePath) {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            audioClip = AudioSystem.getClip();
            audioClip.open(audioStream);

            FloatControl volumeControl = (FloatControl) audioClip.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(masterVolume);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {}
    }

    public void setMasterVolume(float volume) {
        masterVolume = Math.max(-80.0f, Math.min(volume, 6.0f));
        if (audioClip != null) {
            FloatControl volumeControl = (FloatControl) audioClip.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(volume);
        }
    }
    
    public float getMasterVolume(){
        return masterVolume;
    }

    public void play() {
        if (audioClip != null) {
            audioClip.setFramePosition(0);
            audioClip.start();
        }
    }

    public void pause() {
        if (audioClip.isRunning()) {
            audioClip.stop();
        }
    }

    public void resume() {
        if (!audioClip.isRunning()) {
            audioClip.start();
        }
    }

    public void stop() {
        if (audioClip != null) {
            audioClip.stop();
            audioClip.close();
            audioClip = null;
        }
    }

    public boolean isPlaying() {
        return audioClip != null && audioClip.isRunning();
    }
}
