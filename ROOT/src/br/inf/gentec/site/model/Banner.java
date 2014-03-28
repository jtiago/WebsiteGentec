package br.inf.gentec.site.model;

import java.io.Serializable;
import org.primefaces.model.UploadedFile;

public class Banner
    implements Serializable
{

    private static final long serialVersionUID = 0x2f05415de830a1a6L;
    private Long id;
    private int posicao;
    private String imagem;
    private String descricao;
    private UploadedFile uploadedFile;

    public Banner()
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

    public int getPosicao()
    {
        return posicao;
    }

    public void setPosicao(int posicao)
    {
        this.posicao = posicao;
    }

    public String getImagem()
    {
        return imagem;
    }

    public void setImagem(String imagem)
    {
        this.imagem = imagem;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
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