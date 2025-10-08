package hw2;
import java.io.File;
import java.nio.file.*;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import static java.nio.file.StandardCopyOption.COPY_ATTRIBUTES;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static java.nio.file.StandardWatchEventKinds.*;

public class ReleaseTask implements Task{
    private Path oldDir;
    private Path newDir;
    private WatchService watchServiceFirst;
    private WatchService watchServiceSecondt;

    ReleaseTask(String oldDir,String newDir) {
        this.oldDir = Path.of(oldDir);
        this.newDir = Path.of(newDir);
    }

    private void addFile (Path oldFile,Path newdir)  {
        try {
            Path temp = Path.of(newdir.toString(),oldFile.getFileName().toString());
            if (!Files.exists(temp)) {
                Path newFile = newdir.resolve(oldFile.getFileName());
                Files.copy(oldFile, newFile);
                if (newdir.equals(this.newDir))
                    System.out.print("Добавлено во вторую директорию: ");
                else
                    System.out.print("Добавлено в первую директорию: ");
                System.out.println(oldFile.getFileName());
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    private void modifyFile(Path oldFile, Path newdir) {
        try {
            Path temp = Path.of(newdir.toString(),oldFile.getFileName().toString());
            if (Files.exists(temp)) {
                Path target = newdir.resolve(oldFile.getFileName());
                Files.copy(oldFile, target, REPLACE_EXISTING, COPY_ATTRIBUTES);
                if (newdir.equals(this.newDir))
                    System.out.print("Изменено во второй директории: ");
                else
                    System.out.print("Изменено в первой директории: ");
                System.out.println(oldFile.getFileName());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteFile(Path oldFile, Path newdir) {
        try {
            Path temp = Path.of(newdir.toString(),oldFile.getFileName().toString());
            if (Files.exists(temp)) {
                Path target = newdir.resolve(oldFile.getFileName());
                if (Files.deleteIfExists(target)) {
                    if (newdir.equals(this.newDir))
                        System.out.print("Удалено из второй директории: ");
                    else
                        System.out.print("Удалено из первой директории: ");
                    System.out.println(oldFile.getFileName());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void start() throws IOException{

        try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(this.oldDir)) {
            for (Path oldFile : dirStream) {
                addFile(oldFile,this.newDir);
            }
        }

        try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(this.newDir)) {
            for (Path oldFile : dirStream) {
                addFile(oldFile,this.oldDir);
            }
        }

        this.watchServiceFirst = FileSystems.getDefault().newWatchService();
        this.oldDir.register(
                watchServiceFirst, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
        this.watchServiceSecondt = FileSystems.getDefault().newWatchService();
        this.newDir.register(
                watchServiceSecondt, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
        WatchKey keyFirst = null;
        WatchKey keySecond = null;
        try {
            while (true) {
                keyFirst  = watchServiceFirst.poll(250, TimeUnit.MILLISECONDS);
                keySecond = watchServiceSecondt.poll(250, TimeUnit.MILLISECONDS);
                if (keyFirst != null)
                    for (WatchEvent<?> event : keyFirst.pollEvents()) {
                        WatchEvent.Kind<?> kind = event.kind();
                        if (kind == ENTRY_CREATE)
                            addFile(Path.of(this.oldDir.toString(),event.context().toString()),this.newDir);
                        if (kind == ENTRY_MODIFY)
                            modifyFile(Path.of(this.oldDir.toString(),event.context().toString()),this.newDir);
                        if (kind == ENTRY_DELETE)
                            deleteFile(Path.of(this.oldDir.toString(),event.context().toString()),this.newDir);
                        keyFirst.reset();
                    }
                if (keySecond != null)
                    for (WatchEvent<?> event : keySecond.pollEvents()) {
                        WatchEvent.Kind<?> kind = event.kind();
                        if (kind == ENTRY_CREATE)
                            addFile(Path.of(this.newDir.toString(),event.context().toString()),this.oldDir);

                        if (kind == ENTRY_MODIFY)
                            modifyFile(Path.of(this.newDir.toString(),event.context().toString()),this.oldDir);

                        if (kind == ENTRY_DELETE)
                            deleteFile(Path.of(this.newDir.toString(),event.context().toString()),this.oldDir);

                        keySecond.reset();
                    }
            }
        }
        catch (InterruptedException e) {
            watchServiceFirst.close();
            watchServiceSecondt.close();
        }

    }

    public void stop() {
        try {
            watchServiceFirst.close();
            watchServiceSecondt.close();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) throws IOException
    {

            ReleaseTask check = new ReleaseTask("src/main/resources/num1","src/main/resources/num2");
            Scanner in = new Scanner(System.in);
            check.start();




    }
}
