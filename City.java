import java.util.*;

class City {
    int id;
    String name;
    Map<City, Integer> roads;

    City(int id, String name) {
        this.id = id;
        this.name = name;
        this.roads = new HashMap<>();
    }
}

class CityNavigationSystem {
    private Map<Integer, City> cityMap;

    public CityNavigationSystem() {
        cityMap = new HashMap<>();
    }

    public void addCity(int id, String name) {
        if (!cityMap.containsKey(id)) {
            cityMap.put(id, new City(id, name));
        }
    }

    public void addRoad(int id1, int id2, int distance) {
        City city1 = cityMap.get(id1);
        City city2 = cityMap.get(id2);
        if (city1 != null && city2 != null) {
            city1.roads.put(city2, distance);
            city2.roads.put(city1, distance);
        }
    }

    public List<City> findShortestPath(int startId, int endId) {
        if (!cityMap.containsKey(startId) || !cityMap.containsKey(endId)) {
            return Collections.emptyList();
        }

        City startCity = cityMap.get(startId);
        City endCity = cityMap.get(endId);

        // Dijkstra's Algorithm
        Map<City, Integer> distances = new HashMap<>();
        Map<City, City> previous = new HashMap<>();
        PriorityQueue<City> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(distances::get));

        for (City city : cityMap.values()) {
            distances.put(city, Integer.MAX_VALUE);
            previous.put(city, null);
        }

        distances.put(startCity, 0);
        priorityQueue.add(startCity);

        while (!priorityQueue.isEmpty()) {
            City currentCity = priorityQueue.poll();

            if (currentCity == endCity) {
                break;
            }

            for (Map.Entry<City, Integer> road : currentCity.roads.entrySet()) {
                City neighbor = road.getKey();
                int newDist = distances.get(currentCity) + road.getValue();

                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    previous.put(neighbor, currentCity);
                    priorityQueue.add(neighbor);
                }
            }
        }

        // Reconstruct the shortest path
        List<City> path = new LinkedList<>();
        for (City at = endCity; at != null; at = previous.get(at)) {
            path.add(0, at);
        }

        if (path.isEmpty() || path.get(0) != startCity) {
            return Collections.emptyList(); // No path found
        }

        return path;
    }

    public boolean detectCycle() {
        Set<City> visited = new HashSet<>();

        for (City city : cityMap.values()) {
            if (!visited.contains(city)) {
                if (detectCycleDFS(city, null, visited)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean detectCycleDFS(City current, City parent, Set<City> visited) {
        visited.add(current);

        for (City neighbor : current.roads.keySet()) {
            if (!visited.contains(neighbor)) {
                if (detectCycleDFS(neighbor, current, visited)) {
                    return true;
                }
            } else if (neighbor != parent) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CityNavigationSystem system = new CityNavigationSystem();

        while (true) {
            System.out.println("\nCity Navigation System Menu:");
            System.out.println("1. Add City");
            System.out.println("2. Add Road");
            System.out.println("3. Find Shortest Path");
            System.out.println("4. Detect Cycle");
            System.out.println("5. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter City ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter City Name: ");
                    String name = scanner.nextLine();
                    system.addCity(id, name);
                    break;
                case 2:
                    System.out.print("Enter City ID 1: ");
                    int id1 = scanner.nextInt();
                    System.out.print("Enter City ID 2: ");
                    int id2 = scanner.nextInt();
                    System.out.print("Enter Distance: ");
                    int distance = scanner.nextInt();
                    system.addRoad(id1, id2, distance);
                    break;
                case 3:
                    System.out.print("Enter Start City ID: ");
                    int startId = scanner.nextInt();
                    System.out.print("Enter End City ID: ");
                    int endId = scanner.nextInt();
                    List<City> path = system.findShortestPath(startId, endId);
                    if (path.isEmpty()) {
                        System.out.println("No path found");
                    } else {
                        path.forEach(city -> System.out.print(city.name + " "));
                        System.out.println();
                    }
                    break;
                case 4:
                    boolean hasCycle = system.detectCycle();
                    System.out.println(hasCycle ? "Cycle detected" : "No cycle detected");
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}