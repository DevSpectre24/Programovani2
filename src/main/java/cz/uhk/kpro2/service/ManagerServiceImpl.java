package cz.uhk.kpro2.service;

import cz.uhk.kpro2.model.Manager;
import cz.uhk.kpro2.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManagerServiceImpl implements ManagerService {

    private final ManagerRepository managerRepository;

    @Autowired
    public ManagerServiceImpl(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @Override
    public List<Manager> getAllManagers() {
        return managerRepository.findAll();
    }

    @Override
    public Manager getManager(long id) {
        Optional<Manager> manager = managerRepository.findById(id);
        return manager.orElse(null);
    }

    @Override
    public void saveManager(Manager manager) {
        managerRepository.save(manager);
    }

    @Override
    public void deleteManager(long id) {
        managerRepository.deleteById(id);
    }
}
