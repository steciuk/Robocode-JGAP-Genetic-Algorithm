package geneticPractice;

import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

import robocode.control.BattleSpecification;
import robocode.control.BattlefieldSpecification;
import robocode.control.RobocodeEngine;
import robocode.control.RobotSetup;
import robocode.control.RobotSpecification;
import robocode.control.events.BattleCompletedEvent;
import robocode.control.events.BattleErrorEvent;
import robocode.control.events.BattleFinishedEvent;
import robocode.control.events.BattleMessageEvent;
import robocode.control.events.BattlePausedEvent;
import robocode.control.events.BattleResumedEvent;
import robocode.control.events.BattleStartedEvent;
import robocode.control.events.IBattleListener;
import robocode.control.events.RoundEndedEvent;
import robocode.control.events.RoundStartedEvent;
import robocode.control.events.TurnEndedEvent;
import robocode.control.events.TurnStartedEvent;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

import org.jgap.Chromosome;


public class TrackerFitnessFunction extends FitnessFunction implements IBattleListener {
	int fitness;
	
	TrackerFitnessFunction()
	{
		fitness = 1;
	}
	
	@Override
	protected double evaluate(IChromosome a_subject) {		
		try {
			printForRobot(a_subject);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		RobocodeEngine engine =  new RobocodeEngine(new java.io.File("C:/Robocode")); 
		engine.setVisible(false); 
		 	
	 //SETTINGS		 	
		int cols = 14;
		int rows = 14;
	 //
		 
		 int NumPixelRows = rows * 64;
		 int NumPixelCols = cols * 64;
		 	 	 
		 BattlefieldSpecification battlefield = new BattlefieldSpecification(NumPixelRows, NumPixelCols);
		 		 
		 int numberOfRounds = 1;
		 long inactivityTime = 10000000;
		 double gunCoolingRate = 0.1;
		 int sentryBorderSize = 50;
		 boolean hideEnemyNames = false; 
		 		 		 
		 RobotSpecification[] modelRobots = engine.getLocalRepository("fnl.SuperRamFire*, fnl.SuperTracker*"); 
		 RobotSpecification[] existingRobots = new RobotSpecification[2]; 
		 RobotSetup[] robotSetups = new RobotSetup[2]; 
		 
		 int colRam = 2;
		 int rowRam = 2; 
		 double iniRowRam = 32 + (rowRam*64);
		 double iniColRam  = 32 + (colRam*64);
		 existingRobots[0] = modelRobots[0];
		 robotSetups[0] = new RobotSetup(iniColRam, iniRowRam, 0.0);
		 
		 int colTrack = 12;
		 int rowTrack = 12; 
		 double iniRowTrack = 32 + (rowTrack*64);
		 double iniColTrack  = 32 + (colTrack*64);
		 existingRobots[1] = modelRobots[1];
		 robotSetups[1] = new RobotSetup(iniColTrack, iniRowTrack, 0.0);
		 	 
		 BattleSpecification battleSpec = new BattleSpecification
				 (
				 battlefield,
				 numberOfRounds,
				 inactivityTime,
				 gunCoolingRate,
				 sentryBorderSize,
				 hideEnemyNames,
				 existingRobots,
				 robotSetups
				 ); 
		  
		 engine.addBattleListener(this);
		 engine.runBattle(battleSpec, true);
	
		 
		 System.out.println(fitness);
		 return fitness;
	}
	
	private static void printForRobot(IChromosome a_subject) throws FileNotFoundException
	{
		try(PrintWriter pw = new PrintWriter("E:\\CODING\\eclipse-workspace\\geneticRobots\\bin\\fnl\\SuperTracker.data\\roboFile.dat")){
			printGenotypeForRobot(a_subject, pw);			
		}		
	}
	
	private static void printGenotypeForRobot(IChromosome ch, PrintWriter pw)
	{
		pw.println(getGvalueAtPos(ch, 0));
		pw.println(getGvalueAtPos(ch, 1));
		pw.println(getGvalueAtPos(ch, 2));
		pw.println(getGvalueAtPos(ch, 3));
	}
	
	private static void printGenotype(IChromosome ch, PrintWriter pw)
	{
		pw.println(getGvalueAtPos(ch, 0) + " " + getGvalueAtPos(ch, 1) + " "+ getGvalueAtPos(ch, 2) + " "+ getGvalueAtPos(ch, 3));
	}
	
	private static double getGvalueAtPos(IChromosome ch, int pos)
	{
		Double val = (Double) ch.getGene(pos).getAllele();
		return val.doubleValue();
	}

	@Override
	public void onBattleCompleted(BattleCompletedEvent event) {
		fitness = event.getIndexedResults()[1].getScore() + 1;
	}

	@Override
	public void onBattleError(BattleErrorEvent event) {
		// TODO Auto-generated method stub
		

	}

	@Override
	public void onBattleFinished(BattleFinishedEvent event) {

	}

	@Override
	public void onBattleMessage(BattleMessageEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBattlePaused(BattlePausedEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBattleResumed(BattleResumedEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBattleStarted(BattleStartedEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRoundEnded(RoundEndedEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRoundStarted(RoundStartedEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTurnEnded(TurnEndedEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTurnStarted(TurnStartedEvent event) {
		// TODO Auto-generated method stub
		
	}

}
