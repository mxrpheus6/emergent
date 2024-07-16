package com.mxrph.dao;

import com.mxrph.entity.AppUser;
import org.springframework.data.repository.CrudRepository;

public interface AppUserDAO extends CrudRepository<AppUser, Long> {

}
