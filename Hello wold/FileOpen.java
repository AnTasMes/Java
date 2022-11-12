
//import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.*;

import java.util.ArrayList;
//import java.io.FileOutputStream;

public class FileOpen {
    public static void main(String[] args) {
        String path = "D:\\Programming\\Java\\Hello wold\\some.txt";
        WordHandler wh = new WordHandler(new FileHandler(path));

        wh.getWords();
        wh.printWords();
    }

    public static void close(Closeable c) {
        if (c == null) {
            return;
        }
        try {
            c.close();
        } catch (IOException e) {

        }
    }
}

class WordHandler {
    private ArrayList<String> words;
    private FileHandler handler;

    WordHandler(FileHandler handler) {
        this.handler = handler;

        words = new ArrayList<String>();
    }

    public char[] clearWord(char[] word) {
        for (int i = 0; i < word.length; i++) {
            word[i] = '\0';
        }
        return word;
    }

    public void getWords() {
        FileInputStream fis = handler.getInputStream();
        int r = 0, i = 0;
        char[] word = new char[99];
        try {
            while ((r = fis.read()) != -1) {
                if ((char) r != ' ') {
                    word[i++] += (char) r;
                } else {
                    words.add(new String(word));
                    word = this.clearWord(word);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printWords() {
        for (int i = 0; i < words.size(); i++) {
            System.out.println(this.words.get(i));
        }
    }
}

class FileHandler {
    public String filepath;

    private File currentFile;

    FileHandler(String filepath) {
        this.filepath = filepath;

        this.currentFile = new File(filepath);
    }

    public FileInputStream getInputStream() {
        try {
            FileInputStream fis = new FileInputStream(filepath);
            return fis;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
