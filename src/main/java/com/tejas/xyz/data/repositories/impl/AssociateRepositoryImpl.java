package com.tejas.xyz.data.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;

import com.tejas.xyz.data.entities.Associates;
import com.tejas.xyz.data.repositories.custom.AssociateRepositoryCustom;

public class AssociateRepositoryImpl implements AssociateRepositoryCustom {

	@Autowired
	private EntityManager em;

	@Override
	public List<Associates> findAssociatesData(String name) {
		String sqlQuery = "select * from dbo.Associates a where a.name LIKE CONCAT('%',:name,'%')";
		Query query = em.createNativeQuery(sqlQuery, Associates.class);
		query.setParameter("name", name);
		return query.getResultList();
	}

	@Override
	public List<Associates> findAssociatesDataBySpecialization(String specialization) {
		String sqlQuery = "select a from Associates a inner join fetch a.specialization s where s.name LIKE CONCAT('%',:specialization,'%')";
		Query query = em.createQuery(sqlQuery, Associates.class);
		query.setParameter("specialization", specialization);
		return query.getResultList();
	}
}
