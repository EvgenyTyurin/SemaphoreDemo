import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * Semaphore mechanism demo
 */

public class SemaphoreDemo {

    public static void main(String[] args) throws InterruptedException {
        int slots = 3;
        // Init login service with limit
        LoginService loginService = new LoginService(slots);
        // Prepare threads execution
        ExecutorService executorService = Executors.newFixedThreadPool(slots);
        // Use login service to limit
        IntStream.range(1, slots + 1).forEach(userNum -> {
            System.out.println("User " + userNum + " try to login...");
            executorService.execute(loginService::tryLogin);
        });
        executorService.shutdown();
        Thread.sleep(1000);
        System.out.println("Slots avail " + loginService.slotsFree()); // 0
        loginService.logout();
        System.out.println("Slots avails after logout " + loginService.slotsFree()); // 1
    }

}
