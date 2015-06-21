package es.nbajugones.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.app.event.implement.IncludeRelativePath;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.tools.generic.DateTool;
import org.apache.velocity.tools.generic.NumberTool;
import org.springframework.beans.factory.annotation.Autowired;

import es.nbajugones.dto.EquipoDTO;
import es.nbajugones.exception.service.ServiceException;

public class ExporterService {

	@Autowired
	EquipoService equipoService;

	@Autowired
	JugadorService jugadorService;

	FTPClient ftp;

	public Map<String, String> generateTeamHTML(String... teams)
			throws ServiceException {
		Map<String, String> results = new HashMap<String, String>();
		for (String id : teams) {
			EquipoDTO equipo = equipoService.getEquipo(id);
			String html = generateTemplate("roster", "equipo", equipo);
			results.put(id, html);
		}
		return results;
	}

	public String generateFAList() throws ServiceException {		
		String html = generateTemplate("fa", "fa", jugadorService.getAllFA());
		return html;
	}
	
	public String generateAllList() throws ServiceException {		
		String html = generateTemplate("todos", "jugadores", jugadorService.getAll());
		return html;
	}
	
	public void export(String... teams) throws ServiceException{
		Map<String, String> equipos = generateTeamHTML(teams);
		for (String id:equipos.keySet()){
			sendContentToFTP(id, equipos.get(id));
		}
		sendContentToFTP(generateFAList(), "fa");
		sendContentToFTP(generateAllList(), "todos");
	}

	public void sendContentToFTP(String content, String fileName)
			throws ServiceException {
		ftp = new FTPClient();
		try {
			ftp.connect("217.160.225.125");
			ftp.login("u60560404", "Clander123");
			int reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				throw new ServiceException("Error uploading to FTP file "
						+ fileName);
			} else {
				File f = File.createTempFile(fileName, ".html");
				Writer out = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(f), "ISO-8859-1"));
				try {
					out.write(content);
				} finally {
					out.close();
				}
				InputStream in = new FileInputStream(f);
				ftp.setFileType(FTP.BINARY_FILE_TYPE);
				ftp.storeFile(fileName + ".html", in);
				in.close();
				ftp.logout();
				ftp.disconnect();
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	private String generateTemplate(String template, String var, Object value)
			throws ServiceException {
		 Properties properties = new Properties();
		 properties.setProperty(RuntimeConstants.EVENTHANDLER_INCLUDE, IncludeRelativePath.class.getName());

		VelocityEngine ve = new VelocityEngine();
		ve.init(properties);
		VelocityContext context = new VelocityContext();
		context.put(var, value);
		context.put("combo", equipoService.getEquipos());
		context.put("numberTool", new NumberTool());
		context.put("dateTool", new DateTool());
		Template t = ve.getTemplate("./src/main/resources/templates/"
				+ template + ".vm");
		StringWriter writer = new StringWriter();
		t.merge(context, writer);
		return writer.toString();
	}
}
