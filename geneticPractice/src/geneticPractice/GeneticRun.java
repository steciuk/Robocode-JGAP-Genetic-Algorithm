package geneticPractice;


import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Vector;

import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.FitnessFunction;
import org.jgap.Gene;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.InvalidConfigurationException;
import org.jgap.Population;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.DoubleGene;

import robocode.control.*;

import robocode.control.events.*; 

public class GeneticRun {
	public static void main(String[] args)
	{
		Configuration conf = new DefaultConfiguration();
		FitnessFunction myFunc = new TrackerFitnessFunction();
		
		try {
			conf.setFitnessFunction( myFunc );
		} catch (InvalidConfigurationException e) {
			System.out.println("Invalid configuration!");
		}
		
		//SETTINGS
		int genotypeSize = 30;
		int numEvolutions = 50;
		

		//GENE VALUES
		double disMin 		= 120;
		double disMax 		= 180;
		double chSpeedMin 	= 0.05;
		double chSpeedMax 	= 0.2;
		double speedRangMin = 6; 
		double speedRangMax	= 20;
		double minSpeedMin 	= 6;
		double minSpeedMax 	= 20;
	
		
		try(PrintWriter pw = new PrintWriter("data.txt"); PrintWriter pw2  = new PrintWriter("fittest.txt")){
			
			Gene[] roboGenes = new Gene[4];
			roboGenes[0] = new DoubleGene( conf, disMin, disMax ); //close distance
			roboGenes[1] = new DoubleGene( conf, chSpeedMin, chSpeedMax ); //change speed prob
			roboGenes[2] = new DoubleGene( conf, speedRangMin, speedRangMax ); //speed ranges
			roboGenes[3] = new DoubleGene( conf, minSpeedMin, minSpeedMax ); //min robot speed
					
			
			Chromosome sampleChromosome = new Chromosome( conf, roboGenes );	
			
			conf.setSampleChromosome( sampleChromosome );
			conf.setPopulationSize( genotypeSize );
			
//			Population initPopulation = new Population(conf, sampleChromosome);
//			Genotype population = new Genotype( conf, initPopulation );		
			
			Genotype population = Genotype.randomInitialGenotype( conf );		
			
			Chromosome bestSolutionSoFar = null;

		    
			for( int i = 0; i < numEvolutions; i++ )
			{
				System.out.println("GEN: " + i);
				population.evolve();
				
			    bestSolutionSoFar = (Chromosome) population.getFittestChromosome();
			    printChromosome(bestSolutionSoFar, pw, i);
			    printFittest(bestSolutionSoFar, pw2);
			}
			
			printForRobot(bestSolutionSoFar);
				
			
					
		}catch(Exception e)
		{
			e.printStackTrace();
		}

	}
	
	private static void printForRobot(Chromosome a_subject) throws FileNotFoundException
	{
		try(PrintWriter pw = new PrintWriter("E:\\CODING\\eclipse-workspace\\geneticRobots\\bin\\fnl\\SuperTracker.data\\roboFile.dat")){
			printGenotypeForRobot(a_subject, pw);			
		}		
	}
	
	private static void printGenotypeForRobot(Chromosome ch, PrintWriter pw)
	{
		pw.println(getGvalueAtPos(ch, 0));
		pw.println(getGvalueAtPos(ch, 1));
		pw.println(getGvalueAtPos(ch, 2));
		pw.println(getGvalueAtPos(ch, 3));
	}
	
	public static void printChromosome(Chromosome ch, PrintWriter pw, int generation)
	{
		pw.println(generation + ": " + ch.getFitnessValue() + 
				" [" + getGvalueAtPos(ch, 0) + ", " + getGvalueAtPos(ch, 1) + ", "+ getGvalueAtPos(ch, 2) + ", "+ getGvalueAtPos(ch, 3) + "]");
	}
	
	public static double getGvalueAtPos(Chromosome ch, int pos)
	{
		Double val = (Double) ch.getGene(pos).getAllele();
		return val.doubleValue();
	}
	
	public static void printFittest(Chromosome ch, PrintWriter pw) throws FileNotFoundException
	{
			pw.println(ch.getFitnessValue());
	}
	
	
}
