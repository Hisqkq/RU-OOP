package quadtrees;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class QTree {
	private final QuadTreeNode root;
	public int size = 1;

	public QTree(Reader input) {
		root = readQTree(input, size);
	}

	public QTree(Bitmap bitmap) {
		root = bitmap2QTree(0, 0, bitmap.getWidth(), bitmap, size);
	}

	public void fillBitmap(Bitmap bitmap) {
		root.fillBitmap(0, 0, bitmap.getWidth(), bitmap);
	}

	public void writeQTree(Writer sb) {
		root.writeNode(sb);
	}

	private static QuadTreeNode readQTree(Reader input, int size) {
		try {
			int token = input.read();
			if (token == '0') {
				token = input.read();
				if (token == '1') {
					return new WhiteLeaf(size);
				} else if (token == '0') {
					return new BlackLeaf(size);
				}
			} else if (token == '1') {
				QuadTreeNode[] children = new QuadTreeNode[4];
				for(int i = 0; i < children.length; i++){
					children[i] = readQTree(input, size*2);
				}
				return new GreyNode(children, size);
			}
		} catch (IOException e) {
			System.err.println("Error reading input: " + e.getMessage());
		}
		return null;
	}
	
	public static QuadTreeNode bitmap2QTree(int x, int y, int width, Bitmap bitmap, int size) {
		boolean isWhite = bitmap.getBit(x, y);
		if (isWhite) {
			return new WhiteLeaf(size);
		} else if (width == 1) {
			return new BlackLeaf(size);
		} else {
			QuadTreeNode[] children = new QuadTreeNode[4];
			int subWidth = width / 2;
			children[0] = bitmap2QTree(x, y, subWidth, bitmap, size*2);
			children[1] = bitmap2QTree(x + subWidth, y, subWidth, bitmap, size*2);
			children[2] = bitmap2QTree(x, y + subWidth, subWidth, bitmap, size*2);
			children[3] = bitmap2QTree(x + subWidth, y + subWidth, subWidth, bitmap, size*2);
			return new GreyNode(children, size);
		}
	}
	
}
