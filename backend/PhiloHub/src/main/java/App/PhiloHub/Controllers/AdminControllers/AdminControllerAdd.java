package App.PhiloHub.Controllers.AdminControllers;

import App.PhiloHub.Api.ApiResponse;
import App.PhiloHub.Models.Philosopher;
import App.PhiloHub.Models.Quote;
import App.PhiloHub.Services.QuoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import App.PhiloHub.Models.PhilosophyTag;
import App.PhiloHub.Services.PhilosopherService;
import App.PhiloHub.Services.PhilosophyTagService;
import App.PhiloHub.Services.CountryService;
import App.PhiloHub.Models.Country;
import App.PhiloHub.Models.FeedbackType;
import App.PhiloHub.Services.FeedbackService;
import java.time.LocalDate;

@RestController
@RequestMapping("/admin/add")
@RequiredArgsConstructor
public class AdminControllerAdd {
   private final QuoteService quoteService;
   private final PhilosopherService philosopherService;
   private final PhilosophyTagService philosophyTagService;
   private final CountryService countryService;
   private final FeedbackService feedbackService;


    @PostMapping("/quote")
    public ResponseEntity<ApiResponse<Quote>> addQuote (@RequestBody @Valid Quote quote)
    {
            quoteService.addQuote(quote);
            ApiResponse<Quote> apiResponse = new ApiResponse<>();
            apiResponse.setMessage("New quote added");
            apiResponse.setSuccess(true);
            apiResponse.setTimestamp(LocalDate.now());
            return ResponseEntity.status(201).body(apiResponse);

    }


    @PostMapping("/philosopher")
    public ResponseEntity<ApiResponse<Philosopher>> addPhilosopher (@RequestBody @Valid Philosopher philosopher)
    {
        philosopherService.addPhilosopher(philosopher);
        ApiResponse<Philosopher> apiResponse=new ApiResponse<>();
        apiResponse.setMessage("new philosopher added");
        apiResponse.setSuccess(true);
        apiResponse.setTimestamp(LocalDate.now());
        return ResponseEntity.status(201).body(apiResponse);
    }
    
    @PostMapping("/philosophy_tags")
    public ResponseEntity<ApiResponse<PhilosophyTag>> addPhilosophyTag (@RequestBody @Valid PhilosophyTag philosophyTag)
    {
        philosophyTagService.addPhilosophyTag(philosophyTag);
        ApiResponse<PhilosophyTag> apiResponse=new ApiResponse<>();
        apiResponse.setMessage("new philosophy tag added");
        apiResponse.setSuccess(true);
        apiResponse.setTimestamp(LocalDate.now());
        return ResponseEntity.status(201).body(apiResponse);
    }

    @PostMapping("/country")
    public ResponseEntity<ApiResponse<Country>> addCountry (@RequestBody @Valid Country country)
    {
        countryService.addCountry(country);
        ApiResponse<Country> apiResponse=new ApiResponse<>();
        apiResponse.setMessage("new country added");
        apiResponse.setSuccess(true);
        apiResponse.setTimestamp(LocalDate.now());
        return ResponseEntity.status(201).body(apiResponse);
    }

    @PostMapping("/feedback_type")
    public ResponseEntity<ApiResponse<FeedbackType>> addFeedbackType (@RequestBody @Valid FeedbackType feedbackType)
    {
        feedbackService.addFeedbackType(feedbackType);
        ApiResponse<FeedbackType> apiResponse=new ApiResponse<>();
        apiResponse.setMessage("new feedback type added");
        apiResponse.setSuccess(true);
        apiResponse.setTimestamp(LocalDate.now());
        return ResponseEntity.status(201).body(apiResponse);
    }
    
}
