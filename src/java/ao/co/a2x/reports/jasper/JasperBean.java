/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.co.a2x.reports.jasper;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRPptxExporter;
import net.sf.jasperreports.export.SimpleDocxReportConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

/**
 *
 * @author a2x
 */
public class JasperBean implements Serializable {

    public void abrirPDFJasper(String reportPath, String nomeDoFicheiroFinal, Map parametersMap, List lista)
            throws JRException, NullPointerException, IOException, Exception {

        try {
            // Datasource para o relatório criado a partir de uma lista de Beans
            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(lista);

            JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, parametersMap, ds); /// carrega .jasper

            //Construir nome do fichiro final
            nomeDoFicheiroFinal = nomeDoFicheiroFinal.replace(" ", "_");
            Random r = new Random();
            nomeDoFicheiroFinal += r.nextInt(1_000_000_000);

            //Gerando o relatorio
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.setContentType("application/pdf");
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + nomeDoFicheiroFinal + ".pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (JRException ex) {
            throw new JRException("Não foi possivel carregar o template", ex);
        } catch (IOException ex) {
            throw new IOException("Não foi possivel carregar o ficheiro", ex);
        } catch (NullPointerException ex) {
            throw new NullPointerException("Ficheiro Nulo");
        } catch (Exception ex) {
            throw new Exception("Erro ao carregar PDf de pré-visualização", ex);
        }
    }

    public void abrirDOCXJasper(String reportPath, String ficheiroFinal, String nomeDoFicheiroFinal, Map parametersMap, List lista) throws JRException {

        // Datasource para o relatório criado a partir de uma lista de Beans
        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(lista);

        JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, parametersMap, ds); /// carrega .jasper

        JRDocxExporter export = new JRDocxExporter();
        export.setExporterInput(new SimpleExporterInput(jasperPrint));
        export.setExporterOutput(new SimpleOutputStreamExporterOutput(new File(ficheiroFinal)));

        SimpleDocxReportConfiguration config = new SimpleDocxReportConfiguration();
        //config.setFlexibleRowHeight(true); //Set desired configuration

        export.setConfiguration(config);
        export.exportReport();

    }

    private JRAbstractExporter getJRExporter(String format) {
        JRAbstractExporter exporter = null;
        if (format.equals("pdf")) {
            exporter = new JRPdfExporter();
        } else if (format.equals("rtf")) {
            exporter = new JRRtfExporter();
        } else if (format.equals("docx")) {
            exporter = new JRDocxExporter();
        } else if (format.equals("pptx")) {
            exporter = new JRPptxExporter();
        }
        return exporter;
    }

}
