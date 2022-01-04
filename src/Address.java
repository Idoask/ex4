public class Address {
    private String cityName;
    private String streetName;

    public Address(String cityName1,String streetName1){
        this.cityName=cityName1;
        this.streetName=streetName1;
    }
    public void print(){
        System.out.println("cityName: "+cityName);
        System.out.println("streetName: "+streetName);
    }
    public String getCityName(){
        return cityName;
    }
    public String getStreetName(){
        return streetName;
    }
}
