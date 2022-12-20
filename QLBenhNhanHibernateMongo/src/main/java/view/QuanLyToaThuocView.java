package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.print.Doc;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

import constant.CrudStatus;
import entity.ChiTietPhieuKham;
import entity.ChiTietToaThuoc;
import entity.HoSoBenhAn;
import entity.ToaThuoc;
import service.BenhService;
import service.ChiTietPhieuKhamService;
import service.ChiTietToaThuocService;
import service.HoSoBenhAnService;
import service.ThuocService;
import service.ToaThuocService;
import service.impl.BenhServiceImpl;
import service.impl.ChiTietPhieuKhamImpl;
import service.impl.ChiTietToaThuocImpl;
import service.impl.HoSoBenhAnImpl;
import service.impl.ThuocImpl;
import service.impl.ToaThuocImpl;


public class QuanLyToaThuocView extends JPanel implements ActionListener{

	private JPanel contentPane;
	private JTextField tfNgayLap;
	private JTable table;
	private JTable table_1;
	private DefaultTableModel model1;
	private DefaultTableModel model2;
	private DefaultTableModel model3;
	private JButton btnThem;
	private JButton btnXoa;
	
	private ChiTietPhieuKhamService chiTietPhieuKhamService = new ChiTietPhieuKhamImpl();
	private BenhService benhService = new BenhServiceImpl();
	private ToaThuocService toaThuocService = new ToaThuocImpl();
	private HoSoBenhAnService hoSoBenhAnService = new HoSoBenhAnImpl();
	private ChiTietToaThuocService chiTietToaThuocService = new ChiTietToaThuocImpl();
	private ThuocService thuocService = new ThuocImpl();
	
	public static HoSoBenhAn hoSoBenhAnNew = null;

	private JTable table_2;
	private JTextField textField;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					QuanLyToaThuocView frame = new QuanLyToaThuocView();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	private DefaultTableModel dtf;
	private DefaultTableModel dtfThuocAll;
	private DefaultTableModel dtfThuocChon;
	private JButton btnXuatPhieu;

	/**
	 * Create the frame.
	 */
	public QuanLyToaThuocView(HoSoBenhAn hoSoBenhAn) {
		hoSoBenhAnNew = hoSoBenhAn;
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
		
		btnXuatPhieu = new JButton("Xu\u1EA5t phi\u1EBFu");
		btnXuatPhieu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnXuatPhieu.setBounds(549, 80, 197, 23);
		
		panel.add(btnXuatPhieu);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Danh s\u00E1ch thu\u1ED1c", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		scrollPane.setBounds(10, 261, 420, 306);
		panel.add(scrollPane);
		
		
		String[] headerThuocAll = {"Mã Thuốc", "Tên thuốc", "Hướng dẫn"};
		dtfThuocAll = new DefaultTableModel(headerThuocAll,0);
		table = new JTable(dtfThuocAll);


		
		scrollPane.setViewportView(table);
		
		JLabel lblQunLBnh_1 = new JLabel("Qu\u1EA3n l\u00FD toa thu\u1ED1c");
		lblQunLBnh_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblQunLBnh_1.setBounds(315, 28, 163, 41);
		panel.add(lblQunLBnh_1);
		
		JButton btnLamMoi = new JButton("L\u00E0m m\u1EDBi");
		btnLamMoi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLamMoi.setBounds(10, 82, 117, 23);
		panel.add(btnLamMoi);
		
		btnThem = new JButton("Th\u00EAm >>");
		btnThem.setBounds(433, 359, 89, 23);
		panel.add(btnThem);
		
		btnXoa = new JButton("<< Xo\u00E1 ");
		btnXoa.addActionListener(this);
		btnXoa.setBounds(433, 424, 89, 23);
		panel.add(btnXoa);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 116, 778, 133);
		panel.add(scrollPane_1);
		
		
		String[] header= {"Mã hồ sơ","tên bệnh","tên thuốc","ngày lập"};
		
        dtf=new DefaultTableModel(header,0);
		table_1 = new JTable(dtf);

