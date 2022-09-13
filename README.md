# Object Detection java

## Table of Contents
* <a href = "#Overview_Head" > Overview </a> 

* <a href = "#Installation" > How to Install and Run the Project </a> 

* <a href = "#FileContent" > File Contents </a> 

* <a href = "#Int" > Introduction </a> 

  * <a href = "#Neighbour" > Neighbourhood </a>
    * <a href = "#4neighbours" > 4-Neighbours </a> 
    * <a href = "#Dneighbours" >  D-Neighbours </a>
    * <a href = "#8neighbours" > 8-Neighbours </a>
          
  * <a href = "#Connectivity" > Connectivity </a>
    * <a href = "#4connectivity" > 4-Connectivity </a> 
    * <a href = "#8connectivity" >   8-Connectivity </a>
    * <a href = "#Mconnectivity" >   M-Connectivity </a>
   
* <a href = "#Scr" > Program Screenshots </a>  
         
## <div id = "Overview_Head"> Overview </div>       

It is a Java based GUI desktop application which “detects the objects in a binary image using the concept of Neighborhood and connectivity”. More specifically it implements the concept of "M-Connectivity" of Digital Image Processing.

## <div id = "Installation"> How to Install and Run the Project </div>
#### <div> Method # 1: Download 'Executable (exe)' file from 'PORTABLE APPLICATION' Folder (Easy & Portable) </div>
                No need to install anything but it is for WINDOWS OS  
                
#### <div> Method # 2: Download and Load Project in Eclipse IDE to Run it </div>
                Eclipse IDE & JVM will be required.

## <div id = "FileContent"> File Contents  </div>

- `src/Application/ObjectDetection.java` includes the implementation of neighbourhood & connectivity concept (main logic).  
- `src/com/test/jfilepicker package` includes source files for GUI components.
- `src/com/test/jfilepicker package/images package` includes the binary images.

## <div id = "Int"> Introduction </div>


### <div id = "Neighbour"> 1-Neighborhood </div>

A Digital Image comprises of a 2D matrix, and every element is called as Pixel.
The Image is denoted by f(x,y) and any pixel can be denoted by small letter, say p, q …
Every Pixel has some Neighbors located around it.

Different types of neighbors include:
4 Neighbors
D Neighbors
8 Neighbors

#### <div id = "4neighbours"> 4-Neighbors </div>

A pixel ’p’ located at coordinate value (x,y) has 4 neighbors, which are adjacent to ‘p’.
The coordinate values can be determined as:
(x+1,y)
(x-1,y)
(x,y+1)
(x,y-1)
by N4(p) 4 Neighbors are denoted

 <p align = 'center'>
 <img src = "ScreenShots/Neighbors/4Neighbors.png"  alt = "Image of 4-Neighbors" >
</p>
 
 <div align = "center">
  <figcaption align = "center"> 4-Neighbors  </figcaption>
 </div>

#### <div id = "Dneighbours"> (ii) D-Neighbors </div>

A pixel ’p’ located at coordinate value (x,y) has D neighbors, which are located in Diagonal of  ‘p’.
The coordinate values can be determined as:
(x+1,y+1)
(x+1,y-1)
(x-1,y+1)
(x-1,y-1)
8 Neighbors are denoted by N8(p)

 <p align = 'center'>
 <img src = "ScreenShots/Neighbors/DNeighbors.png"  alt = "Image of D-Neighbors" >
</p>
 
 <div align = "center">
  <figcaption align = "center"> D-Neighbors  </figcaption>
 </div>

#### <div id = "8neighbours"> (ii) 8-Neighbors </div>

A pixel ’p’ located at coordinate value (x,y) has 8 neighbors, which are combination of 4 and D Neighbors.
8 Neighbors are denoted by N8(p)

(x+1,y)
(x-1,y)
(x,y+1)
(x,y-1)
(x+1,y+1)
(x+1,y-1)
(x-1,y+1)
(x-1,y-1)

<p align = 'center'>
 <img src = "ScreenShots/Neighbors/8Neighbors.png"  alt = "Image of 8-Neighbors" >
</p>
 
 <div align = "center">
  <figcaption align = "center"> 8-Neighbors  </figcaption>
 </div>

### <div id = "Connectivity"> Connectivity </div>

Connectivity between pixels is an important concept used in establishing boundaries of objects and components of regions in an image. 

As we can identify different objects in Image depending upon the Intensity values of Pixels.

So all Pixels comprising an object would have Same or Close values.

To establish whether two pixels are connected, it must be determined 

If they are adjacent in some sense (say, if they are 4-neighbors) .
and
If their grey levels satisfy a specified criterion of similarity.

The specified Criteria (Intensity Range ) can be defined by defining a set ‘V’ at start.
For V={2}
Only those Pixel can be connected which have value =2
For V={1,2}
Only those Pixel can be connected which have value =1 or 2.
The specified Criteria (Intensity Range ) can be defined by defining a set ‘V’ at start.

#### <div id = "4connectivity"> (i) 4-Connectivity: </div>

<p align = 'center'>
 <img src = "ScreenShots/Connectivity/4Connectivity.png"  alt = "Image of 4-Connectivity" >
</p>
 
 <div align = "center">
  <figcaption align = "center"> 4-Connectivity  </figcaption>
 </div>

Two pixels p and q with values from V are 4- connected if q is in the set N4 (p).

#### <div id = "8connectivity"> (ii) 8-Connectivity: </div>

<p align = 'center'>
 <img src = "ScreenShots/Connectivity/8Connectivity.png"  alt = "Image of 8-Connectivity" >
</p>
 
 <div align = "center">
  <figcaption align = "center"> 8-Connectivity  </figcaption>
 </div>


Two pixels p and q with values from V are 8- connected if q is in the set N8 (p). 
      
#### <div id = "Mconnectivity"> (ii) M-Connectivity: </div>

Two pixels p and q with values from V are m-connected if:
       q is in N4 (p), 	OR
       q is in ND (p) AND the set  N4 (p) ∩  N4 			(p) is empty.

<p align = 'center'>
 <img src = "ScreenShots/Connectivity/MConnectivity.png"  alt = "Image of M-Connectivity" >
</p>
 
 <div align = "center">
  <figcaption align = "center"> M-Connectivity  </figcaption>
 </div>


## <div id = "Scr">  Program Screenshots </div>

<p align = 'center'>
 <img src = "ScreenShots/UI/FirstWindow.png"  alt = "1st Window" >
</p>
 
 <div align = "center">
  <figcaption align = "center"> Fig-A: Browsing image </figcaption>
 </div> </br>
 
 <p align = 'center'>
 <img src = "ScreenShots/UI/SecondWindow.png"  alt = "2nd Window" >
</p>
 
 <div align = "center">
  <figcaption align = "center"> Fig-B: Selecting an image </figcaption>
 </div> </br>
 
 <p align = 'center'>
 <img src = "ScreenShots/UI/ThirdWindow.png"  alt = "3rd Window" >
</p>
 
 <div align = "center">
  <figcaption align = "center"> Fig-C: Specifying color of object  </figcaption>
 </div> </br>

 <p align = 'center'>
 <img src = "ScreenShots/UI/FinalOutputWindow.png"  alt = "4th Window" >
</p>
 
 <div align = "center">
  <figcaption align = "center"> Fig-D: Result  </figcaption>
 </div>
 
 
