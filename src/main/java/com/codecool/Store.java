package com.codecool;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class Store implements StorageCapable {
    List<Product> listOfProducts = new ArrayList<>();

    public void store(Product product){
        listOfProducts = loadProducts();
        storeProduct(product);
        saveToXml(listOfProducts);
    }

    @Override
    public List<Product> getAllProduct(){
        return loadProducts();
    }

    @Override
    public void storeCDProduct(String name, int price, int tracks){
        store(createProduct("CD", name, price, tracks));
    }

    @Override
    public void storeBookProduct(String name, int price, int pages){
        store(createProduct("Book", name, price, pages));
    }

    protected Product createProduct(String type, String name, int price, int size){

        if (type.equals("CD")) return new CDProduct(name, price, size);
        else return new BookProduct(name, price, size);
    }

    protected abstract void storeProduct(Product product);

    private void saveToXml(List<Product> list){

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Products");
            doc.appendChild(rootElement);

            for (Product product : list) {
                Element child = doc.createElement("Product");
                rootElement.appendChild(child);
                Attr nameAttr = doc.createAttribute("name");
                Attr priceAttr = doc.createAttribute("price");
                Attr sizeAttr = doc.createAttribute("size");
                Attr typeAttr = doc.createAttribute("type");
                nameAttr.setValue(product.getName());
                priceAttr.setValue(Integer.toString(product.getPrice()));

                if (product instanceof BookProduct) {
                    sizeAttr.setValue(Integer.toString(((BookProduct) product).getSize()));
                    typeAttr.setValue("book");
                } else {
                    sizeAttr.setValue(Integer.toString(((CDProduct) product).getSize()));
                    typeAttr.setValue("cd");
                }

                child.setAttributeNode(nameAttr);
                child.setAttributeNode(priceAttr);
                child.setAttributeNode(sizeAttr);
                child.setAttributeNode(typeAttr);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("file.xml"));
            transformer.transform(source, result);

        } catch (ParserConfigurationException | TransformerException pce) {
            pce.printStackTrace();
        }
    }

    public List<Product> loadProducts(){

        List<Product> loadedList = new ArrayList<>();
        Product pro;

        try {

            File fXmlFile = new File("file.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            NodeList nList = doc.getElementsByTagName("Product");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String name = eElement.getAttribute("name");
                    int price = Integer.parseInt(eElement.getAttribute("price"));
                    int size = Integer.parseInt(eElement.getAttribute("size"));

                    if (eElement.getAttribute("type").equals("book")) {
                        pro = new BookProduct(name, price, size);
                    }
                    else {
                        pro = new CDProduct(name, price, size);
                    }
                    loadedList.add(pro);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < listOfProducts.size() ; i++) {
            loadedList.add(listOfProducts.get(i));
        }
        //loadedList.addAll(listOfProducts); alternative for the for loop
        return loadedList;
    }
}