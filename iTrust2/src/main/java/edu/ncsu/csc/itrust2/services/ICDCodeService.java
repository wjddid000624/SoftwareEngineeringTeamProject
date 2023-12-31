package edu.ncsu.csc.itrust2.services;

import edu.ncsu.csc.itrust2.models.ICDCode;
import edu.ncsu.csc.itrust2.repositories.ICDCodeRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class ICDCodeService extends Service {

    private final ICDCodeRepository repository;

    @Override
    protected JpaRepository getRepository() {
        return repository;
    }

    public ICDCode findByCode(final String code) {
        return repository.findByCode(code);
    }
}
