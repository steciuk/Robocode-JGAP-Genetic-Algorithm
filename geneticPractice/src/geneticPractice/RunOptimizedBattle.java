package geneticPractice;



import robocode.control.BattleSpecification;
import robocode.control.BattlefieldSpecification;
import robocode.control.RobocodeEngine;
import robocode.control.RobotSetup;
import robocode.control.RobotSpecification;



public class RunOptimizedBattle{	
	public static void main(String[] args)
	{
		
		RobocodeEngine engine =  new RobocodeEngine(new java.io.File("C:/Robocode")); 
		engine.setVisible(true); 
		 	
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
		  
		 engine.runBattle(battleSpec, true);
	
		 engine.close();
	}
}
