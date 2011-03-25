package main;

import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.esiag.security.cesar.Cesar;
import fr.esiag.util.Alphabet;
import fr.esiag.util.Frequence;

public class IHM extends JFrame{

	JLabel labelCrypt = new JLabel("key");
	JLabel labelDecrypt = new JLabel("key");
	JLabel labelProbable = new JLabel("probable key");
	
	TextArea textDecrypt = new TextArea(10, 50);
	TextArea textprobable = new TextArea(10, 50);
	TextArea textToCrypt = new TextArea(10, 10);
	
	TextArea textDecryptResult = new TextArea(10, 50);
	TextArea textprobableResult = new TextArea(10, 50);
	TextArea textToCryptResult = new TextArea(10, 10);

	
	TextField cleDecrypt = new TextField(2);
	TextField cle = new TextField(2);
	TextField cleprobable = new TextField(2);

	
	JButton buttonToCrypt = new JButton("crypter");
	JButton buttonDecrypt = new JButton("decrypter");
	JButton buttonProbable = new JButton("cryptanalyse");
	
	JLabel cleLabel = new JLabel("cle pour crypter");
	JLabel cleDecryptLabel = new JLabel("cle pour decrypter");
	JLabel cleProbableLabel = new JLabel("cle probable");
	
	JPanel panelDecrypt = new JPanel();
	JPanel panelCrypt = new JPanel();
	JPanel panelProbable = new JPanel();
	
	JPanel panelInDecrypt = new JPanel();
	JPanel panelIncrypt = new JPanel();
	JPanel panelInProbable = new JPanel();
	
	public IHM(){
		super("Cesar");
		cleprobable.setEditable(false);
		
		textDecryptResult.setEditable(false);
		textprobableResult.setEditable(false);
		textToCryptResult.setEditable(false);
		
		panelCrypt.setLayout(new GridLayout(1,4));
		panelCrypt.add(textToCrypt);
		panelCrypt.add(panelIncrypt);
		panelIncrypt.add(cleLabel);
		panelIncrypt.add(cle);
		panelIncrypt.add(buttonToCrypt);
		panelCrypt.add(textToCryptResult);
		
		panelDecrypt.setLayout(new GridLayout(1,4));
		panelDecrypt.add(textDecrypt);
		panelDecrypt.add(panelInDecrypt);
		panelInDecrypt.add(cleDecryptLabel);
		panelInDecrypt.add(cleDecrypt);
		panelInDecrypt.add(buttonDecrypt);
		panelDecrypt.add(textDecryptResult);
		
		panelProbable.setLayout(new GridLayout(1,4));
		panelProbable.add(textprobable);
		panelProbable.add(panelInProbable);
		panelInProbable.add(buttonProbable);
		panelInProbable.add(cleProbableLabel);
		panelInProbable.add(cleprobable);
		
		panelProbable.add(textprobableResult);
		
		this.setLayout(new GridLayout(3,1));
		this.add(panelCrypt);
		this.add(panelDecrypt);
		this.add(panelProbable);
		
		buttonToCrypt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				textToCryptResult.setText(Cesar.encrypt(textToCrypt.getText(), Integer.parseInt(cle.getText())));
				
			}
		});
		buttonDecrypt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				textDecryptResult.setText(Cesar.decrypt(textDecrypt.getText(), Integer.parseInt(cleDecrypt.getText())));
				
			}
		});
		
		buttonProbable.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Frequence freq = new Frequence(textprobable.getText());
				Character cMax = freq.getMax();
				Integer cle = Alphabet.getPosition(cMax) - 4 % 36;
				
				cleprobable.setText(cle+"");
				textprobableResult.setText(Cesar.decrypt(textprobable.getText(), Integer.parseInt(cleprobable.getText())));
			}
		});
		
	}
	public static void main(String[] args) {
		IHM ihm = new IHM();
		 ihm.pack();
	     ihm.setVisible(true);
	}

}
