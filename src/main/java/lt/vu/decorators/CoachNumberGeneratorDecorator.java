package lt.vu.decorators;

import lt.vu.services.ICoachNumberGenerator;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
public abstract class CoachNumberGeneratorDecorator implements ICoachNumberGenerator {
    @Inject
    @Delegate
    @Any
    ICoachNumberGenerator coachNumberGenerator;

    @Override
    public Integer generateCoachNumber() {
        try {
            Integer generatedCoachNumber = coachNumberGenerator.generateCoachNumber();
            Thread.sleep(5000);
            System.out.println("DECORATOR TASK DONE");
            return generatedCoachNumber + 1000;
        } catch (InterruptedException e) {
            return 0;
        }
    }
}
