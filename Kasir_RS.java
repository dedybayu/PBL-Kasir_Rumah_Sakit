import java.util.Scanner;
public class Kasir_RS{
    public static void main(String[] args){
        boolean berhasil;
        berhasil = false;
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("|           Selamat Datang di Kasir           |");
        System.out.println("|            Rumah Sakit Cina Java            |");
        System.out.println("|                Silahkan Login               |");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++"); 

        while (berhasil == false){
            // untuk login password 
        Scanner input = new Scanner(System.in);
        System.out.print("Masukan Username: ");
        String username= input.nextLine();
        System.out.print("Masukan Password: ");
        String password= input.nextLine();

        //Deklarasi Password
        if ((username.equals("alek")) && (password.equals("admin"))){
        berhasil = true;

            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("|           Selamat Datang di Kasir           |");
            System.out.println("|           Rumah Sakit Cina Sejati           |");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");    
        }

        else{
            System.out.println(" ");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("|               Passwordmu Salah              |");
            System.out.println("|              Masukan yang Benar             |");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");       
        }
    }
    }
}
