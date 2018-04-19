package com.tejas.xyz.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.tejas.xyz.data.entities.Associates;
import com.tejas.xyz.data.entities.AssociatesDTO;
import com.tejas.xyz.manager.AssociateManager;

@RestController
@RequestMapping("/api/v1")
public class AssociateController {

	private static final Logger LOG = LoggerFactory.getLogger(AssociateController.class);

	@Autowired
	private AssociateManager asssociateManager;

	@RequestMapping(value = "/associates", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResponseEntity<List<Associates>> getAllAssociatesDetails() throws Exception {
		LOG.info("REST call to get all the associates details");

		List<Associates> result = asssociateManager.getAllAssociatesList();
		if (result == null) {
			throw new Exception("Unable to fetch associates details");
		}
		return new ResponseEntity<List<Associates>>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/associates/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResponseEntity<Optional<Associates>> getAssociatesDetails(@PathVariable("id") Long id)
			throws Exception {
		LOG.info("REST call to get the associates details by id");

		Optional<Associates> result = asssociateManager.getAssociate(id);
		if (!result.isPresent()) {
			//throw new Exception("Unable to fetch associates details by id = " + id);
			return new ResponseEntity<Optional<Associates>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Optional<Associates>>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/associates/{id}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<?> deleteAssociateDetails(@PathVariable("id") Long id) throws Exception {
		LOG.info("REST call to delete the associates details by id");
		Optional<Associates> result = asssociateManager.getAssociate(id);
		if (!result.isPresent()) {
			//throw new Exception("Unable to fetch associates details by id = " + id);
			return new ResponseEntity<Optional<Associates>>(HttpStatus.NOT_FOUND);
		}
		asssociateManager.deleteAssociate(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/associates/search", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResponseEntity<List<Associates>> getAssociateDetails(
			@RequestParam("name") String name ,@RequestParam("specialization") String specialization )
			throws Exception {
		LOG.info("REST call to get the associates details by name and specialization");

		List<Associates> result = asssociateManager.getAssociatesList(name, specialization );
		if (result.isEmpty()) {
			//throw new Exception("Unable to fetch associates details by name = " + name);
			return new ResponseEntity<List<Associates>>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<Associates>>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/associates", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseEntity<?> createAssociate(@RequestBody Associates associates) throws Exception {
		LOG.info("REST call to create the associates details ");
		asssociateManager.createAssociate(associates);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
}
