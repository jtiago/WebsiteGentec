package br.inf.gentec.site.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import br.inf.gentec.site.model.Usuario;
import br.inf.gentec.site.security.CriptografiaBase64;

public class UsuarioDAO implements Serializable {

    private static final long serialVersionUID = 0xfbf1cd3eea8ea45eL;
    private Connection connection;

    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }

    public Usuario login(Usuario usuario) {
        String find = "SELECT * FROM usuarios WHERE usuario=? AND senha=? AND ativo=?;";
        PreparedStatement stm = null;
        ResultSet set = null;
        try {
            System.out.println((new StringBuilder("|> ")).append(find).toString());
            stm = connection.prepareStatement(find);
            stm.setString(1, usuario.getUsuario());
            stm.setString(2, CriptografiaBase64.encrypt(usuario.getSenha()));
            stm.setBoolean(3, true);
            set = stm.executeQuery();
            if(set.next())
            {
                usuario.setSenha(CriptografiaBase64.encrypt(usuario.getSenha()));
                usuario.setAtivo(true);
            } else
            {
                usuario = null;
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
        return usuario;
    }

    public void save(Usuario usuario) {
        String find = "SELECT * FROM usuarios WHERE usuario=?;";
        String sql = "INSERT INTO usuarios (usuario, senha, ativo) VALUES (?,?,?);";
        PreparedStatement stm  = null;
        ResultSet set = null;
        try {
            System.out.println((new StringBuilder("|> ")).append(find).toString());
            stm = connection.prepareStatement(find);
            stm.setString(1, usuario.getUsuario());
            set = stm.executeQuery();
            if(!set.next()) {
                System.out.println((new StringBuilder("|> ")).append(sql).toString());
                stm = connection.prepareStatement(sql);
                stm.setString(1, usuario.getUsuario());
                stm.setString(2, CriptografiaBase64.encrypt(usuario.getSenha()));
                stm.setBoolean(3, usuario.isAtivo());
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
    }

    public void alterar(Usuario usuario) {
        String sql = "UPDATE usuarios SET senha=? WHERE usuario=?;";
        PreparedStatement stm = null;
        try {
            System.out.println((new StringBuilder("|> ")).append(sql).toString());
            stm = connection.prepareStatement(sql);
            stm.setString(1, CriptografiaBase64.encrypt(usuario.getNova()));
            stm.setString(2, usuario.getUsuario());
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

    public void delete(Usuario usuario) {
        String sql = "DELETE FROM usuarios WHERE id=?;";
        PreparedStatement stm = null;
        try {
            System.out.println((new StringBuilder("|> ")).append(sql).toString());
            stm = connection.prepareStatement(sql);
            stm.setLong(1, usuario.getId().longValue());
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

    public List<Usuario> findAll() {
        List<Usuario> list = new LinkedList<Usuario>();
        String find = "SELECT * FROM usuarios;";
        PreparedStatement stm = null;
        ResultSet set = null;
        try {
            System.out.println((new StringBuilder("|> ")).append(find).toString());
            stm = connection.prepareStatement(find);
            set = stm.executeQuery();
            Usuario usuario = null;
            while(set.next()) {
                usuario = new Usuario();
                usuario.setId(Long.valueOf(set.getLong(1)));
                usuario.setUsuario(set.getString(2));
                usuario.setSenha(set.getString(3));
                usuario.setAtivo(set.getBoolean(4));
                list.add(usuario);
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
        return list;
    }
}
