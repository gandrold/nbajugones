/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.datagetter;

import es.nbajugones.datagetter.beans.events.EventsList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author iblanco
 */
public class EventGetterTest {

	EventGetter eventGetter;

	public EventGetterTest() {
	}

	@Before
	public void setUp() {
		eventGetter = new EventGetter();
	}

	@Test
	public void test(){
		try {
			EventsList events = eventGetter.getEvents("20181016", "");
			assertTrue(!events.getEvents().isEmpty());
		} catch (Exception e){
			e.printStackTrace();
			fail();
		}
	}

}
