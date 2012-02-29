package com.autentia.intra.xml;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class UtilitiesXML {


    /**
     * Este m�todo busca un fichero de tipo XML en el classpath crea un objeto
     * de tipo org.w3c.dom.Document.
     *
     * @param fichero: El nombre del fichero a procesar.
     * @return
     * @throws Exception
     */
    public static Document file2Document(String fichero) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        ClassLoader loader = (UtilitiesXML.class).getClassLoader();
        URL urlfichero = loader.getResource(fichero);
        Document XMLDoc = factory.newDocumentBuilder().parse(new InputSource(urlfichero.openStream()));
        return XMLDoc;
    }


    /**
     * Este m�todo convierte un objeto de tipo org.w3c.dom.NodeList a List
     *
     * @param nodes
     * @return
     * @throws Exception
     */
    public static List nodes2String(NodeList nodes) throws Exception {
        List<String> nodeText = new ArrayList<String>();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = (Node) nodes.item(i);
            nodeText.add(normalizeName(getTexto(node)));
        }
        return nodeText;
    }


    /**
     * Este m�todo convierte un objeto de tipo org.w3c.dom.NodeList a List
     * Este m�todo solo saca el atributo especificado con nombre
     *
     * @param nodo
     * @return
     * @throws Exception
     */
    public static List printAttribute(String nombre, NodeList nodes) throws Exception {
        List<String> nodeText = new ArrayList<String>();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = (Node) nodes.item(i);
            nodeText.add(normalizeName(giveAttributeNode(nombre, node)));
        }
        return nodeText;
    }

    /**
     * Devuelve el texto de un nodo: <tag>TEXTO</tag>
     *
     * @param n
     * @return
     */
    public static String getTexto(Node n) {
        NodeList nl = n.getChildNodes();
        Node act = null;

        for (int i = 0; i < nl.getLength(); i++) {
            act = nl.item(i);
            if (act == null)
                return null;
            if ((act.getNodeType() == Node.CDATA_SECTION_NODE) || (act.getNodeType() == Node.TEXT_NODE))
                return act.getNodeValue();
        }
        return "";
    }

    /**
     * Devuelve el valor del atributo "nombre" de un nodo
     *
     * @param nombre
     * @param nodo
     * @return
     */
    public static String giveAttributeNode(String name, Node node) {
        NamedNodeMap map = node.getAttributes();
        String value = null;
        if (map != null) {
            Node nodoAt = map.getNamedItem(name);
            if (nodoAt != null)
                value = nodoAt.getNodeValue();
        }
        return value;
    }

    /**
     * Este metodo normaliza un texto pasado por parametro
     *
     * @param nombre
     * @return
     */
    public static String normalizeName(String name) {
        return name.replace('.', ' ');
    }


    /**
     * Esta funcion retorna en un List todos los ficheros de la carpeta report
     *
     * @return
     */
    public static List filesFromFolder(String path) {
        File[] filesList = null;
        List list = new ArrayList();
        ClassLoader loader = (UtilitiesXML.class).getClassLoader();
        File f = null;
        try {
            f = new File(loader.getResource(path).toURI());
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (f.isDirectory()) {
            filesList = f.listFiles();
            for (File file : filesList) {
                int i = file.getAbsolutePath().lastIndexOf(".");
                String format = file.getAbsolutePath().substring(i + 1);
                if (file.isFile() && (format.equals("jrxml"))) {
                    list.add(path + File.separator + file.getName());
                }
            }
        }
        return list;
    }

    /**
     * Esta funcion recoge un path y devuelve el nombre del fichero referido sin extension
     *
     * @return
     */
    public static String cleanReport(String path) {
        String pathCleaned = "";

        pathCleaned = path.substring(path.indexOf("Informe"));
        pathCleaned = pathCleaned.replaceFirst(".jrxml", "");
        return pathCleaned;
    }
}
