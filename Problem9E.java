import java.util.ArrayList;

public class Problem9E {
    public static void main(String[] args){
        Matrix matrix= new Matrix(3,3);
        double[] arrayNegativeOneOneOne= {-1,1,1};
        double[] arrayOneNegativeOneOne= {1,-1,1};
        double[] arrayOneOneNegativeOne= {1,1,-1};
        double[] arrayNegativeOneNegativeOneOne= {-1,-1,1};
        double[] arrayOneNegativeOneNegativeOne= {1,-1,-1};
        double[] arrayNegativeOneOneNegativeOne= {-1,1,-1};
        double[] arrayOneOneOne= {1,1,1};
        double[] arrayNegativeOneNegativeOneNegtivaOne= {-1,-1,-1};

        ArrayList<double[]> list = new ArrayList<>();
        list.add(arrayOneOneOne);
        list.add(arrayNegativeOneOneOne);
        list.add(arrayOneNegativeOneOne);
        list.add(arrayOneOneNegativeOne);
        list.add(arrayNegativeOneNegativeOneOne);
        list.add(arrayNegativeOneOneNegativeOne);
        list.add(arrayOneNegativeOneNegativeOne);
        list.add(arrayNegativeOneNegativeOneNegtivaOne);

        ArrayList<Double> detList = new ArrayList<>();
        for(double[] row: list){
            matrix.InitializeArray(row, 1);
            for(double[] row2: list){
                matrix.InitializeArray(row2, 2);
                for(double[] row3: list){
                    matrix.InitializeArray(row3, 3);
                    detList.add(Determinant.det3x3(matrix));
                }
            }
        }

        System.out.println(detList);
        for(double check: detList){
            if(check== -6){
                System.out.println("-6 appears");
            }
            if(check== -2){
                System.out.println("-2 appears");
            }
            if(check== 6){
                System.out.println("6 appears");
            }
            if(check== 2){
                System.out.println("2 appears");
            }
        }
    }
}
