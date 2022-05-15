import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String args[]){
        Path path = Paths.get("C:/Projetos/BackEnd_LetsCode/LC.MaterialProjetoJava/male_oscar.csv");
        List<Ator> listaCompleta = new ArrayList<>();
        listaCompleta = readLine(path);
    }

    public static List<Ator> readLine(Path path){
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