package com.tejas.xyz.manager.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tejas.xyz.data.entities.Associates;
import com.tejas.xyz.data.entities.AssociatesDTO;
import com.tejas.xyz.data.repositories.AssociateRepository;
import com.tejas.xyz.manager.AssociateManager;

@Service
public class AssociateManagerImpl implements AssociateManager {

	@Autowired
	private AssociateRepository associateRepository;

	private static final Logger LOG = LoggerFactory.getLogger(AssociateManagerImpl.class);

	@Override
	public List<Associates> getAllAssociatesList() throws Exception {
		LOG.info("Fetching all the Associates details");
		return (List<Associates>) associateRepository.findAll();
	}

	@Override
	public Optional<Associates> getAssociate(Long id) throws Exception {
		LOG.info("Fetching  the Associates details by id");
		return associateRepository.findById(id);
	}

	@Override
	public void deleteAssociate(Long id) throws Exception {
		LOG.info("deleteing  the Associates details by id");

		try {
			associateRepository.deleteById(id);
		} catch (Exception e) {
			LOG.error("unable to find associate with id = " + id);
			// e.printStackTrace();
		}
	}

	@Override
	public List<Associates> getAssociatesList(String name, String specialization) throws Exception {
		LOG.info("fetching the Associates details by name and specialization");
		return associateRepository.findAssociatestData(name, specialization);
	}

	@Override
	public void createAssociate(AssociatesDTO associatesDTO) throws Exception {
		LOG.info("creating the Associates details");
		associateRepository.createAssociate(associatesDTO);
	}

}
