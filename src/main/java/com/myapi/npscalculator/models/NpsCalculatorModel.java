package com.myapi.npscalculator.models;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_NPS")
public class NpsCalculatorModel {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private int detractorsAmount;

    @Column(nullable = false)
    private int passivesAmount;

    @Column(nullable = false)
    private int promotorsAmount;

    @Column(nullable = true)
    private Integer personalGoal;

    @Column(nullable = false)
    private int total;

    @Column(nullable = false)
    private float detractorsPercentage;

    @Column(nullable = false)
    private float passivesPercentage;

    @Column(nullable = false)
    private float promotorsPercentage;

    @Column(nullable = false)
    private float npsPercentage;

    @Column(nullable = true)
    private Boolean isGoalAchived;

    @Column(nullable = true)
    private Integer neededToReachGoal;

    public Integer getPersonalGoal() {
        return personalGoal;
    }

    public void setPersonalGoal(Integer personalGoal) {
        this.personalGoal = personalGoal;
    }

    public Boolean isGoalAchived() {
        return isGoalAchived;
    }

    public void setGoalAchived(Boolean isGoalAchived) {
        this.isGoalAchived = isGoalAchived;
    }

    public Integer getNeededToReachGoal() {
        return neededToReachGoal;
    }

    public void setNeededToReachGoal(Integer neededToReachGoal) {
        this.neededToReachGoal = neededToReachGoal;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public UUID getId() {
        return id;
    }

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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public float getDetractorsPercentage() {
        return detractorsPercentage;
    }

    public void setDetractorsPercentage(float detractorsPercentage) {
        this.detractorsPercentage = detractorsPercentage;
    }

    public float getPassivesPercentage() {
        return passivesPercentage;
    }

    public void setPassivesPercentage(float passivesPercentage) {
        this.passivesPercentage = passivesPercentage;
    }

    public float getPromotorsPercentage() {
        return promotorsPercentage;
    }

    public void setPromotorsPercentage(float promotorsPercentage) {
        this.promotorsPercentage = promotorsPercentage;
    }

    public float getNpsPercentage() {
        return npsPercentage;
    }

    public void setNpsPercentage(float npsPercentage) {
        this.npsPercentage = npsPercentage;
    }

}
