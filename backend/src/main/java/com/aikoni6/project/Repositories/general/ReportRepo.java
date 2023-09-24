package com.aikoni6.project.Repositories.general;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aikoni6.project.Entities.general.Report;

public interface ReportRepo extends JpaRepository<Report, Long> {

}
