import java.lang.module.FindException;

public class Rref {

    /*public static boolean rrefCheck(Matrix matrix){

    }*/

    public static boolean inconsistentRowCheck(Matrix matrix, int row){
        double[] rowArray= matrix.getRow(row).array;
        double num=0.0;
        for(int i=0; i< matrix.columnSize()-1;i++){
            num+=rowArray[i];
        }
        return (num==0.0 && rowArray[matrix.columnSize()-1]!=0);
    }

    public static boolean inconsistentMatrixCheck(Matrix matrix){
        boolean check= false;
        for(int i=0; i<matrix.rowSize();i++){
            check= inconsistentRowCheck(matrix,i+1);
            if(check==true){
                return true;
            }
        }
        return check;
    }

    public static int leftMostNonZeroColumn(Matrix matrix){
        for(int i=1; i<=matrix.columnSize(); i++){ //column num
            for(int g=1; g<= matrix.rowSize(); g++){ //row num
                if(matrix.getValue(g,i)!=0){
                    return i;
                }
            }
        }
        return -1;
    }

    //this used at the beggining
    public static void putNumOnTop(Matrix matrix, int putTo){
        int mostLeftColumn= leftMostNonZeroColumn(matrix);
        int upperMostNonZero=-1;
        if(mostLeftColumn<=0){
            throw new FindException("This is an all zero matrix");
        }
        for(int g=1; g<= matrix.rowSize(); g++){ //row num
            if(matrix.getValue(g,mostLeftColumn)!=0){
                upperMostNonZero=g;
            }
        }
        matrix.switchRow(putTo,upperMostNonZero);
    }

    public static void organizeRow(Matrix matrix, int column, int rowStart){
        if(matrix.getValue(rowStart, column)!=0){
            return;
        }
        for(int i=rowStart; i<= matrix.rowSize(); i++){
            if(matrix.getValue(i, column)!=0){
                matrix.switchRow(rowStart, i);
                return;
            }
        }
    }

    //
    public static void reduceColumnDown(Matrix matrix, int column, int rowBegining){
        double valueOfTopRow= matrix.getValue(rowBegining, column);
        for(int i=rowBegining+1; i<=matrix.rowSize(); i++){
            double numbaOfRow=matrix.getValue(i,column);
            if(numbaOfRow==0){
                continue;
            }
            matrix.multiplyRow(i, valueOfTopRow);
            matrix.addSubtractRow(i,-(numbaOfRow), rowBegining, null);
        }
        matrix.print();
        System.out.println("---------");
    }
    public static void reduceColumnUp(Matrix matrix, int column, int rowBegining){
        double valueOfTopRow= matrix.getValue(rowBegining, column);
        for(int i=rowBegining-1; i>0; i--){
            double numbaOfRow=matrix.getValue(i,column);
            if(numbaOfRow==0){
                continue;
            }
            matrix.multiplyRow(i, valueOfTopRow);
            matrix.addSubtractRow(i,-(numbaOfRow), rowBegining, null);
        }
        matrix.print();
        System.out.println("---------");
    }

    public static boolean columnCheckForAllZeros(Matrix matrix, int column){
        for(int i=1; i<=matrix.rowSize(); i++){
            if(matrix.getValue(i, column)!=0){
                return false;
            }
        }
        return true;
    }

    public static boolean columnCheckForValuesBelow(Matrix matrix, int column, int row){
        for(int i=row; i<=matrix.rowSize(); i++){
            if(matrix.getValue(i, column)!=0){
                return false;
            }
        }
        return true;
    }

    public static void rref(Matrix matrix){
        int start = leftMostNonZeroColumn(matrix);
        int rowCounter=1;
        putNumOnTop(matrix, rowCounter);
        for(int i=start; i<=matrix.columnSize();i++){ //i is the column
            if(columnCheckForAllZeros(matrix, i)){
                continue;
            }

            if(columnCheckForValuesBelow(matrix, i, rowCounter)){
                continue;
            }
            organizeRow(matrix, i,rowCounter);

            if(rowCounter>matrix.rowSize()){
                continue;
            }

            if(rowCounter>1){
                reduceColumnUp(matrix, i, rowCounter);
            }
            reduceColumnDown(matrix, i, rowCounter);
            System.out.println("column "+i);
            rowCounter++;
        }
        reduceAllRows(matrix);
    }

    public static void reduceAllRows(Matrix matrix){
        for(int i=1; i<=matrix.rowSize(); i++){
            matrix.getRow(i).reduceRow();
        }
    }






    public static void putOnesOnBottom(Matrix matrix, int row, int column){ //aka sort from greatest to least

    }




}
