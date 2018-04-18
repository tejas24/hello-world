package com.tejas.xyz.data.repositories;

import org.springframework.data.repository.CrudRepository;

import com.tejas.xyz.data.entities.Associates;
import com.tejas.xyz.data.repositories.custom.AssociateRepositoryCustom;

public interface AssociateRepository extends CrudRepository<Associates, Long>,AssociateRepositoryCustom {

}
