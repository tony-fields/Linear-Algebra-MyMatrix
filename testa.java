public class testa {

    public static void main(String[] args){
        int[] nums= {1,2,3,4,5};
        char[] chars= new char[13];
        chars[0]='c';
        //productExceptSelf(nums);
        System.out.println(chars.length);
    }
    public static int[] productExceptSelf(int[] nums) {
        int length=nums.length;
        int[] previousNums= new int[nums.length];
        int[] postNums= new int[nums.length];
        int[] answer= new int[nums.length];
        previousNums[0]=nums[0];
        postNums[length-1]=nums[length-1];
        for(int i=1; i<nums.length; i++){
            previousNums[i]=previousNums[i-1]*nums[i];
            postNums[length-i-1]=postNums[length-i]*nums[length-i-1];
        }
        answer[0]=postNums[1];
        answer[length-1]=previousNums[length-2];
        for(int i=1; i<nums.length-1;i++){
            answer[i]=previousNums[i-1]*postNums[i+1];
        }


        System.out.println("This is previousNums:");
        for(int value: previousNums){
            System.out.println(value);
        }
        System.out.println("This is postNums:");
        for(int value: postNums){
            System.out.println(value);
        }
        System.out.println("This is answer:");
        for(int value: answer){
            System.out.println(value);
        }
        return answer;
    }



}
