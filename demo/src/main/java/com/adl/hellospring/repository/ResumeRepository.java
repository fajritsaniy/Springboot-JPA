
package com.adl.hellospring.repository;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.adl.hellospring.model.Resume;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Integer>{

	@Query("select r from Resume r")
	List<Resume> findAllResumes();
	
	@Query("select r from Resume r where r.tipe = ?1")
	Page<Resume> findAllByTipeResume(String tipe ,org.springframework.data.domain.Pageable pageable);
	
	@Query("select r from Resume r where r.tipe = ?1 and r.judul =?2")
	List<Resume> findAllByTipeResumeAndJudul(String tipe, String judul);
}
