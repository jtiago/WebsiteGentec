package br.inf.gentec.site.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import br.inf.gentec.site.model.Parceiros;

public class ParceirosDAO implements Serializable {

    private static final long serialVersionUID = 0xc63a0a4ef055531eL;
    private Connection connection;

    public ParceirosDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Parceiros> findAll() {
        String sql = "SELECT * FROM parceiros p WHERE ativo = ?;";
        PreparedStatement stm = null;
        ResultSet set = null;
        List<Parceiros> collection = new LinkedList<Parceiros>();
        try {
            System.out.println((new StringBuilder("|> ")).append(sql).toString());
            stm = connection.prepareStatement(sql);
            stm.setBoolean(1, true);
            set = stm.executeQuery();
            Parceiros parceiros = null;
            while(set.next()) {
                parceiros = new Parceiros();
                parceiros.setId(Long.valueOf(set.getLong(1)));
                parceiros.setDescricao(set.getString(2));
                parceiros.setImagem(set.getString(3));
                collection.add(parceiros);
            }
            connection.commit();
        } catch(SQLException e) {
            try {
                connection.rollback();
            } catch(SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
        	try {
                if(set != null) {
                    set.close();
                    set = null;
                }
                if(stm != null) {
                    stm.close();
                    stm = null;
                }
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return collection;
    }
}
