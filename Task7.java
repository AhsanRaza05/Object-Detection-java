
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.*;

public class Task7 {
    
    final int x, y; 
    static int objectColor = 0;

    //objectColor = 0; // Black Colored Objects
    //objectColor = 255; // White Colored Objects

    static int img[][] = null;

    Task7(final int a, final int b){

        x = a;
        y = b;
    }
    public String toString(){

        //System.out.printf();
        return " x = %s, y = %s ".formatted(x, y) ;
    }

    public static boolean isCommon(ArrayList<Task7> p, ArrayList<Task7> q){

        for(Task7 a : p){

            for(Task7 b : q){

                if(a.x == b.x && a.y == b.y){

                    return true;
                }

            }
        }

        return false;

    }
    public static ArrayList <Task7> ND (int i, int j){

        ArrayList<Task7> x = new ArrayList(4);

        // For Finding FOUR DIAGONAL MEMBERS

        // Top Left
        
        if((i - 1) > -1 && (j + 1) < img[i].length && img[i - 1][j + 1] == objectColor && isCommon(N4(i, j) , N4(i-1, j+1)) == false){

            x.add(new Task7(i - 1, j + 1));
        }

        // Top Right
        if((i + 1) < img.length && (j + 1) < img[i].length && img[i + 1][j + 1] == objectColor && isCommon(N4(i, j) , N4(i+1, j+1)) == false){
                                    
            x.add(new Task7(i + 1, j + 1));
        }

        // Bottom Left
        if((i - 1) > -1 && (j - 1) > -1 && img[i - 1][j - 1] == objectColor && isCommon(N4(i, j) , N4(i-1, j-1)) == false){

            x.add(new Task7(i - 1, j - 1));                        
        }

        // Bottom Right
        if((i + 1) < img.length && (j - 1) > -1 && img[i + 1][j - 1] == objectColor && isCommon(N4(i, j) , N4(i+1, j-1)) == false){

            x.add(new Task7(i + 1, j - 1));
        }
        

        return x;
    }

    public static ArrayList<Task7> N4(int i, int j){

        
        ArrayList<Task7> x = new ArrayList(4);

        // For Finding FOUR DIRECT NEIGHBOURS

        // LEFT 
        if((j - 1) > -1 && img[i][j - 1] == objectColor){

            x.add(new Task7(i, j - 1));
            //hasNeighbours = true;
        }

        //RIGHT
        if((j + 1) < img[i].length && img[i][j + 1] == objectColor){

            x.add(new Task7(i, j + 1));
            //hasNeighbours = true;
        }

        // TOP
        if((i - 1) > -1 && img[i - 1][j] == objectColor){

            x.add(new Task7(i - 1, j));
            //hasNeighbours = true;
        }

        // BOTTOM
        if((i + 1) < img.length && img[i + 1][j] == objectColor){

            x.add(new Task7(i + 1, j));
            //hasNeighbours = true;
        }

        return x;

    }
    
    public static void main(String[] args) {
        // System.out.print();

        BufferedImage image;
        int width;
        int height;
        
        try {
            //File input = new File("C:\\Users\\AHSAN\\Desktop\\abc.bmp"); // Has 15 Objects
            System.out.println("HI AHSA");
            //File input = new File("C:\\Users\\AHSAN\\Desktop\\filled.bmp");
            //File input = new File("C:\\Users\\AHSAN\\Desktop\\Test.bmp");

            File input = new File("C:\\Users\\AHSAN\\Desktop\\Java Image Testing\\Second Test\\Test2.bmp");
            //File input = new File("C:\\Users\\AHSAN\\Desktop\\Java Image Testing\\Second Test\\Test3.bmp");

            image = ImageIO.read(input);
            width = image.getWidth();
            height = image.getHeight();

            System.out.println("Height = " + height);
            System.out.println("Width = " + width);
            img = new int[height][width];
            
            int count = 0;
            for(int i=0; i<height; i++) {
            
               for(int j=0; j<width; j++) {
               
                    count++;
                    Color c = new Color(image.getRGB(j, i));
                    img[i][j] = c.getBlue();
                }
            }
            System.out.println("Count = "+ count);
   
        } catch (Exception e) {

            e.printStackTrace();

        }
        
        int o = 0;
        boolean hasNeighbours = false;
        boolean isPreviouseObjectPart = false;
        boolean isNothingCommon = false;
        
        ArrayList < ArrayList<Task7> > objects = new ArrayList(10);
        
        ArrayList <Task7> x;

        System.out.println("Height = " + img.length);
        System.out.println("Width = " + img[0].length);

        System.out.println("img = "+img[124][114]);

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
   
                    x.add(new Task7(i,j));

                    if(!hasNeighbours){ // If Pixel has No Neighbours then consider it as new object directly.
                        o++;
                        objects.add(x);
                        System.out.println("\nHI 1");
                    }

                    else{  

                        //objects.add(x);
                        // If it has Neigbours then check that whether that group of pixel is part of previous object or not.
                        isPreviouseObjectPart = false;

                        isNothingCommon = false;

                        loop2 : for(ArrayList <Task7> l : objects){

                                    for(Task7 t : l){

                                        for(Task7 u : x){

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

        System.out.println("Total " + total);
        System.out.println("No of 1s: " + cv);

        int f = 0;

            // Complexity = O(n^4)
        System.out.println("\n No of Objects:" + objects.size());

        // Second Last Row
        for(int ii = objects.size() - 2 ; ii >= 0 ; ii--){
            // Last Row   
            for(int aa = objects.size() - 1; aa > ii; aa--) {
                
                //aa = ii + 1; // 

                l: for(int jj = 0; jj < objects.get(ii).size(); jj++){
                   
                    for(int kk = 0; kk < objects.get(aa).size(); kk++){

                        if((objects.get(ii).get(jj).x == objects.get(aa).get(kk).x ) && (objects.get(ii).get(jj).y == objects.get(aa).get(kk).y )){
                    
                            System.out.print("jojo ");
                            objects.get(ii).addAll(objects.get(aa));
                            objects.remove(aa);
                            break l;
                        }    
                    }
                }
            }    
        }

        ArrayList < ArrayList<Task7> > objects2 = new ArrayList(10);
        ArrayList <Task7> x2  = new ArrayList<Task7>();
        boolean isExists = false;

        // Remove the Repetation of coordinates from each Object 
            // Complexity = O(n^3)
        for(int i = 0; i < objects.size(); i++){
            
            x2  = new ArrayList<Task7>();
            
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
        System.out.println("\n Final List \n");
        Integer c = 1, z = 0 ;
        for(ArrayList <Task7> l : objects2){

            System.out.printf("\nObject # %s consists of %s Pixels \n", c , l.size());
            z += l.size();

            for(Task7 t : l){

               System.out.printf("(%s, %s), ",t.x,t.y);
            }
            System.out.println();
            c++;
        }

        int y = 0;
        for(ArrayList <Task7> l : objects){

            y += l.size();

        }
        System.out.println("\nNo. of Objects: " + objects2.size());

        System.out.println("\nTotal Pixel: " + z);
        System.out.println("\nTotal Pixel: " + y);

        objects = null;

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