/**
 * General exception indicating that a simulation step could not be
 * completed successfully.
 *
 * <p>This class serves as the common superclass of all exceptions that
 * may occur during the execution of a simulation step.</p>
 *
 * <p>Each {@code StepException} stores the simulation step in which the
 * problem occurred.</p>
 *
 * <p>Typical subclasses are:</p>
 * <ul>
 *   <li>{@link UnresolvedCollisionException}</li>
 *   <li>{@link SimulationStepException}</li>
 * </ul>
 */
public class StepException extends Exception {

    private int step;

    /**
     * Creates a new step exception for the specified simulation step.
     *
     * @param step the simulation step in which the exception occurred;
     *             {@code step >= 0}
     */
    public StepException(int step) {

        this.step = step;
    }

    /**
     * Returns the simulation step in which the exception occurred.
     *
     * @return the simulation step
     */
    public int getStep() {

        return step;
    }

    /**
     * Returns a textual representation of this exception.
     *
     * <p>The returned string is intended for diagnostic output and log files.
     * It contains at least the simulation step in which the exception
     * occurred.</p>
     *
     * <p>Subclasses may extend the returned representation with additional
     * information specific to the concrete exception type.</p>
     *
     * @return a textual representation of this exception
     */
    @Override
    public String toString() {

        return "step=" + step;
    }
}