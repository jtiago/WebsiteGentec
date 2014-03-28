package br.inf.gentec.site.jsf.facade;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.inf.gentec.site.dao.UsuarioDAO;
import br.inf.gentec.site.db.ConnectionFactory;
import br.inf.gentec.site.model.Usuario;

public class UsuarioFacade implements Serializable {

    private static final long serialVersionUID = 0xeff6c73ecf34d291L;
    private Usuario usuario1;
    private Usuario usuario2;
    private List<Usuario> list;

    public Usuario getUsuario1() {
        return usuario1 != null ? usuario1 : (usuario1 = new Usuario());
    }

    public Usuario getUsuario2() {
        return usuario2 != null ? usuario2 : (usuario2 = new Usuario());
    }

    public void setUsuario2(Usuario usuario2) {
        this.usuario2 = usuario2;
    }

    public List<Usuario> getList() {
        try {
            if(list == null) {
                ConnectionFactory factory = new ConnectionFactory();
                Connection connection = factory.getConnection();
                UsuarioDAO dao = new UsuarioDAO(connection);
                list = dao.findAll();
                connection.close();
                connection = null;
                factory = null;
                dao = null;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void login() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            Connection connection = factory.getConnection();
            UsuarioDAO dao = new UsuarioDAO(connection);
            Usuario usuario = dao.login(getUsuario1());
            if(usuario != null)
            {
                HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
                HttpSession session = request.getSession();
                session.setAttribute("user", usuario);
                session.setAttribute("date", Calendar.getInstance().getTime());
                FacesContext.getCurrentInstance().getExternalContext().redirect((new StringBuilder(String.valueOf(request.getContextPath()))).append("/admx/home.jsf").toString());
            }
            dao = null;
            usuario = null;
            connection.close();
            connection = null;
            factory = null;
        } catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("rawtypes")
	public void logout() {
        try {
            HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
            HttpSession session = request.getSession();
            Enumeration items;
            for(items = session.getAttributeNames(); items.hasMoreElements(); session.removeAttribute((String)items.nextElement())) { }
            items = null;
            session.invalidate();
            session = null;
            FacesContext.getCurrentInstance().getExternalContext().redirect((new StringBuilder(String.valueOf(request.getContextPath()))).append("/pages/login.jsf").toString());
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void salvar() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            Connection connection = factory.getConnection();
            UsuarioDAO dao = new UsuarioDAO(connection);
            dao.save(getUsuario1());
            dao = null;
            usuario1 = null;
            list = null;
            connection.close();
            connection = null;
            factory = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usu\341rio cadastrado com sucesso.", ""));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            Connection connection = factory.getConnection();
            UsuarioDAO dao = new UsuarioDAO(connection);
            dao.delete(getUsuario2());
            dao = null;
            usuario2 = null;
            list = null;
            connection.close();
            connection = null;
            factory = null;
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void alterar() {
        try {
            HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
            HttpSession session = request.getSession();
            Usuario usuario = (Usuario)session.getAttribute("user");
            getUsuario2().setUsuario(usuario.getUsuario());
            ConnectionFactory factory = new ConnectionFactory();
            Connection connection = factory.getConnection();
            UsuarioDAO dao = new UsuarioDAO(connection);
            dao.alterar(getUsuario2());
            dao = null;
            usuario2 = null;
            usuario = null;
            connection.close();
            connection = null;
            factory = null;
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}