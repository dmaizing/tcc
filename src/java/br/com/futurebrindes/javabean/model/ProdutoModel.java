package br.com.futurebrindes.javabean.model;

import java.util.Date;


public class ProdutoModel {
    
    private int proCodigo;
    private int catCodigo;
    private int forCodigo;
    private String proDescricao;
    private double proQtdEstoque;
    private double proPrecoCusto;
    private int proPercLucro;
    private double proPrecoVenda;
    private Date proDataCadastro;
    private Date proDataUltVenda;
    private String proObs;
    
    
    
    
    @Override
    public String toString() {
        return "ProdutoModel{" + "proCodigo=" + proCodigo + ", catCodigo=" + catCodigo + ", forCodigo=" + forCodigo + ", proDescricao=" + proDescricao + ", proQtdEstoque=" + proQtdEstoque + ", proPrecoCusto=" + proPrecoCusto + ", proPercLucro=" + proPercLucro + ", proPrecoVenda=" + proPrecoVenda + ", proDataCadastro=" + proDataCadastro + ", proDataUltVenda=" + proDataUltVenda + ", proObs=" + proObs + '}';
    }

    public int getProCodigo() {
        return proCodigo;
    }

    public void setProCodigo(int proCodigo) {
        this.proCodigo = proCodigo;
    }

    public int getCatCodigo() {
        return catCodigo;
    }

    public void setCatCodigo(int catCodigo) {
        this.catCodigo = catCodigo;
    }

    public int getForCodigo() {
        return forCodigo;
    }

    public void setForCodigo(int forCodigo) {
        this.forCodigo = forCodigo;
    }

    public String getProDescricao() {
        return proDescricao;
    }

    public void setProDescricao(String proDescricao) {
        this.proDescricao = proDescricao;
    }

    public double getProQtdEstoque() {
        return proQtdEstoque;
    }

    public void setProQtdEstoque(double proQtdEstoque) {
        this.proQtdEstoque = proQtdEstoque;
    }

    public double getProPrecoCusto() {
        return proPrecoCusto;
    }

    public void setProPrecoCusto(double proPrecoCusto) {
        this.proPrecoCusto = proPrecoCusto;
    }

    public int getProPercLucro() {
        return proPercLucro;
    }

    public void setProPercLucro(int proPercLucro) {
        this.proPercLucro = proPercLucro;
    }

    public double getProPrecoVenda() {
        return proPrecoVenda;
    }

    public void setProPrecoVenda(double proPrecoVenda) {
        this.proPrecoVenda = proPrecoVenda;
    }

    public Date getProDataCadastro() {
        return proDataCadastro;
    }

    public void setProDataCadastro(Date proDataCadastro) {
        this.proDataCadastro = proDataCadastro;
    }

    public Date getProDataUltVenda() {
        return proDataUltVenda;
    }

    public void setProDataUltVenda(Date proDataUltVenda) {
        this.proDataUltVenda = proDataUltVenda;
    }

    public String getProObs() {
        return proObs;
    }

    public void setProObs(String proObs) {
        this.proObs = proObs;
    }
    
}//classe
