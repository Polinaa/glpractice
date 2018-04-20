package com.flowergarden.servlets;

import com.flowergarden.bouquet.Bouquet;
import com.flowergarden.dao.impl.InitDBImplSqlite;
import com.flowergarden.dao.impl.MarriedBouquetDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class BouquetsServlet extends HttpServlet {

    @Autowired
    MarriedBouquetDaoImpl marriedBouquetDao;

    @Autowired
    InitDBImplSqlite initDBImplSqlite;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
        try {
            loadData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        final StringBuilder str = new StringBuilder();
        List<Bouquet> bouquets;
        try {
            bouquets = marriedBouquetDao.findAllBouquets();
            bouquets.forEach(b -> str.append(b.toString()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        writer.println(str);
        writer.close();
    }

    private void loadData() throws SQLException {
        initDBImplSqlite.deleteTables();
        initDBImplSqlite.createTables();
        initDBImplSqlite.populateTables();
    }
}
