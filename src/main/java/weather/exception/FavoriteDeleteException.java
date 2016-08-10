package weather.exception;

public class FavoriteDeleteException extends RuntimeException {
    public FavoriteDeleteException() {
        super("Could not delete favorite.");
    }
}
