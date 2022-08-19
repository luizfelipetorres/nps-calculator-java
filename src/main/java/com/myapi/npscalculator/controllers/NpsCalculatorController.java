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

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*", maxAge = 36000)
@RequestMapping("/nps-calculator")
public class NpsCalculatorController {

    @Autowired
    NpsCalculatorService npsCalculatorService;

    @PostMapping
    @ApiOperation(value = "Enviar um novo cálculo de NPS")
    public ResponseEntity<Object> saveNps(@RequestBody @Valid NpsCalculatorDto npsCalculatorDto) {
        var model = new NpsCalculatorModel();
        BeanUtils.copyProperties(npsCalculatorDto, model);

        if (npsCalculatorService.isAllZero(model))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campos zerados! Consulta inválida.");

        model.setTotal(model.getDetractorsAmount() + model.getPassivesAmount() + model.getPromotorsAmount());
        model.setDetractorsPercentage(model.getDetractorsAmount() / (float) model.getTotal() * 100);
        model.setPassivesPercentage(model.getPassivesAmount() / (float) model.getTotal() * 100);
        model.setPromotorsPercentage(model.getPromotorsAmount() / (float) model.getTotal() * 100);
        model.setNpsPercentage(model.getPromotorsPercentage() - model.getDetractorsPercentage());

        return ResponseEntity.status(HttpStatus.OK).body(npsCalculatorService.save(model));
    }

    @GetMapping
    @ApiOperation(value = "Retornar todas as consultas feitas")
    public ResponseEntity<List<NpsCalculatorModel>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(npsCalculatorService.getAll());
    }

    @GetMapping("/greater-than-{nps}")
    @ApiOperation(value = "Faz uma consulta apenas de valores maiores que {nps}")
    public ResponseEntity<List<NpsCalculatorModel>> getGreaterThan(@PathVariable(value = "nps") int nps) {
        return ResponseEntity.status(HttpStatus.OK).body(npsCalculatorService.getGreaterThan(nps));
    }

    @GetMapping("/less-than-{nps}")
    @ApiOperation(value = "Faz uma consulta apenas de valores menores que {nps}")
    public ResponseEntity<List<NpsCalculatorModel>> getLessThan(@PathVariable(value = "nps") int nps) {
        return ResponseEntity.status(HttpStatus.OK).body(npsCalculatorService.getLessThan(nps));
    }
}
