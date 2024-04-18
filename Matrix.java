import java.util.Iterator;

public class Matrix{
    private int n; //row
    private int m; //column
    private Row head;
    private Row tail;
    private Row[] array;

    //n is row, m is column

    public int rowSize(){
        return n;
    }
    public int columnSize(){
        return m;
    }

    public Row getRow(int rowNum){
        if((rowNum<1 || rowNum> array.length)){
            throw new OutOfBoundsOfMatrix();
        }
        Row row1=head.next;
        for(int i=1; i<rowNum; i++) {
            row1 = row1.next;
        }
        return row1;
    }

    public double getValue(int rowNum, int columnNum){
        if((rowNum<1 || rowNum> array.length) || (columnNum<1 || columnNum>m)){
            throw new OutOfBoundsOfMatrix();
        }
        Row row1=head.next;
        for(int i=1; i<rowNum; i++) {
            row1 = row1.next;
        }
        if(row1.array.length==1){
            throw new OutOfBoundsOfMatrix();
        }
        return row1.array[columnNum-1];
    }
    public Matrix(int n, int m) {
        this.n = n;
        this.m = m;
        head = new Row(1);
        tail = new Row(1);
        head.next = tail;
        tail.previous = head;
        array = new Row[n + 2];
        for (int i = 0; i < n; i++) {
            array[i] = new Row(m);
            array[i].next = tail;
            array[i].previous = tail.previous;
            tail.previous.next = array[i];
            tail.previous = array[i];
        }
    }


    public void multiplyRow(int row, double multiply){
        if((row<1 || row> array.length)){
            throw new OutOfBoundsOfMatrix();
        }
        Row row1=head.next;
        for(int i=1; i<row; i++) {
            row1 = row1.next;
        }
        row1.multiplyRow(multiply);
    }

    public void divideRow(int row, double divide){
        if((row<1 || row> array.length)){
            throw new OutOfBoundsOfMatrix();
        }
        Row row1=head.next;
        for(int i=1; i<row; i++) {
            row1 = row1.next;
        }
        row1.divideRow(divide);
    }
    public void switchRow(int rowA,int rowB){
        if(array.length==1){
            throw new InvalidArrayException();
        }
        if((rowA<1 || rowA> array.length) || (rowB<1 || rowB> array.length)){
            throw new OutOfBoundsOfMatrix();
        }
        Row row1=head.next;
        Row row2=head.next;

        Row temp1= new Row(1);
        Row temp2= new Row(1);
        for(int i=1; i<rowA; i++) {
            row1 = row1.next;
        }
        for(int i=1; i<rowB; i++) {
            row2 = row2.next;
        }
        //set temps with memory addresses of the actual rows
        temp1.next=row1.next;
        temp1.previous=row1.previous;
        temp2.next=row2.next;
        temp2.previous=row2.previous;
        if(rowA-rowB==1 || rowA-rowB==-1){
            row2.previous=row1.previous;
            row1.previous.next=row2;
            row1.next=row2.next;
            row2.next.previous=row1;
            row1.previous=row2;
            row2.next=row1;
        } else {
            //make the 4 memory swaps coming from the rowA and rowB
            row2.next = row1.next;
            row2.previous = row1.previous;
            row1.next = temp2.next;
            row1.previous = temp2.previous;
            //memory swaps of the previous and next rows
            temp1.previous.next = row2;
            temp1.next.previous = row2;
            temp2.previous.next = row1;
            temp2.next.previous = row1;
        }
    }

    public void print(){
        Row temp= head;
        for(int i=0; i<n; i++){
            temp=temp.next;
            System.out.println(temp);
        }
    }

    public void printOperations(){
        Row temp= head;
        for(int i=0; i<n; i++){
            temp=temp.next;
            System.out.println("row "+(i+1) +"operations performed "+ temp.actionsPerformed());
        }
    }

    public void InitializeArray(double[] a, int rowNum){
        Row temp= head;
        for(int i=0; i<rowNum; i++){
            temp=temp.next;
        }
        temp.InitializeArray(a);

    }

    public void addSubtractRow(int rowA, double doBy, int rowB, String div){
        if((rowA<1 || rowA> array.length) || (rowB<1 || rowB> array.length)){
            throw new OutOfBoundsOfMatrix();
        }
        Row row1=head.next;
        for(int i=1; i<rowA; i++) {
            row1 = row1.next;
        }
        Row row2=head.next;
        for(int i=1; i<rowB; i++) {
            row2 = row2.next;
        }
        for(int i=0; i< row1.array.length; i++){
            if(div==null){
                row1.array[i]+=(row2.array[i]*doBy);
                row1.operationPerformed("*"+doBy);
            } else {
                row1.array[i]+=(row2.array[i]/doBy);
                row1.operationPerformed("/"+doBy);
            }

        }
    }
}



class Row {
    Row next = null;
    Row previous = null;
    double[] array;

    String operationsPeformed="";

    public void operationPerformed(String operation){
        operationsPeformed.concat(operation+" ");
    }

    public Row(int num) {
        this.array = new double[num];
    }

    public void InitializeArray(double[] arr) {
        if (arr.length != array.length) {
            throw new InvalidArrayException();
        }
        for (int i = 0; i < arr.length; i++) {
            array[i] = arr[i];
        }
    }

    public String actionsPerformed(){
        return operationsPeformed;
    }

    public String toString(){
        String returnS="";
        for(int i=0; i< array.length; i++){
            returnS=returnS+ truncateToFiveDecimals(array[i]) + " ";
        }
        return returnS;
    }

    public void reduceRow(){
        double numbaToDivide=0;
        for(int i=0; i< array.length; i++){
            if(array[i]!=0){
                numbaToDivide=array[i];
                i=array.length+1;
            }
        }
        if(numbaToDivide!=0){
            for(int i=0; i<array.length; i++){
                array[i]/=numbaToDivide;
            }
        }
    }

    public void multiplyRow(double multiply){
        for(int i=0; i< array.length; i++){
            array[i]=array[i]*multiply;
        }
        operationsPeformed.concat("*"+multiply+" ");
    }
    public void divideRow(double multiply){
        for(int i=0; i< array.length; i++){
            array[i]=array[i]/multiply;
            operationsPeformed.concat("/"+multiply+" ");
        }
    }

    public static String truncateToFiveDecimals(double number){
        String numba= String.valueOf(number);
        String numbaBack="";
        final int TRUNCATEDNUMBERTO=6;
        if(numba.length()<TRUNCATEDNUMBERTO){
            for(int i=0; i<numba.length(); i++){
                numbaBack=numbaBack.concat(String.valueOf(numba.charAt(i)));
            }
            while(numbaBack.length()<TRUNCATEDNUMBERTO){
                numbaBack=numbaBack.concat("0");
            }
        } else{
            for(int i=0; i<TRUNCATEDNUMBERTO; i++){
                numbaBack=numbaBack.concat(String.valueOf(numba.charAt(i)));
            }
        }
        return numbaBack;
    }
}




