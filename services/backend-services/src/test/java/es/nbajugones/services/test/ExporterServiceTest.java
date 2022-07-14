package es.nbajugones.services.test;

import java.util.ArrayList;
import java.util.HashMap;
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
		exporterService.sendMapToFTP(export);
	}

	@Test
	public void testRondaCopa() throws ServiceException {
		String html = exporterService.generateCopa("2017-18");
		Map<String, String> map = new HashMap<String, String>();
		map.put("copa1718",html);
		exporterService.sendMapToFTP(map);
	}

	@Test
	public void testFAHTML() throws ServiceException{
	    String export = exporterService.generateFAList();
		Assert.assertTrue(!export.isEmpty());
	}

	@Test
	public void testWidget() throws ServiceException{
	    String export = exporterService.generateWidget();
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

		Map<String, String> export = new HashMap<String, String>();
		String testTeam = "BRO";
		List<String> teams = new ArrayList<String>();
		teams.add(testTeam);
		Map<String, String> t = exporterService.generateTeamHTML(teams);
		Assert.assertTrue(!t.isEmpty());
		String fileContent = t.get(testTeam);
//		export.put("test", fileContent);
		export.put("copa1819", exporterService.generateCopa("2018-19"));
		export.put("copa1718", exporterService.generateCopa("2017-18"));
        export.put("copa1617", exporterService.generateCopa("2016-17"));
		export.put("copa1516", exporterService.generateCopa("2015-16"));
		export.put("copa1415", exporterService.generateCopa("2014-15"));
		export.put("copa1314", exporterService.generateCopa("2013-14"));
		export.put("copa1213", exporterService.generateCopa("2012-13"));
		export.put("copa1112", exporterService.generateCopa("2011-12"));
		export.put("draft2020", exporterService.generateDraft(2020));
		export.put("draft2019", exporterService.generateDraft(2019));
		export.put("draft2018", exporterService.generateDraft(2018));
		export.put("draft2017", exporterService.generateDraft(2017));
		export.put("draft2015", exporterService.generateDraft(2015));
		export.put("draft2014", exporterService.generateDraft(2014));
		export.put("draft2013", exporterService.generateDraft(2013));
		export.put("draft2012", exporterService.generateDraft(2012));
		export.put("draft2011", exporterService.generateDraft(2011));
		export.put("draft2010", exporterService.generateDraft(2010));
		export.put("draft2009", exporterService.generateDraft(2009));
	export.put("draft2008", exporterService.generateDraft(2008));
	export.put("draft2007", exporterService.generateDraft(2007));
		export.put("draft2006", exporterService.generateDraft(2006));
		export.put("draft2005", exporterService.generateDraft(2005));
		export.put("temp2021", exporterService.generateHistorico("2020-21"));
		export.put("temp1920", exporterService.generateHistorico("2019-20"));
		export.put("temp1819", exporterService.generateHistorico("2018-19"));
		export.put("temp1718", exporterService.generateHistorico("2017-18"));
        export.put("temp1617", exporterService.generateHistorico("2016-17"));
	export.put("temp1516", exporterService.generateHistorico("2015-16"));
		export.put("temp1415", exporterService.generateHistorico("2014-15"));
	export.put("temp1314", exporterService.generateHistorico("2013-14"));
	export.put("temp1213", exporterService.generateHistorico("2012-13"));
		export.put("temp1112", exporterService.generateHistorico("2011-12"));
		export.put("temp1011", exporterService.generateHistorico("2010-11"));
		export.put("temp0910", exporterService.generateHistorico("2009-10"));
		export.put("temp0809", exporterService.generateHistorico("2008-09"));
		export.put("temp0708", exporterService.generateHistorico("2007-08"));
		export.put("temp0607", exporterService.generateHistorico("2006-07"));
		export.put("temp0506", exporterService.generateHistorico("2005-06"));
		export.put("temp0405", exporterService.generateHistorico("2004-05"));
		exporterService.sendMapToFTP(export);
	}

}
