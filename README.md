# OBA-Open-Biofilm-Altimeter

OBA is an open source software tool to determine the area, number of bacteria and height of a biofilm.

OBA was developed to work with the VideoScan system. VideoScan is a fully automatized multispectral imaging plattform that can be used to analyze microbead assays, cell assays or a combination thereof. For details see [A Highly Versatile Microscope Imaging Technology Platform for the Multiplex Real-Time Detection of Biomolecules and Autoimmune Antibodies ](https://doi.org/10.1007/10_2011_132).

Part of this platform is a cloused source software stack. As supporters of reproducible research and open source software ([DOI:10.21037/jlpm.2019.04.05](http://dx.doi.org/10.21037/jlpm.2019.04.05)), we have reimplemented the algortihm to determine the area, number of bacteria and height of a biofilm by using the presumably most widely used bioimage software [ImageJ](https://imagej.net/Welcome) and its  batteries-included distribution [FIJI](https://fiji.sc/).

OBO is distributed under the GNU General Public License v3.0.

## How to install

1. Create a new folder named in ImageJ/Plugins

2. Open ImageJ or FIJI

3. Click on Plugins/Install

4. Go to the created Folder on choose BacteriaCounter.java. ImageJ will then open a dialog to ask where the plugin should be installed.

5. Go to the created folder and click Yes. ImageJ/FIJI will warn you that BacteriaCounter.java already exists. Click **Yes** to overwrite

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
