package com.example.vacuumtubee.finalapproach;

/**
 * Created by Vacuum Tubee on 5/31/2016.
 */
public class SignUpValidate {
    String name, password, confirmPass;

    public SignUpValidate(String name, String password, String confirmPass) {
        this.password = password;
        this.confirmPass = confirmPass;
        this.name = name;
    }
    public boolean passWordMatch(){
        if(!password.equals(confirmPass)){
            return false;
        }
        return true;
    }

    public boolean passLength(){
        if(password.length()<4){

            return false;
        }
        return true;

    }

    public boolean nameLength(){
        if(name.length()==0){
            return false;

        }
        return true;
    }
    public  boolean validate(){
        if(nameLength() && passLength() && passWordMatch()){
            return  true;
        }
        return  false;
    }




}
