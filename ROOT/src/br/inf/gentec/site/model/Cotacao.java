package br.inf.gentec.site.model;

import java.io.Serializable;

public class Cotacao
    implements Serializable
{

    private static final long serialVersionUID = 1L;
    private Long id;
    private String nomeCliente;
    private String nomeEmpresa;
    private String numeroCnpj;
    private String numeroTelefone;
    private String email;
    private String observacoes;

    public Cotacao()
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

    public String getNomeCliente()
    {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente)
    {
        this.nomeCliente = nomeCliente;
    }

    public String getNomeEmpresa()
    {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa)
    {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getNumeroCnpj()
    {
        return numeroCnpj;
    }

    public void setNumeroCnpj(String numeroCnpj)
    {
        this.numeroCnpj = numeroCnpj;
    }

    public String getNumeroTelefone()
    {
        return numeroTelefone;
    }

    public void setNumeroTelefone(String numeroTelefone)
    {
        this.numeroTelefone = numeroTelefone;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getObservacoes()
    {
        return observacoes;
    }

    public void setObservacoes(String observacoes)
    {
        this.observacoes = observacoes;
    }
}