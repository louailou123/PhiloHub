package App.PhiloHub.Controllers;

import App.PhiloHub.Api.ApiResponse;
import App.PhiloHub.Models.Philosopher;
import App.PhiloHub.Models.Quote;
import App.PhiloHub.Services.PhilosopherService;
import App.PhiloHub.Services.QuoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
   private final QuoteService quoteService;
   private final PhilosopherService philosopherService;


    @PostMapping("/add/quote")
    public ResponseEntity<?> addQuote (@RequestBody @Valid Quote quote)
    {
            quoteService.addQuote(quote);
            ApiResponse apiResponse = new ApiResponse<>();
            apiResponse.setMessage("New quote added");
            apiResponse.setSuccess(true);
            apiResponse.setTimestamp(LocalDate.now());
            return ResponseEntity.status(201).body(apiResponse);

    }
    @PostMapping("/add/philosopher")
    public ResponseEntity<?> addPhilosopher (@RequestBody @Valid Philosopher philosopher)
    {
        philosopherService.addPhilosopher(philosopher);
        ApiResponse apiResponse=new ApiResponse<>();
        apiResponse.setMessage("new philosopher added");
        apiResponse.setSuccess(true);
        apiResponse.setTimestamp(LocalDate.now());
        return ResponseEntity.status(201).body(apiResponse);
    }

}
