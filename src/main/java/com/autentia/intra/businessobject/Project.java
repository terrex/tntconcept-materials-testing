/* 
 *  TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L.  
 *	Copyright (C) 2007 Autentia Real Bussiness Solution S.L.					   
 *	
 * 	This program is free software; you can redistribute it and/or
 * 	modify it under the terms of the GNU General Public License
 * 	as published by the Free Software Foundation; either version 2
 * 	of the License, or (at your option) any later version.
 *
 * 	This program is distributed in the hope that it will be useful,
 * 	but WITHOUT ANY WARRANTY; without even the implied warranty of
 * 	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * 	GNU General Public License for more details.
 *
 * 	You should have received a copy of the GNU General Public License
 * 	along with this program; if not, write to the Free Software
 * 	Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 * 	Autentia Real Bussiness Solution S.L.
 * 	Tlf: +34 91 675 33 06, +34 655 99 11 72
 * 	Fax: +34 91 656 65 04
 * 	info@autentia.com																
 */

package com.autentia.intra.businessobject;

import com.autentia.intra.dao.ITransferObject;
import com.autentia.intra.dao.SortCriteria;
import com.autentia.intra.dao.hibernate.PautaDAO;
import com.autentia.intra.dao.search.ProjectSearch;
import com.autentia.intra.manager.admin.ProjectManager;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;

/**
 * Transfer object to store Projects
 *
 * @author stajanov code generator
 */
public class Project implements Serializable, ITransferObject {
    public static boolean historial = true;

    private String plantilla = "tit";
    private Integer updatedById;
    private Offer offer;
    private Albaran albaran;
    private Integer tiempoRespuesta;
    private Bill bill;

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Integer getTiempoRespuesta() {
        return tiempoRespuesta;
    }

    public void setTiempoRespuesta(Integer tiempoRespuesta) {
        this.tiempoRespuesta = tiempoRespuesta;
    }

    public Albaran getAlbaran() {
        return albaran;
    }

    public void setAlbaran(Albaran albaran) {
        this.albaran = albaran;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public Integer getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Integer updatedById) {
        this.updatedById = updatedById;
    }

    public String getPlantilla() {
        return plantilla;
    }

    public void setPlantilla(String plantilla) {
        this.plantilla = plantilla;
    }

    public String siguienteRefLab(Organization client) {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMinimumIntegerDigits(2);
        nf.setGroupingUsed(false);
        String anio = nf.format(new Date().getYear() - 100);

        ProjectSearch ps = new ProjectSearch();
        ps.setReferenciaLaboratorio(client.getAcronimo() + "%/" + anio);
        ProjectManager manager = ProjectManager.getDefault();
        List<Project> todos = manager.getAllEntities(ps, new SortCriteria("id", false));
        nf.setMinimumIntegerDigits(4);
        int p = 0;
        for (Project f : todos) {
            if (f.getReferenciaLaboratorio().matches(client.getAcronimo() + "[0-9]+/" + anio)) {
                String x = f.getReferenciaLaboratorio().replace(client.getAcronimo(), "");
                x = x.substring(0, x.indexOf("/"));
                int z = Integer.parseInt(x);
                if (z > p) {
                    p = z;
                }
            }
        }
        return client.getAcronimo() + nf.format(p + 1) + "/" + anio;
    }

    public Project() {
    }

