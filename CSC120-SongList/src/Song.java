/**
 * the class for an individual song
 */
public class Song implements Comparable<Song>{
    private String title;
    private String artist;

  public Song(String title,String artist){
      this.title=title;
      this.artist=artist;
  }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    @Override
    public int compareTo(Song o) {
      int diff = this.title.compareTo(o.title);
      if(diff!=0){
          return diff;
      }
        return this.artist.compareTo(o.artist);
    }

    @Override
    public String toString() {
        return title+", "+artist;
    }
}
