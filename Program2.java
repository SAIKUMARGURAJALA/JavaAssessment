/*
Given four input (x,y) coordinates, determine the shape type and 
area. Return the shape type and area values. If shape type is 
other, return -1 as the area value.
Input Format:
(x1,y1) (x2,y2) (x3,y3) (x4,y4)

Shape types: 
* Trapezoid
* Rectangle
* Square
* Rhombus
* Parallelogram
* Kite
* Other

Expected output format:
Square 9
Other -1
 */
import java.util.Scanner;
public class Program2 {
    public static void main(String[] args) {
        double side1=0,side2=0, side3=0, side4=0;
        double x1 = 0, y1 = 0;
        double x2 = 0, y2 = 0;
        double x3 = 0, y3 = 0;
        double x4 = 0, y4 = 0;
        Scanner scanner=new Scanner(System.in);

        System.out.println("Let's check the shape and are: ");
        //square
        // x1 = 1; y1 = 1;
        // x2 = 2; y2 = 1;
        // x3 = 2; y3 = 2;
        // x4 = 1; y4 = 2;

        //Trapezoid. (AB and CD are horizontal and parallel, with different lengths.)
        // x1 = 1; y1 = 1;
        // x2 = 5; y2 = 1;
        // x3 = 4; y3 = 3;
        // x4 = 2; y4 = 3;

        //Rhombus (all sides equal, but diagonals not equal, it's a non-square rhombus)
        // x1 = 1; y1 = 3;
        // x2 = 3; y2 = 4;
        // x3 = 5; y3 = 3;
        // x4 = 3; y4 = 2;

        //Rectangle (Both 2 opposites will be equal)
        // x1 = 0 ; y1 = 0;
        // x2 = 4 ; y2 = 0;
        // x3 = 4 ; y3 = 3;
        // x4 = 0 ; y4 = 3;

        //Parallelogram (opposite sides equal & parallel but not a rectangle)
        // x1 = 0 ; y1 = 0;
        // x2 = 4 ; y2 = 0;
        // x3 = 5 ; y3 = 3;
        // x4 = 1 ; y4 = 3;

        //Kite (adjacents sides will be equal, not opposite sides.)
        // x1 = 2 ; y1 = 3;
        // x2 = 4 ; y2 = 3;
        // x3 = 2 ; y3 = 5;
        // x4 = 0 ; y4 = 3;
        
        //Other
        // x1 = 0 ; y1 = 0;
        // x2 = 4 ; y2 = 1;
        // x3 = 3 ; y3 = 4;
        // x4 = 1 ; y4 = 3;

        // Input coordinates
        System.out.println("Enter coordinates (x1, y1), (x2, y2), (x3, y3), (x4, y4): ");
        System.out. print("Enter x1: ");
        x1 = scanner.nextDouble(); 
        System.out.print("Enter y1: ");
        y1 = scanner.nextDouble();
        System.out.print("Enter x2: ");
        x2 = scanner.nextDouble(); 
        System.out.print("Enter y2: ");
        y2 = scanner.nextDouble();
        System.out.print("Enter x3: ");
        x3 = scanner.nextDouble();
        System.out.print("Enter y3: ");
        y3 = scanner.nextDouble();
        System.out.print("Enter x4: ");
        x4 = scanner.nextDouble();
        System.out.print("Enter y4: ");
        y4 = scanner.nextDouble();
        scanner.close();

        side1=getDistanceBtwTwoPoints(x1, y1, x2, y2);
        side2=getDistanceBtwTwoPoints(x2, y2, x3, y3);
        side3=getDistanceBtwTwoPoints(x3, y3, x4, y4);
        side4=getDistanceBtwTwoPoints(x4, y4, x1, y1);
        System.out.println("\nDistance1: " + side1);
        System.out.println("Distance2: " + side2);
        System.out.println("Distance3: " + side3);
        System.out.println("Distance4: " + side4);

        if(side1==side2 && side2==side3 && side3==side4)
        {
            double diagonal1=0, diagonal2=0;
            diagonal1=getDistanceBtwTwoPoints(x1, y1, x3, y3);
            diagonal2=getDistanceBtwTwoPoints(x2, y2, x4, y4);
            
            if(diagonal1!=diagonal2)
            {
                System.out.println("Rhombus "+(diagonal1*diagonal2)/2);
            }
            else{
                System.out.println("Square " + side1*side1);
            }
            
        }
        else if(side1==side3 && side2==side4 && side1!=side2 &&side3!=side4)
        {
            //parallelogram
            //https://byjus.com/maths/parallelogram-trapezium-kite/
            if(!areDiagonalsEqual(x1, y1, x3, y3, x2, y2, x4, y4))
            {
                // Compute the cross product magnitude for vectors AB and AC
                double crossProductMagnitude = Math.abs((x2 - x1) * (y3 - y1) - (y2 - y1) * (x3 - x1));
                // Calculate height (h)
                double height = crossProductMagnitude / side1;
                //are of Parallelogram is base*height. Here base is side1 and is as calculated above
                System.out.println("Parallelogram "+side1*height);
            }
            else
            {
                //area of rectangle is L*W
                System.out.println("Rectangle "+side1*side2);
            }
        }
        //Trapezoid opposite side will be parallel but not equal 
        else if(side1!=side3 && areTwoSidesParallel(x1,y1,x2,y2,x3,y3,x4,y4))
        {
            //https://byjus.com/maths/trapezoids/#:~:text=Trapezoids%20are%20quadrilaterals%20that%20have,figure%20and%20not%203D%20figure.
            double height=0, area=0;

            //area of Trapezoid is: (average of 2 opposite sides taken as bases)*height
            height=getDistanceBtwTwoPoints(x4, y1, x4, y4);
            area=((side1+side2)/2)*height;

            System.out.println("Trapezoid "+area);
        }
        //Kite has adjacent sides equal and opposite sides not equal
        else if(side1==side4 && side2==side3 && side1!=side2 && side3!=side4)
        {
            double diagonal1=0, diagonal2=0;

            diagonal1=getDistanceBtwTwoPoints(x1, y1, x3, y3);
            diagonal2=getDistanceBtwTwoPoints(x2, y2, x4, y4);
            System.out.println("Kite "+(diagonal1*diagonal2)/2);
        }
        else
            System.out.println("Other -"+1);
    }
    