    public Project(Offer o) {
        this.startDate = new Date();
        this.client = o.getOrganization();
        this.referenciaLaboratorio = siguienteRefLab(this.client);
        this.costeAFacturar = new BigDecimal(0);
        this.ensayos = new ArrayList<ProjectEnsayo>();
        int i = 1;
        for (OfferEnsayo oe : o.getEnsayos()) {
            for (int j = 0; j < oe.getUnidades(); j++) {
                ProjectEnsayo pre = new ProjectEnsayo();
                pre.setEnsayo(oe.getEnsayo());
                pre.setCost(oe.getCost() == null ? new BigDecimal(0) : oe.getCost());
                pre.setIdentificacionLab(this.referenciaLaboratorio + "-" + i++);

                pre.setProject(this);
                this.ensayos.add(pre);
                this.costeAFacturar = this.costeAFacturar.add(pre.getCost());
            }
        }


        this.open = true;
        this.billable = true;
        String titulo = o.getTitle();
        String numero = o.getNumber();
        if (!titulo.equals(numero))
            this.name = numero.concat(" ").concat(titulo);
        else
            this.name = numero;
        this.description = o.getDescription();
        this.orderNumber = o.getOrderNumber();

        this.roles = new HashSet<ProjectRole>();
        this.costs = new HashSet<ProjectCost>();
/*
        for (OfferRole role : o.getRoles()) {
            ProjectRole pr = new ProjectRole();
            pr.setName(role.getName());
            pr.setCostPerHour(role.getCostPerHour());
            pr.setExpectedHours(role.getExpectedHours());

            pr.setProject(this);
            this.roles.add(pr);
            this.costeAFacturar = this.costeAFacturar.add(pr.getExpectedHours().multiply(pr.getCostPerHour()));
        }
        for (OfferCost cost : o.getCosts()) {
            ProjectCost pc = new ProjectCost();
            pc.setName(cost.getName());
            pc.setBillable(cost.isBillable());
            pc.setCost(cost.getCost());

            pc.setProject(this);
            this.costs.add(pc);
            if (cost.isBillable())
                this.costeAFacturar = this.costeAFacturar.add(cost.getCost());
        }
*/

        this.offer = o;

    }

    public Project(OfferPauta op) {
        this.especificacion = op.getPauta().getName();
        this.startDate = new Date();
        this.client = op.getOffer().getOrganization();
        this.ensayosSolicitados = op.getPauta().getEnsayosSolicitados();
        this.referenciaCliente = op.getPauta().getReferenciaCliente();
        this.referenciaLaboratorio = siguienteRefLab(this.client);
        this.ensayos = new ArrayList<ProjectEnsayo>();
        int i = 1;
        PautaDAO pautaDAO = PautaDAO.getDefault(); //Lazy collection fetching fixes #51
        op.setPauta((Pauta) pautaDAO.merge(op.getPauta()));
        for (PautaEnsayo pe : op.getPauta().getEnsayos()) {
            ProjectEnsayo pre = new ProjectEnsayo();
            pre.setEnsayo(pe.getEnsayo());
            pre.setCost(pe.getEnsayo().getCost() == null ? new BigDecimal(0) : pe.getEnsayo().getCost());
            pre.setIdentificacionLab(this.referenciaLaboratorio + "-" + i++);
            pre.setProcedimiento(pe.getProcedimiento());
            pre.setRequerimientos(new ArrayList<ProjectEnsayoRequerimiento>());
            if (pe.getRequerimientos() != null && pe.getRequerimientos().size() == 1) {
                pre.setRequerimiento(pe.getRequerimientos().get(0).getRequerimiento());
            } else {
                for (ParametroString ps : pe.getRequerimientos()) {
                    ProjectEnsayoRequerimiento per = new ProjectEnsayoRequerimiento();
                    per.setNombre(ps.getNombre());
                    per.setNombreIngles(ps.getNombreIngles());
                    per.setRequerimiento(ps.getRequerimiento());

                    per.setProjectEnsayo(pre);
                    pre.getRequerimientos().add(per);
                }
            }
            pre.setDimensiones(new ArrayList<ProjectEnsayoDimension>());
            for (PautaEnsayoDimension ps : pe.getDimensiones()) {
                ProjectEnsayoDimension per = new ProjectEnsayoDimension();
                per.setNombre(ps.getNombre());
                per.setValor(ps.getValor());

                per.setProjectEnsayo(pre);
                pre.getDimensiones().add(per);
            }

            pre.setProject(this);
            this.ensayos.add(pre);
        }

        this.open = true;
        this.billable = true;
        String titulo = op.getOffer().getTitle();
        String numero = op.getOffer().getNumber();
        if (!titulo.equals(numero))
            this.name = numero.concat(" ").concat(titulo);
        else
            this.name = numero;
        this.description = op.getOffer().getDescription();
        this.orderNumber = op.getOffer().getOrderNumber();

        this.costeAFacturar = op.getCost();

        this.offer = op.getOffer();

        this.titulo = op.getPauta().getTitulo();
        this.referenciasCliente = new ArrayList<ProjectIdentificacion>();
        for (PautaIdentificacion pi : op.getPauta().getReferenciasCliente()) {
            ProjectIdentificacion pri = new ProjectIdentificacion();
            pri.setName(pi.getName());
            pri.setValor(pi.getValor());
            pri.setProject(this);

            this.referenciasCliente.add(pri);
        }
        this.tiempoRespuesta = op.getPauta().getTiempoRespuesta();
    }

