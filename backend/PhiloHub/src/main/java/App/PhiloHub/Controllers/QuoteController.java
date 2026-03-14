package App.PhiloHub.Controllers;

import App.PhiloHub.Models.Quote;
import App.PhiloHub.Services.QuoteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/quote")
public class QuoteController {

    private final QuoteService quoteService;

    @GetMapping("/get")
    public List<Quote> getAllQuotes ()
    {
       return quoteService.getAllQuotes();

    }
}
