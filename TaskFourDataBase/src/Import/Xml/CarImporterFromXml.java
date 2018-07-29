package Import.Xml;

import Garage.Brand;
import Garage.Car;
import Garage.CarBody;
import Import.CarInputType;
import Import.DataGetterFromStrings;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarImporterFromXml implements DataGetterFromStrings {

    public CarImporterFromXml(List<Car> cars, Scanner scanner, int amountOfCarsToGenerate) {
        File schemaFile = new File("src/cars.xsd");
        File xmlFile = new File("src/cars.xml");
        getXmlData(schemaFile, xmlFile, cars, scanner, amountOfCarsToGenerate);
    }

    private void getXmlData(File schemaFile, File xmlFile, List<Car> cars, Scanner scanner, int amountOfCarsToGenerate) {
        try {
            /*methods are similar, so exceptions are thrown here*/
            validator(schemaFile, xmlFile);
            xmlParser(xmlFile, cars);
        } catch (ParserConfigurationException e) {
            System.err.println("Parsing failed: " + e);
            errorHandling(cars, scanner, amountOfCarsToGenerate);
        } catch (SAXException e) {
            System.err.println("Validation failed: " + e);
            errorHandling(cars, scanner, amountOfCarsToGenerate);
        } catch (IOException e) {
            System.err.println("XML or XSD file not found: " + e);
            errorHandling(cars, scanner, amountOfCarsToGenerate);
        }
    }

    private void errorHandling(List<Car> cars, Scanner scanner, int amountOfCarsToGenerate) {
        System.err.println("Please try other options.");
        new CarInputType(cars, scanner, amountOfCarsToGenerate);
    }

    private void validator(File schemaFile, File xmlFile) throws SAXException, IOException {
        Source streamSource = new StreamSource(xmlFile);
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(schemaFile);
        Validator validator = schema.newValidator();
        validator.validate(streamSource);
    }

    private void xmlParser(File xmlFile, List<Car> cars)
            throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("car");
        List<Car> tempCars = new ArrayList<>();
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                tempCars.add(new Car(
                        getBrand(eElement.getElementsByTagName("brand").item(0).getTextContent()),
                        getCarBody(eElement.getElementsByTagName("carBody").item(0).getTextContent()),
                        getFuelConsumption(eElement.getElementsByTagName("fuelConsumption").item(0).getTextContent()),
                        getPrice(eElement.getElementsByTagName("price").item(0).getTextContent())));
            }
        }
        /*no validation required during parsing due to validation by XSD,
        * in case there is an error during parsing, damaged data won't affect
        * main list, data to main list is added after XML is completely parsed*/
        cars.addAll(tempCars);
    }

    @Override
    public double getFuelConsumption(String data) {
        return Double.valueOf(data);
    }

    @Override
    public int getPrice(String data) {
        return Integer.valueOf(data);
    }

    @Override
    public Brand getBrand(String data) {
        return Brand.valueOf(data);
    }

    @Override
    public CarBody getCarBody(String data) {
        return CarBody.valueOf(data);
    }

}
