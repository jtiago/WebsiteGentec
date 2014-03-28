package br.inf.gentec.site.model;

import java.io.Serializable;
import org.primefaces.model.UploadedFile;

public class Produto
    implements Serializable
{

    private static final long serialVersionUID = 0xc06207650fcc6aeL;
    private Long id;
    private String titulo;
    private String imagem;
    private String texto;
    private UploadedFile uploadedFile;

    public Produto()
    {
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getTitulo()
    {
        return titulo;
    }

    public void setTitulo(String titulo)
    {
        this.titulo = titulo;
    }

    public String getImagem()
    {
        return imagem;
    }

    public void setImagem(String imagem)
    {
        this.imagem = imagem;
    }

    public String getTexto()
    {
        return texto;
    }

    public void setTexto(String texto)
    {
        this.texto = texto;
    }

    public UploadedFile getUploadedFile()
    {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile)
    {
        this.uploadedFile = uploadedFile;
    }
}
