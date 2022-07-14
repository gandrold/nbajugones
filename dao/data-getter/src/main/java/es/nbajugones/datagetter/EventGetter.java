/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.datagetter;

import es.nbajugones.datagetter.beans.events.EventsList;
import java.io.IOException;

/**
 *
 * @author iblanco
 */
public class EventGetter extends BasicGetter<EventsList>{

	@Override
	public Class<EventsList> getGetterClass() {
		return EventsList.class;
	}

	public EventsList getEvents(String date, String token) throws IOException{
		return getResultsWithApache("https://erikberg.com/events.json?date=" + date + "&sport=nba", token);
	}

}
