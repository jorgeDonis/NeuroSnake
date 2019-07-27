package snake;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JTextArea;

class Console {
  final JFrame frame = new JFrame();
  public JTextArea textArea = new JTextArea(24, 80);
  public Console() {
    textArea.setBackground(Color.WHITE);
    textArea.setForeground(Color.BLACK);
    textArea.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
    System.setOut(new PrintStream(new OutputStream() {
      @Override
      public void write(int b) throws IOException {
        textArea.append(String.valueOf((char) b));
      }
    }));
    frame.add(textArea);
  }
  
  public void clear() {
	  textArea.setText("");
  }
  public void init() {
    frame.pack();
    frame.setVisible(true);
  }
  public JFrame getFrame() {
    return frame;
  }
}