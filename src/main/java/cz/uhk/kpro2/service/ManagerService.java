package cz.uhk.kpro2.service;

import cz.uhk.kpro2.model.Manager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ManagerService {
    List<Manager> getAllManagers();
    Manager getManager(long id);
    void saveManager(Manager manager);
    void deleteManager(long id);
}
