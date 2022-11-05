package com.tmt.gradessubmission.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmt.gradessubmission.Constants;
import com.tmt.gradessubmission.Grade;
import com.tmt.gradessubmission.repository.GradeRepository;

@Service
public class GradeService {

    @Autowired      //injects the Repoclass bean instead of creating new object and avoiding tight coupling
    GradeRepository gradeRepo;
    
     //return a grade by passing its index.  the method accesses the read operation in repo class
     //all methods in repo class are replicated
     public Grade getGrade(int index){
        return gradeRepo.getGrade(index);
    }

    public void addGrade(Grade grade){
        gradeRepo.addGrade(grade);
    }

    public void updateGrade(Grade grade, int index){
        gradeRepo.updateGrade(grade, index);
    }

    public List<Grade> getGrades(){
        return gradeRepo.getGrades();
    }

    //LOGIC functions:
    /*create function to get the index of the grade object whose id was passed in the GET request
    that is in order to find appropriately query the datastore
    moved to this class as it is part of decision making
    */
    public int getGradeIndex(String id){
        for (int i=0; i<getGrades().size(); i++){
            if (getGrades().get(i).getId().equals(id)) return i;
             }
        return Constants.NOT_FOUND; //returned if the index is not found
    }

    //moved the logic in get request ("/")
    public Grade getGradeById(String id){
        int index = getGradeIndex(id);  //find index of the given param in datastore
        if (index == Constants.NOT_FOUND){
            return new Grade();
        }else{
            return getGrade(index);  //use the method from gradeRepo to get the grade at that index.
        }
    }    

    public void submitGrade(Grade grade){
        int index=getGradeIndex(grade.getId());
        if (index == Constants.NOT_FOUND){
            addGrade(grade);
        }else{
           updateGrade(grade, index);
        }
    }    
}

