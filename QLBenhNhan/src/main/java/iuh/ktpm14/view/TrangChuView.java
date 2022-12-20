package iuh.ktpm14.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import java.awt.Component;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.bson.Document;

import iuh.ktpm14.entity.Benh;
import iuh.ktpm14.entity.HoSoBenhAn;
import iuh.ktpm14.service.HoSoBenhAnService;
import iuh.ktpm14.service.HoSoBenhAnServiceIml;

public class TrangChuView extends JFrame implements ActionListener, MouseListener {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_diachi;
	private JTextField textField_tuoi;
	private JTextField textField_sodt;
	private JTable table;
	private JPanel panel_2;

	
	private JPanel panel_4;
	private DefaultTableModel dtf;

	private static JList<String> jList_msg_area;
	public static HoSoBenhAn HSBA = null;

	private HoSoBenhAnService benhAnService = new HoSoBenhAnServiceIml();
	private JButton btnLamMoi;
	private JButton btnSua;
	private JButton btnXoa;

	static Socket s;
	static DataInputStream din;
	static DataOutputStream dout;

	static DefaultListModel dm = new DefaultListModel();

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrangChuView frame = new TrangChuView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		try {
			s = new Socket("127.0.0.1", 1201);
			din = new DataInputStream(s.getInputStream());
			dout = new DataOutputStream(s.getOutputStream());
			String msgin = "";
			while (!msgin.equals("exit")) {
				msgin = din.readUTF();
				jList_msg_area.setModel(dm);
				dm.addElement("\t" + msgin + "\t");
			}
		} catch (Exception e) {
		}
	}

	/**
	 * Create the frame.
	 */
	public TrangChuView() {
		panel_2 = new JPanel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1082, 686);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 5, 5, 10));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		final JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(128, 128, 128)));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 1055, 73);
		contentPane.add(panel);
		panel.setLayout(null);
		
		panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_2.add(panel_4);

		final JButton btnQuanLyBenhAn = new JButton("QL b\u1EC7nh \u00E1n");
		btnQuanLyBenhAn.setForeground(new Color(255, 255, 255));
		btnQuanLyBenhAn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				if(obj.equals(btnQuanLyBenhAn)) {
					panel_2.removeAll();
					panel_2.add(panel_4);
					panel_2.repaint();
					panel_2.revalidate();
				}
			}
		});
		btnQuanLyBenhAn.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnQuanLyBenhAn.setBackground(new Color(0, 128, 192));
		btnQuanLyBenhAn.setBounds(10, 11, 133, 51);
		btnQuanLyBenhAn.setHorizontalTextPosition(SwingConstants.CENTER);
		panel.add(btnQuanLyBenhAn);

		final JButton btnQuanLyPhieuKham = new JButton("Phi\u1EBFu kh\u00E1m b\u1EC7nh");
		btnQuanLyPhieuKham.setForeground(new Color(255, 255, 255));
		btnQuanLyPhieuKham.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				if(obj.equals(btnQuanLyPhieuKham)) {
					System.out.println("run");
					panel_2.removeAll();
					panel_2.add(new QuanLyPhieuKhamView());
					panel_2.repaint();
					panel_2.revalidate();
				}
			}
		});
		btnQuanLyPhieuKham.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnQuanLyPhieuKham.setBackground(new Color(0, 128, 192));
		btnQuanLyPhieuKham.setBounds(207, 11, 153, 51);
		btnQuanLyPhieuKham.setHorizontalTextPosition(SwingConstants.CENTER);
		panel.add(btnQuanLyPhieuKham);

		final JButton btnQuanLyBenh = new JButton("Qu\u1EA3n l\u00FD b\u1EC7nh");
		btnQuanLyBenh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				if(obj.equals(btnQuanLyBenh)) {
					System.out.println("run");
					panel_2.removeAll();
					panel_2.add(new QuanLyBenhView());
					panel_2.repaint();
					panel_2.revalidate();
				}
			}
		});
		btnQuanLyBenh.setForeground(new Color(255, 255, 255));
		btnQuanLyBenh.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnQuanLyBenh.setBackground(new Color(0, 128, 192));
		btnQuanLyBenh.setBounds(435, 11, 150, 51);
		btnQuanLyBenh.setHorizontalTextPosition(SwingConstants.CENTER);
		panel.add(btnQuanLyBenh);

		final JButton btnQuanLyThuoc = new JButton("Qu\u1EA3n l\u00FD thu\u1ED1c");
		btnQuanLyThuoc.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				if(obj.equals(btnQuanLyThuoc)) {
					panel_2.removeAll();
					panel_2.add(new QuanLyThuocView());
					panel_2.repaint();
					panel_2.revalidate();
				}
			}
			
		});
		btnQuanLyThuoc.setForeground(new Color(255, 255, 255));
		btnQuanLyThuoc.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnQuanLyThuoc.setBackground(new Color(0, 128, 192));
		btnQuanLyThuoc.setBounds(653, 11, 153, 51);
		btnQuanLyThuoc.setHorizontalTextPosition(SwingConstants.CENTER);
		panel.add(btnQuanLyThuoc);

		final JButton btnQuanLyToaThuoc = new JButton("QL toa thu\u1ED1c");
		btnQuanLyToaThuoc.setForeground(new Color(255, 255, 255));
		btnQuanLyToaThuoc.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnQuanLyToaThuoc.setBackground(new Color(0, 128, 192));
		btnQuanLyToaThuoc.setBounds(871, 11, 146, 51);
		btnQuanLyToaThuoc.setHorizontalTextPosition(SwingConstants.CENTER);
		panel.add(btnQuanLyToaThuoc);
		
		btnQuanLyToaThuoc.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				if(obj.equals(btnQuanLyToaThuoc)) {
					panel_2.removeAll();
					panel_2.add(new QuanLyToaThuocView());
					panel_2.repaint();
					panel_2.revalidate();
				}
			}});

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Danh s\u00E1ch b\u1EC7nh nh\u00E2n", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_1.setBounds(0, 73, 231, 566);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		jList_msg_area = new JList();
		jList_msg_area.setForeground(new Color(0, 128, 255));
		jList_msg_area.setBackground(new Color(255, 255, 255));
		jList_msg_area.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jList_msg_area.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jList_msg_area.setBounds(10, 22, 211, 533);

		/*
		 * jList_msg_area.setModel(new AbstractListModel() { String[] values = new
		 * String[] {"Patient1_123456789", "Patient2_123456789", "Patient3_123456789",
		 * "Patient4_123456789", "Patient5_123456789", "Patient6_123456789"}; public int
		 * getSize() { return values.length; } public Object getElementAt(int index) {
		 * return values[index]; } });
		 */

		panel_1.add(jList_msg_area);

		
		panel_2.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Qu\u1EA3n l\u00FD", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(241, 73, 814, 566);
		contentPane.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));

		/*
		 * JPanel panel_4 = new JPanel(); panel_4.setLayout(null); panel_2.add(panel_4);
		 */

		JLabel lblTnBnhNhn = new JLabel("T\u00EAn b\u1EC7nh nh\u00E2n: ");
		lblTnBnhNhn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTnBnhNhn.setBounds(125, 80, 118, 23);
		panel_4.add(lblTnBnhNhn);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(239, 80, 179, 25);
		panel_4.add(textField);

		
		JButton btnThem = new JButton("Gọi Khám");
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnThem.setBounds(206, 216, 103, 23);
		panel_4.add(btnThem);
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String list = jList_msg_area.getSelectedValue().toString().trim();
				String[] splits = list.split(" ");
				HSBA = benhAnService.findBySDT(splits[0]);
				try {
					String msgout = "";
					msgout = "\nMời bệnh nhân\n" + jList_msg_area.getSelectedValue().toString().trim()
							+ "\nlên phòng khám\n";
					dout.writeUTF(msgout);
					dm.remove(jList_msg_area.getSelectedIndex());
					jList_msg_area.setModel(dm);
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});

		btnXoa = new JButton("Xo\u00E1");
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnXoa.setBounds(341, 216, 89, 23);
		panel_4.add(btnXoa);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 298, 765, 232);
		panel_4.add(scrollPane);

		
		String[] header= {"Tên bệnh nhân","tuổi", "số điện thoại", "địa chỉ","ngày lập hồ sơ"};
		
        dtf=new DefaultTableModel(header,0);
		table = new JTable(dtf);
		
