package br.inf.gentec.site.jsf.facade;

import br.inf.gentec.site.dao.ParceirosDAO;
import br.inf.gentec.site.db.ConnectionFactory;
import br.inf.gentec.site.model.Parceiros;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

public class ParceirosFacade implements Serializable {

    private static final long serialVersionUID = 0x2c6ca8f26240802dL;
    private List<Parceiros> listaParceiros;

    public List<Parceiros> getListaParceiros() {
        if(listaParceiros == null) {
            try {
                ConnectionFactory factory = new ConnectionFactory();
                Connection connection = factory.getConnection();
                ParceirosDAO dao = new ParceirosDAO(connection);
                listaParceiros = dao.findAll();
                dao = null;
                connection.close();
                connection = null;
                factory = null;
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return listaParceiros;
    }
}