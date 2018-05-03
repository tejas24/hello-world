package com.tejas.xyz.manager;

import java.util.Optional;

import com.tejas.xyz.data.entities.Specialization;

public interface SpecializationManager {

	public Optional<Specialization> getSpecialization(Long id) throws Exception;

	public void deleteSpecialization(Long id) throws Exception;

	void updateSpecialization(Specialization specialization, Long id) throws Exception;

}
