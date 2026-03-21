package App.PhiloHub.Services;


import java.util.List;

import org.springframework.stereotype.Service;

import App.PhiloHub.Models.Quote;
import App.PhiloHub.Repositories.QuoteRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuoteService {

    private  final QuoteRepository quoteRepository;

    public void addQuote (Quote quote)
    {
        Quote addedQuote= new Quote();
        addedQuote.setText(quote.getText());
        addedQuote.setPhilosopher(quote.getPhilosopher());
        addedQuote.setEra(quote.getEra());
        quoteRepository.save(addedQuote);
    }

    public void addQuotes(List<Quote> quotes)
    {
        quoteRepository.saveAll(quotes);
    }

    public List<Quote> getAllQuotes ()
    {
        return quoteRepository.findAll();

    }

}
