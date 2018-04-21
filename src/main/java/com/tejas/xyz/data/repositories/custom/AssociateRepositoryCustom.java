package com.tejas.xyz.data.repositories.custom;

import java.util.List;

import com.tejas.xyz.data.entities.Associates;

public interface AssociateRepositoryCustom {

	List<Associates> findAssociatestData(String name,String specialization);
		
}
