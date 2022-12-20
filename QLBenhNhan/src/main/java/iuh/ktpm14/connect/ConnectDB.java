package iuh.ktpm14.connect;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class ConnectDB {
	private MongoDatabase database;
	private MongoClient client;
	private MongoCollection collection;
	
	public MongoCollection connectMongo (String dataName, String collectionName) {
		String url ="mongodb://localhost";
		
		this.client = MongoClients.create(url);
		this.database = this.client.getDatabase(dataName);
		this.collection = this.database.getCollection(collectionName);
		
		System.out.println("Connected successfully");
		
		return this.collection;
	}
	
	
	public MongoClient connect() {
		String url ="mongodb://localhost";
		MongoClient client = MongoClients.create(url);
		return client;
	}

	public MongoDatabase createDatabase(MongoClient client, String name) {
		MongoDatabase database = client.getDatabase(name);
		return database;
	}
	
}
