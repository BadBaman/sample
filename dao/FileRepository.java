package com.fabric.dao;

import com.fabric.entity.File;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FileRepository extends CrudRepository<File, Integer> {
    List<File> findById(int id);
    void deleteById(int id);
}
