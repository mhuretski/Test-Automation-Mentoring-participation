package Import.Xml;

import Garage.Brand;
import Garage.Car;
import Garage.CarBody;
import Import.CarGetter;
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

public class CarImporterFromXml implements CarGetter {


    private boolean isNoErrors = true;
    private List<Car> tempCars;

    public CarImporterFromXml() {
        File schemaFile = new File("src/cars.xsd");
        File xmlFile = new File("src/cars.xml");
        getXmlData(schemaFile, xmlFile);
    }

    private void getXmlData(File schemaFile, File xmlFile) {
        try {
            /*methods are similar, so exceptions are thrown here*/
            validator(schemaFile, xmlFile);
            xmlParser(xmlFile);
        } catch (ParserConfigurationException e) {
            System.err.println("Parsing failed: " + e);
            isNoErrors = false;
        } catch (SAXException e) {
            System.err.println("Validation failed: " + e);
            isNoErrors = false;
        } catch (IOException e) {
            System.err.println("XML or XSD file not found: " + e);
            isNoErrors = false;
        }
    }

    private void validator(File schemaFile, File xmlFile) throws SAXException, IOException {
        Source streamSource = new StreamSource(xmlFile);
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(schemaFile);
        Validator validator = schema.newValidator();
        validator.validate(streamSource);
    }

    private void xmlParser(File xmlFile)
            throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("car");
        tempCars = new ArrayList<>();
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                /*no validation required during parsing due to validation by XSD*/
                tempCars.add(new Car(
                        getBrand(eElement.getElementsByTagName("brand").item(0).getTextContent()),
                        getCarBody(eElement.getElementsByTagName("carBody").item(0).getTextContent()),
                        getFuelConsumption(eElement.getElementsByTagName("fuelConsumption").item(0).getTextContent()),
                        getPrice(eElement.getElementsByTagName("price").item(0).getTextContent())));
            }
        }
    }

    private Brand getBrand(String data) {
        return Brand.valueOf(data);
    }

    private CarBody getCarBody(String data) {
        return CarBody.valueOf(data);
    }

    private double getFuelConsumption(String data) {
        return Double.valueOf(data);
    }

    private int getPrice(String data) {
        return Integer.valueOf(data);
    }

    @Override
    public List<Car> getCars() {
        return tempCars;
    }

    @Override
    public boolean isNoErrors() {
        return isNoErrors;
    }

}
