package com.eagle.controller;

import com.eagle.controller.utils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookcController {

    @GetMapping("/{number}")
    public int getById(@PathVariable Integer number){

        return number==Result.correct?Result.correct:Result.incorrect;
    }
}
