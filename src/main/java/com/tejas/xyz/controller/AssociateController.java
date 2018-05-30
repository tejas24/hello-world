package com.tejas.xyz.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tejas.xyz.data.entities.Associates;
import com.tejas.xyz.manager.AssociateManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
@Api(value = "/api/v1")
public class AssociateController {

	private static final Logger LOG = LoggerFactory.getLogger(AssociateController.class);

	@Autowired
	private AssociateManager asssociateManager;

	@ApiOperation(value = "Get All Asociates Details", notes = "Get All Asociates Details")
	@RequestMapping(value = "/associates", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResponseEntity<List<Associates>> getAllAssociatesDetails() throws Exception {
		LOG.info("REST call to get all the associates details");
		List<Associates> result = asssociateManager.getAllAssociatesList();
		if (result.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiOperation(value = "Get an Associate Details by Id ", notes = "Get an Associate Details by Id")
	@RequestMapping(value = "/associates/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResponseEntity<Optional<Associates>> getAssociatesDetails(@PathVariable("id") Long id)
			throws Exception {
		LOG.info("REST call to get the associates details by id = {}" , id);
		Optional<Associates> result = asssociateManager.getAssociate(id);
		if (!result.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiOperation(value = "Delete an Associate Details", notes = "Delete an Associate Details")
	@RequestMapping(value = "/associates/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public @ResponseBody ResponseEntity<Long> deleteAssociateDetails(@PathVariable("id") Long id) throws Exception {
		LOG.info("REST call to delete the associates details by id = {}", id);
		if (!asssociateManager.existsAssociate(id)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		asssociateManager.deleteAssociate(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "Search an Associate Details by name", notes = "Search an Associate Details by name")
	@RequestMapping(value = "/associates/name", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResponseEntity<List<Associates>> getAssociateDetails(@RequestParam("name") String name)
			throws Exception {
		LOG.info("REST call to get the associates details by name");

		List<Associates> result = asssociateManager.getAssociatesList(name);
		if (result.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiOperation(value = "Search an Associate Details by specialization", notes = "Search an Associate Details by specialization")
	@RequestMapping(value = "/associates/specialization", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResponseEntity<List<Associates>> getAssociateDetailsBySpecializationc(
			@RequestParam(value = "specialization") String specialization) throws Exception {
		LOG.info("REST call to get the associates details by specialization");

		List<Associates> result = asssociateManager.getAssociatesListBySpecialization(specialization);
		if (result.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiOperation(value = "Create an Associate Details", notes = "Create an Associate Details")
	@RequestMapping(value = "/associates", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseEntity<Associates> createAssociate(@RequestBody Associates associates)
			throws Exception {
		LOG.info("REST call to create the associates details ");
		asssociateManager.createAssociate(associates);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@ApiOperation(value = "Update an Associate Details", notes = "Update an Associate Details")
	@RequestMapping(value = "/associates/{id}", method = RequestMethod.PUT, produces = "application/json")
	public @ResponseBody ResponseEntity<Associates> updateAssociate(@RequestBody Associates associates,
			@PathVariable("id") Long id) throws Exception {
		LOG.info("REST call to update the associates details ");
		if (!asssociateManager.existsAssociate(id)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		asssociateManager.updateAssociate(associates, id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "Partially Update Associate Details", notes = "Partially Update an Associate Details")
	@RequestMapping(value = "/associates/{id}", method = RequestMethod.PATCH, produces = "application/json")
	public @ResponseBody ResponseEntity<Map<String, String>> partialUpdateAssociate(@RequestBody Map<String, String> updates,
			@PathVariable("id") Long id) throws Exception {
		LOG.info("REST call to update the associates partial details ");
		if (!asssociateManager.existsAssociate(id)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		asssociateManager.partialUpdateAssociate(updates, id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
