import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Solution1 {

   // public native int intMethod(int a);

    public static void main(String[] args) {
        int indx=0;
        for (String s:args
             ) {
            System.out.println(indx++ + ". input "+s);
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = args[0];

//sc.hasNext("]");
       // try {
         //   int a = 0;
            // s = br.readLine();
            s.replaceAll(" ", "");
            Scanner sc = new Scanner(s);
        System.out.printf("%-15s%03d\n", "nme", 3089 );

        //sc.useDelimiter(",");
//sc.useDelimiter("[");
         //   while (true) {
           //     System.out.println(a++ + ".  " + sc.nextInt());


//            }

  //      } catch (Exception e) {
    //    }

        List<String> brackets = getSubstringBetweenDelim(s, '[', ']');
        List<String> vect1 = getSubstringBetweenDelim(brackets.get(0), ',');
        List<String> vect2 = getSubstringBetweenDelim(brackets.get(1), ',');
        // String[] vect2 =new String[]{"34","466","6657"};
        System.out.println("strings 1 ");

        for (String a : vect1) {
            System.out.print(a + " ");
        }
        System.out.println("strings 2 ");

        for (String a : vect2) {
            System.out.print(a + " ");
        }

        int[] v1 = StringArrayListtointArray(vect1);
        int[] v2 = StringArrayListtointArray(vect2);
        Arrays.stream(v1).average();
        System.out.println("Array 1: ");

        for (int a : v1) {
            System.out.println(a);
        }
        System.out.println("Array 2: ");

        for (int a : v2) {
            System.out.println(a);
        }
        double arg = ArrayProd(v1, v2) / (ArrayModul(v1) * ArrayModul(v2));
        double res = Math.acos(arg);
        DecimalFormat df = new DecimalFormat("0.0000");
        df.setRoundingMode(RoundingMode.DOWN);
        System.out.println(df.format(res));
        System.out.print(String.format("%.4f", res));

//[12,43,45][55,787,5453]   [3,2,0,5,0,0,0,2,0,0] [1,0,0,0,0,0,0,1,0,2]
    }


    static double ArrayModul(int[] v1) {
        int sum = 0;
        for (int a : v1) {
            sum += a * a;
        }
        double res = Math.sqrt((double) sum);
        return res;
    }

    static int ArrayProd(int[] a1, int[] a2) {
        int sum = 0;
        for (int a = 0; a < a1.length; a++) {

            sum += a1[a] * a2[a];
        }


        return sum;
    }

    static int[] StringArrayListtointArray(List<String> in) {
        int[] rez = new int[in.size()];
        for (int a = 0; a < rez.length; a++) {
            try {
                rez[a] = Integer.parseInt(in.get(a));

            } catch (Exception e) {
                System.out.println("Parse err-" + in.get(a) + "-");
//e.printStackTrace();
            }
        }

        return rez;
    }

    static List<String> getSubstringBetweenDelim(String s, char del1, char del2) {
        char[] data = s.toCharArray();
        ArrayList<String> res = new ArrayList<String>();//size
        //if (data[0]!=del1)
        //  return null;
        int b = 0;
        for (int i = 0; i < data.length; i++) {

            String str = new String();
            int a = 0;
            if (data[i] == del1) { //start reading subString
                i++;  // skip delimiter
                while (i < data.length && data[i] != del2) {
                    String add = String.valueOf(data[i++]);
                    str = str.concat(add);
                    if (data[i] == del2) {
                        res.add(str);
                        System.out.println(str);

                        break;
                    }
                }
            }


        }

        return res;
    }

    static ArrayList<String> getSubstringBetweenDelim(String s, char del1) {
        char[] data = s.toCharArray();
        ArrayList<String> res = new ArrayList<String>();//size
        //if (data[0]!=del1)
        //  return null;
        int b = 0;
        for (int i = 0; i < data.length; i++) {

            String str = new String();
            int a = 0;
            //start reading subString
            // skip delimiter
            while (i < data.length && data[i] != del1) {
                String add = String.valueOf(data[i++]);
                str = str.concat(add);
            }
            res.add(str);
            System.out.println(str);


        }

        return res;
    }
}
