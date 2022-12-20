package iuh.ktpm14.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.function.Consumer;

import javax.swing.JTable;
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
import iuh.ktpm14.entity.Thuoc;

public class ThuocServiceImpl implements ThuocService{
//
	public List<Thuoc> getAllThuoc(DefaultTableModel table) {
		ConnectDB connectDB = new ConnectDB();
		MongoClient client = connectDB.connect();
		MongoDatabase database = connectDB.createDatabase(client, "QLKhamBenh");
		MongoCollection<Document> collection = database.getCollection("Thuoc");
		
		FindIterable<Document> fi = collection.find();
		Iterator<Document> it = fi.iterator();
		Gson gson = new Gson();
		List<Thuoc> list = new ArrayList<Thuoc>();
		while (it.hasNext()) {
			Vector<Object> vector = new Vector<Object>();
			Document document = it.next();
			vector.add(document.get("_id"));
			vector.add(document.getString("ten_thuoc"));
			vector.add(document.getString("huong_dan"));
			table.addRow(vector);
		}
		return list;
	}
	public List<Thuoc> getAllThuoc() {
		ConnectDB connectDB = new ConnectDB();
		MongoClient client = connectDB.connect();
		MongoDatabase database = connectDB.createDatabase(client, "QLKhamBenh");
		MongoCollection<Document> collection = database.getCollection("Thuoc");
		
		FindIterable<Document> fi = collection.find();
		Iterator<Document> it = fi.iterator();
		Gson gson = new Gson();
		List<Thuoc> list = new ArrayList<Thuoc>();
		while (it.hasNext()) {
			Thuoc thuoc = gson.fromJson(it.next().toJson(), Thuoc.class);
			list.add(thuoc);
		}
		return list;
	}
	
	public void addThuoc(Thuoc thuoc) {
		ConnectDB connectDB = new ConnectDB();
		MongoClient client = connectDB.connect();
		MongoDatabase database = connectDB.createDatabase(client, "QLKhamBenh");
		
		Document document = new Document().append("ten_thuoc", thuoc.getTen_thuoc()).append("huong_dan", thuoc.getHuong_dan());
		database.getCollection("Thuoc").insertOne(document);
	}

	public boolean deleteThuoc(String name) {
		ConnectDB connectDB = new ConnectDB();
		MongoClient client = connectDB.connect();
		MongoDatabase database = connectDB.createDatabase(client, "QLKhamBenh");
		MongoCollection<Document> collection = database.getCollection("Thuoc");
		
		if(collection.deleteOne(Filters.eq("ten_thuoc", name))!=null)
			return true;
		return false;
	}
	
	public Thuoc getThuocById(String id) {
		ConnectDB connectDB = new ConnectDB();
		MongoClient client = connectDB.connect();
		MongoDatabase database = connectDB.createDatabase(client, "QLKhamBenh");
		MongoCollection<Document> collection = database.getCollection("Thuoc");
		
		
		FindIterable<Document> fi = collection.find(Filters.eq("_id", new ObjectId(id)));
		Iterator<Document> it = fi.iterator();
		Gson gson = new Gson();
		Thuoc thuoc = null;
		while (it.hasNext()) {
			thuoc = gson.fromJson(it.next().toJson(), Thuoc.class);
		}
		return thuoc;
	}
	
	public Thuoc getThuocByName(String name, DefaultTableModel table) {
		ConnectDB connectDB = new ConnectDB();
		MongoClient client = connectDB.connect();
		MongoDatabase database = connectDB.createDatabase(client, "QLKhamBenh");
		MongoCollection<Document> collection = database.getCollection("Thuoc");
		
		
		FindIterable<Document> fi = collection.find(Filters.eq("ten_thuoc", name));
		Iterator<Document> it = fi.iterator();
		Gson gson = new Gson();
		Thuoc thuoc = null;
		while (it.hasNext()) {
			Vector<Object> vector = new Vector<Object>();
			Document document = it.next();
			vector.add(document.getString("ten_thuoc"));
			vector.add(document.getString("huong_dan"));
			table.addRow(vector);
			thuoc = gson.fromJson(it.next().toJson(), Thuoc.class);
		}
		return thuoc;
	}
	
	
	public Thuoc update(String id, Thuoc newThuoc) {
		ConnectDB connectDB = new ConnectDB();
		MongoClient client = connectDB.connect();
		MongoDatabase database = connectDB.createDatabase(client, "QLKhamBenh");
		MongoCollection<Document> collection = database.getCollection("Thuoc");
		
		
		FindIterable<Document> fi = collection.find(Filters.eq("_id", new ObjectId(id)));
		Iterator<Document> it = fi.iterator();
		Gson gson = new Gson();
		Thuoc thuoc = null;
		while (it.hasNext()) {
			thuoc = gson.fromJson(it.next().toJson(), Thuoc.class);
			thuoc.setTen_thuoc(newThuoc.getTen_thuoc());
			thuoc.setHuong_dan(newThuoc.getHuong_dan());
		}
		return thuoc;
	}
	
	
//	public static void main(String[] args) {
//		ConnectDB connectDB = new ConnectDB();
//		MongoClient client = connectDB.connect();
//		ThuocServiceImpl impl = new ThuocServiceImpl();
//		MongoDatabase database = connectDB.createDatabase(client, "QLKhamBenh");
//		Thuoc thuoc = new Thuoc("Test", "Test");
//		impl.addThuoc(thuoc);
//		impl.addThuoc(thuoc);
//		impl.addThuoc(thuoc);
//		impl.addThuoc(thuoc);
//		impl.addThuoc(thuoc);
//		impl.addThuoc(thuoc);
//		impl.addThuoc(thuoc);
//		impl.addThuoc(thuoc);
//		impl.getAllThuoc().forEach(new Consumer<Thuoc>() {
//			public void accept(Thuoc t) {
//				System.out.println(t.toString());
//			}
//		});
//		impl.getThuocByName("Test").forEach(new Consumer<Thuoc>() {
//			public void accept(Thuoc t) {
//				System.out.println(t.toString());
//			}
//		});
//		System.out.println(impl.getThuocById("638622df286f6d63723af964").toString());
//		System.out.println(impl.deleteThuoc("Test"));
//	}

	
 
}