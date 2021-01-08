package com.waracle.cakemgr.repostory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.waracle.cakemgr.model.CakeEntity;

/**
 * @author gfeng
 */

@Repository
public interface CakeRepository extends JpaRepository<CakeEntity, Long> {
    
}
