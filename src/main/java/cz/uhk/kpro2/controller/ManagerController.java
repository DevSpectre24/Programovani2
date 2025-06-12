package cz.uhk.kpro2.controller;

import cz.uhk.kpro2.model.Manager;
import cz.uhk.kpro2.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/managers")
public class ManagerController {

    private final ManagerService managerService;

    @Autowired
    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping("/")
    public String getAll(Model model) {
        model.addAttribute("managers", managerService.getAllManagers());
        return "manager_list";
    }

    @GetMapping("/new")
    public String newManager(Model model) {
        model.addAttribute("manager", new Manager());
        return "manager_form";
    }

    @GetMapping("/{id}")
    public String getManager(Model model, @PathVariable long id) {
        Manager manager = managerService.getManager(id);
        if (manager != null) {
            model.addAttribute("manager", manager);
            return "manager_detail";
        }
        return "redirect:/managers/";
    }

    @GetMapping("/{id}/edit")
    public String editManager(Model model, @PathVariable long id) {
        Manager manager = managerService.getManager(id);
        if (manager != null) {
            model.addAttribute("manager", manager);
            return "manager_form";
        }
        return "redirect:/managers/";
    }

    @PostMapping("/save")
    public String saveManager(@ModelAttribute Manager manager) {
        managerService.saveManager(manager);
        return "redirect:/managers/";
    }

    @GetMapping("/{id}/delete")
    public String deleteManager(Model model, @PathVariable long id) {
        Manager manager = managerService.getManager(id);
        if (manager != null) {
            model.addAttribute("manager", manager);
            return "manager_delete";
        }
        return "redirect:/managers/";
    }

    @PostMapping("/{id}/delete")
    public String deleteManagerConfirm(@PathVariable long id) {
        managerService.deleteManager(id);
        return "redirect:/managers/";
    }
}
