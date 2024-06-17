package quadtrees;

import java.io.Writer;

public class GreyNode implements QuadTreeNode {
    private final QuadTreeNode[] children;
    public int size;

    public GreyNode(QuadTreeNode[] children, int size) {
        this.children = children;
        this.size = size;
    }

    public QuadTreeNode[] getChildren() {
        return children;
    }


    @Override
    public void fillBitmap(int x, int y, int width, Bitmap bitmap) {
        int halfWidth = width / 2;
        children[0].fillBitmap(x, y, halfWidth, bitmap);
        children[1].fillBitmap(x + halfWidth, y, halfWidth, bitmap);
        children[2].fillBitmap(x + halfWidth, y + halfWidth, halfWidth, bitmap);
        children[3].fillBitmap(x, y + halfWidth, halfWidth, bitmap);
    }

    public void writeNode(Writer out) {
        try {
            out.write("1");
            for (int i = 0; i < children.length; i++) {
                children[i].writeNode(out);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

