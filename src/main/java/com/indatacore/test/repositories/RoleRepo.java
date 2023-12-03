package com.indatacore.test.repositories;

import com.indatacore.test.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role,String> {


}
