package br.inf.gentec.site.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.inf.gentec.site.model.EmailNewsletter;

public class EmailNewsletterDAO implements Serializable {

    private static final long serialVersionUID = 0xfbf1cd3eea8ea45eL;
    private Connection connection;

    public EmailNewsletterDAO(Connection connection) {
        this.connection = connection;
    }

    public void save(EmailNewsletter newslatter) {
        String find = "SELECT * FROM newsletter WHERE email=?;";
        String sql = "INSERT INTO newsletter (email, ativo) VALUES (?,?);";
        PreparedStatement stm = null;
        ResultSet set = null;
        try {
            System.out.println((new StringBuilder("|> ")).append(find).toString());
            stm = connection.prepareStatement(find);
            stm.setString(1, newslatter.getEmail());
            set = stm.executeQuery();
            if(!set.next()) {
                stm.clearParameters();
                System.out.println((new StringBuilder("|> ")).append(sql).toString());
                stm = connection.prepareStatement(sql);
                stm.setString(1, newslatter.getEmail());
                stm.setBoolean(2, newslatter.getAtivo().booleanValue());
                stm.executeUpdate();
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
                    stm = null;
                }
                if(stm != null) {
                    stm.close();
                    stm = null;
                }
            } catch(SQLException e)
            {
                e.printStackTrace();
            }
        }
        
    }
}
