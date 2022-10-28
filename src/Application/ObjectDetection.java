package Application;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.test.jfilepicker.JFilePicker;
import com.test.jfilepicker.JFilePickerFrame;

import java.util.*;

public class ObjectDetection {
    
    final int x, y; 
    static int objectColor;

    //objectColor = 0; // Black Colored Objects
    //objectColor = 255; // White Colored Objects

    static int img[][] = null;

    ObjectDetection(final int a, final int b){

        x = a;
        y = b;
    }
    public String toString(){

        //System.out.printf();
        return " x = %s, y = %s ".formatted(x, y) ;
    }
    

    public static boolean isCommon(ArrayList<ObjectDetection> p, ArrayList<ObjectDetection> q){

        for(ObjectDetection a : p){

            for(ObjectDetection b : q){

                if(a.x == b.x && a.y == b.y){

                    return true;
                }

            }
        }

        return false;

    }
    public static ArrayList <ObjectDetection> ND (int i, int j){

        ArrayList<ObjectDetection> x = new ArrayList(4);

        // For Finding FOUR DIAGONAL MEMBERS

        // Top Left
        
        if((i - 1) > -1 && (j + 1) < img[i].length && img[i - 1][j + 1] == objectColor && isCommon(N4(i, j) , N4(i-1, j+1)) == false){

            x.add(new ObjectDetection(i - 1, j + 1));
        }

        // Top Right
        if((i + 1) < img.length && (j + 1) < img[i].length && img[i + 1][j + 1] == objectColor && isCommon(N4(i, j) , N4(i+1, j+1)) == false){
                                    
            x.add(new ObjectDetection(i + 1, j + 1));
        }

        // Bottom Left
        if((i - 1) > -1 && (j - 1) > -1 && img[i - 1][j - 1] == objectColor && isCommon(N4(i, j) , N4(i-1, j-1)) == false){

            x.add(new ObjectDetection(i - 1, j - 1));                        
        }

        // Bottom Right
        if((i + 1) < img.length && (j - 1) > -1 && img[i + 1][j - 1] == objectColor && isCommon(N4(i, j) , N4(i+1, j-1)) == false){

            x.add(new ObjectDetection(i + 1, j - 1));
        }
        

        return x;
    }

    public static ArrayList<ObjectDetection> N4(int i, int j){

        
        ArrayList<ObjectDetection> x = new ArrayList(4);

        // For Finding FOUR DIRECT NEIGHBOURS

        // LEFT 
        if((j - 1) > -1 && img[i][j - 1] == objectColor){

            x.add(new ObjectDetection(i, j - 1));
            //hasNeighbours = true;
        }

        //RIGHT
        if((j + 1) < img[i].length && img[i][j + 1] == objectColor){

            x.add(new ObjectDetection(i, j + 1));
            //hasNeighbours = true;
        }

        // TOP
        if((i - 1) > -1 && img[i - 1][j] == objectColor){

            x.add(new ObjectDetection(i - 1, j));
            //hasNeighbours = true;
        }

        // BOTTOM
        if((i + 1) < img.length && img[i + 1][j] == objectColor){

            x.add(new ObjectDetection(i + 1, j));
            //hasNeighbours = true;
        }

        return x;

    }
    
    public static void main(String[] args) {
        // System.out.print();

    	
        JFilePickerFrame.getFilePath();
    }
    
