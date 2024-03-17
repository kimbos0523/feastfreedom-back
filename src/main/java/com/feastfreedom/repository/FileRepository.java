package com.feastfreedom.repository;

import com.feastfreedom.entity.UploadFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<UploadFile, Long> {

    Optional<UploadFile> findByKitchenId(Long kitchenId);

}
