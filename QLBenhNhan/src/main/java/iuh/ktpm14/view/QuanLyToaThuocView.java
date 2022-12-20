package iuh.ktpm14.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Date;

import java.time.format.DateTimeFormatter;

import java.util.Vector;

import javax.print.Doc;
import javax.swing.AbstractButton;
import javax.swing.JButton;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import javax.swing.JScrollPane;

import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;


import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

import iuh.ktpm14.connect.ConnectDB;
import iuh.ktpm14.entity.ChiTietToaThuoc;

import iuh.ktpm14.entity.ChiTietToaThuoc;
import iuh.ktpm14.entity.HoSoBenhAn;

import iuh.ktpm14.entity.Thuoc;
import iuh.ktpm14.service.ThuocServiceImpl;
import iuh.ktpm14.service.ToaThuocServiceImpl;

public class QuanLyToaThuocView extends JPanel implements ActionListener{

	private JPanel contentPane;
	private JTextField tfNgayLap;
	private JTable table;
	private JTable table_1;
	private DefaultTableModel model1;
	private DefaultTableModel model2;
	private DefaultTableModel model3;
	private ThuocServiceImpl thuocServiceImpl = new ThuocServiceImpl();
	private ToaThuocServiceImpl toaThuocServiceImpl = new ToaThuocServiceImpl();
	private JTable table_2;

	private DefaultTableModel dtf;
	private JButton btnThem;
	private JButton btnXoa;
	private AbstractButton textField;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLyToaThuocView frame = new QuanLyToaThuocView();
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
	public QuanLyToaThuocView() {
		
		setBounds(0, 0, 800, 580);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 798, 652);
		add(panel);
		
		JLabel lblNgyLpToa = new JLabel("Ng\u00E0y l\u1EADp toa thu\u1ED1c:");
		lblNgyLpToa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNgyLpToa.setBounds(166, 82, 143, 23);
		panel.add(lblNgyLpToa);
		
		tfNgayLap = new JTextField();
		tfNgayLap.setEnabled(false);
		tfNgayLap.setEditable(false);
		tfNgayLap.setColumns(10);
		tfNgayLap.setBounds(319, 80, 179, 25);
		Date date = new Date();
		tfNgayLap.setText(date.toString());
		panel.add(tfNgayLap);
		

		JButton btnXuatPhieu = new JButton("Xu\u1EA5t phi\u1EBFu");
		btnXuatPhieu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnXuatPhieu.setBounds(549, 80, 197, 23);
		panel.add(btnXuatPhieu);

