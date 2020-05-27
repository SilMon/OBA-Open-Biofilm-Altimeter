import ij.ImagePlus;
import ij.process.AutoThresholder;
import ij.process.AutoThresholder.Method;
import ij.process.ColorProcessor;
import ij.process.ByteProcessor;
import ij.gui.DialogListener;
import ij.gui.GenericDialog;
import ij.plugin.filter.ExtendedPlugInFilter;
import ij.plugin.filter.PlugInFilterRunner;
import ij.process.ImageProcessor;
import ij.plugin.filter.GaussianBlur;
import ij.plugin.filter.RankFilters;
import ij.IJ;
import java.awt.AWTEvent;
import java.lang.Math;
import java.util.Arrays;


public class Bacteria_counter implements ExtendedPlugInFilter, DialogListener{
	private final int FLAGS = DOES_RGB | PARALLELIZE_STACKS;
	private boolean preview = true;
	private double bacteriaHeight, pixelsPerBacterium;
	private boolean stretchContrast;

	public int setup(String args, ImagePlus im){
		// Define variables
		bacteriaHeight = 1;
		double bacteriumArea = 1.5;
		int binning = 2;
		double pixelSize20 = 0.172;
		double miM2Pixels = 1.0d / (binning * binning * pixelSize20 * pixelSize20);
		pixelsPerBacterium = bacteriumArea * miM2Pixels;
		stretchContrast = true;
		return FLAGS;
	}
	
	public void run(ImageProcessor ip){
		// Cast ip to ColorProcessor and get green channel
		ColorProcessor cp = (ColorProcessor) ip;
		ByteProcessor green = cp.getChannel(2, null);
		// Get threshold value
		AutoThresholder thresh = new AutoThresholder();
		double threshold = thresh.getThreshold(Method.Li, green.getHistogram(256));
		double theoArea = 0;
		double maxHeight = 0;
		// Minimal height is 0
		int minPix = 0;
		int maxPix = 0;
		// Change value of pixels according to threshold
		for (int v = 0; v < cp.getHeight(); v++){
			for (int u = 0; u < cp.getWidth(); u++){
				// Get pixel value
				int value = green.get(u, v);
				if (value > threshold){
					theoArea += value / threshold;
					double val = value / (threshold * bacteriaHeight);
					// Check if val is larger than the maxHeight
					maxHeight = (maxHeight < val) ? val : maxHeight;
					// Construct a new RGB pixel
					int pixVal = (int)Math.round(val);
					maxPix = (maxPix < pixVal) ? pixVal : maxPix;
					int newPix = pixVal << 16 | pixVal << 8 | pixVal;
					ip.set(u, v, newPix);
				}else{
					ip.set(u, v, 0);
				}
			}
		}
		if (stretchContrast){
			// Calculate ratio of 255 to min max distance
			double ratio = 255.0 / (maxPix - minPix);
			// Create lookup table
			int[] lut = new int[256];
			for(int a = 0; a < 256; a++){
				// Calculate the new intensity according to ratio
				int b = (int)(a * ratio);
				// Create new pixel value for color image
				lut[a] = b << 16 | b << 8 | b;
			}
			for (int v = 0; v < cp.getHeight(); v++){
				for (int u = 0; u < cp.getWidth(); u++){
					// Get intensity at v, u
					int pix = (ip.get(u, v) >> 16) & 0xFF;
					// Stretch contrast according to lut
					ip.set(u, v, lut[pix]);
				}
			}
		}
		// Log results
		IJ.log(String.format("Approximate Bacteria Number: %d", Math.round(theoArea / pixelsPerBacterium)));
		IJ.log(String.format("Max. Height: %f", maxHeight));
		IJ.log(String.format("Theoretical Area: %f", theoArea));
		IJ.log("");
	}
	
	public int showDialog(ImagePlus imp, String Command, PlugInFilterRunner pfr){
		// Create interface dialog
		GenericDialog gd = new GenericDialog("BacteriaCounter");
		gd.addMessage("Set up the analysis parameters:");
		gd.addNumericField("Bacteria Height", bacteriaHeight, 4, 6, "units");
		gd.addNumericField("Pixels per Bacterium", pixelsPerBacterium, 4, 6, "pixels");
		gd.addCheckbox("Stretch Contrast", true);
		gd.addPreviewCheckbox(pfr, "Preview");
		gd.addDialogListener(this);
		gd.showDialog();
		
		if(gd.wasCanceled() ||!dialogItemChanged(gd, null)){
			return DONE;
		}
		return IJ.setupDialog(imp, FLAGS);
	}
	
	public boolean dialogItemChanged(GenericDialog gd, AWTEvent e){
		// React to User Input
		bacteriaHeight = gd.getNextNumber();
		pixelsPerBacterium = gd.getNextNumber();
		stretchContrast = gd.getNextBoolean();
		return true;
	}
	
	public void setNPasses(int nPasses){
		//Has to be implemented for ExtendedPlugInFilter
	}
	
}