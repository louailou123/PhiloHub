package App.PhiloHub.Services;


import java.util.List;

import org.springframework.stereotype.Service;

import App.PhiloHub.Models.Philosopher;
import App.PhiloHub.Repositories.PhilosopherRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PhilosopherService {
    private final PhilosopherRepository philosopherRepository;

    public void addPhilosopher(Philosopher philosopher)
    {
        Philosopher savedPhilosopher=new Philosopher();
        savedPhilosopher.setFullName(philosopher.getFullName());
        philosopherRepository.save(savedPhilosopher);
    }

    public void addPhilosophers(List<Philosopher> philosophers)
    {
        philosopherRepository.saveAll(philosophers);
    }

    public List<Philosopher> getAllPhilosophers()
    {
        return philosopherRepository.findAll();
    }


}
