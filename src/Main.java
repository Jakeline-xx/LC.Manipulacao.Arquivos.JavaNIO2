import dto.Actor;
import services.CsvHandler;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String args[]){
        CsvHandler handler = new CsvHandler();

        Path pathMale = Paths.get("C:/Projetos/BackEnd_LetsCode/ArquivosJavaNIO2/LC.Manipulacao.Arquivos.JavaNIO2/src/infra/repository/male_oscar.csv");
        Path pathFemale = Paths.get("C:/Projetos/BackEnd_LetsCode/ArquivosJavaNIO2/LC.Manipulacao.Arquivos.JavaNIO2/src/infra/repository/female_oscar.csv");

        List<Actor> listMale = new ArrayList<>();
        listMale = handler.readData(pathMale);

        List<Actor> listFemale = new ArrayList<>();
        listFemale = handler.readData(pathFemale);


        List<Actor> allActors = new ArrayList();

        allActors.addAll(listMale);
        allActors.addAll(listFemale);


        //YOUNGEST ACTOR
        String youngerActor = handler.getYougerActor(listMale);
        System.out.println("Youngest Actor Awarded: " + youngerActor);
        System.out.println("\n ___________________________________");

        //MOST AWARDED ACTRESS
        String mostAwardedActress = handler.getMostAwarded(listFemale);
        System.out.println("Most Awarded Actress: " + mostAwardedActress);
        System.out.println("\n ___________________________________");

        //MOST AWARDED ACTRESS BETWEEN 20 AND 30 YEARS
        List<Actor> actressesBetweenAge = handler.getActressesBetweenAge(listFemale, 20, 30);
        Long qtdAwarded = handler.getQtdAwarded(actressesBetweenAge);
        List<Map.Entry<String, Long>> actressesFinal = handler.getMostAwardedFinal(actressesBetweenAge,qtdAwarded);
        System.out.println("Actress who was awarded the most awards aged between 20 and 30:");
        actressesFinal.forEach(a -> System.out.print("\n" + a.getKey() + ": " + a.getValue() + " times "));
        System.out.println("\n ___________________________________");

        //ACTORS THAT HAVE BEEN AWARDED MORE THAN ONE TIME
        List<Map.Entry<String, Long>> allActorsAwards = handler.getAllActorsAwards(allActors);
        System.out.println("Actors who have been awarded more than one time:");
        allActorsAwards.forEach(a -> System.out.print("\n" + a.getKey() + ": " + a.getValue() + " times "));
        System.out.println("\n ___________________________________");

        //INFOS ACTORS
        List<Actor> actorInfo = handler.getActorInfo(allActors, "Fredric March");
        System.out.println("Number of times won: " + actorInfo.size());
        actorInfo.forEach(a -> System.out.println("Year:" + a.getYear() + " Idade:" + a.getAge() + " Movie:" + a.getMovie()));
    }
}