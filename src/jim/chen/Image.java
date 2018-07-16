package jim.chen;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.text.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * Servlet implementation class Image
 */
@WebServlet("/Image")
public class Image extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Image() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("image/jpeg");
		BufferedImage bimage = new BufferedImage(55,30,BufferedImage.TYPE_INT_RGB); 
		Graphics2D g = bimage.createGraphics(); 
		g.setColor(Color.white); 

		String text = String.format("%04d",(int)(Math.random()*9999));
		Font f = new Font("Arial Black", Font.PLAIN, 16); 
		
		g.setFont(f);

		g.drawString(text, (int) 5, (int) 20);  
		g.dispose();
		try { 
			OutputStream out = response.getOutputStream();
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out); 

			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bimage); 

			param.setQuality(1, true); 
			encoder.encode(bimage, param); 
			out.close(); 
		}catch(Exception e) {
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
