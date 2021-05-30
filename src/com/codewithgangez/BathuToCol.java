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

public class BathuToCol extends Application { //creating the class for the first route

    static final int SEATING_CAPACITY = 42;// declaring the seat capacity
    static ArrayList<String> names = new ArrayList<>();//creating the arraylist for customer names
    static ArrayList<Integer> seatNo = new ArrayList<>();//creating the arraylist for seatnumber
    static int lastclicked;

    public void start(Stage primaryStage){//creating the main method
        System.out.println("\n");
        menu();
    }

    public static void menu() {//creating a method called menu
        Scanner input = new Scanner(System.in);
        System.out.println("Badulla to Colombo");
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
        String Option = input.next().trim().toLowerCase();//changing the input option to lowercase
        switch (Option) {
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
                ColToBathu.selectTheRoute();
                break;
            default://setting up an except to carried out when an invalid option is entered
                System.out.println("\nEntered option is invalid ");
                System.out.println("\n-------------Enter the valid option by selecting from  the above menu-------------");
                System.out.println("\n");
                menu();
        }
    }


    private static void addCustomer() { //creating a method to add customer
        final Stage[] stage = {new Stage()};//creating the stage
        Button[] btn = new Button[SEATING_CAPACITY]; //creating the 42 seats
        Button done = new Button("DONE"); //creating the done button
        done.setLayoutX(620);
        done.setLayoutY(420);
        Button close = new Button("BACK TO THE MENU");//creating the back to menu button
        close.setLayoutX(590);
        close.setLayoutY(470);
        TextField CustomerNameTxtField = new TextField(); //customer name txt field to enter name
        Label CusNameLabel = new Label("Enter Customer First Name : ");
        CustomerNameTxtField.setLayoutX(720);
        CustomerNameTxtField.setLayoutY(300);
        CusNameLabel.setStyle("-fx-font-size:20px;");
        CusNameLabel.setLayoutX(480);
        CusNameLabel.setLayoutY(300);
        Label CusSurNameLabel = new Label("Enter Customer Sur Name :");
        CusSurNameLabel.setStyle("-fx-font-size:20px;");
        CusSurNameLabel.setLayoutX(480);
        CusSurNameLabel.setLayoutY(370);
        TextField CusSurNameTxtField = new TextField(); //customer name txt field
        CusSurNameTxtField.setLayoutX(720);
        CusSurNameTxtField.setLayoutY(370);
        Label heading = new Label("Badulla to Colombo Train Booking System-Grab your tickets now  ! ! !");
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
        booked.setPadding(new Insets(12));//setting the size of the button
        Button unbooked = new Button();
        unbooked.setStyle("-fx-background-color:#1aeb8d");
        unbooked.setLayoutX(500);
        unbooked.setLayoutY(210);
        Label unbook = new Label("----Not-Booked Seats----");
        unbook.setLayoutY(210);
        unbook.setLayoutX(550);
        unbook.setStyle("-fx-font-size:30px;-fx-text-fill:#1aeb8d;");
        unbooked.setPadding(new Insets(12));

        GridPane gridPane = new GridPane();//creating a gridpane in the stage
        int columnIndex = 0,rowIndex = 0,rowLoop = 0;//declaring
        String Numberofseat;//declaring a string variable
        for(int r = 0; r<(SEATING_CAPACITY); r++){//creating a for loop to print numbers for seats
            if (r<=9){  // if seat no<10 '0' will add
                Numberofseat = "0"+(r);
            }
            else{
                Numberofseat  = ""+(r);//if  seat no>10 nothing added
            }
            btn[r] = new Button(Numberofseat);//numbering all 42 seats
            btn[r].setPadding(new Insets(12));//adjusting the button in the gridpane
            gridPane.add(btn[r],columnIndex,rowIndex);//arranging the buttons accoring to rows and column
            columnIndex++;//column will be added again and again
            rowLoop++;//row will be iteraning
            if(rowLoop==4){//setting the number of columns for 4
                columnIndex=0;
                rowLoop=0;
                rowIndex++;
            }
        }

        gridPane.setLayoutY(100);//setting the layout for gridpane
        gridPane.setLayoutX(150);
        gridPane.setHgap(40);
        gridPane.setVgap(15);
        AnchorPane anchorPane = new AnchorPane();//creating the anchorpane
        anchorPane.setStyle("-fx-background-color:#fafaaa");//i am fixing the gridpane inside the anchorpane
        anchorPane.getChildren().addAll(gridPane,CusNameLabel,close,CustomerNameTxtField,heading,done,booked,unbooked,book,unbook,CusSurNameTxtField,CusSurNameLabel);//calling out all the labels
        Scene scene = new Scene(anchorPane,1000,900); //and buttons that we wnat in the GUI
        stage[0].setTitle(" Badulla--Train  seat booking system--Colombo ");//giving the title for the stage
        stage[0].setScene(scene);//creating a scene for GUI
        stage[0].show();//calling out the stage

        stage[0].setOnCloseRequest(e ->{//when we close the GUI using red close button
            menu();                  //the menu will be called again
            stage[0].close();//the stage will close after that
        });

         lastclicked = 0;

        for(int i = 0;i < SEATING_CAPACITY;i++){//creating a for loop to generate 42 buttons
            int last = i; //declaring the variable last in the seats
            btn[i].setOnAction(e ->{//putting the actions for the seats
                for(int y = 0;y < SEATING_CAPACITY;y++){//creating a nested for loops for the seats to set colors for them
                    btn[y].setStyle("-fx-background-color:LightGreen; -fx-border-width:1 1 1 1; -fx-border-color:black;");
                }//so all seats will green color at first
                lastclicked = last;//declarin the lastclicked button to last to get the finally clicked button only in red color
                btn[lastclicked].setStyle("-fx-background-color:Red; -fx-border-width:1 1 1 1; -fx-border-color:black;");
            });
        }

        done.setOnAction(e-> {//setting click action for done button
            String customerFirstName = CustomerNameTxtField.getText().toLowerCase().trim().toLowerCase().trim();
            String customerSurName = CusSurNameTxtField.getText().toLowerCase().trim().toLowerCase().trim();


            if (!(customerFirstName.equals("")) && !(btn.equals(lastclicked)) && !(customerSurName.equals(""))) {
                names.add(customerFirstName+" "+customerSurName);
                seatNo.add(lastclicked);//adding the last clicked button to the seatNo arraylist
                stage[0].close();//after completing these two tasks then  when we press done button stage will close
                menu();
            }
        });

        close.setOnAction(e ->{//if we press the back to menu button the stage will close
            menu();//calling the menu method again
            stage[0].close();
        });
        for(int i = 0;i < SEATING_CAPACITY;i++){//using a forloop to give color to buttons after selecting a seat
            btn[i].setStyle("-fx-background-color:LightGreen; -fx-border-width:1 1 1 1; -fx-border-color:black;");
        }
        for(int n :seatNo){//if the seat number found in the seatNo arraylist it'll be red and disabled after booking
            btn[n].setStyle("-fx-background-color:#FF0000; -fx-border-width:1 1 1 1; -fx-border-color:black;");
            btn[n].setDisable(true);
        }

    }