//		table.setModel(new DefaultTableModel(new Object[][] {},
//				new String[] { "New column", "New column", "New column", "New column", "New column", "New column" }));
		scrollPane.setViewportView(table);

		JLabel lblQunLBnh_1 = new JLabel("Qu\u1EA3n l\u00FD b\u1EC7nh \u00E1n");
		lblQunLBnh_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblQunLBnh_1.setBounds(315, 28, 151, 41);
		panel_4.add(lblQunLBnh_1);

		btnLamMoi = new JButton("L\u00E0m m\u1EDBi");
		btnLamMoi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLamMoi.setBounds(680, 264, 95, 23);
		panel_4.add(btnLamMoi);

		btnSua = new JButton("S\u1EEDa");
		btnSua.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSua.setBounds(455, 216, 89, 23);
		panel_4.add(btnSua);

		JLabel lblaCh = new JLabel("\u0110\u1ECBa ch\u1EC9:");
		lblaCh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblaCh.setBounds(125, 148, 118, 23);
		panel_4.add(lblaCh);

		textField_diachi = new JTextField();
		textField_diachi.setColumns(10);
		textField_diachi.setBounds(239, 148, 276, 57);
		panel_4.add(textField_diachi);

		JLabel lblTui = new JLabel("Tu\u1ED5i:");
		lblTui.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTui.setBounds(428, 80, 38, 23);
		panel_4.add(lblTui);

		textField_tuoi = new JTextField();
		textField_tuoi.setColumns(10);
		textField_tuoi.setBounds(469, 80, 46, 25);
		panel_4.add(textField_tuoi);

		JLabel lblSinThoi = new JLabel("S\u1ED1 \u0111i\u1EC7n tho\u1EA1i:");
		lblSinThoi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSinThoi.setBounds(125, 114, 118, 23);
		panel_4.add(lblSinThoi);

		/*
		 * <<<<<<< HEAD textField_3 = new JTextField(); textField_3.setColumns(10);
		 * textField_3.setBounds(239, 114, 276, 25); panel_4.add(textField_3); =======
		 */
		textField_sodt = new JTextField();
		textField_sodt.setColumns(10);
		textField_sodt.setBounds(239, 114, 276, 25);
		panel_4.add(textField_sodt);
		
		docLenTBL();
		
		table.addMouseListener(this);
		jList_msg_area.addMouseListener(this);
