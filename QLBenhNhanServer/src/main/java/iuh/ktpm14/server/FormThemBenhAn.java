package iuh.ktpm14.server;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

import iuh.ktpm14.entity.HoSoBenhAn;
import iuh.ktpm14.service.HoSoBenhAnImpl;
import iuh.ktpm14.service.HoSoBenhAnService;


import javax.swing.JTextArea;
import java.awt.Color;

public class FormThemBenhAn extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField msg_textHoTen;
	private JTextField msg_textTuoi;
	private JTextField msg_textDienThoai;
	private JTextField msg_textDiaChi;
	private JPanel panel_3;
	private JButton btnSend;
	private static JTextArea msg_area;
	
	private HoSoBenhAnService benhAnService = new HoSoBenhAnImpl();

	static ServerSocket ss;
	static Socket s;
	static DataInputStream din;
	static DataOutputStream dout;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormThemBenhAn frame = new FormThemBenhAn();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		String msgin = "";
		try {
			ss = new ServerSocket(1201);
            s = ss.accept();
            
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            
            while(!msgin.equals("exit")){
                msgin = din.readUTF();
                msg_area.setText(msg_area.getText().trim()+"\n"+msgin);
            }
		} catch (Exception e) {
			
		}
	}

	/**
	 * Create the frame.
	 */
	public FormThemBenhAn() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 872, 462);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("H\u1ED3 s\u01A1 b\u1EC7nh \u00E1n");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblTitle.setBounds(338, 33, 220, 32);
		contentPane.add(lblTitle);

		//JPanel panel_3 = new JPanel();
		panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(
				new TitledBorder(null, "Th\u00F4ng b\u00E1o", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(546, 87, 312, 335);
		contentPane.add(panel_3);

		//JTextArea msg_area = new JTextArea();
		msg_area = new JTextArea();
		
		msg_area.setRows(100);
		msg_area.setForeground(new Color(0, 128, 255));
		msg_area.setFont(new Font("Tahoma", Font.BOLD, 15));
		msg_area.setEnabled(false);
		msg_area.setEditable(false);
		msg_area.setDisabledTextColor(new Color(0, 128, 255));
		msg_area.setBounds(10, 23, 292, 301);
		panel_3.add(msg_area);
		

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Th\u00EAm b\u1EC7nh \u00E1n", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		panel.setBounds(0, 87, 540, 336);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblName = new JLabel("H\u1ECD v\u00E0 t\u00EAn: ");
		lblName.setBounds(21, 24, 105, 20);
		panel.add(lblName);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 16));

		msg_textTuoi = new JTextField();
		msg_textTuoi.setBounds(399, 46, 104, 29);
		panel.add(msg_textTuoi);
		msg_textTuoi.setColumns(10);

		msg_textHoTen = new JTextField();
		msg_textHoTen.setBounds(21, 46, 294, 29);
		panel.add(msg_textHoTen);
		msg_textHoTen.setColumns(10);

		JLabel lblAge = new JLabel("Tu\u1ED5i: ");
		lblAge.setBounds(344, 48, 58, 20);
		panel.add(lblAge);
		lblAge.setFont(new Font("Tahoma", Font.BOLD, 16));

		msg_textDiaChi = new JTextField();
		msg_textDiaChi.setBounds(21, 113, 368, 42);
		panel.add(msg_textDiaChi);
		msg_textDiaChi.setColumns(10);

		JLabel lblaCh = new JLabel("\u0110\u1ECBa ch\u1EC9: ");
		lblaCh.setBounds(21, 86, 105, 20);
		panel.add(lblaCh);
		lblaCh.setFont(new Font("Tahoma", Font.BOLD, 16));

		msg_textDienThoai = new JTextField();
		msg_textDienThoai.setBounds(21, 191, 294, 29);
		panel.add(msg_textDienThoai);
		msg_textDienThoai.setColumns(10);

		JLabel lblPhone = new JLabel("S\u1ED1 \u0111i\u00EAn tho\u1EA1i: ");
		lblPhone.setBounds(21, 166, 130, 20);
		panel.add(lblPhone);
		lblPhone.setFont(new Font("Tahoma", Font.BOLD, 16));

		btnSend = new JButton("G\u1EEDi t\u1EDBi b\u00E1c s\u0129");
		btnSend.setBounds(133, 240, 130, 35);
		panel.add(btnSend);
		btnSend.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JButton btnRefresh = new JButton("L\u00E0m m\u1EDBi");
		btnRefresh.setBounds(316, 240, 105, 35);
		panel.add(btnRefresh);
		btnRefresh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		btnSend.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnSend)) {
			String hoten = msg_textHoTen.getText();
			int tuoi = Integer.parseInt(msg_textTuoi.getText());
			String sdt = msg_textDienThoai.getText();
			String diaChi = msg_textDiaChi.getText();
			HoSoBenhAn benhAn = new HoSoBenhAn(hoten, tuoi, diaChi, sdt);
			
			try {
				String msgout_DienThoai = msg_textDienThoai.getText() + " ";
				String msgout_HoTen = msg_textHoTen.getText() + " ";
				dout.writeUTF(  msgout_DienThoai + msgout_HoTen);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
			if(benhAnService.findByPhoneNumber(sdt) == null) {
				if(benhAnService.createHoSoBenhAn(benhAn))
				JOptionPane.showMessageDialog(this,"Thêm thành công");
			}
			else {
				JOptionPane.showMessageDialog(this,"Bệnh nhân đã có trong hệ thống");	
			}
			
			msg_textHoTen.setText("");
			msg_textTuoi.setText("");
			msg_textDienThoai.setText("");
			msg_textDiaChi.setText("");
			
		}
		
	}
}
