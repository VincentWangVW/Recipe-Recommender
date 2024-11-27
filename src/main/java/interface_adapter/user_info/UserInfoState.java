package interface_adapter.user_info;

import java.util.List;
import java.util.HashSet;

public class UserInfoState {
    private Integer shopAmount;
    private HashSet<String> allergies;
    public Integer getShopAmount(){
        return shopAmount;
    }
    public void setShopAmount(Integer shopAmount){
        this.shopAmount = shopAmount;
    }
    public HashSet<String> getAllergies(){
        return allergies;
    }
    public void setAllergies(HashSet<String> allergies){
        this.allergies = allergies;
    }
}
