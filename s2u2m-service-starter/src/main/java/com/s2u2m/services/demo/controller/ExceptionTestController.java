package com.s2u2m.services.demo.controller;

import com.s2u2m.services.core.error.exception.ClientValidationException;
import com.s2u2m.services.demo.error.ErrorCode;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;

@Validated
@RestController
@RequestMapping("/exception")
public class ExceptionTestController {

    @GetMapping("/{id}")
    public Integer getInput(@PathVariable @Min(3) int id) {
        if (id > 10) {
            throw new ClientValidationException(ErrorCode.INPUT_INVALID);
        }
        return id;
    }
}
