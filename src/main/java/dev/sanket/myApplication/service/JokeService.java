package dev.sanket.myApplication.service;

import dev.sanket.myApplication.model.Joke;
import dev.sanket.myApplication.repository.JokeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JokeService {
    private final RestClient jokeRestClient;
    private final JokeRepository jokeRepository;

//    public static void main(String[] args) {
//        String str = "sanket";
//        Set<Character> set = new HashSet<>();
//        str.chars()
//                .mapToObj(c -> (char) c)
//                .filter(c -> !set.add(c))
//                .forEach(System.out::println);
//
//        System.out.println();
//        System.out.println(checkIsogram(str));
//
//        List<Employee> employees = List.of(
//                new Employee(1, "Gangadhar", 10.0, "IT"),
//                new Employee(2, "Akash", 20.0, "IT"),
//                new Employee(3, "Akshay", 30.0, "Math"),
//                new Employee(5, "Swati", 40.0, "Math"),
//                new Employee(4, "Alok", 50.0, "Admin"),
//                new Employee(6, "Sanket", 60.0, "Admin")
//
//        );
//
//        Map<String, Double> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::department,
//                        Collectors.averagingDouble(Employee::salary)));
//
//        collect.forEach((dept, avgSalary) -> System.out.println("Department :" + dept + " average salary :" + avgSalary));
//
//        Map<String, Optional<Employee>> top = employees.stream()
//                .collect(Collectors.groupingBy(Employee::department,
//                        Collectors.reducing(BinaryOperator.maxBy(Comparator.comparingDouble(Employee::salary)))));
//
//        top.forEach((department, employee) -> System.out.println("Department :" + department + " emploee :" + employee));
//
//        Map<String, Integer> map1 = new HashMap<>(Map.of(
//                "math", 40,
//                "chem", 50,
//                "physics", 45,
//                "bio", 36,
//                "english", 56
//        ));
//
//        Map<String, Integer> map2 = Map.of(
//                "math", 40,
//                "physics", 52,
//                "geography", 48,
//                "history", 34,
//                "english", 28
//        );
//
//        Map<String, Integer> map3 = Stream.of(map1, map2)
//                .flatMap(map -> map.entrySet().stream())
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Integer::sum));
//
//        map2.forEach((key, value) -> {
//            map1.merge(key, value, Integer::sum);
//        });
//
//        System.out.println(map1);
//        System.out.println(map3);
//
//        int[] arr1 = {4, 7, 3, 5};
//        int[] arr2 = {8, 2, 9, 1, 6};
//
//        int[] array = IntStream.concat(Arrays.stream(arr1), Arrays.stream(arr2))
//                .sorted()
//                .toArray();
//        System.out.println(Arrays.toString(array));
//
//        int[] array1 = Stream.of(arr1, arr2)
//                .flatMapToInt(Arrays::stream)
//                .sorted()
//                .toArray();
//        System.out.println(Arrays.toString(array1));
//
//    }

//    private static boolean checkIsogram(String str) {
//        Set<Character> s = new HashSet<>();
//        long count = str.toLowerCase()
//                .chars()
//                .mapToObj(c -> (char) c)
//                .filter(c -> !s.add(c))
//                .count();
//        return count == 0;
//    }

    public Joke getJoke() {
        Joke joke = jokeRestClient.get()
                .uri("/random_joke")
                .retrieve()
                .body(Joke.class);
        return jokeRepository.existsById(joke.getId()) ? joke : jokeRepository.save(joke);
    }

    public List<Joke> getJokes() {
        return jokeRepository.findAll();
    }

    public List<Joke> saveJokes(List<Joke> jokes) {
        return jokeRepository.saveAll(jokes);
    }
}

//record Employee(Integer id, String name, Double salary, String department) implements Comparable<Employee> {
//    @Override
//    public int compareTo(Employee e) {
//        return this.id().compareTo(e.id());
//    }
//}