package desmoj.extensions.report;

import java.util.ArrayList;
import java.util.HashMap;

import desmoj.core.report.Message;
import desmoj.core.report.OutputTypeEndToExport;
import desmoj.core.report.Reporter;
import desmoj.core.report.TraceNote;
import desmoj.extensions.experimentation.ui.SetPdfPassword;
import desmoj.extensions.experimentation.util.FileUtil;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;

/**
 * Use this class to create PDF formatted Simulation Trace Output.
 * 
 * @version DESMO-J, Ver. 2.5.1d copyright (c) 2015
 * @date 30.03.2011
 * @author Xiufeng Li, Binhua Han, Rene Wecker
 */
public class PdfTraceOutput implements OutputTypeEndToExport
{
	/** CollectionDataSource */
	private ArrayList<HashMap<String, String>> al;

	/** Create a new PdfTraceOutput class */
	public PdfTraceOutput()
	{
		al = new ArrayList<HashMap<String, String>>();
	}

	/**
	 * Implement this method to define the behaviour of the messagereceiver when
	 * messages are sent to it.
	 * 
	 * @param m
	 *            Message : The message sent to the messagereceiver
	 */
	public void receive(Message m)
	{

		// check parameters
		if (m == null)
			return; // again nulls
		if (!(m instanceof TraceNote))
			return; // got wrong message
		TraceNote tmp = (TraceNote) m; // cast and buffer for easier access

		// For Jasper-Reports

		HashMap<String, String> hm = new HashMap<String, String>();

		hm.put("model", tmp.getModelName());
		hm.put("time", tmp.getTime());
		hm.put("event", tmp.getEvent());
		hm.put("entity", tmp.getEntity());
		hm.put("action", tmp.getDescription());
		al.add(hm);
	}

	/**
	 * Implement this method to define the behaviour of the messagereceiver when
	 * reporters are sent to it.
	 * 
	 * @param r
	 *            Reporter : The reporter sent to the messagereceiver
	 */
	public void receive(Reporter r)
	{
	}

	/**
	 * opens a new file for writting the output
	 * 
	 * @param pathname
	 *            String: path to write in
	 * @param name
	 *            String: name of the file
	 */
	public void open(String pathname, String name)
	{
	}

	/**
	 * Closes the file
	 */
	public void close()
	{
	}

	/***************************************************************************
	 * deliever the file appendix used for the specific format (e.g. html, txt)
	 **************************************************************************/
	public String getAppendix()
	{
		return ".pdf";
	}

	/**
	 * Export a new few file for the writting output.
	 * 
	 * @param pathname
	 *            String: path to write in
	 * @param filename
	 *            String: name of the file
	 */
	public void export(String pathname, String filename)
	{
        String jasperSource = "trace_layout_pdf.jasper";
        String jasperSourcePath = "desmoj/extensions/report/jrxmlFiles/"; 
        String jasperDestination = System.getProperty("user.dir") + "/" + jasperSource;

		try
		{
		    HashMap<String, String> parameter = new HashMap<String, String>();
	        JRMapCollectionDataSource ds = new JRMapCollectionDataSource(al);
	        System.out.print("Exporting Trace Channel as PDF, please wait... ");
	            
	        FileUtil.copy(this.getClass().getClassLoader().getResource(jasperSourcePath + jasperSource), jasperDestination);
	        JasperReport jasperReport = JasperManager.loadReport(jasperDestination);
		    
	        JasperPrint jasperPrint = JasperFillManager.fillReport(
					jasperReport, parameter, ds);
			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
					pathname + "/" + filename + "_trace.pdf");
			if (SetPdfPassword._pw != null)
			{
			exporter.setParameter(JRPdfExporterParameter.IS_ENCRYPTED,
					new Boolean(true));
				exporter.setParameter(JRPdfExporterParameter.USER_PASSWORD,
						SetPdfPassword._pw);
			}
			exporter.exportReport();
			FileUtil.deleteFile(jasperDestination);
			System.out.println("Finished!");

        } catch (Exception e)
        {
            System.out.println("Failure! (Try extracting the contents of the jar file, if not done already.)");
            System.out.println("Error stack trace: ");
            e.printStackTrace();
            FileUtil.deleteFile(jasperDestination);
        }
    }
}