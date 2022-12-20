package util;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.ogm.OgmSessionFactory;
import org.hibernate.ogm.boot.OgmSessionFactoryBuilder;
import org.hibernate.ogm.cfg.OgmProperties;
import org.hibernate.service.ServiceRegistry;

import iuh.ktpm14.entity.Benh;
import iuh.ktpm14.entity.ChiTietPhieuKham;
import iuh.ktpm14.entity.HoSoBenhAn;




public class Connect {
  private static Connect instance = null;
  private OgmSessionFactory sessionFactory;

  private Connect() {
    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySetting(OgmProperties.ENABLED, true)
        .configure().build();

    Metadata metadata = new MetadataSources(serviceRegistry)
    		.addAnnotatedClass(Benh.class)
            .addAnnotatedClass(ChiTietPhieuKham.class)
            .addAnnotatedClass(HoSoBenhAn.class)    
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
