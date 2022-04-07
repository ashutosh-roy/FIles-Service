package com.algomox.filesservice.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.algomox.filesservice.models.LicensedData;
@Repository
public interface FilesRepository extends CassandraRepository<LicensedData,String> {

	@AllowFiltering
	List<LicensedData> findByName(String name);
}