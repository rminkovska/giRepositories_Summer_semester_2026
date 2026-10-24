/**
 * Describes an object that participates actively in the simulation
 * by updating its internal state over time.
 *
 * <p>A {@code Steppable} object represents a dynamic component of the system.
 * During each simulation step, its {@link #step()} method is invoked in order
 * to update its state according to its internal logic and the current state
 * of the environment.</p>
 *
 * <p>Typical examples of {@code Steppable} objects include agents (such as
 * ants), controllers, or global processes that evolve over time.</p>
 *
 * Please do not change this file!
 */
public interface Steppable {

    /**
     * Performs one simulation step.
     *
     * <p>This method updates the internal state of the object by one discrete
     * time step.</p>
     *
     * @throws StepException if the simulation step cannot be completed
     * successfully
     */
    void step() throws StepException;
}