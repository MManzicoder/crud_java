public class User {
    private String names;
    private String email;
    public User(String names, String email){
      this.names = names;
      this.email = email;
    }

    int genereateUniqueId(){
     int uniqueId = (int)Math.floor(Math.random()* 100000);
     return uniqueId;
    }



    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

}
