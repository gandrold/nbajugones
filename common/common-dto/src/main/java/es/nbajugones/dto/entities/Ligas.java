/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.dto.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author iblanco
 */
@Entity
@Table(name = "ligas")
@NamedQueries({
    @NamedQuery(name = "Ligas.findAll", query = "SELECT l FROM Ligas l")})
public class Ligas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "equipos")
    private int equipos;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "limiteSalarial")
    private Double limiteSalarial;
    @Column(name = "cortable")
    private Double cortable;
    @Column(name = "contratoMaximo")
    private Double contratoMaximo;
    @Column(name = "rookies")
    private Integer rookies;
    @Column(name = "copa")
    private Integer copa;
    @Column(name = "maxRoster")
    private Integer maxRoster;
    @Column(name = "lesionados")
    private Integer lesionados;
    @Column(name = "faMovible")
    private Integer faMovible;
    @Column(name = "faTraspasable")
    private Integer faTraspasable;
    @Column(name = "faTraspasado")
    private Integer faTraspasado;
    @Column(name = "reduccionMinutos")
    private Integer reduccionMinutos;

    public Ligas() {
    }

    public Ligas(Integer id) {
        this.id = id;
    }

    public Ligas(Integer id, String nombre, int equipos) {
        this.id = id;
        this.nombre = nombre;
        this.equipos = equipos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEquipos() {
        return equipos;
    }

    public void setEquipos(int equipos) {
        this.equipos = equipos;
    }

    public Double getLimiteSalarial() {
        return limiteSalarial;
    }

    public void setLimiteSalarial(Double limiteSalarial) {
        this.limiteSalarial = limiteSalarial;
    }

    public Double getCortable() {
        return cortable;
    }

    public void setCortable(Double cortable) {
        this.cortable = cortable;
    }

    public Double getContratoMaximo() {
        return contratoMaximo;
    }

    public void setContratoMaximo(Double contratoMaximo) {
        this.contratoMaximo = contratoMaximo;
    }

    public Integer getRookies() {
        return rookies;
    }

    public void setRookies(Integer rookies) {
        this.rookies = rookies;
    }

    public Integer getCopa() {
        return copa;
    }

    public void setCopa(Integer copa) {
        this.copa = copa;
    }

    public Integer getMaxRoster() {
        return maxRoster;
    }

    public void setMaxRoster(Integer maxRoster) {
        this.maxRoster = maxRoster;
    }

    public Integer getLesionados() {
        return lesionados;
    }

    public void setLesionados(Integer lesionados) {
        this.lesionados = lesionados;
    }

    public Integer getFaMovible() {
        return faMovible;
    }

    public void setFaMovible(Integer faMovible) {
        this.faMovible = faMovible;
    }

    public Integer getFaTraspasable() {
        return faTraspasable;
    }

    public void setFaTraspasable(Integer faTraspasable) {
        this.faTraspasable = faTraspasable;
    }

    public Integer getFaTraspasado() {
        return faTraspasado;
    }

    public void setFaTraspasado(Integer faTraspasado) {
        this.faTraspasado = faTraspasado;
    }

    public Integer getReduccionMinutos() {
        return reduccionMinutos;
    }

    public void setReduccionMinutos(Integer reduccionMinutos) {
        this.reduccionMinutos = reduccionMinutos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ligas)) {
            return false;
        }
        Ligas other = (Ligas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "es.nbajugones.dto.entities.Ligas[ id=" + id + " ]";
    }
    
}
