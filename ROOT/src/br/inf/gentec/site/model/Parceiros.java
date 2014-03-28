package br.inf.gentec.site.model;

import java.io.Serializable;

public class Parceiros
    implements Serializable
{

    private static final long serialVersionUID = 0xd77211204c469dcfL;
    private Long id;
    private String descricao;
    private String imagem;
    private boolean ativo;

    public Parceiros()
    {
        ativo = true;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public String getImagem()
    {
        return imagem;
    }

    public void setImagem(String imagem)
    {
        this.imagem = imagem;
    }

    public boolean isAtivo()
    {
        return ativo;
    }

    public void setAtivo(boolean ativo)
    {
        this.ativo = ativo;
    }
}
