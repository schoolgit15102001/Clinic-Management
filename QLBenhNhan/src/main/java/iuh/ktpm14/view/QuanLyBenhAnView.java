package iuh.ktpm14.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class QuanLyBenhAnView extends JPanel {

	private JPanel contentPane;
	private JTextField tfTen;
	private JTextField tfDiaChi;
	private JTextField tfTuoi;
	private JTextField tfPhone;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLyBenhAnView frame = new QuanLyBenhAnView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public QuanLyBenhAnView() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 550);
		contentPane = new JPanel();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setBorder(new EmptyBorder(5, 5, 5, 5));

		//setContentPane(contentPane);
		//contentPane.setLayout(null);
		//add(contentPane);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 785, 542);
		//contentPane.add(panel);
		add(panel);
		
		JLabel lblTnBnhNhn = new JLabel("T\u00EAn b\u1EC7nh nh\u00E2n: ");
		lblTnBnhNhn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTnBnhNhn.setBounds(125, 80, 118, 23);
		panel.add(lblTnBnhNhn);
		
		tfTen = new JTextField();
		tfTen.setColumns(10);
		tfTen.setBounds(239, 80, 179, 25);
		panel.add(tfTen);
		
		JButton btnThem = new JButton("Th\u00EAm");
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnThem.setBounds(226, 216, 89, 23);
		panel.add(btnThem);
		
		JButton btnXoa = new JButton("Xo\u00E1");
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnXoa.setBounds(341, 216, 89, 23);
		panel.add(btnXoa);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 298, 765, 232);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"M\u00E3 b\u1EC7nh \u00E1n", "T\u00EAn b\u1EC7nh nh\u00E2n", "Tu\u1ED5i", "S\u1ED1 \u0111i\u1EC7n tho\u1EA1i", "Gi\u1EDBi t\u00EDnh", "\u0110\u1ECBa ch\u1EC9"
			}
		));
		table.getColumnModel().getColumn(1).setPreferredWidth(178);
		scrollPane.setViewportView(table);
		
		JLabel lblQunLBnh_1 = new JLabel("Qu\u1EA3n l\u00FD b\u1EC7nh \u00E1n");
		lblQunLBnh_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblQunLBnh_1.setBounds(315, 28, 151, 41);
		panel.add(lblQunLBnh_1);
		
		JButton btnLamMoi = new JButton("L\u00E0m m\u1EDBi");
		btnLamMoi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLamMoi.setBounds(680, 264, 95, 23);
		panel.add(btnLamMoi);
		
		JButton btnSua = new JButton("S\u1EEDa");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSua.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSua.setBounds(455, 216, 89, 23);
		panel.add(btnSua);
		
		JLabel lblaCh = new JLabel("\u0110\u1ECBa ch\u1EC9:");
		lblaCh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblaCh.setBounds(125, 148, 118, 23);
		panel.add(lblaCh);
		
		tfDiaChi = new JTextField();
		tfDiaChi.setColumns(10);
		tfDiaChi.setBounds(239, 148, 276, 57);
		panel.add(tfDiaChi);
		
		JLabel lblTui = new JLabel("Tu\u1ED5i:");
		lblTui.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTui.setBounds(428, 80, 38, 23);
		panel.add(lblTui);
		
		tfTuoi = new JTextField();
		tfTuoi.setColumns(10);
		tfTuoi.setBounds(469, 80, 46, 25);
		panel.add(tfTuoi);
		
		JLabel lblSinThoi = new JLabel("S\u1ED1 \u0111i\u1EC7n tho\u1EA1i:");
		lblSinThoi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSinThoi.setBounds(125, 114, 118, 23);
		panel.add(lblSinThoi);
		
		tfPhone = new JTextField();
		tfPhone.setColumns(10);
		tfPhone.setBounds(239, 114, 276, 25);
		panel.add(tfPhone);
		
		JLabel lblGiiTnh = new JLabel("Gi\u1EDBi t\u00EDnh: ");
		lblGiiTnh.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblGiiTnh.setBounds(545, 99, 80, 20);
		panel.add(lblGiiTnh);
		
		JRadioButton rdNam = new JRadioButton("Nam");
		rdNam.setBounds(631, 82, 47, 23);
		panel.add(rdNam);
		
		JRadioButton rdNu = new JRadioButton("N\u1EEF");
		rdNu.setBounds(631, 114, 47, 23);
		panel.add(rdNu);
	}
}
