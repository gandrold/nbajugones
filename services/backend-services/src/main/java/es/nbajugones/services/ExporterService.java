package es.nbajugones.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.apache.velocity.tools.generic.DateTool;
import org.apache.velocity.tools.generic.NumberTool;
import org.springframework.beans.factory.annotation.Autowired;

import es.nbajugones.dto.EquipoDTO;
import es.nbajugones.dto.CopaDTO;
import es.nbajugones.exception.service.ServiceException;

public class ExporterService {

	public static final int LAST_DRAFT = 2015;

	@Autowired
	EquipoService equipoService;

	@Autowired
	JugadorService jugadorService;

	@Autowired
	DraftService draftService;

	FTPClient ftp;

	public Map<String, String> generateTeamHTML(List<String> teams) throws ServiceException {
		Map<String, String> results = new HashMap<String, String>();
		for (String id : teams) {
			EquipoDTO equipo = equipoService.getEquipo(id);
			Map<String, Object> values = new HashMap<String, Object>();
			values.put("equipo", equipo);
			values.put("evaluacion", equipoService.evaluar(id));
			String html = generateTemplate("roster", values);
			results.put(id, html);
		}
		return results;
	}

	public String generateFAList() throws ServiceException {
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("fa", jugadorService.getAllFA());
		String html = generateTemplate("fa", values);
		return html;

	}

	public String generateAllList() throws ServiceException {
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("jugadores", jugadorService.getAll());
		String html = generateTemplate("todos", values);
		return html;
	}

	public String generateDerechos() throws ServiceException {
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("derechos", jugadorService.getDerechos());
		String html = generateTemplate("derechos", values);
		return html;
	}

	public String generateRondas() throws ServiceException {
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("rondas", equipoService.getRondas());
		String html = generateTemplate("rondas", values);
		return html;
	}

	public String generateDraft(int y) throws ServiceException {
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("primeraRonda", draftService.getDraft(y, 1));
		values.put("segundaRonda", draftService.getDraft(y, 2));
		values.put("ano", y);
		String html = generateTemplate("draft", values);
		return html;
	}
	
	public String generateCopa(String temporada) throws ServiceException {
		Map<String, Object> values = new HashMap<String, Object>();
		List<CopaDTO> ronda1 = equipoService.getRondaCopa(temporada, 1);
        values.put("ronda1", ronda1);
		values.put("ronda2", equipoService.getRondaCopa(temporada, 2));
		values.put("cuartos", equipoService.getRondaCopa(temporada, 3));
		values.put("semi", equipoService.getRondaCopa(temporada, 4));
		List<CopaDTO> rondaFinal = equipoService.getRondaCopa(temporada, 5);
		values.put("rondaFinal", rondaFinal);
		if (!rondaFinal.isEmpty()){
			if (rondaFinal.get(0).isCasaGanador()){
				values.put("ganador", rondaFinal.get(0).getEquipoCasa());
			} else {
				if (rondaFinal.get(0).isFueraGanador()){
				values.put("ganador", rondaFinal.get(0).getEquipoFuera());
			}
			}
		}	
		values.put("temporada", temporada);
		String html = generateTemplate("copa", values);
		return html;
	}
	
	public String generateIndex() throws ServiceException{
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("evaluacion", equipoService.evaluar());
		String html = generateTemplate("index", values);
		return html;
	}

	public void export(List<String> teams) throws ServiceException {
		Map<String, String> equipos = generateTeamHTML(teams);
		equipos.put("free_agents", generateFAList());
		equipos.put("todos", generateAllList());
		equipos.put("derechos", generateDerechos());
		equipos.put("rondas", generateRondas());
		equipos.put("index", generateIndex());
		sendMapToFTP(equipos);
	}

	public void exportOld(List<String> teams) throws ServiceException {
		Map<String, String> equipos = generateTeamHTML(teams);
		equipos.put(generateFAList(), "free_agents");
		equipos.put(generateAllList(), "todos");
		equipos.put(generateDerechos(), "derechos");
		equipos.put(generateRondas(), "rondas");
		for (String id : equipos.keySet()) {
			sendContentToFTP(equipos.get(id), id);
		}
		sendContentToFTP(generateFAList(), "free_agents");
		sendContentToFTP(generateAllList(), "todos");
		sendContentToFTP(generateDerechos(), "derechos");
		sendContentToFTP(generateRondas(), "rondas");
		for (int i = LAST_DRAFT; i >= 2005; i--) {
			sendContentToFTP(generateDraft(i), "draft" + i);
		}
	}

	public void sendMapToFTP(Map<String, String> files) throws ServiceException {
		ftp = new FTPClient();
		try {
			ftp.connect("217.160.225.125");
			ftp.login("u60560404", "Clander123");
			int reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				throw new ServiceException("Error connecting to FTP file ");
			} else {
				for (String fileName : files.keySet()) {
					String content = files.get(fileName);
					System.out.println("Uploading " + fileName);
					File f = new File("C:\\temp\\roster\\"+fileName+".html");
					Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f), "ISO-8859-1"));
					try {
						out.write(content);
					} finally {
						out.close();
					}
					//InputStream in = new FileInputStream(f);
					//ftp.setFileType(FTP.BINARY_FILE_TYPE);
					//ftp.storeFile(fileName + ".html", in);
					//in.close();
				}
				ftp.logout();
				ftp.disconnect();
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	public void sendContentToFTP(String content, String fileName) throws ServiceException {
		ftp = new FTPClient();
		try {
			ftp.connect("217.160.225.125");
			ftp.login("u60560404", "Clander123");
			int reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				throw new ServiceException("Error uploading to FTP file " + fileName);
			} else {
				System.out.println("Uploading " + fileName);
				File f = File.createTempFile(fileName, ".html");
				Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f), "ISO-8859-1"));
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

	private String generateTemplate(String template, Map<String, Object> values) throws ServiceException {
		Properties properties = new Properties();
		properties.setProperty(RuntimeConstants.EVENTHANDLER_INCLUDE, IncludeRelativePath.class.getName());
		properties.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		properties.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		properties.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS,
				"org.apache.velocity.runtime.log.NullLogSystem");
		VelocityEngine ve = new VelocityEngine();
		ve.init(properties);
		VelocityContext context = new VelocityContext();
		for (String var : values.keySet()) {
			context.put(var, values.get(var));
		}
		List<Integer> years = new ArrayList<Integer>();
		for (int i = LAST_DRAFT; i >= 2005; i--) {
			years.add(i);
		}
		context.put("combo", equipoService.getEquipos());
		context.put("numberTool", new NumberTool());
		context.put("dateTool", new DateTool());
		context.put("years", years);
		Template t = ve.getTemplate("/templates/" + template + ".vm");
		StringWriter writer = new StringWriter();
		t.merge(context, writer);
		return writer.toString();
	}
}
