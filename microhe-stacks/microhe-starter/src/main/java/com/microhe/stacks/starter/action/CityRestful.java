package com.microhe.stacks.starter.action;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.microee.plugin.response.R;
import com.microhe.stacks.starter.domain.CityModel;
import com.microhe.stacks.starter.handler.CityHandler;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/city")
public class CityRestful {


    @Autowired
    private CityHandler cityHandler;

    @GetMapping(value = "/{id}")
    public Mono<R<CityModel>> findCityById(@PathVariable("id") Long id) {
        return Mono.justOrEmpty(R.ok(cityHandler.findCityById(id)));
    }

    @GetMapping()
    public Mono<R<Collection<CityModel>>> findAllCity() {
        return Mono.create(cityMonoSink -> cityMonoSink.success(R.ok(cityHandler.findAllCity())));
        // Flux：实现发布者，并返回 N 个元素，即 List 列表对象
        // return Flux.fromIterable(cityHandler.findAllCity());
    }

    // Mono：实现发布者，并返回 0 或 1 个元素，即单对象
    @PostMapping()
    public Mono<R<Long>> saveCity(@RequestBody CityModel city) {
        return Mono.create(cityMonoSink -> cityMonoSink.success(R.ok(cityHandler.save(city))));
    }

    @PutMapping()
    public Mono<R<Long>> modifyCity(@RequestBody CityModel city) {
        return Mono.create(cityMonoSink -> cityMonoSink.success(R.ok(cityHandler.modifyCity(city))));
    }

    @DeleteMapping(value = "/{id}")
    public Mono<R<Long>> deleteCity(@PathVariable("id") Long id) {
        return Mono.create(cityMonoSink -> cityMonoSink.success(R.ok(cityHandler.deleteCity(id))));
    }

}
