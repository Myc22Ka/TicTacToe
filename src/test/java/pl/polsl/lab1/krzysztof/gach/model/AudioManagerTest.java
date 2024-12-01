package pl.polsl.lab1.krzysztof.gach.model;

import javax.sound.sampled.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@link AudioManager} class, validating audio volume control 
 * and behavior of sound-related functionality.
 * 
 * @author Krzysztof Gach
 * @version 1.1
 */
class AudioManagerTest {

    private AudioManager audioManager;
    private Clip mockClip;
    private FloatControl mockVolumeControl;

    @BeforeEach
    void setUp() throws LineUnavailableException {
        audioManager = AudioManager.getInstance();
        mockClip = mock(Clip.class);
        mockVolumeControl = mock(FloatControl.class);
        when(mockClip.getControl(FloatControl.Type.MASTER_GAIN)).thenReturn(mockVolumeControl);
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
    
    @ParameterizedTest
    @CsvSource({"-5.0, -5.0", "0.0, 0.0", "4.5, 4.5"})
    void testSetMasterVolumeValid(float inputVolume, float expectedVolume) {
        // WHEN
        audioManager.setMasterVolume(inputVolume);
        
        // THEN
        assertEquals(expectedVolume, audioManager.getMasterVolume(), "Master volume should be correctly set");
        verify(mockVolumeControl).setValue(inputVolume);
    }

    @ParameterizedTest
    @CsvSource({"-200.0, -80.0", "100.0, 6.0"})
    void testSetMasterVolumeClamped(float inputVolume, float expectedVolume) {
        // WHEN
        audioManager.setMasterVolume(inputVolume);
        
        // THEN
        assertEquals(expectedVolume, audioManager.getMasterVolume(), "Volume should be clamped to the boundary values");
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

    @ParameterizedTest
    @CsvSource({"true, true", "false, false"})
    void testIsPlaying(boolean isRunning, boolean expectedResult) {
        // WHEN
        when(mockClip.isRunning()).thenReturn(isRunning);
        
        // THEN
        assertEquals(expectedResult, audioManager.isPlaying(), "isPlaying should reflect clip's running state");
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