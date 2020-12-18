package com.gmibank.stepDefinitions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmibank.pojos.Customer;
import com.gmibank.utilities.ConfigurationReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.Response.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class CustomerApiStepDef {

    Response response;
    Customer[] customers;

    @Given("user read all customer and sets response using to api end point {string}")
    public void user_read_all_customer_and_sets_response_using_to_api_end_point(String api_endpoint) {
         response=given().headers(
                "Authorization",
                "Bearer " + ConfigurationReader.getProperty("token"),
                "Content-Type",
                ContentType.JSON,
                "Accept",
                ContentType.JSON).
                    and().
                    queryParam("size", 1500) //Customer'i alirken size'i belirtir
                .when()
                                    .get(api_endpoint)
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

       //  System.out.println("FirstName: " + customers[0].getFirstName());

      for (int i=0; i< customers.length;i++) {
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
