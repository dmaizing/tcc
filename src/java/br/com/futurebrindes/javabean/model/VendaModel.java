package br.com.futurebrindes.javabean.model;

import java.util.Date;


public class VendaModel {
    
    private int venCodigo;
    private int cliCodigo;
    private Date venData;
    private double venValorTotal;

    /**
     * @return the venCodigo
     */
    public int getVenCodigo() {
        return venCodigo;
    }

    /**
     * @param venCodigo the venCodigo to set
     */
    public void setVenCodigo(int venCodigo) {
        this.venCodigo = venCodigo;
    }

    /**
     * @return the cliCodigo
     */
    public int getCliCodigo() {
        return cliCodigo;
    }

    /**
     * @param cliCodigo the cliCodigo to set
     */
    public void setCliCodigo(int cliCodigo) {
        this.cliCodigo = cliCodigo;
    }

    /**
     * @return the venData
     */
    public Date getVenData() {
        return venData;
    }

    /**
     * @param venData the venData to set
     */
    public void setVenData(Date venData) {
        this.venData = venData;
    }

    /**
     * @return the venValorTotal
     */
    public double getVenValorTotal() {
        return venValorTotal;
    }

    /**
     * @param venValorTotal the venValorTotal to set
     */
    public void setVenValorTotal(double venValorTotal) {
        this.venValorTotal = venValorTotal;
    }


    
}
