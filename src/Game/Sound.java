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
        
        // �õ���ʽ
        format = stream.getFormat();

        // ���ݸ�ʽ������Ƶ����Ϣ
        info = new DataLine.Info(Clip.class, format);

        // ������Ƶ��
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
