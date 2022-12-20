package iuh.ktpm14.service;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.google.gson.Gson;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import iuh.ktpm14.connect.ConnectDB;
import iuh.ktpm14.entity.Benh;
import iuh.ktpm14.entity.HoSoBenhAn;
import iuh.ktpm14.entity.PhieuKham;

public class PhieuKhamServiceImpl {
private MongoCollection collect;
	

	private Gson gson = new Gson();
	private ConnectDB connect = new ConnectDB();
	
			
	public PhieuKhamServiceImpl() {
		this.collect = this.connect.connectMongo("QLKhamBenh", "PhieuKhamBenh");
	}
	
	public boolean save(PhieuKham phieuKham) {
			if(findByMaPhieu(phieuKham.getMaHoSoBA()) == null) {
				Document doc = new Document()
						.append("MaHoSoBenhAn", phieuKham.getMaHoSoBA());
						
				this.collect.insertOne(doc);
				return true;
			} 
			return false;
	}
	
	public PhieuKham findByMaPhieu(ObjectId name) {
		Document doc = new Document("MaHoSoBenhAn", name);
		FindIterable<Document> result = this.collect.find(doc);
		PhieuKham benh = null;
		for(Document dc : result) {
			benh= gson.fromJson(dc.toJson(), PhieuKham.class);
		}
		

		return benh;
	}
	
	
}
