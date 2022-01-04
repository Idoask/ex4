import java.util.Scanner;

public class RealEstate {
    private User[] users;
    private Property[] properties;
    private Address[] addresses;

 //real estate const - also initializing the address book
    public RealEstate() {
        this.addresses = new Address[10];
        addresses[0] = new Address("tel aviv","dizingoff");
        addresses[1] = new Address("tel aviv","alenbi");
        addresses[2] = new Address("tel aviv","rotshild");
        addresses[3] = new Address("tel aviv","eban gabirol");
        addresses[4] = new Address("ramat gan","bialik");
        addresses[5] = new Address("givataim","katzanelson");
        addresses[6] = new Address("ashdod","hatzionot");
        addresses[7] = new Address("sderot","alonim");
        addresses[8] = new Address("sderot","uzi hitman");
        addresses[9] = new Address("ashkelon","tavlan");
        this.properties = new Property[0];
        this.users = new User[0];
    }

    // main function - running all other functions and functionality
    public void main() {
        RealEstate rs = new RealEstate();
        String ans = "";
        while (!ans.equals("3")){
        ans = mainMenu();
        if (ans.equals("1")){
            rs.createUser();
        }
        else if (ans.equals("2")) {
            User logged = rs.userLogin();
            if (logged == null) {
                continue;
                }
            else{
                String sec = rs.secondaryMenu();
                if (sec.equals("6")){
                    continue;
                }else{
                    if(sec.equals("1")){
                        postNewProperty(logged);
                        continue;
                    }
                    else{
                        if (sec.equals("2")){
                            removeProperty(logged);
                            continue;
                        }
                        else{
                            if (sec.equals("3")){
                                printAllProperties();
                                continue;
                            }
                            else{
                                if (sec.equals("4")){
                                    printAllProperties(logged);
                                    continue;
                                }
                                else{
                                    if (sec.equals("5")){
                                        search();
                                        continue;
                                    }
                                }

                            }
                        }
                    }
                }
            }
           }
        }
    }

    // search functionality
    public Property[] search() {
        Scanner sc = new Scanner(System.in);
        int forRent = -1;
        boolean boolForRent = false;
        int propType = -1;
        int desiredRoom = -1;
        int minimumPrice = -1;
        int maximumPrice = -1;
        System.out.println("for rent press 1, else press 2");
        forRent = sc.nextInt();
        if (forRent == 1 ){
            boolForRent = true;
        }
        else {
            boolForRent = false;
        }
        // show the questions and get the relevant data
        System.out.println("whats the property type?");
        System.out.println("press 1 for appartment in a building");
        System.out.println("press 2 for penthouse in a building");
        System.out.println("press 3 for private house");
        propType = sc.nextInt();
        String type = "";
        if (propType == 1){
            type = "appartment";
        }
        if (propType == 2){
            type = "penthouse";
        }
        if (propType == 3){
            type = "private";
        }
        System.out.println("how many rooms?");
        desiredRoom = sc.nextInt();
        System.out.println("whats the minimum price?");
        minimumPrice = sc.nextInt();
        System.out.println("whats the maximum price?");
        maximumPrice = sc.nextInt();
        Property[] answer = new Property[0];
        for (int i = 0; i < properties.length; i++) {
            if (forRent != -999 && properties[i].isForItForRent()!=boolForRent){
                continue;
            }
            if (propType != -999 && !properties[i].getType().equals(propType)){
                continue;
            }
            if (forRent != -999 && properties[i].getNumberOfRooms()!=desiredRoom){
                continue;
            }
            if (forRent != -999 && properties[i].getPrice()<minimumPrice){
                continue;
            }
            if (forRent != -999 && properties[i].getPrice()>maximumPrice){
                continue;
            }
            answer = addPropertyToList(properties[i],answer);
        }
        return answer;

    }

    // print all properties the user published
    public void printAllProperties(User user){
        for (int i = 0; i < properties.length; i++) {
            if (properties[i].getPublishedUser().getUserName().equals(user.getUserName())) {
                System.out.println(properties[i]);
            }
        }
    }

    //print all properties in the system
    public void printAllProperties(){
        for (int i = 0; i < properties.length; i++) {
            System.out.println(properties[i]);
        }
    }

    // check if the city founded in the list of addresses
    public boolean isCityExist(String cityName){
        for (int i = 0; i < addresses.length; i++) {
            if (addresses[i].getCityName().equals(cityName)){
                return true;
            }
        }
        return false;
    }

    // run the main menu
    public static String  mainMenu(){
        String ans = "";
        Scanner sc = new Scanner(System.in);
        while (!ans.equals("1") && !ans.equals("2") && !ans.equals("3")) {
            System.out.println("what would you like to do? press the corlated key");
            System.out.println("Create account - 1");
            System.out.println("connect to exist account - 2");
            System.out.println("End the program - 3");
            ans = sc.nextLine();
        }
        return ans;
    }


