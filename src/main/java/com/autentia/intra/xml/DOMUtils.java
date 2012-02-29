package com.autentia.intra.xml;

import org.w3c.dom.Node;

/**
 * Funcionalidad para facilitar el manejo de DOM (Document Object Model)
 *
 * @author Carlos García. Autentia
 */
public class DOMUtils {

    /**
     * @param node Nodo sobre el que queremos consultar
     * @param name Nombre de la propiedad
     * @return Devuelve el valor de una propiedad (atributo) o null en caso de que no exista.
     */
    public static String getAttribute(org.w3c.dom.Node node, String name) {
        return DOMUtils.getAttribute(node, name, null);
    }

    /**
     * @param node Nodo sobre el que queremos consultar
     * @param name Nombre de la propiedad
     * @param def  Valor por defecto
     * @return Devuelve el valor de una propiedad (atributo) o 'def' en caso de que no exista.
     */
    public static String getAttribute(org.w3c.dom.Node node, String name, String def) {
        Node attr = node.getAttributes().getNamedItem(name);
        String value = def;
        if (attr != null) {
            value = attr.getNodeValue();
        }
        return value;
    }


}
