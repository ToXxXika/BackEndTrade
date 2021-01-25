package com.example.demo.Controllers;

import com.example.demo.Documents.Product;
import com.example.demo.Repositories.ProductRepository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;


@CrossOrigin("*")
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ProductRepository PR;

    @GetMapping("/getProducts")
    public List<Product> getAllProducts(){return PR.findAll();}

    @PostMapping("addProduct")
    public boolean addProduct(@RequestBody Product p,@RequestParam(name="email") String mail){
        try{

            //TODO : Custom Matricule
           CreateQrCode(p.getMatriculeproduit(),p.getNomproduit(),mail);
            PR.save(p);
            return true;
        }catch(Exception e){
            return false;
        }
    }


    public static void CreateQrCode(String id , String nameProd,String mail)throws WriterException, IOException{
       try {
           String qrCodeText = id;
           String filePath =  nameProd+".png";
           int size = 125;
           String fileType = "png";
           File qrFile = new File(filePath);
           createQrImage(qrFile,qrCodeText,size,fileType,mail);
           System.out.println("DONE");
       }catch (IOException E){
           System.out.println(E.getMessage());
       }


    }
    public static void createQrImage(File qrFile,String qrCodeText,int size,String fileType,String mail)throws WriterException,IOException{
      try {
          Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
          hintMap.put(EncodeHintType.ERROR_CORRECTION,ErrorCorrectionLevel.L);
          QRCodeWriter qrCodeWriter = new QRCodeWriter();
          BitMatrix byteMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE,size,size,hintMap);
          int matrixWidth = byteMatrix.getWidth();
          BufferedImage image =  new BufferedImage(matrixWidth,matrixWidth,BufferedImage.TYPE_INT_RGB);
          image.createGraphics();
          Graphics2D graphics = (Graphics2D) image.getGraphics();
          graphics.setColor(Color.white);
          graphics.fillRect(0,0,matrixWidth,matrixWidth);
          graphics.setColor(Color.BLACK);
          for (int i = 0; i < matrixWidth; i++) {
              for (int j = 0; j < matrixWidth; j++) {
                  if (byteMatrix.get(i, j)) {
                      graphics.fillRect(i, j, 1, 1);
                  }
              }
          }
          ImageIO.write(image, fileType, qrFile);
          sendMail(mail,qrFile);
      }catch (Exception E ){
          System.out.println(E.getMessage());
      }
    }
    public static  void sendMail(String mail,File file){
        final String username = "mabrouki552";
        final String password ="Freefallaga123";
       String from ="mabrouki552@gmail.com";
        String to = mail;
        String host ="localhost";
        Properties prop  = new Properties();
        prop.put("mail.smtp.host","smtp.gmail.com");
        prop.put("mail.smtp.auth","true");
        prop.put("mail.smtp.starttls.enable","true");
        Session session =  Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username,password);
            }
        });
        try {
           MimeMessage message = new MimeMessage(session);
           Multipart multipart = new MimeMultipart();
           MimeBodyPart mimeBodyPart = new MimeBodyPart();
           message.setFrom(new InternetAddress(from));
           message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
           message.setSubject("this is your product QR CODE");
           mimeBodyPart.attachFile(file);
           multipart.addBodyPart(mimeBodyPart);
           message.setContent(multipart);
            Transport.send(message);
            System.out.println("WEREREREREY");
        }catch (Exception E ){
            System.out.println(E.getMessage());
        }

    }
    @GetMapping("/getProductByProductName")
    public int getProductByName(@RequestParam(name = "name") String name){
        Query q = new Query();
        q.addCriteria(Criteria.where("nomproduit").is(name));
        List<Product> products = mongoTemplate.find(q, Product.class);
        return products.size();
    }

}
