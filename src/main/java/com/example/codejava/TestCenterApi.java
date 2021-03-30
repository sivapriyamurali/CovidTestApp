package com.example.codejava;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("hc")
@AllArgsConstructor
public class TestCenterApi {

    private final TestCenterRepository centerRepository;

    @GetMapping("test-center")
    public String home() {
        return "HC_Home";
    }

    @GetMapping("test-centers")
    @ResponseBody
    public List<TestCenter> getCenters() {
        return centerRepository.findAll();
    }

}
