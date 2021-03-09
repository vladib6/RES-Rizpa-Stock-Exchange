package com.load;

import com.Engine.MainEngine;
import com.Engine.Myexception;
import com.stock.Stock;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;




public class Loadxml {//TODO:change the load to using DOM JAXB

    public MainEngine ParseXml(String filepath) throws JAXBException, Myexception, FileNotFoundException {
        JAXBContext jaxbContext=JAXBContext.newInstance(MainEngine.class);
        Unmarshaller unmarshaller=jaxbContext.createUnmarshaller();
        MainEngine mainEngine=(MainEngine) unmarshaller.unmarshal(new File(filepath));

        for(Stock stock: mainEngine.getStockArray().getArrayList()){//pass allstocks from Stockarray.class to Allstocks.class
            mainEngine.addStock(stock);
        }
        return mainEngine;
    }


//    public MainEngine ParseXml(String filepath) throws IOException, ParserConfigurationException, SAXException {//TODO
//        mainEngine=new MainEngine();
//        SAXParser saxParser= SAXParserFactory.newInstance().newSAXParser();
//        saxParser.parse(new FileInputStream(filepath),handler);
//
//        return this.mainEngine;
//    }

//    final DefaultHandler handler = new DefaultHandler() {
//        boolean stock = false;
//        boolean symbol = false;
//        boolean company_name = false;
//        boolean price = false;
//
//        String _symbol, _company;
//        int _price;
//
//
//        @Override
//        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
//            if (qName.equalsIgnoreCase("rse-symbol")) {
//                symbol = true;
//            }
//            if (qName.equalsIgnoreCase("rse-company-name")) {
//                company_name = true;
//            }
//            if (qName.equalsIgnoreCase("rse-price")) {
//                price = true;
//            }
//            if (qName.equalsIgnoreCase("rse-stock")) {
//                stock = true;
//            }
//        }
//
//        public void endElement(String uri, String localName, String qName) throws SAXException {
//            if (qName.equalsIgnoreCase("rse-stock")) {
//                if (stock) {
//                    try {
//                        mainEngine.addStock(new Stock(_symbol, _company, _price));
//                        stock = false;
//                    } catch (Myexception myexception) {
//                        throw new SAXException(myexception.toString());
//                    }
//                }
//            }
//        }
//
//        public void characters(char[] ch, int start, int length) throws SAXException {
//            if(symbol){
//                _symbol=new String(ch,start,length);
//                symbol=false;
//            }
//            if(company_name){
//                _company=new String(ch,start,length);
//                company_name=false;
//            }
//            if(price){
//                _price=Integer.parseInt(new String(ch,start,length));
//                price=false;
//            }
//        }
//
//
//    };

}
