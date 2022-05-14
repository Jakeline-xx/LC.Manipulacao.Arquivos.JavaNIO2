import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String args[]){
         Path path = Paths.get("C:/Projetos/BackEnd_LetsCode/LC.MaterialProjetoJava/male_oscar.csv");
         readLine(path);
    }

    public static void readLine(Path file){
        try(Stream<String> stream = Files.lines(file, StandardCharsets.UTF_8))
        {
            List<Ator> listaAtores = new ArrayList<Ator>();
                stream.skip(1).forEach(line -> {
                    String[] lineTeste = line.split(";");
                    Arrays.toString(lineTeste);
                    Ator ator = new Ator();
                    ator.setIndex(lineTeste[0]);
                    ator.setYear(lineTeste[1]);
                    ator.setAge(Integer.parseInt(lineTeste[2]));
                    ator.setName(lineTeste[3]);
                    ator.setMovie(lineTeste[4]);
                    listaAtores.add(ator);
                });
            System.out.println("FIM");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}