    private static boolean areTwoSidesParallel(double x1Line1, double y1Line1, double x2Line1, double y2Line1, double x2Line2, double y2Line2, double x1Line2, double y1Line2)
    {
        Double slopeOfLine1=null, slopeOfLine2=null;
        boolean isParallel=false;

        //slope1 of line1
        slopeOfLine1=calculateSlopeOfLine(x1Line1, y1Line1, x2Line1, y2Line1);
        //slope2 of line2
        slopeOfLine2=calculateSlopeOfLine(x1Line2, y1Line2, x2Line2, y2Line2);

        if(slopeOfLine1==null && slopeOfLine2==null)
        {
            //both line are parallel as they both are vertical lines with slope as null/ undefined.
            return true;
        }
        // else if(slopeOfLine1==0 && slopeOfLine2==null)
        else if(slopeOfLine1==null || slopeOfLine2==null)
        {
            //if either one of the slope is null/ defined, then either one of them is vertical and the other is not. Thus not parallel.
            return false;
        }
        //when both slopes are equal, they are parallel.
        isParallel=slopeOfLine1.doubleValue()==slopeOfLine2.doubleValue();
        return isParallel;
    }

    private static Double calculateSlopeOfLine(double x1, double y1, double x2, double y2)
    {
        //Cannot divide by zero. The slope is undefined for vertical lines. The line is a vertical line
        if(x2-x1==0)
        {
            return null;
        }
        //this is the formula for finding slope of a line
        return (y2-y1)/(x2-x1);
    }

    private static boolean areDiagonalsEqual(double x1, double y1, double x3, double y3, double x2, double y2, double x4, double y4)
    {
        double diagonal1=0, diagonal2=0;
        diagonal1=getDistanceBtwTwoPoints(x1, y1, x3, y3);
        diagonal2=getDistanceBtwTwoPoints(x2, y2, x4, y4);

        return diagonal1==diagonal2;
    }

    private static double getDistanceBtwTwoPoints(double x1, double y1, double x2, double y2)
    {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
    
}