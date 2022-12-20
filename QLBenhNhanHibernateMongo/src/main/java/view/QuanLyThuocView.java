package view;

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

import entity.Thuoc;
import service.ThuocService;
import service.impl.ThuocImpl;

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
	private ThuocService thuocService = new ThuocImpl();
	private DefaultTableModel dtf;

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
		panel.add(btnThem);
		
		btnXoa = new JButton("Xo\u00E1");
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 16));
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
		
		
		String[] header= {"Mã Thuốc", "Tên Thuốc", "Hướng dẫn sử dụng"};
		
        dtf=new DefaultTableModel(header,0);
		table = new JTable(dtf);
		
		scrollPane.setViewportView(table);
		
		docLenTBL();
		
		
		

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
		
		btnLamMoi.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnSua.addActionListener(this);
		
	}
	
	public void docLenTBL() {
		thuocService.findAll().forEach(thuoc -> {
			Object [] ob = {thuoc.getId(), thuoc.getTenThuoc(), thuoc.getHuongDanSuDung()};
			
			dtf.addRow(ob);
		});
	}
	
	public void deleteTBL() {
		DefaultTableModel df = (DefaultTableModel) table.getModel();
		df.getDataVector().removeAllElements();
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(btnThem)) {
			String tenThuoc = tfTenThuoc.getText().trim();
			String huongDan = tfHuongDan.getText().trim();
			Thuoc thuoc = new Thuoc();
			thuoc.setTenThuoc(tenThuoc);
			thuoc.setHuongDanSuDung(huongDan);
			
			if(thuocService.createThuoc(thuoc)) {
				JOptionPane.showMessageDialog(this, "Thêm thành công");
				deleteTBL();
				docLenTBL();
				
			}
			else {
				JOptionPane.showMessageDialog(this, "Thêm thất bại");
			}
			
		}
		if(o.equals(btnXoa)) {
			int row = table.getSelectedRow();
			String id = dtf.getValueAt(row, 0).toString();
			if(thuocService.deleteById(id) ==  "success") {
				JOptionPane.showMessageDialog(this, "xóa thành công");
				deleteTBL();
				docLenTBL();
				
			}
			else {
				JOptionPane.showMessageDialog(this, "xóa thất bại");
			}
		}
		if(o.equals(btnSua)) {
			int row = table.getSelectedRow();
			String idThuoc = dtf.getValueAt(row, 0).toString();
			String tenThuoc = tfTenThuoc.getText().trim();
			String huongDan = tfHuongDan.getText().trim();
			Thuoc thuoc = new Thuoc();
			thuoc.setId(idThuoc);
			thuoc.setTenThuoc(tenThuoc);
			thuoc.setHuongDanSuDung(huongDan);
			
			if(thuocService.updateThuoc(thuoc)) {
				JOptionPane.showMessageDialog(this, "Sửa thuốc thành công");
				deleteTBL();
				docLenTBL();
				
			}
			else {
				JOptionPane.showMessageDialog(this, "Sửa thuốc thất bại");
			}
		}
		if(o.equals(btnLamMoi)) {
			tfTenThuoc.setText("");
			tfHuongDan.setText("");
			
			deleteTBL();
			docLenTBL();
		}
	}

}
