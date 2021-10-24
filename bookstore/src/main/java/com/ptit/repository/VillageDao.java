package com.ptit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ptit.model.District;
import com.ptit.model.Village;

@Repository
public interface VillageDao extends JpaRepository<Village, String>{
	public List<Village> findByDistrict(District district);
}
