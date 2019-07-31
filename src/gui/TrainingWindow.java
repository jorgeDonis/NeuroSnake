package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;

import training.Trainer;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TrainingWindow extends JFrame{
	private JTextField textField;
	private Trainer trainer;

	/**
	 * Create the panel.
	 */
	public TrainingWindow() {
		super();
		trainer = new Trainer();
		getContentPane().setLayout(null);
		setResizable(false);
		setSize(519, 446);
		setLocation(getX() + getWidth() + getInsets().right, getY());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		JLabel lblTraining = new JLabel("Training");
		lblTraining.setFont(new Font("Dialog", Font.BOLD, 22));
		lblTraining.setBounds(197, 12, 207, 53);
		getContentPane().add(lblTraining);
		
		JLabel lblBestFitness = new JLabel("Best Fitness: ");
		lblBestFitness.setFont(new Font("Dialog", Font.BOLD, 15));
		lblBestFitness.setBounds(30, 93, 119, 31);
		getContentPane().add(lblBestFitness);
		
		JLabel label = new JLabel("0");
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
		
		JLabel lblSpecimens = new JLabel("Specimens: ");
		lblSpecimens.setFont(new Font("Dialog", Font.BOLD, 15));
		lblSpecimens.setBounds(30, 204, 119, 31);
		getContentPane().add(lblSpecimens);
		
		JLabel label_2 = new JLabel("0");
		label_2.setForeground(Color.DARK_GRAY);
		label_2.setFont(new Font("Dialog", Font.BOLD, 15));
		label_2.setBounds(161, 204, 119, 31);
		getContentPane().add(label_2);
		
		JButton btnTrain = new JButton("Train");
		btnTrain.setFont(new Font("Dialog", Font.BOLD, 18));
		btnTrain.setBounds(108, 304, 114, 25);
		getContentPane().add(btnTrain);
		
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
}
