package com.tejas.xyz.manager.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tejas.xyz.data.entities.Associates;
import com.tejas.xyz.data.entities.Specialization;
import com.tejas.xyz.data.repositories.SpecializationRepository;
import com.tejas.xyz.manager.SpecializationManager;

@Service
public class SpecializationManagerImpl implements SpecializationManager {

	@Autowired
	private SpecializationRepository specializationRepository;

	private static final Logger LOG = LoggerFactory.getLogger(AssociateManagerImpl.class);

	@Override
	public Optional<Specialization> getSpecialization(Long id) throws Exception {
		LOG.info("Fetching the Associates details by id");
		return specializationRepository.findById(id);
	}

	@Override
	public void deleteSpecialization(Long id) throws Exception {
		LOG.info("deleteing  the specialization details by id");

		try {
			specializationRepository.deleteById(id);
		} catch (Exception e) {
			LOG.error("unable to find associate with id = " + id);
			// e.printStackTrace();
		}
	}
	
	@Override
	public void updateSpecialization(Specialization specialization, Long id) throws Exception {
		LOG.info("creating the Specializations details");
		//specialization.setAssociateId(id);
		specializationRepository.save(specialization);
	}

}
