package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors

public class Main {
    public static void main(String[] args) {
        ArrayList<TestCaseObj> testCaseObjs = new ArrayList<>();
        final long startTime = System.currentTimeMillis();
        int successCase = 0;
        int failCase = 0;

        try {
            readFile(testCaseObjs);
        }catch (NumberFormatException e) {
            System.out.println("Input should be integer");
            e.printStackTrace();
        }

        for (TestCaseObj tc: testCaseObjs) {
            try {
                int checkType;
                for (String str: tc.getInputs()) {
                    checkType = Integer.parseInt(str);
                }
            }catch (NumberFormatException e) {
                tc.setActual("Input Should Be Integer");
                tc.setErrCode(3);
                failCase++;
                continue;
            }

            int Xmax = Integer.parseInt(tc.getInputs()[0]);
            int Xmin = Integer.parseInt(tc.getInputs()[1]);
            int Ymax = Integer.parseInt(tc.getInputs()[2]);
            int Ymin = Integer.parseInt(tc.getInputs()[3]);
            int approach = Integer.parseInt(tc.getInputs()[4]);


            // check negative number and range
            if ((Xmax < 0) && (Xmin < 0) && (Ymax < 0) && (Ymin < 0)) {
                tc.setActual("Input Out Of Range");
                tc.setErrCode(1);
                failCase++;
                continue;
            }else if ((Xmax > 200) && (Xmin > 200) && (Ymax > 200) && (Ymin > 200)){
                tc.setActual("Input Out Of Range");
                tc.setErrCode(1);
                failCase++;
                continue;
            }

            // check max <= min
            if (Xmax <= Xmin) {
                tc.setActual("Xmax Should Be More Than Xmin");
                tc.setErrCode(4);
                failCase++;
                continue;
            }else if (Ymax <= Ymin) {
                tc.setActual("Ymax Should Be More Than Ymin");
                tc.setErrCode(5);
                failCase++;
                continue;
            }

            // validate text approach input
            if ((0 <= approach) && (approach < 4)) {
                tc.setActual("Approach Input Out Of Range");
                tc.setErrCode(2);
                failCase++;
                continue;
            }

            // find size of X and Y
            int sizeOfX = Xmax - (Xmin);
            int sizeOfY = Ymax - (Ymin);

            // set X and Y to array index
            String[][] graph = new String[sizeOfX+3][sizeOfY+3];

            // Test case print
            if(approach == 1 ){ // สร้าง test case สำหรับ วิธี BVA
//                System.out.println("There are all test cases as follows:(X,Y) ");
//                System.out.println("(" +Xmin+ "," + (Ymax + Ymin) / 2 + ")");//  ค่า Y ถ้ามี ทศนิยมจะปัดลง เพราะเป็นตัวแปร int
                graph[1][(sizeOfY / 2)+1] = "o";

//                System.out.printf("(%d,%d)\n",Xmin+1, (Ymax + Ymin) / 2);
                graph[2][(sizeOfY / 2)+1] = "o";

//                System.out.printf("(%d,%d)\n",(Xmax + Xmin) / 2, (Ymax + Ymin) / 2); // ถ้ามี ทศนิยม จะปัดลงทั้งคู่
                graph[(sizeOfX / 2)+1][(sizeOfY / 2)+1] = "o";

//                System.out.printf("(%d,%d)\n",Xmax - 1 , (Ymax + Ymin) / 2);
                graph[sizeOfX][(sizeOfY / 2)+1] = "o";

//                System.out.printf("(%d,%d)\n",Xmax , (Ymax + Ymin) / 2);
                graph[sizeOfX+1][(sizeOfY / 2)+1] = "o";

//                System.out.printf("(%d,%d)\n",(Xmax + Xmin) / 2, Ymin);
                graph[(sizeOfX / 2)+1][1] = "o";

//                System.out.printf("(%d,%d)\n",(Xmax + Xmin) / 2, Ymin+1);
                graph[(sizeOfX / 2)+1][2] = "o";

//                System.out.printf("(%d,%d)\n",(Xmax + Xmin) / 2, Ymax-1);
                graph[(sizeOfX / 2)+1][sizeOfY] = "o";

//                System.out.printf("(%d,%d)\n",(Xmax + Xmin) / 2, Ymax);
                graph[(sizeOfX / 2)+1][sizeOfY+1] = "o";
            }
            else if(approach == 2 ){
//                System.out.println("There are all test cases as follows:(X,Y) ");
//                System.out.printf("(%d,%d)",Xmin-1, (Ymax + Ymin) / 2);
                graph[0][(sizeOfY / 2)+1] = "o";

//                System.out.printf("(%d,%d)",Xmin, (Ymax + Ymin) / 2);//  ค่า Y ถ้ามี ทศนิยมจะปัดลง เพราะเป็นตัวแปร int
                graph[1][(sizeOfY / 2)+1] = "o";

//                System.out.printf("(%d,%d)\n",Xmin+1, (Ymax + Ymin) / 2);
                graph[2][(sizeOfY / 2)+1] = "o";

//                System.out.printf("(%d,%d)\n",(Xmax + Xmin) / 2, (Ymax + Ymin) / 2); // ถ้ามี ทศนิยม จะปัดลงทั้งคู่
                graph[(sizeOfX / 2)+1][(sizeOfY / 2)+1] = "o";

//                System.out.printf("(%d,%d)\n",Xmax - 1 , (Ymax + Ymin) / 2);
                graph[sizeOfX][(sizeOfY / 2)+1] = "o";

//                System.out.printf("(%d,%d)\n",Xmax , (Ymax + Ymin) / 2);
                graph[sizeOfX+1][(sizeOfY / 2)+1] = "o";

//                System.out.printf("(%d,%d)\n",Xmax+1 , (Ymax + Ymin) / 2);
                graph[sizeOfX+2][(sizeOfY / 2)+1] = "o";

//                System.out.printf("(%d,%d)\n",(Xmax + Xmin) / 2, Ymin-1);
                graph[(sizeOfX /2)+1][0] = "o";

//                System.out.printf("(%d,%d)\n",(Xmax + Xmin) / 2, Ymin);
                graph[(sizeOfX /2)+1][1] = "o";

//                System.out.printf("(%d,%d)\n",(Xmax + Xmin) / 2, Ymin+1);
                graph[(sizeOfX /2)+1][2] = "o";

//                System.out.printf("(%d,%d)\n",(Xmax + Xmin) / 2, Ymax-1);
                graph[(sizeOfX /2)+1][sizeOfY] = "o";

//                System.out.printf("(%d,%d)\n",(Xmax + Xmin) / 2, Ymax);
                graph[(sizeOfX /2)+1][sizeOfY+1] = "o";

//                System.out.printf("(%d,%d)\n",(Xmax + Xmin) / 2, Ymax+1);
                graph[(sizeOfX / 2)+1][sizeOfY+2] = "o";
            }
            else if(approach == 3){
//                System.out.println("There are all test cases as follows:(X,Y) ");
//                System.out.printf("(%d,%d)\n",Xmin, Ymin);
                graph[1][1] = "o";

//                System.out.printf("(%d,%d)\n",Xmin+1,Ymin);
                graph[2][1] = "o";

//                System.out.printf("(%d,%d)\n",Xmin,Ymin+1);
                graph[1][2] = "o";

//                System.out.printf("(%d,%d)\n",Xmin+1,Ymin+1);
                graph[2][2] = "o";

//                System.out.printf("(%d,%d)\n",Xmax, Ymin);
                graph[sizeOfX+1][1] = "o";

//                System.out.printf("(%d,%d)\n",Xmax,Ymin+1);
                graph[sizeOfX+1][2] = "o";

//                System.out.printf("(%d,%d)\n",Xmax-1,Ymin);
                graph[sizeOfX][1] = "o";

//                System.out.printf("(%d,%d)\n",Xmax-1,Ymin+1);
                graph[sizeOfX][2] = "o";

//                System.out.printf("(%d,%d)\n",Xmax, Ymax);
                graph[sizeOfX+1][sizeOfY+1] = "o";

//                System.out.printf("(%d,%d)\n",Xmax,Ymax-1);
                graph[sizeOfX+1][sizeOfY] = "o";

//                System.out.printf("(%d,%d)\n",Xmax-1,Ymax);
                graph[sizeOfX][sizeOfY+1] = "o";

//                System.out.printf("(%d,%d)\n",Xmax-1,Ymax-1);
                graph[sizeOfX][sizeOfY] = "o";

//                System.out.printf("(%d,%d)\n",Xmin, Ymax);
                graph[1][sizeOfY+1] = "o";

//                System.out.printf("(%d,%d)\n",Xmin,Ymax-1);
                graph[1][sizeOfY] = "o";

//                System.out.printf("(%d,%d)\n",Xmin+1,Ymax);
                graph[2][sizeOfY+1] = "o";

//                System.out.printf("(%d,%d)\n",Xmin+1,Ymax-1);
                graph[2][sizeOfY] = "o";

//                System.out.printf("(%d,%d)\n",Xmin, (Ymax + Ymin) / 2);//  ค่า Y ถ้ามี ทศนิยมจะปัดลง เพราะเป็นตัวแปร int
                graph[1][(sizeOfY / 2)+1] = "o";

//                System.out.printf("(%d,%d)\n",Xmin+1, (Ymax + Ymin) / 2);
                graph[2][(sizeOfY / 2)+1] = "o";

//                System.out.printf("(%d,%d)\n",(Xmax + Xmin) / 2, (Ymax + Ymin) / 2); // ถ้ามี ทศนิยม จะปัดลงทั้งคู่
                graph[(sizeOfX / 2)+1][(sizeOfY / 2)+1] = "o";

//                System.out.printf("(%d,%d)\n",Xmax - 1 , (Ymax + Ymin) / 2);
                graph[sizeOfX][(sizeOfY / 2)+1] = "o";

//                System.out.printf("(%d,%d)\n",Xmax , (Ymax + Ymin) / 2);
                graph[sizeOfX+1][(sizeOfY / 2)+1] = "o";

//                System.out.printf("(%d,%d)\n",(Xmax + Xmin) / 2, Ymin);
                graph[(sizeOfX / 2)+1][1] = "o";

//                System.out.printf("(%d,%d)\n",(Xmax + Xmin) / 2, Ymin+1);
                graph[(sizeOfX / 2)+1][2] = "o";

//                System.out.printf("(%d,%d)\n",(Xmax + Xmin) / 2, Ymax-1);
                graph[(sizeOfX / 2)+1][sizeOfY] = "o";

//                System.out.printf("(%d,%d)\n",(Xmax + Xmin) / 2, Ymax);
                graph[(sizeOfX / 2)+1][sizeOfY+1] = "o";

            }
            else if(approach == 4){
//                System.out.println("There are all test cases as follows:(X,Y) ");
//                System.out.printf("(%d,%d)\n",Xmin-1, Ymin-1);
                graph[0][0] = "o";

//                System.out.printf("(%d,%d)\n",Xmin, Ymin-1);
                graph[1][0] = "o";

//                System.out.printf("(%d,%d)\n",Xmin-1, Ymin);
                graph[0][1] = "o";

//                System.out.printf("(%d,%d)\n",Xmin, Ymin);
                graph[1][1] = "o";

//                System.out.printf("(%d,%d)\n",Xmin+1,Ymin);
                graph[2][1] = "o";

//                System.out.printf("(%d,%d)\n",Xmin,Ymin+1);
                graph[1][2] = "o";

//                System.out.printf("(%d,%d)\n",Xmin+1,Ymin+1);
                graph[2][2] = "o";

//                System.out.printf("(%d,%d)\n",Xmin+1,Ymin-1);
                graph[2][0] = "o";

//                System.out.printf("(%d,%d)\n",Xmin-1,Ymin+1);
                graph[0][2] = "o";

//                System.out.printf("(%d,%d)\n",Xmax+1, Ymin-1);
                graph[sizeOfX+2][0] = "o";

//                System.out.printf("(%d,%d)\n",Xmax, Ymin-1);
                graph[sizeOfX+1][0] = "o";

//                System.out.printf("(%d,%d)\n",Xmax+1, Ymin);
                graph[sizeOfX+2][1] = "o";

//                System.out.printf("(%d,%d)\n",Xmax, Ymin);
                graph[sizeOfX+1][1] = "o";

//                System.out.printf("(%d,%d)\n",Xmax,Ymin+1);
                graph[sizeOfX+1][2] = "o";

//                System.out.printf("(%d,%d)\n",Xmax-1,Ymin);
                graph[sizeOfX][1] = "o";

//                System.out.printf("(%d,%d)\n",Xmax-1,Ymin+1);
                graph[sizeOfX][2] = "o";

//                System.out.printf("(%d,%d)\n",Xmax+1, Ymin+1);
                graph[sizeOfX+2][2] = "o";

//                System.out.printf("(%d,%d)\n",Xmax-1, Ymin-1);
                graph[sizeOfX][0] = "o";

//                System.out.printf("(%d,%d)\n",Xmax+1, Ymax+1);
                graph[sizeOfX+2][sizeOfY+2] = "o";

//                System.out.printf("(%d,%d)\n",Xmax, Ymax+1);
                graph[sizeOfX+1][sizeOfY+2] = "o";

//                System.out.printf("(%d,%d)\n",Xmax+1, Ymax);
                graph[sizeOfX+2][sizeOfY+1] = "o";

//                System.out.printf("(%d,%d)\n",Xmax, Ymax);
                graph[sizeOfX+1][sizeOfY+1] = "o";

//                System.out.printf("(%d,%d)\n",Xmax,Ymax-1);
                graph[sizeOfX+1][sizeOfY] = "o";

//                System.out.printf("(%d,%d)\n",Xmax-1,Ymax);
                graph[sizeOfX][sizeOfY+1] = "o";

//                System.out.printf("(%d,%d)\n",Xmax-1,Ymax-1);
                graph[sizeOfX][sizeOfY] = "o";

//                System.out.printf("(%d,%d)\n",Xmax-1, Ymax+1);
                graph[sizeOfX][sizeOfY+2] = "o";

//                System.out.printf("(%d,%d)\n",Xmax+1, Ymax-1);
                graph[sizeOfX+2][sizeOfY] = "o";

//                System.out.printf("(%d,%d)\n",Xmin-1, Ymax+1);
                graph[0][sizeOfY+2] = "o";

//                System.out.printf("(%d,%d)\n",Xmin-1, Ymax);
                graph[0][sizeOfY+1] = "o";

//                System.out.printf("(%d,%d)\n",Xmin, Ymax+1);
                graph[1][sizeOfY+2] = "o";

//                System.out.printf("(%d,%d)\n",Xmin, Ymax);
                graph[1][sizeOfY+1] = "o";

//                System.out.printf("(%d,%d)\n",Xmin,Ymax-1);
                graph[1][sizeOfY] = "o";

//                System.out.printf("(%d,%d)\n",Xmin+1,Ymax);
                graph[2][sizeOfY+1] = "o";

//                System.out.printf("(%d,%d)\n",Xmin+1,Ymax-1);
                graph[2][sizeOfY] = "o";

//                System.out.printf("(%d,%d)\n",Xmin-1,Ymax-1);
                graph[0][sizeOfY] = "o";

//                System.out.printf("(%d,%d)\n",Xmin+1,Ymax+1);
                graph[2][sizeOfY+2] = "o";

//                System.out.printf("(%d,%d)\n",Xmin, (Ymax + Ymin) / 2);//  ค่า Y ถ้ามี ทศนิยมจะปัดลง เพราะเป็นตัวแปร int
                graph[1][(sizeOfY / 2)+1] = "o";

//                System.out.printf("(%d,%d)\n",Xmin+1, (Ymax + Ymin) / 2);
                graph[2][(sizeOfY / 2)+1] = "o";

//                System.out.printf("(%d,%d)\n",Xmin-1, (Ymax + Ymin) / 2); // Pond edit
                graph[0][(sizeOfY / 2)+1] = "o";

//                System.out.printf("(%d,%d)\n",(Xmax + Xmin) / 2, (Ymax + Ymin) / 2); // ถ้ามี ทศนิยม จะปัดลงทั้งคู่
                graph[(sizeOfX / 2)+1][(sizeOfY / 2)+1] = "o";

//                System.out.printf("(%d,%d)\n",Xmax - 1 , (Ymax + Ymin) / 2);
                graph[sizeOfX][(sizeOfY / 2)+1] = "o";

//                System.out.printf("(%d,%d)\n",Xmax , (Ymax + Ymin) / 2);
                graph[sizeOfX+1][(sizeOfY / 2)+1] = "o";

//                System.out.printf("(%d,%d)\n",Xmax+1 , (Ymax + Ymin) / 2); //Pond edit
                graph[sizeOfX+2][(sizeOfY / 2)+1] = "o";

//                System.out.printf("(%d,%d)\n",(Xmax + Xmin) / 2, Ymin);
                graph[(sizeOfX / 2)+1][1] = "o";

//                System.out.printf("(%d,%d)\n",(Xmax + Xmin) / 2, Ymin+1);
                graph[(sizeOfX / 2)+1][2] = "o";

//                System.out.printf("(%d,%d)\n",(Xmax + Xmin) / 2, Ymin-1);
                graph[(sizeOfX / 2)+1][0] = "o";

//                System.out.printf("(%d,%d)\n",(Xmax + Xmin) / 2, Ymax-1);
                graph[(sizeOfX / 2)+1][sizeOfY] = "o";

//                System.out.printf("(%d,%d)\n",(Xmax + Xmin) / 2, Ymax+1);
                graph[(sizeOfX / 2)+1][sizeOfY+2] = "o";

//                System.out.printf("(%d,%d)\n",(Xmax + Xmin) / 2, Ymax);
                graph[(sizeOfX / 2)+1][sizeOfY+1] = "o";

            }
//                System.out.printf("Input error.");


            // print graph

            for (int i = sizeOfX+2; i >= 0; i--) {
                for (int j = 0; j <= sizeOfY+2; j++) {
                    if (graph[i][j] == null){
//                        System.out.printf(" ");
                    }else {
//                        System.out.printf(graph[i][j]);
                    }

                }
//                System.out.printf("\n");
            }

            tc.setActual("Running Success");
            tc.setErrCode(0);
            successCase++;

        }
        final long endTime = System.currentTimeMillis();

        writeFile(testCaseObjs, successCase, failCase, startTime, endTime);

    }


