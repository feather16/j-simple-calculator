import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleCalculator extends JFrame implements ActionListener{
	
	StringBuilder Formula = new StringBuilder();
	JLabel FormulaPrinter = new JLabel("0");
	JButton button0 = new JButton("0");
	JButton button1 = new JButton("1");
	JButton button2 = new JButton("2");
	JButton button3 = new JButton("3");
	JButton button4 = new JButton("4");
	JButton button5 = new JButton("5");
	JButton button6 = new JButton("6");
	JButton button7 = new JButton("7");
	JButton button8 = new JButton("8");
	JButton button9 = new JButton("9");
	JButton buttonLeft = new JButton("(");
	JButton buttonRight = new JButton(")");
	JButton buttonDEL = new JButton("DEL");
	JButton buttonAC =  new JButton("AC");
	JButton buttonEqual = new JButton("=");
	JButton buttonPlus = new JButton("+");
	JButton buttonMinus = new JButton("-");
	JButton buttonTimes = new JButton("\u00D7");
	JButton buttonDivide = new JButton("\u00F7");
	
	public static void main(String args[]){
		System.out.println("Log:");
		SimpleCalculator frame= new SimpleCalculator("Calc 1.0");
		frame.setVisible(true);
		frame.setSize(312,320);
		frame.setLocationRelativeTo(null);
	}
	
	SimpleCalculator(String title){
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel p = new JPanel();
		p.setLayout(null);
		
		FormulaPrinter.setFont(new Font("Arial",Font.BOLD,30));
		FormulaPrinter.setHorizontalTextPosition(JLabel.LEFT);
		FormulaPrinter.setBounds(10,21,300,50);
		p.add(FormulaPrinter);
		
		ButtonSet(button0,p,0,233,50);
		ButtonSet(button1,p,0,183,50);
		ButtonSet(button2,p,50,183,50);
		ButtonSet(button3,p,100,183,50);
		ButtonSet(button4,p,0,133,50);
		ButtonSet(button5,p,50,133,50);
		ButtonSet(button6,p,100,133,50);
		ButtonSet(button7,p,0,83,50);
		ButtonSet(button8,p,50,83,50);
		ButtonSet(button9,p,100,83,50);
		ButtonSet(buttonLeft,p,50,233,50);
		ButtonSet(buttonRight,p,100,233,50);
		ButtonSet(buttonDEL,p,150,83,74);
		ButtonSet(buttonAC,p,224,83,74);
		ButtonSet(buttonEqual,p,150,233,148);
		ButtonSet(buttonPlus,p,150,183,74);
		ButtonSet(buttonMinus,p,224,183,74);
		ButtonSet(buttonTimes,p,150,133,74);
		ButtonSet(buttonDivide,p,224,133,74);
		
		getContentPane().add(p, BorderLayout.CENTER);
	}
	
	public void ButtonSet(JButton b,JPanel p,int x,int y,int w){
		b.setFont(new Font("Arial",Font.BOLD,20));
		b.setBounds(x,y,w,50);
		p.add(b);
		b.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e){
		String F = new String(Formula);
		if(F.equals("Error")){
			Formula.delete(0,Formula.length());
		}
		
		if(e.getSource()==button0) Formula.append("0");
		else if(e.getSource()==button1) Formula.append("1");
		else if(e.getSource()==button2) Formula.append("2");
		else if(e.getSource()==button3) Formula.append("3");
		else if(e.getSource()==button4) Formula.append("4");
		else if(e.getSource()==button5) Formula.append("5");
		else if(e.getSource()==button6) Formula.append("6");
		else if(e.getSource()==button7) Formula.append("7");
		else if(e.getSource()==button8) Formula.append("8");
		else if(e.getSource()==button9) Formula.append("9");
		else if(e.getSource()==buttonLeft) Formula.append("(");
		else if(e.getSource()==buttonRight) Formula.append(")");
		else if(e.getSource()==buttonDEL){
			if(Formula.length()>0) Formula.deleteCharAt(Formula.length()-1);
		}
		else if(e.getSource()==buttonAC) Formula.delete(0,Formula.length());
		else if(e.getSource()==buttonEqual){
			Calculation c = new Calculation(Formula.toString());
			Formula.delete(0,Formula.length());
			Formula.append(c.output());
		}
		else if(e.getSource()==buttonPlus) Formula.append("+");
		else if(e.getSource()==buttonMinus) Formula.append("-");
		else if(e.getSource()==buttonTimes) Formula.append("\u00D7");
		else if(e.getSource()==buttonDivide) Formula.append("\u00F7");
		
		FormulaPrinter.setText(Formula.toString());
		if(Formula.length()==0) FormulaPrinter.setText("0");
	}
}