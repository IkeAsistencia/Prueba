package com.ike.service.banbajio.getCustomersProspects.dto;

import java.io.Serializable;

public class CardProspects implements Serializable {

    private static final long serialVersionUID = 1L;

    private String tdc;
    private String tdd;

    public CardProspects() {
    }

    public CardProspects(String tdc, String tdd) {
        this.tdc = tdc;
        this.tdd = tdd;
    }

    public String getTdc() {
        return tdc;
    }

    public void setTdc(String tdc) {
        this.tdc = tdc;
    }

    public String getTdd() {
        return tdd;
    }

    public void setTdd(String tdd) {
        this.tdd = tdd;
    }

    @Override
    public String toString() {
        return "InstructionCollectionCard [tdc=" + tdc + ", tdd=" + tdd + "]";
    }

}
