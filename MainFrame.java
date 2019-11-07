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
		
		
		//Конструктор класса
		public MainFrame() {
			
			super("Вычисление формулы");
			setSize(WIDTH, HEIGHT);
			Toolkit kit = Toolkit.getDefaultToolkit();
			//Отцентрировать окно приложения на экране
			setLocation((kit.getScreenSize().width - WIDTH)/2, (kit.getScreenSize().height - HEIGHT)/2);
			
			//Контейнер для выбора формулы
			hboxFormulaType.add(Box.createHorizontalGlue());
			addRadioButtonForFormula("Формула 1", 1);
			hboxFormulaType.add(Box.createHorizontalStrut(20));
			addRadioButtonForFormula("Формула 2", 2);
			radioButtonsForFormula.setSelected(radioButtonsForFormula.getElements().nextElement().getModel(), true);
			hboxFormulaType.add(Box.createHorizontalGlue());
			hboxFormulaType.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
			
			//Контейнер для выбора переменной
			hboxMemoryType.add(Box.createHorizontalGlue());
			addRadioButtonForMemory("Переменная 1", 1);
			hboxMemoryType.add(Box.createHorizontalStrut(20));
			addRadioButtonForMemory("Переменная 2", 2);
			hboxMemoryType.add(Box.createHorizontalStrut(20));
			addRadioButtonForMemory("Переменная 3", 3);
			hboxMemoryType.add(Box.createHorizontalGlue());
			radioButtonsForMemory.setSelected(radioButtonsForMemory.getElements().nextElement().getModel(), true);
			hboxMemoryType.add(Box.createVerticalGlue());
			hboxMemoryType.setBorder(BorderFactory.createLineBorder(Color.GREEN));
			
			//Область для вывода значения переменной
			textFieldMemory = new JTextField("0", 15);
			textFieldMemory.setMaximumSize(textFieldMemory.getPreferredSize());
		
			Box hboxMemoryValue = Box.createHorizontalBox();
			hboxMemoryValue.add(Box.createHorizontalGlue());
			hboxMemoryValue.add(Box.createVerticalGlue());
			hboxMemoryValue.add(textFieldMemory);
			hboxMemoryValue.add(Box.createHorizontalGlue());
			hboxMemoryValue.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
			
			//Область с полями ввода для X, Y, Z
			JLabel labelForX = new JLabel("X:");
			textFieldX = new JTextField("0", 10);
			textFieldX.setMaximumSize(textFieldX.getPreferredSize());
			JLabel labelForY = new JLabel("Y:");
			textFieldY = new JTextField("0", 10);
			textFieldY.setMaximumSize(textFieldY.getPreferredSize());
			JLabel labelForZ = new JLabel("Z:");
			textFieldZ = new JTextField("0", 10);
			textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());
			
			Box hboxXYZ = Box.createHorizontalBox();
			hboxXYZ.setBorder(BorderFactory.createLineBorder(Color.RED));
			hboxXYZ.add(Box.createHorizontalGlue());
			hboxXYZ.add(Box.createHorizontalStrut(10));
			hboxXYZ.add(labelForX);
			hboxXYZ.add(Box.createHorizontalStrut(10));
			hboxXYZ.add(textFieldX);
			hboxXYZ.add(Box.createHorizontalStrut(50));
			hboxXYZ.add(labelForY);
			hboxXYZ.add(Box.createHorizontalStrut(10));
			hboxXYZ.add(textFieldY);
			hboxXYZ.add(Box.createHorizontalStrut(50));
			hboxXYZ.add(labelForZ);
			hboxXYZ.add(Box.createHorizontalStrut(10));
			hboxXYZ.add(textFieldZ);
			hboxXYZ.add(Box.createHorizontalStrut(10));
			hboxXYZ.add(Box.createHorizontalGlue());
			
			//Создать область для вывода результата
			JLabel labelForResult = new JLabel("Результат:");
			textFieldResult = new JTextField("0", 15);
			textFieldResult.setMaximumSize(textFieldResult.getPreferredSize());
			Box hboxResult = Box.createHorizontalBox();
			hboxResult.add(Box.createHorizontalGlue());
			hboxResult.add(labelForResult);
			hboxResult.add(Box.createHorizontalStrut(10));
			hboxResult.add(textFieldResult);
			hboxResult.add(Box.createHorizontalGlue());
			hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			
			//Область для кнопок
			
			JButton buttonCalculate = new JButton("Вычислить");
			buttonCalculate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
				try {
					Double x = Double.parseDouble(textFieldX.getText());
					Double y = Double.parseDouble(textFieldY.getText());
					Double z = Double.parseDouble(textFieldZ.getText());
					Double result;
					if (formulaID == 1)
						result = calculate_formula1(x, y, z);
					else
						result = calculate_formula2(x, y, z);
					textFieldResult.setText(result.toString());
				} catch (NumberFormatException exeption) {
					JOptionPane.showMessageDialog(MainFrame.this, "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа", JOptionPane.WARNING_MESSAGE);
				}
			}
			});
			
			JButton buttonMPlus = new JButton("M+");
			buttonMPlus.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					Double result = Double.parseDouble(textFieldResult.getText());
					switch (memID) {
					case 1: mem1 += result;
							textFieldMemory.setText(mem1.toString());
							break;
					case 2:	mem2 += result;
							textFieldMemory.setText(mem2.toString());
							break;
					case 3:	mem3 += result;
							textFieldMemory.setText(mem3.toString());
							break;
					}
				}
			});
			
			JButton buttonMC = new JButton("MC");
			buttonMC.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					switch (memID) {
					case 1: mem1 = 0.0; break;
					case 2:	mem2 = 0.0; break;
					case 3:	mem3 = 0.0; break;
					}
					textFieldMemory.setText("0");
				}
			});
			
			JButton buttonReset = new JButton("Очистить поля");
			buttonReset.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					textFieldX.setText("0");
					textFieldY.setText("0");
					textFieldZ.setText("0");
					textFieldResult.setText("0");
				}
			});
	}
		
	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
