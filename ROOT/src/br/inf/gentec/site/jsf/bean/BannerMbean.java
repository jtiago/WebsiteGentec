package br.inf.gentec.site.jsf.bean;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.inf.gentec.site.jsf.facade.BannerFacade;

@ViewScoped
public class BannerMbean implements Serializable
{

    private static final long serialVersionUID = 0x18a1bf79e71b13a8L;
    private BannerFacade facade;


    public synchronized BannerFacade getFacade() {
        return facade != null ? facade : (facade = new BannerFacade());
    }

    public void salvar() {
        getFacade().salvar();
        facade = null;
    }

    public void delete(ActionEvent event){
        getFacade().delete();
        facade = null;
    }
}