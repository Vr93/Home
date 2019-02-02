package Server.Model;



public class AverageFilter {

    private  Float filterList[];

    public AverageFilter(int length){
        filterList = new Float[length];
        /* Initialize the list with 0's */
        for(int i = 0; i < filterList.length; i++){
            filterList[i] = 0.0f;
        }

    }

    /**
     * Inserts a value to the filter.
     * @param value, float
     */
    public void insertValue(float value){
        for(int i = filterList.length - 1; i >= 0; i--){
            if(i > 0) {
                filterList[i] = filterList[i - 1];
            }
            else{
                filterList[0] = value;
            }
        }
    }

    /**
     * Returns the average of all valid values in the filter list.
     * @return float
     */
    public float getAverageValue(){
        float sum = 0.0f;
        int amountOfValidNumbers = 0;
        for(Float f: getFilterList()){
            if(f > 0){
                amountOfValidNumbers++;
                sum += f;
            }
            else{
                break;
            }
        }
        return sum/amountOfValidNumbers;
    }

    private Float[] getFilterList() {
        return filterList;
    }


}
