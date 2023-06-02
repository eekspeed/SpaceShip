package Game;

import javax.sound.sampled.*;

import java.io.IOException;

public class Sound {
    static AudioInputStream stream;
    static AudioFormat format;
    static DataLine.Info info;
    static Clip clip;

    public Sound() {
    }

    public static void playSound(String fileName) {

        try {
            stream = AudioSystem.getAudioInputStream(Sound.class.getClassLoader().getResourceAsStream(fileName));
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // 得到格式
        format = stream.getFormat();

        // 根据格式设置音频行信息
        info = new DataLine.Info(Clip.class, format);

        // 建立音频行
        try {
            clip = (Clip) AudioSystem.getLine(info);
        } catch (LineUnavailableException ex) {

        }

        try {
            clip.open(stream);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        clip.start();
        if(fileName.indexOf("BGM")!=-1){
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public static void stopSound(){
			clip.close();
    }
}
