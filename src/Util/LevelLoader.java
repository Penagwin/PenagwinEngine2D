package Util;

import org.lwjgl.opengl.Display;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by penagwin on 4/29/14.
 */
public class LevelLoader {
    private static int stage = 1;
    public static ArrayList<Rectangle> ProgresStory() throws ParserConfigurationException, IOException, SAXException {
        ArrayList<Rectangle> rectangles  = new ArrayList<Rectangle>();
        File fXmlFile = new File("Story.yml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("area");

        for (int temp = 0; temp < nList.getLength(); temp++) {

            Node nNode = nList.item(temp);
            Element eElement = (Element) nNode;

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                String[] tmp = eElement.getAttribute("coords").split(",");
                rectangles.add(new Rectangle(Integer.valueOf(tmp[0]), Integer.valueOf(tmp[1]), Integer.valueOf(tmp[2]) - Integer.valueOf(tmp[0]), Integer.valueOf(tmp[3]) - Integer.valueOf(tmp[1])));

            }

        }
        Rectangle leftwall = new Rectangle(-100, 50, 98, Display.getHeight());
        Rectangle rightwall = new Rectangle(Display.getWidth()-1 , 0, 100, Display.getHeight());
        rectangles.add(leftwall);
        rectangles.add(rightwall);
            return rectangles;
    }

    public static int getStage() {
        return stage;
    }

    public static void setStage(int nstage) {
        stage = nstage;
    }


}
