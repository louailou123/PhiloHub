package App.PhiloHub.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import App.PhiloHub.Models.FeedbackType;
import App.PhiloHub.Repositories.FeedbackTypeRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedbackTypeRepository feedbackTypeRepository;

    public void addFeedbackType(FeedbackType feedbackType)
    {
        FeedbackType savedFeedbackType=new FeedbackType();
        savedFeedbackType.setFeedbackTypeName(feedbackType.getFeedbackTypeName());
        savedFeedbackType.setFeedbackTypeDescription(feedbackType.getFeedbackTypeDescription());
        feedbackTypeRepository.save(savedFeedbackType);
    }
    
    public void addFeedbackTypes(List<FeedbackType> feedbackTypes)
    {
        feedbackTypeRepository.saveAll(feedbackTypes);
    }

    
    public List<FeedbackType> getAllFeedbackTypes()
    {
        return feedbackTypeRepository.findAll();
    }


}
