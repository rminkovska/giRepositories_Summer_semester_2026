/**
 * Exception indicating that a collection was structurally modified
 * while an iteration was in progress.
 *
 * <p>This exception is used by fail-fast iterators of the data structures
 * in this project.</p>
 *
 * <p>The exception message states how many structural modifications
 * were detected since the iterator was created.</p>
 *
 * <p>The message must have the following format:</p>
 *
 * <pre>
 * "2 modification(s) detected!"
 * </pre>
 */
public class ConcurrentModificationException extends RuntimeException {

    //TODO: complete class definition.
    public ConcurrentModificationException(int count) {
        super(count + " modification(s) detected!");
    }
}
