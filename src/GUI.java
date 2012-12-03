import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.UnknownHostException;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextArea TextArea;
	private MainController mainController;
	private static Process proc;
	private final static String cmd = "C:\\mongodb\\bin\\mongod.exe";
	private JTabbedPane tabbedPane;
	private JTextArea symptomsArea;
	private JTextArea diagnosisArea;
	private JTextArea statsArea;
	private JScrollPane scrollPane_2;
	private JTextArea pathArea;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					proc = Runtime.getRuntime().exec(cmd);
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					System.out.print(e);
					proc.destroy();
				}
			}
		});
	}

	public GUI() throws UnknownHostException {
		mainController = new MainController();

		setTitle("Easy Diagnosis");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 650);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 153, 153));
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("250dlu:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("35dlu"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("160dlu"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		addWindowListener(new WindowAdapter() { @Override
			public void windowClosing(WindowEvent arg0) {
			if(textField.getText().equals("!cleardb"))
				mainController.DBcontroller.clear();
			if(mainController.end)
				System.out.print("\nConversation was saved to easy-diagnosis database on localhost\n");
			else System.out.print("\nThe conversation ended early and was not saved\n");
			proc.destroy();
		}});

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "2, 2, 3, 1, fill, fill");

		TextArea = new JTextArea();
		TextArea.setBackground(new Color(255, 204, 0));
		TextArea.setFont(new Font("Levenim MT", Font.PLAIN, 14));
		TextArea.setWrapStyleWord(true);
		TextArea.setLineWrap(true);
		TextArea.setEditable(false);
		TextArea.setText("Welcome, you are now speaking with a mental health professional, " +
				"for best results please participate fully and answer any questions in detail. " +
				"Feel free to speak openly and discuss any sensitive topics as this conversation is completely confidential.\n");
		scrollPane.setViewportView(TextArea);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setBackground(Color.WHITE);
		textField.setColumns(10);
		textField.setText("Enter text here");
		textField.addKeyListener(new KeyAdapter() { @Override
			public void keyPressed(KeyEvent arg0) {
			if(arg0.getKeyCode()==10){
				if(!mainController.end){
					String user = textField.getText();
					String agent = mainController.addToConvo(user);
					pathArea.append(mainController.HTcontroller.getPath(agent));
					TextArea.append("\nYou: " +  user);
					TextArea.append("\nDoc: " + agent);
					textField.setText("");
					symptomsArea.setText(mainController.STcontroller.getMaxSymp());
					diagnosisArea.append(mainController.STcontroller.getDiagnosis());
				}
			}
		}});
		textField.addFocusListener(new FocusAdapter() { @Override
			public void focusGained(FocusEvent e) {
			textField.setText("");
		}});

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(UIManager.getColor("Button.shadow"));
		contentPane.add(tabbedPane, "6, 2, 1, 3, fill, fill");
		
				symptomsArea = new JTextArea();
				symptomsArea.setForeground(new Color(0, 153, 153));
				symptomsArea.setFont(new Font("Levenim MT", Font.BOLD, 20));
				symptomsArea.setBackground(new Color(255, 204, 102));
				tabbedPane.addTab("Symptoms", null, symptomsArea, null);
				tabbedPane.setEnabledAt(0, true);
		
				diagnosisArea = new JTextArea();
				diagnosisArea.setForeground(new Color(0, 153, 153));
				diagnosisArea.setFont(new Font("Levenim MT", Font.BOLD, 20));
				diagnosisArea.setBackground(new Color(255, 204, 102));
				tabbedPane.addTab("Diagnosis", null, diagnosisArea, null);
		
		scrollPane_2 = new JScrollPane();
		tabbedPane.addTab("Tree", null, scrollPane_2, null);
		
		pathArea = new JTextArea();
		pathArea.setWrapStyleWord(true);
		pathArea.setEditable(false);
		pathArea.setBackground(new Color(255, 204, 102));
		scrollPane_2.setViewportView(pathArea);
		
				statsArea = new JTextArea();
				statsArea.addComponentListener(new ComponentAdapter() { @Override
					public void componentShown(ComponentEvent arg0) {
					if(arg0.getID() == 102)
						statsArea.setText(mainController.DBcontroller.getStats());
				}
				});
				
				statsArea.setBackground(new Color(255, 204, 102));
				tabbedPane.addTab("Statistics", null, statsArea, null);
		
		
		contentPane.add(textField, "2, 4, fill, default");

		JButton sendButton = new JButton("Send");
		sendButton.setBackground(new Color(255, 255, 255));
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!mainController.end){
					String user = textField.getText();
					String agent = mainController.addToConvo(user);
					pathArea.append(mainController.HTcontroller.getPath(agent));
					TextArea.append("\nYou: " +  user);
					TextArea.append("\nDoc: " + agent);
					textField.setText("");
				}
			}
		});
		contentPane.add(sendButton, "4, 4");
	}
}
