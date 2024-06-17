package quadtrees;

import java.io.IOException;
import java.io.Writer;

public class BlackLeaf implements QuadTreeNode {

    private final int size;

    public BlackLeaf(int size) {
        this.size = size;
    }

    @Override
    public void fillBitmap(int x, int y, int width, Bitmap bitmap) {
        bitmap.fillArea(x, y, bitmap.getWidth()/size, false);
    }

    @Override
    public void writeNode(Writer out) {
        try {
            if (this instanceof BlackLeaf) {
                out.write("00");
            } 
        } catch (IOException e) {
            System.err.println("Error writing node to output stream: " + e.getMessage());
        }
    }

}
