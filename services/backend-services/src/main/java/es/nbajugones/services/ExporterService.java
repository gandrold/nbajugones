package es.nbajugones.services;

import java.io.*;
import java.nio.charset.StandardCharsets;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import es.nbajugones.dto.EquipoDTO;
import es.nbajugones.dto.CopaDTO;
import es.nbajugones.dto.MatchDTO;
import es.nbajugones.dto.PlayoffDTO;
import es.nbajugones.exception.service.ServiceException;

import java.util.Calendar;

public class ExporterService {

	public static final int LAST_DRAFT = 2021;

	Logger logger = LoggerFactory.getLogger(ExporterService.class.getName());

	@Autowired
	EquipoService equipoService;

	@Autowired
	JugadorService jugadorService;

	@Autowired
	DraftService draftService;

	@Autowired
	HistoricoService historicoService;

	@Autowired
	CalendarioService calendarioService;

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

	public String generateWidget() throws ServiceException {
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("faG", jugadorService.getTop5FA("G"));
		values.put("faF", jugadorService.getTop5FA("F"));
		values.put("faC", jugadorService.getTop5FA("C"));
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -1);
		List<MatchDTO> data = calendarioService.getWidgetDay(c.getTime());
		if (!data.isEmpty()) {
			values.put("schedule", data);
		}
		values.put("size", data.size()+3);
		String html = generateTemplate("widget", values);
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

	public String generateRondaCopa(String temporada, int ronda) throws ServiceException {
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("ronda", equipoService.getRondaCopa(temporada, ronda));
		values.put("boxscores", equipoService.getBoxscoresRondaCopa(temporada, ronda));
		values.put("temporada", temporada);
		values.put("titulo", "Ronda "+ronda);
		String html = generateTemplate("copaBoxscores", values);
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
		if (!rondaFinal.isEmpty()) {
			if (rondaFinal.get(0).isCasaGanador()) {
				values.put("ganador", rondaFinal.get(0).getEquipoCasa());
			} else {
				if (rondaFinal.get(0).isFueraGanador()) {
					values.put("ganador", rondaFinal.get(0).getEquipoFuera());
				}
			}
		}
		values.put("temporada", temporada);
		String html = generateTemplate("copa", values);
		return html;
	}

	public String generateHistorico(String temporada) throws ServiceException {
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("rs", historicoService.getResultados(temporada));

		values.put("ronda1", historicoService.getPlayOff(temporada, 1));
		values.put("hasPlayoff", !historicoService.getPlayOff(temporada, 1).isEmpty());
		values.put("ronda2", historicoService.getPlayOff(temporada, 2));
		values.put("semis", historicoService.getPlayOff(temporada, 3));
		List<PlayoffDTO> rondaFinal = historicoService.getPlayOff(temporada, 4);
		values.put("rondaFinal", rondaFinal);
		if (!rondaFinal.isEmpty()) {
			if (rondaFinal.get(0).isGanador1()) {
				values.put("ganador", rondaFinal.get(0).getEquipo1());
			} else {
				if (rondaFinal.get(0).isGanador2()) {
					values.put("ganador", rondaFinal.get(0).getEquipo2());
				}
			}
		}
		values.put("temporada", temporada);
		String html = generateTemplate("historico", values);
		return html;
	}

	public String generateIndex() throws ServiceException {
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("evaluacion", equipoService.evaluar());
		String html = generateTemplate("index", values);
		return html;
	}

	public void export(List<String> teams) throws ServiceException {
		Map<String, String> equipos = generateTeamHTML(teams);
		equipos.put("free_agents", generateFAList());
		equipos.put("widget", generateWidget());
		//equipos.put("schedule", generateMatchWidget());
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
			//ftp.connect("home344607320.1and1-data.host");
			//ftp.login("u60560404", "Clander123");
			//int reply = ftp.getReplyCode();
			//if (!FTPReply.isPositiveCompletion(reply)) {
			//	ftp.disconnect();
			//	throw new ServiceException("Error connecting to FTP file ");
			//} else {
				//ftp.enterLocalPassiveMode();
				//ftp.setFileType(FTP.BINARY_FILE_TYPE);
				for (String fileName : files.keySet()) {
					String content = files.get(fileName);
					logger.info("Uploading " + fileName);
					Writer writer = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream("C:\\temp\\Rosters\\"+fileName+".html"), "ISO-8859-1"));
					writer.write(content);
					writer.close();
					//InputStream stream = new ByteArrayInputStream(content.getBytes("ISO-8859-1"));
					//OutputStream outputStream =  ftp.storeFileStream(fileName + ".html");
					//byte[] bytesIn = new byte[4096];
					//int read = 0;
					//while ((read = stream.read(bytesIn)) != -1) {
					//	outputStream.write(bytesIn, 0, read);
					//}
					//stream.close();
					//outputStream.close();
					//boolean completed = ftp.completePendingCommand();
					//if (completed) {
					//	logger.info("File is uploaded successfully.");
					//} else {
					//	logger.info("Error.");
					//}
				}
			//}

		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
		} finally {
			if (ftp.isConnected()){
				try {
					ftp.logout();
					ftp.disconnect();
				} catch (IOException e){
					e.printStackTrace();
				}
			}
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
				logger.info("Uploading " + fileName);
				File f = File.createTempFile(fileName, ".html");
				Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f), "ISO-8859-1"));
				try {
					out.write(content);
				} finally {
					out.close();
				}
				InputStream in = new FileInputStream(f);
				ftp.setFileType(FTP.BINARY_FILE_TYPE);
				boolean done = ftp.storeFile(fileName + ".html", in);
				in.close();
				logger.info(done?"Upload successful":"Error on upload");
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
		} finally {
			if (ftp.isConnected()){
				try {
					ftp.logout();
					ftp.disconnect();
				} catch (IOException e){
					e.printStackTrace();
				}
			}
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
