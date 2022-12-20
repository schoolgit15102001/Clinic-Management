package iuh.ktpm14.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.print.Doc;
import javax.swing.table.DefaultTableModel;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.google.gson.Gson;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import iuh.ktpm14.connect.ConnectDB;

import iuh.ktpm14.entity.ChiTietToaThuoc;

import iuh.ktpm14.entity.Benh;

import iuh.ktpm14.entity.PhieuKham;

import iuh.ktpm14.entity.Thuoc;
import iuh.ktpm14.entity.ToaThuoc;

public class ToaThuocServiceImpl implements ToaThuocService{

	public List<ToaThuoc> getAllToaThuoc() {
		ConnectDB connectDB = new ConnectDB();
		MongoClient client = connectDB.connect();
		MongoDatabase database = connectDB.createDatabase(client, "QLKhamBenh");
		MongoCollection<Document> collection = database.getCollection("ToaThuoc");
		
		FindIterable<Document> fi = collection.find();
		Iterator<Document> it = fi.iterator();
		Gson gson = new Gson();
		List<ToaThuoc> list = new ArrayList<ToaThuoc>();
		while (it.hasNext()) {
			System.out.println(it.next().toJson());
			ToaThuoc toaThuoc = gson.fromJson(it.next().toJson(), ToaThuoc.class);
			list.add(toaThuoc);
		}
		return list;
	}
	
	public List<ChiTietToaThuoc> getAllChiTietToa(DefaultTableModel table) {
		ConnectDB connectDB = new ConnectDB();
		MongoClient client = connectDB.connect();
		MongoDatabase database = connectDB.createDatabase(client, "QLKhamBenh");
		MongoCollection<Document> collection = database.getCollection("ChiTietToaThuoc");
		
//		FindIterable<Document> fi = collection.find();
//		Iterator<Document> it = fi.iterator();
		Gson gson = new Gson();
		List<ChiTietToaThuoc> list = new ArrayList<ChiTietToaThuoc>();
		FindIterable<Document> fi = collection.find();
		Iterator<Document> it = fi.iterator();
		while (it.hasNext()) {
			Vector<Object> vector = new Vector<Object>();
			Document document = it.next();
			ChiTietToaThuoc ct = gson.fromJson(document.toJson(), ChiTietToaThuoc.class);
			vector.add(ct.getId().get());
			vector.add(ct.getThuoc().getTen_thuoc());
			vector.add(ct.getSo_luong());
			list.add(ct);
			table.addRow(vector);
		}
		return list;
	}
	public ChiTietToaThuoc getChiTietToaThuocByTenThuoc(String name, DefaultTableModel table) {
		ConnectDB connectDB = new ConnectDB();
		MongoClient client = connectDB.connect();
		MongoDatabase database = connectDB.createDatabase(client, "QLKhamBenh");
		MongoCollection<Document> collection = database.getCollection("ChiTietToaThuoc");
		
		
		FindIterable<Document> fi = collection.find(Filters.eq("thuoc.ten_thuoc", name).toBsonDocument());
		Iterator<Document> it = fi.iterator();
		Gson gson = new Gson();
		ChiTietToaThuoc chiTiet = null;
		Vector<Object> vector = new Vector<Object>();
		Document document = it.next();
		chiTiet = gson.fromJson(document.toJson(), ChiTietToaThuoc.class);
		vector.add(chiTiet.getId().get());
		vector.add(chiTiet.getThuoc().getTen_thuoc());
		vector.add(chiTiet.getSo_luong());
		table.addRow(vector);
		return chiTiet;
	}
	
