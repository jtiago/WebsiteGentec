package br.inf.gentec.site.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import br.inf.gentec.site.model.Banner;

public class BannerDAO implements Serializable {

    private static final long serialVersionUID = 0xfbf1cd3eea8ea45eL;
    private Connection connection;

    public BannerDAO(Connection connection) {
        this.connection = connection;
    }

    public void save(Banner banner) {
        String sql;
        PreparedStatement stm;
        sql = "INSERT INTO banner (posicao, imagem, descricao) VALUES (?,?,?);";
        stm = null;
        try {
            System.out.println((new StringBuilder("|> ")).append(sql).toString());
            stm = connection.prepareStatement(sql);
            stm.setInt(1, banner.getPosicao());
            stm.setString(2, banner.getImagem());
            stm.setString(3, banner.getDescricao());
            stm.executeUpdate();
            connection.commit();
        }
        catch(SQLException e) {
            try {
                connection.rollback();
            }
            catch(SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
        	 try {
                 if(stm != null) {
                     stm.close();
                     stm = null;
                 }
             }
             catch(SQLException e) {
                 e.printStackTrace();
             }
        }
    }

    public void delete(Banner banner) {
        String sql;
        PreparedStatement stm;
        sql = "DELETE FROM banner WHERE id=?;";
        stm = null;
        try {
            System.out.println((new StringBuilder("|> ")).append(sql).toString());
            stm = connection.prepareStatement(sql);
            stm.setLong(1, banner.getId().longValue());
            stm.executeUpdate();
            connection.commit();
        }
        catch(SQLException e) {
            try {
                connection.rollback();
            }
            catch(SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
        	 try {
                 if(stm != null) {
                     stm.close();
                     stm = null;
                 }
             }
             catch(SQLException e) {
                 e.printStackTrace();
             }
        }
    }

    public List<Banner> findAll() {
        String sql = "SELECT * FROM banner p ORDER BY posicao ASC;";
        PreparedStatement stm = null;
        ResultSet set = null;
        List<Banner> collection = new LinkedList<Banner>();
        try
        {
            System.out.println((new StringBuilder("|> ")).append(sql).toString());
            stm = connection.prepareStatement(sql);
            set = stm.executeQuery(); 
            Banner banner = null;
            while(set.next()) {
                banner = new Banner();
                banner.setId(Long.valueOf(set.getLong(1)));
                banner.setPosicao(set.getInt(2));
                banner.setImagem(set.getString(3));
                banner.setDescricao(set.getString(4));
                collection.add(banner);
            }
            connection.commit();
        }
        catch(SQLException e) {
            try {
                connection.rollback();
            }
            catch(SQLException e1) {
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
            }
            catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return collection;
    }
}