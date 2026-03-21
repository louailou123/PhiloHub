package App.PhiloHub.Controllers.AdminControllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import App.PhiloHub.Api.ApiResponse;
import App.PhiloHub.Models.Country;
import App.PhiloHub.Models.FeedbackType;
import App.PhiloHub.Models.Philosopher;
import App.PhiloHub.Models.PhilosophyTag;
import App.PhiloHub.Models.Quote;
import App.PhiloHub.Services.CountryService;
import App.PhiloHub.Services.FeedbackService;
import App.PhiloHub.Services.PhilosopherService;
import App.PhiloHub.Services.PhilosophyTagService;
import App.PhiloHub.Services.QuoteService;
import lombok.RequiredArgsConstructor;
import java.time.LocalDate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;


@RestController
@RequestMapping("/admin/add/many")
@RequiredArgsConstructor
public class AdminControllerAddMany {
private final QuoteService quoteService;
   private final PhilosopherService philosopherService;
   private final PhilosophyTagService philosophyTagService;
   private final CountryService countryService;
   private final FeedbackService feedbackService;

   @PostMapping("/quotes")
   public ResponseEntity<ApiResponse<Quote>> addQuotes(@RequestBody List<Quote> quotes)
   { 
    quoteService.addQuotes(quotes);
    ApiResponse<Quote> apiResponse=new ApiResponse<>();
    apiResponse.setMessage("New quotes added");
    apiResponse.setSuccess(true);
    apiResponse.setTimestamp(LocalDate.now());
    return ResponseEntity.status(201).body(apiResponse);
     
   }

   @PostMapping("/philosophers")
   public ResponseEntity<ApiResponse<Philosopher>> addPhilosophers(@RequestBody List<Philosopher> philosophers)
   { 
    philosopherService.addPhilosophers(philosophers);
    ApiResponse<Philosopher> apiResponse=new ApiResponse<>();
    apiResponse.setMessage("New philosophers added");
    apiResponse.setSuccess(true);
    apiResponse.setTimestamp(LocalDate.now());
    return ResponseEntity.status(201).body(apiResponse);
     
   }

   @PostMapping("/philosophy_tags")
   public ResponseEntity<ApiResponse<PhilosophyTag>> addPhilosophyTags(@RequestBody List<PhilosophyTag> philosophyTags)
   { 
    philosophyTagService.addPhilosophyTags(philosophyTags);
    ApiResponse<PhilosophyTag> apiResponse=new ApiResponse<>();
    apiResponse.setMessage("New philosophy tags added");
    apiResponse.setSuccess(true);
    apiResponse.setTimestamp(LocalDate.now());
    return ResponseEntity.status(201).body(apiResponse);
     
   }

   @PostMapping("/countries")
   public ResponseEntity<ApiResponse<Country>> addCountries(@RequestBody List<Country> countries)
   { 
    countryService.addCountries(countries);
    ApiResponse<Country> apiResponse=new ApiResponse<>();
    apiResponse.setMessage("New countries added");
    apiResponse.setSuccess(true);
    apiResponse.setTimestamp(LocalDate.now());
    return ResponseEntity.status(201).body(apiResponse);
     
   }

   @PostMapping("/feedback_types")
   public ResponseEntity<ApiResponse<FeedbackType>> addFeedbackTypes(@RequestBody List<FeedbackType> feedbackTypes)
   { 
    feedbackService.addFeedbackTypes(feedbackTypes);
    ApiResponse<FeedbackType> apiResponse=new ApiResponse<>();
    apiResponse.setMessage("New feedback types added");
    apiResponse.setSuccess(true);
    apiResponse.setTimestamp(LocalDate.now());
    return ResponseEntity.status(201).body(apiResponse);
     
   }
}
