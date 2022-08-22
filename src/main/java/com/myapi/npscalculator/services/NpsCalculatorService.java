package com.myapi.npscalculator.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapi.npscalculator.models.NpsCalculatorModel;
import com.myapi.npscalculator.repositories.NpsCalculatorRepository;

@Service
public class NpsCalculatorService {

    @Autowired
    NpsCalculatorRepository npsCalculatorRepository;

    public NpsCalculatorModel save(NpsCalculatorModel npsCalculatorModel) {
        return npsCalculatorRepository.save(npsCalculatorModel);
    }

    /**
     * Verifies if all values for nps are zero
     * 
     * @param nps NpsCalculatorModel
     * @return True or false
     */
    public boolean isAllZero(NpsCalculatorModel nps) {
        return nps.getDetractorsAmount() == 0 && nps.getPassivesAmount() == 0 && nps.getPromotorsAmount() == 0
                ? true
                : false;
    }

    /**
     * Return a list of all consults in the DB
     * 
     * @return List of NpsCalculatorModel
     */
    public List<NpsCalculatorModel> getAll() {
        return npsCalculatorRepository.findAll();
    }

    /**
     * Returns a list of NpsCalculatorModel that have NPS percentage smaller than
     * param NPS
     * 
     * @param nps NpsCalculatorModel
     * @return A list of NpsCalculatorModel
     */
    public List<NpsCalculatorModel> getGreaterThan(int nps) {
        return this
                .getAll()
                .stream()
                .filter(i -> i.getNpsPercentage() > nps)
                .collect(Collectors.toList());
    }

    /**
     * Returns a list of NpsCalculatorModel that have NPS percentage bigger than
     * param NPS
     * 
     * @param nps NpsCalculatorModel
     * @return A list of NpsCalculatorModel
     */
    public List<NpsCalculatorModel> getLessThan(int nps) {
        return this
                .getAll()
                .stream()
                .filter(i -> i.getNpsPercentage() < nps)
                .collect(Collectors.toList());
    }

    public NpsCalculatorModel calculateGrades(NpsCalculatorModel model) {
        model.setTotal(model.getDetractorsAmount() + model.getPassivesAmount() + model.getPromotorsAmount());
        model.setDetractorsPercentage(model.getDetractorsAmount() / (float) model.getTotal() * 100);
        model.setPassivesPercentage(model.getPassivesAmount() / (float) model.getTotal() * 100);
        model.setPromotorsPercentage(model.getPromotorsAmount() / (float) model.getTotal() * 100);
        model.setNpsPercentage(model.getPromotorsPercentage() - model.getDetractorsPercentage());

        return model;
    }

    public int calculateGoals(NpsCalculatorModel model) {
        NpsCalculatorModel copy = new NpsCalculatorModel();
        int promotorsRemaining = 0;

        BeanUtils.copyProperties(model, copy);

        while (copy.getNpsPercentage() < copy.getPersonalGoal()) {
            copy.setPromotorsAmount(copy.getPromotorsAmount() + 1);
            copy = calculateGrades(copy);
        }

        return copy.getPromotorsAmount() - model.getPromotorsAmount();
    }

    public List<NpsCalculatorModel> getAllWithGoal(){
        return this.getAll()
            .stream()
            .filter(i -> i.getPersonalGoal() != null)
            .collect(Collectors.toList());
    }

}
