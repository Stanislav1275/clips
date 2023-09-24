package com.aikoni6.project.Repositories.system;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aikoni6.project.Entities.system.FileEntity;

import java.util.List;

public interface FileEntityRepo extends JpaRepository<FileEntity, Long> {
    public List<FileEntity> findFileEntityByFullName(String name);
}
