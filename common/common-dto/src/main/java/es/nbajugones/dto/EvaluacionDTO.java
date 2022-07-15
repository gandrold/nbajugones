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
    private String nombre;
    private String logo;
	private String propietario;
    private Double concontrato;
    private Double sanciones;
    private Double bonusact;

	private Double bonusant;

	private Double cortes;
	private Double lesionados;
    private double salarios;
    private double limite;
    private int jugadores;
    private int fa;
   
    public EvaluacionDTO() {
		super();
	}

	public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public int getFa() {
        return fa;
    }

    public void setFa(int fa) {
        this.fa = fa;
    }

    public int getJugadores() {
        return jugadores;
    }

    public void setJugadores(int jugadores) {
        this.jugadores = jugadores;
    }

    public double getLimite() {
    	if (limite == 0){
    		limite=61-(sanciones!=null?sanciones:0)+(bonusant !=null? bonusant :0)+
    				(bonusact !=null? bonusact :0);
    	}
        return Math.round((limite) * 100.0) / 100.0;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public double getSalarios() {
        return Math.round((salarios) * 100.0) / 100.0;
    }
    
    public double getSalaryCap(){
    	double cap=salarios-(lesionados!=null?lesionados:0)+(cortes!=null?cortes:0);
        return Math.round((cap) * 100.0) / 100.0;
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

	public Double getBonusact() {
		return bonusact;
	}

	public void setBonusact(Double bonusact) {
		this.bonusact = bonusact;
	}

	public Double getBonusant() {
		return bonusant;
	}

	public void setBonusant(Double bonusant) {
		this.bonusant = bonusant;
	}

	public Double getCortes() {
		return cortes;
	}

	public void setCortes(Double cortes) {
		this.cortes = cortes;
	}

	public Double getLesionados() {
		return lesionados;
	}

	public void setLesionados(Double lesionados) {
		this.lesionados = lesionados;
	}

	public Double getConcontrato() {
		return concontrato;
	}

	public void setConcontrato(Double concontrato) {
		this.concontrato = concontrato;
	}

	public double getDisponible(){
		return Math.round((getLimite() - getSalaryCap()) * 100.0) / 100.0;
	}
	
	public int getTotalJugadores(){
		return jugadores-fa;
	}
	
	public boolean getWarning(){
		if (getDisponible()<0){
			return true;
		}
		if (14 < (jugadores-fa) ){
			return true;
		}
		return false;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPropietario() {
		return propietario;
	}

	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}
}
