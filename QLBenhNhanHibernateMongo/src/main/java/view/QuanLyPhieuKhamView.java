package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import constant.CrudStatus;
import entity.Benh;
import entity.ChiTietPhieuKham;
import entity.HoSoBenhAn;
import entity.ToaThuoc;
import service.BenhService;
import service.ChiTietPhieuKhamService;
import service.HoSoBenhAnService;
import service.ToaThuocService;
import service.impl.BenhServiceImpl;
import service.impl.ChiTietPhieuKhamImpl;
import service.impl.HoSoBenhAnImpl;
import service.impl.ToaThuocImpl;

import javax.swing.border.TitledBorder;
import java.awt.Component;
import javax.swing.border.EtchedBorder;

public class QuanLyPhieuKhamView extends JPanel implements ActionListener{
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable tbDanhSachBenh;
	private JTable tbMacBenh;
	private JTable tbLichSuBenhAn;
	private JButton btnXutPhiuKhm;
	private JButton btnLamMoi;
	private JButton btnSua;
	private DefaultTableModel dtf;
	
	private ChiTietPhieuKhamService chiTietPhieuKhamService = new ChiTietPhieuKhamImpl();
	private BenhService benhService = new BenhServiceImpl();
	private ToaThuocService toaThuocService = new ToaThuocImpl();
	private HoSoBenhAnService hoSoBenhAnService = new HoSoBenhAnImpl();
	private DefaultTableModel dtfDanhSachBenh;
	private DefaultTableModel dtfBiBenh;
	private JButton btnThem;
	private JButton btnXoa;
	
	public static List<ChiTietPhieuKham> chiTietPhieuKhamId;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					QuanLyPhieuKhamView frame = new QuanLyPhieuKhamView();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public QuanLyPhieuKhamView(HoSoBenhAn hoSoBenhAn) {
		setAlignmentX(Component.RIGHT_ALIGNMENT);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 782, 550);
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setBorder(new EmptyBorder(5,5,5,5));
		//contentPane.setLayout(null);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 778, 550);
		//contentPane.add(panel);
		add(panel);
		
		JLabel lblTnBnhNhn = new JLabel("T\u00EAn b\u1EC7nh nh\u00E2n: ");
		lblTnBnhNhn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTnBnhNhn.setBounds(125, 80, 118, 23);
		panel.add(lblTnBnhNhn);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setEnabled(false);
		textField.setColumns(10);
		textField.setBounds(239, 80, 179, 25);
		panel.add(textField);
		
		btnXutPhiuKhm = new JButton("Xu\u1EA5t toa thu\u1ED1c");
		btnXutPhiuKhm.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnXutPhiuKhm.setBounds(549, 80, 197, 23);
		panel.add(btnXutPhiuKhm);
		
		JLabel lblQunLBnh_1 = new JLabel("Qu\u1EA3n l\u00FD phi\u1EBFu kh\u00E1m b\u1EC7nh");
		lblQunLBnh_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblQunLBnh_1.setBounds(294, 28, 245, 41);
		panel.add(lblQunLBnh_1);
		
		btnLamMoi = new JButton("L\u00E0m m\u1EDBi");
		btnLamMoi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLamMoi.setBounds(673, 212, 105, 23);
		panel.add(btnLamMoi);
		
		btnSua = new JButton("S\u1EEDa");
		btnSua.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSua.setBounds(549, 114, 197, 23);
//		panel.add(btnSua);
		
		JLabel lblTriuChng = new JLabel("Tri\u1EC7u ch\u1EE9ng:");
		lblTriuChng.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTriuChng.setBounds(125, 148, 118, 23);
		panel.add(lblTriuChng);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(239, 148, 276, 57);
		panel.add(textField_1);
		
		JLabel lblTui = new JLabel("Tu\u1ED5i:");
		lblTui.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTui.setBounds(428, 80, 38, 23);
		panel.add(lblTui);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setEnabled(false);
		textField_2.setColumns(10);
		textField_2.setBounds(469, 80, 46, 25);
		panel.add(textField_2);
		
