package com.gmibank.utilities;

import com.gmibank.api.pojos.Country;
import com.gmibank.api.pojos.Customer;
import com.gmibank.api.pojos.States;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class WriteToTxt {


    //saveDataInFile
    public static void saveDataInFile(String fileName, Customer[] customers)  {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            for (int i=0;i<customers.length;i++)

                writer.append(customers[i].getSsn() + ",\n");

            writer.close();
        } catch(Exception e){

        }
    }

    //saveDataInFileWithSSN
    public static void saveDataInFileWithSSN(String fileName, Customer customers)  {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));

            writer.append(customers.getSsn());

            writer.close();
        } catch(Exception e){

        }
    }

    //saveDataInFileWithUserInfo
    public static void saveDataInFileWithUserInfo(String fileName, Customer customer)  {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));

            writer.append(customer.getUser().getFirstName());

            writer.close();
        } catch(Exception e){

        }
    }

    //saveDataInFileWithAllCustomerInfo
    public static void saveDataInFileWithAllCustomerInfo(String fileName, Customer[] customers)  {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));


            for (int i =0; i<customers.length;i++){

                writer.append(customers[i].getFirstName() + " , " + customers[i].getLastName() + " , " + customers[i].getCity() + " , " + customers[i].getAddress() + "\n");
                if(customers[i].getUser() != null)
                    writer.append(customers[i].getUser().getFirstName());
                if(customers[i].getCountry() != null)
                    writer.append(customers[i].getCountry().getName() + "\n");

            }

            writer.close();
        } catch(Exception e){

        }
    }

    //saveAllStates
    public static void saveAllStates(String fileName, States[] states)  {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));

            for (int i=0; i<states.length; i++)
                writer.append(states[i].getName() + " , " + states[i].getId() + "\n");

            writer.close();
        } catch(Exception e){

        }
    }

    //saveAllStates2
    public static void saveAllStates2(String fileName, States[] states)  {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));

            for (int i=0; i<states.length; i++)
                writer.append(states[i].getName() + " , " + states[i].getId() + "\n");

            writer.close();
        } catch(Exception e){

        }
    }

    //saveAllCountries
    public static void saveAllCountries(String fileName, Country[] countries)  {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));

            for (int i=0; i<countries.length; i++)
                writer.append(countries[i].getName() + " , " + countries[i].getId() + "\n");

            writer.close();
        } catch(Exception e){

        }
    }



}
