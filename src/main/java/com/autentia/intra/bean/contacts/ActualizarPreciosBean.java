package com.autentia.intra.bean.contacts;

import com.autentia.intra.bean.BaseBean;
import com.autentia.intra.businessobject.*;
import com.autentia.intra.dao.SortCriteria;
import com.autentia.intra.dao.search.EnsayoPrecioSearch;
import com.autentia.intra.dao.search.PautaPrecioSearch;
import com.autentia.intra.manager.contacts.*;
import com.autentia.intra.util.FacesUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.faces.model.SelectItem;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ActualizarPreciosBean extends BaseBean {
    private static final long serialVersionUID = 1L;

    private static final Log log = LogFactory.getLog(ActualizarPreciosBean.class);

    public String run() {
        EnsayoManager ensayoManager = EnsayoManager.getDefault();
        PautaManager pautaManager = PautaManager.getDefault();
        EnsayoPrecioManager ensayoPrecioManager = EnsayoPrecioManager.getDefault();
        PautaPrecioManager pautaPrecioManager = PautaPrecioManager.getDefault();
        int numensayos = 0;
        int numpautas = 0;
        if (getClientes().size() == 0) { //si no se ha elegido cliente, son los generales.
            if (actualizar.contains("ensayos")) {
                for (Ensayo e : ensayoManager.getAllEntities(null, null)) {
                    if (e.getCost() != null) {
                        e.setCost(e.getCost().multiply(this.factor));
                        ensayoManager.updateEntity(e);
                        numensayos++;
                    }
                }
            }
            if (actualizar.contains("pautas")) {
                for (Pauta p : pautaManager.getAllEntities(null, null)) {
                    if (p.getCost() != null) {
                        p.setCost(p.getCost().multiply(this.factor));
                        pautaManager.updateEntity(p);
                        numpautas++;
                    }
                }
            }
        } else { //solo de los clientes elegidos
            for (Organization o : getClientes()) {
                if (actualizar.contains("ensayos")) {
                    EnsayoPrecioSearch ensayoPrecioSearch = new EnsayoPrecioSearch();
                    ensayoPrecioSearch.setClient(o);
                    for (EnsayoPrecio ep : ensayoPrecioManager.getAllEntities(ensayoPrecioSearch, null)) {
                        if (ep.getCost() != null) {
                            ep.setCost(ep.getCost().multiply(this.factor));
                            ensayoPrecioManager.updateEntity(ep);
                            numensayos++;
                        }
                    }
                }
                if (actualizar.contains("pautas")) {
                    PautaPrecioSearch pautaPrecioSearch = new PautaPrecioSearch();
                    pautaPrecioSearch.setClient(o);
                    for (PautaPrecio pp : pautaPrecioManager.getAllEntities(pautaPrecioSearch, null)) {
                        if (pp.getCost() != null) {
                            pp.setCost(pp.getCost().multiply(this.factor));
                            pautaPrecioManager.updateEntity(pp);
                            numpautas++;
                        }
                    }
                }
            }
        }
        FacesUtils.addInfoMessage(null, "actualizarPrecios.successMsg", numensayos, numpautas);
        return null;
    }

    private BigDecimal factor = new BigDecimal(1);

    private List<Organization> clientes = new ArrayList<Organization>();

    public BigDecimal getFactor() {
        return factor;
    }

    public void setFactor(BigDecimal factor) {
        this.factor = factor;
    }

    public List<Organization> getClientes() {
        return clientes;
    }

    public void setClientes(List<Organization> clientes) {
        this.clientes = clientes;
    }

    public List<SelectItem> getTodosClientes() {
        List<SelectItem> ret = new ArrayList<SelectItem>();
        for (Organization o : OrganizationManager.getDefault().getAllEntities(null, new SortCriteria("name"))) {
            if (o.isEsCliente())
                ret.add(new SelectItem(o, o.getNameAcro()));
        }
        return ret;
    }

    private List<String> actualizar = new ArrayList<String>();

    public List<String> getActualizar() {
        return actualizar;
    }

    public void setActualizar(List<String> actualizar) {
        this.actualizar = actualizar;
    }
}