/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uzayoyun;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;//method kullanrak topu hareket ettircez
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;//klavye işlenlerini anlaması için tuşa basıldığında gerekli methodları getirir
import java.awt.image.BufferedImage;//fotoğraf için
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

class ates{
    
    private int x;
    private int y;

    public ates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}


public class Oyun extends JPanel implements KeyListener,ActionListener{

Timer timer = new Timer(5,this);   //5milisaniyede hareket eder 
private int gecen_sure=0;
private int harcanan_ates=0;

private BufferedImage image;

private ArrayList<ates> atesler =new ArrayList<ates>();

private int atesdirY=1;//atesi y yönünde 1 br yukarı götürmek için

private int topX=400;//top 0a 0 noktasından başlayacak ve sağa sola götürücez
private int topdirX=2;//bunu topXe ekleyerek sağa sola oynatıcaz

private int uzayGemisi=400;//konumu
private int diruzayGemisi=20;//20 br sola gitmrk için kullanıcaz
public boolean kontrolEt(){
    for(ates ats:atesler){
        if(new Rectangle(ats.getX(),ats.getY(),10,20 ).intersects(new Rectangle(topX,0,20,20))){//dikdörtgenle top çarpıştı mı onu anlamak için
         return true;   
            
        }
        }
        return false;
    }

    public Oyun() {
        
    try {
        image = ImageIO.read(new FileImageInputStream(new File("rocket-png-40794.png")));
                
  } catch (IOException ex) {
        Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
    }    
        setBackground(Color.BLACK);
        timer.start();//başlatır
        
      } 

    @Override
    public void paint(Graphics g) {
         super.paint(g); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
         gecen_sure+=5;
         g.setColor(Color.red);
         g.fillOval(topX,0,20 ,20);//topun konumu ve büyüklüğü
         g.drawImage(image,uzayGemisi,500,image.getWidth()/4, image.getHeight() / 4,this);//foto,x konumu,y konumu,genişlik,yükseklik
        for (ates ats:atesler){
            if(ats.getY()<0){//başlangıç konumundan 1 çıkartmıştık bunu kontrol ederiz
                atesler.remove(ats);//panelden çıkan ateşi siler
        }   
        }
        g.setColor(Color.BLUE);
        for (ates ats:atesler){
        g.fillRect(ats.getX(),ats.getY(), 10, 20);
        
        }
        if(kontrolEt()){
            timer.stop();
            String message="Kazanadınız...\n"+
                            "Harcanan ates:"+harcanan_ates+
                            "\nGeçen süre:"+gecen_sure/1000.0;
            JOptionPane.showMessageDialog(this, message);
            System.exit(0);//oyunun sonlanması için yazdık
        }
    }

    @Override
    public void repaint() {
        super.repaint(); // oyun bittiğinde tekrar çizer //Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
      





    


    /**
     *
     * @param e
     */
    @Override
    public void keyTyped(KeyEvent e) {   
//methodlar bunlar Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();
      if(c==KeyEvent.VK_LEFT){//sola götürmek için
          if(uzayGemisi<=0){//en sola gittiyse
              uzayGemisi=0;// en solda kalsın
              
          }else{//hareket ettirir
              uzayGemisi-=diruzayGemisi;
          }
          
      }else if (c==KeyEvent.VK_RIGHT){//sağa hareket
           if(uzayGemisi >=720){
               uzayGemisi=720;
                
           }else{
               uzayGemisi+=diruzayGemisi;
               
           }
      }else if(c==KeyEvent.VK_CONTROL){
          atesler.add(new ates (uzayGemisi+12,480));
          harcanan_ates++;
          
          
      }
        



//methodlar bunlar Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    } 

    @Override
    public void keyReleased(KeyEvent e) {
       //methodlar bunlar Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(ates ats:atesler){//atesleri yukarı yönlü ilerletmek için
            ats.setY(ats.getY()- atesdirY  );//her ates edildiğinde tüm ateslerin konumu değişir ,ateşin şimdiki konumundan 1 çıkarttık
        
        }
        
        
        
        topX+=topdirX;
        if(topX >=750){
            topdirX=-topdirX;
        }
        if(topX<=0){
            topdirX=-topdirX;
        }
        repaint();

// methodlar bunlar Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
