package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.xml.ForecastSeedDTO;
import softuni.exam.models.dto.xml.ForecastSeedRoodDTO;
import softuni.exam.models.entity.City;
import softuni.exam.models.entity.DayOfWeek;
import softuni.exam.models.entity.Forecast;
import softuni.exam.repository.CityRepository;
import softuni.exam.repository.ForecastRepository;
import softuni.exam.service.ForecastService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ForecastServiceImpl implements ForecastService {

    private final String PATH_INPUT = "src/main/resources/files/xml/forecasts.xml";

    private final ForecastRepository forecastRepository;
    private final CityRepository cityRepository;
    private final Gson gson;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public ForecastServiceImpl(ForecastRepository forecastRepository, CityRepository cityRepository, Gson gson, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.forecastRepository = forecastRepository;
        this.cityRepository = cityRepository;
        this.gson = gson;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.forecastRepository.count() > 0;
    }

    @Override
    public String readForecastsFromFile() throws IOException {
        return new String(Files.readAllBytes(Path.of(PATH_INPUT)));
    }

    @Override
    public String importForecasts() throws  JAXBException {
        StringBuilder stringBuilder = new StringBuilder();

        ForecastSeedRoodDTO forecastSeedRoodDTO = this.xmlParser.parse(ForecastSeedRoodDTO.class, PATH_INPUT);
        for (ForecastSeedDTO forecastSeedDTO : forecastSeedRoodDTO.getForecastSeedDTOList()) {
            Optional<City> cityOptional = this.cityRepository.findById(forecastSeedDTO.getCity());

            DayOfWeek dayOfWeek = null;
            try {
                dayOfWeek = DayOfWeek.valueOf(forecastSeedDTO.getDayOfWeek());
            } catch (IllegalArgumentException | NullPointerException e) {
                stringBuilder.append("Invalid forecast").append(System.lineSeparator());
                continue;
            }

            boolean isDayOfWeekExistForTheCity = false;

            if (cityOptional.isPresent()) {
                if (!cityOptional.get().getForecastSet().isEmpty()) {
                    for (Forecast forecast : cityOptional.get().getForecastSet()) {
                        if (String.valueOf(forecast.getDayOfWeek()).equals(String.valueOf(dayOfWeek))) {
                            isDayOfWeekExistForTheCity = true;
                        }
                    }
                }
            }
            if (!this.validationUtil.isValid(forecastSeedDTO) || cityOptional.isEmpty() || isDayOfWeekExistForTheCity) {
                stringBuilder.append("Invalid forecast").append(System.lineSeparator());
                continue;
            }
            Forecast forecast = this.modelMapper.map(forecastSeedDTO, Forecast.class);
            forecast.setDayOfWeek(dayOfWeek);
            forecast.setCity(cityOptional.get());
            City city = cityOptional.get();
            city.getForecastSet().add(forecast);
            this.forecastRepository.saveAndFlush(forecast);
            stringBuilder.append(String.format("Successfully import forecast %s - %.2f", forecast.getDayOfWeek(), forecast.getMaxTemperature())).append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }

    @Override
    public String exportForecasts() {
        return this.forecastRepository.findSundayForecastsInSmallCities(DayOfWeek.SUNDAY, 150000)
                .stream()
                .map(forecast -> String.format("City: %s:\n" +
                                "-min temperature: %.2f\n" +
                                "--max temperature: %.2f\n" +
                                "---sunrise: %s\n" +
                                "----sunset: %s\n",
                        forecast.getCity().getCityName(),
                        forecast.getMinTemperature(),
                        forecast.getMaxTemperature(),
                        forecast.getSunrise(),
                        forecast.getSunset()))
                .collect(Collectors.joining());
    }
}
