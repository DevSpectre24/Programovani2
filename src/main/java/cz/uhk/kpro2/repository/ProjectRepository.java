package cz.uhk.kpro2.repository;

import cz.uhk.kpro2.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
