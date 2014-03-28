package br.inf.gentec.site.model;

import java.io.Serializable;

public class Contato
    implements Serializable
{

    private static final long serialVersionUID = 0x8069a0f3cbde3e3bL;
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String cidade;
    private String estado;
    private String mensagem;
    private Boolean newsletter;

    public Contato()
    {
        newsletter = Boolean.valueOf(true);
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getTelefone()
    {
        return telefone;
    }

    public void setTelefone(String telefone)
    {
        this.telefone = telefone;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getCidade()
    {
        return cidade;
    }

    public void setCidade(String cidade)
    {
        this.cidade = cidade;
    }

    public String getEstado()
    {
        return estado;
    }

    public void setEstado(String estado)
    {
        this.estado = estado;
    }

    public String getMensagem()
    {
        return mensagem;
    }

    public void setMensagem(String mensagem)
    {
        this.mensagem = mensagem;
    }

    public Boolean getNewsletter()
    {
        return newsletter;
    }

    public void setNewsletter(Boolean newsletter)
    {
        this.newsletter = newsletter;
    }
}