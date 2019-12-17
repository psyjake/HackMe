package hackme.screens;

/**
 * To allow the controllers to interact with each other
 */
public interface ScreenReturner {
    public void onScreenReturn(String message) throws Exception;
}