    private String referenciaCliente;
    private String ensayosSolicitados;
    private String estadoDeRecepcion;
    private BigDecimal costeAFacturar;
    private Date realizadoFecha;
    private Date fechaCaducidad;
    private String titulo;
    private List<ProjectIdentificacion> referenciasCliente;

    public List<ProjectIdentificacion> getReferenciasCliente() {
        return referenciasCliente;
    }

    public void setReferenciasCliente(List<ProjectIdentificacion> referenciasCliente) {
        this.referenciasCliente = referenciasCliente;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public Date getRealizadoFecha() {
        return realizadoFecha;
    }

    public void setRealizadoFecha(Date realizadoFecha) {
        this.realizadoFecha = realizadoFecha;
    }

    public BigDecimal getCosteAFacturar() {
        return costeAFacturar;
    }

    public void setCosteAFacturar(BigDecimal costeAFacturar) {
        this.costeAFacturar = costeAFacturar;
    }

    public String getEstadoDeRecepcion() {
        return estadoDeRecepcion;
    }

    public void setEstadoDeRecepcion(String estadoDeRecepcion) {
        this.estadoDeRecepcion = estadoDeRecepcion;
    }

    public String getEnsayosSolicitados() {
        return ensayosSolicitados;
    }

    public void setEnsayosSolicitados(String ensayosSolicitados) {
        this.ensayosSolicitados = ensayosSolicitados;
    }

    public String getReferenciaCliente() {
        return referenciaCliente;
    }

    public void setReferenciaCliente(String referenciaCliente) {
        this.referenciaCliente = referenciaCliente;
    }

    private String referenciaLaboratorio;

    public String getReferenciaLaboratorio() {
        return referenciaLaboratorio;
    }

    public void setReferenciaLaboratorio(String referenciaLaboratorio) {
        this.referenciaLaboratorio = referenciaLaboratorio;
    }

    private String especificacion;

    public String getEspecificacion() {
        return especificacion;
    }

    public void setEspecificacion(String especificacion) {
        this.especificacion = especificacion;
    }

    private String descripcion;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    private Integer permanencia;

    public Integer getPermanencia() {
        return permanencia;
    }

    public void setPermanencia(Integer permanencia) {
        this.permanencia = permanencia;
    }

    private User recibidoPor;

    public User getRecibidoPor() {
        return recibidoPor;
    }

    public void setRecibidoPor(User recibidoPor) {
        this.recibidoPor = recibidoPor;
    }

    private User supervisadoPor;

    public User getSupervisadoPor() {
        return supervisadoPor;
    }

    public void setSupervisadoPor(User supervisadoPor) {
        this.supervisadoPor = supervisadoPor;
    }

    private User realizadoPor;

    public User getRealizadoPor() {
        return realizadoPor;
    }

    public void setRealizadoPor(User realizadoPor) {
        this.realizadoPor = realizadoPor;
    }

    private String observaciones;

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    private Integer numMuestras;

    public Integer getNumMuestras() {
        return numMuestras;
    }

    public void setNumMuestras(Integer numMuestras) {
        this.numMuestras = numMuestras;
    }

    private String motivoDevolucion;

    public String getMotivoDevolucion() {
        return motivoDevolucion;
    }

    public void setMotivoDevolucion(String motivoDevolucion) {
        this.motivoDevolucion = motivoDevolucion;
    }

    /**
     * Serial version field
     */
    private static final long serialVersionUID = -1L;

    /**
     * Test if a project is finished as of now
     *
     * @return true if project is finished
     */
    public boolean isFinished() {
        return !open;
    }

/* project - generated by stajanov (do not edit/delete) */


    private String orderNumber;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    // Fields


    private Integer id;


    private Date startDate;


    private Date endDate;


    private Boolean open = true;


    private String name;


    private String description;


    private Boolean billable;


    private Integer ownerId;


    private Integer departmentId;


    private Date insertDate;


    private Date updateDate;


    private Organization client;


    private Set<ProjectRole> roles;


    private List<ProjectEnsayo> ensayos;


    private Set<ProjectCost> costs;

    // Setters and getters


    public Integer getId() {
        return id;
    }

    private void setId(Integer id) {
        this.id = id;
    }


    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }


    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
        if (endDate != null) {
            this.setOpen(false);//Paloma me lo pide El mié, 07-01-2009 a las 12:19 +0100, Paloma Rodríguez escribió:
            //1/ En un informe cuando se añada fecha de finalización deberá
            //automáticamente ponerse en estado: terminado
        }
    }


    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }


    public String getNameDescriptivo() {
        return ((referenciaLaboratorio == null) ?
                ("") :
                (referenciaLaboratorio + " - ")) + name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Boolean getBillable() {
        return billable;
    }

    public void setBillable(Boolean billable) {
        this.billable = billable;
    }


    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }


    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }


    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }


    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }


    public Organization getClient() {
        return client;
    }

    public void setClient(Organization client) {
        this.client = client;
    }


    public Set<ProjectRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<ProjectRole> roles) {
        this.roles = roles;
    }


    public List<ProjectEnsayo> getEnsayos() {
        return ensayos;
    }

    public void setEnsayos(List<ProjectEnsayo> ensayos) {
        this.ensayos = ensayos;
    }


    public Set<ProjectCost> getCosts() {
        return costs;
    }

    public void setCosts(Set<ProjectCost> costs) {
        this.costs = costs;
    }


    @Override
    public boolean equals(Object that) {
        try {
            if (that == null)
                return false;
            else
                return this.getId().equals(((Project) that).getId());
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public int hashCode() {
        if (this.getId() == null)
            return super.hashCode();
        else
            return this.getId().intValue();
    }

/* project - generated by stajanov (do not edit/delete) */

    public BigDecimal getTotalCost() {
        BigDecimal ret = new BigDecimal(0);

        if (getCosts() != null) {
            for (ProjectCost cost : getCosts()) {
                BigDecimal c = cost.getCost();
                if (c != null) {
                    ret = ret.add(c);
                }
            }
        }
        if (getRoles() != null) {
            for (ProjectRole role : getRoles()) {
                BigDecimal cph = role.getCostPerHour();
                if (cph != null) {
                    BigDecimal eh = new BigDecimal(role.getExpectedHours().doubleValue());
                    ret = ret.add(cph.multiply(eh));
                }
            }
        }
        if (getEnsayos() != null) {
            for (ProjectEnsayo ensayo : getEnsayos()) {
                BigDecimal c = ensayo.getCost();
                if (c != null) {
                    ret = ret.add(c);
                }
            }
        }

        return ret;
    }

    public String getPlantillaLab() {
        return plantillaLab;
    }

    public void setPlantillaLab(String plantillaLab) {
        this.plantillaLab = plantillaLab;
    }

    private String plantillaLab = "boleto_lab";

    ProjectEstado estado = ProjectEstado.PENDIENTE;

    public ProjectEstado getEstado() {
        return this.estado;
    }

    public void setEstado(ProjectEstado projectEstado) {
        this.estado = projectEstado;
    }

    public boolean isTienePEnElNombre() {
        return this.getOffer() != null && this.getName().contains(this.offer.getNumber());
    }

    public String getNameSinPAntes() {
        if (isTienePEnElNombre()) {
            return this.getName().substring(0, this.getName().indexOf(this.offer.getNumber()));
        } else {
            return this.getName();
        }
    }

    public String getNameSinPDespues() {
        if (isTienePEnElNombre()) {
            return this.getName().substring(this.getName().indexOf(this.offer.getNumber()) + this.offer.getNumber().length());
        } else {
            return this.getName();
        }
    }
}
