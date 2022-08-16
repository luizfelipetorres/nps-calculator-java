package com.myapi.npscalculator.services;

import java.util.List;
import java.util.stream.Collectors;

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

    public boolean isAllZero(NpsCalculatorModel nps) {
        return nps.getDetractorsAmount() == 0 && nps.getPassivesAmount() == 0 && nps.getPromotorsAmount() == 0
                ? true
                : false;
    }

    public List<NpsCalculatorModel> getAll() {
        return npsCalculatorRepository.findAll();
    }

    public List<NpsCalculatorModel> getGreaterThan(int nps) {
        return this
                .getAll()
                .stream()
                .filter(i -> i.getNpsPercentage() > nps)
                .collect(Collectors.toList());
    }

    public List<NpsCalculatorModel> getLessThan(int nps) {
        return this
                .getAll()
                .stream()
                .filter(i -> i.getNpsPercentage() < nps)
                .collect(Collectors.toList());
    }

}
