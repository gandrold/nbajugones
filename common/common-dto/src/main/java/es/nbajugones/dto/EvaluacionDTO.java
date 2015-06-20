/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package es.nbajugones.dto;

/**
 *
 * @author Ignacio Blanco
 */
public class EvaluacionDTO {

    private String equipo;
    private String logo;
    private Double conContrato;
    private Double sanciones;
    private Double bonusAct;

	private Double bonusAnt;

	private Double cortes;
	private Double lesionados;
    private double salarios;
    private double limite;
    private long jugadores;
    private long fa;
   
    public EvaluacionDTO() {
		super();
	}

	public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public long getFa() {
        return fa;
    }

    public void setFa(long fa) {
        this.fa = fa;
    }

    public long getJugadores() {
        return jugadores;
    }

    public void setJugadores(long jugadores) {
        this.jugadores = jugadores;
    }

    public double getLimite() {
    	if (limite == 0){
    		limite=61-(sanciones!=null?sanciones:0)+(bonusAnt!=null?bonusAnt:0)+
    				(bonusAct!=null?bonusAct:0);
    	}
        return Math.round((limite) * 100.0) / 100.0;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public double getSalarios() {
    	if (salarios==0){
    		salarios=conContrato-(lesionados!=null?lesionados:0)+(cortes!=null?cortes:0);
    	}
        return Math.round((salarios) * 100.0) / 100.0;
    }

    public void setSalarios(double salarios) {
        this.salarios = salarios;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

	public double getSanciones() {
		return sanciones;
	}

	public void setSanciones(Double sanciones) {
		this.sanciones = sanciones;
	}

	public double getBonusAct() {
		return bonusAct;
	}

	public void setBonusAct(Double bonusAct) {
		this.bonusAct = bonusAct;
	}

	public double getBonusAnt() {
		return bonusAnt;
	}

	public void setBonusAnt(Double bonusAnt) {
		this.bonusAnt = bonusAnt;
	}

	public double getCortes() {
		return cortes;
	}

	public void setCortes(Double cortes) {
		this.cortes = cortes;
	}

	public double getLesionados() {
		return lesionados;
	}

	public void setLesionados(Double lesionados) {
		this.lesionados = lesionados;
	}

	public Double getConContrato() {
		return conContrato;
	}

	public void setConContrato(Double conContrato) {
		this.conContrato = conContrato;
	}

    
}