package lab2B;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private static final int WIDTH = 600;
	private static final int HEIGHT = 420;
	
	//Текстовые поля для считывания значений переменных 
	private JTextField textFieldX;
	private JTextField textFieldY;
	private JTextField textFieldZ;
	
	//для вывода значений переменных
	private JTextField textFieldResult;
	private JTextField textFieldMemory;
	
	//Группы радио-кнопок для обеспечения уникальности выделения в группе
	private ButtonGroup radioButtonsForFormula = new ButtonGroup();
	private ButtonGroup radioButtonsForMemory = new ButtonGroup();
	
	//Контейнеры для отображения радио-кнопок
	private Box hboxFormulaType = Box.createHorizontalBox();
	private Box hboxMemoryType = Box.createHorizontalBox();
	
	private int formulaID = 1;
	private int memID = 1;
	private Double mem1 = 0.0, mem2 = 0.0, mem3 = 0.0;
	
	//Вычисление по формуле 1
	public Double calculate_formula1(Double x, Double y, Double z) {
		if (x == 0.0) {
			JOptionPane.showMessageDialog(MainFrame.this, "'x' не должен равняться нулю", "Ошибка ввода", JOptionPane.WARNING_MESSAGE);
			return 0.0;
		}
		return (Math.pow(Math.cos(Math.exp(y)) + Math.exp(Math.pow(y, 2)) + Math.sqrt(1/x), 0.25)) / 
				(Math.pow(Math.cos(Math.PI * Math.pow(z, 3)) + Math.log(Math.pow(1 + z, 2)), Math.sin(y)));
	}
		
	//Вычисление по формуле 2
	public Double calculate_formula2(Double x, Double y, Double z) {
		if (y == 0.0) {
			JOptionPane.showMessageDialog(MainFrame.this, "'y' не должен равняться нулю", "Ошибка ввода", JOptionPane.WARNING_MESSAGE);
			return 0.0;
		}
		return (Math.pow((1 + Math.pow(x, 2)), (1/y))) / (Math.exp(Math.sin(z) + x));
	}
	
	//Вспомогательные методы для добавления кнопок на панель
		private void addRadioButtonForFormula(String buttonName, final int ID) { 
			JRadioButton button = new JRadioButton(buttonName);
			button.addActionListener(new ActionListener() { 
				public void actionPerformed(ActionEvent event) { 
					MainFrame.this.formulaID = ID;
				} 
			}); 
			radioButtonsForFormula.add(button);
			hboxFormulaType.add(button);
		}
		
		private void addRadioButtonForMemory(String buttonName, final int ID) { 
			JRadioButton button = new JRadioButton(buttonName);
			button.addActionListener(new ActionListener() { 
				public void actionPerformed(ActionEvent event) { 
					MainFrame.this.memID = ID;
					switch (memID) {
					case 1: textFieldMemory.setText(mem1.toString()); break;
					case 2: textFieldMemory.setText(mem2.toString()); break;
					case 3: textFieldMemory.setText(mem3.toString()); break;
					}
				} 
			}); 
			radioButtonsForMemory.add(button);
			hboxMemoryType.add(button);
		}
		
		
	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
