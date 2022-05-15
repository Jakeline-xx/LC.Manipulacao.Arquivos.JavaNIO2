package services;

import dto.Actor;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CsvHandler {
    public static List<Actor> getActorInfo(List<Actor> actors, String name){
        return actors.stream()
                .filter(a -> a.getName().trim().toUpperCase().equals(name.trim().toUpperCase()))
                .toList();
    }
    public static List<Map.Entry<String, Long>> getAllActorsAwards(List<Actor> actors){
        return actors.stream()
                .collect(Collectors.groupingBy(Actor::getName, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(a -> a.getValue() > 1).toList();
    }
    public static String getMostAwarded(List<Actor> actresses){
        return actresses.stream()
                .collect(Collectors.groupingBy(Actor::getName, Collectors.counting()))
                .entrySet()
                .stream()
                .max((a1, a2) -> a1.getValue().intValue() - a2.getValue().intValue()).get().getKey();
    }
    public static List<Actor> getActressesBetweenAge (List<Actor> actresses, int ageStart, int ageFinal){
        return actresses.stream().filter(a -> a.getAge() >= ageStart && a.getAge() <= ageFinal).toList();
    }
    public static Long getQtdAwarded(List<Actor> actresses){
        return actresses.stream()
                .collect(Collectors.groupingBy(Actor::getName, Collectors.counting()))
                .entrySet()
                .stream()
                .max((a1, a2) -> a1.getValue().intValue() - a2.getValue().intValue()).get().getValue();
    }
    public static List<Map.Entry<String, Long>> getMostAwardedFinal(List<Actor> actresses, Long qtdAwarded){
        return actresses.stream()
                .collect(Collectors.groupingBy(Actor::getName, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(a -> a.getValue() == qtdAwarded).toList();
    }
    public static String getYougerActor(List<Actor> actors){
        return actors.stream()
                .min(Comparator.comparingInt(Actor::getAge))
                .get()
                .getName();
    }
    public static List<Actor> readData(Path path){
        List<Actor> listActors = new ArrayList<>();
        try{
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            lines.stream().skip(1).forEach(line -> {
                String[] lineTest = line.split(";");
                listActors.add(new Actor(
                        lineTest[0],
                        lineTest[1],
                        Integer.parseInt(lineTest[2]),
                        lineTest[3],
                        lineTest[4]));
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return listActors;
    }
}
