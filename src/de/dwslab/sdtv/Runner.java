package de.dwslab.sdtv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The main class that runs SDType&Validate
 * @author Heiko
 */
public class Runner {
	
		public static void main(String[] args) throws IOException {

	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	     //   System.out.print("Enter Properties File: ");
	        if (args.length != 3) System.out.println("Please Enter the Reguired: SDtype.jar mappingbased_properties_path DBPedia_Types_Only_path disambiguationFile_path"); 

	        String propFile = args[0];
	        //	        String propFile = br.readLine();
	      //  System.out.print("Enter Type File: ");
	        String typeFile = args[1];
	        //	        String typeFile = br.readLine();

	       // System.out.print("Enter Disambiguation File: ");
	        String disambiguationFile = args[2];
	        //	        String disambiguationFile = br.readLine();

		LoadFiles loadFiles = new LoadFiles();
		ComputeBaseStatistics computeBaseStatistics = new ComputeBaseStatistics();
		MaterializeSDTypes materializeSDTypes = new MaterializeSDTypes();
		MaterializeSDValidate materializeSDValidate = new MaterializeSDValidate();
		try {
			//loadFiles.loadProperties("./mappingbased_properties_ittop.ttl");
			//loadFiles.loadProperties(propFile.replace("\"", ""));
			loadFiles.loadProperties(propFile);
			loadFiles.createPropertyIndices();
			//loadFiles.loadTypes("./instance_types_ittop.ttl");
			loadFiles.loadTypes(typeFile);
			loadFiles.createTypeIndices();
			//loadFiles.loadDisambiguations("./disambiguations_unredirected_ittop.ttl");
			loadFiles.loadDisambiguations(disambiguationFile);
			loadFiles.createDisambiguationIndices();
			computeBaseStatistics.computeGlobalTypeDistribution();
			computeBaseStatistics.computePerPredicateDistribution();
			materializeSDTypes.computeSDTypes();
			String path = System.getProperty("user.dir");
			materializeSDTypes.writeTypeFile(path+"/sdtypes.ttl", 0.4f);
			materializeSDValidate.computeSDValidateScores();
			materializeSDValidate.writeWrongStatementsFile(path+"/sdinvalid.ttl", 0.15f);
			if(1<0)
				throw new IOException();
		} catch (IOException e) {
			System.out.println("Error loading input files");
			e.printStackTrace();
		}
	}
}
