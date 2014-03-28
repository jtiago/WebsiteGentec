package br.inf.gentec.site.jsf.facade;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.inf.gentec.site.dao.ContatoDAO;
import br.inf.gentec.site.dao.EmailNewsletterDAO;
import br.inf.gentec.site.db.ConnectionFactory;
import br.inf.gentec.site.model.Contato;
import br.inf.gentec.site.model.EmailNewsletter;

public class ContatoFacade implements Serializable {

    private static final long serialVersionUID = 0xeff6c73ecf34d291L;
    private Contato contato;
    private Contato contato2;
    private Long key;
    private List<Contato> contatos;

    public Contato getContato() {
        return contato != null ? contato : (contato = new Contato());
    }

    public Contato getContato2() {
        return contato2 != null ? contato2 : (contato2 = new Contato());
    }

    public Long getKey() {
        return key;
    }

    @SuppressWarnings("rawtypes")
	public void setKey(Long key) {
        if(key != null) {
            for(Iterator iterator = getContatos().iterator(); iterator.hasNext();) {
                Contato contato = (Contato)iterator.next();
                if(contato.getId() == key) {
                    contato2 = contato;
                }
            }
        }
        this.key = key;
    }

    public void setContato2(Contato contato2) {
        this.contato2 = contato2;
    }

    public List<Contato> getContatos() {
        if(contatos == null) {
            try {
                ConnectionFactory factory = new ConnectionFactory();
                Connection connection = factory.getConnection();
                ContatoDAO dao = new ContatoDAO(connection);
                contatos = dao.findAll();
                dao = null;
                contato = null;
                contato2 = null;
                connection.close();
                connection = null;
                factory = null;
            }
            catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return contatos;
    }

    public void salvar() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            Connection connection = factory.getConnection();
            ContatoDAO dao = new ContatoDAO(connection);
            dao.save(getContato());
            if(getContato().getNewsletter().booleanValue()) {
                EmailNewsletter newsletter = new EmailNewsletter();
                newsletter.setEmail(getContato().getEmail());
                EmailNewsletterDAO nDao = new EmailNewsletterDAO(connection);
                nDao.save(newsletter);
                newsletter = null;
                nDao = null;
            }
            dao = null;
            contato = null;
            connection.close();
            connection = null;
            factory = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sua mensagem foi enviada com sucesso. Em breve entraremos em contato.", ""));
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            Connection connection = factory.getConnection();
            ContatoDAO dao = new ContatoDAO(connection);
            dao.delete(getContato2());
            dao = null;
            contato2 = null;
            contatos = null;
            connection.close();
            connection = null;
            factory = null;
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}