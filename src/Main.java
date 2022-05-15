import dto.Actor;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


public class Main {
    public static void main(String args[]){
        Path pathMale = Paths.get("C:/Projetos/BackEnd_LetsCode/ArquivosJavaNIO2/LC.Manipulacao.Arquivos.JavaNIO2/src/infra/repository/male_oscar.csv");
        Path pathFemale = Paths.get("C:/Projetos/BackEnd_LetsCode/ArquivosJavaNIO2/LC.Manipulacao.Arquivos.JavaNIO2/src/infra/repository/female_oscar.csv");

        List<Actor> listMale = new ArrayList<>();
        listMale = readData(pathMale);

        List<Actor> listFemale = new ArrayList<>();
        listFemale = readData(pathFemale);


        List<Actor> allActors = new ArrayList();

        allActors.addAll(listMale);
        allActors.addAll(listFemale);


        //YOUNGEST ACTOR
        String youngerActor = getYougerActor(listMale);
        System.out.println("Youngest Actor Awarded: " + youngerActor);
        System.out.println("\n ___________________________________");

        //MOST AWARDED ACTRESS
        String mostAwardedActress = getMostAwarded(listFemale);
        System.out.println("Most Awarded Actress: " + mostAwardedActress);
        System.out.println("\n ___________________________________");

        //MOST AWARDED ACTRESS BETWEEN 20 AND 30 YEARS
        List<Actor> actressesBetweenAge = getActressesBetweenAge(listFemale, 20, 30);
        Long qtdAwarded = getQtdAwarded(actressesBetweenAge);
        List<Map.Entry<String, Long>> actressesFinal = getMostAwardedFinal(actressesBetweenAge,qtdAwarded);
        System.out.println("Actress who was awarded the most awards aged between 20 and 30:");
        actressesFinal.forEach(a -> System.out.print("\n" + a.getKey() + ": " + a.getValue() + " times "));
        System.out.println("\n ___________________________________");

        //ACTORS THAT HAVE BEEN AWARDED MORE THAN ONE TIME
        List<Map.Entry<String, Long>> allActorsAwards = getAllActorsAwards(allActors);
        System.out.println("Actors who have been awarded more than one time:");
        allActorsAwards.forEach(a -> System.out.print("\n" + a.getKey() + ": " + a.getValue() + " times "));
        System.out.println("\n ___________________________________");

        //INFOS ACTORS
        List<Actor> actorInfo = getActorInfo(allActors, "Fredric March");
        System.out.println("Number of times won: " + actorInfo.size());
        actorInfo.forEach(a -> System.out.println("Year:" + a.getYear() + " Idade:" + a.getAge() + " Movie:" + a.getMovie()));
    }

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