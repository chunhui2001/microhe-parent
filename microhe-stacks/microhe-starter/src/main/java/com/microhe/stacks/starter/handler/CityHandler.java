package com.microhe.stacks.starter.handler;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.microhe.stacks.starter.domain.CityModel;
import com.microhe.stacks.starter.domain.CityRepository;

@Component
public class CityHandler {

    private final CityRepository cityRepository;

    @Autowired
    public CityHandler(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public Long save(CityModel city) {
        return cityRepository.save(city);
    }

    public CityModel findCityById(Long id) {
        return cityRepository.findCityById(id);
    }

    public Collection<CityModel> findAllCity() {
        return cityRepository.findAll();
    }

    public Long modifyCity(CityModel city) {
        return cityRepository.updateCity(city);
    }

    public Long deleteCity(Long id) {
        return cityRepository.deleteCity(id);
    }
}
