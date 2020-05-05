# Analysis strategy for biofilms with a thickness of up to 20 µm

One two-dimensional image is analyzed by calculation of the number of bacteria by differences in the gray intensity of pixels of this image. A pixel with higher gray intensity than the background belonged to a bacterium. The higher the gray intensity of one pixel, the more bacteria lay on top of each other. In more detail, to distinguish the bacteria from the background, an intensity threshold (determined experimentally) was set: pixels with a higher fluorescence intensity than the threshold were defined as belonging to a bacterium (a bacterium ~20 pixel).
To **count bacteria** in biofilms, we assume that the bacteria are uniformly stained and brighter areas are caused by the superposition of several bacteria within this area. The bacterium with the lowest fluorescence intensity that is separated from the background is single-layered. Brighter bacteria are multi-layered. This divides the foreground area into brightness classes and represents a linear relation. The first class comprise the areas with intensity values from the threshold up to the doubled threshold. These areas are covered with single-layered biofilms. Intensity values from the double threshold up to the triple threshold are covered with double-layered biofilms. These areas are then divided by the size of a single bacteria and multiplied by the number of superimposed layers. The pixel brightness was used to differentiate between a bacterium and the background, thus determining the **colonized are**a in the grayscale images.

We the VideoScan platform for all analysis. VideoScan is a fully automatized multispectral imaging plattform that can be used to analyze microbead assays, cell assays or a combination thereof. For details see [A Highly Versatile Microscope Imaging Technology Platform for the Multiplex Real-Time Detection of Biomolecules and Autoimmune Antibodies](https://doi.org/10.1007/10_2011_132). Part of this platform is a cloused source software stack. As supporters of reproducible research and open source software ([DOI:10.21037/jlpm.2019.04.05](http://dx.doi.org/10.21037/jlpm.2019.04.05)), we have reimplemented the algortihm to determine the area, number of bacteria and height of a biofilm by using the presumably most widely used bioimage software [ImageJ](https://imagej.net/Welcome) and its  batteries-included distribution [FIJI](https://fiji.sc/) as a plugin called OBA-Open-Biofilm-Altimeter.

# OBA-Open-Biofilm-Altimeter

![OBA](https://github.com/SilMon/OBA-Open-Biofilm-Altimeter/blob/master/logo.png)

OBA (Open Biofilm Altimeter) is an open source software tool to determine the area, number of bacteria and height of a biofilm. To determine the **thickness of a biofilm**, height profiles are created for the gray scale images captured (and could be converted into 3D representations using the ImageJ 1.5 plugin *Interactive 3D Surface Plot*). 

OBA was developed to work with the VideoScan system. 

OBO is distributed under the [GNU General Public License v3.0](https://github.com/SilMon/OBA-Open-Biofilm-Altimeter/blob/master/LICENSE).

Core functions of OBA are implemented in the Java library `Bacteria_counter.java`.

## How to install

1. Create a new folder named in ImageJ/Plugins

2. Open ImageJ or FIJI

3. Click on Plugins/Install

4. Go to the created Folder on choose BacteriaCounter.java. ImageJ will then open a dialog to ask where the plugin should be installed.

5. Go to the created folder and click Yes. ImageJ/FIJI will warn you that `Bacteria_Counter.java` already exists. Click **Yes** to overwrite

6. Open an image e.g. one of the examples provided in this repository

7. Then go to Plugins/Name of the install folder/Bacteria counter

## Interface

![Image of the interface](https://raw.githubusercontent.com/SilMon/OBA-Open-Biofilm-Altimeter/master/bactCounter.PNG "The Interface of the Plugin")

Upon start, the plugin will open a dialog for analysis. This dialog contains following parameters:

* **Bacteria Height**: The height of one bacterium in units
* **Pixels per bacterium**: The area of one bacterium in pixels
* **Stretch Contrast**: Stretches the values of the image to a range between 0 and 255. This allows visual control of the detected areas, but might falsify the results. Should not be used if the results should be used further. Nevertheless, useful during preview.
* **Preview**: Enables the preview mode of ImageJ for parameter tuning

## Results

After analysis, following results will be logged by the plugin:

* **Approximate Bacteria Count**: The approximate number of the bacteria in the biofilm
* **Max. Height**: The maximal amount of bacteria overlapping each other.
* **Theoretical Area**: The area in pixels the bacteria would need if layed out in a one dimensional layer
