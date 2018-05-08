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
		String sqlQuery = "select * from dbo.Associates a where a.name LIKE CONCAT('%',:name,'%')";// and a.specialization LIKE CONCAT('%',:specialization,'%')";
		//String sqlQuery = "select a.Name,a.Phone,a.Address,s.name from dbo.Associates a inner join dbo.Specialization s where a.name LIKE CONCAT('%',:name,'%') and s.name LIKE CONCAT('%',:specialization,'%')";
		//String sqlQuery ="SELECT a, s  FROM dbo.Associates a, dbo.Specialization s WHERE a = s.associates";
		//String sqlQuery = "select * from dbo.Associates a join a.Specialization as s where a.name LIKE CONCAT('%',:name,'%') and s.name LIKE CONCAT('%',:specialization,'%')";
		Query query = em.createNativeQuery(sqlQuery, Associates.class);
		query.setParameter("name", name);
		List<Associates> result = query.getResultList();
		return result;
	}

	@Override
	public List<Associates> findAssociatesDataBySpecialization(String specialization) {
		String sqlQuery = "select a from Associates a inner join fetch a.specialization s where s.name LIKE CONCAT('%',:specialization,'%')";
		Query query = em.createQuery(sqlQuery, Associates.class);
		query.setParameter("specialization", specialization);
		List<Associates> result = query.getResultList();
		return result;
	}
}