		JLabel lblSinThoi = new JLabel("S\u1ED1 \u0111i\u1EC7n tho\u1EA1i:");
		lblSinThoi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSinThoi.setBounds(125, 114, 118, 23);
		panel.add(lblSinThoi);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setEnabled(false);
		textField_3.setColumns(10);
		textField_3.setBounds(239, 114, 276, 25);
		panel.add(textField_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Danh s\u00E1ch b\u1EC7nh", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		scrollPane.setBounds(10, 366, 365, 173);
		panel.add(scrollPane);
		
		String[] header = {"Mã Bệnh","Tên Bệnh"};
		dtfDanhSachBenh = new DefaultTableModel(header,0);
		tbDanhSachBenh = new JTable(dtfDanhSachBenh);
		scrollPane.setViewportView(tbDanhSachBenh);
		docDSBenh();
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "M\u1EAFc b\u1EC7nh", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		scrollPane_2.setBounds(515, 366, 263, 173);
		panel.add(scrollPane_2);
		
		String[] headerBenhMac = {"Mã Bệnh","Tên Bệnh"};
		dtfBiBenh = new DefaultTableModel(headerBenhMac,0);
		tbMacBenh = new JTable(dtfBiBenh);
		scrollPane_2.setViewportView(tbMacBenh);
		
		btnThem = new JButton("Thêm >>");
		btnThem.setBounds(405, 431, 89, 23);
		panel.add(btnThem);
		
		btnXoa = new JButton("<< Xoá ");
		btnXoa.setBounds(405, 463, 89, 23);
		panel.add(btnXoa);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "L\u1ECBch s\u1EED b\u1EC7nh \u00E1n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		scrollPane_1.setBounds(10, 239, 768, 116);
		panel.add(scrollPane_1);
		
		String[] headerLichSu = {"Tên Bệnh","Triệu Chứng","Ngày lập"};
		dtf = new DefaultTableModel(headerLichSu,0);
		tbLichSuBenhAn = new JTable(dtf);
		scrollPane_1.setViewportView(tbLichSuBenhAn);
		
		docDLHoSo(hoSoBenhAn);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXutPhiuKhm.addActionListener(this);
		
	}
	
	public void docDLHoSo(HoSoBenhAn hoSoBenhAn) {
		if(hoSoBenhAn != null) {
			textField.setText(hoSoBenhAn.getHoTen());
			textField_2.setText(String.valueOf(hoSoBenhAn.getTuoi()));
			textField_3.setText(hoSoBenhAn.getDienThoai());
			
			if(chiTietPhieuKhamService.findByIdHoSo(hoSoBenhAn.getId()) != null ){
				List<ChiTietPhieuKham> chiTietPhieuKhams = chiTietPhieuKhamService.findByIdHoSo(hoSoBenhAn.getId());
				
				chiTietPhieuKhams.forEach(ct -> {
					String idBenh = ct.getBenh().getId();
					String tenBenh = benhService.findById(idBenh).getTenBenh();
					
					String idChiTietPk = ct.getId();
					
					System.out.println(ct.getId()+" Bên gửi");
					
					Date ngayLap = toaThuocService.findByIdPK(idChiTietPk).getNgayLap();
					
					Object[] ob = {tenBenh, ct.getTrieuChung(),
							new SimpleDateFormat(CrudStatus.DATETIME_FORMAT).format(ngayLap)
					};
					dtf.addRow(ob);
				});
			}
			
			
			
		}
	}
	
	public void docDSBenh() {
		benhService.findAll().forEach(benh -> {
			Object[] ob = {benh.getId(),benh.getTenBenh()};
			dtfDanhSachBenh.addRow(ob);
		
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if(ob.equals(btnThem)) {
			int rowDsBenh = tbDanhSachBenh.getSelectedRow();
			String benhDs = dtfDanhSachBenh.getValueAt(rowDsBenh, 1).toString();
			String idBenh = dtfDanhSachBenh.getValueAt(rowDsBenh, 0).toString(); 
			
			Object[] obj = {idBenh,benhDs};
			dtfBiBenh.addRow(obj);
		}
		else if(ob.equals(btnXoa)) {
			int rowBenh = tbMacBenh.getSelectedRow();
			dtfBiBenh.removeRow(rowBenh);
		}
		else if(ob.equals(btnXutPhiuKhm)) {
            int rows = dtfBiBenh.getRowCount(); 
            for(int i = rows - 1; i >=0; i--){
            	
            	ChiTietPhieuKham chiTietPhieuKham = new ChiTietPhieuKham();
            	Benh benh = new Benh();
            	benh.setId(dtfBiBenh.getValueAt(i, 0).toString());
            	chiTietPhieuKham.setBenh(benh);
            	
            	HoSoBenhAn hoSoBenhAn= new HoSoBenhAn();
            	String idHoSo = hoSoBenhAnService.findByPhoneNumber(textField_3.getText()).getId();
            	hoSoBenhAn.setId(idHoSo);
            	chiTietPhieuKham.setHoSoBenhAn(hoSoBenhAn);
            	
            	chiTietPhieuKham.setTrieuChung("Trieu chung "+ textField_1.getText());
            	
         
//    			ctKham.setBenh(benhServiceImpl.findByName("Benh " + i));
//    			ctKham.setHoSoBenhAn(hoSoBenhAnImpl.findByName("BenhNhan" + i));
            	
            	if(chiTietPhieuKhamService.createChiTietPhieuKham(chiTietPhieuKham)) {
            		JOptionPane.showMessageDialog(this, "Đã thêm phiếu khám");
            	} else {
            		JOptionPane.showMessageDialog(this, "Thêm phiếu khám thất bại");
            	}
            	
            	
             }
		}
		
	}
	
}
