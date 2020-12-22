package com.gmibank.stepDefinitions.ApiStepDefs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmibank.Api.pojos.Customer;
import com.gmibank.utilities.ConfigurationReader;
import com.gmibank.utilities.ReadTxt;
import com.gmibank.utilities.WriteToTxt;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import org.junit.Assert;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiCustomerStepDef {

    Response response;
    Customer[] customers;

    @Given("user read all customer and sets response using to api end point {string}")
    public void user_read_all_customer_and_sets_response_using_to_api_end_point(String customer_Api_endpoint) {
         response=given().headers(
                "Authorization",
                "Bearer " + ConfigurationReader.getProperty("token"),
                "Content-Type",
                ContentType.JSON,
                "Accept",
                ContentType.JSON).
                    and().
                    queryParam("size", 20) //Customer'i alirken size'i belirtir
                .when()
                                    .get(customer_Api_endpoint)
                .then()
                                    .assertThat()   //assertThat() kullanmasak da olur
                                    .contentType(ContentType.JSON)
                                    .statusCode(200)
                                    .extract()
                                    .response();
        //response.prettyPrint();

        //Eger post, put, patch request yapiyorsak accept type yerine yerine ==> contentType(ContentType.JSON) kullanilmali
        //get ve delete de ==> accept type kullanabiliriz
        /*
                                        accept(ContentType.JSON).
                                        auth().
                                oauth2(ConfigurationReader.getProperty("token")).
                                contentType(ContentType.JSON)
         */
    }

    List<String> allSsn = new ArrayList<>();
    @Given("user deserialization customer data json to java pojo")
    public void user_deserialization_customer_data_json_to_java_pojo() throws IOException {

        /*
        deserialization icin;
        1.Yöntem ==> JsonPath - List<Map<String, Object>>
        2.Yöntem ==> Pojo
        3.Yöntem ==> google Gson
         */
        /*1.Yöntem ==> JsonPath
        List<Map<String, Object>> allCustomerData = json.getList("$");
        System.out.println("Java List Map response: " + allCustomerData);
        System.out.println("First customer info: " + allCustomerData.get(0));
        System.out.println("First customer firstname: " + allCustomerData.get(0).get("firstName"));
         */


        ObjectMapper objectMapper = new ObjectMapper();
        customers=objectMapper.readValue(response.asString(),Customer[].class);

        for (int i =0; i<customers.length; i++){
            allSsn.add(customers[i].getSsn());
        }
        System.out.println(allSsn);

        WriteToTxt.saveDataInFile("allCustomerSsn1.txt", customers);
        List<String > customerSsnList = ReadTxt.returnCustomerSNNList("allCustomerSsn1.txt");
        Assert.assertEquals("not verify", allSsn, customerSsnList);

/*        WriteToTxt.saveDataInFile("SsnFile.txt", customers);
        List<String > allCustomer = ReadTxt.returnCustomerSNNList("SsnFile.txt");
        Assert.assertEquals("not verified", allSsn, allCustomer);

       //  System.out.println("FirstName: " + customers[0].getFirstName());

/*      for (int i=0; i< customers.length;i++) {
            System.out.println("ID: " + customers[i].getId());
        }

        System.out.println("************************");

 /*       for (int i=0;i<customers.length;i++) {
            if (customers[i].getUser()!=null) {
                System.out.println("LastName: " + customers[i].getUser().getLastName());
            }
        }

        System.out.println("************************");
*/
/*        for(int i=0; i< customers.length; i++){
            System.out.println("Customer SSN: " + customers[i].getSsn());
            System.out.println("Customer mobilePhoneNumber: " + customers[i].getMobilePhoneNumber());
            if (customers[i].getCountry()!=null)
            System.out.println("Country Name: " + customers[i].getCountry().getName());
        }
*/
    }

    @Then("user validates all data")
    public void user_validates_all_data() {

    }



}
