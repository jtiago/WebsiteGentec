package br.inf.gentec.site.jsf.bean;

import br.inf.gentec.site.jsf.facade.ContatoFacade;
import java.io.Serializable;
import javax.faces.event.ActionEvent;

public class ContatoMbean implements Serializable {

    private static final long serialVersionUID = 0x18a1bf79e71b13a8L;
    private ContatoFacade facade;
    private boolean rendered;

    public ContatoMbean() {
        rendered = false;
    }

    public synchronized ContatoFacade getFacade() {
        return facade != null ? facade : (facade = new ContatoFacade());
    }

    public boolean isRendered() {
        return rendered;
    }

    public void setRendered(boolean rendered) {
        this.rendered = rendered;
    }

    public void salvar(ActionEvent event) {
        getFacade().salvar();
        facade = null;
        rendered = true;
    }

    public void delete(ActionEvent event) {
        getFacade().delete();
        facade = null;
    }
}