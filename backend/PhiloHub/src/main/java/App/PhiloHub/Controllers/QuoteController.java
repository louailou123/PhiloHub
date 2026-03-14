package App.PhiloHub.Controllers;

import App.PhiloHub.Api.ApiResponse;
import App.PhiloHub.Models.Quote;
import App.PhiloHub.Repositories.QuoteRepository;
import App.PhiloHub.Services.QuoteServices;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class QuoteController {
   private final QuoteServices quoteServices;


    @PostMapping("/add/quote")
    public ResponseEntity<?> addQuote (@RequestBody @Valid Quote quote)
    {
            quoteServices.addQuote(quote);
            ApiResponse apiResponse = new ApiResponse<>();
            apiResponse.setMessage("New quote added");
            apiResponse.setSuccess(true);
            apiResponse.setTimestamp(LocalDate.now());
            return ResponseEntity.status(201).body(apiResponse);

    }
}