	public ChiTietToaThuoc getChiTietToaThuocByTenThuoc(String name) {
		ConnectDB connectDB = new ConnectDB();
		MongoClient client = connectDB.connect();
		MongoDatabase database = connectDB.createDatabase(client, "QLKhamBenh");
		MongoCollection<Document> collection = database.getCollection("ChiTietToaThuoc");
		
		
		FindIterable<Document> fi = collection.find(Filters.eq("thuoc.ten_thuoc", name).toBsonDocument());
		Iterator<Document> it = fi.iterator();
		Gson gson = new Gson();
		ChiTietToaThuoc chiTiet = null;
		Vector<Object> vector = new Vector<Object>();
		Document document = it.next();
		
		chiTiet = gson.fromJson(document.toJson(), ChiTietToaThuoc.class);
		vector.add(chiTiet.getThuoc().getTen_thuoc());
		vector.add(chiTiet.getSo_luong());
		System.out.println(vector.toString());
		System.out.println(chiTiet.getThuoc().toString());
		return chiTiet;
	}
	public ChiTietToaThuoc getChiTietToaThuocByIdThuoc(String id) {
		ConnectDB connectDB = new ConnectDB();
		MongoClient client = connectDB.connect();
		MongoDatabase database = connectDB.createDatabase(client, "QLKhamBenh");
		MongoCollection<Document> collection = database.getCollection("ChiTietToaThuoc");
		
		
		FindIterable<Document> fi = collection.find(Filters.eq("thuoc._id._id", new ObjectId(id)).toBsonDocument());
		Iterator<Document> it = fi.iterator();
		ChiTietToaThuoc chiTiet = null;
		Gson gson = new Gson();
		while(it.hasNext()) {
			Document document = it.next();
			System.out.println(document);
			chiTiet = gson.fromJson(document.toJson(), ChiTietToaThuoc.class);
		}
		
		return chiTiet;
	}
	public void addChiTiet(ChiTietToaThuoc chiTietToaThuoc) {
		ConnectDB connectDB = new ConnectDB();
		MongoClient client = connectDB.connect();
		MongoDatabase database = connectDB.createDatabase(client, "QLKhamBenh");
		
		Thuoc thuoc = chiTietToaThuoc.getThuoc();
		Document documentId = new Document().append("_id", thuoc.getId().get());
		Document documentThuoc = new Document().append("_id", documentId).append("ten_thuoc", thuoc.getTen_thuoc()).append("huong_dan", thuoc.getHuong_dan());
		Document document = new Document().append("thuoc",documentThuoc).append("so_luong", chiTietToaThuoc.getSo_luong());
		database.getCollection("ChiTietToaThuoc").insertOne(document);
	}

	public void addToaThuoc(ToaThuoc toaThuoc) {
		ConnectDB connectDB = new ConnectDB();
		MongoClient client = connectDB.connect();
		MongoDatabase database = connectDB.createDatabase(client, "QLKhamBenh");
		
		
		Benh benh = toaThuoc.getBenh();
		PhieuKham phieuKham = toaThuoc.getPhieuKham();
		List<ChiTietToaThuoc> chiTietToaThuocs = toaThuoc.getChiTietToaThuocs();
		List<Document> listChiTiet = new ArrayList<Document>();
		chiTietToaThuocs.forEach(ct->{
			Document docChiTiet = new Document().append("thuoc", ct.getThuoc()).append("so_luong", ct.getSo_luong());
			listChiTiet.add(docChiTiet);
		});
		Document documentId = new Document().append("_id", toaThuoc.get_id().get());
		Document docBenh = new Document().append("_id", benh.getId().get()).append("ten_benh", benh.getTenBenh());
		Document docPhieuKham = new Document().append("_id", phieuKham.getId()).append("ma_ho_so", phieuKham.getMaHoSoBA());
//		Document documentThuoc = new Document().append("_id", documentId).append("ten_thuoc", thuoc.getTen_thuoc()).append("huong_dan", thuoc.getHuong_dan());
		Document document = new Document().append("_id", documentId).append("benh",docBenh).append("chi_tiet", listChiTiet);
		database.getCollection("ToaThuoc").insertOne(document);
	}

	
	public boolean deleteChiTiet(String id) {
		ConnectDB connectDB = new ConnectDB();
		MongoClient client = connectDB.connect();
		MongoDatabase database = connectDB.createDatabase(client, "QLKhamBenh");
		MongoCollection<Document> collection = database.getCollection("ChiTietToaThuoc");
		
		if(collection.deleteOne(Filters.eq("_id", new ObjectId(id)))!=null)
			return true;
		return false;
	}
	
	public static void main(String[] args) {
		Thuoc thuoc = new Thuoc("Test", "Test");
		ChiTietToaThuoc chiTietToaThuoc = new ChiTietToaThuoc(thuoc, 1);
		ToaThuocServiceImpl impl = new ToaThuocServiceImpl();
//		impl.addChiTiet(chiTietToaThuoc);
//		impl.addChiTiet(chiTietToaThuoc);
//		impl.addChiTiet(chiTietToaThuoc);
//		impl.addChiTiet(chiTietToaThuoc);
//		impl.addChiTiet(chiTietToaThuoc);
//		impl.addChiTiet(chiTietToaThuoc);
//		System.out.println(impl.deleteChiTiet("6386f92e020ae64a70208cc7"));
//		System.out.println(impl.getChiTietToaThuocByTenThuoc("Test"));
//		System.out.println(impl.getChiTietToaThuocByTenThuoc("Test"));
//		System.out.println(impl.getChiTietToaThuocByTenThuoc("Test"));
		ChiTietToaThuoc chiTietToaThuoc2 = impl.getChiTietToaThuocByIdThuoc("6386fd44f76d391a8fa52ad7");
		System.out.println(chiTietToaThuoc2);
	}
}

