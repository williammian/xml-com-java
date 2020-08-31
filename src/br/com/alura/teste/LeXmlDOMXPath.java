package br.com.alura.teste;

import br.com.alura.model.Produto;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

public class LeXmlDOMXPath {

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

        //Consulta no XML Document por meio de expressão
        //String exp = "/venda/produtos/produto[2]"; //segundo produto
        //String exp = "/venda/produtos/produto[nome='Livro de XML']";
        String exp = "/venda/produtos/produto[contains(nome, 'Livro')]";
        XPath path = XPathFactory.newInstance().newXPath();
        XPathExpression expression = path.compile(exp);

        NodeList produtos = (NodeList) expression.evaluate(document, XPathConstants.NODESET);

        for(int i = 0; i < produtos.getLength(); i++) {
            Element produto = (Element)produtos.item(i);
            String nome = produto.getElementsByTagName("nome").item(0).getTextContent();
            double preco = Double.parseDouble(produto.getElementsByTagName("preco").item(0).getTextContent());
            Produto prod = new Produto(nome, preco);
            System.out.println(prod);
        }

    }

}
