package com.myapi.npscalculator.services;

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

}
