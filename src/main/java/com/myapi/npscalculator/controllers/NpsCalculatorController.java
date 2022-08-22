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

    /**
     * Responsible for calculate NPS percentage and save into the DB.
     * 
     * @param npsCalculatorDto Data transfer object for NpsCalculatorModel.
     * @return A response entity with OK and the values in json or BAD_REQUEST
     */
    @PostMapping
    @ApiOperation(value = "Enviar um novo cálculo de NPS")
    public ResponseEntity<Object> saveNps(@RequestBody @Valid NpsCalculatorDto npsCalculatorDto) {
        var model = new NpsCalculatorModel();
        BeanUtils.copyProperties(npsCalculatorDto, model);

        if (npsCalculatorService.isAllZero(model))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campos zerados! Consulta inválida.");
        else
            model = npsCalculatorService.calculateGrades(model);

        if (model.getPersonalGoal() != null){
            model.setNeededToReachGoal(npsCalculatorService.calculateGoals(model));
            model.setGoalAchived(model.getNeededToReachGoal() == 0 ? true : false);
        } 
            
        return ResponseEntity.status(HttpStatus.OK).body(npsCalculatorService.save(model));
    }

    /**
     * Return all the previous consults made
     * 
     * @return All the previous consults
     */
    @GetMapping
    @ApiOperation(value = "Retornar todas as consultas feitas")
    public ResponseEntity<List<NpsCalculatorModel>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(npsCalculatorService.getAll());
    }

    /**
     * Receive a NPS value and return a list of NPS with values bigger than the
     * param NPS
     * 
     * @param nps Minimum NPS value for consult
     * @return Values bigger than param NPS
     */
    @GetMapping("/greater-than-{nps}")
    @ApiOperation(value = "Faz uma consulta apenas de valores maiores que {nps}")
    public ResponseEntity<List<NpsCalculatorModel>> getGreaterThan(@PathVariable(value = "nps") int nps) {
        return ResponseEntity.status(HttpStatus.OK).body(npsCalculatorService.getGreaterThan(nps));
    }

    /**
     * Receive a NPS value and return a list of NPS with values smallest than the
     * param NPS
     * 
     * @param nps Maximun NPS value for consult
     * @return Values smaller than param NPS
     */
    @GetMapping("/less-than-{nps}")
    @ApiOperation(value = "Faz uma consulta apenas de valores menores que {nps}")
    public ResponseEntity<List<NpsCalculatorModel>> getLessThan(@PathVariable(value = "nps") int nps) {
        return ResponseEntity.status(HttpStatus.OK).body(npsCalculatorService.getLessThan(nps));
    }

    @GetMapping("/get-all-with-goal")
    @ApiOperation(value = "Retornar todas as consultas com Goal != null")
    public ResponseEntity<List<NpsCalculatorModel>> getAllWithPersonalGoal(){
        return ResponseEntity.status(HttpStatus.OK).body(npsCalculatorService.getAllWithGoal());
    }
}
