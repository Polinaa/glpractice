package com.flowergarden.dao;

import com.flowergarden.bouquet.Bouquet;
import org.codehaus.jettison.json.JSONException;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.sql.SQLException;

public interface MarriedBouquetJsonDao {

    void saveBouquetToJson(int id) throws IOException, JAXBException, SQLException;

    Bouquet readBouquet() throws JAXBException, IOException, JSONException, XMLStreamException;
}
