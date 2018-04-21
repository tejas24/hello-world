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
	public List<Associates> findAssociatestData(String name, String specialization) {

		//String sqlQuery = "select * from dbo.Associates where name = (:name) and specialization = (:specialization)";
		String sqlQuery = "select * from dbo.Associates where name LIKE CONCAT('%',:name,'%') and specialization LIKE CONCAT('%',:specialization,'%')";
		Query query = em.createNativeQuery(sqlQuery, Associates.class);
		query.setParameter("name", name);
		query.setParameter("specialization", specialization);
		List<Associates> result = query.getResultList();
		return result;
	}

}
