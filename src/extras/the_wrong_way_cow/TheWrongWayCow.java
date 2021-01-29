//https://www.codewars.com/kata/the-wrong-way-cow
//
//Task
//Given a field of cows find which one is the Wrong-Way Cow and return her position.
//
//Notes:
//
//There are always at least 3 cows in a herd
//There is only 1 Wrong-Way Cow!
//Fields are rectangular
//The cow position is zero-based [x,y] of her head (i.e. the letter c)
//Examples
//Ex1
//
//cow.cow.cow.cow.cow
//cow.cow.cow.cow.cow
//cow.woc.cow.cow.cow
//cow.cow.cow.cow.cow
//Answer: [6,2]
//
//Ex2
//
//c..........
//o...c......
//w...o.c....
//....w.o....
//......w.cow
//Answer: [8,4]
//
//Notes
//The test cases will NOT test any situations where there are "imaginary" cows, so your solution does not need to worry about such things!
//
//To explain - Yes, I recognize that there are certain configurations where an "imaginary" cow may appear that in fact is just made of three other "real" cows.
//In the following field you can see there are 4 real cows (3 are facing south and 1 is facing north). There are also 2 imaginary cows (facing east and west).
//
//...w...
//..cow..
//.woco..
//.ow.c..
//.c.....

package extras.the_wrong_way_cow;

public class TheWrongWayCow {

    public static int[] findWrongWayCow(final char[][] field) {
    	//ints to keep track of each instance of "cow"
    	int leftCount = 0;
    	int rightCount = 0;
    	int upCount = 0;
    	int downCount = 0;
    	
    	int[] leftHead = {0,0};
    	int[] rightHead = {0,0};
    	int[] downHead = {0,0};
    	int[] upHead = {0,0};
    	
    	int[] counts = {0,0,0,0};
    	int largest = 0;
    	int second = 0;

    	//[0] = horizontal
    	
    	//finding c/o/w aka up
        for (int i = 0; i < field.length - 2; i++) {
        	for (int j = 0; j < field[i].length; j++) {
        		if(field[i][j] == 'c' && field[i+1][j] == 'o' && field[i+2][j] == 'w') {
        			System.out.println("up");
        			upHead[1] = i;
        			upHead[0] = j;
        			upCount++;
        		}
    		}
		}      

        //finding woc aka right
        for (int i = 0; i < field.length - 2; i++) {
        	for (int j = 0; j < field[i].length; j++) {
        		if(field[i][j] == 'w' && field[i+1][j] == 'o' && field[i+2][j] == 'c') {
        			System.out.println("right");
        			rightHead[1] = i;
        			rightHead[0] = j;
        			rightCount++;
        		}
    		}
		}
          
        //finding cow aka left
        for (int i = 0; i < field.length; i++) {
        	for (int j = 0; j < field[i].length - 2; j++) {
        		if(field[i][j] == 'c' && field[i][j+1] == 'o' && field[i][j+2] == 'w') {
        			System.out.println("left");
        			leftHead[1] = i;
        			leftHead[0] = j;
        			leftCount++;
        		}
    		}
		}
        System.out.println(leftHead[0]);
        System.out.println(leftHead[1]);
        //finding w/o/c aka down 
        for (int i = 0; i < field.length; i++) {
        	for (int j = 0; j < field[i].length - 2; j++) {
        		if(field[i][j] == 'w' && field[i][j+1] == 'o' && field[i][j+2] == 'c') {
        			System.out.println("down");
        			downHead[1] = i;
        			downHead[0] = j;
        			downCount++;
        		}
    		}
		}
        
        counts[0] = leftCount;
        counts[1] = rightCount;
        counts[2] = upCount;
        counts[3] = downCount;
        
        for (int i = 0; i < counts.length; i++) {
        	if (counts[i] > largest) {
        		second  = largest;
        		largest = counts[i];
        	}
		}
        
    		if (second == leftCount) {
    			return leftHead;
    		}
        	if (second == rightCount) {
        		return rightHead;
			}
        	if (second == upCount) {
        		return upHead;
			}
        	if (second == downCount) {
        		return downHead;
			}
      
        return null;
    }
}
