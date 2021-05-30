package com.codewithgangez;


import com.mongodb.client.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.bson.Document;
import java.util.ArrayList;
import java.util.Scanner;

public class ColToBathu extends Application { //creating the class for the first route

    static final int SEATING_CAPACITY = 42; // declaring the seat capacity
    static ArrayList<String> names = new ArrayList<>();//creating the arraylist for customer names
    static ArrayList<Integer> seatNo = new ArrayList<>();//creating the arraylist for seatnumber
    static int lastclicked;//Declaring the variable for the selected button by the customer



    public void start(Stage primaryStage){
        selectTheRoute();//calling out the select the route menu
    }

    public static void menu() {//creating a method called menu
        Scanner input = new Scanner(System.in);
        System.out.println("\n");
        System.out.println("Colombo to Bathulla");
        System.out.println("\nPlease select one of choices given below according to your needs");
        System.out.println("_________________________________________________________________");

        System.out.println("\n01. Enter \"A\" to add customer to a seat");
        System.out.println("\n02. Enter \"V\" to view all seats");
        System.out.println("\n03. Enter \"E\" to display empty seats");
        System.out.println("\n04. Enter \"D\" to delete customer from seat");
        System.out.println("\n05. Enter \"F\" to find seat for a given customer name");
        System.out.println("\n06. Enter \"S\" to store program data into a file");
        System.out.println("\n07. Enter \"L\" to load program data from file ");
        System.out.println("\n08. Enter \"O\" to view seats ordered alphabetically by name");
        System.out.println("\n09. Enter \"Q\" to exit the program");
        System.out.println("\n09. Enter \"R\" to select the Route");
        System.out.println("\n");

        System.out.print("\n----Select a option----: ");
        String Option = input.next().toLowerCase().trim();//changing the input option to lowercase
        switch (Option) {//using the switching mechanism to choose the option
            case ("a"):
                addCustomer();
                break;
            case ("v"):
                viewSeats();
                break;
            case ("e"):
                emptySeats();
                break;
            case ("d"):
                deleteSeatsByCustomerName();
                break;
            case ("f"):
                findCustomerByName();
                break;
            case ("s"):
                storeDataToFile();
                break;
            case ("l"):
                loadDataFromFile();
                break;
            case ("o"):
                viewSeatsInOrder();
                break;
            case ("q"):
                System.exit(0);
            case ("r"):
                selectTheRoute();
                break;
            default://setting up an exception to carried out when an invalid option is entered
                System.out.println("\nEntered option is invalid ");
                System.out.println("\n-------------Enter the valid option by selecting from  the above menu-------------");
                System.out.println("\n");
                menu();
        }
    }



