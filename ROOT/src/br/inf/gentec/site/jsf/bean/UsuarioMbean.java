package br.inf.gentec.site.jsf.bean;

import br.inf.gentec.site.jsf.facade.UsuarioFacade;
import java.io.Serializable;
import javax.faces.event.ActionEvent;
import javax.faces.event.ComponentSystemEvent;

public class UsuarioMbean implements Serializable {

    private static final long serialVersionUID = 0x18a1bf79e71b13a8L;
    private UsuarioFacade facade;


    public synchronized UsuarioFacade getFacade() {
        return facade != null ? facade : (facade = new UsuarioFacade());
    }

    public void login(ActionEvent event) {
        getFacade().login();
        facade = null;
    }

    public void logout(ComponentSystemEvent event) {
        getFacade().logout();
        facade = null;
    }

    public void salvar(ActionEvent event) {
        getFacade().salvar();
        facade = null;
    }

    public void delete(ActionEvent event) {
        getFacade().delete();
        facade = null;
    }

    public void alterar(ActionEvent event) {
        getFacade().alterar();
        facade = null;
    }
}