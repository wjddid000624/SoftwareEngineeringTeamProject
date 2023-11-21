package edu.ncsu.csc.itrust2.repositories;

import edu.ncsu.csc.itrust2.models.Patient;
import edu.ncsu.csc.itrust2.models.PersonalRepresentation;

import edu.ncsu.csc.itrust2.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PersonalRepresentationRepository
        extends JpaRepository<PersonalRepresentation, Long> {
    List<PersonalRepresentation> findAllByPatient(Patient patient);
    List<PersonalRepresentation> findAllByPersonalRepresentative(Patient personalRepresentative);

    PersonalRepresentation findByPatientAndPersonalRepresentative(Patient patient, Patient representative);
}
