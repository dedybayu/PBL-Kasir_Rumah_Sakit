import java.util.Scanner;

public class rscinta {

    public static void main(String[] args) {
        Scanner inputLogIn = new Scanner(System.in);

        // Tampilan login
        System.out.println("--------------------------");
        System.out.println("- Welcome Bro in kasir   -");
        System.out.println("- Rumah sakit Cinta java -");
        System.out.println("-   Login Dulu Boss      -");
        System.out.println("--------------------------");

        System.out.println("----------------------------------");
        System.out.println("-       Pilih Login Sebagai     -");
        System.out.println("- 1. Login Sebagai Admin        -");
        System.out.println("- 2. Login Sebagai Manajer      -");
        System.out.println("----------------------------------");

        System.out.print("Login Sebagai : ");
        int loginSebagai = inputLogIn.nextInt();

        // Variabel untuk data pasien
        String namaPasien, alamatPasien, nomorHpPasien, penyakitPasien, perluRawatInap;

        switch (loginSebagai) {
            case 1:
            System.out.println("Selamat datang Admin\n");
            Scanner inputAdmin = new Scanner(System.in);
            boolean adminBerhasil = false; // Variabel untuk menandai apakah admin berhasil login

            // Username dan password admin
            String usernameAdmin = "admin";
            String passwordAdmin = "admin123";

            while (!adminBerhasil) {
                System.out.print("Username : ");
                String username = inputAdmin.next();
                System.out.print("Password : ");
                String password = inputAdmin.next();

                if (username.equals(usernameAdmin) && password.equals(passwordAdmin)) {
                    System.out.println("Login berhasil!");
                    adminBerhasil = true;
                    // Mengimputkan data pasien
                    System.out.println("Masukan Biodata Pasien");
                    inputAdmin.nextLine(); // Membersihkan newline dari input sebelumnya
                    System.out.print("Masukan Nama pasien: ");
                    namaPasien = inputAdmin.nextLine();
                    System.out.print("Masukan Alamat pasien: ");
                    alamatPasien = inputAdmin.nextLine();
                    System.out.print("Masukan Nomer HP pasien: ");
                    nomorHpPasien = inputAdmin.nextLine();
                    System.out.print("Masukan penyakit pasien: ");
                    penyakitPasien = inputAdmin.nextLine();
                    System.out.print("Apakah perlu pasien Rawat Inap (Y/N): ");
                    perluRawatInap = inputAdmin.nextLine();

                    // biodata Pasien setelah input
                    System.out.println("Nama Pasien : " + namaPasien);
                    System.out.println("Alamat Pasien : " + alamatPasien);
                    System.out.println("Nomer HP Pasien : " + nomorHpPasien);
                    System.out.println("Penyakit Pasien : " + penyakitPasien);
                    System.out.println("Apakah perlu rawat Pasien : " + perluRawatInap);

                    // Menggunakan operator ternary untuk menampilkan pesan berdasarkan
                    // perluRawatInap
                    String rawatInapMessage = (perluRawatInap.equalsIgnoreCase("Y"))
                            ? "Pasien Butuh inap. Silakan lanjutkan input data rawat inap."
                            : "Pasien tidak perlu inap.";
                    System.out.println("");
                    System.out.println(rawatInapMessage);

                    if (perluRawatInap.equalsIgnoreCase("Y")) {
                        System.out.println("Silahkan Masukan kategori kelas ");
                        System.out.println("1. VIP");
                        System.out.println("2. Reguler");
                        System.out.println("3. Biasa");

                    System.out.print("Masukan Pilihan Ketegori Anda : ");
                    int kelas = inputAdmin.nextInt();

                        int lamainapVip = 0, lamainapreguler = 0,lamainapBpjs = 0, obat = 0, konsumsi = 0, total = 0, bayar = 0, kembalian = 0;
                        int donasi, kembalianAkhir = 0;

                        if (kelas == 1) {
                            System.out.print("Berapa Lama inap (hari) : ");
                            lamainapVip = inputAdmin.nextInt();
                            System.out.print("Berapa obat perhari : ");
                            obat = inputAdmin.nextInt();
                            System.out.print("Berapa Layanan Konsumsi : ");
                                konsumsi = inputAdmin.nextInt();

                                // Perhitungan total biaya rawat inap VIP
                                total = (1000000 * lamainapVip) + (obat * 500000 * lamainapVip)
                                        + (konsumsi * 500000 * lamainapVip);

                                System.out.println("Total Semua adalah : " + total);
                                System.out.print("Anda Harus Membayar : ");
                                bayar = inputAdmin.nextInt();
                                kembalian = bayar - total;
                                System.out.println("Kembalian Anda Adalah : " + kembalian);

                                // donasi
                                System.out.println("Terimah KAsih Sudah Bayar Apakah Anda Mau di Donasikan (Y/N)");
                                String donasiChoice = inputAdmin.next();

                                if (donasiChoice.equalsIgnoreCase("Y")) {
                                    System.out.print("Masukan Donasi Anda : ");
                                    donasi = inputAdmin.nextInt();
                                    kembalianAkhir = kembalian - donasi;
                                } else if (donasiChoice.equalsIgnoreCase("N")) {
                                    System.out.print("Masukan Nominal Donasi : ");
                                    donasi = inputAdmin.nextInt();
                                    kembalianAkhir = kembalian - donasi;
                                } else {
                                    donasi = 0;
                                    kembalianAkhir = kembalian;
                                }

                                System.out.println("Kembalian Akhir Anda Adalah : " + kembalianAkhir);
                                System.out.println("----------------------------");
                                System.out.println("Bukti pembayran : ");
                                System.out.println("Nama Pasien : " + namaPasien);
                                System.out.println("Alamat Pasien : " + alamatPasien);
                                System.out.println("Nomer Telpon Pasien : " + nomorHpPasien);
                                System.out.println("Penyakit Pasien : " + penyakitPasien);
                                System.out.println("---------------------------------------");
                                System.out.println("total Tagihan : " + total);
                                System.out.println("Bayar : " + bayar);
                                System.out.println("Bayar : " + bayar);
                                System.out.println("kembalian : " + kembalian);
                                System.out.println("---------------------------------------");
                                System.out.println("Donasi :" + donasi);
                                System.out.println("kembalian Anda :" + kembalianAkhir) ;
                                System.out.println("-----------------------------------------") ;
                                System.out.println("Terimah kasih Semoga Anda Sehat selalu ");
                                
                            }    
                            
                           else if (kelas == 2) {
                                System.out.print("Berapa Lama inap (hari) : ");
                                lamainapreguler = inputAdmin.nextInt();
                                System.out.print("Berapa obat perhari : ");
                                obat = inputAdmin.nextInt();
                                System.out.print("Berapa Layanan Konsumsi : ");
                                konsumsi = inputAdmin.nextInt();

                                // Perhitungan total biaya rawat inap Reguler
                                total = (100000 * lamainapreguler) + (obat * 40000 * lamainapreguler)
                                        + (konsumsi * 400000 * lamainapreguler);

                                System.out.println("Total Semua adalah : " + total);
                                System.out.print("Anda Harus Membayar : ");
                                bayar = inputAdmin.nextInt();
                                kembalian = bayar - total;
                                System.out.println("Kembalian Anda Adalah : " + kembalian);

                                // donasi
                                System.out.println("Terimah KAsih Sudah Bayar Apakah Anda Mau di Donasikan (Y/N)");
                                String donasiChoice = inputAdmin.next();

                                if (donasiChoice.equalsIgnoreCase("Y")) {
                                    System.out.print("Masukan Donasi Anda : ");
                                    donasi = inputAdmin.nextInt();
                                    kembalianAkhir = kembalian - donasi;
                                } else if (donasiChoice.equalsIgnoreCase("N")) {
                                    System.out.print("Masukan Nominal Donasi : ");
                                    donasi = inputAdmin.nextInt();
                                    kembalianAkhir = kembalian - donasi;
                                } else {
                                    donasi = 0;
                                    kembalianAkhir = kembalian;
                                }

                                System.out.println("Kembalian Akhir Anda Adalah : " + kembalianAkhir);
                                System.out.println("----------------------------");
                                System.out.println("Bukti pembayran : ");
                                System.out.println("Nama Pasien : " + namaPasien);
                                System.out.println("Alamat Pasien : " + alamatPasien);
                                System.out.println("Nomer Telpon Pasien : " + nomorHpPasien);
                                System.out.println("Penyakit Pasien : " + penyakitPasien);
                                System.out.println("---------------------------------------");
                                System.out.println("total Tagihan : " + total);
                                System.out.println("Bayar : " + bayar);
                                System.out.println("Bayar : " + bayar);
                                System.out.println("kembalian : " + kembalian);
                                System.out.println("---------------------------------------");
                                System.out.println("Donasi :" + donasi);
                                System.out.println("kembalian Anda :" + kembalianAkhir) ;
                                System.out.println("-----------------------------------------") ;
                                System.out.println("Terimah kasih Semoga Anda Sehat selalu ");

                                else if (kelas == 3) {
                                System.out.print("Berapa Lama inap (hari) : ");
                                lamainapBpjs = inputAdmin.nextInt();
                                System.out.print("Berapa obat perhari : ");
                                obat = inputAdmin.nextInt();
                                System.out.print("Berapa Layanan Konsumsi : ");
                                konsumsi = inputAdmin.nextInt();

                                // Perhitungan total biaya rawat inap Reguler
                                total = (10000 * lamainapBpjs) + (obat * 300000 * lamainapBpjs)
                                        + (konsumsi * 20000 * lamainapBpjs);

                                System.out.println("Total Semua adalah : " + total);
                                System.out.print("Anda Harus Membayar : ");
                                bayar = inputAdmin.nextInt();
                                kembalian = bayar - total;
                                System.out.println("Kembalian Anda Adalah : " + kembalian);

                                // donasi
                                System.out.println("Terimah KAsih Sudah Bayar Apakah Anda Mau di Donasikan (Y/N)");
                                String donasiChoice = inputAdmin.next();

                                if (donasiChoice.equalsIgnoreCase("Y")) {
                                    System.out.print("Masukan Donasi Anda : ");
                                    donasi = inputAdmin.nextInt();
                                    kembalianAkhir = kembalian - donasi;
                                } else if (donasiChoice.equalsIgnoreCase("N")) {
                                    System.out.print("Masukan Nominal Donasi : ");
                                    donasi = inputAdmin.nextInt();
                                    kembalianAkhir = kembalian - donasi;
                                } else {
                                    donasi = 0;
                                    kembalianAkhir = kembalian;
                                }

                                System.out.println("Kembalian Akhir Anda Adalah : " + kembalianAkhir);
                                System.out.println("----------------------------");
                                System.out.println("Bukti pembayran : ");
                                System.out.println("Nama Pasien : " + namaPasien);
                                System.out.println("Alamat Pasien : " + alamatPasien);
                                System.out.println("Nomer Telpon Pasien : " + nomorHpPasien);
                                System.out.println("Penyakit Pasien : " + penyakitPasien);
                                System.out.println("---------------------------------------");
                                System.out.println("total Tagihan : " + total);
                                System.out.println("Bayar : " + bayar);
                                System.out.println("Bayar : " + bayar);
                                System.out.println("kembalian : " + kembalian);
                                System.out.println("---------------------------------------");
                                System.out.println("Donasi :" + donasi);
                                System.out.println("kembalian Anda :" + kembalianAkhir) ;
                                System.out.println("-----------------------------------------") ;
                                System.out.println("Terimah kasih Semoga Anda Sehat selalu ");

                            }

                            } else {
                                System.out.println("Pilihan kelas tidak valid.");
                            }

                        } else {
                            System.out.println("Pasien tidak perlu rawat inap.");
                        }



                    } else {
                        System.out.println("Login gagal. Coba lagi.");
                    }
                }
            
        }
    }
}
