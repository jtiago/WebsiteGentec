package br.inf.gentec.site.filter;

import java.io.UnsupportedEncodingException;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PhaseListenerSite implements PhaseListener {

    private static final long serialVersionUID = 0x349e0ed97b57e188L;

    public PhaseListenerSite()
    {
    }

    public void afterPhase(PhaseEvent arg0) {
        try {
            HttpServletRequest request = (HttpServletRequest)arg0.getFacesContext().getExternalContext().getRequest();
            HttpServletResponse response = (HttpServletResponse)arg0.getFacesContext().getExternalContext().getResponse();
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
        } catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void beforePhase(PhaseEvent arg0) {
        try {
            HttpServletRequest request = (HttpServletRequest)arg0.getFacesContext().getExternalContext().getRequest();
            HttpServletResponse response = (HttpServletResponse)arg0.getFacesContext().getExternalContext().getResponse();
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
        } catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }
}