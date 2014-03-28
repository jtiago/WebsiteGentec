package br.inf.gentec.site.model;

import java.io.Serializable;

public class EmailNewsletter
    implements Serializable
{

    private static final long serialVersionUID = 0x9e0a6fdd45425deeL;
    private Long id;
    private String email;
    private Boolean ativo;

    public EmailNewsletter()
    {
        ativo = Boolean.valueOf(true);
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public Boolean getAtivo()
    {
        return ativo;
    }

    public void setAtivo(Boolean ativo)
    {
        this.ativo = ativo;
    }
}
