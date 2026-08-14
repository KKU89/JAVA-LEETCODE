import java.util.*;
class Solution{
    public List<List<Integer>> generate(int numRows){
        List<List<Integer>> kk=new ArrayList<>();
        if(numRows==0){
            return kk;
        }
        for(int i=0;i<numRows;i++){
            List<Integer> row=new ArrayList<>();
            if(i==0){
                row.add(1);
            }else{
                List<Integer> prev=kk.get(i-1);
                row.add(1);
                for(int j=0;j<prev.size()-1;j++){
                    row.add(prev.get(j)+prev.get(j+1));
                }
                row.add(1);
            }
            kk.add(row);
        }
        return kk;
    }
}