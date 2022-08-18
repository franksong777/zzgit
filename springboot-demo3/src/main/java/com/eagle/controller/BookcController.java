package com.eagle.controller;

import com.eagle.controller.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j              // for logging  after dependency lombok in pom.xml , straight use log.info(); below
@RestController
@RequestMapping("/books")
public class BookcController {

    @GetMapping("/{number}")
    public int getById(@PathVariable Integer number){

        log.debug("debug...");
        log.info("info...");
        log.warn("warn...");
        log.error("error...");
        System.out.println("hot deploy test ...");
        System.out.println("hot deploy test ...");

        return number==Result.correct?Result.correct:Result.incorrect;
    }
}
