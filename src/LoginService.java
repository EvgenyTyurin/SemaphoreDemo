import java.util.concurrent.Semaphore;

public class LoginService {

    private final Semaphore semaphore;

    public LoginService(int usersLimit) {
        semaphore = new Semaphore(usersLimit);
    }

    boolean tryLogin() {
        boolean result =  semaphore.tryAcquire();
        if (result) {
            System.out.println("Login success");
        } else {
            System.out.println("Login failure");
        }
        return result;
    }

    void logout() {
        System.out.println("Logout");
        semaphore.release();
    }

    int slotsFree() {
        return semaphore.availablePermits();
    }


}
