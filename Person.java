import java.util.*;

class Person {
    int id;
    String name;
    List<Person> friends;

    Person(int id, String name) {
        this.id = id;
        this.name = name;
        this.friends = new ArrayList<>();
    }
}

class SocialNetwork {
    private Map<Integer, Person> personMap;

    public SocialNetwork() {
        personMap = new HashMap<>();
    }

    public void addPerson(int id, String name) {
        if (!personMap.containsKey(id)) {
            personMap.put(id, new Person(id, name));
        }
    }

    public void addFriendship(int id1, int id2) {
        Person person1 = personMap.get(id1);
        Person person2 = personMap.get(id2);
        if (person1 != null && person2 != null && !person1.friends.contains(person2)) {
            person1.friends.add(person2);
            person2.friends.add(person1);
        }
    }

    public boolean areConnected(int id1, int id2) {
        return bfs(id1, id2, false) != null;
    }

    public List<Person> findShortestPath(int id1, int id2) {
        return bfs(id1, id2, true);
    }

    public List<Person> listAllFriends(int id) {
        Person person = personMap.get(id);
        if (person != null) {
            return person.friends;
        }
        return new ArrayList<>();
    }

    private List<Person> bfs(int startId, int endId, boolean returnPath) {
        Set<Integer> visited = new HashSet<>();
        Queue<List<Person>> queue = new LinkedList<>();

        if (personMap.containsKey(startId)) {
            queue.add(Collections.singletonList(personMap.get(startId)));

            while (!queue.isEmpty()) {
                List<Person> path = queue.poll();
                Person last = path.get(path.size() - 1);

                if (last.id == endId) {
                    return returnPath ? path : Arrays.asList(last);
                }

                for (Person friend : last.friends) {
                    if (!visited.contains(friend.id)) {
                        visited.add(friend.id);
                        List<Person> newPath = new ArrayList<>(path);
                        newPath.add(friend);
                        queue.add(newPath);
                    }
                }
            }
        }
        return null; // No path found
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SocialNetwork network = new SocialNetwork();

        while (true) {
            System.out.println("\nSocial Network Menu:");
            System.out.println("1. Add Person");
            System.out.println("2. Add Friendship");
            System.out.println("3. Check Connection");
            System.out.println("4. Find Shortest Path");
            System.out.println("5. List All Friends");
            System.out.println("6. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    network.addPerson(id, name);
                    break;
                case 2:
                    System.out.print("Enter ID 1: ");
                    int id1 = scanner.nextInt();
                    System.out.print("Enter ID 2: ");
                    int id2 = scanner.nextInt();
                    network.addFriendship(id1, id2);
                    break;
                case 3:
                    System.out.print("Enter ID 1: ");
                    id1 = scanner.nextInt();
                    System.out.print("Enter ID 2: ");
                    id2 = scanner.nextInt();
                    boolean connected = network.areConnected(id1, id2);
                    System.out.println(connected ? "Connected" : "Not Connected");
                    break;
                case 4:
                    System.out.print("Enter ID 1: ");
                    id1 = scanner.nextInt();
                    System.out.print("Enter ID 2: ");
                    id2 = scanner.nextInt();
                    List<Person> path = network.findShortestPath(id1, id2);
                    if (path != null) {
                        path.forEach(person -> System.out.print(person.name + " "));
                        System.out.println();
                    } else {
                        System.out.println("No path found");
                    }
                    break;
                case 5:
                    System.out.print("Enter ID: ");
                    id = scanner.nextInt();
                    List<Person> friends = network.listAllFriends(id);
                    friends.forEach(friend -> System.out.println(friend.name));
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}