		JButton btnXutPhiuKhm = new JButton("Xu\u1EA5t phi\u1EBFu");
		btnXutPhiuKhm.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnXutPhiuKhm.addActionListener(new ActionListener() {
			
		
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		btnXutPhiuKhm.setBounds(549, 80, 197, 23);
		panel.add(btnXutPhiuKhm);

		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Danh s\u00E1ch thu\u1ED1c", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		scrollPane.setBounds(10, 261, 420, 306);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setModel(model1 = new DefaultTableModel(

			new String[] {
				"M\u00E3 thu\u1ED1c", "T\u00EAn thu\u1ED1c", "H\u01B0\u1EDBng d\u1EABn"
			},0
		){
			boolean[] columnEditables = new boolean[] { false, false, false};

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}});
		model1.setRowCount(0);
			thuocServiceImpl.getAllThuoc(model1);

		scrollPane.setViewportView(table);
		
		JLabel lblQunLBnh_1 = new JLabel("Qu\u1EA3n l\u00FD toa thu\u1ED1c");
		lblQunLBnh_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblQunLBnh_1.setBounds(315, 28, 163, 41);
		panel.add(lblQunLBnh_1);
		
		JButton btnLamMoi = new JButton("L\u00E0m m\u1EDBi");
		btnLamMoi.setFont(new Font("Tahoma", Font.PLAIN, 16));

		btnLamMoi.setBounds(10, 82, 117, 23);

		btnLamMoi.setBounds(10, 82, 95, 23);
		btnLamMoi.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				model3.setRowCount(0);
				toaThuocServiceImpl.getAllChiTietToa(model3);
				model1.setRowCount(0);
				thuocServiceImpl.getAllThuoc(model1);
			}
		});

		panel.add(btnLamMoi);
		
		btnThem = new JButton("Th\u00EAm >>");
		btnThem.addActionListener(this);
		btnThem.setBounds(433, 359, 89, 23);
		panel.add(btnThem);
		
		btnXoa = new JButton("<< Xo\u00E1 ");

		btnXoa.addActionListener(this);

		btnXoa.setBounds(433, 404, 89, 23);
		btnXoa.addActionListener(this);
		panel.add(btnXoa);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 116, 778, 133);
		panel.add(scrollPane_1);
		

		table_1 = new JTable();
		table_1.setModel(model2 = new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"S\u1ED1 phi\u1EBFu kh\u00E1m b\u1EC7nh", "T\u00EAn b\u1EC7nh", "Ng\u00E0y l\u1EADp toa thu\u1ED1c"
			}
		));

		String[] header= {"Mã hồ sơ","Tên bệnh nhân", "ngày lập hồ sơ"};
		
        dtf=new DefaultTableModel(header,0);
		table_1 = new JTable(dtf);

		table_1.getColumnModel().getColumn(0).setPreferredWidth(112);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(114);
		scrollPane_1.setViewportView(table_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Thu\u1ED1c \u0111\u01B0\u1EE3c ch\u1ECDn", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		scrollPane_2.setBounds(532, 261, 256, 306);
		panel.add(scrollPane_2);
		
		table_2 = new JTable();
		table_2.setModel(model3 = new DefaultTableModel(
				new String[] {
					"Mã chi tiết","Tên thuốc", "Số lượng"
				},0
			){
				boolean[] columnEditables = new boolean[] {false, false};

				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}});
		scrollPane_2.setViewportView(table_2);
		toaThuocServiceImpl.getAllChiTietToa(model3);

	}
	public void actionPerformed(ActionEvent e) {
		int count=0;
		// TODO Auto-generated method stub
		Object o = e.getSource();
		ThuocServiceImpl thuocServiceImpl = new ThuocServiceImpl();
		ToaThuocServiceImpl toaThuocServiceImpl = new ToaThuocServiceImpl();
		if(o.equals(btnThem)) {
			count++;
			int i = table.getSelectedRow();
			Thuoc thuoc = thuocServiceImpl.getThuocById(table.getValueAt(i, 0).toString());
			ChiTietToaThuoc chiTietToaThuoc = new ChiTietToaThuoc(thuoc, 1);
			Vector<Object> vector = new Vector<Object>();
			vector.add(chiTietToaThuoc.getId().get());
			vector.add(chiTietToaThuoc.getThuoc().getTen_thuoc());
			System.out.println("1");
//						System.out.println(chiTietToaThuoc.getSo_luong());
//						System.out.println(chiTietToaThuoc.getId().get().toString());
			toaThuocServiceImpl.addChiTiet(chiTietToaThuoc);
			vector.add(chiTietToaThuoc.getSo_luong());
			model3.addRow(vector);
		}
		if(o.equals(btnXoa)) {
			int i = table_2.getSelectedRow();
			System.out.println(table_2.getValueAt(i, 0).toString());
			toaThuocServiceImpl.deleteChiTiet(table_2.getValueAt(i, 0).toString());
			model3.removeRow(i);
		}

		addHoSo();

	}
	
	public void addHoSo() {
		HoSoBenhAn ba = TrangChuView.HSBA;
		if(ba != null) {
			textField.setText(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").format(ba.getNgayLap()));
			Object [] ob = {ba.getId().get(),ba.getHoTen(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").format(ba.getNgayLap())};
			dtf.addRow(ob);
			
		}
	}
	
	
	
}
