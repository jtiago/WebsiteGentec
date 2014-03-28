package br.inf.gentec.site.jsf.facade;

import br.inf.gentec.site.dao.EmailNewsletterDAO;
import br.inf.gentec.site.db.ConnectionFactory;
import br.inf.gentec.site.model.EmailNewsletter;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

public class EmailNewsletterFacade implements Serializable
{

    private static final long serialVersionUID = 0xeff6c73ecf34d291L;
    private EmailNewsletter newsletter;

    public EmailNewsletter getNewsletter() {
        return newsletter != null ? newsletter : (newsletter = new EmailNewsletter());
    }

    public void assinar() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            Connection connection = factory.getConnection();
            EmailNewsletterDAO dao = new EmailNewsletterDAO(connection);
            dao.save(getNewsletter());
            dao = null;
            newsletter = null;
            connection.close();
            connection = null;
            factory = null;
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}