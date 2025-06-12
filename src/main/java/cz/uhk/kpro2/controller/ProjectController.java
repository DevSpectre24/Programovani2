package cz.uhk.kpro2.controller;

import cz.uhk.kpro2.model.Project;
import cz.uhk.kpro2.service.ManagerService;
import cz.uhk.kpro2.service.ProjectService;
import cz.uhk.kpro2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final ManagerService managerService;
    private final UserService userService;

    @Autowired
    public ProjectController(ProjectService projectService, ManagerService managerService, UserService userService) {
        this.projectService = projectService;
        this.managerService = managerService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String listProjects(Model model) {
        model.addAttribute("projects", projectService.getAllProjects());
        return "projects_list";
    }

    @GetMapping("/new")
    public String newProject(Model model) {
        model.addAttribute("project", new Project());
        model.addAttribute("managers", managerService.getAllManagers());
        model.addAttribute("users", userService.getAllUsers());
        return "projects_form";
    }

    @GetMapping("/{id}")
    public String projectDetail(@PathVariable Long id, Model model) {
        Project project = projectService.getProject(id);
        if (project == null) {
            return "redirect:/projects/";
        }
        model.addAttribute("project", project);
        return "projects_detail";
    }

    @GetMapping("/{id}/edit")
    public String editProject(@PathVariable Long id, Model model) {
        Project project = projectService.getProject(id);
        if (project == null) {
            return "redirect:/projects/";
        }
        model.addAttribute("project", project);
        model.addAttribute("managers", managerService.getAllManagers());
        model.addAttribute("users", userService.getAllUsers());
        return "projects_form";
    }

    @PostMapping("/save")
    public String saveProject(@ModelAttribute Project project) {
        projectService.saveProject(project);
        return "redirect:/projects/";
    }

    @GetMapping("/{id}/delete")
    public String deleteProjectConfirm(@PathVariable Long id, Model model) {
        Project project = projectService.getProject(id);
        if (project == null) {
            return "redirect:/projects/";
        }
        model.addAttribute("project", project);
        return "projects_delete";
    }

    @PostMapping("/{id}/delete")
    public String deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return "redirect:/projects/";
    }
}

