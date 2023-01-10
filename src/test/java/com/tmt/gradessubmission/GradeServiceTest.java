package com.tmt.gradessubmission;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.tmt.gradessubmission.pojo.Grade;
import com.tmt.gradessubmission.repository.GradeRepository;
import com.tmt.gradessubmission.service.GradeService;

@RunWith(MockitoJUnitRunner.class)
public class GradeServiceTest {
    
    @Mock
    private GradeRepository gradeRepository;

    @InjectMocks
    private GradeService gradeService;

    @Test
    public void getGradesTest(){
        //testing getGrades() in service class wh returns data from repo
        //arrange mock data as below to be returned during testing
        when(gradeRepository.getGrades()).thenReturn(Arrays.asList(
            //giving a mock list of grades to be returned since real repo cant be accessed during test
            new Grade("Amina", "Maths", "B+"),
            new Grade("Bashir", "Business", "A+")
        ));
        
        //act- call the method to be tested, but save value in a variable
        List<Grade> result = gradeService.getGrades();

        //assert - check that the method is actually getting grades from gradeRepo(mock one)
        //below compares the expected output with the actual value
        assertEquals("Amina", result.get(0).getName());
        assertEquals("Business", result.get(1).getSubject());
    }

    //check that getGradeIndex(id) returns the correct index
    @Test
    public void testGetGradeIndex(){
        //the method to be tested calls getGrades, wh calls repo, need mock repo data as above
        //arrange mock data as below to be returned during testing
        when(gradeRepository.getGrades()).thenReturn(Arrays.asList(
            //giving a mock list of grades to be returned since real repo cant be accessed during test
            new Grade("Amina", "Maths", "B+"),
            new Grade("Bashir", "Business", "A+")
        ));
        //also needs data from result to get index being tested
        List<Grade> result = gradeService.getGrades();

        //act- call the method to be tested, but save value in a variable
        int valid = gradeService.getGradeIndex(result.get(0).getId());//give id of obj at ind 0 to test if ind is found
        int notFound = gradeService.getGradeIndex("123"); //give random id, expected not to find an index

        assertEquals(0, valid);
        assertEquals(Constants.NOT_FOUND, notFound);
    }

    //check getGradeById() is returning correct grade
    @Test
    public void testGetGradeById(){
        //arrange data
        Grade grade = new Grade("Amina", "Maths", "B+");
        when(gradeRepository.getGrades()).thenReturn(Arrays.asList(grade));
        when(gradeRepository.getGrade(0)).thenReturn(grade);
        String id = grade.getId();

        //act
        Grade resultGrade = gradeService.getGradeById(id);

        assertEquals(grade, resultGrade);

    }
    //check submitGrade() can actually adds a grade
    @Test
    public void testSubmitGradeAddsGrade(){
        //arrange
        Grade grade = new Grade("Amina", "Maths", "B+");
        when(gradeRepository.getGrades()).thenReturn(Arrays.asList(grade));  //return mock empty list
        //when(gradeRepository.getGrade(0)).thenReturn(grade); removed bcz it is unnecessary and causes test to fail
        //create new grade
        Grade newGrade = new Grade("Bashir", "Business", "A+");
        //act
        gradeService.submitGrade(newGrade);

        //assert/check if submitGrade calls addGrade at least once when new Grade is given to it
        verify(gradeRepository, times(1)).addGrade(newGrade);

    }

    //check if submitGrade updates an existing grade
    @Test
    public void testSubmitGradeUpdatesGrade(){
        //arrange
        Grade grade = new Grade("Amina", "Maths", "B+");
        when(gradeRepository.getGrades()).thenReturn(Arrays.asList(grade));  //return mock empty list
        
        grade.setName("Aisha");
        //act
        gradeService.submitGrade(grade);
        verify(gradeRepository, times(1)).updateGrade(grade, 0);
    }
}

