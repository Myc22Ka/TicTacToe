package pl.polsl.lab1.krzysztof.gach.model;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * The AudioManager class handles audio playback and volume control for the application.
 * This singleton class provides methods to load, play, pause, resume, and stop audio,
 * as well as adjust the master volume.
 * 
 * @author Krzysztof Gach
 * @version 1.0
 */
public class AudioManager {
    private static AudioManager instance;
    private Clip audioClip;
    private float masterVolume = 0.0f;

    /**
     * Private constructor to prevent instantiation from outside the class.
     * Initializes the master volume to a default level.
     */
    private AudioManager() {
        masterVolume = -10.0f;
    }

    /**
     * Retrieves the single instance of AudioManager.
     * 
     * @return the singleton instance of AudioManager
     */
    public static AudioManager getInstance() {
        if (instance == null) {
            instance = new AudioManager();
        }
        return instance;
    }

    /**
     * Loads an audio file and prepares it for playback.
     * 
     * @param filePath the path to the audio file to load
     */
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

    /**
     * Sets the master volume for audio playback.
     * 
     * @param volume the desired volume level in decibels, clamped between -80.0 and 6.0
     */
    public void setMasterVolume(float volume) {
        masterVolume = Math.max(-80.0f, Math.min(volume, 6.0f));
        if (audioClip != null) {
            FloatControl volumeControl = (FloatControl) audioClip.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(volume);
        }
    }
    
    /**
     * Retrieves the current master volume level.
     * 
     * @return the current master volume in decibels
     */
    public float getMasterVolume(){
        return masterVolume;
    }

    /**
     * Starts or restarts audio playback from the beginning.
     */
    public void play() {
        if (audioClip != null) {
            audioClip.setFramePosition(0);
            audioClip.start();
        }
    }

    /**
     * Pauses audio playback if it is currently playing.
     */
    public void pause() {
        if (audioClip.isRunning()) {
            audioClip.stop();
        }
    }

    /**
     * Resumes audio playback if it is currently paused.
     */
    public void resume() {
        if (!audioClip.isRunning()) {
            audioClip.start();
        }
    }

    /**
     * Stops audio playback and releases resources associated with the audio clip.
     */
    public void stop() {
        if (audioClip != null) {
            audioClip.stop();
            audioClip.close();
            audioClip = null;
        }
    }

    /**
     * Checks if audio is currently playing.
     * 
     * @return true if the audio is playing, false otherwise
     */
    public boolean isPlaying() {
        return audioClip != null && audioClip.isRunning();
    }
}
