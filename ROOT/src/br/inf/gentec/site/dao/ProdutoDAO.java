package br.inf.gentec.site.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import br.inf.gentec.site.model.Produto;

public class ProdutoDAO implements Serializable {

    private static final long serialVersionUID = 0xfbf1cd3eea8ea45eL;
    private Connection connection;

    public ProdutoDAO(Connection connection) {
        this.connection = connection;
    }

    public void save(Produto produto) {
        String sql = "INSERT INTO produto (titulo, imagem, texto) VALUES (?,?,?);";
        PreparedStatement stm = null;
        try {
            System.out.println((new StringBuilder("|> ")).append(sql).toString());
            stm = connection.prepareStatement(sql);
            stm.setString(1, produto.getTitulo());
            stm.setString(2, produto.getImagem());
            stm.setString(3, produto.getTexto());
            stm.executeUpdate();
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
                 if(stm != null) {
                     stm.close();
                     stm = null;
                 }
             } catch(SQLException e) {
                 e.printStackTrace();
             }
        }
    }

    public void delete(Produto produto) {
        String sql = "DELETE FROM produto WHERE id=?;";
        PreparedStatement stm = null;
        try {
            System.out.println((new StringBuilder("|> ")).append(sql).toString());
            stm = connection.prepareStatement(sql);
            stm.setLong(1, produto.getId().longValue());
            stm.executeUpdate();
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
                if(stm != null) {
                    stm.close();
                    stm = null;
                }
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Produto> findAll() {
        String sql = "SELECT * FROM produto p;";
        PreparedStatement stm = null;
        ResultSet set = null;
        List<Produto> collection = new LinkedList<Produto>();
        try {
            System.out.println((new StringBuilder("|> ")).append(sql).toString());
            stm = connection.prepareStatement(sql);
            set = stm.executeQuery();
            Produto produto = null;
            while(set.next()) {
                produto = new Produto();
                produto.setId(Long.valueOf(set.getLong(1)));
                produto.setTitulo(set.getString(2));
                produto.setImagem(set.getString(3));
                produto.setTexto(set.getString(4));
                collection.add(produto);
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
