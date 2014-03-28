package br.inf.gentec.site.jsf.facade;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.inf.gentec.site.dao.CotacaoDAO;
import br.inf.gentec.site.db.ConnectionFactory;
import br.inf.gentec.site.email.SendEmail;
import br.inf.gentec.site.model.Cotacao;

public class CotacaoFacade implements Serializable {

    private static final long serialVersionUID = 1L;
    private Cotacao cotacao;

    public void salvar() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            Connection connection = factory.getConnection();
            CotacaoDAO dao = new CotacaoDAO(connection);
            dao.save(getCotacao());
            SendEmail.sendPromocao(getCotacao());
            dao = null;
            cotacao = null;
            connection.close();
            connection = null;
            factory = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sua Cota\347\343o foi enviada com sucesso. Em breve entraremos em contato.", ""));
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public Cotacao getCotacao() {
        if(cotacao == null) {
            cotacao = new Cotacao();
        }
        return cotacao;
    }

    public void setCotacao(Cotacao cotacao) {
        this.cotacao = cotacao;
    }
}