//		jList_msg_area.addMouseListener(new java.awt.event.MouseAdapter() {		
//		});
		
		btnLamMoi.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);

	}

	public void docLenTBL() {
		for (HoSoBenhAn benh : benhAnService.findAll()) {
			Object[] ob = { benh.getHoTen(), benh.getTuoi(), benh.getDienThoai(), benh.getDiaChi(),
					DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").format(benh.getNgayLap()) };
			dtf.addRow(ob);
		}
	}

	public void deleteTBL() {
		DefaultTableModel df = (DefaultTableModel) table.getModel();
		df.getDataVector().removeAllElements();
	}

	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(table)) {
			int row = table.getSelectedRow();
			textField.setText(dtf.getValueAt(row, 0).toString());
			textField_tuoi.setText(dtf.getValueAt(row, 1).toString());
			textField_diachi.setText(dtf.getValueAt(row, 3).toString());
			textField_sodt.setText(dtf.getValueAt(row, 2).toString());

		} else if (o.equals(jList_msg_area)) {
			String list = jList_msg_area.getSelectedValue().toString().trim();
			String[] splits = list.split(" ");
			HoSoBenhAn ba = benhAnService.findBySDT(splits[0]);

			textField.setText(ba.getHoTen());

			textField_tuoi.setText(String.valueOf(ba.getTuoi()));

			textField_diachi.setText(ba.getDiaChi());

			textField_sodt.setText(ba.getDienThoai());
			deleteTBL();

			Object[] ob = { ba.getHoTen(), ba.getTuoi(), ba.getDienThoai(), ba.getDiaChi(),
					DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").format(ba.getNgayLap()) };
			dtf.addRow(ob);

		}
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if(ob.equals(btnLamMoi)) {
			textField.setText("");
			
			textField_tuoi.setText("");
			
			textField_diachi.setText("");
			
			textField_sodt.setText("");
			deleteTBL();
			docLenTBL();
		}
		else if(ob.equals(btnXoa)) {
			int row= table.getSelectedRow();
			String std = dtf.getValueAt(row, 2).toString();
			if(benhAnService.deleteBySdt(std)) {
				JOptionPane.showMessageDialog(this,"xóa thành công");
			}
			else {
				JOptionPane.showMessageDialog(this,"xóa thất bại");
			}
			
			deleteTBL();
			docLenTBL();
		}
		
		else if(ob.equals(btnSua)) {
			int row= table.getSelectedRow();
			String std = dtf.getValueAt(row, 2).toString();
			
			String hoten = textField.getText();
			int tuoi = Integer.parseInt(textField_tuoi.getText());
			String sdt = textField_sodt.getText();
			String diaChi = textField_diachi.getText();
			
			HoSoBenhAn benhAn = new HoSoBenhAn(hoten, tuoi, diaChi, sdt);
			if(benhAnService.updateBySdt(sdt, benhAn)) {
				JOptionPane.showMessageDialog(this,"cập nhật thành công");
				deleteTBL();
				docLenTBL();
			}
			else {
				JOptionPane.showMessageDialog(this,"cập nhật thất bại");
			}
		}

	}
}
