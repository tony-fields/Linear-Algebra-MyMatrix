//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Matrix matrix;
        if(args.length==0){
            Scanner s= new Scanner(System.in);
            System.out.println("How big of an matrix? rows x columns");
            String matrixDimensions= s.nextLine();
            String[] matDim= matrixDimensions.split(" ");
            int matrixSizeRow= Integer.parseInt(matDim[0]);
            int matrixSizeColumn=Integer.parseInt(matDim[1]);
            matrix= new Matrix(matrixSizeRow,matrixSizeColumn);
            System.out.println("Enter your matrix values one row at a time");
            for(int i=0; i<matrixSizeRow; i++){
                String[] line= s.nextLine().split(" ");
                double[] lineDouble= new double[line.length];
                for(int m=0; m<line.length;m++){
                    lineDouble[m]=Double.parseDouble(line[m]);
                }
                matrix.InitializeArray(lineDouble, i+1);
            }
        } else {
            Scanner input = null;
            ArrayList<String[]> arrayList= new ArrayList<>();

            try{
                File f= new File(args[0]);
                input = new Scanner(f);
            } catch (Exception e){
                System.out.println("The exception is: "+e);
                System.exit(-1);
            }
            int lineNumber=0;


            while(input.hasNextLine()) {
                String line = input.nextLine();
                String[] lineContents = line.split(" ");
                arrayList.add(lineContents);

            }
            matrix= new Matrix(arrayList.size(),arrayList.get(0).length);
            for(int i=0; i<arrayList.size(); i++){
                double[] lineDouble= new double[arrayList.get(i).length];
                for(int m=0; m<lineDouble.length;m++){
                    lineDouble[m]=Integer.parseInt(arrayList.get(i)[m]);
                }
                matrix.InitializeArray(lineDouble, i+1);
            }
        }



        System.out.println("----------");
        System.out.println("What would you like to do to your matrix?");
        System.out.println("swap, multiply, divide, det3x3, rref, or combine two rows?");
        Scanner s2= new Scanner(System.in);
        String controller= "ligma";
        int firstTime=0;
        while(!controller.equals("stop")){
            if(firstTime==0){
                controller= s2.next();
            }
            Scanner b= new Scanner(System.in);
            Scanner l = new Scanner(System.in);
            switch (controller) {
                case "swap":
                    System.out.println("What two rows do you want to swap?");
                    String bruv= b.nextLine();
                    String[] directions= bruv.split(" ");
                    int a=Integer.parseInt(directions[0]);
                    int c=Integer.parseInt(directions[1]);
                    matrix.switchRow(a,c);
                    break;
                case "multiply":
                    System.out.println("What row and by what number?");
                    String[] numbas= (b.nextLine()).split(" ");
                    int d= Integer.parseInt(numbas[0]);
                    double e= Double.parseDouble(numbas[1]);
                    matrix.multiplyRow(d,e);
                    break;
                case "divide":
                    System.out.println("What row and by what number?");
                    String[] numbas2= (b.nextLine()).split(" ");
                    int f= Integer.parseInt(numbas2[0]);
                    double g= Double.parseDouble(numbas2[1]);
                    matrix.divideRow(f,g);
                    break;
                case "combine":
                    System.out.println("What row +/- by what multiple of another row?");
                    String[] numbas3= (b.nextLine()).split(" ");
                    int h= Integer.parseInt(numbas3[0]);
                    double i= Double.parseDouble(numbas3[1]);
                    int j= Integer.parseInt(numbas3[2]);
                    String div= null;
                    if(numbas3.length==4){
                        div=numbas3[3];
                    }
                    matrix.addSubtractRow(h,i,j, div);
                    break;
                case "rref":
                    Rref.rref(matrix);
                    break;
                case "det":
                    System.out.println("The det is "+Determinant.det3x3(matrix));
            }
            matrix.print();
            System.out.println("------------");
            /*System.out.println("This is an organized one");
            Rref.putOneOnTop(matrix);
            matrix.print();*/
            System.out.println("stop, swap, multiply, divide, rref or combine two rows?");
            controller= l.next();
            firstTime++;
        }

        }
    }
