package cz.uhk.kpro2.controller;

import cz.uhk.kpro2.model.Course;
import cz.uhk.kpro2.service.CourseService;
import cz.uhk.kpro2.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    private final ManagerService managerService;

    @Autowired
    public CourseController(CourseService courseService, ManagerService managerService) {
        this.courseService = courseService;
        this.managerService = managerService;
    }

    @GetMapping("/")
    public String getAll(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        return "courses_list";
    }

    @GetMapping("/new")
    public String newCourse(Model model) {
        model.addAttribute("course", new Course());
        model.addAttribute("managers", managerService.getAllManagers()); // ZMĚNA zde
        return "courses_form";
    }

    @GetMapping("/{id}")
    public String getCourse(Model model, @PathVariable long id) {
        Course course = courseService.getCourse(id);
        if (course != null) {
            model.addAttribute("course", course);
            return "courses_detail";
        }
        return "redirect:/courses/";
    }

    @GetMapping("/{id}/edit")
    public String editCourse(Model model, @PathVariable long id) {
        Course course = courseService.getCourse(id);
        if (course != null) {
            model.addAttribute("managers", managerService.getAllManagers()); // ZMĚNA zde
            model.addAttribute("course", course);
            return "courses_form";
        }
        return "redirect:/courses/";
    }

    @PostMapping("/save")
    public String saveCourse(@ModelAttribute Course course) {
        courseService.saveCourse(course);
        return "redirect:/courses/";
    }

    @GetMapping("/{id}/delete")
    public String deleteCourse(Model model, @PathVariable long id) {
        Course course = courseService.getCourse(id);
        if (course != null) {
            model.addAttribute("course", course);
            return "courses_delete";
        }
        return "redirect:/courses/";
    }

    @PostMapping("/{id}/delete")
    public String deleteCourseConfirm(@PathVariable long id) {
        courseService.deleteCourse(id);
        return "redirect:/courses/";
    }
}
