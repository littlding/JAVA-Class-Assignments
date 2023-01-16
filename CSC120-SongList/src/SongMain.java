import java.io.File;
import java.util.Scanner;

public class SongMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SongCollection collection=new SongCollection();

        while (true){
            showTable();
            System.out.print("Enter choice: ");
            int cmd =Integer.parseInt(scanner.nextLine());
            switch (cmd){
                case 0:
                    return;
                case 1:
                    System.out.println("***\n" +
                            "*** Size = "+collection.size());
                    break;
                case 2:
                    System.out.print("Enter title search key: ");
                    String titleKey=scanner.nextLine();
                    collection.searchByTitle(titleKey);
                    break;
                case 3:
                    System.out.print("Enter artist search key: ");
                    String artistKey=scanner.nextLine();
                    collection.searchByArtist(artistKey);
                    break;
                case 4:
                    System.out.print("Enter file name: ");
                    String fileName=scanner.nextLine();
                    collection.addFromFile(new File(fileName));
                    break;
                case 5:
                    System.out.print("Enter file name: ");
                    String fileName5=scanner.nextLine();
                    collection.writeToFile(new File(fileName5));
                    break;
                case 6:
                    System.out.print("Enter title: ");
                    String t = scanner.nextLine();
                    System.out.print("Enter artist: ");
                    String a=scanner.nextLine();
                    collection.addOneSong(t,a);
                    break;
                case 7:
                    System.out.print("Enter position: ");
                    int pos=Integer.parseInt(scanner.nextLine());
                    collection.delete(pos);
                    break;
                case 8:
                    System.out.print("Enter start position: ");
                    int start = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter end position: ");
                    int end =Integer.parseInt(scanner.nextLine());
                    collection.show(start,end);
                    break;
                default:
                    System.out.println("invalid choice !!!");
                    break;
            }
        }

    }
    private static void showTable(){
        System.out.println(
                "========Select action========\n" +
                        "0. Quit\n" +
                        "1. Get collection size\n" +
                        "2. Search for title\n" +
                        "3. Search for artist\n" +
                        "4. Add from file\n" +
                        "5. Save to file\n" +
                        "6. Add one song\n" +
                        "7. Remove one song\n" +
                        "8. Show");
    }
}
