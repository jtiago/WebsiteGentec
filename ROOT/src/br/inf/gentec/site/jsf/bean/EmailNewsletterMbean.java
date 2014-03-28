package br.inf.gentec.site.jsf.bean;

import br.inf.gentec.site.jsf.facade.EmailNewsletterFacade;
import java.io.Serializable;
import javax.faces.event.ActionEvent;

public class EmailNewsletterMbean implements Serializable {

    private static final long serialVersionUID = 0x18a1bf79e71b13a8L;
    private EmailNewsletterFacade facade;

    public synchronized EmailNewsletterFacade getFacade() {
        return facade != null ? facade : (facade = new EmailNewsletterFacade());
    }

    public void assinar(ActionEvent event) {
        getFacade().assinar();
        facade = null;
    }
}