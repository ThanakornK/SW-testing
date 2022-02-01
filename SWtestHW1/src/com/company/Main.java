package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.lang.Math;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> inputs = new ArrayList<String>();
        String[] inputsName = {"Xmax", "Xmin", "Ymax", "Ymin"};
        for (int i = 0; i < 5; i++) {
            System.out.printf("Press %d",inputsName[i]);
            inputs.add(sc.nextLine());
        }
        try {
            int checkType;
            for (String str: inputs) {
                checkType = Integer.parseInt(str);
            }
        }catch (NumberFormatException e){
            System.out.println("Input should be integer");
        }

        int Xmax = Integer.parseInt(inputs.get(0));
        int Xmin = Integer.parseInt(inputs.get(1));
        int Ymax = Integer.parseInt(inputs.get(2));
        int Ymin = Integer.parseInt(inputs.get(3));
        int approach = Integer.parseInt(inputs.get(4));


        // check negative number and range
        if ((Xmax < 0) && (Xmin < 0) && (Ymax < 0) && (Ymin < 0)) {
            System.out.printf("Input shouldn't be negative number");
            System.exit(0);
        }else if ((Xmax > 200) && (Xmin > 200) && (Ymax > 200) && (Ymin > 200)){
            System.out.printf("Input out of range");
            System.exit(0);
        }

        // check max <= min
        if (Xmax <= Xmin) {
            System.out.printf("Xmax should be more than Xmin");
            System.exit(0);
        }else if (Ymax <= Ymin) {
            System.out.printf("Ymax should be more than Ymin");
            System.exit(0);
        }

        System.out.println("Choose a test approach : 1 = BVA | 2 = Robustness | 3 = Worse case | 4 = Worse case for robustness");
