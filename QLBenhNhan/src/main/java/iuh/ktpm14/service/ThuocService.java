package iuh.ktpm14.service;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import iuh.ktpm14.entity.Thuoc;

public interface ThuocService{
//	
	public List<Thuoc> getAllThuoc(DefaultTableModel tableModel);
	public List<Thuoc> getAllThuoc();
	public void addThuoc(Thuoc thuoc);
	public boolean deleteThuoc(String name);
	public Thuoc getThuocById(String id);
	
}