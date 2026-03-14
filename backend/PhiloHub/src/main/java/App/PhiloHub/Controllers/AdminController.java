package App.PhiloHub.Controllers;

import App.PhiloHub.Api.ApiResponse;
import App.PhiloHub.Models.Quote;
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

}
