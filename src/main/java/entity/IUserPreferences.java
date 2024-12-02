package entity;

public interface IUserPreferences {
    public int getMissingIngredientsLimit();
    public boolean getDairyFree();
    public boolean getGlutenFree();
    public String[] getAllergies();
    void addAllergy(String allergyName);
    void removeAllergy(String allergyName);
    void setMissingIngredientsLimit(int shopAmount);
}
