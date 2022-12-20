package iuh.ktpm14.service;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.google.gson.Gson;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import iuh.ktpm14.connect.ConnectDB;
import iuh.ktpm14.entity.Benh;
import iuh.ktpm14.entity.ChiTietPhieuKham;

public class ChiTietPhieuKhamServiceImpl {
private MongoCollection collect;
	
	private ConnectDB connect = new ConnectDB();
	
	private Gson gson = new Gson();
	
	public ChiTietPhieuKhamServiceImpl() {
		this.collect = connect.connectMongo("QLKhamBenh","ChiTietPhieuKhamBenh");
	}
	
	
	public boolean save(ChiTietPhieuKham benh) {
		if(findBymaPhieu(benh.getMaPhieuKhamBenh()) == null) {
			Document doc = new Document()
					.append("maPhieuKhamBenh", benh.getMaPhieuKhamBenh())
					.append("maBenh", benh.getMaBenh())
					.append("trieuChung", benh.getTrieuChung());

			this.collect.insertOne(doc);
			return true;
		}
		return false;
	}
	
	
	public ChiTietPhieuKham findBymaPhieu(ObjectId name) {
		Document doc = new Document("maPhieuKhamBenh", name);
		FindIterable<Document> result = this.collect.find(doc);
		ChiTietPhieuKham benh = null;
		for(Document dc : result) {
			benh= gson.fromJson(dc.toJson(), ChiTietPhieuKham.class);
		}
		

		return benh;
	}
	
	
}
