package App.PhiloHub.Services;


import App.PhiloHub.Models.Quote;
import App.PhiloHub.Repositories.QuoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuoteServices {

    private  final QuoteRepository quoteRepository;

    public void addQuote (Quote quote)
    {
        Quote addedQuote= new Quote();
        addedQuote.setText(quote.getText());
        addedQuote.setPhilosopher(quote.getPhilosopher());
        addedQuote.setEra(quote.getEra());
        quoteRepository.save(addedQuote);
    }

}
