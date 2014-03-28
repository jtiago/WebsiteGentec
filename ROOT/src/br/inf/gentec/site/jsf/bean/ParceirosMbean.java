package br.inf.gentec.site.jsf.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.inf.gentec.site.jsf.facade.ParceirosFacade;

@ViewScoped
public class ParceirosMbean implements Serializable {

    private static final long serialVersionUID = 0x9f8c5b12ab21c41fL;
    private ParceirosFacade facade;

    public synchronized ParceirosFacade getFacade() {
        return facade != null ? facade : (facade = new ParceirosFacade());
    }

    public void redireciona(ActionEvent event) {
        try {
            HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
            HttpServletResponse response = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.sendRedirect((new StringBuilder(String.valueOf(request.getContextPath()))).append("/pages/contato.jsf").toString());
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}