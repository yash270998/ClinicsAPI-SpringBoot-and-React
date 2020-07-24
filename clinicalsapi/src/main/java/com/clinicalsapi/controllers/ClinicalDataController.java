package com.clinicalsapi.controllers;

import java.util.Optional;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.clinicalsapi.dto.ClinicalDataRequest;
import com.clinicalsapi.entities.ClinicalData;
import com.clinicalsapi.entities.Patient;
import com.clinicalsapi.repos.ClinicalDataRepository;
import com.clinicalsapi.repos.PatientRepository;

@RestController
@RequestMapping("/api")
public class ClinicalDataController {
	private ClinicalDataRepository clinicalDataRepository;
	private PatientRepository patientRepository;

	public ClinicalDataController(ClinicalDataRepository clinicalDataRepository, PatientRepository patientRepository) {
		this.clinicalDataRepository = clinicalDataRepository;
		this.patientRepository = patientRepository;

	}

	@RequestMapping(value = "/clinicals", method = RequestMethod.POST)
	public ClinicalData saveClinicalData(@RequestBody ClinicalDataRequest request) {
		System.out.println("WTF"+request.getId() + request.getComponentName());
		Patient patient = patientRepository.findById(request.getId()).get();
		ClinicalData clinicalData = new ClinicalData();
		clinicalData.setComponentName(request.getComponentName());
		clinicalData.setComponentValue(request.getComponentValue());
		clinicalData.setPatient(patient);
		return clinicalDataRepository.save(clinicalData);
	}

}
