package br.com.redhat.base.springrest.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.redhat.base.springrest.domain.Simple;

@Repository
public interface SimplePersistence extends CrudRepository<Simple, Long> {

}
