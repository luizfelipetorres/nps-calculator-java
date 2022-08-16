package com.myapi.npscalculator.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myapi.npscalculator.dtos.NpsCalculatorDto;
import com.myapi.npscalculator.models.NpsCalculatorModel;
import com.myapi.npscalculator.services.NpsCalculatorService;

@RestController
@CrossOrigin(origins = "*", maxAge = 36000)
@RequestMapping("/nps-calculator")
public class NpsCalculatorController {

    @Autowired
    NpsCalculatorService npsCalculatorService;

    @PostMapping
    public ResponseEntity<Object> saveNps(@RequestBody @Valid NpsCalculatorDto npsCalculatorDto) {
        var npsCalculatorModel = new NpsCalculatorModel();
        BeanUtils.copyProperties(npsCalculatorDto, npsCalculatorModel);

        if (npsCalculatorService.isAllZero(npsCalculatorModel))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campos zerados! Consulta inválida.");


        npsCalculatorModel.setTotal(npsCalculatorModel.getDetractorsAmount() + npsCalculatorModel.getPassivesAmount()
                + npsCalculatorModel.getPromotorsAmount());
        npsCalculatorModel
                .setDetractorsPercentage(
                        npsCalculatorModel.getDetractorsAmount() / (float) npsCalculatorModel.getTotal() * 100);
        npsCalculatorModel
                .setPassivesPercentage(
                        npsCalculatorModel.getPassivesAmount() / (float) npsCalculatorModel.getTotal() * 100);
        npsCalculatorModel
                .setPromotorsPercentage(
                        npsCalculatorModel.getPromotorsAmount() / (float) npsCalculatorModel.getTotal() * 100);
        npsCalculatorModel.setNpsPercentage(
                npsCalculatorModel.getPromotorsPercentage() - npsCalculatorModel.getDetractorsPercentage());

        return ResponseEntity.status(HttpStatus.OK).body(npsCalculatorService.save(npsCalculatorModel));
    }

    @GetMapping
    public ResponseEntity<List<NpsCalculatorModel>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(npsCalculatorService.getAll());
    }

    @GetMapping("/greater-than-{nps}")
    public ResponseEntity<List<NpsCalculatorModel>> getGreaterThan(@PathVariable(value = "nps") int nps){

        return ResponseEntity.status(HttpStatus.OK).body(npsCalculatorService.getGreaterThan(nps));
    }

    @GetMapping("/less-than-{nps}")
    public ResponseEntity<List<NpsCalculatorModel>> getLessThan(@PathVariable(value = "nps") int nps){
        return ResponseEntity.status(HttpStatus.OK).body(npsCalculatorService.getLessThan(nps));
    }
}
