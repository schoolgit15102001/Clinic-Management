package iuh.ktpm14.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.google.gson.Gson;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Updates;

import iuh.ktpm14.connect.ConnectDB;
import iuh.ktpm14.entity.Benh;

public class BenhServiceImpl implements BenhService{
	
	private MongoCollection collect;
	
	private ConnectDB connect = new ConnectDB();
	
	private Gson gson = new Gson();
	
	public BenhServiceImpl() {
		this.collect = connect.connectMongo("QLKhamBenh","Benh");
	}

	public boolean save(Benh benh) {
		if(findByName(benh.getTenBenh()) == null) {
			Document doc = new Document().append("tenBenh", benh.getTenBenh());
			this.collect.insertOne(doc);
			return true;
		}
		return false;
	}

	public List<Benh> findAll() {
		List<Benh> benhs = new ArrayList<Benh>();
		FindIterable<Document> result = this.collect.find();
		Gson gson = new Gson();
		
		for(Document doc : result) {
//			System.out.println(doc.toJson());
			Benh benh= gson.fromJson(doc.toJson(), Benh.class);
			benhs.add(benh);
		}
		
		
		return benhs;
		
	}

	public Benh findByName(String name) {
		Document doc = new Document("tenBenh", name);
		FindIterable<Document> result = this.collect.find(doc);
		Benh benh = null;
		for(Document dc : result) {
			benh= gson.fromJson(dc.toJson(), Benh.class);
		}
		

		return benh;
	}

	public boolean deleteByName(String name) {
		if(findByName(name) != null) {
			Document doc = new Document("tenBenh",name);
			this.collect.deleteOne(doc);
			return true;
		}
		return false;
	}

	public boolean updateByName(String name, String key, String value) {
		if(findByName(name) != null) {
			Document doc = new Document("tenBenh", name); 
			this.collect.updateOne(doc, Updates.set(key, value));
			return true;
		}
		return false;
	}

	public Benh findById(ObjectId name) {
		Document doc = new Document("id", name);
		FindIterable<Document> result = this.collect.find(doc);
		Benh benh = null;
		for(Document dc : result) {
			benh= gson.fromJson(dc.toJson(), Benh.class);
		}
		

		return benh;
	}
	


} 
