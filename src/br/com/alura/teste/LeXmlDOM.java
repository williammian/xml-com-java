package br.com.alura.teste;

import br.com.alura.model.Produto;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class LeXmlDOM {

    public static void main(String[] args) throws Exception {
        DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
        fabrica.setValidating(true);
        fabrica.setNamespaceAware(true);
        fabrica.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://www.w3.org/2001/XMLSchema");

        DocumentBuilder builder = fabrica.newDocumentBuilder();
        Document document = builder.parse("src/vendas.xml");

        Element venda = document.getDocumentElement();
        String moeda = venda.getAttribute("moeda");
        System.out.println(moeda);

        NodeList formasDePagamento = document.getElementsByTagName("formaDePagamento");
        Element fdp = (Element)formasDePagamento.item(0);
        String formaDePagamento = fdp.getTextContent();
        System.out.println(formaDePagamento);

        NodeList produtos = document.getElementsByTagName("produto");
        for(int i = 0; i < produtos.getLength(); i++) {
            Element produto = (Element)produtos.item(i);
            String nome = produto.getElementsByTagName("nome").item(0).getTextContent();
            double preco = Double.parseDouble(produto.getElementsByTagName("preco").item(0).getTextContent());
            Produto prod = new Produto(nome, preco);
            System.out.println(prod);
        }

    }

}
