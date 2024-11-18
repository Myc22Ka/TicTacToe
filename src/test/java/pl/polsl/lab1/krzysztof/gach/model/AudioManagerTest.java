/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pl.polsl.lab1.krzysztof.gach.model;

import javax.sound.sampled.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Krzysztof
 */
class AudioManagerTest {

    private AudioManager audioManager;
    private Clip mockClip;
    private FloatControl mockVolumeControl;

    @BeforeEach
    void setUp() throws LineUnavailableException {
        audioManager = AudioManager.getInstance();

        // Mock the Clip and FloatControl
        mockClip = mock(Clip.class);
        mockVolumeControl = mock(FloatControl.class);

        when(mockClip.getControl(FloatControl.Type.MASTER_GAIN)).thenReturn(mockVolumeControl);

        // Inject the mock clip into the audio manager
        audioManager.setAudioClip(mockClip);
    }

    @Test
    void testSingletonInstance() {
        // GIVEN & WHEN
        AudioManager anotherInstance = AudioManager.getInstance();

        // THEN
        assertSame(audioManager, anotherInstance, "AudioManager should return the same instance");
    }

    @Test
    void testSingletonInstanceMultipleCalls() {
        // GIVEN & WHEN
        AudioManager firstInstance = AudioManager.getInstance();
        AudioManager secondInstance = AudioManager.getInstance();

        // THEN
        assertSame(firstInstance, secondInstance, "Multiple calls to getInstance should return the same instance");
    }

    @Test
    void testSingletonInstanceAfterReset() {
        // GIVEN
        audioManager = null; // Reset the instance

        // WHEN
        AudioManager newInstance = AudioManager.getInstance();

        // THEN
        assertNotNull(newInstance, "A new instance should be created after reset");
    }

    @Test
    void testSetMasterVolume() {
        // GIVEN
        float validVolume = -5.0f;

        // WHEN
        audioManager.setMasterVolume(validVolume);

        // THEN
        assertEquals(validVolume, audioManager.getMasterVolume(), "Master volume should be updated");
        verify(mockVolumeControl).setValue(validVolume);
    }
    
    @Test
    void testSetMasterVolumeNegative() {
        // GIVEN
        float validNegativeVolume = -30.0f;

        // WHEN
        audioManager.setMasterVolume(validNegativeVolume);

        // THEN
        assertEquals(validNegativeVolume, audioManager.getMasterVolume(), "Volume should be correctly set to a valid negative value");
    }
    
    @Test
    void testSetMasterVolumePositive() {
        // GIVEN
        float validPositiveVolume = 4.5f;

        // WHEN
        audioManager.setMasterVolume(validPositiveVolume);

        // THEN
        assertEquals(validPositiveVolume, audioManager.getMasterVolume(), "Volume should be correctly set to a valid positive value");
    }

    @Test
    void testSetMasterVolumeClamped() {
        // GIVEN
        float tooLowVolume = -100.0f;
        float tooHighVolume = 10.0f;

        // WHEN
        audioManager.setMasterVolume(tooLowVolume);
        float clampedLowVolume = audioManager.getMasterVolume();

        audioManager.setMasterVolume(tooHighVolume);
        float clampedHighVolume = audioManager.getMasterVolume();

        // THEN
        assertEquals(-80.0f, clampedLowVolume, "Volume should be clamped to -80.0f");
        assertEquals(6.0f, clampedHighVolume, "Volume should be clamped to 6.0f");
    }
    
    @Test
    void testSetMasterVolumeBelowMin() {
        // GIVEN
        float tooLowVolume = -200.0f;

        // WHEN
        audioManager.setMasterVolume(tooLowVolume);

        // THEN
        assertEquals(-80.0f, audioManager.getMasterVolume(), "Volume should be clamped to -80.0f if below minimum");
    }
    
    @Test
    void testSetMasterVolumeAboveMax() {
        // GIVEN
        float tooHighVolume = 100.0f;

        // WHEN
        audioManager.setMasterVolume(tooHighVolume);

        // THEN
        assertEquals(6.0f, audioManager.getMasterVolume(), "Volume should be clamped to 6.0f if above maximum");
    }

