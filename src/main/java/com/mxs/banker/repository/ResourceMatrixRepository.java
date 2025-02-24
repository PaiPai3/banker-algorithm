package com.mxs.banker.repository;

import com.mxs.banker.model.ResourceMatrix;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ResourceMatrixRepository extends JpaRepository<ResourceMatrix, Long> {
    List<ResourceMatrix> findByTimeStamp(Date date);
}
