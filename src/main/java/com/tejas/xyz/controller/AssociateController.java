package com.tejas.xyz.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tejas.xyz.data.entities.Associates;
import com.tejas.xyz.data.entities.Specialization;
import com.tejas.xyz.manager.AssociateManager;
import com.tejas.xyz.manager.SpecializationManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1")
@Api(value = "/api/v1")
public class AssociateController {

	private static final Logger LOG = LoggerFactory.getLogger(AssociateController.class);

	@Autowired
	private AssociateManager asssociateManager;

	@Autowired
	private SpecializationManager specializationManager;

	@ApiOperation(value = "Get All Asociates", notes = "Get All Asociates")
	@RequestMapping(value = "/associates", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResponseEntity<List<Associates>> getAllAssociatesDetails() throws Exception {
		LOG.info("REST call to get all the associates details");
		List<Associates> result = asssociateManager.getAllAssociatesList();
		if (result.isEmpty()) {
			//throw new Exception("Unable to fetch associates details");
			return new ResponseEntity<List<Associates>>(HttpStatus.NOT_FOUND);
		}
		/*
		 * for (Associates associates : result) { if
		 * (associates.getSpecialization().isEmpty()) {
		 * associates.getSpecialization().clear(); } } for (Associates associates :
		 * result) { System.out.println("*** Associates Details ***");
		 * 
		 * System.out.println("Associates Id   : " + associates.getAssociateId());
		 * System.out.println("Associates Name : " + associates.getName());
		 * System.out.println("Associates Phone : " + associates.getPhone());
		 * System.out.println("Associates Address : " + associates.getAddress());
		 * 
		 * Set<Specialization> specializationSet = associates.getSpecialization(); if
		 * (!specializationSet.isEmpty()) {
		 * System.out.println("*** Associates Specialization Details ***"); for
		 * (Specialization specialization : specializationSet) {
		 * 
		 * System.out.println("Specialization Name  : " + specialization.getName()); } }
		 * }
		 */
		// List<Associates> result1 = new ArrayList<>();
		// return new ResponseEntity<List<Associates>>(result1, HttpStatus.OK);
		return new ResponseEntity<List<Associates>>(result, HttpStatus.OK);
		// return new ResponseEntity(new AssociatesDTO(result, result1), HttpStatus.OK);
	}

	@ApiOperation(value = "Get Asociate by Id", notes = "Get Asociate by Id")
	@RequestMapping(value = "/associates/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResponseEntity<Optional<Associates>> getAssociatesDetails(@PathVariable("id") Long id)
			throws Exception {
		LOG.info("REST call to get the associates details by id");
		// Optional<Specialization> result1 =
		// specializationManager.getSpecialization(id);
		Optional<Associates> result = asssociateManager.getAssociate(id);
		if (!result.isPresent()) {
			// throw new Exception("Unable to fetch associates details by id = " + id);
			return new ResponseEntity<Optional<Associates>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Optional<Associates>>(result, HttpStatus.OK);
		// return new ResponseEntity(new AssociatesDTO(result, result1), HttpStatus.OK);
	}

	@ApiOperation(value = "Delete an Associate", notes = "Delete an Associate")
	@RequestMapping(value = "/associates/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public @ResponseBody ResponseEntity<?> deleteAssociateDetails(@PathVariable("id") Long id) throws Exception {
		LOG.info("REST call to delete the associates details by id = " + id);
		Optional<Associates> result = asssociateManager.getAssociate(id);
		if (!result.isPresent()) {
			// throw new Exception("Unable to fetch associates details by id = " + id);
			return new ResponseEntity<Optional<Associates>>(HttpStatus.NOT_FOUND);
		}
		/*
		 * Optional<Specialization> result1 =
		 * specializationManager.getSpecialization(id); if (!result1.isPresent()) { //
		 * throw new Exception("Unable to fetch associates details by id = " + id);
		 * return new ResponseEntity<Optional<Specialization>>(HttpStatus.NOT_FOUND); }
		 */
		asssociateManager.deleteAssociate(id);
		// specializationManager.deleteSpecialization(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "Search an Asociate", notes = "Search an Asociate")
	@RequestMapping(value = "/associates/search", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResponseEntity<List<Associates>> getAssociateDetails(@RequestParam("name") String name,
			@RequestParam("specialization") String specialization) throws Exception {
		LOG.info("REST call to get the associates details by name and specialization");

		List<Associates> result = asssociateManager.getAssociatesList(name, specialization);
		if (result.isEmpty()) {
			// throw new Exception("Unable to fetch associates details by name = " + name);
			return new ResponseEntity<List<Associates>>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<Associates>>(result, HttpStatus.OK);
	}

	@ApiOperation(value = "Create an Asociate", notes = "Create an Asociate")
	@RequestMapping(value = "/associates", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseEntity<?> createAssociate(@RequestBody Associates associates) throws Exception {
		LOG.info("REST call to create the associates details ");
		// associates.setSpecialization(associates.getSpecialization());
		asssociateManager.createAssociate(associates);
		/*
		 * List<Associates> result = asssociateManager.getAllAssociatesList();
		 * for(Associates associatesData : result) { associatesData.getAssociateId(); }
		 */
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}

	@ApiOperation(value = "Update an Asociate", notes = "Update an Asociate")
	@RequestMapping(value = "/associates/{id}", method = RequestMethod.PUT, produces = "application/json")
	public @ResponseBody ResponseEntity<?> updateAssociate(@RequestBody Associates associates,
			@PathVariable("id") Long id) throws Exception {
		LOG.info("REST call to create the associates details ");
		Optional<Associates> result = asssociateManager.getAssociate(id);
		if (!result.isPresent()) {
			// throw new Exception("Unable to fetch associates details by id = " + id);
			// return new ResponseEntity(new Exception("Unable to fetch associates details by id = " + id).getMessage(),HttpStatus.NOT_FOUND);
			return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Associate not found with id=" + id);
		}
		asssociateManager.updateAssociate(associates, id);
		// specializationManager.updateSpecialization(specialization, id);
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}
}
