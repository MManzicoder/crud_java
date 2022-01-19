public class User {
    private int uId;
    private String names;
    private float amount;
    public User(String names, float amount){
      this.uId = genereateUniqueId();
      this.names = names;
      this.amount = amount;
    }

    int genereateUniqueId(){
     int uniqueId = (int)Math.floor(Math.random()* 100000);
     return uniqueId;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
