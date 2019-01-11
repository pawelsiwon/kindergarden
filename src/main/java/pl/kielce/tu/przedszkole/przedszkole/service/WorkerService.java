package pl.kielce.tu.przedszkole.przedszkole.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kielce.tu.przedszkole.przedszkole.model.Worker;
import pl.kielce.tu.przedszkole.przedszkole.repository.ActionRepository;
import pl.kielce.tu.przedszkole.przedszkole.repository.RoleRepository;
import pl.kielce.tu.przedszkole.przedszkole.repository.WorkerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {

    private final RoleRepository roleRepository;
    private final WorkerRepository workerRepository;
    private final ActionRepository actionRepository;

    @Autowired
    public WorkerService(RoleRepository roleRepository,
                         WorkerRepository workerRepository,
                         ActionRepository actionRepository) {
        this.roleRepository = roleRepository;
        this.workerRepository = workerRepository;
        this.actionRepository = actionRepository;
    }
    @Transactional
    public void addWorker(Long issuingWorkerId, Worker addedWorker) throws Exception {
        checkIfIsAdminOrHeadTeacher(issuingWorkerId);

        workerRepository.save(addedWorker);
    }

    private void checkIfIsAdminOrHeadTeacher(Long issuingWorkerId) throws Exception {
        Optional<Worker> issuingWorkerOptional = workerRepository.findById(issuingWorkerId);
        if(!issuingWorkerOptional.isPresent()) {
            throw new Exception("Worker with id "+issuingWorkerId+" not found.");
        }
        Worker issuingWorker = issuingWorkerOptional.get();
        if(!(issuingWorker.getRole().getName().equals("ADMIN") ||
                issuingWorker.getRole().getName().equals("HEAD_TEACHER"))) {
            throw new Exception("Worker with id "+issuingWorkerId+" is not authorized to perform new worker addition.");
        }
    }

    private List<Worker> getWorkersList(Long issuingWorkerId) throws Exception {
        checkIfIsAdminOrHeadTeacher(issuingWorkerId);

        return workerRepository.findAll();
    }

    @Transactional
    public void removeWorker(Long issuingWorkerId, Long removedWorkerId) throws Exception {
        checkIfIsAdminOrHeadTeacher(issuingWorkerId);
        Optional<Worker> removedWorkerOptional = workerRepository.findById(removedWorkerId);
        if(!removedWorkerOptional.isPresent()) {
            throw new Exception("Worker with id "+removedWorkerId+" not found. Failed to remove.");
        }

        Worker removedWorker = removedWorkerOptional.get();

        workerRepository.deleteById(removedWorker.getId());
    }

    public Worker getWorkerById(Long requestedWorkerId) throws Exception {
        Optional<Worker> workerOptional = workerRepository.findById(requestedWorkerId);
        if(workerOptional.isPresent()) {
            return workerOptional.get();
        }
        else throw new Exception("Worker with id "+requestedWorkerId+" not found.");
    }

}
