import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {
    final static String USERPATH = "/Users/rusimac/Games/";
    final static String ZIPFILE = "saves.zip";

    public static void main(String[] args) {
        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(USERPATH + "savegames/" + ZIPFILE))) {
            ZipEntry entry;
            String name;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                name = entry.getName();
                FileOutputStream fileOutputStream = new FileOutputStream(USERPATH + "savegames/" + name);
                for (int f = zipInputStream.read(); f != -1; f = zipInputStream.read()) {
                    fileOutputStream.write(f);
                }
                fileOutputStream.flush();
                zipInputStream.closeEntry();
                fileOutputStream.close();
            }
        } catch (IOException ex) {
            System.err.println("Не удалось разархивировать файл");
        }
    }
}
