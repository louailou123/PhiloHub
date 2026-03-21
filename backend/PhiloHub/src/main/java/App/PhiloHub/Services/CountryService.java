package App.PhiloHub.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import App.PhiloHub.Models.Country;
import App.PhiloHub.Repositories.CountryRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;

    public void addCountry(Country country)
    {
        Country savedCountry=new Country();
        savedCountry.setName(country.getName());
        savedCountry.setCode(country.getCode());
        countryRepository.save(savedCountry);
    }

    public void addCountries(List<Country> countries)
    {
        countryRepository.saveAll(countries);
    }

    public List<Country> getAllCountries()
    {
        return countryRepository.findAll();
    }


}
