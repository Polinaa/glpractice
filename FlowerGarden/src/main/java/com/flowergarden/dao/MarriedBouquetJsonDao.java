package com.flowergarden.dao;

import com.flowergarden.bouquet.Bouquet;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface MarriedBouquetJsonDao {

    void saveBouquetToJson(Bouquet bouquet) throws IOException, JAXBException;

    Bouquet readBouquetById(int id) throws JAXBException;
}
