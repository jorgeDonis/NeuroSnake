package snake;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;

import game.HumanGame;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class MainWindow {

	private JFrame frame;
	private JTextField textField;
	
	public String brainDirectory = "";
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
		frame.setBounds(100, 100, 798, 410);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNeurasnake = new JLabel("NeuraSnake");
		lblNeurasnake.setFont(new Font("Dialog", Font.BOLD, 30));
		lblNeurasnake.setBounds(300, -14, 204, 79);
		frame.getContentPane().add(lblNeurasnake);

		JButton btnNewButton = new JButton("Play (human)");
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Thread t = new Thread(new HumanGame());
				t.start();
			}
		});
		btnNewButton.setBounds(116, 91, 157, 42);
		frame.getContentPane().add(btnNewButton);

		JButton button = new JButton("Play (brain)");
		button.setFont(new Font("Dialog", Font.BOLD, 14));
		button.setBounds(324, 91, 157, 42);
		frame.getContentPane().add(button);

		JButton button_1 = new JButton("Train");
		button_1.setFont(new Font("Dialog", Font.BOLD, 14));
		button_1.setBounds(517, 91, 157, 42);
		frame.getContentPane().add(button_1);

		JLabel lblNeuralModelsSave = new JLabel("neural models save folder:");
		lblNeuralModelsSave.setFont(new Font("Dialog", Font.BOLD, 13));
		lblNeuralModelsSave.setBounds(12, 163, 227, 56);
		frame.getContentPane().add(lblNeuralModelsSave);

		textField = new JTextField();
		textField.setFont(new Font("Dialog", Font.PLAIN, 13));
		textField.setEditable(false);
		textField.setBounds(224, 181, 417, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JButton btnSelect = new JButton("select");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.showOpenDialog(null);
				brainDirectory = chooser.getSelectedFile().getAbsolutePath();
				textField.setText(brainDirectory);
			}
		});
		btnSelect.setBounds(682, 178, 74, 25);
		frame.getContentPane().add(btnSelect);
	}
}
