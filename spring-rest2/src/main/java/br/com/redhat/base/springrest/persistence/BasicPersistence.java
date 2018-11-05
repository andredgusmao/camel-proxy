package br.com.redhat.base.springrest.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.redhat.base.springrest.domain.Basic;

@Repository
public interface BasicPersistence extends CrudRepository<Basic, Long> {

}
