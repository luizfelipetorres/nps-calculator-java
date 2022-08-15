package com.myapi.npscalculator.repositories;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myapi.npscalculator.models.NpsCalculatorModel;

@Repository
public interface NpsCalculatorRepository extends JpaRepository<NpsCalculatorModel, UUID>{
    
}
