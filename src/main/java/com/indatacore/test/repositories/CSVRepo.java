package com.indatacore.test.repositories;

import com.indatacore.test.entities.CSV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CSVRepo extends JpaRepository<CSV,Long> {


}
