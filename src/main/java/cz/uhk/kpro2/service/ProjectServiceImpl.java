package cz.uhk.kpro2.service;

import cz.uhk.kpro2.model.Project;
import cz.uhk.kpro2.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project getProject(Long id) {
        return projectRepository.findById(id).orElse(null);
    }

    @Override
    public void saveProject(Project project) {
        projectRepository.save(project);
    }

    @Override
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
}
