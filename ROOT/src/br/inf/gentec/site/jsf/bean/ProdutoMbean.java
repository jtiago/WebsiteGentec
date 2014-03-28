package br.inf.gentec.site.jsf.bean;

import br.inf.gentec.site.jsf.facade.ProdutoFacade;
import java.io.Serializable;
import javax.faces.event.ActionEvent;

public class ProdutoMbean implements Serializable {

    private static final long serialVersionUID = 0x18a1bf79e71b13a8L;
    private ProdutoFacade facade;

    public synchronized ProdutoFacade getFacade() {
        return facade != null ? facade : (facade = new ProdutoFacade());
    }

    public void salvar() {
        getFacade().salvar();
        facade = null;
    }

    public void delete(ActionEvent event) {
        getFacade().delete();
        facade = null;
    }
}