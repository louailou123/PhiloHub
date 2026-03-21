package App.PhiloHub.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import App.PhiloHub.Models.PhilosophyTag;
import App.PhiloHub.Repositories.PhilosophyTagRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PhilosophyTagService {
    private final PhilosophyTagRepository philosophyTagRepository;

    public void addPhilosophyTag(PhilosophyTag philosophyTag)
    {
        PhilosophyTag savedPhilosophyTag=new PhilosophyTag();
        savedPhilosophyTag.setName(philosophyTag.getName());
        philosophyTagRepository.save(savedPhilosophyTag);
    }

    public void addPhilosophyTags(List<PhilosophyTag> philosophyTags)
    {
        philosophyTagRepository.saveAll(philosophyTags);
    }

    public List<PhilosophyTag> getAllPhilosophyTags()
    {
        return philosophyTagRepository.findAll();
    }

}
