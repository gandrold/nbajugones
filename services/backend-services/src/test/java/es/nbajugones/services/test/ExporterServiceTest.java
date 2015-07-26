package es.nbajugones.services.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;





import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import es.nbajugones.exception.service.ServiceException;
import es.nbajugones.services.ExporterService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:backend-services.xml" })
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
@Transactional
public class ExporterServiceTest {

	@Autowired
	ExporterService exporterService;
	
	@Test
	public void testTeamHTML() throws ServiceException{
		List<String> teams = new ArrayList<String>();
		teams.add("BRO");
		Map<String, String> export = exporterService.generateTeamHTML(teams);
		Assert.assertTrue(!export.isEmpty());
	}
	
	@Test
	public void testFAHTML() throws ServiceException{
	    String export = exporterService.generateFAList();
		Assert.assertTrue(!export.isEmpty());
	}
	
	@Test
	public void testAllHTML() throws ServiceException{
		String export = exporterService.generateAllList();
		Assert.assertTrue(!export.isEmpty());
	}
	
	@Test
	public void testDerechos() throws ServiceException{
		String export = exporterService.generateDerechos();
		Assert.assertTrue(!export.isEmpty());
	}
	
	@Test
	public void testRondas() throws ServiceException{
		String export = exporterService.generateRondas();
		Assert.assertTrue(!export.isEmpty());
	}
	
	@Test
	public void testDraft() throws ServiceException{
		String export = exporterService.generateDraft(2015);
		Assert.assertTrue(!export.isEmpty());
	}
	
	@Test
	public void testTeamUploadFTP() throws ServiceException{
		String testTeam = "BRO";
		List<String> teams = new ArrayList<String>();
		teams.add(testTeam);
		Map<String, String> export = exporterService.generateTeamHTML(teams);
		Assert.assertTrue(!export.isEmpty());
		String fileContent = export.get(testTeam);
		exporterService.sendContentToFTP(fileContent, "testBoots");
	}
	
}
