package es.nbajugones.dbdao.data;

import es.nbajugones.dto.entities.Schedule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by iblanco on 17/11/2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:dao-context.xml" })
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
@Transactional
public class ScheduleDAOTest {

	@Autowired
	ScheduleDAO scheduleDAO;

	@Test
	public void getTeamSchedule() throws Exception {
		List<Schedule> schedule = scheduleDAO.getTeamSchedule("BRO", "2016-17");
		assertEquals(82, schedule.size());

	}

}