package util;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.ogm.OgmSessionFactory;
import org.hibernate.ogm.boot.OgmSessionFactoryBuilder;
import org.hibernate.ogm.cfg.OgmProperties;
import org.hibernate.service.ServiceRegistry;

import entity.Benh;
import entity.ChiTietPhieuKham;
import entity.ChiTietToaThuoc;
import entity.HoSoBenhAn;
import entity.Thuoc;
import entity.ToaThuoc;


public class Connect {
  private static Connect instance = null;
  private OgmSessionFactory sessionFactory;

  private Connect() {
    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySetting(OgmProperties.ENABLED, true)
        .configure().build();

    Metadata metadata = new MetadataSources(serviceRegistry)
        .addAnnotatedClass(Benh.class)
        .addAnnotatedClass(ChiTietPhieuKham.class)
        .addAnnotatedClass(ChiTietToaThuoc.class)
        .addAnnotatedClass(HoSoBenhAn.class)
        .addAnnotatedClass(Thuoc.class)
        .addAnnotatedClass(ToaThuoc.class)
        .getMetadataBuilder().build();

    sessionFactory = metadata.getSessionFactoryBuilder().unwrap(OgmSessionFactoryBuilder.class).build();
  }

  public static Connect getInstance() {
    if (instance == null) {
      instance = new Connect();
    }
    return instance;
  }

  public OgmSessionFactory getSessionFactory() {
    return sessionFactory;
  }
}
