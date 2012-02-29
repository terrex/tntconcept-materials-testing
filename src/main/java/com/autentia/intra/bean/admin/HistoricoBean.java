package com.autentia.intra.bean.admin;

import com.autentia.intra.bean.BaseBean;
import com.autentia.intra.businessobject.Historial;
import com.autentia.intra.manager.admin.HistorialManager;
import com.autentia.intra.util.FacesUtils;

import java.util.List;

public class HistoricoBean extends BaseBean {
    private static final long serialVersionUID = -1L;

    Integer idObjeto;
    Class klazz;

    public String ver_historico() throws ClassNotFoundException {
        idObjeto = Integer.parseInt(FacesUtils.getRequestParameter("idObjeto"));
        klazz = Class.forName(FacesUtils.getRequestParameter("klazz"));

        return "ver_historico";
    }

    public List<Historial> getHistoricos() {
        return HistorialManager.getDefault().getEntriesFor(this.klazz, this.idObjeto);
    }
}
