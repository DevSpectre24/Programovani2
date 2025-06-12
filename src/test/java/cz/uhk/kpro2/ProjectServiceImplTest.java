package cz.uhk.kpro2;

import cz.uhk.kpro2.model.Project;
import cz.uhk.kpro2.repository.ProjectRepository;
import cz.uhk.kpro2.service.ProjectServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProjectServiceImplTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectServiceImpl projectService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProjects() {
        List<Project> projects = Arrays.asList(new Project(), new Project());
        when(projectRepository.findAll()).thenReturn(projects);

        List<Project> result = projectService.getAllProjects();

        assertEquals(2, result.size());
        verify(projectRepository, times(1)).findAll();
    }

    @Test
    void testGetProjectFound() {
        Project project = new Project();
        project.setId(1L);
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));

        Project result = projectService.getProject(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void testGetProjectNotFound() {
        when(projectRepository.findById(1L)).thenReturn(Optional.empty());

        Project result = projectService.getProject(1L);

        assertNull(result);
    }

    @Test
    void testSaveProject() {
        Project project = new Project();
        projectService.saveProject(project);

        verify(projectRepository, times(1)).save(project);
    }

    @Test
    void testDeleteProject() {
        projectService.deleteProject(1L);

        verify(projectRepository, times(1)).deleteById(1L);
    }
}
