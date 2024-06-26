package com.yufeng.springboot;

import com.yufeng.springboot.model.Event;
import com.yufeng.springboot.model.Group;
import com.yufeng.springboot.model.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collections;
import java.util.stream.Stream;

@Component
class Initializer implements CommandLineRunner {

    @Autowired
    private GroupRepository repository;

    @Override
    public void run(String... strings) {
        Stream.of("Seattle JUG", "Denver JUG", "Dublin JUG",
                "London JUG").forEach(name ->
                repository.save(new Group(name))
        );

        Group djug = repository.findByName("Seattle JUG");
        if(djug != null) {
            Event e = Event.builder().title("Micro Frontends for Java Developers")
                    .description("JHipster now has microfrontend support!")
                    .date(Instant.parse("2022-09-13T17:00:00.000Z"))
                    .build();
            djug.setEvents(Collections.singleton(e));
            repository.save(djug);
        }

        repository.findAll().forEach(System.out::println);
    }
}