		table_1.getColumnModel().getColumn(0).setPreferredWidth(112);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(114);
		scrollPane_1.setViewportView(table_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Thu\u1ED1c \u0111\u01B0\u1EE3c ch\u1ECDn", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		scrollPane_2.setBounds(532, 261, 256, 306);
		panel.add(scrollPane_2);
		
		String [] headerThuocChon = {"Mã chi tiết","Tên thuốc", "Hướng dẫn"};
		dtfThuocChon = new DefaultTableModel(headerThuocChon,0);
		table_2 = new JTable(dtfThuocChon);
//		table_2.setModel(model3 = new DefaultTableModel(
//				new String[] {
//					
//				},0
//			){
//				boolean[] columnEditables = new boolean[] {false, false};
//
//				public boolean isCellEditable(int row, int column) {
//					return columnEditables[column];
//				}});
		scrollPane_2.setViewportView(table_2);
		
		textField = new JTextField();
		textField.setBounds(447, 393, 51, 20);
		//panel.add(textField);
		//textField.setColumns(10);
		
		docLenTBL(hoSoBenhAn);
		docLenTBLThuoc();
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXuatPhieu.addActionListener(this);
		
	

	}
	
	
	public void docLenTBL(HoSoBenhAn hoSoBenhAn) {
		if(hoSoBenhAn !=null) {
			String maHoSo =  hoSoBenhAn.getId();
			
			if(chiTietPhieuKhamService.findByIdHoSo(hoSoBenhAn.getId()) != null ){
				List<ChiTietPhieuKham> chiTietPhieuKhams = chiTietPhieuKhamService.findByIdHoSo(hoSoBenhAn.getId());
				
				chiTietPhieuKhams.forEach(ct -> {
					String idBenh = ct.getBenh().getId();
					String tenBenh = benhService.findById(idBenh).getTenBenh();
					
					String idChiTietPk = ct.getId();
					
					System.out.println(ct.getId()+" Bên gửi");
					
					
					if(toaThuocService.findByIdPK(idChiTietPk)!=null) {
						Date ngayLap = toaThuocService.findByIdPK(idChiTietPk).getNgayLap();
						
						
						String idToaThuoc = toaThuocService.findByIdPK(idChiTietPk).getId();
						
						chiTietToaThuocService.findByIdToaThuoc(idToaThuoc).forEach(ct1 ->{
							String tenThuoc = thuocService.findById(ct1.getThuoc().getId()).getTenThuoc();
							
							
							Object[] ob = {maHoSo, tenBenh,tenThuoc,new SimpleDateFormat(CrudStatus.DATETIME_FORMAT).format(ngayLap)};
							dtf.addRow(ob);
							
						});
					}
					else {
						
					}

					
				});
			}
		}
	}
	
	public void docLenTBLThuoc() {
		thuocService.findAll().forEach(thuoc -> {
			Object [] ob = {thuoc.getId(), thuoc.getTenThuoc(), thuoc.getHuongDanSuDung()};
			
			dtfThuocAll.addRow(ob);
		});
	}
	
	
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		if(o.equals(btnThem)) {
			int rowDsBenh = table.getSelectedRow();
			String idThuoc = dtfThuocAll.getValueAt(rowDsBenh, 0).toString();
			String tenThuoc = dtfThuocAll.getValueAt(rowDsBenh, 1).toString(); 
			String huongDan = dtfThuocAll.getValueAt(rowDsBenh,2).toString();
			
			Object[] obj = {idThuoc,tenThuoc,huongDan};
			dtfThuocChon.addRow(obj);
		}
		if(o.equals(btnXoa)) {
			int rowBenh = table_2.getSelectedRow();
			dtfThuocChon.removeRow(rowBenh);
		}
		if(o.equals(btnXuatPhieu)) {
			
			chiTietPhieuKhamService.findByIdHoSo(hoSoBenhAnNew.getId()).forEach(ct -> {
				ToaThuoc toaThuoc = new ToaThuoc();
				
				if(toaThuocService.findByIdPK(ct.getId()) == null) {
					toaThuoc.setChiTietPhieuKham(ct);
					toaThuoc.setNgayLap(new Date());	
					toaThuocService.createToaThuoc(toaThuoc);
				}
				
				
				if(chiTietToaThuocService.findByIdToaThuoc(toaThuocService.findByIdPK(ct.getId()).getId())!=null) {
					 int rows = dtfThuocChon.getRowCount(); 
			         for(int i = rows - 1; i >=0; i--){
						ChiTietToaThuoc chiTietToaThuoc = new ChiTietToaThuoc();
						chiTietToaThuoc.setThuoc(thuocService.findById(dtfThuocChon.getValueAt(i, 0).toString()));
						chiTietToaThuoc.setToaThuoc(toaThuocService.findByIdPK(ct.getId()));
						
						chiTietToaThuocService.createChiTietToaThuoc(chiTietToaThuoc);
						
						System.out.print("1213343565787");
						System.out.print(chiTietToaThuocService.findByIdToaThuoc(toaThuocService.findByIdPK(ct.getId()).getId()));
				     }
				}
			});
			
			
			
			
			
		}
	}
}