    @Test
    void testPlay() {
        // GIVEN & WHEN
        audioManager.play();

        // THEN
        verify(mockClip).setFramePosition(0);
        verify(mockClip).start();
    }
    
    @Test
    void testPlayWhenAlreadyPlaying() {
        // GIVEN
        when(mockClip.isRunning()).thenReturn(true);

        // WHEN
        audioManager.play();

        // THEN
        verify(mockClip, times(1)).setFramePosition(0); // Should still reset position
        verify(mockClip, times(1)).start();  // Should not restart the clip if already playing
    }

    @Test
    void testPlayClipNotPlaying() {
        // GIVEN
        when(mockClip.isRunning()).thenReturn(false);

        // WHEN
        audioManager.play();

        // THEN
        verify(mockClip).setFramePosition(0);
        verify(mockClip).start();
    }

    @Test
    void testPause() {
        // GIVEN
        when(mockClip.isRunning()).thenReturn(true);

        // WHEN
        audioManager.pause();

        // THEN
        verify(mockClip).stop();
    }
    
    @Test
    void testPauseWhenNotRunning() {
        // GIVEN
        when(mockClip.isRunning()).thenReturn(false);

        // WHEN
        audioManager.pause();

        // THEN
        verify(mockClip, times(0)).stop(); // It should not stop if the clip is already paused
    }

    @Test
    void testPauseAfterStop() {
        // GIVEN
        when(mockClip.isRunning()).thenReturn(false);

        // WHEN
        audioManager.pause();

        // THEN
        verify(mockClip, times(0)).stop(); // It should not stop if the clip is already stopped
    }
    
    @Test
    void testResume() {
        // GIVEN
        when(mockClip.isRunning()).thenReturn(false);

        // WHEN
        audioManager.resume();

        // THEN
        verify(mockClip).start();
    }
    
    @Test
    void testResumeWhenAlreadyRunning() {
        // GIVEN
        when(mockClip.isRunning()).thenReturn(true);

        // WHEN
        audioManager.resume();

        // THEN
        verify(mockClip, times(0)).start(); // No action should be taken if already running
    }

    @Test
    void testResumeAfterPause() {
        // GIVEN
        when(mockClip.isRunning()).thenReturn(false);

        // WHEN
        audioManager.resume();

        // THEN
        verify(mockClip).start();
    }

    @Test
    void testStop() {
        // GIVEN & WHEN
        audioManager.stop();

        // THEN
        verify(mockClip).stop();
        verify(mockClip).close();
        assertNull(audioManager.getAudioClip(), "AudioClip should be null after stopping");
    }
    
    @Test
    void testStopWhenNotRunning() {
        // GIVEN
        when(mockClip.isRunning()).thenReturn(false);

        // WHEN
        audioManager.stop();

        // THEN
        verify(mockClip, times(1)).stop(); // Ensure stop is still called even if not running
    }

    @Test
    void testStopMultipleTimes() {
        // GIVEN & WHEN
        audioManager.stop();
        audioManager.stop(); // Call stop again

        // THEN
        verify(mockClip, times(1)).stop(); // Stop should only be called once
        verify(mockClip, times(1)).close(); // Close should only be called once
    }

    @Test
    void testIsPlaying() {
        // GIVEN
        when(mockClip.isRunning()).thenReturn(true);

        // WHEN
        boolean isPlaying = audioManager.isPlaying();

        // THEN
        assertTrue(isPlaying, "Audio should be playing");
    }
    
    @Test
    void testIsPlayingWhenPaused() {
        // GIVEN
        when(mockClip.isRunning()).thenReturn(false);

        // WHEN
        boolean isPlaying = audioManager.isPlaying();

        // THEN
        assertFalse(isPlaying, "Audio should not be playing when paused");
    }

    @Test
    void testIsPlayingWhenStopped() {
        // GIVEN
        when(mockClip.isRunning()).thenReturn(false);

        // WHEN
        boolean isPlaying = audioManager.isPlaying();

        // THEN
        assertFalse(isPlaying, "Audio should not be playing when stopped");
    }
}