package rssPackage;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.*;
import java.net.URL;

public class Parser {
	
	/**
	 * Abgeänderter StAX-Parser aus der Theorie
	 * Lädt den XML Datenstrom einer Rss url und liest in mit dem XMLStreamReader
	 * Erstellt Sender Objekt und füllt es mit einer Liste von Nachricht Objekten
	 * @param urlString
	 * @return sender, welches das RSS Feed enthält
	 */
	 public Sender parse(String urlString) {
	        Sender sender = null;
	        Nachricht currentNachricht = null;
	        String characters = "";
	        boolean isHeader = true;

	        try {
	            XMLInputFactory factory = XMLInputFactory.newInstance();
	            URL url = new URL(urlString);
	            InputStream in = url.openStream();
	            XMLStreamReader parser = factory.createXMLStreamReader(in);

	            while (parser.hasNext()) {
	                int elementType = parser.next();

	                switch (elementType) {
	                    case XMLStreamConstants.START_ELEMENT:
	                        characters = "";
	                        String localName = parser.getLocalName();

	                        if ("channel".equals(localName)) {
	                            sender = new Sender();
	                        } else if ("item".equals(localName)) {
	                            currentNachricht = new Nachricht();
	                            isHeader = false;
	                        }
	                        break;

	                    case XMLStreamConstants.CHARACTERS:
	                        if (!parser.isWhiteSpace() && parser.getText() != null) {
	                            characters += parser.getText();
	                        }
	                        break;

	                    case XMLStreamConstants.END_ELEMENT:
	                        String endName = parser.getLocalName();

	                        if (sender != null) {
	                            if ("item".equals(endName)) {
	                                sender.addNachricht(currentNachricht);
	                                currentNachricht = null;
	                                isHeader = true;
	                            } else if ("title".equals(endName)) {
	                                if (currentNachricht != null) currentNachricht.setTitel(characters);
	                                else if (isHeader) sender.setTitel(characters);
	                            } else if ("pubDate".equals(endName)) {
	                                if (currentNachricht != null) currentNachricht.setDatum(characters);
	                            } else if ("link".equals(endName)) {
	                                if (currentNachricht != null) currentNachricht.setLink(characters);
	                            }
	                        }
	                        characters = "";
	                        break;
	                        
	                    case XMLStreamConstants.END_DOCUMENT:
	                        parser.close();
	                        break;
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return sender;
	    }
}
