package com.flowergarden.rest;

import com.flowergarden.bouquet.MarriedBouquet;
import com.flowergarden.dao.impl.InitDBImplSqlite;
import com.flowergarden.dao.impl.MarriedBouquetDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;

@Path("/boquete")
public class BouqueteRestService {


    @Autowired
    MarriedBouquetDaoImpl marriedBouquetDao;

    @Autowired
    InitDBImplSqlite initDBImplSqlite;

    public BouqueteRestService() throws SQLException {
        initDBImplSqlite.deleteTables();
        initDBImplSqlite.createTables();
        initDBImplSqlite.populateTables();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public MarriedBouquet getBouqueteById(
        @PathParam("id")
            int bouqueteId) throws SQLException {
        return marriedBouquetDao.findBouquetById(bouqueteId);
    }
}
