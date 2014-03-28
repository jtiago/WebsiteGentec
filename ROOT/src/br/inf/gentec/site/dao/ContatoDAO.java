package br.inf.gentec.site.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import br.inf.gentec.site.model.Contato;

public class ContatoDAO implements Serializable {

    private static final long serialVersionUID = 0xfbf1cd3eea8ea45eL;
    private Connection connection;

    public ContatoDAO(Connection connection) {
        this.connection = connection;
    }

    public void save(Contato contato) {
        String sql = "INSERT INTO contato (nome, telefone, email, cidade, estado, mensagem) VALUES (?,?,?,?,?,?);";
        PreparedStatement stm = null;
        try {
            System.out.println((new StringBuilder("|> ")).append(sql).toString());
            stm = connection.prepareStatement(sql);
            stm.setString(1, contato.getNome());
            stm.setString(2, contato.getTelefone());
            stm.setString(3, contato.getEmail());
            stm.setString(4, contato.getCidade());
            stm.setString(5, contato.getEstado());
            stm.setString(6, contato.getMensagem());
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

    public void delete(Contato contato) {
        String sql = "DELETE FROM contato WHERE id=?;";
        PreparedStatement stm = null;
        try {
            System.out.println((new StringBuilder("|> ")).append(sql).toString());
            stm = connection.prepareStatement(sql);
            stm.setLong(1, contato.getId().longValue());
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

    public List<Contato> findAll() {
        String sql = "SELECT * FROM contato c;";
        PreparedStatement stm = null;
        ResultSet set = null;
        List<Contato> collection = new LinkedList<Contato>();
        try {
            System.out.println((new StringBuilder("|> ")).append(sql).toString());
            stm = connection.prepareStatement(sql);
            set = stm.executeQuery();
            Contato contato = null;
            while(set.next()) {
                contato = new Contato();
                contato.setId(Long.valueOf(set.getLong(1)));
                contato.setNome(set.getString(2));
                contato.setTelefone(set.getString(3));
                contato.setEmail(set.getString(4));
                contato.setCidade(set.getString(5));
                contato.setEstado(set.getString(6));
                contato.setMensagem(set.getString(7));
                collection.add(contato);
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
