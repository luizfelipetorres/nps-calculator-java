package com.myapi.npscalculator.dtos;

import javax.validation.constraints.Min;

public class NpsCalculatorDto {

    @Min(value = 0, message = "Valores menores que 0 não são aceitos!")
    private int detractorsAmount;

    @Min(value = 0, message = "Valores menores que 0 não são aceitos!")
    private int passivesAmount;

    @Min(value = 0, message = "Valores menores que 0 não são aceitos!")
    private int promotorsAmount;

    public int getDetractorsAmount() {
        return detractorsAmount;
    }

    public void setDetractorsAmount(int detractorsAmount) {
        this.detractorsAmount = detractorsAmount;
    }

    public int getPassivesAmount() {
        return passivesAmount;
    }

    public void setPassivesAmount(int passivesAmount) {
        this.passivesAmount = passivesAmount;
    }

    public int getPromotorsAmount() {
        return promotorsAmount;
    }

    public void setPromotorsAmount(int promotorsAmount) {
        this.promotorsAmount = promotorsAmount;
    }

}
