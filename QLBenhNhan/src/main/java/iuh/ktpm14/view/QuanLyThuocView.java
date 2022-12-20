package iuh.ktpm14.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import iuh.ktpm14.entity.Thuoc;
import iuh.ktpm14.service.ThuocServiceImpl;

public class QuanLyThuocView extends JPanel implements ActionListener{

	private JPanel contentPane;
	private JTextField tfTenThuoc;
	private JTextField tfHuongDan;
	private JTable table;
	private DefaultTableModel model;
	private JButton btnThem;
	private JButton btnSua;
	private JButton btnXoa;
	private JButton btnLamMoi;
	private ThuocServiceImpl thuocServiceImpl = new ThuocServiceImpl();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLyThuocView frame = new QuanLyThuocView();
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
	public QuanLyThuocView() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 800, 580);

		setBorder(new EmptyBorder(5, 5, 5, 5));

		//setContentPane(contentPane);
		
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 795, 580);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblTenThuoc = new JLabel("T\u00EAn thu\u1ED1c: ");
		lblTenThuoc.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTenThuoc.setBounds(203, 83, 86, 23);
		panel.add(lblTenThuoc);
		
		tfTenThuoc = new JTextField();
		tfTenThuoc.setBounds(354, 86, 195, 20);
		panel.add(tfTenThuoc);
		tfTenThuoc.setColumns(10);
		
		btnThem = new JButton("Th\u00EAm");
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnThem.setBounds(203, 182, 89, 23);
		btnThem.addActionListener(this);
		panel.add(btnThem);
		
		btnXoa = new JButton("Xo\u00E1");
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnXoa.addActionListener(this);
		btnXoa.setBounds(336, 182, 89, 23);
		panel.add(btnXoa);
		
		JLabel lblHngDnS = new JLabel("H\u01B0\u1EDBng d\u1EABn s\u1EED d\u1EE5ng: ");
		lblHngDnS.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblHngDnS.setBounds(203, 117, 153, 23);
		panel.add(lblHngDnS);
		
		tfHuongDan = new JTextField();
		tfHuongDan.setColumns(10);
		tfHuongDan.setBounds(354, 120, 195, 51);
		panel.add(tfHuongDan);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 232, 775, 332);
		panel.add(scrollPane);
		
		
		table = new JTable();
		table.setModel(model = new DefaultTableModel(
			new String[] {
				"M\u00E3 thu\u1ED1c", "T\u00EAn thu\u1ED1c", "H\u01B0\u1EDBng d\u1EABn"
			},0
		) {
			boolean[] columnEditables = new boolean[] { false, false, false};

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		model.setRowCount(0);
		thuocServiceImpl.getAllThuoc(model);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				tfTenThuoc.setText(table.getValueAt(i, 1).toString());
				tfHuongDan.setText(table.getValueAt(i, 2).toString());
				super.mouseClicked(e);
			}
		});
		
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("Qu\u1EA3n l\u00FD thu\u1ED1c");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(336, 31, 129, 41);
		panel.add(lblNewLabel);
		
		btnLamMoi = new JButton("L\u00E0m m\u1EDBi");
		btnLamMoi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLamMoi.setBounds(690, 205, 95, 23);
		panel.add(btnLamMoi);
		
		btnSua = new JButton("S\u1EEDa");
		btnSua.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSua.setBounds(460, 182, 89, 23);
		panel.add(btnSua);	
		
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		ThuocServiceImpl thuocServiceImpl = new ThuocServiceImpl();
		if(o.equals(btnThem)) {
			String tenThuoc = tfTenThuoc.getText().trim();
			String huongDan = tfHuongDan.getText().trim();
			Thuoc thuoc = new Thuoc(tenThuoc, huongDan);
			thuocServiceImpl.addThuoc(thuoc);
			JOptionPane.showMessageDialog(this, "Thêm thành công");
			model.setRowCount(0);
			thuocServiceImpl.getAllThuoc(model);
		}
		if(o.equals(btnXoa)) {
			thuocServiceImpl.deleteThuoc(tfTenThuoc.getText().trim());
			JOptionPane.showMessageDialog(this, "Xoá thành công");
			model.setRowCount(0);
			thuocServiceImpl.getAllThuoc(model);
		}
		if(o.equals(btnSua)) {
			int i = table.getSelectedRow();
			String tenThuoc = tfTenThuoc.getText().trim();
			String huongDan = tfHuongDan.getText().trim();
			Thuoc newThuoc = new Thuoc(tenThuoc, huongDan);
			thuocServiceImpl.update(table.getValueAt(i, 0).toString(), newThuoc);
		}
		if(o.equals(btnLamMoi)) {
			model.setRowCount(0);
			thuocServiceImpl.getAllThuoc(model);
		}
	}

}
