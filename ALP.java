import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ALP {

    public static Scanner s = new Scanner(System.in);
    public static int id;
    public static int [][] jumlahPesanan = new int [100] [100];     //untuk simpan jumlah pesanan [user][jumlahpesanan]
    public static String [][] namaPesanan = new String [100] [100]; //untuk simpan jumlah pesanan [user][namapesanan]
    public static ArrayList<String> varianMenu = new ArrayList<>();     //untuk tampilan menu varian dan harga
    public static ArrayList<Integer> hargaMenu = new ArrayList<>();

        public static void tampilMenu1(){                                        //ini untuk menu awal semua
            System.out.println("===Menu===");
            System.out.println("1. Registrasi");
            System.out.println("2. Masuk");
            System.out.println("0. Keluar");
            System.out.print("Pilih Menu (1/2/0): ");
        }

        public static String [] namaA = new String [100];                          //ini untuk array simpan nama dan password
        public static String [] passA = new String [100];                       
        public static int jumlahPengguna = 0;                                         //ini untuk simpan berapa jumlah pengguna yang sudah regis supaya bisa tau nama orang tersimpan di array ke brp juga

        public static String namaP = "pemilik";                                     //ini untuk tetapkan nama dan pass pengurus
        public static String passP = "hunay123";

        public static void register() { // function untuk regist
            if (jumlahPengguna >= 100) {
                System.out.println("Registrasi gagal, kapasitas pengguna penuh");
                return;
            }
            buatNama();
        }
    
        public static void buatNama() {
            while (true) {
                System.out.print("Buat Nama : ");
                String nama = s.next();
    
                boolean namaSudahAda = false;
                for (int i = 0; i < jumlahPengguna; i++) { // untuk memastikan nama regis tidak ada yang sama dari array
                                                           // index 0
                    if (namaA[i].equals(nama)) {
                        System.out.println("Nama telah digunakan, coba nama lain\n");
                        namaSudahAda=true;
                        break;
                    }
                }
    
                if (!namaSudahAda){
                System.out.print("Buat Kata Sandi : ");
                String pass = s.next();
    
                if (pass.equals(passP)) {
                    System.out.println("Udh dibilang gblh\n");
                    return;
                }
    
                System.out.println("\nNama : " + nama);
                System.out.println("Kata Sandi : " + pass);
    
                namaA[jumlahPengguna] = nama; // untuk simpan nama dan pass yang diisi di regis ke array dengan index yang
                                              // sudah disimpan juga dalam int
                passA[jumlahPengguna] = pass;
                jumlahPengguna++;
    
                System.out.println("Registrasi sudah berhasil\n");
                return;
            }
        }
        }
        
        public static void login(){                                                           //ini untuk login stelah registrasi
            System.out.print("Masukkan Nama : ");
            String nama = s.next();

            System.out.print("Masukkan Kata Sandi : ");
            String pass = s.next();
            
            if (nama.equals(namaP) && pass.equals(passP)){
                menuP1();
                return;
            }

            boolean userAda = false;
            boolean passBenar = false;

            for(int i = 0; i < jumlahPengguna; i++){
                if (namaA[i].equals(nama)){
                    userAda = true;
                    if (passA[i].equals(pass)){
                        passBenar = true;
                        System.out.println("Berhasil Masuk");
                        id = i;
                        menu2();
                        return;
                    }
                }
            }
            if (userAda){
                System.out.println("Nama atau Password Salah");
            } else {
                System.out.println("Silahkan Registrasi Terlebih Dahulu");
            }
        }

        public static void menu2() {                                                 //ini untuk menu yang ditampilkan untuk mahasiswa
            while (true) { 
                try {
                    welcome();
                    System.out.println("==Menu==");
                    System.out.println("1. Pesan");
                    System.out.println("2. Riwayat");
                    System.out.println("0. Keluar");
                    System.out.print("Pilih opsi (1/2/0): ");
                    int pilih2 = s.nextInt();

                    switch(pilih2){
                        case 1 :
                            pesan();
                            break;
                        case 2 : 
                            riwayat();
                            break;
                        case 0 : 
                            return;
                        default :
                            System.out.println("Masukkan Pilihan Yang Tesedia");
                    }
                } catch (Exception e) {
                    System.out.println("Pilihan Tidak Sesuai, Masukkan Sesuai Pilihan Yang Tersedia");
                    s.nextLine();
                }
                
            }    
        }

        public static void pesan1(){
            daftarVarian();
            
            for(int b=0; b<varianMenu.size(); b++){                             //untuk ngeloop tampilkan menu dari awal
                System.out.println((b+1) + ". " + varianMenu.get(b) + " Rp." + hargaMenu.get(b));          //untuk tampilkan menu sesuai dengan format
            }
        }

        public static void pesan(){                 //untuk pesan di menu mahasiswa
            pesan1();
            int index = 0;                  //bagian pertama untuk cek untuk orang pertama kali pesan atau tdk
            if (namaPesanan[id][0] == null){            //untuk pastikan pengguna sesuai id belum pernah pesan sblmnya karna index 0 itu masih kosong 
                index = 0;                              //kalo pesanannya pertama brrti pake index ini = 0 (tersimpan di inndex 0)
            } else {                                        //untuk cek slot ketika user sudah pernah beli
                for(int i=0; i<namaPesanan[id].length; i++){               //untuk loop pesanan orang kesimpan smpe 99
                    if(namaPesanan[id][i] == null){     //untuk periksa index selanjutnya kosong atau tidak agar bisa diisi untuk pesanan barunya
                        index = i;                 //setelah priksa misal sdh ada yg kosong langsung disimpan di index selanjutnya
                        break;
                    }
                }
            }

            int pilihPesan;
            while(true){
                System.out.println("");
                System.out.print("Ingin membeli apa? (1/2/3) ");
                try {
                    pilihPesan = s.nextInt();

                    if(pilihPesan >= 1 && pilihPesan <= 3){
                        break;
                    } else {
                        System.out.println("Pilihan Tidak Valid, Pilih pilihan Yang Ada");
                    }
                } catch (Exception e){
                    System.out.println("Masukan Pilihan yang Tersedia");
                    s.nextLine();
                }
                
            }
            
            String namaBarang1 = varianMenu.get(pilihPesan - 1);
            // int hargaBarang1 = hargaMenu.get(pilihPesan - 1);

            // namaPesanan[id][index] = varianMenu.get(pilihPesan-1);              //menu yang dipilih sesuai pilihan pesanan tersimpan dlm index yang sudah di cek diatas
            System.out.print("Ingin pesan berapa? (dalam angka) ");
            int jumlahBarang1 = s.nextInt();
            // jumlahPesanan[id][index]= s.nextInt();

            boolean barangSudahAda = false;
            
            for(int i = 0; i < namaPesanan[id].length; i++){
                if(namaPesanan[id][i] != null && namaPesanan[id][i].equals(namaBarang1)){
                    jumlahPesanan[id][i] += jumlahBarang1;
                    barangSudahAda = true;
                    break;
                }
            }

            if(!barangSudahAda){
                for(int i = 0; i < namaPesanan[id].length; i++){
                    if(namaPesanan[id][i] == null){
                        namaPesanan[id][i] = namaBarang1;
                        jumlahPesanan[id][i] = jumlahBarang1;
                        break;
                    }
                }
            }

            if (jumlahPesanan[id][index] == 0){
                namaPesanan[id][index] = null;
                jumlahPesanan[id][index] = 0;
            }

            System.out.println("");
            System.out.println("Pesanan anda saat ini: ");

            boolean adaPesanan = false;
            for (int p=0; p < namaPesanan[id].length; p++){         //untuk tampilkan smua pesanan yg di pesan 
                if (namaPesanan[id][p] != null) {       //kalo pesanannya blm ada stop
                    adaPesanan = true;

                    String namaBarang = namaPesanan[id][p];      
                    int jumlahBarang = jumlahPesanan[id][p];
                    int hargaBarang = hargaMenu.get(varianMenu.indexOf(namaBarang));    //harganya ambil dri menu berdasar index nama barang

                    System.out.println((p + 1) + ". " + namaBarang + " (" + jumlahBarang + ")" +  " --> Rp. " + hargaBarang + " = " + (jumlahBarang * hargaBarang)); //kalo ada pesanan baru ditampilkan
                } 
            }
            if(!adaPesanan){
                System.out.println("-");
            }
            tambahPesanan();  
        }
        
        public static void tambahPesanan(){
            while(true){
                System.out.println("");
                System.out.print("Ingin menambah pesanan? (y/n) ");
                String tambah = s.next();
                if (tambah.equalsIgnoreCase("y")){
                    pesan();
                    return;
                } else if (tambah.equalsIgnoreCase("n")) {
                    break;
                }else {
                    System.out.println("masukan pilihan y/n");
                }
            }
        }

        public static void riwayat(){                       //untuk pesan di menu mahasiswa
            System.out.println("");
            System.out.println("===Riwayat Pesanan Anda===");

            boolean adaPesanan = false;
            for (int p=0; p < namaPesanan[id].length; p++){                                //namaPesanan[id] merujuk pada array 2 dimensi namaPesanan 2D dimana [usesr][pesanan]
                if (namaPesanan[id][p] != null) {           
                    adaPesanan = true;
                    break;
                }
            }
            if (!adaPesanan){
                System.out.println("Belum Ada Pesanan, Silahkan Pesan terlebih dahulu");
                return;
            }

            int totalPesanan = 0;
            int totalHarga = 0;

            for (int p=0; p < namaPesanan[id].length; p++){                                //namaPesanan[id] merujuk pada array 2 dimensi namaPesanan 2D dimana [usesr][pesanan]
                if (namaPesanan[id][p] == null) {           //kalo pesanannya masih kosong indexnya brrti stop
                    break;
                }
                String namaBarang = namaPesanan[id][p];      
                    int jumlahBarang = jumlahPesanan[id][p];
                    int hargaBarang = hargaMenu.get(varianMenu.indexOf(namaBarang));    //harganya ambil dri menu berdasar index nama barang
                    totalHarga += jumlahBarang * hargaBarang;

                    System.out.println((p + 1) + ". " + namaBarang + " (" + jumlahBarang + ")" +  " --> Rp. " + hargaBarang + " = " + (jumlahBarang * hargaBarang));
                    
                    totalPesanan += jumlahPesanan[id][p];    
                
            }
            System.out.println("Total item dipesan : " + totalPesanan);
                System.out.println("Total Harga Pesanan : " + totalHarga);
                System.out.println();

            while (true){
                System.out.println("");
                System.out.print("Ingin mengubah pesanan ? (y/n) ");
                String yNkurang = s.next();
                if(yNkurang.equalsIgnoreCase("y")){
                    System.out.println("");
                    ubahPesanan();
                } else if (yNkurang.equalsIgnoreCase("n")){
                    break;
                } else {
                    System.out.println("masukkan pilihan y/n");
                }
            }
        }

        public static void ubahPesanan() { // fungsi untuk mengubah pesanan
            System.out.println("Daftar Pesanan : ");
            for (int p = 0; p < namaPesanan[id].length; p++) { // menampilkan semua pesanan pengguna saat ini
                if (namaPesanan[id][p] == null) {
                    break;
                }
                System.out.println((p + 1) + ". " + namaPesanan[id][p] + " : " + jumlahPesanan[id][p]);
            }
            ubahP();
        }
    
        public static void ubahP() {
            while (true) {
                try {
                    System.out.print("Pilih pesanan yang ingin diubah: ");
                    int ubahPesan = s.nextInt();
        
                    boolean pilihGaSesuai = false;
                    for (int i = 0; i < namaPesanan[id].length; i++) {
                        if ((namaPesanan[id][i] == null && ubahPesan > i) || ubahPesan == 0) {
                            pilihGaSesuai = true;
                            System.out.println("Masukkan pilihan yang sesuai\n");
                            break;
                        }
                    }
        
                    if (pilihGaSesuai) {
                        continue; // ulangi loop jika input tidak sesuai
                    }
        
                    int index2 = ubahPesan - 1;
        
                    System.out.println("Pesanan Saat Ini: ");
                    System.out.println(namaPesanan[id][index2] + " : " + jumlahPesanan[id][index2]);
        
                    int jumlahBaru;
                    while (true) { // loop untuk validasi input jumlah baru
                        try {
                            System.out.print("Masukkan jumlah baru untuk pesanan ini: ");
                            jumlahBaru = s.nextInt();
                            break; // keluar dari loop jika input valid
                        } catch (InputMismatchException e) {
                            System.out.println("Masukkan jumlah yang valid (hanya angka)");
                            s.nextLine(); 
                        }
                    }
        
                    if (jumlahBaru == 0) { // jika jumlah baru adalah 0, pesanan akan dihapus
                        System.out.println("Pesanan " + namaPesanan[id][index2] + " dihapus");
                        namaPesanan[id][index2] = null;
                        for (int j = index2; j < namaPesanan[id].length - 1; j++) { // menggeser pesanan berikutnya ke index yang kosong
                            namaPesanan[id][j] = namaPesanan[id][j + 1];
                            jumlahPesanan[id][j] = jumlahPesanan[id][j + 1];
                        }
                    } else { // jika jumlah baru bukan 0, perbarui jumlah pesanan
                        jumlahPesanan[id][index2] = jumlahBaru;
                        System.out.println(
                                "Pesanan " + namaPesanan[id][index2] + " diperbarui menjadi " + jumlahPesanan[id][index2]);
                    }
        
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Masukkan pilihan yang sesuai (hanya angka)");
                    s.nextLine(); 
                }
            }
        }
        

        public static void menuP1(){     
            while (true) {
                try {
                    haloPengurus();
                    System.out.println("===MENU===");
                    System.out.println("1. Total data");
                    System.out.println("2. Tampilan setiap anak");
                    System.out.println("0. Keluar");
                    System.out.print("pilih menu (1/2/0): ");
                    int pilihP1 = s.nextInt();

                    switch(pilihP1){
                        case 1 : 
                            totalData();
                            break;
                        case 2 : 
                            dataPerAnak();
                            break;
                        case 0 :
                            return;
                        default : 
                            System.out.println("Pilihan Tidak Tersedia");
                    }
                } catch (Exception e) {
                    System.out.println("Pilihan Tidak Sesuai, Masukkan Sesuai Pilihan Yang Tersedia");
                    s.nextLine();
                }
                
            }
            
        }
        
        public static void totalData(){             //untuk total data di menu pengurus
            System.out.println("");
            System.out.println("==Total Data==");
            
            int totalBawangGoreng = 0;
            int totalSambal = 0;
            int totalRambak = 0;

            int totalSemuaPesanan = 0;
            int totalSemuaHarga = 0;

            for (int i = 0; i <jumlahPengguna ; i++){
                for(int j = 0; j < namaPesanan[i].length; j++){
                    if("Bawang goreng".equals(namaPesanan[i][j])){
                        totalBawangGoreng += jumlahPesanan[i][j];
                    }
                    if("Sambal".equals(namaPesanan[i][j])){
                        totalSambal += jumlahPesanan[i][j];
                    }
                    if("Rambak pisang".equals(namaPesanan[i][j])){
                        totalRambak += jumlahPesanan[i][j];
                    }
                }
            }
            
            for(int i = 0; i < jumlahPengguna; i++){
                for(int j = 0; j < namaPesanan[i].length; j++){     //loop untuk pesanan ke index yg di loop dari pengguna sesuai index yg di loop
                    if(namaPesanan[i][j] == null){
                        break;
                    }
                    int hargaBarang = hargaMenu.get(varianMenu.indexOf(namaPesanan[i][j]));        //harganya ambil dri menu berdasar index nama barang

                    totalSemuaPesanan += jumlahPesanan[i][j];      //jumlah semua barang yang dipesan
                    totalSemuaHarga += jumlahPesanan[i][j] * hargaBarang;      //total harga semua barang 
                }
            }

            if (totalBawangGoreng!=0){
                System.out.println("1. Bawang Goreng : " + totalBawangGoreng);
            }
            
            if (totalSambal!=0){
                System.out.println("2. Sambal : " + totalSambal);
            }
            
            if (totalRambak!=0){
                System.out.println("3. Rambak Pisang : " + totalRambak);
            }
            
            System.out.println("Total Item yang Dipesan : " + totalSemuaPesanan);
            System.out.println("Total Harga Pesanan : " + totalSemuaHarga);
        }

        public static void dataPerAnak(){               //untuk data per anak di menu pengurus
            System.out.println("");
            System.out.println("==Total Data Per Anak==");
            
            if (jumlahPengguna == 0){
                System.out.println("Belum ada pesanan");
            }

            for(int i = 0; i < jumlahPengguna; i++){          //ngeloop untuk setiap anak dari index 0
                System.out.println("Pengguna : " + namaA[i]);
                int totalPesanan = 0;
                int totalHarga = 0;

                for (int j = 0; j < namaPesanan[i].length; j++){    //ngloop pesanan anaknya 
                    if(namaPesanan[i][j] == null){
                        break;
                    }
                    
                    String namaBarang = namaPesanan[i][j];      
                    int jumlahBarang = jumlahPesanan[i][j];
                    int hargaBarang = hargaMenu.get(varianMenu.indexOf(namaBarang));    //harganya ambil dri menu berdasar index nama barang
                    totalHarga += jumlahBarang * hargaBarang;

                    System.out.println((j + 1) + ". " + namaBarang + " (" + jumlahBarang + ")" +  " --> Rp. " + hargaBarang + " = " + (jumlahBarang * hargaBarang));
                    
                    totalPesanan += jumlahPesanan[i][j];    //total pesanan dijumlah semuanya untuk dapat total smua pesanan
                }
                System.out.println("Total item dipesan : " + totalPesanan);
                System.out.println("Total Harga Pesanan : " + totalHarga);
                System.out.println();
            }
        }
        
        public static void judul (){
            System.out.println("");
            System.out.println(" _  __          _   _              _           _                      _   _                                 \r\n" + //
                                "| |/ /   __ _  | | | | __  _   _  | |   __ _  | |_    ___    _ __    | | | |  _   _   _ __     __ _   _   _ \r\n" + //
                                "| ' /   / _` | | | | |/ / | | | | | |  / _` | | __|  / _ \\  | '__|   | |_| | | | | | | '_ \\   / _` | | | | |\r\n" + //
                                "| . \\  | (_| | | | |   <  | |_| | | | | (_| | | |_  | (_) | | |      |  _  | | |_| | | | | | | (_| | | |_| |\r\n" + //
                                "|_|\\_\\  \\__,_| |_| |_|\\_\\  \\__,_| |_|  \\__,_|  \\__|  \\___/  |_|      |_| |_|  \\__,_| |_| |_|  \\__,_|  \\__, |\r\n" + //
                                "                                                                                                      |___/ ");
            System.out.println("");
        }

        public static void welcome(){
            System.out.println("");
            System.out.println(" ___  ____  __      __    __  __    __   ____    ____    __   ____   __    _  _  ___ \n" + //
                                "/ __)( ___)(  )    /__\\  (  \\/  )  /__\\ (_  _)  (  _ \\  /__\\ (_  _) /__\\  ( \\( )/ __)\n" + //
                                "\\__ \\ )__)  )(__  /(__)\\  )    (  /(__)\\  )(     )(_) )/(__)\\  )(  /(__)\\  )  (( (_-.\n" + //
                                "(___/(____)(____)(__)(__)(_/\\/\\_)(__)(__)(__)   (____/(__)(__)(__)(__)(__)(_)\\_)\\___/");
            System.out.println("");
        }

        public static void daftarVarian(){
            System.out.println("");
            System.out.println("___  ____ ____ ___ ____ ____    _  _ ____ ____ _ ____ _  _\r\n" + //
                                "|  \\ |__| |___  |  |__| |__/    |  | |__| |__/ | |__| |\\ |\r\n" + //
                                "|__/ |  | |     |  |  | |  \\     \\/  |  | |  \\ | |  | | \\|");
            System.out.println("");
        }

        public static void haloPengurus(){
            System.out.println("");
            System.out.println("/*  _   _           _             ____                                                       */\r\n" + //
                                "/* | | | |   __ _  | |   ___     |  _ \\    ___   _ __     __ _   _   _   _ __   _   _   ___  */\r\n" + //
                                "/* | |_| |  / _` | | |  / _ \\    | |_) |  / _ \\ | '_ \\   / _` | | | | | | '__| | | | | / __| */\r\n" + //
                                "/* |  _  | | (_| | | | | (_) |   |  __/  |  __/ | | | | | (_| | | |_| | | |    | |_| | \\__ \\ */\r\n" + //
                                "/* |_| |_|  \\__,_| |_|  \\___/    |_|      \\___| |_| |_|  \\__, |  \\__,_| |_|     \\__,_| |___/ */\r\n" + //
                                "/*                                                       |___/                               */");
            System.out.println("");
        }
        
        public static void terimaKasih(){
            System.out.println("");
            System.out.println("████████╗███████╗██████╗ ██╗███╗   ███╗ █████╗     ██╗  ██╗ █████╗ ███████╗██╗██╗  ██╗\r\n" + //
                                "╚══██╔══╝██╔════╝██╔══██╗██║████╗ ████║██╔══██╗    ██║ ██╔╝██╔══██╗██╔════╝██║██║  ██║\r\n" + //
                                "   ██║   █████╗  ██████╔╝██║██╔████╔██║███████║    █████╔╝ ███████║███████╗██║███████║\r\n" + //
                                "   ██║   ██╔══╝  ██╔══██╗██║██║╚██╔╝██║██╔══██║    ██╔═██╗ ██╔══██║╚════██║██║██╔══██║\r\n" + //
                                "   ██║   ███████╗██║  ██║██║██║ ╚═╝ ██║██║  ██║    ██║  ██╗██║  ██║███████║██║██║  ██║\r\n" + //
                                "   ╚═╝   ╚══════╝╚═╝  ╚═╝╚═╝╚═╝     ╚═╝╚═╝  ╚═╝    ╚═╝  ╚═╝╚═╝  ╚═╝╚══════╝╚═╝╚═╝  ╚═╝");
                                System.out.println("");
        }
        

        public static void main (String[]args){
            varianMenu.add("Bawang goreng");        //index tersimpan sesuai urutan
            hargaMenu.add(30000);               //harga menu tersimpan sesuai dengan index varian menunya 

            varianMenu.add("Sambal");
            hargaMenu.add(25000);

            varianMenu.add("Rambak pisang");
            hargaMenu.add(25000);
            while (true) {
                try {
                    judul();
                    tampilMenu1();
                    int pilih = s.nextInt();
                    switch (pilih) {
                        case 1 : 
                            register();
                            break;
                        case 2 :
                            login();
                            break;
                        case 0 : 
                            terimaKasih();
                            System.exit(0);
                            break;
                        default : 
                            System.out.println("Masukkan Pilihan Yang Tersedia");
                    }
                } catch (Exception e) {
                    System.out.println("Pilihan Tidak Sesuai, Masukkan Sesuai Pilihan Yang Tersedia");
                    s.nextLine();
                }
            }
    }
}