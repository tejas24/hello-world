package com.tejas.xyz.manager;

import java.util.List;
import java.util.Optional;

import com.tejas.xyz.data.entities.Associates;
import com.tejas.xyz.data.entities.AssociatesDTO;

public interface AssociateManager {

	public List<Associates> getAllAssociatesList() throws Exception;

	public Optional<Associates> getAssociate(Long id) throws Exception;

	public void deleteAssociate(Long id) throws Exception;
	
	public List<Associates> getAssociatesList(String name,String specialization) throws Exception;
	
	public void createAssociate(AssociatesDTO associatesDTO) throws Exception;

}
