package br.inf.gentec.site.jsf.bean;

import br.inf.gentec.site.jsf.facade.CotacaoFacade;
import java.io.Serializable;
import javax.faces.event.ActionEvent;

public class CotacaoMbean implements Serializable {

    private static final long serialVersionUID = 0x27b1087fd596b764L;
    private CotacaoFacade facade;
    private boolean rendered;

    public CotacaoMbean() {
        rendered = false;
    }

    public CotacaoFacade getFacade() {
        return facade != null ? facade : (facade = new CotacaoFacade());
    }

    public void salvar(ActionEvent event) {
        getFacade().salvar();
        facade = null;
        rendered = true;
    }

    public boolean isRendered() {
        return rendered;
    }

    public void setRendered(boolean rendered) {
        this.rendered = rendered;
    }
}