//        int approach  = sc.nextInt();

        // validate text approach input
        if ((0 <= approach) && (approach < 4)) {
            System.out.printf("Input out of range");
            System.exit(0);
        }

        // find size of X and Y
        int sizeOfX = Xmax - (Xmin);
        int sizeOfY = Ymax - (Ymin);

        // set X and Y to array index
        String[][] graph = new String[sizeOfX+3][sizeOfY+3];

        // create line of graph
        for (int i = 0; i < sizeOfX+3; i++) {
            for (int j = 0; j < sizeOfY+3; j++) {
                if ((i == 1 && j == 1) || (i == sizeOfX+1 && j == sizeOfY+1)) {         // if (0,0) or (Xmax, Ymax)
                    graph[i][j] = "+";
                }else if ((i == 1 && j == sizeOfY+1) || (i == sizeOfX+1 && j == 1)) {   // if (0,Ymax) or (Xmax,0)
                    graph[i][j] = "+";
                }else if ((i == 1) || (i == sizeOfX+1)) {     // if (x,0) or (x,Ymax)
                    graph[i][j] = "-";
                }else if ((j == 1 )|| (j == sizeOfY+1)) {
                    graph[i][j] = "|";                      // if (0,y) or (Xmax,0)
                }
            }
        }

        System.out.printf("\n");

        // Test case print
        if(approach == 1 ){ // สร้าง test case สำหรับ วิธี BVA
            System.out.println("There are all test cases as follows:(X,Y) ");
            System.out.println("(" +Xmin+ "," + (Ymax + Ymin) / 2 + ")");//  ค่า Y ถ้ามี ทศนิยมจะปัดลง เพราะเป็นตัวแปร int
            graph[1][(sizeOfY / 2)+1] = "o";

            System.out.printf("(%d,%d)\n",Xmin+1, (Ymax + Ymin) / 2);
            graph[2][(sizeOfY / 2)+1] = "o";

            System.out.printf("(%d,%d)\n",(Xmax + Xmin) / 2, (Ymax + Ymin) / 2); // ถ้ามี ทศนิยม จะปัดลงทั้งคู่
            graph[(sizeOfX / 2)+1][(sizeOfY / 2)+1] = "o";

            System.out.printf("(%d,%d)\n",Xmax - 1 , (Ymax + Ymin) / 2);
            graph[sizeOfX][(sizeOfY / 2)+1] = "o";

            System.out.printf("(%d,%d)\n",Xmax , (Ymax + Ymin) / 2);
            graph[sizeOfX+1][(sizeOfY / 2)+1] = "o";

            System.out.printf("(%d,%d)\n",(Xmax + Xmin) / 2, Ymin);
            graph[(sizeOfX / 2)+1][1] = "o";

            System.out.printf("(%d,%d)\n",(Xmax + Xmin) / 2, Ymin+1);
            graph[(sizeOfX / 2)+1][2] = "o";

            System.out.printf("(%d,%d)\n",(Xmax + Xmin) / 2, Ymax-1);
            graph[(sizeOfX / 2)+1][sizeOfY] = "o";

            System.out.printf("(%d,%d)\n",(Xmax + Xmin) / 2, Ymax);
            graph[(sizeOfX / 2)+1][sizeOfY+1] = "o";
        }
        else if(approach == 2 ){
            System.out.println("There are all test cases as follows:(X,Y) ");
            System.out.printf("(%d,%d)",Xmin-1, (Ymax + Ymin) / 2);
            graph[0][(sizeOfY / 2)+1] = "o";

            System.out.printf("(%d,%d)",Xmin, (Ymax + Ymin) / 2);//  ค่า Y ถ้ามี ทศนิยมจะปัดลง เพราะเป็นตัวแปร int
            graph[1][(sizeOfY / 2)+1] = "o";

            System.out.printf("(%d,%d)\n",Xmin+1, (Ymax + Ymin) / 2);
            graph[2][(sizeOfY / 2)+1] = "o";

            System.out.printf("(%d,%d)\n",(Xmax + Xmin) / 2, (Ymax + Ymin) / 2); // ถ้ามี ทศนิยม จะปัดลงทั้งคู่
            graph[(sizeOfX / 2)+1][(sizeOfY / 2)+1] = "o";

            System.out.printf("(%d,%d)\n",Xmax - 1 , (Ymax + Ymin) / 2);
            graph[sizeOfX][(sizeOfY / 2)+1] = "o";

            System.out.printf("(%d,%d)\n",Xmax , (Ymax + Ymin) / 2);
            graph[sizeOfX+1][(sizeOfY / 2)+1] = "o";

            System.out.printf("(%d,%d)\n",Xmax+1 , (Ymax + Ymin) / 2);
            graph[sizeOfX+2][(sizeOfY / 2)+1] = "o";

            System.out.printf("(%d,%d)\n",(Xmax + Xmin) / 2, Ymin-1);
            graph[(sizeOfX /2)+1][0] = "o";

            System.out.printf("(%d,%d)\n",(Xmax + Xmin) / 2, Ymin);
            graph[(sizeOfX /2)+1][1] = "o";

            System.out.printf("(%d,%d)\n",(Xmax + Xmin) / 2, Ymin+1);
            graph[(sizeOfX /2)+1][2] = "o";

            System.out.printf("(%d,%d)\n",(Xmax + Xmin) / 2, Ymax-1);
            graph[(sizeOfX /2)+1][sizeOfY] = "o";

            System.out.printf("(%d,%d)\n",(Xmax + Xmin) / 2, Ymax);
            graph[(sizeOfX /2)+1][sizeOfY+1] = "o";

            System.out.printf("(%d,%d)\n",(Xmax + Xmin) / 2, Ymax+1);
            graph[(sizeOfX / 2)+1][sizeOfY+2] = "o";
        }
        else if(approach == 3){
            System.out.println("There are all test cases as follows:(X,Y) ");
            System.out.printf("(%d,%d)\n",Xmin, Ymin);
            graph[1][1] = "o";

            System.out.printf("(%d,%d)\n",Xmin+1,Ymin);
            graph[2][1] = "o";

            System.out.printf("(%d,%d)\n",Xmin,Ymin+1);
            graph[1][2] = "o";

            System.out.printf("(%d,%d)\n",Xmin+1,Ymin+1);
            graph[2][2] = "o";

            System.out.printf("(%d,%d)\n",Xmax, Ymin);
            graph[sizeOfX+1][1] = "o";

            System.out.printf("(%d,%d)\n",Xmax,Ymin+1);
            graph[sizeOfX+1][2] = "o";

            System.out.printf("(%d,%d)\n",Xmax-1,Ymin);
            graph[sizeOfX][1] = "o";

            System.out.printf("(%d,%d)\n",Xmax-1,Ymin+1);
            graph[sizeOfX][2] = "o";

            System.out.printf("(%d,%d)\n",Xmax, Ymax);
            graph[sizeOfX+1][sizeOfY+1] = "o";

            System.out.printf("(%d,%d)\n",Xmax,Ymax-1);
            graph[sizeOfX+1][sizeOfY] = "o";

            System.out.printf("(%d,%d)\n",Xmax-1,Ymax);
            graph[sizeOfX][sizeOfY+1] = "o";

            System.out.printf("(%d,%d)\n",Xmax-1,Ymax-1);
            graph[sizeOfX][sizeOfY] = "o";

            System.out.printf("(%d,%d)\n",Xmin, Ymax);
            graph[1][sizeOfY+1] = "o";

            System.out.printf("(%d,%d)\n",Xmin,Ymax-1);
            graph[1][sizeOfY] = "o";

            System.out.printf("(%d,%d)\n",Xmin+1,Ymax);
            graph[2][sizeOfY+1] = "o";

            System.out.printf("(%d,%d)\n",Xmin+1,Ymax-1);
            graph[2][sizeOfY] = "o";

            System.out.printf("(%d,%d)\n",Xmin, (Ymax + Ymin) / 2);//  ค่า Y ถ้ามี ทศนิยมจะปัดลง เพราะเป็นตัวแปร int
            graph[1][(sizeOfY / 2)+1] = "o";

            System.out.printf("(%d,%d)\n",Xmin+1, (Ymax + Ymin) / 2);
            graph[2][(sizeOfY / 2)+1] = "o";

            System.out.printf("(%d,%d)\n",(Xmax + Xmin) / 2, (Ymax + Ymin) / 2); // ถ้ามี ทศนิยม จะปัดลงทั้งคู่
            graph[(sizeOfX / 2)+1][(sizeOfY / 2)+1] = "o";

            System.out.printf("(%d,%d)\n",Xmax - 1 , (Ymax + Ymin) / 2);
            graph[sizeOfX][(sizeOfY / 2)+1] = "o";

            System.out.printf("(%d,%d)\n",Xmax , (Ymax + Ymin) / 2);
            graph[sizeOfX+1][(sizeOfY / 2)+1] = "o";

            System.out.printf("(%d,%d)\n",(Xmax + Xmin) / 2, Ymin);
            graph[(sizeOfX / 2)+1][1] = "o";

            System.out.printf("(%d,%d)\n",(Xmax + Xmin) / 2, Ymin+1);
            graph[(sizeOfX / 2)+1][2] = "o";

            System.out.printf("(%d,%d)\n",(Xmax + Xmin) / 2, Ymax-1);
            graph[(sizeOfX / 2)+1][sizeOfY] = "o";

            System.out.printf("(%d,%d)\n",(Xmax + Xmin) / 2, Ymax);
            graph[(sizeOfX / 2)+1][sizeOfY+1] = "o";

        }
        else if(approach == 4){
            System.out.println("There are all test cases as follows:(X,Y) ");
            System.out.printf("(%d,%d)\n",Xmin-1, Ymin-1);
            graph[0][0] = "o";

            System.out.printf("(%d,%d)\n",Xmin, Ymin-1);
            graph[1][0] = "o";

            System.out.printf("(%d,%d)\n",Xmin-1, Ymin);
            graph[0][1] = "o";

            System.out.printf("(%d,%d)\n",Xmin, Ymin);
            graph[1][1] = "o";

            System.out.printf("(%d,%d)\n",Xmin+1,Ymin);
            graph[2][1] = "o";

            System.out.printf("(%d,%d)\n",Xmin,Ymin+1);
            graph[1][2] = "o";

            System.out.printf("(%d,%d)\n",Xmin+1,Ymin+1);
            graph[2][2] = "o";

            System.out.printf("(%d,%d)\n",Xmin+1,Ymin-1);
            graph[2][0] = "o";

            System.out.printf("(%d,%d)\n",Xmin-1,Ymin+1);
            graph[0][2] = "o";

            System.out.printf("(%d,%d)\n",Xmax+1, Ymin-1);
            graph[sizeOfX+2][0] = "o";

            System.out.printf("(%d,%d)\n",Xmax, Ymin-1);
            graph[sizeOfX+1][0] = "o";

            System.out.printf("(%d,%d)\n",Xmax+1, Ymin);
            graph[sizeOfX+2][1] = "o";

            System.out.printf("(%d,%d)\n",Xmax, Ymin);
            graph[sizeOfX+1][1] = "o";

            System.out.printf("(%d,%d)\n",Xmax,Ymin+1);
            graph[sizeOfX+1][2] = "o";

            System.out.printf("(%d,%d)\n",Xmax-1,Ymin);
            graph[sizeOfX][1] = "o";

            System.out.printf("(%d,%d)\n",Xmax-1,Ymin+1);
            graph[sizeOfX][2] = "o";

            System.out.printf("(%d,%d)\n",Xmax+1, Ymin+1);
            graph[sizeOfX+2][2] = "o";

            System.out.printf("(%d,%d)\n",Xmax-1, Ymin-1);
            graph[sizeOfX][0] = "o";

            System.out.printf("(%d,%d)\n",Xmax+1, Ymax+1);
            graph[sizeOfX+2][sizeOfY+2] = "o";

            System.out.printf("(%d,%d)\n",Xmax, Ymax+1);
            graph[sizeOfX+1][sizeOfY+2] = "o";

            System.out.printf("(%d,%d)\n",Xmax+1, Ymax);
            graph[sizeOfX+2][sizeOfY+1] = "o";

            System.out.printf("(%d,%d)\n",Xmax, Ymax);
            graph[sizeOfX+1][sizeOfY+1] = "o";

            System.out.printf("(%d,%d)\n",Xmax,Ymax-1);
            graph[sizeOfX+1][sizeOfY] = "o";

            System.out.printf("(%d,%d)\n",Xmax-1,Ymax);
            graph[sizeOfX][sizeOfY+1] = "o";

            System.out.printf("(%d,%d)\n",Xmax-1,Ymax-1);
            graph[sizeOfX][sizeOfY] = "o";

            System.out.printf("(%d,%d)\n",Xmax-1, Ymax+1);
            graph[sizeOfX][sizeOfY+2] = "o";

            System.out.printf("(%d,%d)\n",Xmax+1, Ymax-1);
            graph[sizeOfX+2][sizeOfY] = "o";

            System.out.printf("(%d,%d)\n",Xmin-1, Ymax+1);
            graph[0][sizeOfY+2] = "o";

            System.out.printf("(%d,%d)\n",Xmin-1, Ymax);
            graph[0][sizeOfY+1] = "o";

            System.out.printf("(%d,%d)\n",Xmin, Ymax+1);
            graph[1][sizeOfY+2] = "o";

            System.out.printf("(%d,%d)\n",Xmin, Ymax);
            graph[1][sizeOfY+1] = "o";

            System.out.printf("(%d,%d)\n",Xmin,Ymax-1);
            graph[1][sizeOfY] = "o";

            System.out.printf("(%d,%d)\n",Xmin+1,Ymax);
            graph[2][sizeOfY+1] = "o";

            System.out.printf("(%d,%d)\n",Xmin+1,Ymax-1);
            graph[2][sizeOfY] = "o";

            System.out.printf("(%d,%d)\n",Xmin-1,Ymax-1);
            graph[0][sizeOfY] = "o";

            System.out.printf("(%d,%d)\n",Xmin+1,Ymax+1);
            graph[2][sizeOfY+2] = "o";

            System.out.printf("(%d,%d)\n",Xmin, (Ymax + Ymin) / 2);//  ค่า Y ถ้ามี ทศนิยมจะปัดลง เพราะเป็นตัวแปร int
            graph[1][(sizeOfY / 2)+1] = "o";

            System.out.printf("(%d,%d)\n",Xmin+1, (Ymax + Ymin) / 2);
            graph[2][(sizeOfY / 2)+1] = "o";

            System.out.printf("(%d,%d)\n",Xmin-1, (Ymax + Ymin) / 2); // Pond edit
            graph[0][(sizeOfY / 2)+1] = "o";

            System.out.printf("(%d,%d)\n",(Xmax + Xmin) / 2, (Ymax + Ymin) / 2); // ถ้ามี ทศนิยม จะปัดลงทั้งคู่
            graph[(sizeOfX / 2)+1][(sizeOfY / 2)+1] = "o";

            System.out.printf("(%d,%d)\n",Xmax - 1 , (Ymax + Ymin) / 2);
            graph[sizeOfX][(sizeOfY / 2)+1] = "o";

            System.out.printf("(%d,%d)\n",Xmax , (Ymax + Ymin) / 2);
            graph[sizeOfX+1][(sizeOfY / 2)+1] = "o";

            System.out.printf("(%d,%d)\n",Xmax+1 , (Ymax + Ymin) / 2); //Pond edit
            graph[sizeOfX+2][(sizeOfY / 2)+1] = "o";

            System.out.printf("(%d,%d)\n",(Xmax + Xmin) / 2, Ymin);
            graph[(sizeOfX / 2)+1][1] = "o";

            System.out.printf("(%d,%d)\n",(Xmax + Xmin) / 2, Ymin+1);
            graph[(sizeOfX / 2)+1][2] = "o";

            System.out.printf("(%d,%d)\n",(Xmax + Xmin) / 2, Ymin-1);
            graph[(sizeOfX / 2)+1][0] = "o";

            System.out.printf("(%d,%d)\n",(Xmax + Xmin) / 2, Ymax-1);
            graph[(sizeOfX / 2)+1][sizeOfY] = "o";

            System.out.printf("(%d,%d)\n",(Xmax + Xmin) / 2, Ymax+1);
            graph[(sizeOfX / 2)+1][sizeOfY+2] = "o";

            System.out.printf("(%d,%d)\n",(Xmax + Xmin) / 2, Ymax);
            graph[(sizeOfX / 2)+1][sizeOfY+1] = "o";

        }else {
            System.out.printf("Input error.");
        }

        // print graph

        for (int i = sizeOfX+2; i >= 0; i--) {
            for (int j = 0; j <= sizeOfY+2; j++) {
                if (graph[i][j] == null){
                    System.out.printf(" ");
                }else {
                    System.out.printf(graph[i][j]);
                }

            }
            System.out.printf("\n");
        }


    }


    public void readFile(TestCaseObj[] tco) {
        try {
            File myObj = new File("input.txt");
            Scanner myReader = new Scanner(myObj);
            int index = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] dataArr = data.split(",");

                int[] dataInputs = {Integer.parseInt(dataArr[1]),Integer.parseInt(dataArr[2]),Integer.parseInt(dataArr[3]),Integer.parseInt(dataArr[4])};

                TestCaseObj t = new TestCaseObj(Integer.parseInt(dataArr[0]),dataInputs,dataArr[5]);
                tco[index] = t;
                index++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Input should be integer");
            e.printStackTrace();
        }
    }
    
    public void writeFile(TestCaseObj[] tco) {
        try {
            File myObj = new File("filename.txt");
            myObj.createNewFile();

            FileWriter myWriter = new FileWriter("filename.txt");
            myWriter.write("TestCaseNumber,(Xmax,Xmin) (Ymax,Ymin), Expected Result, Actual Result, Error Code\n");
            myWriter.write("0 = Running Success | 1 =  Input Out Of Range | 2 = Input Should Be Integer | 3 = Xmax Should Be More Than Xmin | 4 = Ymax Should Be More Than Ymin\n");
            for (TestCaseObj tc: tco) {
                //0,('a','b') (0,0),invalid type
                myWriter.write(tc.getTestCaseNum() + ",("
                        + tc.getInputs()[0] + "," + tc.getInputs()[1] + ") "
                        + "(" + tc.getInputs()[2] + "," + tc.getInputs()[3] + "),"
                        + tc.getExpected() + "," + tc.getActual() + "," + tc.getErrCode() + "\n"
                );
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


}




