package br.com.datadev.jaspertojrxml.util;

import java.io.File;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlWriter;

/**
 *
 * @author Fabrício
 */
public class Relatorio {

    private final File origem;
    private final String destino;

    public Relatorio(File origem, String destino) {
        this.origem = origem;
        this.destino = destino;
    }

    /**
     * Carrega o arquivo .jasper e gera o .jrxml
     * @throws JRException
     */
    public void getJrxml() throws JRException {
        JasperReport report = (JasperReport) JRLoader.loadObject(origem);
        JRXmlWriter.writeReport(report, destino, "UTF-8");
    }
}
