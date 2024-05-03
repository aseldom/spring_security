package com.zaurtregulov.spring.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeController {

    @GetMapping("/")
    public String getAllEmployees() {
        return "view_all_employees";
    }

    @GetMapping("/hr_info")
    public String forHrInfo() {
        return "view_for_hr";
    }

    @GetMapping("/manager_info")
    public String forManagerInfo() {
        return "view_for_manager";
    }
}
