package com.myapi.npscalculator.dtos;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Data Transfer Object with some validations for NpsCalculatorModel
 */
public class NpsCalculatorDto {

    @Min(value = 0, message = "Valores menores que 0 não são aceitos!")
    private int detractorsAmount;

    @Min(value = 0, message = "Valores menores que 0 não são aceitos!")
    private int passivesAmount;

    @Min(value = 0, message = "Valores menores que 0 não são aceitos!")
    private int promotorsAmount;

    @Min(value = -100, message = "Valores menores que -100 não são aceitos!")
    @Max(value = 100, message = "Valores maiores que 100 não são aceitos!")
    private Integer personalGoal;

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

    public int getPersonalGoal() {
        return personalGoal;
    }

    public void setPersonalGoal(int personalGoal) {
        this.personalGoal = personalGoal;
    }

}
