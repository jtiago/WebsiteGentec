package br.inf.gentec.site.model;

import java.io.Serializable;

public class Usuario
    implements Serializable
{

    private static final long serialVersionUID = 0x4b88beccb12c776fL;
    private Long id;
    private String usuario;
    private String senha;
    private String confirmacao;
    private String nova;
    private boolean ativo;

    public Usuario()
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

    public String getUsuario()
    {
        return usuario;
    }

    public void setUsuario(String usuario)
    {
        this.usuario = usuario;
    }

    public String getSenha()
    {
        return senha;
    }

    public void setSenha(String senha)
    {
        this.senha = senha;
    }

    public String getConfirmacao()
    {
        return confirmacao;
    }

    public void setConfirmacao(String confirmacao)
    {
        this.confirmacao = confirmacao;
    }

    public String getNova()
    {
        return nova;
    }

    public void setNova(String nova)
    {
        this.nova = nova;
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
