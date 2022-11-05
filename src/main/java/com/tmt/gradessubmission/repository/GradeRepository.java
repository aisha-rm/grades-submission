package com.tmt.gradessubmission.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.tmt.gradessubmission.Grade;

@Repository  //to turn class to a bean
public class GradeRepository {
    
    //arrayList to store data f rom form
    private List<Grade> studentGrades = new ArrayList<>(); 

    //method to return a grade by passing its index.  the method does a Read operation on the data store
    public Grade getGrade(int index){
        return studentGrades.get(index);
    }

    //for the create operation 
    public void addGrade(Grade grade){
        studentGrades.add(grade);
    }

    //update operation
    public void updateGrade(Grade grade, int index){
        studentGrades.set(index, grade);
    }

    //another read op to get all grades
    public List<Grade> getGrades(){
        return studentGrades;
    }
}
