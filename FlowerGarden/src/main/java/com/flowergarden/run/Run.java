package com.flowergarden.run;

import com.flowergarden.dao.ConnectionProvider;
import com.flowergarden.dao.GeneralFlowerDao;
import com.flowergarden.dao.RoseDao;
import com.flowergarden.dao.impl.ConnectionProviderImplSqlite;
import com.flowergarden.flowers.Flower;
import com.flowergarden.flowers.GeneralFlower;
import com.flowergarden.flowers.Rose;
import com.flowergarden.properties.FreshnessInteger;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Run {

	public static void main(String[] args) throws IOException {

		ConnectionProvider connectionProvider = new ConnectionProviderImplSqlite();
		GeneralFlowerDao generalFlowerDao = new RoseDao(connectionProvider.getConnection());
//		generalFlowerDao.deleteFlowerById(6);
//		generalFlowerDao.saveFlower(new Rose(true, 2, 2, new FreshnessInteger(2)));
		generalFlowerDao.findFlowers();

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
