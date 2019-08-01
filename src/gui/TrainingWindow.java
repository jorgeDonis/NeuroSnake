package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import snake.Constants;
import training.Trainer;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TrainingWindow extends JFrame {

	private JTextField textField;
	private Trainer trainer;
	private final JButton btnTrain = new JButton("Train");
	private final JLabel label = new JLabel("0"); // best fitness
	private final JLabel label_2 = new JLabel("0"); // number specimens
	private Thread trainerThread;

	Thread updaterThread;

	private void initForms() {
		JLabel lblTraining = new JLabel("Training");
		lblTraining.setFont(new Font("Dialog", Font.BOLD, 22));
		lblTraining.setBounds(197, 12, 207, 53);
		getContentPane().add(lblTraining);

		JLabel lblBestFitness = new JLabel("Best Fitness: ");
		lblBestFitness.setFont(new Font("Dialog", Font.BOLD, 15));
		lblBestFitness.setBounds(30, 93, 119, 31);
		getContentPane().add(lblBestFitness);

		label.setForeground(new Color(255, 99, 71));
		label.setFont(new Font("Dialog", Font.BOLD, 15));
		label.setBounds(161, 93, 119, 31);
		getContentPane().add(label);

		JLabel lblMutationRate = new JLabel("Mutation rate (1 -100)");
		lblMutationRate.setFont(new Font("Dialog", Font.BOLD, 15));
		lblMutationRate.setBounds(30, 148, 207, 31);
		getContentPane().add(lblMutationRate);

		textField = new JTextField();
		textField.setFont(new Font("Dialog", Font.PLAIN, 15));
		textField.setBounds(233, 153, 47, 19);
		getContentPane().add(textField);
		textField.setColumns(10);
		textField.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				warn();
			}

			public void removeUpdate(DocumentEvent e) {
				warn();
			}

			public void insertUpdate(DocumentEvent e) {
				warn();
			}

			public void warn() {
				try {
					if (Integer.parseInt(textField.getText()) <= 0)
						System.out.println("Error parsing mutation rate string");
					else {
						trainer.setMutationRate(Integer.parseInt(textField.getText()));
						System.out.println("Mutation rate is : " + trainer.getMutationRate());
					}
				} catch (Exception e) {
					System.out.println("Error parsing mutation rate string");
				}
			}
		});

		JLabel lblSpecimens = new JLabel("Specimens: ");
		lblSpecimens.setFont(new Font("Dialog", Font.BOLD, 15));
		lblSpecimens.setBounds(30, 204, 119, 31);
		getContentPane().add(lblSpecimens);

		label_2.setForeground(Color.DARK_GRAY);
		label_2.setFont(new Font("Dialog", Font.BOLD, 15));
		label_2.setBounds(161, 204, 119, 31);
		getContentPane().add(label_2);

		btnTrain.setFont(new Font("Dialog", Font.BOLD, 18));
		btnTrain.setBounds(108, 304, 114, 25);

		JButton btnExportBrain = new JButton("Export brain");
		btnExportBrain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnExportBrain.setFont(new Font("Dialog", Font.BOLD, 15));
		btnExportBrain.setBounds(258, 305, 146, 25);
		getContentPane().add(btnExportBrain);
		setVisible(true);

	}

	private void setFrameParametres() {
		getContentPane().setLayout(null);
		setResizable(false);
		setSize(519, 446);
		setLocation(getX() + getWidth() + getInsets().right, getY());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public TrainingWindow() {
		super();
		trainer = new Trainer();
		updaterThread = new Thread(new Runnable() {
			@Override
			public void run() { // update window values from the trainer
				while (true) {
					label_2.setText(Integer.toString(trainer.getNumberSpecimens()));
					label.setText(Integer.toString(trainer.getBestFitness()));
					try {
						Thread.sleep(Constants.TRAINING_REFRESH_TIME);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		updaterThread.start();
		setFrameParametres();
		initForms();

		btnTrain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (trainer.keepTraining) {
						trainer.keepTraining = false;
						btnTrain.setText("Train");
						trainerThread.interrupt();
						trainerThread.join();
					} else {
						trainerThread = new Thread(trainer);
						trainerThread.start();
						trainer.keepTraining = true;
						btnTrain.setText("Stop");
					}
				} catch (Exception e) {
					System.out.println("Error handling trainer thread");
				}
			}
		});
		getContentPane().add(btnTrain);

	}
}
