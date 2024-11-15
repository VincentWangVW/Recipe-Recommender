package entity;

public class CommonRecipe implements Recipe {
    private final String name;
    private final String url;
    private final Integer missingItems;

    public CommonRecipe(String name, String url, Integer missingItems) {
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
    public Integer getMissingItems() {
        return missingItems;
    }

}
