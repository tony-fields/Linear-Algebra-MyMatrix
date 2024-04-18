public class Determinant {

    public static double det3x3(Matrix matrix){
        if(matrix.rowSize()!=3 && matrix.columnSize()!=3){
            throw new IllegalArgumentException();
        }
        double crossOne = matrix.getValue(1,1)*(
                (matrix.getValue(2,2)*matrix.getValue(3,3))-
                        (matrix.getValue(2,3)* matrix.getValue(3,2)));

        double crossTwo = matrix.getValue(1,2)*(
                (matrix.getValue(2,1)*matrix.getValue(3,3))-
                        (matrix.getValue(2,3)* matrix.getValue(3,1)));

        double crossThree = matrix.getValue(1,3)*(
                (matrix.getValue(2,1)*matrix.getValue(3,2))-
                        (matrix.getValue(2,2)* matrix.getValue(3,1)));

        return crossOne-crossTwo+crossThree;


    }
}
