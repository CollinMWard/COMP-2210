import java.util.Scanner;
public class PA3_Java {
/* Multiplies matrix 1 by matrix 2
*/
   public static float[][] matrix_mult(float[][] mat1, float[][] mat2) {
      float[][] result = new float[mat1.length][mat2.length];
      for (int i = 0; i < mat1.length; i++) {
         for (int j = 0; j < mat2.length; j++) {
            for (int k = 0; k < mat2.length; k++)
               result[i][j] += mat1[i][k] * mat2[k][j];
         }
      }
      return result;
   }
   /* mulitplies matrix with param of [][] and []
   * made another method to avoid chaning any return types and etc.
   */
   public static float[][] matrix_mult(float[][] mat1, float[] mat2) {
      float[][] result = new float[mat1.length][mat2.length];
      for (int i = 0; i < mat1.length; i++) {
         for (int j = 0; j < mat2.length; j++) {
            result[i][j] += mat1[i][j] * mat2[j];
         }
      }
      // again to avoid changing return types bc of gradescope.
      // if the current day is R, it swaps the values around
      if (result[0][0] == 0.0) {
         result[0][0] = result[0][1];
         result[1][0] = result[1][1];
      }
      return result;
   }
   /* Create the transition matrix from the given observation points
*/
   public static float[][] calc_transition_matrix(String observation_string) throws java.lang.Exception {
      float[][] transition_matrix = new float[2][2];
      String str = observation_string;
      int j = 0;
      float dd = 0;
      float dr = 0;
      float rr = 0;
      float rd = 0;
      float rTotal = 0;
      float dTotal = 0;
      for (int i = 1; i < str.length(); i++) {
         String strTwo = str.substring(j, i + 1);
         if (strTwo.equals("DD")) {
            dd++;  
            dTotal++;
         } else
            if (strTwo.equals("DR")) {
               dr++;  
               dTotal++;  
            } else
               if (strTwo.equals("RR")) {
                  rr++;
                  rTotal++;
               } else
                  if (strTwo.equals("RD")) {
                     rd++;
                     rTotal++;
                  }
         j++;
      }
      // checks for all requirments of the observation.
      // dTotal + rTotal should equal the length - 1 of string if it has only acceptible char
      if (dd == 0 || dr == 0 || rr == 0 || rr == 0 || dTotal + rTotal != str.length() - 1) {
         
         throw new java.lang.Exception();
      }
   
      // probability is based off of the numner of potenial next inputs so total is split between dTotal and rTotal
      transition_matrix[0][0] = dd / dTotal;
      transition_matrix[1][0] = dr / dTotal;
      transition_matrix[0][1] = rd / rTotal;
      transition_matrix[1][1] = rr / rTotal;
      
      
      
      return transition_matrix;
   }
/* Generates the forecast for the next 7 days given the transition matrix and
the current weather
The forecast should be a 2x7 matrix where each row is a forecast for a day
*/
   public static float[][] generate_forecast(float[][] transition_matrix, char
   curr_weather) {
      try {
         if (curr_weather != 'D' && curr_weather != 'R') {
            throw new java.lang.Exception();
         }
      } catch(java.lang.Exception e) {
         System.out.println(e);
      }
      float[] vector = new float[2];
      float[][] forecast = new float[7][2];
      if (curr_weather == 'D') {
         vector[0] = 1;
         vector[1] = 0;
      } else {
         vector[0] = 0;
         vector[1] = 1;
      }
      // could use the new matrix_power method instead but this is fine
      // Day 1
      float[][] dayOne = matrix_mult(transition_matrix, vector);
      forecast[0][0] = dayOne[0][0];
      forecast[0][1] = dayOne[1][0];
      // Day 2
      float[][] dayTwo = matrix_mult(matrix_mult(transition_matrix, transition_matrix), vector);
      forecast[1][0] = dayTwo[0][0];
      forecast[1][1] = dayTwo[1][0];
       // Day 3
      float[][] dayThree = matrix_mult(matrix_mult(matrix_mult(transition_matrix, transition_matrix), transition_matrix), vector);
      forecast[2][0] = dayThree[0][0];
      forecast[2][1] = dayThree[1][0];
      // Day 4
      float[][] dayFour = matrix_mult(matrix_mult(transition_matrix, matrix_mult(matrix_mult(transition_matrix, transition_matrix), transition_matrix)), vector);
      forecast[3][0] = dayFour[0][0];
      forecast[3][1] = dayFour[1][0];
    // Day 5
      float[][] dayFive = matrix_mult(matrix_mult(transition_matrix, matrix_mult(transition_matrix, matrix_mult(matrix_mult(transition_matrix, transition_matrix), transition_matrix))), vector);
      forecast[4][0] = dayFive[0][0];
      forecast[4][1] = dayFive[1][0];
      // Day 6
      float[][] daySix = matrix_mult(matrix_mult(transition_matrix, matrix_mult(transition_matrix, matrix_mult(transition_matrix, matrix_mult(matrix_mult(transition_matrix, transition_matrix), transition_matrix)))), vector);
      forecast[5][0] = daySix[0][0];
      forecast[5][1] = daySix[1][0];
        // Day 7
      float[][] daySevon = matrix_mult(matrix_mult(transition_matrix, matrix_mult(transition_matrix, matrix_mult(transition_matrix, matrix_mult(transition_matrix, matrix_mult(matrix_mult(transition_matrix, transition_matrix), transition_matrix))))), vector);
      forecast[6][0] = daySevon[0][0];
      forecast[6][1] = daySevon[1][0];
      
      return forecast;
   }
/* Generates the climate prediction (i.e., steady state vector) given the
transition matrix, current
weather, and precision
*/
   public static float[] generate_climate_prediction(float[][] transition_matrix,
   char curr_weather, float precision) throws java.lang.Exception {
    
      if (precision > .10f || precision < 0) {
         throw new java.lang.Exception(); }
   
      float[] steady_state = new float[2];
    
      float[] vector = new float[2];
      float[][] forecast = new float[7][2];
      if (curr_weather == 'D') {
         vector[0] = 1;
         vector[1] = 0;
      } else {
         vector[0] = 0;
         vector[1] = 1;
      }
      int i = 8;
      while ((matrix_mult(matrix_power(transition_matrix, i), vector)[0][0] - (matrix_mult(matrix_power(transition_matrix, i - 1), vector)[0][0]) > precision)
         && (matrix_mult(matrix_power(transition_matrix, i), vector)[1][1] - (matrix_mult(matrix_power(transition_matrix, i - 1), vector)[1][1])) > precision) {
         i++;
      }
      float[][] temp = matrix_mult(matrix_power(transition_matrix, i), vector);
      steady_state[0] = temp[0][0];
      steady_state[1] = temp[1][0];
      
      return steady_state;
   }
   /* raises a matrix to the given power
   */
   public static float[][] matrix_power (float[][] a, int p) {
      float[][] result = a;
      for (int n = 1; n < p; ++ n)
         result = matrix_mult(result, a);
      return result;
   }
/* Print the forecasted weather predictions
*/
   public static void print_predictions(float[][] forecast) {
   // Print first line
      System.out.println("[[" + forecast[0][0] + "," + forecast[0][1] + "],");
   // Print middle 5 lines
      for (int i = 1; i < forecast.length - 1; i++) {
         System.out.println(" [" + forecast[i][0] + "," + forecast[i][1] + "],");
      }
   // Print the last line
      System.out.println(" [" + forecast[6][0] + "," + forecast[6][1] + "]]");
   }
/* Print the steady state vector containing the climate prediction
*/
   public static void print_steady_state(float[] steady_state) {
      System.out.println(steady_state[0]);
      System.out.println(steady_state[1]);
   }
   public static void main(String[] args) {
      String observation;
      float precision;
      System.out.println("Enter observation input (D or R)");
      Scanner scan = new Scanner(System.in);
      observation = scan.nextLine();
      boolean pass = false;
      while (pass == false) {
         try {
            PA3_Java.calc_transition_matrix(observation);
            pass = true;
         } catch (java.lang.Exception e) {
            System.out.println("invalid input");
            observation = scan.nextLine();
         }
         
      }
      
      System.out.println("Enter precision (less than .1)");
      precision = Float.parseFloat(scan.nextLine());
      while (precision > .1f) {
         try {
            throw new java.lang.Exception();
         
         } catch (java.lang.Exception e) {
            System.out.println("invalid input, must be <= .1");
            precision = Float.parseFloat(scan.nextLine());
            
         } 
      }
     
      char c = observation.charAt(observation.length() - 1);
      try { 
         float[][] tempOut = PA3_Java.calc_transition_matrix(observation);
      
         PA3_Java.print_predictions(PA3_Java.generate_forecast(tempOut, c));
         PA3_Java.print_steady_state(PA3_Java.generate_climate_prediction(tempOut, c, precision));
      } catch (Exception e) {
      
      }
   }
}