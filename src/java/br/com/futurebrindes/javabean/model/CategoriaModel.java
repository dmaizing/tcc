/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.futurebrindes.javabean.model;

/**
 *
 * @author davis_9n3g2
 */
public class CategoriaModel {
    
    private int catCodigo;
    private String catDescricao;

    public int getCatCodigo() {
        return catCodigo;
    }

    public void setCatCodigo(int catCodigo) {
        this.catCodigo = catCodigo;
    }

    public String getCatDescricao() {
        return catDescricao;
    }

    public void setCatDescricao(String catDescricao) {
        this.catDescricao = catDescricao;
    }

    @Override
    public String toString() {
        return "CategoriaModel{" + "catCodigo=" + catCodigo + ", catDescricao=" + catDescricao + '}';
    }
    
    
    
}
