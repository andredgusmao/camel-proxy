package br.com.redhat.base.springrest.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.redhat.base.springrest.domain.Complex;

@Repository
public interface ComplexPersistence extends CrudRepository<Complex, Long> {

}
