public class User {
    private String userName;
    private String password;
    private String phoneNumber;
    private boolean isMediator;
    private Property[] postedProerty;

    public User(String userName1,String password1, String phoneNumber1, boolean isMediator1){
        this.userName = userName1;
        this.isMediator = isMediator1;
        this.password = password1;
        this.phoneNumber = phoneNumber1;
        this.postedProerty=new Property[0];
    }

    public String getUserName(){
        return userName;
    }
    public String getPassword() {
        return this.password;
    }
    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    public boolean getIsMediator(){
        return isMediator;
    }
    public Property[] getPostedProerty(){
        return postedProerty;
    }
}
