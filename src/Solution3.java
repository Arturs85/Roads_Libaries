import java.util.*;

public class Solution3 {
    //static Scanner sc = new Scanner(System.in);
//static cost()
    Scanner in = new Scanner(System.in);

    List<Road> readOneQuery(int m) {


        List<Road> allRoads = new LinkedList<>();

        for (int a1 = 0; a1 < m; a1++) {
            int city_1 = in.nextInt();
            int city_2 = in.nextInt();
            allRoads.add(new Road(city_1, city_2));

        }
        return allRoads;
    }

    List<Road> allInterconectedRoads(List<Road> allRoads) {
        List<Road> conectedRoads = new ArrayList<>();
        if (allRoads.size() < 1)
            return null;
        conectedRoads.add(allRoads.get(allRoads.size() - 1));//sāk ar  pēdējo no dotajiem celiem
        for (int times = 0; times < allRoads.size(); times++) { //lai nepalaistu garaam nevienu saistiito celu
            for (int i = 0; i < conectedRoads.size(); i++) {
                ListIterator<Road> iter = conectedRoads.listIterator();
               while(iter.hasNext()){// for (int j = 0; j < allRoads.size(); j++) {//salīdzina ar katru ceļu katrā tīklā
                   Road itm = iter.next();
                   if (conectedRoads.get(i).isConnected(itm)) {
                        conectedRoads.add(itm);  //pievieno visus no dtajiem celiem
                       iter.remove();
                       //allRoads.remove(itm);
                    }
                }
            }

        }
        return conectedRoads;
    }

    List<List<Road>> getConnectedRoadLists(List<Road> allRoads) {
        List<List<Road>> netList = new ArrayList<>(); //net list contains Lists of interconnected roads
        while (!allRoads.isEmpty()) {
            List<Road> interconectedRoads = allInterconectedRoads(allRoads);
            netList.add(interconectedRoads);
        }
            return netList;
        }


    public static void main(String[] args) {
        Solution3 solution3 = new Solution3();
        int q = solution3.in.nextInt();
        for (int a0 = 0; a0 < q; a0++) {
            int n = solution3.in.nextInt();
            int m = solution3.in.nextInt();
            long x = solution3.in.nextLong();
            long y = solution3.in.nextLong();
            List<Road> givenRoads = solution3.readOneQuery(m); //read m roads from input
            List<List<Road>> netList = solution3.getConnectedRoadLists(givenRoads);//
            int localNetsCount = netList.size();
         //   System.out.println("size: " + localNetsCount);

            if (x < y)//ceļi dārrgāki par libary -> katrā pilsētā libary, celi nav svarīgi
                System.out.println(x * n);

            else {//libary dārgāka par ceļu
                long sum = 0;
                int connectedCities = 0;
                for (List<Road> l : netList) { //how many cities is connected by every subnet
                    int citiyCount = 0;
                    Set<Integer> cities = new TreeSet<Integer>();
                    for (int i = 0; i < l.size(); i++) {
                        cities = l.get(i).addCity(cities);

                    }
                    connectedCities += cities.size();
                    sum += (cities.size() - 1) * y + x;
                }
                int loneCities = n - connectedCities;
                System.out.println(sum + loneCities * x);
            }
        }


    }



class Road {
    int x = 0;
    int y = 0;

    Road(int x, int y) {
        this.x = x;
        this.y = y;

    }

    boolean isConnected(Road road) {
        if (this.x == road.x || this.y == road.y || this.x == road.y || this.y == road.x)
            return true;
        else
            return false;

    }



    Set<Integer> addCity(Set<Integer> cities) {
        cities.add(x);
        cities.add(y);
        return cities;
    }
}
}

