package es.nbajugones.dto.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by iblanco on 12/12/2016.
 */
@Entity
@Table(name="tokens")
public class Tokens implements Serializable {

	@Id
	@Column(name="sport")
	private String sport;

	@Column(name="token")
	private String token;

	public String getSport() {
		return sport;
	}

	public void setSport(String sport) {
		this.sport = sport;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