    public static void readFile(ArrayList<TestCaseObj> tco) {
        try {
            File myObj = new File("input.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] dataArr = data.split(",");
                String[] dataInputs = {dataArr[1], dataArr[2], dataArr[3], dataArr[4], dataArr[5]};

                TestCaseObj t = new TestCaseObj(Integer.parseInt(dataArr[0]),dataInputs,dataArr[6]);
                tco.add(t);
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
    
    public static void writeFile(ArrayList<TestCaseObj> tco, int successCase, int failCase, long startTime, long endTime) {
        try {
            File myObj = new File("logFile.txt");
            myObj.createNewFile();

            FileWriter myWriter = new FileWriter("logFile.txt");
            myWriter.write("TestCaseNumber,(Xmax,Xmin) (Ymax,Ymin), Approach, Expected Result, Actual Result, Error Code\n");
            myWriter.write("0 = Running Success | 1 =  Input Out Of Range | 2 = Approach Input Out Of Range | 3 = Input Should Be Integer | 4 = Xmax Should Be More Than Xmin | 5 = Ymax Should Be More Than Ymin\n");
            myWriter.write("\n");
            for (TestCaseObj tc: tco) {
                //0,('a','b') (0,0),invalid type
                myWriter.write(tc.getTestCaseNum() + ",("
                        + tc.getInputs()[0] + "," + tc.getInputs()[1] + ") "
                        + "(" + tc.getInputs()[2] + "," + tc.getInputs()[3] + ")," + tc.getInputs()[4] + ","
                        + tc.getExpected() + "," + tc.getActual() + "," + tc.getErrCode() + "\n"
                );
            }
            myWriter.write("\n");
            myWriter.write("Start time: " + startTime + " milliseconds" +", End time: " + endTime + " milliseconds" +", Total time: " + (endTime - startTime) + " milliseconds" + "\n");
            myWriter.write("Success Cases: " + successCase + ", Fail Cases: " + failCase);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


}




