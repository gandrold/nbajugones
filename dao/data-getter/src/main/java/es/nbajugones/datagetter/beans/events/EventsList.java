/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.datagetter.beans.events;

import java.util.Date;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author iblanco
 */
public class EventsList {

	@JsonProperty("events_date")
	private Date eventsDate;
	@JsonProperty("count")
	private int count;
	@JsonProperty("event")
	private List<Event> events;

	public Date getEventsDate() {
		return eventsDate;
	}

	public void setEventsDate(Date eventsDate) {
		this.eventsDate = eventsDate;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	


}