    public static String result(String path, String oColor) {
    	
    	//objectColor = 0; // Black Colored Objects
	    //objectColor = 255; // White Colored Objects
    	objectColor = (int)((oColor.equalsIgnoreCase("1"))? 0 : 255);
    	
    	BufferedImage image;
        int width;
        int height;
       
        String result = "";
        
        try {
            //File input = new File("C:\\Users\\AHSAN\\Desktop\\abc.bmp"); // Has 15 Objects
            //System.out.println("HI AHSA");
            
//            JFilePickerFrame t = new JFilePickerFrame();
//            t.setVisible(true);
            
            File input = new File(path);
            //System.out.println(input);
            //File input = new File("filled.bmp");
//            File input = new File("src\\com\\images\\Fruits.bmp");
            //File input = new File("C:\\Users\\AHSAN\\Desktop\\Test.bmp");

            // ? File input = new File("C:\\Users\\AHSAN\\Desktop\\Java Image Testing\\Second Test\\Test2.bmp");
            //File input = new File("C:\\Users\\AHSAN\\Desktop\\Java Image Testing\\Second Test\\Test3.bmp");

            image = ImageIO.read(input);
            width = image.getWidth();
            height = image.getHeight();

            result += "Height = " + height + "\n";
            result += "Width = " + width + "\n";
            
//            System.out.println("Height = " + height);
//            System.out.println("Width = " + width);
            img = new int[height][width];
            
            int count = 0;
            for(int i=0; i<height; i++) {
            
               for(int j=0; j<width; j++) {
               
                    count++;
                    Color c = new Color(image.getRGB(j, i));
                    img[i][j] = c.getBlue();
                }
            }
            	
            result += "Count = "+ count + "\n";
//            System.out.println("Count = "+ count);
   
        } catch (Exception e) {

            e.printStackTrace();

        }
        
        int o = 0;
        boolean hasNeighbours = false;
        boolean isPreviouseObjectPart = false;
        boolean isNothingCommon = false;
        
        ArrayList < ArrayList<ObjectDetection> > objects = new ArrayList(10);
        
        ArrayList <ObjectDetection> x;

        result += "Height = " + img.length + "\n";
        result += "Width = " + img[0].length + "\n"; 
//        System.out.println("Height = " + img.length);
//        System.out.println("Width = " + img[0].length);

        //System.out.println("img = "+img[124][114]);

        int cv = 0;
        int total = 0;

        for(int i = 0; i < img.length; i++){

            for(int j = 0; j < img[i].length; j++){
                isPreviouseObjectPart = false;
                total++;
                x = new ArrayList(10);
                hasNeighbours = false;
                
                if(img[i][j] == objectColor){

                    cv++;

                    x.addAll(N4(i,j));
                    x.addAll(ND(i,j));

                    hasNeighbours = !x.isEmpty();
   
                    x.add(new ObjectDetection(i,j));

                    if(!hasNeighbours){ // If Pixel has No Neighbours then consider it as new object directly.
                        o++;
                        objects.add(x);
                        //System.out.println("\nHI 1");
                    }

                    else{  

                        //objects.add(x);
                        // If it has Neigbours then check that whether that group of pixel is part of previous object or not.
                        isPreviouseObjectPart = false;

                        isNothingCommon = false;

                        loop2 : for(ArrayList <ObjectDetection> l : objects){

                                    for(ObjectDetection t : l){

                                        for(ObjectDetection u : x){

                                            //if((Math.abs(t.x - u.x) == 1 && t.y == u.y) || (Math.abs(t.y - u.y) == 1 && t.x == u.x) || (t.x == u.x && t.y == u.y)){
                                            if((Math.abs(t.x - u.x) == 1 && t.y == u.y) || (Math.abs(t.y - u.y) == 1 && t.x == u.x) || (t.x == u.x && t.y == u.y) || (Math.abs(t.x - u.x) == 1 && Math.abs(t.y - u.y) == 1)){
                                                
                                                x.remove(u);
                                                //x.removeAll(c)
                                                //System.out.println(x.remove(u));
                                                l.addAll(x);
                                                isPreviouseObjectPart = true;
                                                break loop2;

                                            }
                                        }
                                    }
                                }


                // loop2 : for(int secondLastRow = objects.size() - 2, lastRow = objects.size() - 1; (secondLastRow >= 0 && lastRow > secondLastRow) ; lastRow--, secondLastRow--){

                //             for(Task7 t : objects.get(secondLastRow)){

                //                 for(Task7 u : objects.get(lastRow)){

                //                     //if((Math.abs(t.x - u.x) == 1 && t.y == u.y) || (Math.abs(t.y - u.y) == 1 && t.x == u.x) || (t.x == u.x && t.y == u.y)){
                //                     if((Math.abs(t.x - u.x) == 1 && t.y == u.y) || (Math.abs(t.y - u.y) == 1 && t.x == u.x) || (t.x == u.x && t.y == u.y) || (Math.abs(t.x - u.x) == 1 && Math.abs(t.y - u.y) == 1)){
                                        
                //                         //objects.get(lastRow).remove(u);
                //                         //x.removeAll(c)
                //                         //System.out.println(x.remove(u));
                //                         objects.get(secondLastRow).addAll(objects.get(lastRow));

                //                         objects.remove(lastRow);
                //                         //System.out.println(objects.remove(lastRow));
                //                         isPreviouseObjectPart = true;
                //                         break loop2;

                //                     }
                //                 }
                //             }
                //         }

                        if(!isPreviouseObjectPart){
                            o++;
                            isPreviouseObjectPart = false;
                            objects.add(x);
                        }

                    }
                    
                }
            }
        }

        result += "Total " + total + "\n";
        result += "No. of 1s: " + cv + "\n";
//        System.out.println("Total " + total);
//        System.out.println("No of 1s: " + cv);

        int f = 0;

            // Complexity = O(n^4)
        result += "\n No of Objects:" + objects.size() + "\n";	
//        System.out.println("\n No of Objects:" + objects.size());

        // Second Last Row
        for(int ii = objects.size() - 2 ; ii >= 0 ; ii--){
            // Last Row   
            for(int aa = objects.size() - 1; aa > ii; aa--) {
                
                //aa = ii + 1; // 

                l: for(int jj = 0; jj < objects.get(ii).size(); jj++){
                   
                    for(int kk = 0; kk < objects.get(aa).size(); kk++){

                        if((objects.get(ii).get(jj).x == objects.get(aa).get(kk).x ) && (objects.get(ii).get(jj).y == objects.get(aa).get(kk).y )){
                    
                        	
                        	result += "jojo ";
//                            System.out.print("jojo ");
                            objects.get(ii).addAll(objects.get(aa));
                            objects.remove(aa);
                            break l;
                        }    
                    }
                }
            }    
        }

        ArrayList < ArrayList<ObjectDetection> > objects2 = new ArrayList(10);
        ArrayList <ObjectDetection> x2  = new ArrayList<ObjectDetection>();
        boolean isExists = false;

        // Remove the Repetation of coordinates from each Object 
            // Complexity = O(n^3)
        for(int i = 0; i < objects.size(); i++){
            
            x2  = new ArrayList<ObjectDetection>();
            
            lop: for(int j = 0; j < objects.get(i).size(); j++){
                
                isExists = false;
                
                for(int k = 0; k < x2.size(); k++){

                    if((x2.get(k).x == objects.get(i).get(j).x && x2.get(k).y == objects.get(i).get(j).y)){
                        continue lop;
                    }

                }

                //if(!isExists){

                    x2.add(objects.get(i).get(j));
                //}   
            }
            objects2.add(x2);
        }
        x2 = null;

        // Display Final List of All Objects along with the coordinates
            // Complexity = O(n^2)
       // result += "\n\n\n*************** Final List ***************\n\n";
//        System.out.println("\n Final List \n");
        Integer c = 1, z = 0 ;
        
        result = "\nNo. of Objects: " + objects2.size() + "\n";
        
        for(ArrayList <ObjectDetection> l : objects2){

        	result += "\nObject # %s consists of %s Pixels \n".formatted(c , l.size());
//            System.out.printf("\nObject # %s consists of %s Pixels \n", c , l.size());
            z += l.size();

            for(ObjectDetection t : l){

            	result += "(%s, %s), ".formatted(t.x,t.y);
//               System.out.printf("(%s, %s), ",t.x,t.y);
            }
            result += "\n";
//            System.out.println();
            c++;
        }

        int y = 0;
        for(ArrayList <ObjectDetection> l : objects){

            y += l.size();

        }
        
//        result += "\nNo. of Objects: " + objects2.size() + "\n"; 
        
//        System.out.println("\nNo. of Objects: " + objects2.size());
        
        result += "\nTotal Pixel: " + z + "\n";
//        result += "\nTotal Pixel: " + y + "\n";
        
//        System.out.println("\nTotal Pixel: " + z);
//        System.out.println("\nTotal Pixel: " + y);

        objects = null;
        
        return result;

        // LinkedHashSet hs = new LinkedHashSet<>()

        // hs.add()
        // hs.addAll(c)

    }
}

// 2:6 2:15
// 2:21 31
// 3:39 3:57
//3:58 
// 11:12
//11:23 11:37
// 11:40 11:
// 2:36 2:44
// 2:48 2:50
//2:57 3:00 
// 3:20 3:22
//3:29 3:32
// 12:15

// 8:23 - 53
// 8:41 -52

// 11:26 - 11 :


// 11:44 - 11 : 57