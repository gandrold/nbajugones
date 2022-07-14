/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.dto.entities;

import es.nbajugones.dto.entities.pk.ScheduleOrderPK;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author iblanco
 */
@Entity
@Table(name = "schedule_order")
@NamedQueries({
    @NamedQuery(name = "ScheduleOrder.findAll", query = "SELECT s FROM ScheduleOrder s")})
public class ScheduleOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ScheduleOrderPK scheduleOrderPK;

	@Column(name="schedule_id")
	private Integer scheduleId;

    public ScheduleOrder() {
    }

    public ScheduleOrder(ScheduleOrderPK scheduleOrderPK) {
        this.scheduleOrderPK = scheduleOrderPK;
    }

    public ScheduleOrder(int match, String team, String season) {
        this.scheduleOrderPK = new ScheduleOrderPK(match, team, season);
    }

    public ScheduleOrderPK getScheduleOrderPK() {
        return scheduleOrderPK;
    }

    public void setScheduleOrderPK(ScheduleOrderPK scheduleOrderPK) {
        this.scheduleOrderPK = scheduleOrderPK;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (scheduleOrderPK != null ? scheduleOrderPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScheduleOrder)) {
            return false;
        }
        ScheduleOrder other = (ScheduleOrder) object;
        if ((this.scheduleOrderPK == null && other.scheduleOrderPK != null) || (this.scheduleOrderPK != null && !this.scheduleOrderPK.equals(other.scheduleOrderPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "es.nbajugones.dto.entities.ScheduleOrder[ scheduleOrderPK=" + scheduleOrderPK + " ]";
    }
    
}
