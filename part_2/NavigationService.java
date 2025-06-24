package part_2;

class NavigationFailedException extends Exception{
    public NavigationFailedException(String message){
        super(message);
    }
}
public interface NavigationService {
    void navigate(String startPoint, String endPoint, RouteValidator validator) throws NavigationFailedException;
}