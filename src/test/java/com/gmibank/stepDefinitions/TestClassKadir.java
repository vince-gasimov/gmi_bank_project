package com.gmibank.stepDefinitions;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.gmibank.Api.ApiUtilities.ApiCountriesUtilities;
import com.gmibank.Api.ApiUtilities.CustomerApiUtilities;
import org.testng.annotations.Test;

public class TestClassKadir {

    @Test
    public void test1(){
        CustomerApiUtilities.getAllCustomers().prettyPrint();
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


}
