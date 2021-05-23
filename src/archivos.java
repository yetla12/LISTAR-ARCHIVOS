import java.io.File;
import java.io.FileFilter;
import java.text.SimpleDateFormat;

public class archivos {
}
class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    public static void main(String[] args) {
        //Carpeta del usuario
        String sCarpAct = System.getProperty("user.dir");
        System.out.println("Carpeta del usuario = " + sCarpAct);

        //Listemos todas las carpetas y archivos de la carpeta actual
        System.out.println(ANSI_RED + "//// LISTADO SIMPLE" + ANSI_RESET);

        File carpeta = new File(sCarpAct);
        String[] listado = carpeta.list();
        if (listado == null || listado.length == 0) {
            System.out.println("No hay elementos dentro de la carpeta actual");
            return;
        }
        else {
            for (int i=0; i< listado.length; i++) {
                System.out.println(listado[i]);
            }
        }

        //Lo mismo que lo anterior pero con objetos File para poder ver sus propiedades
        System.out.println(ANSI_RED + "//// LISTADO CON OBJETOS File" + ANSI_RESET);

        File[] archivos = carpeta.listFiles();
        if (archivos == null || archivos.length == 0) {
            System.out.println("No hay elementos dentro de la carpeta actual");
            return;
        }
        else {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            for (int i=0; i< archivos.length; i++) {
                File archivo = archivos[i];
                System.out.println(String.format("%s (%s) - %d - %s",
                        archivo.getName(),
                        archivo.isDirectory() ? "Carpeta" : "Archivo",
                        archivo.length(),
                        sdf.format(archivo.lastModified())
                ));
            }
        }

        //Se pueden filtrar los resultados tanto usando list() como usando listFiles()
        //Por ejemplo, en este segundo caso para mostrar solo archivos y no carpetas
        System.out.println(ANSI_RED + "//// LISTADO CON FILTRO APLICADO - SOLO ARCHIVOS" + ANSI_RESET);

        FileFilter filtro = new FileFilter() {
            @Override
            public boolean accept(File arch) {
                return arch.isFile();
            }
        };

        archivos = carpeta.listFiles(filtro);

        if (archivos == null || archivos.length == 0) {
            System.out.println("No hay elementos dentro de la carpeta actual");
            return;
        }
        else {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            for (int i=0; i< archivos.length; i++) {
                File archivo = archivos[i];
                System.out.println(String.format("%s (%s) - %d - %s",
                        archivo.getName(),
                        archivo.isDirectory() ? "Carpeta" : "Archivo",
                        archivo.length(),
                        sdf.format(archivo.lastModified())
                ));
            }
        }

    }
}
