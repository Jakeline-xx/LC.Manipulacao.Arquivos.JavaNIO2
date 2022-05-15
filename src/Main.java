import jdk.swing.interop.SwingInterOpUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Main {
    public static void main(String args[]){
        Path pathMale = Paths.get("C:/Projetos/BackEnd_LetsCode/LC.MaterialProjetoJava/male_oscar.csv");
        Path pathFemale = Paths.get("C:/Projetos/BackEnd_LetsCode/LC.MaterialProjetoJava/female_oscar.csv");

        List<Ator> listMale = new ArrayList<>();
        listMale = readData(pathMale);

        List<Ator> listFemale = new ArrayList<>();
        listFemale = readData(pathFemale);

        //ATOR MAIS JOVEM
        String youngerActor = getYougerActor(listMale);
        System.out.println("Ator mais jovem premiado: " + youngerActor);

        //ATRIZ MAIS PREMIADA
        String actressMostAwarded = getMostAwarded(listFemale);
        System.out.println("Atriz que mais vezes foi premiada: " + actressMostAwarded);
    }

    public static String getMostAwarded(List<Ator> actresses){
         return actresses.stream()
                .collect(Collectors.groupingBy(Ator::getName, Collectors.counting()))
                 .entrySet()
                 .stream()
                 .max((Map.Entry<String, Long> e1, Map.Entry<String, Long> e2) -> e1.getValue()
                         .compareTo(e2.getValue())).get().getKey();
    }

    public static String getYougerActor(List<Ator> actors){
        return actors.stream()
                .min(Comparator.comparingInt(Ator::getAge))
                .get()
                .getName();
    }

    public static List<Ator> readData(Path path){
        List<Ator> listaAtores = new ArrayList<>();
        try{
                List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
                lines.stream().skip(1).forEach(line -> {
                    String[] lineTeste = line.split(";");
                    listaAtores.add(new Ator(
                        lineTeste[0],
                        lineTeste[1],
                        Integer.parseInt(lineTeste[2]),
                        lineTeste[3],
                        lineTeste[4]));
                });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return listaAtores;
    }
}