package com.tmt.gradessubmission.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tmt.gradessubmission.pojo.Grade;
import com.tmt.gradessubmission.service.GradeService;

@Controller
public class FormController {
    
    @Autowired
    GradeService gradeService;  //to be able to access the data stored in the grade repo through service class
    
    @GetMapping("/")
    public String getForm(Model model, @RequestParam(required=false) String id){
        //eiher new grade or existing grade object is returned based on the logic in service class
        model.addAttribute("grade", gradeService.getGradeById(id));
        return "form";
    }

    
    @PostMapping("/handleSubmit")
    public String submitForm(@Valid Grade grade, BindingResult result){

        
        if (result.hasErrors()) return "form";  //keep user in from if error in validation, not redirect so as not to lose th BR info
        gradeService.submitGrade(grade);    //saves new form or update it, logic stated in service class
        return "redirect:/grades";  //client is redirected to view the data stored, on a new GET request handled below
    }

    @GetMapping("/grades")
    public String getGrades(Model model){
        model.addAttribute("grades", gradeService.getGrades());
        return "grades";
    }

}
