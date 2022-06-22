
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Nafees
 */
public class Song {
    public File file;
    public AudioInputStream audioStream;
    public Clip clip;

    public Song() {
        try {
            this.file = new File("C://Users//User//Downloads//Attack_ON_Titan.wav");
            this.audioStream = null;
            try {
                this.audioStream = AudioSystem.getAudioInputStream(file);
            } catch (UnsupportedAudioFileException | IOException ex) {
                Logger.getLogger(TitanFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.clip = AudioSystem.getClip();
            try {
                this.clip.open(audioStream);
            } catch (IOException ex) {
                Logger.getLogger(TitanFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (LineUnavailableException ex) {
            Logger.getLogger(TitanFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void playSong(){
        this.clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stopSong(){
        this.clip.stop();
    }
    
}
