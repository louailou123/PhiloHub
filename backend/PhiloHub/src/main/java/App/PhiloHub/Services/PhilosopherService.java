package App.PhiloHub.Services;


import App.PhiloHub.Models.Philosopher;
import App.PhiloHub.Repositories.PhilosopherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