    private static void viewSeats(){//here i am using the viewSeats methods to view the seats only
        Stage stage = new Stage(); //here you can't select the seat only you can view the booked and unbooked seats
        Button[] btn = new Button[SEATING_CAPACITY];
        Button close = new Button("BACK TO THE MENU");
        close.setLayoutX(590);
        close.setLayoutY(400);
        Label heading = new Label("Badulla to Colombo Train Booking System - Viewing the Seats Only ! ! !");
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
        int colIndex = 0,rowIndex = 0,rowLoop = 0;
        String seatNumber;
        for(int i = 0; i<(SEATING_CAPACITY); i++){
            if (i<=9){
                seatNumber = "0"+(i);
            }
            else{
                seatNumber  = ""+(i);
            }
            btn[i] = new Button(seatNumber);
            btn[i].setPadding(new Insets(12));
            gridPane.add(btn[i],colIndex,rowIndex);
            colIndex++;
            rowLoop++;
            if(rowLoop==4){
                colIndex=0;
                rowLoop=0;
                rowIndex++;
            }
        }

        gridPane.setLayoutY(100);
        gridPane.setLayoutX(150);
        gridPane.setHgap(40);
        gridPane.setVgap(15);
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setStyle("-fx-background-color:#fafaaa");
        anchorPane.getChildren().addAll(gridPane,close,heading,booked,unbooked,book,unbook);
        Scene scene = new Scene(anchorPane,1000,900);
        stage.setTitle(" Badulla--Train  seat booking system--Colombo ");
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(e ->{
            menu();
            stage.close();
        });

        close.setOnAction(e ->{
            menu();
            stage.close();
        });
        for(int i = 0;i < SEATING_CAPACITY;i++){
            btn[i].setStyle("-fx-background-color:LightGreen; -fx-border-width:1 1 1 1; -fx-border-color:black;");
        }

        for(int n :seatNo){
            btn[n].setStyle("-fx-background-color:Red; -fx-border-width:1 1 1 1; -fx-border-color:black;");
            btn[n].setDisable(true);
        }

    }