    public static void selectTheRoute() {//selecting the route
        System.out.print("\n" +
                "\nEnter the way you want to book seat " +
                "\nColombo to Badulla      \"1\" " +
                "\nBadulla to Colombo         \"2\" " +
                "\n : ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next().trim().toLowerCase();
        switch (input) {
            case "1":
                menu();
                break;
            case "2":
                BathuToCol.menu();
                break;
            default:
                System.out.println("\nInvalid Input");
                selectTheRoute();

        }
    }


    private static void addCustomer() {//creating a method to add customer
        Stage stage = new Stage();//creating the stage
        Button[] btn = new Button[SEATING_CAPACITY]; //creating the 42 seats
        Button done = new Button("DONE");//creating the done button
        done.setLayoutX(620);// posistionin the button in the gridpane using cartesean
        done.setLayoutY(420);
        Button close = new Button("BACK TO THE MENU");//creating the back to menu button
        close.setLayoutX(590);
        close.setLayoutY(470);
        TextField CusNameTxtField = new TextField(); //customer name txt field
        Label CusNameLabel = new Label("Enter Customer First Name :");//taking customer  first name as input in GUI
        CusNameTxtField.setLayoutX(720);
        CusNameTxtField.setLayoutY(300);
        CusNameLabel.setLayoutX(480);
        CusNameLabel.setStyle("-fx-font-size:20px;");
        CusNameLabel.setLayoutY(300);
        Label CusSurNameLabel = new Label("Enter Customer Sur Name :");//taking the customer sur name as input in GUI
        CusSurNameLabel.setStyle("-fx-font-size:20px;");
        CusSurNameLabel.setLayoutX(480);
        CusSurNameLabel.setLayoutY(370);
        TextField CusSurNameTxtField = new TextField(); //customer name txt field
        CusSurNameTxtField.setLayoutX(720);
        CusSurNameTxtField.setLayoutY(370);
        Label heading = new Label("Colombo to Badulla Train Booking System-Grab your tickets now  ! ! !");
        heading.setLayoutX(80);
        heading.setLayoutY(40);
        heading.setStyle("-fx-font-size:30px;");
        Button booked = new Button();
        booked.setStyle("-fx-background-color:red");
        booked.setLayoutX(500);
        booked.setLayoutY(130);
        Label book = new Label("----Booked Seats----");
        book.setLayoutY(130);
        book.setLayoutX(550);
        book.setStyle("-fx-font-size:30px;-fx-text-fill:red");//setting the color for the button
        booked.setPadding(new Insets(12));//setting the size of the button
        Button unbooked = new Button();
        unbooked.setStyle("-fx-background-color:#1aeb8d");
        unbooked.setLayoutX(500);
        unbooked.setLayoutY(210);
        Label unbook = new Label("----Not-Booked Seats----");
        unbook.setLayoutY(210);
        unbook.setLayoutX(550);
        unbook.setStyle("-fx-font-size:30px;-fx-text-fill:#1aeb8d;");
        unbooked.setPadding(new Insets(12));//posistioning the button

        GridPane gridPane = new GridPane();//creating the gridpane in the GUI
        int colIndex = 0, rowIndex = 0, rowLoop = 0;
        String seatNumber;//declaring a variable to get  numbers for seats
        for (int i = 0; i < (SEATING_CAPACITY); i++) { //creating a for loop to print numbers for seats
            if (i <= 9) {  // if seat no<10 '0' will add
                seatNumber = "0" + (i);
            } else {
                seatNumber = "" + (i); //if  seat no>10 nothing added
            }
            btn[i] = new Button(seatNumber);//giving nubers for all 42 seats
            btn[i].setPadding(new Insets(12));//adjusting the all 42 buttons
            gridPane.add(btn[i], colIndex, rowIndex);//getting arranged the buttons according to the row,column indexes
            colIndex++;//column will be added again and again
            rowLoop++;//row will be iteraning
            if (rowLoop == 4) {//setting the number of columns for 4
                colIndex = 0;
                rowLoop = 0;
                rowIndex++;
            }
        }

        gridPane.setLayoutY(100);//setting the layout for gridpane
        gridPane.setLayoutX(150);
        gridPane.setHgap(40);
        gridPane.setVgap(15);
        AnchorPane anchorPane = new AnchorPane();//creating the anchorpane
        anchorPane.setStyle("-fx-background-color:#66FFFF"); //i am fixing the gridpane inside the anchorpane
        anchorPane.getChildren().addAll(gridPane, CusNameLabel, close, CusNameTxtField, heading, done, booked, unbooked, book, unbook,CusSurNameTxtField,CusSurNameLabel);//calling out all the labels
        Scene scene = new Scene(anchorPane, 1000, 900);//and buttons that we wnat in the GUI
        stage.setTitle(" Colombo--Train  seat booking system--Badulla ");//giving the title for the stage
        stage.setScene(scene);//creating a scene for GUI
        stage.show();//calling out the stage

        stage.setOnCloseRequest(e -> {
            menu();
            stage.close();
        });


         lastclicked = 0;//declaring the last clicked variable

        for (int i = 0; i < SEATING_CAPACITY; i++) {//here i am iterating 42 times to check weather the seat is clicked or not
            int last = i;//delaring the variable
            btn[i].setOnAction(e -> {
                for (int y = 0; y < SEATING_CAPACITY; y++) {//here iam using the nested for loop to check weather the buttonis clicked or not
                    btn[y].setStyle("-fx-background-color:LightGreen; -fx-border-width:1 1 1 1; -fx-border-color:black;");
                }
                lastclicked = last;//delaring the last clicked button
                btn[lastclicked].setStyle("-fx-background-color:Red; -fx-border-width:1 1 1 1; -fx-border-color:black;");
            });
        }

        done.setOnAction(e -> {
            String customerFirstName = CusNameTxtField.getText().toLowerCase().trim().toLowerCase().trim();//here i am setting the name to lower case and triming it avoid space between names
            String customerSurName = CusSurNameTxtField.getText().toLowerCase().trim().toLowerCase().trim();
            if (!(customerFirstName.equals("")) && !(btn.equals(lastclicked)) && !(customerSurName.equals(""))) {//validating while taking the name from the user inputs
                names.add(customerFirstName+" "+customerSurName);//addid the two name inputs together
                seatNo.add(lastclicked);
                stage.close();
                menu();
            }
        });

        close.setOnAction(e -> {
            menu();
            stage.close();
        });
        for (int i = 0; i < SEATING_CAPACITY; i++) {//setting the color for each and every seats in compartment
            btn[i].setStyle("-fx-background-color:LightGreen; -fx-border-width:1 1 1 1; -fx-border-color:black;");
        }
        for (int n : seatNo) {//setting the color for the seat in the seatNo arraylist which means the seat is booked already
            btn[n].setStyle("-fx-background-color:#FF0000; -fx-border-width:1 1 1 1; -fx-border-color:black;");
            btn[n].setDisable(true);
            btn[n].setStyle("-fx-background-color:#FF0000;");
        }

    }

    private static void viewSeats() {//here i am using the viewSeats methods to view the seats only
        Stage stage = new Stage();//here you can't select the seat only you can view the booked and unbooked seats
        Button[] btn = new Button[SEATING_CAPACITY];
        Button close = new Button("BACK TO THE MENU");
        close.setLayoutX(590);
        close.setLayoutY(400);
        Label heading = new Label("Colombo to Badulla Train Booking System - Viewing the Seats Only  ! ! ! ");
        heading.setLayoutX(80);
        heading.setLayoutY(40);
        heading.setStyle("-fx-font-size:30px;");
        Button booked = new Button();
        booked.setStyle("-fx-background-color:red");
        booked.setLayoutX(500);
        booked.setLayoutY(130);
        Label book = new Label("----Booked Seats----");
        book.setLayoutY(130);
        book.setLayoutX(550);
        book.setStyle("-fx-font-size:30px;-fx-text-fill:red");
        booked.setPadding(new Insets(12));
        Button unbooked = new Button();
        unbooked.setStyle("-fx-background-color:#1aeb8d");
        unbooked.setLayoutX(500);
        unbooked.setLayoutY(210);
        Label unbook = new Label("----Not-Booked Seats----");
        unbook.setLayoutY(210);
        unbook.setLayoutX(550);
        unbook.setStyle("-fx-font-size:30px;-fx-text-fill:#1aeb8d;");
        unbooked.setPadding(new Insets(12));

        GridPane gridPane = new GridPane();
        int colIndex = 0, rowIndex = 0, rowLoop = 0;
        String seatNumber;
        for (int i = 0; i < (SEATING_CAPACITY); i++) {
            if (i <= 9) {
                seatNumber = "0" + (i);
            } else {
                seatNumber = "" + (i);
            }
            btn[i] = new Button(seatNumber);
            btn[i].setPadding(new Insets(12));
            gridPane.add(btn[i], colIndex, rowIndex);
            colIndex++;
            rowLoop++;
            if (rowLoop == 4) {
                colIndex = 0;
                rowLoop = 0;
                rowIndex++;
            }

        }
        gridPane.setLayoutY(100);
        gridPane.setLayoutX(150);
        gridPane.setHgap(40);
        gridPane.setVgap(15);
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setStyle("-fx-background-color:#66FFFF");
        anchorPane.getChildren().addAll(gridPane, close, heading, booked, unbooked, book, unbook);
        Scene scene = new Scene(anchorPane, 1000, 900);
        stage.setTitle(" Colombo--Train  seat booking system--Badulla ");
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(e -> {
            menu();
            stage.close();
        });

        close.setOnAction(e -> {
            menu();
            stage.close();
        });
        for (int i = 0; i < SEATING_CAPACITY; i++) {//setting color for all the buttons
            btn[i].setStyle("-fx-background-color:LightGreen; -fx-border-width:1 1 1 1; -fx-border-color:black;");
        }

        for (int n : seatNo) { //if the seat is in the seatNo arraylist gonna print button in the red color
            btn[n].setStyle("-fx-background-color:Red; -fx-border-width:1 1 1 1; -fx-border-color:black;");
            btn[n].setDisable(true);
        }
    }

    private static void emptySeats() {//here i am calling the emptySeats methods to view the empty seats only
        Stage stage = new Stage();//the seats which are unbooked only
        Button[] btn = new Button[SEATING_CAPACITY];
        Button close = new Button("BACK TO THE MENU");
        close.setLayoutX(590);
        close.setLayoutY(400);
        Label heading = new Label("Colombo to Badulla Train Booking System-Viewing the Empty Seats Only");
        heading.setLayoutX(80);
        heading.setLayoutY(40);
        heading.setStyle("-fx-font-size:30px;");
        Button booked = new Button();
        booked.setStyle("-fx-background-color:red");
        booked.setLayoutX(500);
        booked.setLayoutY(130);
        Label book = new Label("----Booked Seats----");
        book.setLayoutY(130);
        book.setLayoutX(550);
        book.setStyle("-fx-font-size:30px;-fx-text-fill:red");
        booked.setPadding(new Insets(12));
        Button unbooked = new Button();
        unbooked.setStyle("-fx-background-color:#1aeb8d");
        unbooked.setLayoutX(500);
        unbooked.setLayoutY(210);
        Label unbook = new Label("----Not-Booked Seats----");
        unbook.setLayoutY(210);
        unbook.setLayoutX(550);
        unbook.setStyle("-fx-font-size:30px;-fx-text-fill:#1aeb8d;");
        unbooked.setPadding(new Insets(12));

        GridPane gridPane = new GridPane();
        int colIndex = 0, rowIndex = 0, rowLoop = 0;
        String seatNumber;
        for (int i = 0; i < (SEATING_CAPACITY); i++) {
            if (i <= 9) {
                seatNumber = "0" + (i);
            } else {
                seatNumber = "" + (i);
            }
            btn[i] = new Button(seatNumber);
            btn[i].setPadding(new Insets(12));
            gridPane.add(btn[i], colIndex, rowIndex);
            colIndex++;
            rowLoop++;
            if (rowLoop == 4) {
                colIndex = 0;
                rowLoop = 0;
                rowIndex++;
            }
        }
        gridPane.setLayoutY(100);
        gridPane.setLayoutX(150);
        gridPane.setHgap(40);
        gridPane.setVgap(15);
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setStyle("-fx-background-color:#66FFFF");
        anchorPane.getChildren().addAll(gridPane, close, heading, booked, unbooked, book, unbook);
        Scene scene = new Scene(anchorPane, 1000, 900);
        stage.setTitle(" Colombo--Train  seat booking system--Badulla ");
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(e -> {
            menu();
            stage.close();
        });

        close.setOnAction(e -> {
            menu();
            stage.close();
        });
        for (int i = 0; i < SEATING_CAPACITY; i++) {
            btn[i].setStyle("-fx-background-color:LightGreen; -fx-border-width:1 1 1 1; -fx-border-color:black;");
        }

        for (int n : seatNo) {
            btn[n].setStyle("-fx-background-color:Red; -fx-border-width:1 1 1 1; -fx-border-color:black;");
            btn[n].setVisible(false);//here I am setting visible to false so booked seats won't appear
        }
    }

    private static void deleteSeatsByCustomerName() {//here deleting a customer from his/her seatNo by entering thier name
        Scanner input = new Scanner(System.in);
        System.out.print("\nPlease enter customer name inorder to delete the seat: ");
        String name = input.nextLine().trim();
        String notBooked = " didn't book a seat therefore there is no seat number found to be deleted.\n";
        boolean found = false;//creating the boolean expression to check weather the customer
        for (int x = 0; x < names.size(); x++) {//here i running a for loop to check wheather the input name found in the names arraylist

            int seat = seatNo.get(x);//if seat found in seatNo array list then store in a variable
            String nameC = names.get(x);//if the name found in the names arraylist
            if (name.equals(nameC)) {//if the user entered name found in the arraylist it'll get removed
                seatNo.remove(seatNo.get(x));//removing the customer from the seat
                names.remove(name);
                System.out.println("\n");
                System.out.println(name + " is successfully deleted from the seat number "+seat+" in the train booking system - Colombo to Badulla");
                found = true;
            }
        }

        if (!found) {//if the name not found in the names array list above action
            System.out.println("\n");
            System.out.println(name +" "+ notBooked);
        }
        System.out.println("\n");
        menu();
    }

    private static void findCustomerByName() {//finding the customer's seat number by searching his name
        Scanner input = new Scanner(System.in);
        System.out.println("\n");
        System.out.print("Please enter customer name inorder to find the seat number: ");
        String name = input.nextLine().trim();
        String notBooked = " didn't book a seat therefore there is no seat number found.\n";
        boolean found = false;
        for (int x = 0; x < names.size(); x++) {//starting a for loop to check whether input name found in the name arraylist or not
            String nameC = names.get(x);//getting the name from the arraylist and storing it in a string variable
            int seat = seatNo.get(x);//getting the seatNo from the arraylist and storing it on the variable seat
            if (name.equals(nameC)) {//if the input name found  in names arraylist below operation will happen
                System.out.println("\n");
                System.out.println(nameC + " " +"have booked the seat number "+ seat+" in the train booking system - Colombo to Badulla"+" on ");
                found = true;
            }
        }

        if (!found) {//if the name not found below action will happen
            System.out.println("\n");
            System.out.println(name + " "+notBooked);
        }
        System.out.println("\n");
        menu();
    }

    private static void viewSeatsInOrder() {
        //creating the temperory array to store the data foun in the array list to get them sorted
        System.out.println("\n");
        System.out.println("--------View the seats in Alphabetical Order--------");
        System.out.println("\n");
        String[] tempNames = new String[names.size()];//creating a temerory array to store name
        int[] tempSeatN = new int[seatNo.size()];//creating a temerory array to store name
        int x = 0;
        for (String name : names) {//iterating the name in the array list and storing it to an temperory array
            tempNames[x++] = name;
        }
        int y = 0;
        for (int seat : seatNo) {//iterating the seat number in the array list and storing it to an temperory array
            tempSeatN[y++] = seat;
        }
        String tempN;
        int tempS;
        for (int j = 0; j < tempNames.length; j++) {
            for (int i = j + 1; i < tempNames.length; i++) {
                // comparing adjacent strings
                if (tempNames[i].compareTo(tempNames[j]) < 0) {
                    tempN = tempNames[j];//when we minus [j] is always bigger and [i] is less so gonna return negative
                    tempS = tempSeatN[j];
                    tempNames[j] = tempNames[i];
                    tempSeatN[j] = tempSeatN[i];
                    tempNames[i] = tempN;
                    tempSeatN[i] = tempS;
                }
            }
        }

        System.out.println("\n--------Customers who booked seats from Colombo to Badulla--------");

        int a = 0;
        for (String name : tempNames) {//iterating the passenger names who are stored in the arraylist names and transferred to temp array
            System.out.println("\n");
            System.out.println(name +  "  booked the seat  " + tempSeatN[a++]+" from Colombo to Badulla");
            System.out.println("\n");
        }
        menu();
    }

    private static void storeDataToFile() {//storing the data to mongodb
        if(names.size()!=0 && seatNo.size()!=0) {
            MongoClient mongoClient;
            MongoDatabase mongoDatabase;
            MongoCollection<Document> mongoCollection;
            mongoClient = MongoClients.create("mongodb://localhost:27017");//connect to mongodb database
            mongoDatabase = mongoClient.getDatabase("ColombotoBadulla");
            mongoCollection = mongoDatabase.getCollection("Customer");

            for (int i = 0; i < names.size(); i++) {//getting the length of the names arraylist
                Document doc = new Document("title", "record")
                        .append("seatNumber", seatNo.get(i))//appendind the certain data to the document
                        .append("name", names.get(i));
                mongoCollection.insertOne(doc);
            }
            System.out.println("\n");
            System.out.println("----------Data successfully stored to the database----------");
            System.out.println("\n");
            menu();
        }else {
            System.out.println("\n");
            System.out.println("----------Add some people to the seat to store them to database----------");
            System.out.println("\n");
            menu();
        }
    }

    private static void loadDataFromFile() {//loading the data to mongodb
        MongoClient mongoClient;
        MongoDatabase mongoDatabase;
        MongoCollection<Document> mongoCollection;
        mongoClient = MongoClients.create("mongodb://localhost:27017");//connect to mongodb database
        mongoDatabase = mongoClient.getDatabase("ColombotoBadulla");//giving the name for the database
        mongoCollection = mongoDatabase.getCollection("Customer");//giving a name for database collection

        names.clear();//clears database
        seatNo.clear();

        FindIterable<Document> data = mongoCollection.find();//find the iterable file with all data
        for (Document temp : data) {
            names.add(temp.getString("name"));
            seatNo.add(temp.getInteger("seatNumber"));
        }
        System.out.println("\n");
        System.out.println("----------Data successfully loaded from the database----------");
        System.out.println("\n");
        menu();

    }
}

