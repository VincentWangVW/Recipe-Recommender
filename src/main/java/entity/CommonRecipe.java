package entity;

import java.util.ArrayList;

public class CommonRecipe implements Recipe {
    private final String name;
    private final String url;
    private final ArrayList<String> missingItems;

    public CommonRecipe(String name, String url, ArrayList<String> missingItems) {
        this.name = name;
        this.url = url;
        this.missingItems = missingItems;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public ArrayList<String> getMissingItems() {
        return missingItems;
    }

}
