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

public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextArea TextArea;
	private FrontEnd frontEnd;
	private static Process proc;
	private final static String cmd = "C:\\mongodb\\bin\\mongod.exe";

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
		frontEnd = new FrontEnd();

		setTitle("Easy Diagnosis");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 153, 153));
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(
				new ColumnSpec[] {
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC
				},
				new RowSpec[] {
						FormFactory.RELATED_GAP_ROWSPEC,
						RowSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC
				}));
		addWindowListener(new WindowAdapter() { @Override
			public void windowClosing(WindowEvent arg0) {
			if(textField.getText().equals("!cleardb"))
				frontEnd.DBcontroller.clear();
			if(frontEnd.end){
				frontEnd.saveToDB();
				System.out.print("Conversation was saved to easy-diagnosis database on localhost");
			}else System.out.print("The conversation ended early and was not saved");
			proc.destroy();
		}});

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "2, 2, 3, 1, fill, fill");

		TextArea = new JTextArea();
		TextArea.setBackground(new Color(255, 204, 0));
		TextArea.setFont(new Font("Levenim MT", Font.PLAIN, 16));
		TextArea.setWrapStyleWord(true);
		TextArea.setLineWrap(true);
		TextArea.setEditable(false);
		TextArea.setText("Welcome, you are now speaking with a mental health professional, " +
				"for best results please participate fully and answer any questions in detail. " +
				"Feel free to speak openly and discuss any sensitive topics as this conversation is completely confidential.\n");
		scrollPane.setViewportView(TextArea);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setBackground(new Color(255, 204, 0));
		textField.setColumns(10);
		textField.setText("Enter text here");
		textField.addKeyListener(new KeyAdapter() { @Override
			public void keyPressed(KeyEvent arg0) {
			if(arg0.getKeyCode()==10){
				if(!frontEnd.end){
					String user = textField.getText();
					String agent = frontEnd.addToConvo(user);
					TextArea.append("\nYou: " +  user);
					TextArea.append("\nDoc: " + agent);
					textField.setText("");
				}
			}
		}});
		textField.addFocusListener(new FocusAdapter() { @Override
			public void focusGained(FocusEvent e) {
			textField.setText("");
		}});
		contentPane.add(textField, "2, 4, fill, default");

		JButton sendButton = new JButton("Send");
		sendButton.setBackground(new Color(255, 255, 255));
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!frontEnd.end){
					String user = textField.getText();
					String agent = frontEnd.addToConvo(user);
					TextArea.append("\nYou: " +  user);
					TextArea.append("\nDoc: " + agent);
					textField.setText("");
				}
			}
		});
		contentPane.add(sendButton, "4, 4");
	}
}
