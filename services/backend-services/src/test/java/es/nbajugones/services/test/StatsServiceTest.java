/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.services.test;

import es.nbajugones.dto.BoxScoreDTO;
import es.nbajugones.dto.PlayerStatsDTO;
import es.nbajugones.dto.ReduccionDTO;
import es.nbajugones.exception.dbdao.DaoException;
import es.nbajugones.exception.service.ServiceException;
import es.nbajugones.services.StatsService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author iblanco
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:backend-services.xml" })
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
@Transactional
public class StatsServiceTest {

	@Autowired
	StatsService statsService;


	@Test
	public void testSaveStats() throws ServiceException{
		statsService.checkPreviousDay();
	}

	@Test
	public void testGetPlayerStats() throws ServiceException{
		PlayerStatsDTO dto = statsService.getPlayerStats(2894);
		assertTrue(!dto.getCareerStatsDTO().isEmpty());
		assertTrue(dto.getCurrentSeasonStats().isEmpty());
	}

	@Test
	public void testGetReduccionMinutos() throws ServiceException{
		List<ReduccionDTO> result = statsService.getReduccionMinutos();
		assertTrue(!result.isEmpty());
		ReduccionDTO reduccionDTO = statsService.getReduccionMinutos(2488);
		assertNotNull(reduccionDTO);
	}

	//@Test
	public void testGetPlayerStatsLast() throws ServiceException{
		PlayerStatsDTO dto = statsService.getPlayerStats(2872);
		assertTrue(dto.getHoopsAvg() == dto.getHoopsLast5());
		assertTrue(dto.getFppmAvg() == dto.getFppmLast5());
	}

	@Test
	public void redoStats() throws ParseException, ServiceException, DaoException {
		Date date = (new SimpleDateFormat("yyyy-MM-dd")).parse("2016-10-25");
		statsService.checkStats(date);
	}

	@Test
	public void getBoxScore() throws ServiceException {
		BoxScoreDTO boxScoreDTO = statsService.getBoxScore("2016-17", "WAS", "MIN", 6);
		assertTrue(boxScoreDTO.getHomeHoopsScore() != 87.1);
	}

	@Test
	public void saveStats() throws ServiceException {
		statsService.saveStats();
	}
}
