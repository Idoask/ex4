public class Property {
    private Address address;
    private int numberOfRooms;
    private int price;
    private String type;
    private boolean isForItForRent;
    private int numberOfhouse;
    private int numberOfFloor;
    private User publishedUser;

    public Property(Address address1,int numberOfRooms1,int price1,String type1,boolean isForItForRent1,int numberOfhouse1,int numberOfFloor1,User user1){
        this.address=address1;
        this.numberOfRooms=numberOfRooms1;
        this.price=price1;
        this.type=type1;
        this.isForItForRent=isForItForRent1;
        this.numberOfhouse=numberOfhouse1;
        this.numberOfFloor=numberOfFloor1;
        this.publishedUser=user1;
    }

    public User getPublishedUser() {
        return publishedUser;
    }

    public Address getAddress() {
        return address;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public int getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public boolean isForItForRent() {
        return isForItForRent;
    }

    public int getNumberOfhouse() {
        return numberOfhouse;
    }

    public int getNumberOfFloor() {
        return numberOfFloor;
    }

    @Override
    public String toString() {
        String saleOrRent = "for sale: ";
        if (isForItForRent){
            saleOrRent = "for rent: ";
        }
        return
                type  +" - "+
                saleOrRent + numberOfRooms + " rooms, "+
                 + numberOfFloor + " floor."+"\n" +
                "price:" + price +"\n" +
                "contact info:" + publishedUser.getUserName()+" " +publishedUser.getPhoneNumber();
    }
}
