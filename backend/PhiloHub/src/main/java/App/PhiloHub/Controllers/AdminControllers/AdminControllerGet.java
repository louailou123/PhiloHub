package App.PhiloHub.Controllers.AdminControllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import App.PhiloHub.Services.CountryService;
import App.PhiloHub.Services.FeedbackService;

import App.PhiloHub.Services.PhilosopherService;
import App.PhiloHub.Services.PhilosophyTagService;
import App.PhiloHub.Models.Quote;
import App.PhiloHub.Models.FeedbackType;
import App.PhiloHub.Models.Philosopher;
import App.PhiloHub.Models.PhilosophyTag;
import App.PhiloHub.Models.Country;


import App.PhiloHub.Services.QuoteService;

import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/admin/get")
@RequiredArgsConstructor
public class AdminControllerGet {

   private final QuoteService quoteService;
   private final PhilosopherService philosopherService;
   private final PhilosophyTagService philosophyTagService;
   private final CountryService countryService;
   private final FeedbackService feedbackService;

  @GetMapping("/quotes")
  public List<Quote> getQuotes()
  {
    return quoteService.getAllQuotes();
  }

  @GetMapping("/philosophers")
  public List<Philosopher> getPhilosophers()
  {
    return philosopherService.getAllPhilosophers();
  }

  @GetMapping("/philosophy_tags")
  public List<PhilosophyTag> getPhilosophyTags()
  {
    return philosophyTagService.getAllPhilosophyTags();
  }

  @GetMapping("/countries")
  public List<Country> getCountries()
  {
    return countryService.getAllCountries();
  }

  @GetMapping("/feedback_types")
  public List<FeedbackType> getFeedbackTypes()
  {
    return feedbackService.getAllFeedbackTypes();
  }
    
}
