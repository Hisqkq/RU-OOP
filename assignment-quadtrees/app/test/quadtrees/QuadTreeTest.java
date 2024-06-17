package quadtrees;

import static org.junit.Assert.*;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

public class QuadTreeTest {

	@Test
	public void readAllBlack() {
		Reader r = new StringReader("00");
		QTree tree = new QTree(r);
		Bitmap b2x2 = new Bitmap(2, 2);
		tree.fillBitmap(b2x2);
		assertEquals("OO\n" + "OO\n", b2x2.toString());

		Bitmap b4x4 = new Bitmap(4, 4);
		tree.fillBitmap(b4x4);
		assertEquals("" + // prevent the code formatter from destroying the ASCII art
				"OOOO\n" + //
				"OOOO\n" + //
				"OOOO\n" + //
				"OOOO\n", b4x4.toString());
	}

	@Test
	public void readAllWhite() {
		Reader r = new StringReader("01");
		QTree tree = new QTree(r);
		Bitmap b2x2 = new Bitmap(2, 2);
		tree.fillBitmap(b2x2);
		assertEquals("**\n" + "**\n", b2x2.toString());

		Bitmap b4x4 = new Bitmap(4, 4);
		tree.fillBitmap(b4x4);
		assertEquals("" + //
				"****\n" + //
				"****\n" + //
				"****\n" + //
				"****\n", b4x4.toString());
	}

	@Test
	public void readMixedBlackWhite() {
		Reader r = new StringReader("100010001");
		QTree tree = new QTree(r);
		Bitmap b2x2 = new Bitmap(2, 2);
		tree.fillBitmap(b2x2);
		assertEquals("O*\n" + "*O\n", b2x2.toString());

		Bitmap b4x4 = new Bitmap(4, 4);
		tree.fillBitmap(b4x4);
		assertEquals("" + //
				"OO**\n" + //
				"OO**\n" + //
				"**OO\n" + //
				"**OO\n", b4x4.toString());
	}
	
	
	@Test
	public void writeAllBlack1x1() {
		Bitmap b1x1 = new Bitmap(1, 1);
		b1x1.fillArea(0, 0, 1, false);
		QTree tree = new QTree(b1x1);
		Writer w = new StringWriter();
		tree.writeQTree(w);
		assertEquals("O\n", b1x1.toString());
		assertEquals("00", w.toString());
	}

	public void writeAllBlack4x4() {
		Bitmap b4x4 = new Bitmap(4, 4);
		b4x4.fillArea(0, 0, 4, false);
		QTree tree = new QTree(b4x4);
		Writer w = new StringWriter();
		tree.writeQTree(w);
		assertEquals("OOOO\n" + "OOOO\n" + "OOOO\n" + "OOOO\n", b4x4.toString());
		assertEquals("00", w.toString());
	}

	
	@Test
	public void writeAllWhite1x1() {
		Bitmap b1x1 = new Bitmap(1, 1);
		b1x1.fillArea(0, 0, 1, true);
		QTree tree = new QTree(b1x1);
		Writer w = new StringWriter();
		tree.writeQTree(w);
		assertEquals("*\n", b1x1.toString());
		assertEquals("01", w.toString());
	}

	public void writeAllWhite4x4() {
		Bitmap b4x4 = new Bitmap(4, 4);
		b4x4.fillArea(0, 0, 4, true);
		QTree tree = new QTree(b4x4);
		Writer w = new StringWriter();
		tree.writeQTree(w);
		assertEquals("****\n" + "****\n" + "****\n" + "****\n", b4x4.toString());
		assertEquals("01", w.toString());
	}

}
