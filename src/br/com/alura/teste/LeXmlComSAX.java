package br.com.alura.teste;

import br.com.alura.handler.ProdutosHandler;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.FileInputStream;
import java.io.InputStream;

public class LeXmlComSAX {

    public static void main(String[] args) throws Exception {
        XMLReader leitor = XMLReaderFactory.createXMLReader();
        ProdutosHandler logica = new ProdutosHandler();
        leitor.setContentHandler(logica);
        InputStream ips = new FileInputStream("src/vendas.xml");
        InputSource is = new InputSource(ips);
        leitor.parse(is);

        System.out.println(logica.getProdutos());
    }

}
