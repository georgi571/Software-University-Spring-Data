package _8SpringData._1SpringData._9XMLProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.util;

import jakarta.xml.bind.JAXBException;

public interface XmlParser {
    <E> E parse(Class<E> clazz, String path) throws JAXBException;
    <E> void exportToFile(Class<E> clazz, E object, String path) throws JAXBException;
}