    // show and run the secondary menu
    public static String secondaryMenu(){
        String ans = "";
        Scanner sc = new Scanner(System.in);
        while (!ans.equals("1") && !ans.equals("2") && !ans.equals("3") && !ans.equals("4") && !ans.equals("5") && !ans.equals("6")) {
            System.out.println("what would you like to do? press the corlated key");
            System.out.println("publish new property - 1");
            System.out.println("remove property from neing published - 2");
            System.out.println("show all properties in the system - 3");
            System.out.println("show all properties published by the user - 4");
            System.out.println("search property by parameters - 5");
            System.out.println("back to main menu - 6");
            ans = sc.nextLine();
        }
        return ans;
    }

    // remove property that published by the user
    public void removeProperty(User user){
        Scanner sc = new Scanner(System.in);
        if (ifUserPublishedProperty(user)==false){
            System.out.println("cant delete any property");
        }
        else{
            for (int i = 0; i < properties.length; i++) {
                if (properties[i].getPublishedUser().getUserName().equals(user.getUserName())){
                    System.out.println("to delete:");
                    System.out.println(properties[i]);
                    System.out.println("press" + i);
                }
            }
            int chosenProp = sc.nextInt();
            if (properties[chosenProp].getPublishedUser().getUserName().equals(user.getUserName())== false){
                System.out.println("please choose from the given list");
            }else{
                removeProperty(chosenProp);
            }
        }
    }
    // remove property from list
    public void removeProperty(int i) {
        properties[i] = null;
        Property[] p = new Property[properties.length - 1];
        int k = 0;
        int index = 0;
        while (k < properties.length){
            if (k != i){
                p[index] = properties[k];
                k++;
                index++;
            }
        }
        properties = p;
    }

    // function that checks if the user published any properties
    public boolean ifUserPublishedProperty(User user){
        for (int i = 0; i < properties.length; i++) {
            if (properties[i].getPublishedUser().equals(user)){
                return true;
            }
        }
        return false;
    }

    // the function get information from the user and generate a new property
    public Property generateProperty(Address address,User user){
        Scanner sc=new Scanner(System.in);
        int floorNumber=0;
        int rooms=-1;
        int numberOfProperty=-1;
        boolean forRent=false;
        int price=-1;
        System.out.println("what is the type of property");
        System.out.println("press 1 for appartment in a building");
        System.out.println("press 2 for penthouse in a building");
        System.out.println("press 3 for private house");
        String userChoice=sc.nextLine();
        if (userChoice.equals("1")){
            System.out.println("which floor");
            floorNumber=sc.nextInt();
        }
        String propertType="appartment";
        if (userChoice.equals("2")){
            propertType="penthouse";
        }
        if (userChoice.equals("3")){
            propertType="private house";
        }
        System.out.println("how many rooms");
        rooms=sc.nextInt();
        System.out.println("what the number of the property");
        numberOfProperty= sc.nextInt();
        System.out.println("is the property for rent press 1 else press 2");
        if (sc.nextInt()==1){
            forRent=true;
        }
        else {
            forRent=false;
        }
        System.out.println("whet is the price");
        price=sc.nextInt();
        Property p=new Property(address,rooms,price,propertType,forRent,numberOfProperty,floorNumber,user);
        addPropertyToList(p);
        return p;
    }

    //the function print the streets of a given city
    public String[] printStreets(String city){
        String[] streets=new String[0];
        for (int i = 0; i <addresses.length ; i++) {
            if (addresses[i].getCityName().equals(city)){
                System.out.println(addresses[i].getStreetName());
                streets=insertCity(streets,addresses[i].getStreetName());
            }
        }
        return streets;
    }

