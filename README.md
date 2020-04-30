# OBA-Open-Biofilm-Altimeter
OBA is an open source software tool to determine the are, number of bacteria and height of a biofilm.

## How to install
* Create a new folder named in ImageJ/Plugins 
* Open ImageJ
* Click on Plugins/Install
* Go to the created Folder on choose BacteriaCounter.java. ImageJ will then open a dialog to know where the plugin should be installed.
* Go to the created folder and click Yes. ImageJ will warn you that BacteriaCounter.java already exists. Click Yes to overwrite
* Open an image e.g. one of the examples provided in this repository
* Then go to Plugins/Name of the install folder/Bacteria counter

## Interface and Results
![Image of the interface](https://raw.githubusercontent.com/SilMon/OBA-Open-Biofilm-Altimeter/master/bactCounter.PNG "The Interface of the Plugin")

Upon start, the plugin will open a dialog for analysis. This dialog contains following parameters:
* **Bacteria Height**: The height of one bacterium in units
* **Pixels per bacterium**: The area of one bacterium in pixels
* **Stretch Contrast**: Stretches the values of the image to a range between 0 and 255. This enables visual control over the detected areas, but might falsify the results. Should not be used if the results should be used further. Nethertheless, useful during preview.
* **Preview**: Enables the preview mode of ImageJ for parameter tuning
