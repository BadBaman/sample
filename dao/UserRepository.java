package com.fabric.dao;
import java.util.List;

import com.fabric.entity.UserVo;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserVo, Integer> {
    List<UserVo> findByUserName(String userName);
    void deleteByUserName(String userName);
}
