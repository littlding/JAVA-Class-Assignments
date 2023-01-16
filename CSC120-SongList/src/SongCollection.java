import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class SongCollection {
    private Song[] theSongs;

    /**
     *
     * @return returns the number of elements in the array theSongs
     */
    public int size(){
        return theSongs.length;
    }
    public SongCollection(){
        theSongs=new Song[0];
    }

    /**
     * read data from file
     * @param f file
     */
    public void addFromFile( File f ){
        try {
            Scanner scanner = new Scanner(f);
            int newLen= Integer.parseInt(scanner.nextLine().trim());
            Song[] temp =Arrays.copyOf(theSongs,theSongs.length+newLen);
            for (int i = theSongs.length; i <theSongs.length+newLen ; i++) {
                String title=scanner.nextLine();
                String artist=scanner.nextLine();
                temp[i]=new Song(title,artist);
            }
            Arrays.sort(temp);
            Song[] merged=new Song[temp.length];
            int index=0;
            merged[index++]=temp[0];
            for (int i = 1; i < temp.length ; i++) {
                if(temp[i].compareTo(temp[i-1])!=0){
                    merged[index++]=temp[i];
                }
            }
            theSongs=Arrays.copyOf(merged,index);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * write data to file.
     *
     * @param f file
     */
    public void writeToFile( File f ){
        BufferedWriter writer=null;
        try {
            writer=new BufferedWriter(new FileWriter(f));
            writer.write(theSongs.length+"\n");
            for(Song song:theSongs){
                writer.write(song.getTitle()+"\n");
                writer.write(song.getArtist()+"\n");
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(writer!=null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * add a song
     *
     * @param t title
     * @param a artist
     */
    public void addOneSong( String t, String a ){
        for(Song song:theSongs){
            if(song.compareTo(new Song(t,a))==0){
                return;
            }
        }
        Song[] temp=Arrays.copyOf(theSongs,theSongs.length+1);
        temp[theSongs.length]=new Song(t,a);
        Arrays.sort(temp);
        theSongs=temp;
    }

    /**
     * delete song
     *
     * @param pos index
     */
    public void delete( int pos ){
        if(pos<0||pos>=theSongs.length){
            return;
        }
        Song[] temp= new Song[theSongs.length-1];
        int index=0;
        for (int i = 0; i < theSongs.length; i++) {
            if(i!=pos){
                temp[index++]=theSongs[i];
            }
        }
    }

    /**
     * prints all the songs whose title contains key along
     * with their index values. You can use the method indexOf( key ) on the value returned by
     * the method getTitle.
     * @param key title
     */
    public void searchByTitle( String key ){
        for(int i=0;i<size();i++){
            if(theSongs[i].getTitle().contains(key)){
                System.out.println(i+": "+theSongs[i]);
            }
        }
    }

    /**
     *  prints all the songs whose artist contains key
     * along with their index values
     *
     * @param key artist
     */
    public void searchByArtist( String key ){
        for(int i=0;i<size();i++){
            if(theSongs[i].getArtist().contains(key)){
                System.out.println(i+": "+theSongs[i]);
            }
        }
    }

    /**
     * show song  begin start and end
     * @param start start include
     * @param end end exclude
     */
    public void show( int start, int end ){
        if(start<0||start>=theSongs.length){
            return;
        }
        if(end<1||end>theSongs.length){
            return;
        }
        for (int i = start; i < end; i++) {
            System.out.println(i+": "+theSongs[i]);
        }
    }

}
