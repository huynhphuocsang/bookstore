package com.ptit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ptit.model.PublishingCompany;
@Repository
public interface PublishingCompanyDao extends JpaRepository<PublishingCompany, Long>{

}
