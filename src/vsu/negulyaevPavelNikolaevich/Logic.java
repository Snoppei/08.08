package vsu.negulyaevPavelNikolaevich;

import util.ArrayUtils;

import java.io.FileNotFoundException;

public class Logic {
    public static int[][] sort(int[][] matrix){ // сортировка выполнена методом "пузырька" при помощи вспомогательной функции compareCols
        for(int i = matrix.length-1; i > 0; i--){
            for(int j = 0; j < i; j++){
                if(compareCols(matrix, j, j+1)==1){
                    int[] tmpArr = matrix[j];
                    matrix[j] = matrix[j+1];
                    matrix[j+1] = tmpArr;
                }

            }
        }
        return matrix;
    }
    public static int compareCols(int[][] matrix, int index1, int index2){
        for(int i = 0; i < matrix[0].length; i++){
        if(matrix[index1][i] > matrix[index2][i]) return 1;
        if(matrix[index1][i] < matrix[index2][i]) return -1;
        }
        if(matrix[index1] == matrix[index2]) return 0;
        return 0;
    }
    public static void writeIntArray2ToConsole(int[][] IntArray){ // debugged
        for(int rows = 0; rows < IntArray.length; rows++){
            for(int cols = 0; cols < IntArray[0].length; cols++){
                System.out.print(IntArray[rows][cols] + " ");
                if(cols == IntArray[0].length-1){
                    System.out.println();
                }
            }
        }
    }
    public static int[][] readIntArray2FromFile(String fileName) {
        try {
            return ArrayUtils.toIntArray2(ArrayUtils.readLinesFromFile(fileName));
        }
        catch(FileNotFoundException e) {
            return null;
        }
    }
}
