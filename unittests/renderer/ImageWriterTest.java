package renderer;




import primitives.Color;
import renderer.ImageWriter;

public class ImageWriterTest {
    public void testWriteToImage() {
        ImageWriter myImg = new ImageWriter("myImg", 800, 500);
        Color blue = new Color(0, 0, 270);
        Color red = new Color(270, 0, 0);
        for (int x = 0; x < 800; x++)
            for (int y = 0; y < 500; y++) {
                if (y % 50 == 0 || x % 50 == 0)
                    myImg.writePixel(x, y, red);
                else
                    myImg.writePixel(x, y, blue);
            }
        myImg.writeToImage();
    }
}
