import java.util.Random;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/*COMP 3240 Spring 2023 Programming Assignment
4”   Collin Ward
*/
public class PA4_Java {
/* Perform one instance of the Monty Hall Problem
*
* should_switch: set to true if running an experiment where the contestant
should
* switch their guess every time. Set to false if not
*
* Returns true if the contestant selected the door with the car behind it.
Returns
* false otherwise.
*/
   public static boolean run_trial(boolean should_switch) {
      String[] doors = new String[3];
      doors[0] = "car";
      doors[1] = "goat";
      doors[2] = "goat";
      List<String> doorsList = Arrays.asList(doors);
      // array is shuffled, creating random order
      Collections.shuffle(doorsList);
      doorsList.toArray(doors);
      Random rand = new Random();
      // player picks door randomly
      int pick = rand.nextInt(3);
      // if they are not switching doors
      if (!should_switch) {
         return (doors[pick] == "car");
      }
      int goat1;
      for (int i = 0; i < doors.length - 1; i++) {
         if (doors[i] == "goat" && i != pick) {
            doors[i] = null;
            goat1 = i;
            break;
         }
      }
      // swaps doors to whichever is not goat1 and not the first pick
      for (int i = 0; i < doors.length - 1; i++) {         
         if (doors[i] != null && i != pick) {
            pick = i;
            break;
         }
      }
      return (doors[pick] == "car");   
   }
          
/* Execute an entire experiment (i.e., execute the specified number of trials)
* and return the desired results
*
* num_trials: number of trials to execute in this experiment
* should_switch: set to true if running an experiment where the contestant
should
* switch their guess every time. Set to false if not
*
* Returns the percentage of games won (i.e., number of wins / number of trials)
*/
   public static double run_experiment(int num_trials, boolean should_switch) {
      double win = 0;
      double total = (double) num_trials;
      for (int i = 0; i < num_trials; i++) {
         if (run_trial(should_switch)) {
            win++;
         }
      }
      return (win/total);
   }
/* This is a stub that you can use to test the rest of the program. The code in
this
* method will not be executed during grading, so you don't need to worry about
user
* input.
*/
   public static void main(String[] args) {
      int num_trials = 1000;
      boolean should_switch = true;
      double prob = PA4_Java.run_experiment(num_trials, should_switch);
   }
}