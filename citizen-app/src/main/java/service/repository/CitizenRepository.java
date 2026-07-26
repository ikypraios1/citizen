package service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import model.Citizen;


@Repository

public interface CitizenRepository
extends JpaRepository<Citizen, String>,
        JpaSpecificationExecutor<Citizen> {
}