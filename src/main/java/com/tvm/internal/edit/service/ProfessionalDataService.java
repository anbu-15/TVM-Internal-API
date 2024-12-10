package com.tvm.internal.edit.service;

import com.tvm.internal.edit.model.ProfessionalData;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProfessionalDataService {
    ProfessionalData createProfessionalData(ProfessionalData professionalData);

    List<ProfessionalData> getAllProfessionalData();

    ProfessionalData getProfessionalDataById(Long id);

    ProfessionalData updateProfessionalData(Long id, ProfessionalData professionalData);

    ResponseEntity<String> deleteProfessionalData(Long id);

    boolean existsById(Long id);
}
