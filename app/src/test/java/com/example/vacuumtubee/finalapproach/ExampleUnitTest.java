package com.example.vacuumtubee.finalapproach;



import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;


/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {

    SignUpValidate signUpValidate = new SignUpValidate("abcd","1234","1234");

    @Test
    public void nameCorrect() throws Exception {

        //

        try {
            assertEquals("NameValidate",signUpValidate.nameLength(),true);
        }catch (Exception e){
            System.err.println("Name Not valid");
        }




    }

    @Test
    public void passLenCorrect(){
        try {
            assertEquals("PassLenvalidate",signUpValidate.passLength(),true);
        }catch (Exception e){
            System.err.println("PassLen Not valid");
        }
    }
    @Test
    public void PassCompare(){
        try {
            assertEquals("PassMatchvalidate",signUpValidate.passWordMatch(),true);
        }catch (Exception e){
            System.err.println("Password Didnt match");
        }
    }
    @Test
    public void OverallValidate(){
        try {
            assertEquals("Overallvalidate",signUpValidate.validate(),true);
        }catch (Exception e){
            System.err.println("Overall not valid");
        }

    }

}