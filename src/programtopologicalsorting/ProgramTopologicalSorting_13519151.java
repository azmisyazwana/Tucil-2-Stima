package programtopologicalsorting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class ProgramTopologicalSorting_13519151 {

    // nama file yang akan diproses
    String FILE_NAME = "test/input.txt";

    // Buat String pesan semester. Pesan yang akan disampaikan di akhir program nanti.
    String PesanSemester = "";

    // Inisialisasi map kuliah
    Hashtable<String, Course_13519151> courses = new Hashtable<>();
    ArrayList<String> listMatkul = new ArrayList<>();

    /* 
    Integer           = semester
    ArrayList<String> = list matkul
     */
    Hashtable<Integer, ArrayList<String>> sollution = new Hashtable<>();

    // kedalaman node
    int depth = 0;

    private ArrayList<String> getNode(int init) {
        ArrayList<String> hasil = new ArrayList<>();

        int countSollution = sollution.size();              // mencari banyak solusi / banyak semester yang sudah ada
        for (int i = init - 1; i < countSollution; i++) {   // mengulang sebanyak banyak semester

            int countCourses = courses.size();              // mencari banyak course
            for (int k = 0; k < countCourses; k++) {        // mengulang sebanyak banyak course

                int countMatkul = sollution.get(i).size();  // encari banyak matkul didalam solusi
                for (int j = 0; j < countMatkul; j++) {     // mengulang sebanyak matkul yang ada didalam solusi

                    List<String> values = courses.get(listMatkul.get(k)).prerequisites;
                    if (!values.isEmpty()) {                // jika data matkulnya tidak kosong, maka proses

                        int countValues = values.size();    // mencari banyak preq didalam courses
                        int countSameValues = 0;            // variabel untuk mencari nilai yang sama
                        for (int l = 0; l < countValues; l++) {
                            if (values.get(l).equals(sollution.get(i).get(j))) {
                                countSameValues++;
                            }
                        }

                        if (countSameValues == countValues) {               // jika "banyak preq" dan "banyak nilai yang sama" nilainya sama
                            hasil.add(courses.get(listMatkul.get(k)).name); //maka masukkan kedalam solusi
                        }
                    }
                }
            }
            System.out.println("\n");

        }
        return hasil;
    }

    /* fungsi untuk sorting*/
    private void sort() {
        int countCourses = courses.size();                                        // ambil banyak course
        ArrayList<String> initSollution = new ArrayList<>();                      // array list untuk inisialisasi solusi

        /* fungsi untuk mencari kedalaman node */
        for (int i = 0; i < countCourses; i++) {
            List<String> preq = courses.get(listMatkul.get(i)).prerequisites;
            int countPreq = preq.size();

            /* mengeset kedalam node */
            if (countPreq > depth) {        // jika banyak preq lebih besar dari depth
                depth = countPreq;          // ganti nilai depth dengan banhyaknya preq
            } else if (countPreq == 0) {
                initSollution.add(courses.get(listMatkul.get(i)).name);
            }
        }
        depth++;
        /* akhir fungsi untuk mencari kedalaman node */

        if (initSollution.isEmpty()) {                       // jika tidak ada init sollution, maka tidak ada solusi
            System.out.println("Solusi tidak ditemukan!");
        } else {
            System.out.println("\n\nSolusi bisa dicari");
            sollution.put(0, initSollution);
            for (int i = 1; i < depth; i++) {
                ArrayList<String> xxx = getNode(i);
                sollution.put(i, xxx);
            }

            for (int i = 0; i < depth; i++) {
                System.out.print("Semester " + (i + 1) + " :");
                int countMatkul = sollution.get(i).size();
                for (int j = 0; j < countMatkul; j++) {
                    System.out.print(" " + sollution.get(i).get(j));
                }
                System.out.println("");
            }
        }

    }

    /* fungsi untuk menampilkan course yang berhasil tersimpan */
    private void showCourses() {
        int countCourses = courses.size();
        for (int i = 0; i < countCourses; i++) {
            List<String> preq = courses.get(listMatkul.get(i)).prerequisites;
            preq.forEach((temp) -> {
                System.out.print(temp + " ");
            });
            System.out.println();
        }
    }

    private void mainFuction() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(FILE_NAME)));
            String kodeKuliah;

            // proses pembacaan dari file input.txt
            while ((kodeKuliah = br.readLine()) != null) {
                kodeKuliah = kodeKuliah.replace(".", "");   // mengganti titik dengan spasi kosong
                String[] listKode = kodeKuliah.split(",");  // memotong baris berdasarkan koma

                /* proses memasukkan pre-requisite kedalam list */
                Course_13519151 temp = new Course_13519151(listKode[0]);
                int countPreq = listKode.length;
                for (int i = 1; i < countPreq; i++) {
                    temp.AddPrerequisite(listKode[i]);
                }
                /* akhir proses memasukkan pre-requisite kedalam list */

                courses.put(listKode[0], temp);             // menambah course

                listMatkul.add(listKode[0]);                // tambahkan kedalam listMatkul
            }

            br.close();

//            showCourses();
            sort();                                         // panggil fungsi sort

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ProgramTopologicalSorting_13519151 p = new ProgramTopologicalSorting_13519151();
        p.mainFuction();
    }

}
