package snake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import javax.swing.JFrame;
import javax.swing.JRootPane;

public class KeyReader implements Runnable {
	
	public KeyEvent readKey;
	
	KeyReader(){}

	@Override
	public void run() {
		getCh();
	}
	
	public void getCh() {  
        final JFrame frame = new JFrame();  
        synchronized (frame) {  
            frame.setUndecorated(true);  
            frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);  
            frame.addKeyListener(new KeyListener() {
                @Override 
                public void keyPressed(KeyEvent e) {  
                    synchronized (frame) {  
                        frame.setVisible(false);  
                        frame.dispose();  
                        frame.notify();
                        readKey = e;
                    }  
                }  
                @Override 
                public void keyReleased(KeyEvent e) {  
                }  
                @Override 
                public void keyTyped(KeyEvent e) {  
                }  
            });  
            frame.setVisible(true);  
            try {  
                frame.wait();  
            } catch (InterruptedException e1) {  
            }  
        }
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }

}
