package com.company;

import java.util.Scanner;
import java.lang.Math;
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Press Xmax");
        int Xmax = sc.nextInt();
        System.out.println("Press Xmin");
        int Xmin = sc.nextInt();
        System.out.println("Press Ymax");
        int Ymax = sc.nextInt();
        System.out.println("Press Ymin");
        int Ymin = sc.nextInt();

        // check negative number
        if ((Xmax < 0) || (Xmin < 0) || (Ymax < 0) || (Ymin < 0)) {
            System.out.printf("Input should't be negative number");
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
        int approch  = sc.nextInt();

        // validate text approach input
        if ( (0 <= approch) || (approch > 4)) {
            System.out.printf("Error test approach input");
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
        if(approch == 1 ){ // สร้าง test case สำหรับ วิธี BVA
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
        else if(approch == 2 ){
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
        else if(approch == 3){
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
        else if(approch == 4){
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
}
