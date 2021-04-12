# Demo Session Management untuk Replikasi #

Menyimpan data session di database supaya aplikasi bisa direplikasi

Cara menjalankan:

1. Jalankan aplikasi di port 10001


    ```
    cd belajar-spring-session-app
    mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=10001
    ```

2. Jalankan aplikasi sekali lagi, di port 10002. Buka command prompt baru

    ```
    cd belajar-spring-session-app
    mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=10002
    ```

3. Jalankan load balancer

    ```
    cd belajar-spring-session-lb
    mvn spring-boot:run
    ```

4. Akses ke aplikasi melalui load-balancer di [http://localhost:10000/aplikasi/](http://localhost:10000)

    > Refresh browser berkali-kali dan perhatikan alamat IP dan port. Apabila load-balancer sudah diarahkan ke dua instance, nilainya akan berubah-ubah.

5. Isi data ke dalam session di [http://localhost:10000/aplikasi/sessiondata/list](http://localhost:10000/aplikasi/sessiondata/list)

    > Apabila data session belum disimpan di database, tidak akan bisa login karena request di-round robin.
    > Coba disable salah satu backend **di load balancer**. Harusnya bisa login.
    > Setelah data session disimpan di database, aktifkan lagi kedua backend. Harusnya bisa login dan simpan data seperti biasa.

