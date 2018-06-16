package standard_huffman;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.logging.*;
import javafx.util.Pair;
import javax.swing.JOptionPane;

public class Standard_Huffman {

    static Map<Character, String> codeMap = new HashMap<>();
    static Map<String, Character> decodeMap = new HashMap<>();

    private static int split(List<Pair<Integer, Character>> list) {
        int s1 = 0, s2 = 0;
        int l = 0, r = list.size() - 1;
        if (list.isEmpty()) {
            return 0;
        }
        while (l < r) {
            if (s1 > s2) {
                s2 += list.get(r).getKey();
                r--;
            } else {
                s1 += list.get(l).getKey();
                l++;
            }
        }
        return r;
    }

    public static void clear() {
        codeMap.clear();
        decodeMap.clear();
    }

    private static int toint(String s) {
        int sum = 0;
        s = new StringBuilder(s).reverse().toString();
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '1') {
                sum += Math.pow(2, i);
            }
        }
        return sum;
    }

    private static String tostring(byte n) {
        String tmp = "00000000", str = Integer.toBinaryString(n);
        if (str.length() < 8) {
            str = tmp.substring(0, 8 - str.length()) + str;
        }
        return str;
    }

    private static void Write(List<Pair<Integer, Character>> list, String str, String path) {
        try {
            try (DataOutputStream out = new DataOutputStream(new FileOutputStream(path))) {
                int size = list.size();
                out.writeByte(size);
                for (Pair<Integer, Character> n : list) {
                    out.writeByte(n.getKey());
                    out.writeByte(n.getValue());
                }
                String[] strings = str.split("(?<=\\G.{8})");
                for (String string : strings) {
                    while (string.length() < 8) {
                        string += '0';
                    }                    
                    int n = toint(string);
                    out.writeByte(n);
                }
                out.close();
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Standard_Huffman.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Standard_Huffman.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void Write(String str, String fileName) {
        try {
            try (FileWriter fw = new FileWriter(new File(fileName))) {
                fw.write(str);
                fw.close();
            }

        } catch (IOException iox) {
            //do stuff with exception
        }
    }

    private static byte[] Read(String Path) {
        byte[] data = null;
        try {
            Path path = Paths.get(Path);
            data = Files.readAllBytes(path);
        } catch (IOException ex) {
            Logger.getLogger(Standard_Huffman.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    private static String Binarystring(byte[] data, int i) {
        String res = "";
        for (int j = i; j < data.length; j++) {
            Byte b = data[j];
            int n = b.intValue() & 255;
            String tmp = Integer.toBinaryString(n);
            while (tmp.length() < 8) {
                tmp = '0' + tmp;
            }
            res += tmp;
        }
        return res;
    }

    private static void Code(List<Pair<Integer, Character>> list, String st) {
        if (list.isEmpty()) {
            return;
        }
        if (list.size() == 1) {
            codeMap.put(list.remove(0).getValue(), st);
        }
        int index = split(list);
        List<Pair<Integer, Character>> newlist = new ArrayList<>(list);
        Code(list.subList(0, index), st + '0');
        index = split(newlist);
        Code(newlist.subList(index, newlist.size()), st + '1');
    }

    @SuppressWarnings("Convert2Lambda")
    public static String Compress(String str, String path) {
        Write(str, "Text.txt");
        int[] freq = new int[257];
        Set<Pair<Integer, Character>> symbolls = new HashSet<>();
        for (char c : str.toCharArray()) {
            freq[c]++;
        }
        for (char c : str.toCharArray()) {
            symbolls.add(new Pair<>(freq[c], c));
        }
        List<Pair<Integer, Character>> list = new ArrayList<>(symbolls);
        Collections.sort(list, new Comparator<Pair<Integer, Character>>() {
            @Override
            public int compare(Pair<Integer, Character> p1, Pair<Integer, Character> p2) {
                if (Objects.equals(p1.getKey(), p2.getKey())) {
                    return p1.getValue().compareTo(p2.getValue());
                }
                return p2.getKey().compareTo(p1.getKey());
            }
        });
        List<Pair<Integer, Character>> newlist = new ArrayList<>(list);
        if(list.size()>1)
                Code(list, "");
        else{
            codeMap.put(list.remove(0).getValue(), "0");
        }
        String res = "";
        for (char c : str.toCharArray()) {
            res += codeMap.get(c);
        }
        Write(newlist, res, path);
        return res;
    }

    public static String Decompress(String path) {
        List<Pair<Integer, Character>> list = new ArrayList<>();
        byte[] data = Read(path);
        if(data.length==0){
            JOptionPane.showMessageDialog(null, "Empty File", "Error", JOptionPane.ERROR_MESSAGE);
            return "";
        }
        int size = data[0], i = 1;
        for (; i <= size * 2; ++i) {
            int n = (int) Byte.toUnsignedInt(data[i]);
            char c = (char) data[++i];
            list.add(new Pair<>(n, c));
        }
        List<Pair<Integer, Character>> newlist = new ArrayList<>(list);
        if(list.size()>1)
            Code(list, "");
        else{
            codeMap.put(list.remove(0).getValue(), "0");
        }
        int sum = 0;
        for (Pair<Integer, Character> p : newlist) {
            sum += p.getKey() * codeMap.get(p.getValue()).length();
        }
        String binString = Binarystring(data, i).substring(0, sum);
        String res = "", tmp = "";
        for (Map.Entry<Character, String> ent : codeMap.entrySet()) {
            decodeMap.put(ent.getValue(), ent.getKey());
        }
        for (char c : binString.toCharArray()) {
            tmp += c;
            if (decodeMap.containsKey(tmp)) {
                res += decodeMap.get(tmp);
                tmp = "";
            }
        }
        Write(res, "Decompress.txt");
        return res;
    }
}
