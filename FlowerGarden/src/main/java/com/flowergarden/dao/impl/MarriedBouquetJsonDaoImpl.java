package com.flowergarden.dao.impl;

import com.flowergarden.bouquet.Bouquet;
import com.flowergarden.bouquet.MarriedBouquet;
import com.flowergarden.dao.MarriedBouquetJsonDao;
import org.codehaus.jettison.mapped.Configuration;
import org.codehaus.jettison.mapped.MappedNamespaceConvention;
import org.codehaus.jettison.mapped.MappedXMLStreamWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLStreamWriter;
import java.io.*;

public class MarriedBouquetJsonDaoImpl implements MarriedBouquetJsonDao {

    private static final String FILE_PATH = "bouquets.xml";

    @Override
    public void saveBouquetToJson(Bouquet bouquet) throws IOException, JAXBException {
        JAXBContext jc = JAXBContext.newInstance(MarriedBouquet.class);
        Configuration config = new Configuration();
        MappedNamespaceConvention con = new MappedNamespaceConvention(config);
        Writer writer = new FileWriter(new File(FILE_PATH));
        XMLStreamWriter xmlStreamWriter = new MappedXMLStreamWriter(con, writer);

        Marshaller marshaller = jc.createMarshaller();
        marshaller.marshal(bouquet, xmlStreamWriter);
    }

    @Override
    public Bouquet readBouquetById(int id) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(Bouquet.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        Bouquet bouquet = (Bouquet) unmarshaller.unmarshal(new File(FILE_PATH));
        return bouquet;
    }
}
