package iuh.ktpm14.service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.bson.Document;

import com.google.gson.Gson;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Updates;

import iuh.ktpm14.connect.ConnectDB;
import iuh.ktpm14.entity.Benh;
import iuh.ktpm14.entity.HoSoBenhAn;

public class HoSoBenhAnServiceIml implements HoSoBenhAnService{
	
	private MongoCollection collect;
	

	
	private ConnectDB connect = new ConnectDB();
	
			
	public HoSoBenhAnServiceIml() {
		this.collect = this.connect.connectMongo("QLKhamBenh", "hoSoBenhAn");
	}
	
	
	public boolean save(HoSoBenhAn benhAn) {
		if(findBySDT(benhAn.getDienThoai()) == null) {
			Document doc = new Document()
					.append("hoTen", benhAn.getHoTen())
					.append("tuoi", benhAn.getTuoi())
					.append("diaChi", benhAn.getDiaChi())
					.append("dienThoai", benhAn.getDienThoai())
					.append("ngayLapHS", benhAn.getNgayLap());
			this.collect.insertOne(doc);
			return true;
		}
		return false;
	}
	
	


	public HoSoBenhAn findBySDT(String sdt) {
		Document doc = new Document("dienThoai", sdt);
		FindIterable<Document> result = this.collect.find(doc);
		
		HoSoBenhAn benhAn = null;
		for(Document dc : result) {			
			Date date = dc.getDate("ngayLapHS");
			SimpleDateFormat formattedDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");
			formattedDate.setTimeZone(TimeZone.getTimeZone("UTC"));
//			System.out.println(formattedDate.format(date));
			LocalDateTime ldt = LocalDateTime.ofInstant(date.toInstant(), ZoneId.of("Asia/Ho_Chi_Minh"));
			
			
			benhAn = new HoSoBenhAn(dc.getObjectId("id"), dc.getString("hoTen"), dc.getInteger("tuoi"),
					dc.getString("diaChi"), dc.getString("dienThoai"), ldt);
		}
		

		return benhAn;
	
	}


	public List<HoSoBenhAn> findAll() {
		List<HoSoBenhAn> benhAns = new ArrayList<HoSoBenhAn>();
		FindIterable<Document> result = this.collect.find();
		
		for(Document dc : result) {
			Date date = dc.getDate("ngayLapHS");
			SimpleDateFormat formattedDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");
			formattedDate.setTimeZone(TimeZone.getTimeZone("UTC"));
//			System.out.println(formattedDate.format(date));
			LocalDateTime ldt = LocalDateTime.ofInstant(date.toInstant(), ZoneId.of("Asia/Ho_Chi_Minh"));
			
			
			HoSoBenhAn benhAn = new HoSoBenhAn(dc.getObjectId("id"), dc.getString("hoTen"), dc.getInteger("tuoi"),
					dc.getString("diaChi"), dc.getString("dienThoai"), ldt);
			
			benhAns.add(benhAn);
		}
		
		
		return benhAns;
	}


	public boolean deleteBySdt(String sdt) {
		if(findBySDT(sdt) != null) {
			Document doc = new Document("dienThoai",sdt);
			this.collect.deleteOne(doc);
			return true;
		}
		return false;
	}


	public boolean updateBySdt(String sdt, HoSoBenhAn benhAn) {
		if(findBySDT(sdt) != null) {
			Document doc = new Document("dienThoai", sdt); 
			
			this.collect.updateOne(doc, Updates.set("hoTen", benhAn.getHoTen()));
			this.collect.updateOne(doc, Updates.set("tuoi", benhAn.getTuoi()));
			this.collect.updateOne(doc, Updates.set("dienThoai", benhAn.getDienThoai()));
			this.collect.updateOne(doc, Updates.set("diaChi", benhAn.getDiaChi()));
			
			return true;
		}
		return false;
	}
	
//	public boolean updateBySdtAnd(String sdt, HoSoBenhAn benhAn, String tenBenh, String trieuChung) {
//		if(findBySDT(sdt) != null) {
//			Document doc = new Document("dienThoai", sdt); 
//			Document dc = new Document().append(tenBenh, trieuChung);
//			
//			this.collect.updateOne(doc, Updates.set("benh", dc));
//			
//			return true;
//		}
//		return false;
//	}

	

}
