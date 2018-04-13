package com.flowergarden.dao.impl;

import com.flowergarden.bouquet.Bouquet;
import com.flowergarden.bouquet.MarriedBouquet;
import com.flowergarden.dao.BouquetDao;
import com.flowergarden.dao.MarriedBouquetJsonDao;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.codehaus.jettison.mapped.Configuration;
import org.codehaus.jettison.mapped.MappedNamespaceConvention;
import org.codehaus.jettison.mapped.MappedXMLStreamReader;
import org.codehaus.jettison.mapped.MappedXMLStreamWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;

@Component
public class MarriedBouquetJsonDaoImpl implements MarriedBouquetJsonDao {

    private static final String FILE_PATH = "bouquets.json";
    private static final Class JAXB_CONTEXT_CLASS = MarriedBouquet.class;

    private JAXBContext jc;

    private MappedNamespaceConvention mappedNamespaceConvention;

    public MarriedBouquetJsonDaoImpl() throws JAXBException {
        this.jc = JAXBContext.newInstance(JAXB_CONTEXT_CLASS);
        Configuration config = new Configuration();
        mappedNamespaceConvention = new MappedNamespaceConvention(config);
    }

    @Autowired
    private BouquetDao marriedBouquetDao;

    @Override
    public void saveBouquetToJson(int id) throws IOException, JAXBException, SQLException {
        Bouquet bouquet = marriedBouquetDao.findBouquetById(id);
        Writer writer = new FileWriter(new File(FILE_PATH));
        XMLStreamWriter xmlStreamWriter = new MappedXMLStreamWriter(mappedNamespaceConvention, writer);
        Marshaller marshaller = jc.createMarshaller();
        marshaller.marshal(bouquet, xmlStreamWriter);
    }

    @Override
    public Bouquet readBouquet() throws JAXBException, IOException, JSONException, XMLStreamException {
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        byte[] encoded = Files.readAllBytes(Paths.get(FILE_PATH));
        String fileContent = new String(encoded, Charset.defaultCharset());
        JSONObject jsonObject = new JSONObject(fileContent);
        XMLStreamReader xmlStreamReader = new MappedXMLStreamReader(jsonObject, mappedNamespaceConvention);
        Bouquet bouquet = (MarriedBouquet) unmarshaller.unmarshal(xmlStreamReader);
        return bouquet;
    }
}
