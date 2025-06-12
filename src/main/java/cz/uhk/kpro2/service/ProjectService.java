package cz.uhk.kpro2.service;

import cz.uhk.kpro2.model.Project;
import java.util.List;

public interface ProjectService {
    List<Project> getAllProjects();
    Project getProject(Long id);
    void saveProject(Project project);
    void deleteProject(Long id);
}
