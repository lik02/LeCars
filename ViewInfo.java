import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
public class ViewInfo {
    static final String customerFile = "cust.csv";
    static final String employeeFile = "employee.csv";
    static final String salesFile = "sales.csv";
    static final String vehicleFile = "vehicle-2.csv";

    private static void printHeader(String file) {
        try (BufferedReader Reader = new BufferedReader(new FileReader(file))) {
            String headerLine;
            if ((headerLine = Reader.readLine()) != null) {
                // Print the header line
                String[] headerData = headerLine.split(",");
                for (int i = 0; i < headerData.length; i++) {
                    // Set the width of the second column to be larger
                    int columnWidth = (i == 1) ? 30 : 15; // Adjust the widths as needed
                    System.out.printf("%-" + columnWidth + "s", headerData[i]);
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void displayOwnCustomerData(String employeeId) {
    boolean printedHeader = false;
    Set<String> processedCustomerIds = new HashSet<>(); // To keep track of processed customer IDs

    try (BufferedReader br = new BufferedReader(new FileReader(salesFile))) {
        String line;

        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            String salesEmployeeId = data[4];

            // Check if the customer data belongs to the specified sales employee
            if (salesEmployeeId.equals(employeeId)) {
                String custId = data[3]; // Get the customer ID from the sales record

                // Check if the customer ID has already been processed
                if (!processedCustomerIds.contains(custId)) {
                    if (!printedHeader) {
                        printHeader(customerFile);
                        printedHeader = true; // Set the flag to indicate header has been printed
                    }

                    // Now, retrieve and display the customer data based on custId
                    displayCustomerDataByCustomerId(custId);

                    // Add the customer ID to the set of processed customer IDs
                    processedCustomerIds.add(custId);
                }
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
     }        
    }

    private static void displayCustomerDataByCustomerId(String customerId) {
        try (BufferedReader customerReader = new BufferedReader(new FileReader(customerFile))) {
            String line;

            while ((line = customerReader.readLine()) != null) {
                String[] data = line.split(",");
                String custId = data[0];// Assuming customer ID is in the 1st column

                // Check if the custId matches the input customerId
                if (custId.equals(customerId)) {
                    // Display the relevant customer data
                    for (int i = 0; i < data.length; i++) {
                        // Set the width of the second column to be larger
                        int columnWidth = (i == 1) ? 30 : 15; // Adjust the widths as needed
                        System.out.printf("%-" + columnWidth + "s", data[i]);
                    }
                    System.out.println();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    public static void displayOwnSalesRecords(String employeeId) {
        boolean printedHeader = false;
        try (BufferedReader br = new BufferedReader(new FileReader(salesFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String salesEmployeeId = data[4];
                

                // Check if the sales record belongs to the sales employee
                if (salesEmployeeId.equals(employeeId)) {
                    if (!printedHeader) {
                    printHeader(salesFile);
                    printedHeader = true; // Set the flag to indicate header has been printed
                }
                    
                    // Display the sales record in tabular form
                    for (int i = 0; i < data.length; i++) {
                        // Set the width of the second column to be larger
                        int columnWidth = (i == 1) ? 30 : 15; // Adjust the widths as needed
                        System.out.printf("%-" + columnWidth + "s", data[i]);
                    }
                    System.out.println();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void displayAllVehicleData() {
        displayTable(vehicleFile);
    }

    public static void displayAllCustomerData() {
        displayTable(customerFile);
    }

    public static void displayAllSalesRecords() {
        displayTable(salesFile);
    }

    public static void displayAllEmployeeData() {
        displayTable(employeeFile);
    }

    private static void displayTable(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split and display data in tabular form
                String[] data = line.split(",");
                for (int i = 0; i < data.length; i++) {
                    // Set the width of the second column to be larger
                    int columnWidth = (i == 1) ? 30 : 15; // Adjust the widths as needed
                    System.out.printf("%-" + columnWidth + "s", data[i]);
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}
