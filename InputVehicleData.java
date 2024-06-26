import java.io.*;
import java.util.*;


public class InputVehicleData {
    static final String customerFile = "cust.csv";
    static final String employeeFile = "employee.csv";
    static final String salesFile = "sales.csv";
    static final String vehicleFile = "vehicle-2.csv";
    public static void InputVehicleData() {
        
        BufferedReader reader = null;
        String line = "";

        String carPlate, carModel, actualPrice, soldPrice;
        String status;
        Scanner s = new Scanner(System.in);
        System.out.print("Enter Car Plate: ");
        carPlate = s.nextLine();
        System.out.print("Enter Car Model: ");
        carModel = s.nextLine();
        System.out.print("Enter Car Price: ");
        actualPrice = s.nextLine();
        System.out.print("Enter Sold Price: ");
        soldPrice = s.nextLine();
        if(soldPrice.isBlank()){
            status = "1";
        } else {
            status = "0";
        }
        System.out.print("Car Status: " + status);
        System.out.println("");
        
        saveVehicleToCSV(vehicleFile, carPlate,  carModel,  actualPrice,  soldPrice,  status);
    }

public static void saveVehicleToCSV(String file, String carPlate, String carModel, String actualPrice, String soldPrice, String status) {
    BufferedWriter writer = null;
    try {
        writer = new BufferedWriter(new FileWriter(file, true)); 

        
        String newVehicleData =  carPlate + "," + carModel + "," + actualPrice + "," + status + "," + soldPrice;

        
        writer.write(newVehicleData);
        writer.newLine(); 
        System.out.println("New Vehicle data saved to CSV file successfully!");
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
            if (writer != null) {
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
}