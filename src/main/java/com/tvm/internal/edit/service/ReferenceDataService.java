package com.tvm.internal.edit.service;

import com.tvm.internal.edit.model.ReferenceData;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReferenceDataService {

    ReferenceData createReferenceData(ReferenceData referenceData);

    List<ReferenceData> getAllReferenceData();

    ReferenceData getReferenceDataById(Long id);

    ReferenceData updateReferenceData(Long id, ReferenceData updatedData);

    ResponseEntity<String> deleteReferenceData(Long id);

    boolean existsById(Long id);
}
