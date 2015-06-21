package es.nbajugones.services.test;

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
		Map<String, String> export = exporterService.generateTeamHTML("BRO");
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
	public void testTeamUploadFTP() throws ServiceException{
		Map<String, String> export = exporterService.generateTeamHTML("BRO");
		Assert.assertTrue(!export.isEmpty());
		String fileContent = export.get("BRO");
		exporterService.sendContentToFTP(fileContent, "testBoots");
	}
	
}