    private static void emptySeats(){//here i am calling the emptySeats methods to view the empty seats only
        Stage stage = new Stage();//the seats which are unbooked only
        Button[] btn = new Button[SEATING_CAPACITY];
        Button close = new Button("BACK TO THE MENU");
        close.setLayoutX(590);
        close.setLayoutY(400);
        Label heading = new Label("Badulla to Colomco Train Booking System-Viewing the Empty Seats Only");
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
        int colIndex = 0,rowIndex = 0,rowLoop = 0;
        String seatNumber;
        int[] x = new int[1];
        for(int i = 0; i<(SEATING_CAPACITY); i++){
            if (i<=9){
                seatNumber = "0"+(i);
            }
            else{
                seatNumber  = ""+(i);
            }
            btn[i] = new Button(seatNumber);
            btn[i].setPadding(new Insets(12));
            gridPane.add(btn[i],colIndex,rowIndex);
            colIndex++;
            rowLoop++;
            if(rowLoop==4){
                colIndex=0;
                rowLoop=0;
                rowIndex++;
            }
        }
        gridPane.setLayoutY(100);
        gridPane.setLayoutX(150);
        gridPane.setHgap(40);
        gridPane.setVgap(15);
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setStyle("-fx-background-color:#fafaaa");
        anchorPane.getChildren().addAll(gridPane,close,heading,booked,unbooked,book,unbook);
        Scene scene = new Scene(anchorPane,1000,900);
        stage.setTitle(" Badulla--Train  seat booking system--Colombo ");
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(e ->{
            menu();
            stage.close();
        });

        close.setOnAction(e ->{
            menu();
            stage.close();
        });
        for(int i = 0;i < SEATING_CAPACITY;i++){
            btn[i].setStyle("-fx-background-color:LightGreen; -fx-border-width:1 1 1 1; -fx-border-color:black;");
        }

        for(int n :seatNo){
            btn[n].setStyle("-fx-background-color:Red; -fx-border-width:1 1 1 1; -fx-border-color:black;");
            btn[n].setVisible(false);//here I am setting visible to false so booked seats won't appear
        }

    }

    private static void deleteSeatsByCustomerName(){//here deleting a customer from his/her seatNo by entering thier name
        Scanner input = new Scanner(System.in);
        System.out.print("\nPlease enter customer name inorder to delete the seat: ");
        String name = input.nextLine().trim();
        System.out.println("\n");
        String notBooked = (" didn't book a seat therefore there is no seat number found to be deleted.\n");
        boolean found = false;
        for(int x = 0;x < names.size();x++){


            int seat = seatNo.get(x);
            String nameC = names.get(x);//if name found in names array list
            if(name.equals(nameC)) {
                seatNo.remove(seatNo.get(x));//removing the customer from the seat
                names.remove(name);
                System.out.println("\n");
                System.out.println(name + " is successfully deleted from the seat number "+seat+" in the train booking system - Colombo to Badulla");
                found = true;
            }
        }

        if (!found){//if the name not found in the names array list above action
            System.out.println(name+" "+notBooked);
            System.out.println("\n");
        }
        System.out.println("\n");
        menu();

    }

