/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.frontend.controllers;

import es.nbajugones.exception.dbdao.DaoException;
import es.nbajugones.exception.service.ServiceException;
import es.nbajugones.services.StatsService;
import java.text.ParseException;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author iblanco
 */
@Component
@EnableScheduling
public class StatsCron {

	@Autowired
	StatsService statsService;

	Logger logger = LoggerFactory.getLogger(StatsCron.class.getName());

	@Scheduled(fixedDelay = 60000)
	public void saveStats() throws ParseException, DaoException, ServiceException {
		if (statsService.isTodayInSeason()) {
			boolean hasExecuted = statsService.saveStats();
			if (!hasExecuted) {
				logger.info("Trying to get current day");
				hasExecuted = statsService.checkPreviousDay();
				if (!hasExecuted) {
					logger.info("Trying to get past calendar");
					statsService.getPastCalendar();
				}
			}
		}
	}



}
