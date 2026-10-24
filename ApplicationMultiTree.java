import codedraw.CodeDraw;
import java.awt.Color;

/**
 * Utility class for visualizing a {@link PhysicalMultiTree}.
 *
 * <p>This class provides static methods to draw a tree of {@link Physical}
 * objects using a {@link CodeDraw} canvas.</p>
 */
public class ApplicationMultiTree {

    /**
     * Draws the specified tree on the given CodeDraw canvas.
     *
     * @param cd the CodeDraw canvas; {@code cd != null}
     * @param tree the tree to draw; {@code tree != null}
     */
    public static void draw(CodeDraw cd, PhysicalMultiTree tree) {
        if (tree == null || tree.getPrimary() == null) {
            return;
        }

        drawRecursive(cd, tree.getPrimary(), tree);
    }

    /**
     * Recursively draws the subtree rooted at {@code current}.
     */
    private static void drawRecursive(CodeDraw cd,
                                      Physical current,
                                      PhysicalMultiTree tree) {

        Vector2D pos = current.getPosition();
        double x = pos.getX();
        double y = pos.getY();
        double r = current.getRadius();

        // Kreis zeichnen (optional farbig je Typ)
        if (current instanceof Nest) {
            cd.setColor(Color.BLUE);
        } else if (current instanceof FoodSource) {
            cd.setColor(Color.GREEN);
        } else {
            cd.setColor(Color.BLACK);
        }

        cd.drawCircle(x, y, r);

        Physical[] children = tree.childrenOf(current);
        if (children == null) {
            return;
        }

        for (Physical child : children) {

            Vector2D cPos = child.getPosition();

            // Verbindung zeichnen
            cd.setColor(Color.DARK_GRAY);
            cd.drawLine(x, y, cPos.getX(), cPos.getY());

            drawRecursive(cd, child, tree);
        }
    }

    /**
     * Demo application: builds a tree and visualizes it.
     */
    public static void main(String[] args) {

        CodeDraw cd = new CodeDraw(800, 600);

        // ===== Baum wie im Test =====

        Physical root = new Nest(new Vector2D(400, 270), 200);
        PhysicalMultiTree tree = new PhysicalMultiTree(root);

        Physical p1 = new FoodSource(new Vector2D(350, 300), 80);
        Physical p2 = new FoodSource(new Vector2D(360, 335), 7);
        Physical p3 = new FoodSource(new Vector2D(370, 275), 5);
        Physical p4 = new FoodSource(new Vector2D(350, 280), 30);
        Physical p5 = new FoodSource(new Vector2D(335, 285), 10);
        Physical p6 = new Nest(new Vector2D(500, 300), 60);
        Physical p7 = new Nest(new Vector2D(400, 200), 90);
        Physical p8 = new Nest(new Vector2D(390, 260), 250);

        tree.insert(p1);
        tree.insert(p2);
        tree.insert(p3);
        tree.insert(p5);
        tree.insert(p6);
        tree.insert(p7); // does not fit
        tree.insert(p8); // new root

        // Debug-Ausgabe im Terminal
        System.out.println(tree);

        // ===== Zeichnen =====
        cd.clear(Color.WHITE);
        draw(cd, tree);
        cd.show();
    }
}