    // the function checks if the given city is within array of cities
    public boolean isCityInArray(String city,String[] array){
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(city)){
                return true;
            }
        }
        return false;
    }

    // the function add city to given array
    public String[] insertCity(String[] array,String city){
        String[] newArray=new String[array.length+1];
        for (int i = 0; i < array.length; i++) {
            newArray[i]=array[i];
        }
        newArray[newArray.length-1]=city;
        return newArray;
    }

    // the function shows all relevant cities
    public void showcaseCity(){
        String[] printedCitys=new String[0];
        for (int i = 0; i < addresses.length; i++) {
            if (isCityInArray(addresses[i].getCityName(),printedCitys)==false){
                System.out.println(addresses[i].getCityName());
                printedCitys=insertCity(printedCitys,addresses[i].getCityName());
            }
            else {
                if (isCityInArray(addresses[i].getCityName(),printedCitys)==true){
                    continue;
                }
            }
        }
    }

    // the function get the information from the user and if the details match - the function logs in
    public User userLogin(){
        System.out.println("please enter your user name");
        Scanner sc=new Scanner(System.in);
        String enterUserName=sc.nextLine();
        System.out.println("please enter your password");
        String enterPassword=sc.nextLine();
        if (ifuserNameExist(enterUserName)==false){
            System.out.println("your user name dosnt exist");
            return null;
        }
        User foundedUser = searchUser(enterUserName);
        if (enterPassword.equals(foundedUser.getPassword())==false){
            System.out.println("wrong password");
            return null;
        }
        else {
            return foundedUser;
        }
    }

    // the function get the information from the user and if the data is valid post an new property
    public boolean postNewProperty(User user){
        Scanner sc = new Scanner(System.in);
        if (isuserAllowToPost(user)==false){
            System.out.println("user is not allowed tp post");
            return false;
        }
        else {
            showcaseCity();
            System.out.println("please enter desired city name");
            String chosenCity = sc.nextLine();
            if (!isCityExist(chosenCity)){
                System.out.println("City not exist.");
                return false;
            }
            else{
                String[] streetsInCity = printStreets(chosenCity);
                System.out.println("please enter desired street");
                String chosenStreet = sc.nextLine();
                if(!isCityInArray(chosenStreet,streetsInCity)){
                    System.out.println("wrong street");
                    return false;
                }
                else{
                    generateProperty(new Address(chosenCity,chosenStreet),user);
                    System.out.println("preoperty added");
                    return true;
                }

            }
        }
    }

    // the function checks if the user allow to post more posts
    public boolean isuserAllowToPost(User user){
        if (user.getIsMediator()==true && user.getPostedProerty().length<10){
            return true;
        }
        if (user.getIsMediator()==false && user.getPostedProerty().length<3){
            return true;
        }
        return false;
    }

    // the function checks if exist user with the given name
    public User searchUser(String username){
        for (int i = 0; i < users.length; i++) {
            if (username.equals(users[i].getUserName())){
                return users[i];
            }
        }
        return null;
    }

    // the function collect relevant data and if the data is valid create user and insert it to the list of users
    public void createUser(){
        System.out.println("please enter your username");
        Scanner sc=new Scanner(System.in);
        String newUserName=sc.nextLine();
        while (ifuserNameExist(newUserName)==true){
            System.out.println("userName already exist please enter a new one");
            newUserName =sc.nextLine();
        }
        System.out.println("please enter your passWord");
        String newPassWord=sc.nextLine();
        while (ifPasswordIsStrong(newPassWord)==false){
            System.out.println("your passWord is weak please enter a new one");
            newPassWord=sc.nextLine();
        }
        System.out.println("please enter your phone number");
        String newPhoneNumber=sc.nextLine();
        while (ifphonenumberisnotstarting(newPhoneNumber)==false){
            System.out.println("please enter a new valid phone number");
            newPhoneNumber=sc.nextLine();
        }
        boolean isMeditor=areYouMediator();
        User newUser= new User(newUserName,newPassWord,newPhoneNumber,isMeditor);
        insertNewUser(newUser);
    }

    // the function insert the new user to the users feild
    public void insertNewUser (User newUser){
        User[] newUsers=new User[users.length+1];
        for (int i = 0; i < users.length ; i++) {
            newUsers[i]=users[i];
        }
        newUsers[newUsers.length-1]=newUser;
        this.users = newUsers;
    }

    // the function validate the password the user insert and check if the password is valid
    public boolean ifPasswordIsStrong(String password) {
        char name = '$';
        char name2 = '%';
        char name3 = '_';
        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) == name || password.charAt(i) == name2 || password.charAt(i) == name3) {
                return true;
            }
        }
        System.out.println("password is not strong enough please enter a stronger password ");
        return false;
    }

    // the function validate the phone number the user insert and check if the number is valid
    public boolean ifPhoneNumberisNotlong(String phonenumber) {
        if (phonenumber.length() == 10) {
            return true;
        }
        return false;
    }

    // checks if the phone number is in length of 10
    public boolean ifphonenumberisnotstarting(String phonenumber) {
        if (ifPhoneNumberisNotlong(phonenumber) && phonenumber.charAt(0) == '0' && phonenumber.charAt(1) == '5') {
            return true;
        }
        return false;
    }

    // checks if all the chars in the given number are numbers
    public boolean enterOnlyNumbers(String phonenumber) {
        String temp = "";
        for (int i = 0; i < phonenumber.length(); i++) {
            try {
                Integer.parseInt(""+phonenumber.charAt(i));
            }
            catch (Exception e){
                return false;
            }
        }
        return true;
    }
    // the function checks if hiven username is exist in the users feild
    public boolean ifuserNameExist(String newUserName){
        for (int i = 0; i < users.length ; i++) {
            if (newUserName.equals(users[i].getUserName())){
                return true;
            }
        }
        return false;
    }

    // function checks if the user is mediator and return the answer
    public boolean areYouMediator(){
        System.out.println("are you mediator?");
        System.out.println("write yes or no");
        Scanner sc=new Scanner(System.in);
        String isMediator=sc.nextLine();
        if (isMediator.equals("yes")){
            return true;
        }
        else {
            return  false;
        }
    }

    // add given property to the list of the roperties in the feild
    public boolean addPropertyToList(Property p){
        Property[] arr = new Property[this.properties.length + 1];
        for (int i = 0; i < this.properties.length; i++) {
            arr[i] = properties[i];
        }
        arr[arr.length-1] = p;
        this.properties = arr;
        return true;
    }

    // add proprty to given list and return the list
    public Property[] addPropertyToList(Property p, Property[] list){
        Property[] arr = new Property[list.length + 1];
        for (int i = 0; i < list.length; i++) {
            arr[i] = list[i];
        }
        arr[arr.length-1] = p;
        return arr;
    }
}