    private static void findCustomerByName(){//finding the customer's seat number by searching his name
        Scanner input = new Scanner(System.in);
        System.out.print("\nPlease enter customer name inorder to find the seat number: ");
        String name = input.nextLine().trim();
        String notBooked = (" didn't book a seat therefore there is no seat number found to be deleted.\n") ;
        boolean found = false;
        for(int x = 0;x < names.size();x++){//starting a for loop to check whether input name found in the name arraylist or not
            String nameC = names.get(x);//getting the name from the arraylist and storing it in a string variable
            int seat = seatNo.get(x);//getting the seatNo from the arraylist and storing it on the variable seat
            if(name.equals(nameC)) {//if the input name found  in names arraylist below operation will happen
                System.out.println("\n");
                System.out.println(nameC +"  have booked the seat number  "+ seat+"  in the train booking system - Badulla to Colombo");
                found = true;
            }
        }

        if (!found){//if the name not found below action will happen
            System.out.println("\n");
            System.out.println(name+" "+notBooked);
        }
        System.out.println("\n");
        menu();
    }

    private static void viewSeatsInOrder(){ //here I am viewing theq
        System.out.println("\n");
        System.out.println("\n---------View the seats in Alphabetical Order---------");//creating the temperory array to store the data
        // found in the array list to get them sorted
        String[] tempNames =  new String[names.size()];//creating the temperory array to store name
        int[] tempSeatN = new int[seatNo.size()];//creating the temperory array to store seatNo
        int x = 0;
        for(String name :names){//if the name found in arrarylist it'll get added to temp array
            tempNames[x++] = name;
        }
        int y = 0;
        for(int seat :seatNo){//if the seat no found in the arraylist
            tempSeatN[y++] = seat;
        }
        String tempN ;
        int tempS;
        for (int j = 0; j < tempNames.length; j++) {
            for (int i = j + 1; i < tempNames.length; i++) {
                // comparing adjacent strings
                if (tempNames[i].compareTo(tempNames[j]) < 0) {
                    tempN = tempNames[j];
                    tempS = tempSeatN[j];
                    tempNames[j] = tempNames[i];
                    tempSeatN[j] = tempSeatN[i];
                    tempNames[i] = tempN;
                    tempSeatN[i] = tempS;
                }
            }
        }

        System.out.println("\n---------Customers who booked seats from Colombo to Badulla---------");

        int a = 0;
        for(String name:tempNames){
            System.out.println("\n");
            System.out.println(name + "  booked the seat  " + tempSeatN[a++]+" from Badulla to Colombo");
            System.out.println("\n");
        }
        menu();
    }

   private static void storeDataToFile() {//storing data to  the mongodb database
       if(names.size()!=0 && seatNo.size()!=0) {
           MongoClient mongoClient;
           MongoDatabase mongoDatabase;
           MongoCollection<Document> mongoCollection;
           mongoClient = MongoClients.create("mongodb://localhost:27017");//connecting to the mongodb database
           mongoDatabase = mongoClient.getDatabase("BadullatoColombo");//giving the name for the database
           mongoCollection = mongoDatabase.getCollection("Customer");//giving a name for database collection

           for (int i = 0; i < names.size(); i++) {//iterating the arraylist which contains name
               Document doc = new Document("title", "record")
                       .append("seatNumber", seatNo.get(i))//so here i am attaching the objects by getter method
                       .append("name", names.get(i));
               mongoCollection.insertOne(doc);
               ;
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

   private static void loadDataFromFile() {//Loading the data from the mongo db

           MongoClient mongoClient;
           MongoDatabase mongoDatabase;
           MongoCollection<Document> mongoCollection;
           mongoClient = MongoClients.create("mongodb://localhost:27017");//connect to mongodb database
           mongoDatabase = mongoClient.getDatabase("BadullatoColombo");//giving the name for the database
           mongoCollection = mongoDatabase.getCollection("Customer");//giving a name for database collection

           names.clear();//clearing the data
           seatNo.clear();

           FindIterable<Document> data = mongoCollection.find();
           for (Document temp : data) {//iteraing the passenger details by getting them from the arraylist
               names.add(temp.getString("name"));//here i am getting the data by getter methods
               seatNo.add(temp.getInteger("seatNumber"));
           }
           System.out.println("\n");
           System.out.println("----------Data successfully loaded from the database----------");
           System.out.println("\n");
           menu();
       }

}
