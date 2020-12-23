package com.gmibank.stepDefinitions;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.gmibank.Api.ApiUtilities.ApiApplicantsUtilities;
import com.gmibank.Api.ApiUtilities.ApiCountriesUtilities;
import com.gmibank.Api.ApiUtilities.ApiCustomerUtilities;
import com.gmibank.Api.ApiUtilities.ApiStatesUtilities;
import org.testng.annotations.Test;

public class TestClassKadir {

    @Test
    public void test1(){
        ApiCustomerUtilities.getAllCustomers().prettyPrint();
    }

    @Test
    public void test2() throws JsonProcessingException {
        ApiCountriesUtilities.postOneCountry("Anil").prettyPrint();
    }

    @Test
    public void test3(){
        ApiCountriesUtilities.updateCountry(60113, "emreyeni").prettyPrint();
    }

    @Test
    public void test4(){
        ApiCountriesUtilities.getSpecifiedCountryInfo(60113).prettyPrint();
    }

    @Test
    public void test5(){
        ApiStatesUtilities.getAllStates().prettyPrint();
    }

    @Test
    public void test6() throws JsonProcessingException {
        ApiStatesUtilities.postOneState("Bayern").prettyPrint();
    }

    @Test
    public void test7(){
        ApiStatesUtilities.deleteOneState("/60664");
    }

    @Test
    public void test8(){
        ApiApplicantsUtilities.getAllApplicants().prettyPrint();
    }

    @Test
    public void test9(){
        ApiStatesUtilities.getSpecifiedStateInfo(60667);
    }


}
