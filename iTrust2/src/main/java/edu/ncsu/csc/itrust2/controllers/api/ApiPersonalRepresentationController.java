package edu.ncsu.csc.itrust2.controllers.api;


import edu.ncsu.csc.itrust2.models.PersonalRepresentatives;
import edu.ncsu.csc.itrust2.services.PersonalRepresentationService;
import edu.ncsu.csc.itrust2.utils.LoggerUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class ApiPersonalRepresentationController {
    private final PersonalRepresentationService personalRepresentationService;
    private final LoggerUtil loggerUtil;

    @GetMapping("/representatives")
    @PreAuthorize("hasRole('ROLE_PATIENT')")
    public List<PersonalRepresentatives> listPersonalRepresentativesByCurrentPatient() {
        final String patientName = loggerUtil.getCurrentUsername();
        return personalRepresentationService.listByPatient(patientName);
    }

    @GetMapping("/representingPatients")
    @PreAuthorize("hasRole('ROLE_PATIENT')")
    public List<PersonalRepresentatives> listPersonalRepresentingByCurrentPatient() {
        final String patientName = loggerUtil.getCurrentUsername();
        return personalRepresentationService.listByRepresenting(patientName);
    }

    @GetMapping("patients/{patientUsername}/representingPatients")
    @PreAuthorize("hasRole('ROLE_HCP')")
    public List<PersonalRepresentatives> listPersonalRepresentingByPatientId(@PathVariable final String patientUsername) {
        return personalRepresentationService.listByRepresenting(patientUsername);
    }


}