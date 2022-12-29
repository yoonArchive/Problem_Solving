package BOJ.DataStructure.Map_Set;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class Main_BJ_20541_앨범정리 {

    private static Album current, album;
    public static StringBuilder sb;

    private static class Album {
        String name;
        TreeMap<String, Album> albums;
        TreeSet<String> photos;
        Album parent;

        Album(String name, Album parent) {
            this.name = name;
            this.albums = new TreeMap<>();
            this.photos = new TreeSet<>();
            this.parent = parent;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        album = new Album("album", null);
        current = album;
        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            String S = st.nextToken();
            switch (command) {
                case "mkalb":
                    mkalb(S);
                    break;
                case "rmalb":
                    int[] rmalbRusult = rmalb(S);
                    sb.append(rmalbRusult[0]).append(" ").append(rmalbRusult[1]).append("\n");
                    break;
                case "insert":
                    insert(S);
                    break;
                case "delete":
                    sb.append(delete(S)).append("\n");
                    break;
                case "ca":
                    sb.append(ca(S)).append("\n");
                    break;
            }
        }
        System.out.println(sb);
        br.close();
    }

    private static void mkalb(String albumName) {
        if (current.albums.containsKey(albumName)) {
            sb.append("duplicated album name").append("\n");
        } else {
            current.albums.put(albumName, new Album(albumName, current));
        }
    }

    private static int[] rmalb(String S) {
        int[] removeCounts = new int[2];
        if (S.equals("-1") && current.albums.size() > 0) {
            removeCounts = countRemovedFilesAndAlbums(current.albums.firstEntry().getValue());
            current.albums.pollFirstEntry();
            removeCounts[0]++;
        } else if (S.equals("0")) {
            for (String key : current.albums.keySet()) {
                int[] tmp = countRemovedFilesAndAlbums(current.albums.get(key));
                removeCounts[0] += tmp[0] + 1;
                removeCounts[1] += tmp[1];
            }
            current.albums.clear();
        } else if (S.equals("1") && current.albums.size() > 0) {
            removeCounts = countRemovedFilesAndAlbums(current.albums.lastEntry().getValue());
            removeCounts[0]++;
            current.albums.pollLastEntry();
        } else {
            if (current.albums.containsKey(S)) {
                removeCounts = countRemovedFilesAndAlbums(current.albums.get(S));
                removeCounts[0]++;
                current.albums.remove(S);
            }
        }
        return removeCounts;
    }

    private static int[] countRemovedFilesAndAlbums(Album removedAlbum) {
        int[] count = new int[2];
        if (!removedAlbum.albums.isEmpty()) {
            for (String key : removedAlbum.albums.keySet()) {
                count[0]++;
                int[] tmp = countRemovedFilesAndAlbums(removedAlbum.albums.get(key));
                count[0] += tmp[0];
                count[1] += tmp[1];
            }
            removedAlbum.albums.clear();
        }
        count[1] += removedAlbum.photos.size();
        removedAlbum.photos.clear();
        return count;
    }

    private static void insert(String photoName) {
        if (current.photos.contains(photoName)) {
            sb.append("duplicated photo name").append("\n");
        } else {
            current.photos.add(photoName);
        }
    }

    private static int delete(String S) {
        if (S.equals("-1") && current.photos.size() > 0) {
            current.photos.pollFirst();
            return 1;
        } else if (S.equals("0")) {
            int size = current.photos.size();
            current.photos.clear();
            return size;
        } else if (S.equals("1") && current.photos.size() > 0) {
            current.photos.pollLast();
            return 1;
        } else {
            if (current.photos.contains(S)) {
                current.photos.remove(S);
                return 1;
            }
        }
        return 0;
    }

    private static String ca(String cmd) {
        if (cmd.equals("..") && current.parent != null) {
            current = current.parent;
        } else if (cmd.equals("/")) {
            current = album;
        } else {
            if (current.albums.containsKey(cmd)) {
                current = current.albums.get(cmd);
            }
        }
        return current.name;
    }
}

