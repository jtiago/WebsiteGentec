package br.inf.gentec.site.jsf.facade;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;

import br.inf.gentec.site.dao.ProdutoDAO;
import br.inf.gentec.site.db.ConnectionFactory;
import br.inf.gentec.site.model.Produto;

public class ProdutoFacade implements Serializable {
    
	private static final long serialVersionUID = 0xeff6c73ecf34d291L;
    private Produto produto;
    private Produto produto2;
    private Long key;
    private List<Produto> produtos;

    public Produto getProduto() {
        return produto != null ? produto : (produto = new Produto());
    }

    public Produto getProduto2() {
        return produto2 != null ? produto2 : (produto2 = new Produto());
    }

    public Long getKey() {
        return key;
    }

    @SuppressWarnings("rawtypes")
	public void setKey(Long key) {
        if(key != null)
        {
            for(Iterator iterator = getProdutos().iterator(); iterator.hasNext();)
            {
                Produto produto = (Produto)iterator.next();
                if(produto.getId() == key)
                {
                    produto2 = produto;
                }
            }

        }
        this.key = key;
    }

    public void setProduto2(Produto produto2) {
        this.produto2 = produto2;
    }

    public List<Produto> getProdutos() {
        if(produtos == null) {
            try {
                ConnectionFactory factory = new ConnectionFactory();
                Connection connection = factory.getConnection();
                ProdutoDAO dao = new ProdutoDAO(connection);
                produtos = dao.findAll();
                dao = null;
                produto = null;
                produto2 = null;
                connection.close();
                connection = null;
                factory = null;
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return produtos;
    }

    @SuppressWarnings("deprecation")
	public void salvar() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            Connection connection = factory.getConnection();
            HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
            String tokem[] = getProduto().getUploadedFile().getFileName().split("\\.");
            String extencao = "jpg";
            if(tokem.length > 0) {
                extencao = tokem[tokem.length - 1];
            }
            String name = (new StringBuilder(String.valueOf(System.currentTimeMillis()))).append(".").append(extencao).toString();
            String path = request.getRealPath((new StringBuilder("/resources/produtos/")).append(name).toString());
            File image = new File(path);
            if(!image.exists()) {
                image.createNewFile();
            }
            IOUtils.copyLarge(getProduto().getUploadedFile().getInputstream(), new FileOutputStream(image));
            getProduto().setImagem(name);
            ProdutoDAO dao = new ProdutoDAO(connection);
            dao.save(getProduto());
            dao = null;
            produto = null;
            connection.close();
            connection = null;
            factory = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Produto cadastro com sucesso.", ""));
        } catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void delete() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            Connection connection = factory.getConnection();
            ProdutoDAO dao = new ProdutoDAO(connection);
            dao.delete(getProduto2());
            dao = null;
            produto = null;
            produto2 = null;
            connection.close();
            connection = null;
            factory = null;
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}