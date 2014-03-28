package br.inf.gentec.site.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import br.inf.gentec.site.model.Contato;
import br.inf.gentec.site.model.Cotacao;

public class CotacaoDAO implements Serializable {

    private static final long serialVersionUID = 0xfbf1cd3eea8ea45eL;
    private Connection connection;

    public CotacaoDAO(Connection connection) {
        this.connection = connection;
    }

    public void save(Cotacao cotacao) {
        String sql = "INSERT INTO cotacao (cliente, empresa, cnpj, telefone, email, observacoes) VALUES (?,?,?,?,?,?);";
        PreparedStatement stm = null;
        try {
            System.out.println((new StringBuilder("|> ")).append(sql).toString());
            stm = connection.prepareStatement(sql);
            stm.setString(1, cotacao.getNomeCliente());
            stm.setString(2, cotacao.getNomeEmpresa());
            stm.setString(3, cotacao.getNumeroCnpj());
            stm.setString(4, cotacao.getNumeroTelefone());
            stm.setString(5, cotacao.getEmail());
            stm.setString(6, cotacao.getObservacoes());
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

    public void delete(Contato contato)
    {
        String sql = "DELETE FROM cotacao WHERE id=?;";
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
        	try{
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

    public List<Cotacao> findAll() {
        String sql = "SELECT * FROM cotacao c;";
        PreparedStatement stm = null;
        ResultSet set = null;
        List<Cotacao> collection = new LinkedList<Cotacao>();
        try {
            System.out.println((new StringBuilder("|> ")).append(sql).toString());
            stm = connection.prepareStatement(sql);
            set = stm.executeQuery();
            Cotacao  cotacao = null;
            while(set.next()) {
            	cotacao = new Cotacao();
            	cotacao.setId(set.getLong(1));
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
