package lt.vu.services;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.Random;

@ApplicationScoped
public class CoachNumberGenerator implements Serializable, ICoachNumberGenerator {
    public Integer generateCoachNumber() {
        try {
            Thread.sleep(3000);
            System.out.println("THREAD SLEEP DONE");
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        Integer generatedCoachNumber = new Random().nextInt(100);
        return generatedCoachNumber;
    }

}
