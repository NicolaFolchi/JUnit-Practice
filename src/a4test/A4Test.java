package a4test;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;


import a4.*;

public class A4Test {

	static public String[] getTestNames() {
		String[] test_names = new String[11];

		test_names[0] = "exampleTest";
		test_names[1] = "testSampleIter";
		test_names[2] = "paintPictureTest";
		test_names[3] = "paintPictureTestWithFactor";
		test_names[4] = "testGetCaption";
		test_names[5] = "testExtractSubpicture";
		test_names[6] = "testWindow";
		test_names[7] = "testExtractCaption";
		test_names[8] = "testZigZag";
		test_names[9] = "testWindow2";
		test_names[10] = "testRectanglePaint";


		return test_names;
	}
	Pixel red = new ColorPixel(1, 0, 0);
	Pixel green = new ColorPixel(0, 1, 0);
	Pixel blue = new ColorPixel(0, 0, 1);

	@Test
	public void exampleTest() {
	}

	@Test
	public void testSampleIter() {
		Pixel[][] pixelArray = new Pixel[3][3];

		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				pixelArray[x][y] = new ColorPixel(0, 0, 0);
			}
		}

		Picture picture = new MutablePixelArrayPicture(pixelArray, "My caption");

		//
		Iterator<Pixel> iter = picture.sample(0, 0, 2, 2);
		assertTrue(iter.hasNext());
		assertEquals(pixelArray[0][0], iter.next());
		assertTrue(iter.hasNext());
		assertEquals(pixelArray[2][0], iter.next());
		assertTrue(iter.hasNext());
		assertEquals(pixelArray[0][2], iter.next());
		assertTrue(iter.hasNext());
		assertEquals(pixelArray[2][2], iter.next());

	}

	@Test
	public void paintPictureTest() {
		Pixel[][] parray = new Pixel[3][3];
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				parray[x][y] = new ColorPixel(0, 0, 0);
			}
		}
		Pixel[][] parray2 = new Pixel[2][2];
		for (int x = 0; x < 2; x++) {
			for (int y = 0; y < 2; y++) {
				parray2[x][y] = new ColorPixel(0, 0, 0);
			}
		}
		Picture myPicture = new MutablePixelArrayPicture(parray, "My caption");
		Picture p = new ImmutablePixelArrayPicture(parray2, "My caption");


		myPicture.paint(0, 0, p.getPixel(0,0));
		myPicture.paint(1, 0, p.getPixel(1,0));
		myPicture.paint(0, 1, p.getPixel(0,1));
		myPicture.paint(1, 1, p.getPixel(1,1));

		Iterator<Pixel> i = myPicture.sample(0, 0, 2, 2);
		assertTrue(i.hasNext());
		assertEquals(i.next(), myPicture.getPixel(0, 0));
		assertTrue(i.hasNext());
		assertEquals(i.next(), myPicture.getPixel(2, 0));
		assertTrue(i.hasNext());
		assertEquals(i.next(), myPicture.getPixel(0, 2));
		assertTrue(i.hasNext());
		assertEquals(i.next(), myPicture.getPixel(2, 2));
	}

	@Test
	public void paintPictureTestWithFactor() {
		Pixel[][] parray = new Pixel[3][3];
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				parray[x][y] = new ColorPixel(0, 0, 0);
			}
		}
		Pixel[][] parray2 = new Pixel[2][2];
		for (int x = 0; x < 2; x++) {
			for (int y = 0; y < 2; y++) {
				parray2[x][y] = new ColorPixel(0, 0, 0);
			}
		}
		Picture myPicture = new MutablePixelArrayPicture(parray, "My caption");
		Picture p = new ImmutablePixelArrayPicture(parray2, "My caption");


		myPicture.paint(0, 0, p.getPixel(0, 0).blend(p.getPixel(0, 0), 0.5));
		myPicture.paint(1, 0, p.getPixel(1,0).blend(p.getPixel(1, 0), 0.5));
		myPicture.paint(0, 1, p.getPixel(0,1).blend(p.getPixel(0, 1), 0.5));
		myPicture.paint(1, 1, p.getPixel(1,1).blend(p.getPixel(1, 1), 0.5));

		Iterator<Pixel> i = myPicture.sample(0, 0, 2, 2);
		assertTrue(i.hasNext());
		assertEquals(i.next(), myPicture.getPixel(0, 0));
		assertTrue(i.hasNext());
		assertEquals(i.next(), myPicture.getPixel(2, 0));
		assertTrue(i.hasNext());
		assertEquals(i.next(), myPicture.getPixel(0, 2));
		assertTrue(i.hasNext());
		assertEquals(i.next(), myPicture.getPixel(2, 2));


	}

	@Test
	public void testGetCaption() {
		Pixel[][] parray = new Pixel[3][3];
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				parray[x][y] = new ColorPixel(0, 0, 0);
			}
		}

		Picture myPicture = new MutablePixelArrayPicture(parray, "My caption");

		myPicture.setCaption("Hello this is my Caption");

		assertEquals("Hello this is my Caption", myPicture.getCaption());

	}

	@Test
	public void testExtractSubpicture() {
		Pixel[][] parray = new Pixel[3][3];
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				parray[x][y] = new ColorPixel(0, 0, 0);
			}
		}

		Picture myPicture =  new MutablePixelArrayPicture(parray, "My caption");

		Pixel[][] parray2 = new Pixel[2][2];
		for (int x = 0; x < 2; x++) {
			for (int y = 0; y < 2; y++) {
				parray2[x][y] = myPicture.getPixel(x, y);
			}
		}
		Picture subPic =  new MutablePixelArrayPicture(parray2, "My caption");


		myPicture.extract(0, 0, 1, 1);

		assertEquals(myPicture.getCaption(), myPicture.extract(0, 0, 1, 1).getCaption());
		assertEquals(myPicture.getPixel(0, 0), subPic.getPixel(0, 0));
		assertEquals(myPicture.getPixel(1, 0), subPic.getPixel(1, 0));
		assertEquals(myPicture.getPixel(0, 1), subPic.getPixel(0, 1));
		assertEquals(myPicture.getPixel(1, 1), subPic.getPixel(1, 1));
	}

	@Test
	public void testWindow() {
		Pixel[][] parray = new Pixel[3][3];
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				parray[x][y] = new ColorPixel(0, 0, 0);
			}
		}

		Picture myPicture =  new MutablePixelArrayPicture(parray, "My caption");

		Pixel[][] parray2 = new Pixel[2][2];
		for (int x = 0; x < 2; x++) {
			for (int y = 0; y < 2; y++) {
				parray2[x][y] = myPicture.getPixel(x, y);
			}
		}
		Picture subPic =  new MutablePixelArrayPicture(parray2, "My caption");

		Iterator<Pixel> i = myPicture.sample(0, 0, 2, 2);
		assertTrue(i.hasNext());
		assertEquals(i.next(), myPicture.getPixel(0, 0));
		assertTrue(i.hasNext());
		assertEquals(i.next(), myPicture.getPixel(2, 0));
		assertTrue(i.hasNext());
		assertEquals(i.next(), myPicture.getPixel(0, 2));
		assertTrue(i.hasNext());
		assertEquals(i.next(), myPicture.getPixel(2, 2));

		assertTrue(4 > myPicture.getWidth());
		assertTrue(4 > myPicture.getHeight());

	}

	@Test
	public void testExtractCaption() {
		Pixel[][] parray = new Pixel[3][3];
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				parray[x][y] = new ColorPixel(0, 0, 0);
			}
		}

		Picture myPicture =  new MutablePixelArrayPicture(parray, "My caption");

		Pixel[][] parray2 = new Pixel[2][2];
		for (int x = 0; x < 2; x++) {
			for (int y = 0; y < 2; y++) {
				parray2[x][y] = myPicture.getPixel(x, y);
			}
		}
		Picture subPic =  new MutablePixelArrayPicture(parray2, "My caption");


		myPicture.extract(0, 0, 1, 1);

		assertEquals(myPicture.getCaption(), myPicture.extract(0, 0, 1, 1).getCaption());
	}

	@Test
	public void testZigZag() {
		Pixel[][] pixelArray = new Pixel[3][3];

		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				pixelArray[x][y] = new ColorPixel(0, 0, 0);
			}
		}

		Picture picture = new MutablePixelArrayPicture(pixelArray, "My caption");


		Iterator<Pixel> iter = picture.zigzag();
		assertTrue(iter.hasNext());
		assertEquals(pixelArray[0][0], iter.next());
		assertTrue(iter.hasNext());
		assertEquals(pixelArray[1][0], iter.next());
		assertTrue(iter.hasNext());
		assertEquals(pixelArray[0][1], iter.next());
		assertTrue(iter.hasNext());
		assertEquals(pixelArray[0][2], iter.next());
		assertTrue(iter.hasNext());
		assertEquals(pixelArray[1][1], iter.next());
		assertTrue(iter.hasNext());
		assertEquals(pixelArray[2][0], iter.next());
	}

	@Test
	public void testWindow2() {
		Pixel[][] pixelArray = new Pixel[5][5];

		for (int x = 0; x < 5; x++) {
			for (int y = 0; y < 5; y++) {
				pixelArray[x][y] = new ColorPixel(0, 0, 0);
			}
		}

		Picture picture = new MutablePixelArrayPicture(pixelArray, "My caption");

		Iterator<SubPicture> i = picture.window(2, 2);
		Iterator<SubPicture> j = picture.window(2, 2);

		assertTrue(i.hasNext());
		assertTrue(j.hasNext());
		assertEquals(picture.extract(0, 0, 2, 2).getPixel(0, 0), i.next().getPixel(0, 0));
		assertEquals(picture.extract(0, 0, 2, 2).getPixel(1, 1), j.next().getPixel(1, 1));

		assertTrue(i.hasNext());
		assertTrue(j.hasNext());
		assertEquals(picture.extract(1, 0, 2, 2).getPixel(0, 0), i.next().getPixel(0, 0));
		assertEquals(picture.extract(1, 0, 2, 2).getPixel(1, 1), j.next().getPixel(1, 1));

	}

	@Test
	public void testRectanglePaint() {
		Pixel[][] parray = new Pixel[5][10];

		for (int x=0; x<5; x++) {
			for (int y=0; y<10; y++) {
				parray[x][y] = red;
			}
		}

		Picture myPicture = new MutablePixelArrayPicture(parray, "my caption");

		Picture r = myPicture.paint(-1, -1, 1, 1, blue);
		assertEquals(myPicture, r);

		for (int x=0; x<5; x++) {
			for (int y=0; y<10; y++) {
				if (x >= -1 && x <=1 && y >= -1 && y <= 1) {
					checkPixelSimilitude(blue, myPicture.getPixel(x, y));
				} else {
					checkPixelSimilitude(red, myPicture.getPixel(x, y));		
				}
			}
		}		

	}

	private static boolean checkPixelSimilitude(Pixel a, Pixel b) {
		assertEquals(a.getRed(), b.getRed(), 0.001);
		assertEquals(a.getGreen(), b.getGreen(), 0.001);
		assertEquals(a.getBlue(), b.getBlue(), 0.001);

		return true;
	}

}

