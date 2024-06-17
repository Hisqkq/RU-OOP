package quadtrees;

import java.io.IOException;
import java.io.Writer;


public class WhiteLeaf implements QuadTreeNode {

	private final int size;

	public WhiteLeaf(int size) {
		this.size = size;
	}

	@Override
	public void fillBitmap(int x, int y, int width, Bitmap bitmap) {
		bitmap.fillArea(x, y, bitmap.getWidth()/size, true);
	}

	@Override
    public void writeNode(Writer out) {
        try {
            if (this instanceof WhiteLeaf) {
                out.write("01");
            } 
        } catch (IOException e) {
            System.err.println("Error writing node to output stream: " + e.getMessage());
        }
    }

}



