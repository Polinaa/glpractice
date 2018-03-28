package com.flowergarden.run;

import com.flowergarden.bouquet.MarriedBouquet;
import com.flowergarden.dao.ConnectionProvider;
import com.flowergarden.dao.GeneralFlowerDao;
import com.flowergarden.dao.impl.MarriedBouquetDaoImpl;
import com.flowergarden.dao.impl.RoseDaoImpl;
import com.flowergarden.dao.impl.ConnectionProviderImplSqlite;

import java.io.IOException;

public class Run {

	public static void main(String[] args) throws IOException {

//		ConnectionProvider connectionProvider = new ConnectionProviderImplSqlite();
//		GeneralFlowerDao generalGeneralFlowerDao = new RoseDaoImpl(connectionProvider.getConnection());
//		generalGeneralFlowerDao.deleteFlowerById(6);
//		generalGeneralFlowerDao.saveFlower(new Rose(true, 2, 2, new FreshnessInteger(2)));
//		generalGeneralFlowerDao.findFlowers();
//		MarriedBouquetDaoImpl marriedBouquet = new MarriedBouquetDaoImpl(connectionProvider.getConnection());
//		marriedBouquet.findBouquets();

		}

//		File file = new File("flowergarden.db");
//		String url = "jdbc:sqlite:"+file.getCanonicalFile().toURI();
//		System.out.println(url);
//		try(Connection conn = DriverManager.getConnection(url)) {
//			Statement st = conn.createStatement();
//			ResultSet rs = st.executeQuery("select * from flower");
//			while (rs.next()) {
//				System.out.println(rs.getString(2));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}

//	}

}
