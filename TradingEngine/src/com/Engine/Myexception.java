package com.Engine;

public class Myexception extends  Exception {
    String str1;

    Myexception(String str2) {
        str1=str2;
    }
    public String toString(){
        return ("MyException Occurred: "+str1) ;
    }
}
