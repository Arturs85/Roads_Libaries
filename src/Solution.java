import java.util.*;

public class Solution {

    boolean[] allCitiesCheckList; //checked=true
    City[] allCities;
    List<Integer> disjoints;
    int loneCitiesCount = 0;

//static Scanner sc = new Scanner(System.in);
//static cost()

    Road[] readOneQuery(int m, Scanner in) {


        Road[] allRoads = new Road[m];

        for (int a1 = 0; a1 < m; a1++) {
            int city_1 = in.nextInt();
            int city_2 = in.nextInt();
            allRoads[a1] = new Road(city_1, city_2);

        }
        return allRoads;
    }

    void findDisjoints(int citiesCount) {
        disjoints = new ArrayList<>();
        for (int i = 0; i < citiesCount; i++) {
            if (allCitiesCheckList[i] == false)//ja pilseeta nav vell pievienota
            {
                allCitiesCheckList[i] = true;
                int size = allCities[i].checkAllConnectedCities() + 1;// +1 to add root city
                if (size == 1)//ja nav saistiitu pilseetu
                    loneCitiesCount++;
                else
                    disjoints.add(size);

            }
        }

    }

    void atrastPilsetas(Road[] givenRoads, int n) {
        allCitiesCheckList = new boolean[n];
        Arrays.fill(allCitiesCheckList, false);
        int a = 0;
        for (int i = 1; i <= n; i++) {  //paarbauda visus pilsetu indeksus
            Set<Integer> conCityIndexes = new TreeSet<>();

            //  while (a<givenRoads.length&&givenRoads[a].x == i) //kameer taam atbilst izejos cels
            //{
            //  conCityIndexes.add(givenRoads[a].y);
            //  a++;
            //}
            allCities[i - 1] = (new City(i, conCityIndexes));

        }


        for (int i = 0; i < givenRoads.length; i++) {  //paarbauda visus pilsetu indeksus no preteejas puses

            int city = givenRoads[i].y;
            int child = givenRoads[i].x;
            allCities[city - 1].connectedCities.add(child);
            allCities[child - 1].connectedCities.add(city);

        }
    }

    public static void main(String[] args) {
        // Solution solution3 = new Solution();
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for (int a0 = 0; a0 < q; a0++) {
            Solution solution3 = new Solution();

            int n = in.nextInt();
            int m = in.nextInt();
            long x = in.nextLong();
            long y = in.nextLong();
            solution3.allCities = new City[n];
            Road[] givenRoads = solution3.readOneQuery(m, in); //read m roads from input
            // Arrays.sort(givenRoads);
            //Collections.sort(givenRoads);
            // for (Road r : givenRoads) {
            //     //System.out.println(r.toString());
            //  }

            solution3.atrastPilsetas(givenRoads, n);
            long sum = 0;
            // for (City c :solution3.allCities) {
            //}

            solution3.findDisjoints(n);

            for (Integer i : solution3.disjoints) {
                //  System.out.println("Disjoint size: "+i);
                sum += (i - 1) * y + x;
            }
//System.out.println("LoneCities count:" +solution3.loneCitiesCount);

            if (x < y)//ceļi dārrgāki par libary -> katrā pilsētā libary, celi nav svarīgi
                System.out.println(x * n);
            else {//libary dārgāka par ceļu
                System.out.println(sum + solution3.loneCitiesCount * x);
            }
        }


    }

    class City {
        int index;
        Set<Integer> connectedCities;

        City(int index, Set<Integer> connectedCities) {
            this.connectedCities = connectedCities;
            this.index = index;
        }

        @Override
        public String toString() {
            return new String("City " + index + ". Blakusvirsotnes: " + connectedCities.size());
        }

        int checkAllConnectedCities() {
            // allCitiesCheckList[index-1] = true; //atziimee kaa paarmekletu

            int foundCitiesCount = 0;
                for (Integer c : connectedCities) {

                    if (allCitiesCheckList[c - 1] == false)//ja pilseetai veel nav paarbaudiiti izejosie celi
                    {
                        allCitiesCheckList[c - 1] = true; //atziimee kaa paarmekletu
                        int a = allCities[c - 1].checkAllConnectedCities();  // recursion
                        foundCitiesCount++;
                        foundCitiesCount += a;
                    }
                }
            return foundCitiesCount;  //returns total count of conected elements in disjoint
        }

    }

    class Road implements Comparable<Road> {
        int x = 0;
        int y = 0;

        Road(int x, int y) {
            if (x < y) {
                this.x = x;  //lai buutu sakartots saraksts
                this.y = y;
            } else {
                this.x = y;
                this.y = x;
            }
        }

        boolean isConnected(Road road) {
            if (this.x == road.x || this.y == road.y || this.x == road.y || this.y == road.x)
                return true;
            else
                return false;
        }

        @Override
        public String toString() {
            //return super.toString();
            return new String("x: " + x + "y: " + y);
        }

        @Override
        public int compareTo(Road o) {
            if (x > o.x)
                return 1;
            else
                return -1;
        }
    }
}


