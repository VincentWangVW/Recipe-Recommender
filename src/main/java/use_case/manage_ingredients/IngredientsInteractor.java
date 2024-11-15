package use_case.manage_ingredients;

public class IngredientsInteractor implements IngredientsInputBoundary {
    private final IngredientsIOutputBoundary outputBoundary;

    public IngredientsInteractor(IngredientsIOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void return_to_main() {
        outputBoundary.return_to_main();
    }
}
