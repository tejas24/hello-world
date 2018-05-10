package com.tejas.xyz.manager.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tejas.xyz.data.entities.Associates;
import com.tejas.xyz.data.entities.Specialization;
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
		List<Associates> associatesList =  (List<Associates>) associateRepository.findAll();
		//return (List<Associates>) associateRepository.findAll();
		return associatesList;
	}

	@Override
	public Optional<Associates> getAssociate(Long id) throws Exception {
		LOG.info("Fetching  the Associates details by id");
		return associateRepository.findById(id);
	}

	@Override
	public void deleteAssociate(Long id) throws Exception {
		LOG.info("deleteing  the Associates details by id = "+ id);

		try {
			associateRepository.deleteById(id);
			LOG.info("deleted  the Associates details by id = "+ id);
		} catch (Exception e) {
			LOG.error("unable to find associate with id = " + id);
			// e.printStackTrace();
		}
	}

	@Override
	public List<Associates> getAssociatesList(String name) throws Exception {
		LOG.info("fetching the Associates details by name");
		return associateRepository.findAssociatesData(name);
	}

	@Override
	public void createAssociate(Associates associates) throws Exception {
		LOG.info("creating the Associates details");
		for (Specialization s : associates.getSpecialization()) {
			s.setAssociates(associates);
		}
		associateRepository.save(associates);
	}

	@Override
	public void updateAssociate(Associates associates, Long id) throws Exception {
		LOG.info("updating the Associates details");
		associates.setAssociateId(id);
		if (associates.getSpecialization() != null) {
			for (Specialization s : associates.getSpecialization()) {
				s.setAssociates(associates);
			}
		}
		associateRepository.save(associates);
	}

	@Override
	public boolean existsAssociate(Long id) throws Exception {
		LOG.info("checking if the Associates details exists");
		return associateRepository.existsById(id);
	}

	@Override
	public List<Associates> getAssociatesListBySpecialization(String specialization) {
		LOG.info("fetching the Associates details by specialization");
		return associateRepository.findAssociatesDataBySpecialization( specialization);
	}

	@Override
	public void partialUpdateAssociate(Map<String, String> updates, Long id) {
		LOG.info("partially updating the Associates details");
		Associates associates = new Associates();
		associates.setAssociateId(id);
		Optional<Associates> associatesData = associateRepository.findById(id);
		associates.setName(associatesData.get().getName());
		associates.setPhone(associatesData.get().getPhone());
		associates.setAddress(associatesData.get().getAddress());
		Set<Map.Entry<String, String>> st = updates.entrySet();
		for (Map.Entry<String, String> me : st) {
			if (me.getKey().equalsIgnoreCase("name")) {
				associates.setName(me.getValue());
			} else if (me.getKey().equalsIgnoreCase("phone")) {
				associates.setPhone(me.getValue());
			} else if (me.getKey().equalsIgnoreCase("address")) {
				associates.setAddress(me.getValue());
			}
		}
		associateRepository.save(associates);
	}
}
